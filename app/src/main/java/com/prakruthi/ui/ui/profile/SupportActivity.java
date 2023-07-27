package com.prakruthi.ui.ui.profile;

import static android.content.ContentValues.TAG;

import static com.prakruthi.ui.Variables.city;
import static com.prakruthi.ui.Variables.district;
import static com.prakruthi.ui.Variables.email;
import static com.prakruthi.ui.Variables.id;
import static com.prakruthi.ui.Variables.name;
import static com.prakruthi.ui.Variables.state;
import static com.prakruthi.ui.Variables.token;
import static com.prakruthi.ui.Variables.userId;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

//import com.prakruthi.Manifest;
import com.prakruthi.R;
import com.prakruthi.ui.APIs.GetRecentViewProductsAPI;
import com.prakruthi.ui.APIs.GetUserDataApi;
import com.prakruthi.ui.APIs.SupportApi;
import com.prakruthi.ui.APIs.UserDetailsUpdateApi;
import com.prakruthi.ui.Variables;
import com.prakruthi.ui.misc.Loading;
import com.saadahmedsoft.popupdialog.PopupDialog;
import com.saadahmedsoft.popupdialog.Styles;
import com.saadahmedsoft.popupdialog.listener.OnDialogButtonClickListener;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SupportActivity extends AppCompatActivity implements SupportApi.OnSupportApiHitFetchedListner {


    TextView tvId, tvPhone, tvEmail, tvAddress;

    TextView tvcall1,tvcall2, tvemail, tvlink;

    ProfileSupportResponse.ProfileSupportModel profileSupportModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        tvId = findViewById(R.id.tv_support_id);
        tvPhone = findViewById(R.id.tv_support_phone);
        tvEmail = findViewById(R.id.tv_support_email);
        tvAddress = findViewById(R.id.tv_support_address);

        tvcall1 = findViewById(R.id.tvcall1);
        tvcall2 = findViewById(R.id.tvcall2);
        tvlink = findViewById(R.id.tvlink);
        tvemail = findViewById(R.id.tvemail);


        SetTextViews();

        UserDetailsUpdateApi();

        SetClickListeners();

        SupportApi supportApi = new SupportApi(this);
//        getUserDataApi.execute();
        supportApi.HitSupportApi();

    }

    public void SetTextViews() {

//        binding.txtId.setText("");
//        binding.txtId.append(String.valueOf(Variables.name));

        tvId.setText("");
        tvId.append(String.valueOf(id));




        tvPhone.setText("");
        tvPhone.append(String.valueOf(Variables.mobile));

        tvEmail.setText("");
        tvEmail.append(String.valueOf(Variables.email));

        tvAddress.setText("");
        tvAddress.append(String.valueOf(Variables.address));


        SupportApi getRecentViewProductsAPI = new SupportApi(this);
        getRecentViewProductsAPI.HitSupportApi();

//        GetRecentViewProductsAPI getRecentViewProductsAPI = new GetRecentViewProductsAPI(this);
//        getRecentViewProductsAPI.HitRecentApi();
//
//        GetUserDataApi getUserDataApi = new GetUserDataApi(this);
//        getUserDataApi.HitGetUserDataApi();
//
//        UserDetailsUpdateApi userDetailsUpdateApi = new UserDetailsUpdateApi(this, name, email, city, state, district, userId, token);
//        userDetailsUpdateApi.HitUserDetailsUpdateApi();

//        userDetailsUpdateApi.execute();

        //Retrofit:---
//        if (editProfileActivity2 != null && editProfileActivity2.isVisible()) {
//            editProfileActivity2.updateDetails(responseProfile.getData());
//        }


    }

    public void SetClickListeners() {

        tvlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String url="http://fitnesswala.in";
                //info@prakruthiepl.com
//                String url="info@prakruthiepl.com";
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));//for a large pdf file to load into webview use this intent
//                startActivity(browserIntent);
                overridePendingTransition(R.anim.trans_left_in,R.anim.trans_left_out);
            }
        });

        tvemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL  , new String[] {  "info@prakruthiepl.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");

                startActivity(Intent.createChooser(intent, "Email via..."));
                overridePendingTransition(R.anim.trans_left_in,R.anim.trans_left_out);

            }
        });


        tvcall1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchDialer();
                if (ActivityCompat.checkSelfPermission(SupportActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }

            }
        });

        tvcall2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchDialer2();
                if (ActivityCompat.checkSelfPermission(SupportActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }

            }
        });



    }

    private void launchDialer()
    {
        // No permisison needed
        Intent callIntent = new Intent(Intent.ACTION_DIAL);

        callIntent.setData(Uri.parse("tel:"+getPhoneNumber()));
//        callIntent.setData(Uri.parse("tel:"+getPhoneNumber2()));
        startActivity(callIntent);
        overridePendingTransition(R.anim.trans_left_in,R.anim.trans_left_out);

    }

    private void launchDialer2()
    {
        // No permisison needed
        Intent callIntent = new Intent(Intent.ACTION_DIAL);

//        callIntent.setData(Uri.parse("tel:"+getPhoneNumber()));
        callIntent.setData(Uri.parse("tel:"+getPhoneNumber2()));
        startActivity(callIntent);
        overridePendingTransition(R.anim.trans_left_in,R.anim.trans_left_out);

    }


    private String getPhoneNumber(){
        TextView phonenumber=(TextView) findViewById(R.id.tvcall1);

        return phonenumber.getText().toString();
    }

    private String getPhoneNumber2(){
        TextView phonenumber2=(TextView) findViewById(R.id.tvcall2);

        return phonenumber2.getText().toString();
    }




    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.bottom_down, R.anim.bottom_down);
    }


    public void UserDetailsUpdateApi() {

        SupportApi supportApi = new SupportApi(this);
        supportApi.HitSupportApi();

//        UserDetailsUpdateApi userDetaialsUpdateApi = new UserDetailsUpdateApi(this, name, email, city, state, district, userId, token);
//        userDetaialsUpdateApi.HitUserDetailsUpdateApi();
////        userDetaialsUpdateApi.execute();
//
//        GetRecentViewProductsAPI getRecentViewProductsAPI = new GetRecentViewProductsAPI(this);
//        getRecentViewProductsAPI.HitRecentApi();

    }



    @Override
    public void OnSupportApiGivesSuccess(ArrayList<ProfileSupportResponse.ProfileSupportModel> profileSupportModels) {
        try {
            SupportActivity.this.runOnUiThread(() -> {
//                UserDetailsUpdateApi();
                Log.e("TAG", String.valueOf(profileSupportModels));


                SupportApi getUserDataApi = new SupportApi(this);
                getUserDataApi.HitSupportApi();

//                UserDetailsUpdateApi userDetaialsUpdateApi = new UserDetailsUpdateApi(this, name, email, city, state, district, userId, token);
//                userDetaialsUpdateApi.HitUserDetailsUpdateApi();
            });
        } catch (Exception e) {
            Log.e("TAG", e.toString());
        }

    }

    @Override
    public void OnSupportApiGivesError(String error) {
        try {
            SupportActivity.this.runOnUiThread(() -> {

//                UserDetailsUpdateApi();

                try {

//                    Loading.hide();
                    Toast.makeText(SupportActivity.this, error, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(requireContext(), "No Data Found", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                PopupDialog.getInstance(SupportActivity.this).setStyle(Styles.FAILED).setHeading("Uh-Oh").setDescription("Unexpected error occurred." + " Try again later.").setCancelable(false).showDialog(new OnDialogButtonClickListener() {
                    @Override
                    public void onDismissClicked(Dialog dialog) {
                        super.onDismissClicked(dialog);
                    }
                });

            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @SuppressLint("StaticFieldLeak")
    public void ApiTaskPro() {
        Loading.show(SupportActivity.this);
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                //Creating array for parameters
                String[] field = new String[2];
                field[0] = "user_id";
                field[1] = "token";
                //Creating array for data
                String[] data = new String[2];

                data[0] = userId;
                data[1] = token;
                PutData putData = new PutData(Variables.BaseUrl + "support", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        // result = Api Result
                        String result = putData.getResult();
                        return result;
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(String result) {
//                super.onPostExecute(s);
                super.onPostExecute(result);


                if (result != null) {
                    try {
                        Log.e(TAG, result);
                        JSONObject json = new JSONObject(result);
                        boolean statusCode = json.getBoolean("status_code");
                        String message = json.getString("message");
                        if (statusCode) {
                            Toast.makeText(SupportActivity.this, message, Toast.LENGTH_SHORT).show();
                            getUserData(json);
                        } else {
                            Toast.makeText(SupportActivity.this, message, Toast.LENGTH_SHORT).show();

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
//                        login.setVisibility(View.VISIBLE);
                        Toast.makeText(SupportActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                    }
                } else {
//                    login.setVisibility(View.VISIBLE);
                    Toast.makeText(SupportActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                }
//                login.setVisibility(View.VISIBLE);
                Loading.hide();
            }
        }.execute();

    }


    public void getUserData(JSONObject ResultJson) {
        try {
//            String token = ResultJson.getString("token");
            String status_code = ResultJson.getString("status_code");
            String message = ResultJson.getString("message");

//            JSONArray userDetailsArray = ResultJson.getJSONArray("user_details");
            JSONArray userDetailsArray = ResultJson.getJSONArray("data");


            JSONObject userDetails = userDetailsArray.getJSONObject(0);

            String id = String.valueOf(userDetails.getInt("id"));
            String name = userDetails.optString("name", "");
            String email = userDetails.optString("email", "");
            String address = userDetails.optString("address", "");


            // Store values in static variables
            Variables.clear();

            Variables.status_code = status_code;
            Variables.message = message;

//            Variables.token = token;
//            Variables.id = id;
//            Variables.departmentId = departmentId;

            Variables.id = Integer.valueOf(String.valueOf(id));
            Variables.name = name;
            Variables.email = email;
            Variables.address= address;


//            Log.e(TAG, Variables.id+Variables.token );

//            iv_edit.setVisibility(View.VISIBLE);
            Loading.hide();
//            if (RememberMe.isChecked())
//                rememberMe();
            startActivity(new Intent(this, SupportActivity.class));
//            finish();
        } catch (JSONException e) {
            Log.e(TAG, e.toString());
            Toast.makeText(this, "Internal Error", Toast.LENGTH_SHORT).show();
//            iv_edit.setVisibility(View.VISIBLE);
//            LoginLayout.setVisibility(View.VISIBLE);
        }

    }



}