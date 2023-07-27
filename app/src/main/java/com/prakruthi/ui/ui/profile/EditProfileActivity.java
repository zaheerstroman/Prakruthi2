//package com.prakruthi.ui.ui.profile;
//
//import static android.content.ContentValues.TAG;
//
//import static com.prakruthi.ui.Variables.city;
//import static com.prakruthi.ui.Variables.district;
//import static com.prakruthi.ui.Variables.email;
//import static com.prakruthi.ui.Variables.name;
//import static com.prakruthi.ui.Variables.state;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.RequiresApi;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.AppCompatButton;
//
//import android.animation.ObjectAnimator;
//import android.annotation.SuppressLint;
//import android.app.Dialog;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.net.Uri;
//import android.os.AsyncTask;
//import android.os.Build;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.util.Log;
//import android.util.Patterns;
//import android.view.MotionEvent;
//import android.view.View;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.android.material.imageview.ShapeableImageView;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.messaging.FirebaseMessaging;
//import com.google.gson.Gson;
//import com.prakruthi.R;
//import com.prakruthi.ui.APIs.GetRecentViewProductsAPI;
//import com.prakruthi.ui.APIs.GetUserDataApi;
//import com.prakruthi.ui.APIs.UserDetailsUpdateApi;
//import com.prakruthi.ui.Login;
//import com.prakruthi.ui.OTP_Verification;
//import com.prakruthi.ui.RegistrationForm;
//import com.prakruthi.ui.Variables;
//import com.prakruthi.ui.misc.Loading;
//import com.saadahmedsoft.popupdialog.PopupDialog;
//import com.saadahmedsoft.popupdialog.Styles;
//import com.saadahmedsoft.popupdialog.listener.OnDialogButtonClickListener;
//import com.skydoves.powerspinner.OnSpinnerItemSelectedListener;
//import com.skydoves.powerspinner.PowerSpinnerView;
//import com.vishnusivadas.advanced_httpurlconnection.FetchData;
//import com.vishnusivadas.advanced_httpurlconnection.PutData;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
////public class EditProfileActivity extends AppCompatActivity implements UserDetailsUpdateApi.OnUserDetailsUpdateApiGivesFetchedListner{
//public class EditProfileActivity extends AppCompatActivity implements UserDetailsUpdateApi.OnUserDetailsUpdateApiGivesFetchedListner {
//
////    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
//
//    EditText et_first_name, et_email, et_city;
//    PowerSpinnerView spinner_state, spinner_district;
//
//    int stateId = 0;
//
//    Button btn_save;
//    AppCompatButton sendotp, backbtn;
//    SharedPreferences sharedPreferences;
//    ArrayList<String> districtNames = new ArrayList<>();
////    ArrayList<String> districtNames = null;
//
//    //    private EditProfileFragment.DismissListener dismissListener;
//    private EditProfileActivity.DismissListener dismissListener;
//
//    CheckBox RememberMe;
//
//    ProfileGetUserDataResponse.ProfileGetUserDataModel profileGetUserDataModel;
//    EditProfileUpdateDrailsUpdateModels editProfileUpdateDrailsUpdateModels;
//
//
//    //    @SuppressLint("WrongViewCast")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_edit_profile);
//
//        btn_save = findViewById(R.id.btn_save);
////        PowerSpinnerView powerSpinnerView = findViewById(R.id.powerSpinnerView);
//
//        et_first_name = findViewById(R.id.et_first_name);
//        et_email = findViewById(R.id.et_email);
//        et_city = findViewById(R.id.et_city);
//        spinner_state = findViewById(R.id.spinner_state);
//        spinner_district = findViewById(R.id.spinner_district);
//
//        backbtn = findViewById(R.id.iv_back);
//
//        setdata();
//
//
////        List<String> items = new ArrayList<>();
////        items.add("Item 1");
////        items.add("Item 2");
////        items.add("Item 3");
////        spinner_state.setItems(items);
////        spinner_district.setItems(items);
//
//
//        //Activity
////        profileGetUserDataModel = new Gson().fromJson(getIntent().getStringExtra("data"), ProfileGetUserDataResponse.ProfileGetUserDataModel.class);
////        editProfileUpdateDrailsUpdateModels == new Gson().fromJson(getIntent().getStringExtra("data"), ProfileGetUserDataResponse.ProfileGetUserDataModel.class);
//
//        //Fragment
////        if (getArguments() != null) {
////            String data = getArguments().getString("data");
////            profileGetUserDataModel = new Gson().fromJson(data, ProfileGetUserDataResponse.ProfileGetUserDataModel.class);
////        }
//
//        // Getting DropDown Arrays
//        getDropDownData();
//
//        spinner_state.setOnSpinnerItemSelectedListener((OnSpinnerItemSelectedListener<String>) (oldIndex, oldItem, newIndex, newItem) -> {
//            stateId = newIndex + 1;
//            getDropDownData();
//            spinner_state.setError(null);
//        });
//
//        spinner_district.setOnClickListener(v -> {
//            if (stateId == 0) {
//                spinner_state.setError("Select State");
//            } else spinner_district.showOrDismiss();
//        });
//
//        getFCMToken();
//
//
//
//        //Fragment
////        View root = view.getRootView();
//        //Activity
//        View root = findViewById(android.R.id.content);
//        root.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    // check if the touch is outside of the state view
//                    int[] location = new int[2];
//                    spinner_state.getLocationOnScreen(location);
//                    int x = location[0];
//                    int y = location[1];
//                    int width = spinner_state.getWidth();
//                    int height = spinner_state.getHeight();
//                    if (!(event.getX() > x && event.getX() < x + width && event.getY() > y && event.getY() < y + height)) {
//                        // dismiss the state view
//                        spinner_state.dismiss();
//                        spinner_district.dismiss();
////                        type.dismiss();
//                        return true; // consume the event
//                    }
//                }
//                return false; // do not consume the event
//            }
//        });
//
//        backbtn.setOnClickListener(view -> {
//            super.onBackPressed();
//        });
//
////        btn_save.setOnClickListener(new View.OnClickListener() {
//        btn_save.setOnClickListener(v -> {
//
////            @Override
////            public void onClick(View v) {
//            spinner_state.setError(null);
//            spinner_district.setError(null);
////            type.setError(null);
//            if (et_first_name.getText().toString().trim().isEmpty()) {
//                et_first_name.setError("Full name is required");
//                ObjectAnimator.ofFloat(et_first_name, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
//                et_first_name.requestFocus();
//                return;
//            }
////            else if (phone_number.getText().toString().trim().isEmpty() || phone_number.getText().length() < 10) {
////                phone_number.setError("Phone number is required");
////                ObjectAnimator.ofFloat(phone_number, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
////                phone_number.requestFocus();
////                return;
////            }
//            else if (et_email.getText().toString().trim().isEmpty()) {
//                et_email.setError("Email is required");
//                ObjectAnimator.ofFloat(et_email, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
//                et_email.requestFocus();
//                return;
//            }
//
////            else if (password.getText().toString().trim().isEmpty()) {
////                password.setError("Password is required");
////                ObjectAnimator.ofFloat(password, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
////                password.requestFocus();
////                return;
////            }
//
//            else if (et_city.getText().toString().trim().isEmpty()) {
//                et_city.setError("City is required");
//                ObjectAnimator.ofFloat(et_city, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
//                et_city.requestFocus();
//                return;
//            }
//
////            else if (spinner_city.getText().toString().trim().isEmpty()) {
////                spinner_city.setError("City is required");
////                ObjectAnimator.ofFloat(spinner_city, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
////                spinner_city.requestFocus();
////                return;
////            }
//
//            else if (spinner_state.getText().toString().isEmpty()) {
//                spinner_state.setError("State is required");
//                ObjectAnimator.ofFloat(spinner_state, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
//                spinner_state.requestFocus();
//                return;
//
//            } else if (spinner_district.getText().toString().isEmpty()) {
//                spinner_district.setError("District is required");
//                ObjectAnimator.ofFloat(spinner_district, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
//                spinner_district.requestFocus();
//                return;
//            }
//
////            else if (type.getText().toString().isEmpty()) {
////                type.setError("Type is required");
////                ObjectAnimator.ofFloat(type, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
////                type.requestFocus();
////                return;
////            }
//
////            else if (Variables.fcmToken == null || Variables.fcmToken.isEmpty()) {
////                Toast.makeText(this,"Internal Error", Toast.LENGTH_SHORT).show();
////                return;
////            }
//
//            else if (Variables.token == null || Variables.token.isEmpty()) {
//                Toast.makeText(this, "Internal Error", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            else if (Variables.userId == null || Variables.userId.isEmpty()) {
////            else if (Variables.userId == null) {
//                Toast.makeText(this, "Internal Error", Toast.LENGTH_SHORT).show();
//                return;
//
//            }
//
////            else if (!terms.isChecked()) {
////                Toast.makeText(this, "Please Accept The Terms And Conditions", Toast.LENGTH_SHORT).show();
////                return;
////            }
//
//            else {
//                String fullnameStr = et_first_name.getText().toString().trim();
////                String phoneStr = phone_number.getText().toString().trim();
//                String emailStr = et_email.getText().toString().trim();
////                String passwordStr = password.getText().toString().trim();
////                String cityStr = city.getText().toString().trim();
//                String cityStr = String.valueOf(et_city.getText().toString().trim());
//                String stateStr = String.valueOf(spinner_state.getSelectedIndex() + 1);
//                String districtStr = String.valueOf(spinner_district.getSelectedIndex() + 1);
////                String typeStr = String.valueOf(GetDepartmentId(type.getText().toString()));
////                new RegistrationForm.ApiTask().execute(fullnameStr,phoneStr,emailStr,passwordStr,cityStr,typeStr,stateStr,districtStr,Variables.fcmToken);
//
//                new ApiTask2().execute(fullnameStr, emailStr, cityStr, stateStr, districtStr, (String) Variables.userId, Variables.token);
//
//            }
////            }
//        });
//
//        //This for Fragement
////        return view;
//
//
//    }
//
////    public void getUserData(JSONObject ResultJson) {
////        try {
////
////            String status_code = ResultJson.getString("status_code");
////            String message = ResultJson.getString("message");
////
////
////            JSONArray resultArray = ResultJson.getJSONArray("result");
//////            for (int i = 0; i < resultArray.length(); i++) {
//////
////////                String token = resultObject.getString("token");
//////                String privacyPolicy = ResultJson.getString("privacyPolicy");
//////                String termsAndConditions = ResultJson.getString("termsAndConditions");
//////                String message = ResultJson.getString("message");
//////                JSONArray userDetailsArray = ResultJson.getJSONArray("result");
//////                JSONObject userDetails = userDetailsArray.getJSONObject(0);
//////
//////                JSONObject resultObject = resultArray.getJSONObject(i);
//////                int id = resultObject.getInt("id");
//////                int departmentId = resultObject.getInt("department_id");
//////                int userId = resultObject.getInt("user_id");
////////                String apiToken = resultObject.getString("api_token");
////////                String user_code = resultObject.getString("user_code");
//////                String userCode = resultObject.optString("user_code", "");
//////
//////
//////                String name = resultObject.optString("name", "");
//////                String lastName = resultObject.optString("last_name", "");
//////                String email = resultObject.optString("email", "");
//////                String password = resultObject.optString("password", "");
//////                String mobile = resultObject.optString("mobile", "");
//////                String gender = resultObject.optString("gender", "");
//////                String dob = resultObject.optString("dob", "");
//////                String attachment = resultObject.optString("attachment", "");
//////                String city = resultObject.optString("city", "");
//////                String postCode = resultObject.optString("postCode", "");
//////                String address = resultObject.optString("address", "");
//////                String state = resultObject.optString("state", "");
//////                String country = resultObject.optString("country", "");
//////                String district = resultObject.optString("district", "");
//////                String street = resultObject.optString("street", "");
//////                String about = resultObject.optString("about", "");
//////                String status = resultObject.optString("status", "");
//////                String mobileVerified = resultObject.optString("mobile_verified", "");
//////                String isVerified = resultObject.optString("is_verified", "");
//////                String logDateCreated = resultObject.optString("log_date_created", "");
//////                String createdBy = resultObject.optString("created_by", "");
//////                String logDateModified = resultObject.optString("log_date_modified", "");
//////                String modifiedBy = resultObject.optString("modified_by", "");
//////                String fcmToken = resultObject.optString("fcm_token", "");
//////                String deviceId = resultObject.optString("device_id", "");
//////                String allowEmail = resultObject.optString("allow_email", "");
//////                String allowSms = resultObject.optString("allow_sms", "");
//////                String allowPush = resultObject.optString("allow_push", "");
//////
//////
//////                // Store values in static variables
//////                Variables.clear();
////////                Variables.token = token;
//////                Variables.id = id;
//////                Variables.departmentId = departmentId;
//////                Variables.userCode = userCode;
//////                Variables.name = name;
//////                Variables.lastName = lastName;
//////                Variables.email = email;
//////                Variables.password = password;
//////                Variables.mobile = mobile;
//////                Variables.gender = gender;
//////                Variables.dob = dob;
//////                Variables.attachment = attachment;
//////                Variables.city = city;
//////                Variables.postCode = postCode;
//////                Variables.address = address;
//////                Variables.state = state;
//////                Variables.country = country;
//////                Variables.district = district;
//////                Variables.street = street;
//////                Variables.about = about;
//////                Variables.status = status;
//////                Variables.mobileVerified = mobileVerified;
//////                Variables.isVerified = isVerified;
//////                Variables.logDateCreated = logDateCreated;
//////                Variables.createdBy = createdBy;
//////                Variables.logDateModified = logDateModified;
//////                Variables.modifiedBy = modifiedBy;
//////                Variables.fcmToken = fcmToken;
//////                Variables.deviceId = deviceId;
//////                Variables.allowEmail = allowEmail;
//////                Variables.allowSms = allowSms;
//////                Variables.allowPush = allowPush;
//////
//////
//////
//////            }
//////            sendotp.setVisibility(View.VISIBLE);
////            btn_save.setVisibility(View.VISIBLE);
////
////            Loading.hide();
////
////            if (RememberMe.isChecked())
////                rememberMe();
////
////            Variables.name = et_first_name.getText().toString();
////            Variables.email = et_email.getText().toString();
////            Variables.city = et_city.getText().toString();
////            Variables.state = spinner_state.getText().toString();
////            Variables.district = spinner_district.getText().toString();
////
//////            Variables.phoneNumber = phone_number.getText().toString();
//////            Variables.name = et_first_name.getText().toString();
//////            Variables.email = et_email.getText().toString();
//////            Variables.city = et_city.getText().toString();
//////            Variables.state = spinner_state.getText().toString();
//////            Variables.district = spinner_district.getText().toString();
////
//////            startActivity(new Intent(RegistrationForm.this, OTP_Verification.class));
////            startActivity(new Intent(EditProfileActivity.this, ProfileFragment.class));
////        } catch (JSONException e) {
////            Log.e(TAG, e.toString());
////            Toast.makeText(this, "System Error", Toast.LENGTH_SHORT).show();
//////            sendotp.setVisibility(View.VISIBLE);
////            btn_save.setVisibility(View.VISIBLE);
////
////        }
////
////    }
//
//
//    public void getUserData(JSONObject ResultJson) {
//        try {
//            JSONArray resultArray = ResultJson.getJSONArray("result");
//            for (int i = 0; i < resultArray.length(); i++) {
//
//                String status_code = ResultJson.getString("status_code");
//                String message = ResultJson.getString("message");
//
////                String token = resultObject.getString("token");
//                String privacyPolicy = ResultJson.getString("privacyPolicy");
//                String termsAndConditions = ResultJson.getString("termsAndConditions");
////                String message = ResultJson.getString("message");
//                JSONArray userDetailsArray = ResultJson.getJSONArray("result");
//                JSONObject userDetails = userDetailsArray.getJSONObject(0);
//
//                JSONObject resultObject = resultArray.getJSONObject(i);
//                int id = resultObject.getInt("id");
//                int departmentId = resultObject.getInt("department_id");
////                int userId = resultObject.getInt("user_id");
//                String userId = String.valueOf(resultObject.getInt("user_id"));
////                String apiToken = resultObject.getString("api_token");
////                String user_code = resultObject.getString("user_code");
//                String userCode = resultObject.optString("user_code", "");
//
//
//                String name = resultObject.optString("name", "");
//                String lastName = resultObject.optString("last_name", "");
//                String email = resultObject.optString("email", "");
//                String password = resultObject.optString("password", "");
//                String mobile = resultObject.optString("mobile", "");
//                String gender = resultObject.optString("gender", "");
//                String dob = resultObject.optString("dob", "");
//                String attachment = resultObject.optString("attachment", "");
//                String city = resultObject.optString("city", "");
//                String postCode = resultObject.optString("postCode", "");
//                String address = resultObject.optString("address", "");
//                String state = resultObject.optString("state", "");
//                String country = resultObject.optString("country", "");
//                String district = resultObject.optString("district", "");
//                String street = resultObject.optString("street", "");
//                String about = resultObject.optString("about", "");
//                String status = resultObject.optString("status", "");
//                String mobileVerified = resultObject.optString("mobile_verified", "");
//                String isVerified = resultObject.optString("is_verified", "");
//                String logDateCreated = resultObject.optString("log_date_created", "");
//                String createdBy = resultObject.optString("created_by", "");
//                String logDateModified = resultObject.optString("log_date_modified", "");
//                String modifiedBy = resultObject.optString("modified_by", "");
//                String fcmToken = resultObject.optString("fcm_token", "");
//                String deviceId = resultObject.optString("device_id", "");
//                String allowEmail = resultObject.optString("allow_email", "");
//                String allowSms = resultObject.optString("allow_sms", "");
//                String allowPush = resultObject.optString("allow_push", "");
//
//
//                // Store values in static variables
//                Variables.clear();
//                Variables.status_code = status_code;
//                Variables.message = message;
//
////                Variables.token = token;
////                Variables.userId = userId;
//
//                Variables.id = id;
//                Variables.departmentId = departmentId;
//                Variables.userCode = userCode;
//                Variables.name = name;
//                Variables.lastName = lastName;
//                Variables.email = email;
//                Variables.password = password;
//                Variables.mobile = mobile;
//                Variables.gender = gender;
//                Variables.dob = dob;
//                Variables.attachment = attachment;
//                Variables.city = city;
//                Variables.postCode = postCode;
//                Variables.address = address;
//                Variables.state = state;
//                Variables.country = country;
//                Variables.district = district;
//                Variables.street = street;
//                Variables.about = about;
//                Variables.status = status;
//                Variables.mobileVerified = mobileVerified;
//                Variables.isVerified = isVerified;
//                Variables.logDateCreated = logDateCreated;
//                Variables.createdBy = createdBy;
//                Variables.logDateModified = logDateModified;
//                Variables.modifiedBy = modifiedBy;
//                Variables.fcmToken = fcmToken;
//                Variables.deviceId = deviceId;
//                Variables.allowEmail = allowEmail;
//                Variables.allowSms = allowSms;
//                Variables.allowPush = allowPush;
//
//
//            }
////            sendotp.setVisibility(View.VISIBLE);
//            btn_save.setVisibility(View.VISIBLE);
//
//            Loading.hide();
//
//            if (RememberMe.isChecked())
//                rememberMe();
//
//
////            Variables.phoneNumber = phone_number.getText().toString();
//            Variables.name = et_first_name.getText().toString();
//            Variables.email = et_email.getText().toString();
//            Variables.city = et_city.getText().toString();
//            Variables.state = spinner_state.getText().toString();
//            Variables.district = spinner_district.getText().toString();
//
////            startActivity(new Intent(RegistrationForm.this, OTP_Verification.class));
//            startActivity(new Intent(EditProfileActivity.this, ProfileFragment.class));
//        } catch (JSONException e) {
//            Log.e(TAG, e.toString());
//            Toast.makeText(this, "System Error", Toast.LENGTH_SHORT).show();
////            sendotp.setVisibility(View.VISIBLE);
//            btn_save.setVisibility(View.VISIBLE);
//
//        }
//
//    }
//
//
//    @SuppressLint("StaticFieldLeak")
//    public void getDropDownData() {
//        new AsyncTask<Void, Void, JSONObject>() {
//            @Override
//            protected JSONObject doInBackground(Void... voids) {
//                FetchData fetchData = new FetchData("https://houseofspiritshyd.in/prakruthi/admin/api/getDropdownData");
//                if (fetchData.startFetch()) {
//                    if (fetchData.onComplete()) {
//                        String result = fetchData.getResult();
//                        Log.i("FetchData", result);
//                        try {
//                            JSONObject jsonObj = new JSONObject(result);
//                            return jsonObj;
//                        } catch (JSONException e) {
//                            Log.e(TAG, e.toString());
//                            return null;
////                            return "";
//                        }
//                    }
//                }
//                return null;
////                return "";
//            }
//
//            @Override
//            protected void onPostExecute(JSONObject jsonObj) {
////                super.onPostExecute(jsonObj);
//
//                if (jsonObj != null) {
//                    try {
////                        JSONArray departments = jsonObj.getJSONArray("departments");
////                        ArrayList<String> departmentNames = new ArrayList<>();
////                        for(int i = 0; i < departments.length(); i++) {
////                            JSONObject department = departments.getJSONObject(i);
////                            departmentNames.add(department.getString("name"));
////                        }
////                        type.setItems(departmentNames);
//
//                        JSONArray states = jsonObj.getJSONArray("state");
//                        ArrayList<String> stateNames = new ArrayList<>();
//                        for (int i = 0; i < states.length(); i++) {
//                            JSONObject state = states.getJSONObject(i);
//                            stateNames.add(state.getString("name"));
//                        }
////                        state.setItems(stateNames);
//                        spinner_state.setItems(stateNames);
//
//
//                        JSONArray districts = jsonObj.getJSONArray("district");
//                        districtNames.clear();
//
//                        //
////                        ArrayList<String> districtNames = new ArrayList<>();
//
//                        for (int i = 0; i < districts.length(); i++) {
//                            JSONObject districtt = districts.getJSONObject(i);
//                            if (districtt.getInt("state_id") == stateId) {
//                                districtNames.add(districtt.getString("name"));
////                                district.setItems(districtNames);
//                                spinner_district.setItems(districtNames);
//
//                            }
//                        }
////                        district.setItems(districtNames);
////                        spinner_district.setItems(districtNames);
//
//
//                    } catch (JSONException e) {
//                        Log.e(TAG, e.toString());
//                        Toast.makeText(EditProfileActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(EditProfileActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }.execute();
//    }
//
//    public void getFCMToken()
//    {
//        FirebaseApp.initializeApp(EditProfileActivity.this);
//        FirebaseMessaging.getInstance().getToken()
//                .addOnCompleteListener(new OnCompleteListener<String>() {
//                    @Override
//                    public void onComplete(@NonNull Task<String> task) {
//                        if (!task.isSuccessful()) {
//                            Log.d("firebase", "Fetching FCM Edit token failed", task.getException());
//                            return;
//                        }
//                        // Get new FCM registration token
//                        String token = task.getResult();
//                        Log.d("firebase", "token" + token);
//                        Variables.token = token;
//                    }
//                });
//    }
//
//
//    @SuppressLint("StaticFieldLeak")
//
////    private class ApiTask2 extends AsyncTask<String, Void, String> {
//    private class ApiTask2 extends AsyncTask<String, Void, String> {
////        Loading.show(EditProfileActivity .this);
//
//        @Override
//        protected String doInBackground(String... params) {
//            //Creating array for parameters
//            String[] field = new String[7];
//            field[0] = "user_id";
//            field[1] = "token";
//
//            field[2] = "name";
////            field[1] = "mobile";
//            field[3] = "email";
////            field[3] = "password";
//            field[4] = "city";
////            field[5] = "type";
//            field[5] = "state";
//            field[6] = "district";
////            field[8] = "fcm_token";
//            //Creating array for data
//            String[] data = new String[7];
//            data[0] = params[0];
//            data[1] = params[1];
//            data[2] = params[2];
//            data[3] = params[3];
//            data[4] = params[4];
//            data[5] = params[5];
//            data[6] = params[6];
////            data[7] = params[7];
////            data[8] = params[8];
//
//            Log.e(TAG, Arrays.toString(data));
////            PutData putData = new PutData(Variables.BaseUrl + "registration", "POST", field, data);
//            PutData putData = new PutData(Variables.BaseUrl + "userDetailsUpdate", "POST", field, data);
//
//            if (putData.startPut()) {
//                if (putData.onComplete()) {
//                    // result = Api Result
//                    String result = putData.getResult();
//                    Log.e(TAG, putData.getResult());
//                    return putData.getResult();
////                    return result;
//
//                }
//            }
//
//            return null;
////            return "";
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//
//            if (result != null) {
//                try {
//                    Log.e(TAG, result);
//                    JSONObject json = new JSONObject(result);
//                    boolean statusCode = json.getBoolean("status_code");
//                    String message = json.getString("message");
//
//                    if (statusCode) {
//                        Toast.makeText(EditProfileActivity.this, message, Toast.LENGTH_SHORT).show();
//                        getUserData(json);
//                    } else {
//                        Toast.makeText(EditProfileActivity.this, message, Toast.LENGTH_SHORT).show();
////                        sendotp.setVisibility(View.VISIBLE);
//                        btn_save.setVisibility(View.VISIBLE);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
////                    sendotp.setVisibility(View.VISIBLE);
//                    btn_save.setVisibility(View.VISIBLE);
//
//                    Toast.makeText(EditProfileActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
//                }
//            } else {
////                sendotp.setVisibility(View.VISIBLE);
//                btn_save.setVisibility(View.VISIBLE);
//
//                Toast.makeText(EditProfileActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//
//    @Override
//    public void OnUserDetailsUpdateApiGivesFetched(String message) {
////        EditProfileActivity.this.runOnUiThread(() -> {
//        runOnUiThread(() -> {
//
//            UserDetailsUpdateApi();
//
////            Loading.hide();
////            SetTextViews();
//            Toast.makeText(EditProfileActivity.this, message, Toast.LENGTH_SHORT).show();
//
////            GetUserDataApi getUserDataApi = new GetUserDataApi(this);
//////            getUserDataApi.execute();
////            getUserDataApi.HitGetUserDataApi();
//
//            UserDetailsUpdateApi userDetailsUpdateApi = new UserDetailsUpdateApi(this, name, email, city, Variables.state, Variables.district, Variables.token, Variables.userId);
////            getUserDataApi.execute();
//            userDetailsUpdateApi.HitUserDetailsUpdateApi();
//
//
//            PopupDialog.getInstance(EditProfileActivity.this)
//                    .setStyle(Styles.SUCCESS)
//                    .setHeading("Well Done")
//                    .setDescription("Successfully" +
//                            " Edit Profile")
//                    .setCancelable(false)
//                    .showDialog(new OnDialogButtonClickListener() {
//                        @Override
//                        public void onDismissClicked(Dialog dialog) {
//                            super.onDismissClicked(dialog);
//                        }
//                    });
//            Loading.hide();
//        });
//
//    }
//
//    public int boolToInt(boolean value) {
//        return value ? 1 : 0;
//    }
//
//    @Override
//    public void OnUserDetailsUpdateApiGivesError(String error) {
//        runOnUiThread(() -> {
//            runOnUiThread(() ->
//
//                    Toast.makeText(EditProfileActivity.this, error, Toast.LENGTH_SHORT).show());
////        Loading.hide();
//            Toast.makeText(EditProfileActivity.this, error, Toast.LENGTH_SHORT).show();
//
//            PopupDialog.getInstance(EditProfileActivity.this)
//                    .setStyle(Styles.FAILED)
//                    .setHeading("Uh-Oh")
//                    .setDescription("Unexpected error occurred." +
//                            " Try again later.")
//                    .setCancelable(false)
//                    .showDialog(new OnDialogButtonClickListener() {
//                        @Override
//                        public void onDismissClicked(Dialog dialog) {
//                            super.onDismissClicked(dialog);
//                        }
//                    });
//        });
//
//
//    }
//
//
//    private void setdata() {
//
//        try {
//
//            et_first_name.setText((CharSequence) profileGetUserDataModel.getName());
//
//            et_email.setText((CharSequence) profileGetUserDataModel.getEmail());
//
//            et_city.setText((CharSequence) profileGetUserDataModel.getCity());
//
//            spinner_state.setText((CharSequence) profileGetUserDataModel.getState());
//
//            spinner_district.setText((CharSequence) profileGetUserDataModel.getDistrict());
//
//
////            binding.etDob.setText((CharSequence) profileGetUserDataModel.getDob());
////            if (data.getGender().equalsIgnoreCase("male"))
////                binding.rbMale.setChecked(true);
////            if (data.getGender().equalsIgnoreCase("female"))
////                binding.rbFemale.setChecked(true);
//
////            Glide.with(EditProfileFragment.this).load(getIntent().getStringExtra("basepath") + profileGetUserDataModel.getProfilePhoto()).error(R.drawable.profile_img).error(R.drawable.profile_img).into(binding.ivProfile);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private boolean isAllFieldsValidated() {
//
//        if (TextUtils.isEmpty(et_first_name.getText().toString().trim())) {
//            et_first_name.setError("Required");
//            return false;
//        }
//
//        if (TextUtils.isEmpty(et_email.getText().toString().trim()) || !Patterns.EMAIL_ADDRESS.matcher(et_email.getText().toString()).matches()) {
//            et_email.setError("Not Valid");
//            return false;
//        }
//        return true;
//    }
//
////    public void setDismissListener(EditProfileFragment.DismissListener dismissListener) {
////        this.dismissListener = dismissListener;
////    }
//
//    public void setDismissListener(EditProfileActivity.DismissListener dismissListener) {
//        this.dismissListener = dismissListener;
//    }
//
//    public interface DismissListener {
//        public void onDismiss();
//    }
//
//    public void rememberMe() {
//        // Get SharedPreferences.Editor object
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//        // Set String value
//        editor.putString("name", et_first_name.getText().toString());
//        editor.putString("email", et_email.getText().toString());
//        editor.putString("city", et_city.getText().toString());
//
//        editor.putBoolean("rememberMe", true);
//        // Apply changes
//        editor.apply();
//    }
//
//    public boolean isRemembered() {
//        return sharedPreferences.getBoolean("rememberMe", false);
//    }
//
//    public void UserDetailsUpdateApi() {
//
////        GetUserDataApi getUserDataApi = new GetUserDataApi(this);
////        getUserDataApi.HitGetUserDataApi();
//
//        UserDetailsUpdateApi userDetaialsUpdateApi = new UserDetailsUpdateApi(this, name, email, city, state, district,Variables.token,Variables.userId);
//        userDetaialsUpdateApi.HitUserDetailsUpdateApi();
////        userDetaialsUpdateApi.execute();
//
//
//    }
//
//
//}