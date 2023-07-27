//package com.prakruthi.ui.ui.profile;
//
//import static android.content.ContentValues.TAG;
//
//import static com.prakruthi.ui.Variables.city;
//import static com.prakruthi.ui.Variables.email;
//import static com.prakruthi.ui.Variables.name;
//
//import android.animation.ObjectAnimator;
//import android.annotation.SuppressLint;
//import android.app.Dialog;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.AsyncTask;
//import android.os.Build;
//import android.os.Bundle;
//
//import androidx.annotation.RequiresApi;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.AppCompatButton;
//import androidx.fragment.app.Fragment;
//
//import android.text.TextUtils;
//import android.util.Log;
//import android.util.Patterns;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import com.bumptech.glide.Glide;
//import com.google.android.material.imageview.ShapeableImageView;
//import com.google.gson.Gson;
//import com.prakruthi.R;
//import com.prakruthi.ui.APIs.GetUserDataApi;
//import com.prakruthi.ui.APIs.UserDetailsUpdateApi;
//import com.prakruthi.ui.OTP_Verification;
//import com.prakruthi.ui.RegistrationForm;
//import com.prakruthi.ui.Variables;
//import com.prakruthi.ui.misc.Loading;
//import com.saadahmedsoft.popupdialog.PopupDialog;
//import com.saadahmedsoft.popupdialog.Styles;
//import com.saadahmedsoft.popupdialog.listener.OnDialogButtonClickListener;
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
//
//public class EditProfileFragment extends Fragment implements UserDetailsUpdateApi.OnUserDetailsUpdateApiGivesFetchedListner{
////public class EditProfileFragment extends AppCompatActivity implements View.OnClickListener {
////public class EditProfileFragment extends AppCompatActivity {
//
//    int view = R.layout.fragment_edit_profile;
//
//    private static final int TIME_INTERVAL = 2000;
//    private long mBackPressed;
//    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
//    EditText et_first_name, et_email, et_city;
//    PowerSpinnerView state, district, type;
//    PowerSpinnerView spinner_city, spinner_state, spinner_district;
//
//    ImageView iv_close, iv_back;
//    ShapeableImageView iv_profile;
//    Button btn_save;
//    AppCompatButton sendotp, backbtn;
//
//    private DismissListener dismissListener;
//    private Uri imageUri;
//    private String selectedPath = " ";
//
//    ProfileGetUserDataResponse.ProfileGetUserDataModel profileGetUserDataModel;
//    EditProfileUpdateDrailsUpdateModels editProfileUpdateDrailsUpdateModels;
//
//    public EditProfileFragment() {
//        // Required empty public constructor
//    }
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
////        profileGetUserDataModel = new Gson().fromJson(getIntent().getStringExtra("data"), ProfileGetUserDataResponse.ProfileGetUserDataModel.class);
//
//        // Retrieve the data passed from the activity
//        if (getArguments() != null) {
//            String data = getArguments().getString("data");
//            profileGetUserDataModel = new Gson().fromJson(data, ProfileGetUserDataResponse.ProfileGetUserDataModel.class);
//        }
//
//////        init();
//////        fetchCities();
//////        fetchStates();
////        getDropDownData();
//////        getFCMToken();
////
////
////        // set an OnTouchListener to the root view
////        View root = findViewById(android.R.id.content);
////        root.setOnTouchListener(new View.OnTouchListener() {
////            @Override
////            public boolean onTouch(View v, MotionEvent event) {
////                if (event.getAction() == MotionEvent.ACTION_DOWN) {
////                    // check if the touch is outside of the state view
////                    int[] location = new int[2];
////                    spinner_state.getLocationOnScreen(location);
////                    int x = location[0];
////                    int y = location[1];
////                    int width = spinner_state.getWidth();
////                    int height = spinner_state.getHeight();
////                    if (!(event.getX() > x && event.getX() < x + width && event.getY() > y && event.getY() < y + height)) {
////                        // dismiss the state view
////                        spinner_state.dismiss();
////                        spinner_district.dismiss();
//////                        type.dismiss();
////                        return true; // consume the event
////                    }
////                }
////                return false; // do not consume the event
////            }
////        });
////
////        backbtn.setOnClickListener(view -> {
////            super.onBackPressed();
////        });
////
//////        sendotp.setOnClickListener(view -> {
////            btn_save.setOnClickListener(view -> {
////            spinner_state.setError(null);
////            district.setError(null);
//////            type.setError(null);
////            if (et_first_name.getText().toString().trim().isEmpty()) {
////                et_first_name.setError("Full name is required");
////                ObjectAnimator.ofFloat(et_first_name, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
////                et_first_name.requestFocus();
////                return;
////            }
//////            else if (phone_number.getText().toString().trim().isEmpty() || phone_number.getText().length() < 10) {
//////                phone_number.setError("Phone number is required");
//////                ObjectAnimator.ofFloat(phone_number, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
//////                phone_number.requestFocus();
//////                return;
//////            }
////            else if (et_email.getText().toString().trim().isEmpty()) {
////                et_email.setError("Email is required");
////                ObjectAnimator.ofFloat(et_email, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
////                et_email.requestFocus();
////                return;
////            }
////
//////            else if (password.getText().toString().trim().isEmpty()) {
//////                password.setError("Password is required");
//////                ObjectAnimator.ofFloat(password, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
//////                password.requestFocus();
//////                return;
//////            }
////
////            else if (et_city.getText().toString().trim().isEmpty()) {
////                et_city.setError("City is required");
////                ObjectAnimator.ofFloat(et_city, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
////                et_city.requestFocus();
////                return;
////            }
////
//////            else if (spinner_city.getText().toString().trim().isEmpty()) {
//////                spinner_city.setError("City is required");
//////                ObjectAnimator.ofFloat(spinner_city, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
//////                spinner_city.requestFocus();
//////                return;
//////            }
////
////            else if (spinner_state.getText().toString().isEmpty()) {
////                spinner_state.setError("State is required");
////                ObjectAnimator.ofFloat(spinner_state, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
////                spinner_state.requestFocus();
////                return;
////            }
////
////            else if (district.getText().toString().isEmpty()) {
////                district.setError("District is required");
////                ObjectAnimator.ofFloat(district, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
////                district.requestFocus();
////                return;
////            }
////
//////            else if (type.getText().toString().isEmpty()) {
//////                type.setError("Type is required");
//////                ObjectAnimator.ofFloat(type, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
//////                type.requestFocus();
//////                return;
//////            }
////
//////            else if (Variables.fcmToken == null || Variables.fcmToken.isEmpty()) {
//////                Toast.makeText(this,"Internal Error", Toast.LENGTH_SHORT).show();
//////                return;
//////            }
////
//////            else if (!terms.isChecked()) {
//////                Toast.makeText(this, "Please Accept The Terms And Conditions", Toast.LENGTH_SHORT).show();
//////                return;
//////            }
////
////            else {
////                String fullnameStr = et_first_name.getText().toString().trim();
//////                String phoneStr = phone_number.getText().toString().trim();
////                String emailStr = et_email.getText().toString().trim();
//////                String passwordStr = password.getText().toString().trim();
//////                String cityStr = city.getText().toString().trim();
////                String cityStr = String.valueOf(et_city.getText().toString().trim());
////                String stateStr = String.valueOf(spinner_state.getSelectedIndex()+1);
////                String districtStr = String.valueOf(district.getSelectedIndex()+1);
//////                String typeStr = String.valueOf(GetDepartmentId(type.getText().toString()));
//////                new RegistrationForm.ApiTask().execute(fullnameStr,phoneStr,emailStr,passwordStr,cityStr,typeStr,stateStr,districtStr,Variables.fcmToken);
////                new ApiTask1().execute(fullnameStr,emailStr,cityStr,stateStr,districtStr);
////
////            }
////        });
//
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
////        return inflater.inflate(R.layout.fragment_edit_profile, container, false);
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
//
//        // Access the root view of the fragment
//        View root = view.getRootView();
//
////            profileGetUserDataModel = new Gson().fromJson(getIntent().getStringExtra("data"), ProfileGetUserDataResponse.ProfileGetUserDataModel.class);
//
//        // Retrieve the data passed from the activity
////            if (getArguments() != null) {
////                String data = getArguments().getString("data");
////                profileGetUserDataModel = new Gson().fromJson(data, ProfileGetUserDataResponse.ProfileGetUserDataModel.class);
////            }
///*
//            profileGetUserDataModel = new Gson().fromJson(data, ProfileGetUserDataResponse.ProfileGetUserDataModel.class);
//*/
//
//        //        init();
////        fetchCities();
////        fetchStates();
//        getDropDownData();
////        getFCMToken();
//
//
//        // set an OnTouchListener to the root view
////        View root = view.findViewById(android.R.id.content);
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
////            backbtn.setOnClickListener(view -> {
////                super.onBackPressed();
////            });
//
////        sendotp.setOnClickListener(view -> {
//        btn_save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                spinner_state.setError(null);
//                spinner_district.setError(null);
////            type.setError(null);
//                if (et_first_name.getText().toString().trim().isEmpty()) {
//                    et_first_name.setError("Full name is required");
//                    ObjectAnimator.ofFloat(et_first_name, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
//                    et_first_name.requestFocus();
//                    return;
//                }
////            else if (phone_number.getText().toString().trim().isEmpty() || phone_number.getText().length() < 10) {
////                phone_number.setError("Phone number is required");
////                ObjectAnimator.ofFloat(phone_number, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
////                phone_number.requestFocus();
////                return;
////            }
//                else if (et_email.getText().toString().trim().isEmpty()) {
//                    et_email.setError("Email is required");
//                    ObjectAnimator.ofFloat(et_email, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
//                    et_email.requestFocus();
//                    return;
//                }
//
////            else if (password.getText().toString().trim().isEmpty()) {
////                password.setError("Password is required");
////                ObjectAnimator.ofFloat(password, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
////                password.requestFocus();
////                return;
////            }
//
//                else if (et_city.getText().toString().trim().isEmpty()) {
//                    et_city.setError("City is required");
//                    ObjectAnimator.ofFloat(et_city, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
//                    et_city.requestFocus();
//                    return;
//                }
//
////            else if (spinner_city.getText().toString().trim().isEmpty()) {
////                spinner_city.setError("City is required");
////                ObjectAnimator.ofFloat(spinner_city, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
////                spinner_city.requestFocus();
////                return;
////            }
//
//                else if (spinner_state.getText().toString().isEmpty()) {
//                    spinner_state.setError("State is required");
//                    ObjectAnimator.ofFloat(spinner_state, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
//                    spinner_state.requestFocus();
//                    return;
//                } else if (spinner_district.getText().toString().isEmpty()) {
//                    spinner_district.setError("District is required");
//                    ObjectAnimator.ofFloat(spinner_district, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
//                    spinner_district.requestFocus();
//                    return;
//                }
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
////            else if (!terms.isChecked()) {
////                Toast.makeText(this, "Please Accept The Terms And Conditions", Toast.LENGTH_SHORT).show();
////                return;
////            }
//
//                else {
//                    String fullnameStr = et_first_name.getText().toString().trim();
////                String phoneStr = phone_number.getText().toString().trim();
//                    String emailStr = et_email.getText().toString().trim();
////                String passwordStr = password.getText().toString().trim();
////                String cityStr = city.getText().toString().trim();
//                    String cityStr = String.valueOf(et_city.getText().toString().trim());
//                    String stateStr = String.valueOf(spinner_state.getSelectedIndex() + 1);
//                    String districtStr = String.valueOf(spinner_district.getSelectedIndex() + 1);
////                String typeStr = String.valueOf(GetDepartmentId(type.getText().toString()));
////                new RegistrationForm.ApiTask().execute(fullnameStr,phoneStr,emailStr,passwordStr,cityStr,typeStr,stateStr,districtStr,Variables.fcmToken);
//                    new ApiTask1().execute(fullnameStr, emailStr, cityStr, stateStr, districtStr);
//
//                }
//            }
//        });
//
//
////        btn_save.setOnClickListener(view -> {
////            spinner_state.setError(null);
////            district.setError(null);
//////            type.setError(null);
////            if (et_first_name.getText().toString().trim().isEmpty()) {
////                et_first_name.setError("Full name is required");
////                ObjectAnimator.ofFloat(et_first_name, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
////                et_first_name.requestFocus();
////                return;
////            }
//////            else if (phone_number.getText().toString().trim().isEmpty() || phone_number.getText().length() < 10) {
//////                phone_number.setError("Phone number is required");
//////                ObjectAnimator.ofFloat(phone_number, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
//////                phone_number.requestFocus();
//////                return;
//////            }
////            else if (et_email.getText().toString().trim().isEmpty()) {
////                et_email.setError("Email is required");
////                ObjectAnimator.ofFloat(et_email, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
////                et_email.requestFocus();
////                return;
////            }
////
//////            else if (password.getText().toString().trim().isEmpty()) {
//////                password.setError("Password is required");
//////                ObjectAnimator.ofFloat(password, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
//////                password.requestFocus();
//////                return;
//////            }
////
////            else if (et_city.getText().toString().trim().isEmpty()) {
////                et_city.setError("City is required");
////                ObjectAnimator.ofFloat(et_city, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
////                et_city.requestFocus();
////                return;
////            }
////
//////            else if (spinner_city.getText().toString().trim().isEmpty()) {
//////                spinner_city.setError("City is required");
//////                ObjectAnimator.ofFloat(spinner_city, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
//////                spinner_city.requestFocus();
//////                return;
//////            }
////
////            else if (spinner_state.getText().toString().isEmpty()) {
////                spinner_state.setError("State is required");
////                ObjectAnimator.ofFloat(spinner_state, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
////                spinner_state.requestFocus();
////                return;
////            } else if (district.getText().toString().isEmpty()) {
////                district.setError("District is required");
////                ObjectAnimator.ofFloat(district, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
////                district.requestFocus();
////                return;
////            }
////
//////            else if (type.getText().toString().isEmpty()) {
//////                type.setError("Type is required");
//////                ObjectAnimator.ofFloat(type, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
//////                type.requestFocus();
//////                return;
//////            }
////
//////            else if (Variables.fcmToken == null || Variables.fcmToken.isEmpty()) {
//////                Toast.makeText(this,"Internal Error", Toast.LENGTH_SHORT).show();
//////                return;
//////            }
////
//////            else if (!terms.isChecked()) {
//////                Toast.makeText(this, "Please Accept The Terms And Conditions", Toast.LENGTH_SHORT).show();
//////                return;
//////            }
////
////            else {
////                String fullnameStr = et_first_name.getText().toString().trim();
//////                String phoneStr = phone_number.getText().toString().trim();
////                String emailStr = et_email.getText().toString().trim();
//////                String passwordStr = password.getText().toString().trim();
//////                String cityStr = city.getText().toString().trim();
////                String cityStr = String.valueOf(et_city.getText().toString().trim());
////                String stateStr = String.valueOf(spinner_state.getSelectedIndex() + 1);
////                String districtStr = String.valueOf(district.getSelectedIndex() + 1);
//////                String typeStr = String.valueOf(GetDepartmentId(type.getText().toString()));
//////                new RegistrationForm.ApiTask().execute(fullnameStr,phoneStr,emailStr,passwordStr,cityStr,typeStr,stateStr,districtStr,Variables.fcmToken);
////                new ApiTask1().execute(fullnameStr, emailStr, cityStr, stateStr, districtStr);
////
////            }
////        });
//        return view;
//
//    }
//
//    public void getUserData(JSONObject ResultJson) {
//        try {
//            JSONArray resultArray = ResultJson.getJSONArray("result");
//            for (int i = 0; i < resultArray.length(); i++) {
//                JSONObject resultObject = resultArray.getJSONObject(i);
//                int userId = resultObject.getInt("user_id");
//                String userMobile = resultObject.getString("user_mobile");
//                String apiToken = resultObject.getString("api_token");
//            }
////            sendotp.setVisibility(View.VISIBLE);
//            btn_save.setVisibility(View.VISIBLE);
////            Variables.phoneNumber = phone_number.getText().toString();
//            startActivity(new Intent(requireContext(), ProfileFragment.class));
//        } catch (JSONException e) {
//            Log.e(TAG, e.toString());
//            Toast.makeText(requireContext(), "System Error", Toast.LENGTH_SHORT).show();
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
//                super.onPostExecute(jsonObj);
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
//                        JSONArray districts = jsonObj.getJSONArray("district");
//                        ArrayList<String> districtNames = new ArrayList<>();
//                        for (int i = 0; i < districts.length(); i++) {
//                            JSONObject district = districts.getJSONObject(i);
//                            districtNames.add(district.getString("name"));
//                        }
////                        district.setItems(districtNames);
//                        spinner_district.setItems(districtNames);
//
//                    } catch (JSONException e) {
//                        Log.e(TAG, e.toString());
//                        Toast.makeText(requireContext(), "Network Error", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(requireContext(), "Network Error", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }.execute();
//    }
//
////    @Override
////    public void OnUserDetailsUpdateApiGivesFetched(String message) {
////
////    }
////
////    @Override
////    public void OnUserDetailsUpdateApiGivesError(String error) {
////
////    }
//
//    @Override
//    public void OnUserDetailsUpdateApiGivesFetched(String message) {
//        requireActivity().runOnUiThread(() -> {
//            Loading.hide();
//
////            SetTextViews();
//
//            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
//
////            GetUserDataApi getUserDataApi = new GetUserDataApi(this);
//////            getUserDataApi.execute();
////            getUserDataApi.HitGetUserDataApi();
//
//            UserDetailsUpdateApi userDetailsUpdateApi = new UserDetailsUpdateApi(this, name,email,city,Variables.state,Variables.district);
////            getUserDataApi.execute();
//            userDetailsUpdateApi.HitUserDetailsUpdateApi();
//
//
//            PopupDialog.getInstance(requireContext())
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
////    private void runOnUiThread(Object well_done) {
////    }
//
//    public int boolToInt(boolean value) {
//        return value ? 1 : 0;
//    }
//
//
//    @Override
//    public void OnUserDetailsUpdateApiGivesError(String error) {
//        requireActivity().runOnUiThread(() -> {
//            requireActivity().runOnUiThread(() ->
//                    Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show());
////        Loading.hide();
//            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show();
//
//            PopupDialog.getInstance(requireContext())
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
////        SetTextViews();
//    }
//
//    private class ApiTask1 extends AsyncTask<String, Void, String> {
//        @Override
//        protected String doInBackground(String... params) {
//            //Creating array for parameters
//            String[] field = new String[5];
//            field[0] = "name";
////            field[1] = "mobile";
//            field[1] = "email";
////            field[3] = "password";
//            field[2] = "city";
////            field[5] = "type";
//            field[3] = "state";
//            field[4] = "district";
////            field[8] = "fcm_token";
//            //Creating array for data
//            String[] data = new String[5];
//            data[0] = params[0];
//            data[1] = params[1];
//            data[2] = params[2];
//            data[3] = params[3];
//            data[4] = params[4];
////            data[5] = params[5];
////            data[6] = params[6];
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
//                    Log.e(TAG, putData.getResult());
//                    return putData.getResult();
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
//                        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
//                        getUserData(json);
//                    } else {
//                        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
////                        sendotp.setVisibility(View.VISIBLE);
//                        btn_save.setVisibility(View.VISIBLE);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
////                    sendotp.setVisibility(View.VISIBLE);
//                    btn_save.setVisibility(View.VISIBLE);
//
//                    Toast.makeText(requireContext(), "Network Error", Toast.LENGTH_SHORT).show();
//                }
//            } else {
////                sendotp.setVisibility(View.VISIBLE);
//                btn_save.setVisibility(View.VISIBLE);
//
//                Toast.makeText(requireContext(), "Network Error", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//
////    @Override
////    public View onCreateView(LayoutInflater inflater, ViewGroup container,
////                             Bundle savedInstanceState) {
////        // Inflate the layout for this fragment
////        return inflater.inflate(R.layout.fragment_edit_profile, container, false);
////    }
//
//
//    //Retrofile:-----------------------------------------
//
////    private void init() {
//////        iv_profile = findViewById(R.id.iv_profile);
////        iv_close = findViewById(R.id.iv_close);
////        btn_save = findViewById(R.id.btn_save);
////
////        et_first_name = findViewById(R.id.et_first_name);
////        et_email = findViewById(R.id.et_email);
////
////        iv_back = findViewById(R.id.iv_back);
////
////        iv_back.setOnClickListener(this);
////
////
////        btn_save.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                if (isAllFieldsValidated())
////                    updateProfileDetails();
////            }
////        });
//////        iv_profile.setOnClickListener(new View.OnClickListener() {
//////            @Override
//////            public void onClick(View v) {
//////                selectImage();
//////            }
//////        });
////        setdata();
////
////
////
////        iv_close.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////
////
////            }
////        });
////
////
////    }
//
//    private void setdata() {
//
//        try {
//
//            et_first_name.setText((CharSequence) profileGetUserDataModel.getName());
//
//            et_email.setText((CharSequence) profileGetUserDataModel.getEmail());
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
//    public void setDismissListener(DismissListener dismissListener) {
//        this.dismissListener = dismissListener;
//    }
//
////    @Override
////    public void onClick(View v) {
////        switch (v.getId()) {
////            case R.id.iv_close:
//////                resendOtp();
////                break;
////            case R.id.iv_back:
////                finish();
////                break;
////        }
////
////    }
//
//    public interface DismissListener {
//        public void onDismiss();
//    }
//
////    ArrayList<LocationTableResponse.LocationTable> citys = new ArrayList<>();
////    ArrayList<LocationTableResponse.LocationTable> states = new ArrayList<>();
////    ArrayList<LocationTableResponse.LocationTable> countrys = new ArrayList<>();
//
//}