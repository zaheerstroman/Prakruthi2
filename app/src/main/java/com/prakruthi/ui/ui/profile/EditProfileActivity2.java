//package com.prakruthi.ui.ui.profile;
//
//import static com.android.volley.VolleyLog.TAG;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.RequiresApi;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.AppCompatButton;
//
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
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.Spinner;
//import android.widget.Toast;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.messaging.FirebaseMessaging;
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//import com.prakruthi.R;
//import com.prakruthi.databinding.ActivityEditProfile2Binding;
//import com.prakruthi.ui.Variables;
//import com.prakruthi.ui.misc.Loading;
//import com.prakruthi.ui.network.APIClient;
//import com.prakruthi.ui.network.ApiInterface;
//import com.prakruthi.ui.utils.CommonUtils;
//import com.prakruthi.ui.utils.Constants;
//import com.prakruthi.ui.utils.SharedPrefs;
//import com.skydoves.powerspinner.OnSpinnerItemSelectedListener;
//import com.skydoves.powerspinner.PowerSpinnerView;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//
//import okhttp3.MediaType;
//import okhttp3.MultipartBody;
//import okhttp3.RequestBody;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class EditProfileActivity2 extends AppCompatActivity implements View.OnClickListener {
////    public class EditProfileActivity2 extends BottomSheetDialogFragment {
//
//    //    int view = R.layout.activity_edit_profile_fragment2;
//    int view = R.layout.activity_edit_profile2;
//    private static final int TIME_INTERVAL = 2000;
//    private long mBackPressed;
//    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
//
//    EditText et_first_name, et_email, et_city;
//
//    ImageView iv_close;
//    AppCompatButton iv_back;
//
//    Button btn_save;
//
//    public Spinner spinner_state, spinner_district;
////    public PowerSpinnerView spinner_state, spinner_district;
//
//    //Use for Get Method only
//    int stateId = 0;
//    CheckBox RememberMe;
//
//    SharedPreferences sharedPreferences;
//
//    ArrayList<String> districtNames = new ArrayList<>();
//
//
////    PowerSpinnerView spinner_state, spinner_district;
//
//    //    ProfileGetUserDataResponse.ProfileGetUserDataModel data;
//    ProfileGetUserDataResponseRetrofit.ProfileGetUserDataModelRetrofit data;
//
//    private DismissListener dismissListener;
//
//    private Uri imageUri;
//    private String selectedPath = " ";
//
//    ActivityEditProfile2Binding binding;
//
////    PowerSpinnerView spinnerState = binding.spinnerState;
////    PowerSpinnerView spinnerDistrict = binding.spinnerDistrict;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_edit_profile2);
//        binding = ActivityEditProfile2Binding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
////        data = new Gson().fromJson(getIntent().getStringExtra("data"), ProfileGetUserDataResponse.ProfileGetUserDataModel.class);
//        data = new Gson().fromJson(getIntent().getStringExtra("data"), ProfileGetUserDataResponseRetrofit.ProfileGetUserDataModelRetrofit.class);
//
////        spinner_state = findViewById(R.id.spinner_state);
////        spinner_district = findViewById(R.id.spinner_district);
//
//
//        init();
//
//        fetchStates();
//        fetchDistrict();
//
////        getDropDownData();
//
////        binding.spinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
////            @Override
////            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
////                stateId = position + 1;
////                // getDropDownData();
////                fetchStates();
////
////                // Reset error message
//////                binding.spinnerState.setError(null);
////            }
////
////            @Override
////            public void onNothingSelected(AdapterView<?> parent) {
////                // Handle case when nothing is selected
////            }
////        });
//
//        spinner_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                stateId = position + 1;
////                getDropDownData();
//                fetchStates();
//
//                // Reset error message
////                spinner_state.setError(null);
////                spinner_state.setError("");
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                // Handle case when nothing is selected
//            }
//        });
//
//
////        spinner_state.setOnSpinnerItemSelectedListener((OnSpinnerItemSelectedListener<String>) (oldIndex, oldItem, newIndex, newItem) -> {
////            stateId = newIndex + 1;
//////            getDropDownData();
////            fetchStates();
////
////            spinner_state.setError(null);
////        });
//
////        spinner_district.setOnClickListener(v -> {
////            if (stateId == 0) {
////                spinner_state.setError("Select State");
////                fetchDistrict();
////
////            }
////            else spinner_district.showOrDismiss();
////        });
//
//
//        spinner_district = findViewById(R.id.spinner_district);
//
////        spinner_district.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                if (stateId == 0) {
//////                    spinner_state.setError("Select State");
////                    fetchDistrict();
////                } else {
////                    // Show or dismiss the district spinner
////                    // Handle this part according to your requirements
////                }
////            }
////        });
//
//        spinner_district.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (stateId == 0) {
////                    spinner_district.setError("Select State");
//                    fetchDistrict();
//                } else {
//                    // Show or dismiss the district spinner
//                    // Handle this part according to your requirements
//                }
//            }
//        });
//
//
//        getFCMToken();
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
////                        spinner_state.dismiss();
////                        spinner_district.dismiss();
////                        type.dismiss();
//                        return true; // consume the event
//                    }
//                }
//                return false; // do not consume the event
//            }
//        });
//
//        iv_back.setOnClickListener(view -> {
//            super.onBackPressed();
//        });
//
//
//    }
//
//
//    private void init() {
////        iv_profile = findViewById(R.id.iv_profile);
//        iv_close = findViewById(R.id.iv_close);
//        btn_save = findViewById(R.id.btn_save);
//        et_first_name = findViewById(R.id.et_first_name);
//        et_email = findViewById(R.id.et_email);
//
////        backbtn = findViewById(R.id.iv_back);
//        iv_back = findViewById(R.id.iv_back);
//
//        spinner_state = findViewById(R.id.spinner_state);
//        spinner_district = findViewById(R.id.spinner_district);
//
//
//        iv_back.setOnClickListener(this);
//
//
//        btn_save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isAllFieldsValidated())
//                    updateProfileDetails();
//
////                    getDropDownData();
//
//
//            }
//        });
//
////        iv_profile.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
//////                selectImage();
////            }
////        });
//
//        setdata();
//
//
//        iv_close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//            }
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
//            et_first_name.setText((CharSequence) data.getName());
//
//            et_email.setText((CharSequence) data.getEmail());
//
//            et_city.setText((CharSequence) data.getCity());
//
//
////            binding.etDob.setText((CharSequence) data.getDob());
////            if (data.getGender().equalsIgnoreCase("male"))
////                binding.rbMale.setChecked(true);
////            if (data.getGender().equalsIgnoreCase("female"))
////                binding.rbFemale.setChecked(true);
//
////            Glide.with(EditProfileFragment.this).load(getIntent().getStringExtra("basepath") + data.getProfilePhoto()).error(R.drawable.profile_img).error(R.drawable.profile_img).into(binding.ivProfile);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    private void updateProfileDetails() {
//
//        CommonUtils.showLoading(EditProfileActivity2.this, "Please Wait", false);
//
//        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);
//
//        MultipartBody.Builder builder = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM);
//
//
//        if (!selectedPath.trim().isEmpty()) {
//            File file = new File(selectedPath);
//            RequestBody fbody = RequestBody.create(MediaType.parse("image/*"),
//                    file);
//            builder.addFormDataPart("file", file.getName(), fbody);
//        }
//
//        String state = "";
//        String district = "";
////        String country = "";
//
//        try {
//            state = binding.spinnerState.getSelectedItem().toString();
////             state = binding.spinnerState.getSelectedData().toString();
////            state = binding.spinnerState.getSpinnerSelectedItemBackground().toString();
//
////            Object selectedItem = binding.spinnerState.getSelectedItem();
////            if (selectedItem != null) {
////                state = selectedItem.toString();
////            }
//
////            state.setOnSpinnerItemSelectedListener((OnSpinnerItemSelectedListener<String>) (oldIndex, oldItem, newIndex, newItem) -> {
////                stateId = newIndex+1;
////                getDropDownData();
////                state.setError(null);
////            });
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            district = binding.spinnerDistrict.getSelectedItem().toString();
////            district = binding.spinnerDistrict.getSelectedData().toString();
////            district = binding.spinnerDistrict.getSpinnerSelectedItemBackground().toString();
////            district.setOnClickListener(v -> {
////                if (stateId == 0)
////                {
////                    state.setError("Select State");
////                }
////                else district.showOrDismiss();
////            });
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
////        try {
////            country = binding.spinnerCountry.getSelectedItem().toString();
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////        String gender = "";
////        if (binding.rbMale.isChecked())
////            gender = "Male";
////        if (binding.rbFemale.isChecked())
////            gender = "Female";
//
//        builder
////                .addFormDataPart("fcm_token", SharedPrefs.getInstance(this).getString(Constants.FCM_TOKEN))
//                .addFormDataPart("user_id", SharedPrefs.getInstance(this).getString(Constants.USER_ID))
//                .addFormDataPart("token", SharedPrefs.getInstance(this).getString(Constants.TOKEN))
//                .addFormDataPart("name", et_first_name.getText().toString())
//
////                .addFormDataPart("city", binding.etCity.getText().toString())
//
////                .addFormDataPart("gender", gender)
//                .addFormDataPart("email", et_email.getText().toString())
//                .addFormDataPart("city", et_city.getText().toString())
//                .addFormDataPart("state", state)
//                .addFormDataPart("district", district)
////                .addFormDataPart("country", country)
//                .build();
//
//
//        Call<EditProfileUpdateDrailsUpdateModels> call = apiInterface.userDetailsUpdate(builder.build());
//        call.enqueue(new Callback<EditProfileUpdateDrailsUpdateModels>() {
//            @Override
//            public void onResponse(Call<EditProfileUpdateDrailsUpdateModels> call, Response<EditProfileUpdateDrailsUpdateModels> response) {
//                CommonUtils.hideLoading();
//                setResult(RESULT_OK);
//                if (response.body() != null && response.body().getStatusCode()) {
//                    if (response.body().getStatusCode()) {
//                        Toast.makeText(EditProfileActivity2.this, "Profile Details Updated successfully", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(EditProfileActivity2.this, "Something went wrong", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<EditProfileUpdateDrailsUpdateModels> call, Throwable t) {
//                CommonUtils.hideLoading();
//                Toast.makeText(EditProfileActivity2.this, "Something went wrong", Toast.LENGTH_SHORT).show();
//                t.printStackTrace();
//            }
//        });
//
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
//
//        if (TextUtils.isEmpty(et_city.getText().toString().trim())) {
//            et_city.setError("Required");
//            return false;
//        }
//        return true;
//    }
//
//    //    public void updateDetails(ProfileGetUserDataResponse.ProfileGetUserDataModel dat) {
//    public void updateDetails(ProfileGetUserDataResponseRetrofit.ProfileGetUserDataModelRetrofit dat) {
//
//        this.data = dat;
//        et_first_name.setText((CharSequence) data.getName());
//        et_email.setText((CharSequence) data.getEmail());
//        et_city.setText((CharSequence) data.getCity());
//
//
////        Glide.with(EditProfileActivity2.this).load(getIntent().getStringExtra("basepath") + data.getProfilePhoto()).error(R.drawable.profile_img).error(R.drawable.profile_img).into(iv_profile);
//
//    }
//
//    public void setDismissListener(DismissListener dismissListener) {
//        this.dismissListener = dismissListener;
//    }
//
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.iv_close:
////                resendOtp();
//                break;
//            case R.id.iv_back:
//                finish();
//                break;
//        }
//    }
//
//    public interface DismissListener {
//        public void onDismiss();
//    }
//
//    //
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
//
//    //
//    public void getFCMToken() {
//        FirebaseApp.initializeApp(EditProfileActivity2.this);
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
//    //Retrofit
//
////    public void getDropDownData() {
//////        ApiService apiService = RetrofitClient.createService(ApiService.class);
////        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);
////
////
//////        Call<JSONObject> call = apiService.getDropdownData();
//////        Call<JSONObject> call = apiInterface.getDropdownData();
////        Call<JSONObject> call = apiInterface.getDropdownData2();
////
//////        Call<GetDropdownDataReponsePrakruthiStateDistrict> call = apiInterface.getDropdownData2();
////
////
////        call.enqueue(new Callback<JSONObject>() {
//////        call.enqueue(new Callback<GetDropdownDataReponsePrakruthiStateDistrict>() {
////
////            @Override
////            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
//////            public void onResponse(Call<GetDropdownDataReponsePrakruthiStateDistrict> call, Response<GetDropdownDataReponsePrakruthiStateDistrict> response) {
////
////                if (response.isSuccessful()) {
////                    JSONObject jsonObj = response.body();
//////                    GetDropdownDataReponsePrakruthiStateDistrict jsonObj = response.body();
////
////
////                    try {
////                        JSONArray states = jsonObj.getJSONArray("state");
////                        ArrayList<String> stateNames = new ArrayList<>();
////                        for (int i = 0; i < states.length(); i++) {
////                            JSONObject state = states.getJSONObject(i);
////                            stateNames.add(state.getString("name"));
////                        }
////                        spinner_state.setItems(stateNames);
////
////                        JSONArray districts = jsonObj.getJSONArray("district");
////                        districtNames.clear();
////                        for (int i = 0; i < districts.length(); i++) {
////                            JSONObject districtt = districts.getJSONObject(i);
////                            if (districtt.getInt("state_id") == stateId) {
////                                districtNames.add(districtt.getString("name"));
////                            }
////                        }
////                        spinner_district.setItems(districtNames);
////                    } catch (JSONException e) {
////                        Log.e(TAG, e.toString());
////                        Toast.makeText(EditProfileActivity2.this, "Network Error", Toast.LENGTH_SHORT).show();
////                    }
////                } else {
////                    Toast.makeText(EditProfileActivity2.this, "Network Error", Toast.LENGTH_SHORT).show();
////                }
////            }
////
////            @Override
////            public void onFailure(Call<JSONObject> call, Throwable t) {
////                Log.e(TAG, t.getMessage());
////                Toast.makeText(EditProfileActivity2.this, "Network Error", Toast.LENGTH_SHORT).show();
////            }
////        });
////    }
//
////    private class ApiTask2 extends AsyncTask<String, Void, String> {
////
////        @Override
////        protected String doInBackground(String... params) {
////            String userId = params[0];
////            String token = params[1];
////            String name = params[2];
////            String email = params[3];
////            String city = params[4];
////            String state = params[5];
////            String district = params[6];
////
//////            ApiService apiService = RetrofitClient.createService(ApiService.class);
////            ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);
////
////
//////            Call<String> call = apiService.updateUserDetails(userId, token, name, email, city, state, district);
////            Call<String> call = apiInterface.updateUserDetails(userId, token, name, email, city, state, district);
////
////
////            try {
////                Response<String> response = call.execute();
////                if (response.isSuccessful()) {
////                    return response.body();
////                } else {
////                    // Handle error response
////                }
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////
////            return null;
////        }
////
////        @Override
////        protected void onPostExecute(String result) {
////            if (result != null) {
////                try {
////                    JSONObject json = new JSONObject(result);
////                    boolean statusCode = json.getBoolean("status_code");
////                    String message = json.getString("message");
////
////                    if (statusCode) {
////                        Toast.makeText(EditProfileActivity2.this, message, Toast.LENGTH_SHORT).show();
////                        getUserData(json);
////                    } else {
////                        Toast.makeText(EditProfileActivity2.this, message, Toast.LENGTH_SHORT).show();
////                        btn_save.setVisibility(View.VISIBLE);
////                    }
////                } catch (JSONException e) {
////                    e.printStackTrace();
////                    btn_save.setVisibility(View.VISIBLE);
////                    Toast.makeText(EditProfileActivity2.this, "Network Error", Toast.LENGTH_SHORT).show();
////                }
////            } else {
////                btn_save.setVisibility(View.VISIBLE);
////                Toast.makeText(EditProfileActivity2.this, "Network Error", Toast.LENGTH_SHORT).show();
////            }
////        }
////    }
//
//
//    //HttpURLConnection
////    @SuppressLint("StaticFieldLeak")
////    public void getDropDownData() {
////        new AsyncTask<Void, Void, JSONObject>() {
////            @Override
////            protected JSONObject doInBackground(Void... voids) {
////                FetchData fetchData = new FetchData("https://houseofspiritshyd.in/prakruthi/admin/api/getDropdownData");
////                if (fetchData.startFetch()) {
////                    if (fetchData.onComplete()) {
////                        String result = fetchData.getResult();
////                        Log.i("FetchData", result);
////                        try {
////                            JSONObject jsonObj = new JSONObject(result);
////                            return jsonObj;
////                        } catch (JSONException e) {
////                            Log.e(TAG, e.toString());
////                            return null;
//////                            return "";
////                        }
////                    }
////                }
////                return null;
//////                return "";
////            }
////
////            @Override
////            protected void onPostExecute(JSONObject jsonObj) {
////                super.onPostExecute(jsonObj);
////
////                if (jsonObj != null) {
////                    try {
//////                        JSONArray departments = jsonObj.getJSONArray("departments");
//////                        ArrayList<String> departmentNames = new ArrayList<>();
//////                        for(int i = 0; i < departments.length(); i++) {
//////                            JSONObject department = departments.getJSONObject(i);
//////                            departmentNames.add(department.getString("name"));
//////                        }
//////                        type.setItems(departmentNames);
////
////                        JSONArray states = jsonObj.getJSONArray("state");
////                        ArrayList<String> stateNames = new ArrayList<>();
////                        for (int i = 0; i < states.length(); i++) {
////                            JSONObject state = states.getJSONObject(i);
////                            stateNames.add(state.getString("name"));
////                        }
//////                        state.setItems(stateNames);
////                        spinner_state.setItems(stateNames);
////
////
////                        JSONArray districts = jsonObj.getJSONArray("district");
////                        districtNames.clear();
////
////                        //
//////                        ArrayList<String> districtNames = new ArrayList<>();
////
////                        for (int i = 0; i < districts.length(); i++) {
////                            JSONObject districtt = districts.getJSONObject(i);
////                            if (districtt.getInt("state_id") == stateId) {
////                                districtNames.add(districtt.getString("name"));
//////                                district.setItems(districtNames);
////                                spinner_district.setItems(districtNames);
////
////                            }
////                        }
//////                        district.setItems(districtNames);
//////                        spinner_district.setItems(districtNames);
////
////
////                    } catch (JSONException e) {
////                        Log.e(TAG, e.toString());
////                        Toast.makeText(EditProfileActivity2.this, "Network Error", Toast.LENGTH_SHORT).show();
////                    }
////                } else {
////                    Toast.makeText(EditProfileActivity2.this, "Network Error", Toast.LENGTH_SHORT).show();
////                }
////            }
////        }.execute();
////    }
////
////
////
////    @SuppressLint("StaticFieldLeak")
////
//////    private class ApiTask2 extends AsyncTask<String, Void, String> {
////    private class ApiTask2 extends AsyncTask<String, Void, String> {
//////        Loading.show(EditProfileActivity .this);
////
////        @Override
////        protected String doInBackground(String... params) {
////            //Creating array for parameters
////            String[] field = new String[7];
////            field[0] = "user_id";
////            field[1] = "token";
////
////            field[2] = "name";
//////            field[1] = "mobile";
////            field[3] = "email";
//////            field[3] = "password";
////            field[4] = "city";
//////            field[5] = "type";
////            field[5] = "state";
////            field[6] = "district";
//////            field[8] = "fcm_token";
////            //Creating array for data
////            String[] data = new String[7];
////            data[0] = params[0];
////            data[1] = params[1];
////            data[2] = params[2];
////            data[3] = params[3];
////            data[4] = params[4];
////            data[5] = params[5];
////            data[6] = params[6];
//////            data[7] = params[7];
//////            data[8] = params[8];
////
////            Log.e(TAG, Arrays.toString(data));
//////            PutData putData = new PutData(Variables.BaseUrl + "registration", "POST", field, data);
////            PutData putData = new PutData(Variables.BaseUrl + "userDetailsUpdate", "POST", field, data);
////
////            if (putData.startPut()) {
////                if (putData.onComplete()) {
////                    // result = Api Result
////                    String result = putData.getResult();
////                    Log.e(TAG, putData.getResult());
////                    return putData.getResult();
//////                    return result;
////
////                }
////            }
////
////            return null;
//////            return "";
////        }
////
////        @Override
////        protected void onPostExecute(String result) {
////
////            if (result != null) {
////                try {
////                    Log.e(TAG, result);
////                    JSONObject json = new JSONObject(result);
////                    boolean statusCode = json.getBoolean("status_code");
////                    String message = json.getString("message");
////
////                    if (statusCode) {
////                        Toast.makeText(EditProfileActivity2.this, message, Toast.LENGTH_SHORT).show();
////                        getUserData(json);
////                    } else {
////                        Toast.makeText(EditProfileActivity2.this, message, Toast.LENGTH_SHORT).show();
//////                        sendotp.setVisibility(View.VISIBLE);
////                        btn_save.setVisibility(View.VISIBLE);
////                    }
////                } catch (JSONException e) {
////                    e.printStackTrace();
//////                    sendotp.setVisibility(View.VISIBLE);
////                    btn_save.setVisibility(View.VISIBLE);
////
////                    Toast.makeText(EditProfileActivity2.this, "Network Error", Toast.LENGTH_SHORT).show();
////                }
////            } else {
//////                sendotp.setVisibility(View.VISIBLE);
////                btn_save.setVisibility(View.VISIBLE);
////
////                Toast.makeText(EditProfileActivity2.this, "Network Error", Toast.LENGTH_SHORT).show();
////            }
////        }
////    }
//
////    public void getUserData(JSONObject ResultJson) {
////        try {
////            JSONArray resultArray = ResultJson.getJSONArray("result");
////            for (int i = 0; i < resultArray.length(); i++) {
////
////                String status_code = ResultJson.getString("status_code");
////                String message = ResultJson.getString("message");
////
//////                String token = resultObject.getString("token");
////                String privacyPolicy = ResultJson.getString("privacyPolicy");
////                String termsAndConditions = ResultJson.getString("termsAndConditions");
//////                String message = ResultJson.getString("message");
////                JSONArray userDetailsArray = ResultJson.getJSONArray("result");
////                JSONObject userDetails = userDetailsArray.getJSONObject(0);
////
////                JSONObject resultObject = resultArray.getJSONObject(i);
////                int id = resultObject.getInt("id");
////                int departmentId = resultObject.getInt("department_id");
//////                int userId = resultObject.getInt("user_id");
////                String userId = String.valueOf(resultObject.getInt("user_id"));
//////                String apiToken = resultObject.getString("api_token");
//////                String user_code = resultObject.getString("user_code");
////                String userCode = resultObject.optString("user_code", "");
////
////
////                String name = resultObject.optString("name", "");
////                String lastName = resultObject.optString("last_name", "");
////                String email = resultObject.optString("email", "");
////                String password = resultObject.optString("password", "");
////                String mobile = resultObject.optString("mobile", "");
////                String gender = resultObject.optString("gender", "");
////                String dob = resultObject.optString("dob", "");
////                String attachment = resultObject.optString("attachment", "");
////                String city = resultObject.optString("city", "");
////                String postCode = resultObject.optString("postCode", "");
////                String address = resultObject.optString("address", "");
////                String state = resultObject.optString("state", "");
////                String country = resultObject.optString("country", "");
////                String district = resultObject.optString("district", "");
////                String street = resultObject.optString("street", "");
////                String about = resultObject.optString("about", "");
////                String status = resultObject.optString("status", "");
////                String mobileVerified = resultObject.optString("mobile_verified", "");
////                String isVerified = resultObject.optString("is_verified", "");
////                String logDateCreated = resultObject.optString("log_date_created", "");
////                String createdBy = resultObject.optString("created_by", "");
////                String logDateModified = resultObject.optString("log_date_modified", "");
////                String modifiedBy = resultObject.optString("modified_by", "");
////                String fcmToken = resultObject.optString("fcm_token", "");
////                String deviceId = resultObject.optString("device_id", "");
////                String allowEmail = resultObject.optString("allow_email", "");
////                String allowSms = resultObject.optString("allow_sms", "");
////                String allowPush = resultObject.optString("allow_push", "");
////
////
////                // Store values in static variables
////                Variables.clear();
////                Variables.status_code = status_code;
////                Variables.message = message;
////
//////                Variables.token = token;
//////                Variables.userId = userId;
////
////                Variables.id = id;
////                Variables.departmentId = departmentId;
////                Variables.userCode = userCode;
////                Variables.name = name;
////                Variables.lastName = lastName;
////                Variables.email = email;
////                Variables.password = password;
////                Variables.mobile = mobile;
////                Variables.gender = gender;
////                Variables.dob = dob;
////                Variables.attachment = attachment;
////                Variables.city = city;
////                Variables.postCode = postCode;
////                Variables.address = address;
////                Variables.state = state;
////                Variables.country = country;
////                Variables.district = district;
////                Variables.street = street;
////                Variables.about = about;
////                Variables.status = status;
////                Variables.mobileVerified = mobileVerified;
////                Variables.isVerified = isVerified;
////                Variables.logDateCreated = logDateCreated;
////                Variables.createdBy = createdBy;
////                Variables.logDateModified = logDateModified;
////                Variables.modifiedBy = modifiedBy;
////                Variables.fcmToken = fcmToken;
////                Variables.deviceId = deviceId;
////                Variables.allowEmail = allowEmail;
////                Variables.allowSms = allowSms;
////                Variables.allowPush = allowPush;
////
////
////            }
//////            sendotp.setVisibility(View.VISIBLE);
////            btn_save.setVisibility(View.VISIBLE);
////
////            Loading.hide();
////
////            if (RememberMe.isChecked())
////                rememberMe();
////
////
//////            Variables.phoneNumber = phone_number.getText().toString();
////            Variables.name = et_first_name.getText().toString();
////            Variables.email = et_email.getText().toString();
////            Variables.city = et_city.getText().toString();
////            Variables.state = spinner_state.getText().toString();
////            Variables.district = spinner_district.getText().toString();
////
//////            startActivity(new Intent(RegistrationForm.this, OTP_Verification.class));
////            startActivity(new Intent(EditProfileActivity2.this, ProfileFragment.class));
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
//    //    ArrayList<LocationTableResponse.LocationTable> citys = new ArrayList<>();
////    ArrayList<LocationTableResponse.LocationTable> states = new ArrayList<>();
//
//    //getDropdownData GET Method
////    ArrayList<GetDropdownDataReponsePrakruthiStateDistrict.State> states = new ArrayList<>();
//    ArrayList<GetDefaultDataPrakruthiStates.States> statess = new ArrayList<>();
//
//    //getDefaultData POST Method
//
//    //------
////    ArrayList<GetDropdownDataReponsePrakruthiStateDistrict.District> districts = new ArrayList<>();
//
//    ArrayList<GetDefaultDataPrakruthiDistrict.District> districts = new ArrayList<>();
//
//
////    ArrayList<LocationTableResponse.LocationTable> districts = new ArrayList<>();
////    ArrayList<LocationTableResponse.LocationTable> countrys = new ArrayList<>();
//
////    private void fetchCities() {
////        CommonUtils.showLoading(EditProfileActivity2.this, "Please Wait", false);
////        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);
////        JsonObject jsonObject = new JsonObject();
////        jsonObject.addProperty("user_id", SharedPrefs.getInstance(EditProfileActivity2.this).getString(Constants.USER_ID));
////        jsonObject.addProperty("token", SharedPrefs.getInstance(EditProfileActivity2.this).getString(Constants.TOKEN));
////        jsonObject.addProperty("table_name", "city");
////
////        Call<LocationTableResponse> call = apiInterface.getDefaultDataLoc(jsonObject);
////        call.enqueue(new Callback<LocationTableResponse>() {
////            @Override
////            public void onResponse(Call<LocationTableResponse> call, Response<LocationTableResponse> response) {
////                LocationTableResponse locationTableResponse = response.body();
////                citys = new ArrayList<>();
////                if (locationTableResponse != null && locationTableResponse.getLocationTables() != null) {
////                    citys.addAll(locationTableResponse.getLocationTables());
////                }
////                ArrayList<String> stateNames = new ArrayList<>();
////                stateNames.add("Select");
////                int selPos = 0;
////                for (LocationTableResponse.LocationTable locationTable : citys) {
////                    stateNames.add(locationTable.getName());
////                    if (data.getCity() != null && data.getCity().equals(locationTable.getName()))
////                        selPos = citys.indexOf(locationTable) + 1;
////                }
////                binding.spinnerCity.setAdapter(new ArrayAdapter<>(EditProfileActivity2.this, R.layout.list_units, stateNames));
////                binding.spinnerCity.setSelection(selPos);
////                CommonUtils.hideLoading();
////            }
////
////            @Override
////            public void onFailure(Call<LocationTableResponse> call, Throwable t) {
////                t.printStackTrace();
////                CommonUtils.hideLoading();
////            }
////        });
////
////    }
//
//    private void fetchStates() {
//        CommonUtils.showLoading(EditProfileActivity2.this, "Please Wait", false);
//        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);
//        JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("table_name", "states");
////        jsonObject.addProperty("user_id", SharedPrefs.getInstance(EditProfileActivity2.this).getString(Constants.USER_ID));
////        jsonObject.addProperty("token", SharedPrefs.getInstance(EditProfileActivity2.this).getString(Constants.TOKEN));
//
//        Call<GetDefaultDataPrakruthiStates> call = apiInterface.getDropdownData(jsonObject);
////        Call<GetDropdownDataReponsePrakruthiStateDistrict> call = apiInterface.getDropdownData(jsonObject);
////        Call<GetDropdownDataReponsePrakruthiStateDistrict> call = apiInterface.getDropdownData();
//        call.enqueue(new Callback<GetDefaultDataPrakruthiStates>() {
//            @Override
//            public void onResponse(Call<GetDefaultDataPrakruthiStates> call, Response<GetDefaultDataPrakruthiStates> response) {
//
//                GetDefaultDataPrakruthiStates getDropdownDataReponsePrakruthiMs = response.body();
//
//                statess = new ArrayList<>();
//
////                GetDefaultDataPrakruthiStates getDropdownDataReponsePrakruthiMs = response.body();
////                if (getDropdownDataReponsePrakruthiMs != null && getDropdownDataReponsePrakruthiMs.getStates() != null) {
//                if (getDropdownDataReponsePrakruthiMs != null && getDropdownDataReponsePrakruthiMs.getData() != null) {
//
////                    states.addAll(locationTableResponse.getLocationTables());
////                    states.addAll(getDropdownDataReponsePrakruthiMs.getState());
//                    statess.addAll(getDropdownDataReponsePrakruthiMs.getData());
//
//                }
//
//                ArrayList<String> stateNames = new ArrayList<>();
//                stateNames.add("Select");
////                stateNames.add("Australia");
////                stateNames.add("Telangana");
//
//                int selPos = 0;
//
//                for (GetDefaultDataPrakruthiStates.States locationTable : statess) {
//                    stateNames.add(locationTable.getName());
////                    if (data.getState().equals(locationTable.getName()))
//                    if (data.getState() != null &&data.getState().equals(locationTable.getName()))
//
//                        selPos = statess.indexOf(locationTable) + 1;
//                }
//
//                binding.spinnerState.setAdapter(new ArrayAdapter<>(EditProfileActivity2.this, R.layout.list_units, stateNames));
//                binding.spinnerState.setSelection(selPos);
////                int selPos = 0;
////                for (int i = 0; i < statess.size(); i++) {
////                    GetDefaultDataPrakruthiStates.States locationTable = statess.get(i);
////                    stateNames.add(locationTable.getName());
//////                    if (data.getState().equals(locationTable.getName())) {
////                    if (data.getState().equals(locationTable.getName())) {
////
////                        selPos = i + 1;
////                        break; // Exit the loop if a match is found
////                    }
////                }
//
////                CommonUtils.hideLoading();
//            }
//
//            @Override
//            public void onFailure(Call<GetDefaultDataPrakruthiStates> call, Throwable t) {
//                t.printStackTrace();
//                CommonUtils.hideLoading();
//            }
//        });
//
//    }
//
//    //    private void fetchCountry() {
//    private void fetchDistrict() {
//        CommonUtils.showLoading(EditProfileActivity2.this, "Please Wait", false);
//        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);
//        JsonObject jsonObject = new JsonObject();
////        jsonObject.addProperty("user_id", SharedPrefs.getInstance(EditProfileActivity2.this).getString(Constants.USER_ID));
////        jsonObject.addProperty("token", SharedPrefs.getInstance(EditProfileActivity2.this).getString(Constants.TOKEN));
////        jsonObject.addProperty("table_name", "country");
//        jsonObject.addProperty("id", SharedPrefs.getInstance(EditProfileActivity2.this).getString(Constants.ID));
//        jsonObject.addProperty("reference_id", "state_id");
//        jsonObject.addProperty("table_name", "district");
//
//
////        Call<LocationTableResponse> call = apiInterface.getDefaultDataLoc(jsonObject);
////        Call<GetDropdownDataReponsePrakruthiStateDistrict> call = apiInterface.getDropdownData(jsonObject);
//        Call<GetDefaultDataPrakruthiDistrict> call = apiInterface.getDropdownData1(jsonObject);
//
//        call.enqueue(new Callback<GetDefaultDataPrakruthiDistrict>() {
//            @Override
//            public void onResponse(Call<GetDefaultDataPrakruthiDistrict> call, Response<GetDefaultDataPrakruthiDistrict> response) {
//                districts = new ArrayList<>();
//                GetDefaultDataPrakruthiDistrict getDropdownDataReponsePrakruthiMs = response.body();
////                if (getDropdownDataReponsePrakruthiMs != null && getDropdownDataReponsePrakruthiMs.getDistrict() != null) {
//                if (getDropdownDataReponsePrakruthiMs != null && getDropdownDataReponsePrakruthiMs.getData() != null) {
//
//                    districts.addAll(getDropdownDataReponsePrakruthiMs.getData());
//                }
//
//                ArrayList<String> districtNames = new ArrayList<>();
////                stateNames.add("Select");
////                stateNames.add("Australia");
//                districtNames.add("Medhak");
//
//
//                int selPos = 0;
//                for (GetDefaultDataPrakruthiDistrict.District locationTable : districts) {
//                    districtNames.add(locationTable.getName());
//                    if (data.getCountry() != null && data.getCountry().equals(locationTable.getName()))
//                        selPos = districts.indexOf(locationTable) + 1;
//                }
//                binding.spinnerDistrict.
//                        setAdapter(new ArrayAdapter<>(EditProfileActivity2.this, R.layout.list_units, districtNames));
//                binding.spinnerDistrict.setSelection(selPos);
//                CommonUtils.hideLoading();
//            }
//
//            @Override
//            public void onFailure(Call<GetDefaultDataPrakruthiDistrict> call, Throwable t) {
//                t.printStackTrace();
//                CommonUtils.hideLoading();
//            }
//        });
//
//    }
//
//
//}