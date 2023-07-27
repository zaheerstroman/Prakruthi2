package com.prakruthi.ui.ui.profile;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.prakruthi.R;
import com.prakruthi.databinding.FragmentEditProfile2Binding;
import com.prakruthi.ui.Variables;
import com.prakruthi.ui.misc.Loading;
import com.prakruthi.ui.network.APIClient;
import com.prakruthi.ui.network.ApiInterface;
import com.prakruthi.ui.utils.CommonUtils;
import com.prakruthi.ui.utils.Constants;
import com.prakruthi.ui.utils.SharedPrefs;
import com.skydoves.powerspinner.OnSpinnerItemSelectedListener;
import com.skydoves.powerspinner.PowerSpinnerView;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


//public class EditProfileFragment2 extends Fragment implements View.OnClickListener {
public class EditProfileFragment2 extends BottomSheetDialogFragment {
//public class EditProfileFragment2 extends AppCompatActivity implements View.OnClickListener{

    //    int view = R.layout.activity_edit_profile_fragment2;
    int view = R.layout.fragment_edit_profile2;
    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

    EditText et_first_name, et_email, et_city;

    //    public Spinner spinner_state, spinner_district;
    public PowerSpinnerView spinner_state, spinner_district;

    int stateId = 0;
    ImageView iv_close;
    AppCompatButton sendotp, backbtn;

    SharedPreferences sharedPreferences;
    Button btn_save;

    ArrayList<String> districtNames = new ArrayList<>();

    //Use for Get Method only

    CheckBox RememberMe;


//    PowerSpinnerView spinner_state, spinner_district;

    //    ProfileGetUserDataResponse.ProfileGetUserDataModel data;
    ProfileGetUserDataResponseRetrofit.ProfileGetUserDataModelRetrofit data;
    EditProfileUpdateDrailsUpdateModels editProfileUpdateDrailsUpdateModels;

    private DismissListener dismissListener;

    private Uri imageUri;
    private String selectedPath = " ";

//    PowerSpinnerView spinnerState = binding.spinnerState;
//    PowerSpinnerView spinnerDistrict = binding.spinnerDistrict;

    private FragmentEditProfile2Binding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile2, container, false);

//        binding = FragmentEditProfile2Binding.inflate(inflater, container, false);
//        binding = FragmentEditProfile2Binding.inflate(getLayoutInflater());

//        return binding.getRoot();
//        setContentView(R.layout.fragment_edit_profile2);
//        setContentView(binding.getRoot());
//        data = new Gson().fromJson(getIntent().getStringExtra("data"), ProfileGetUserDataResponse.ProfileGetUserDataModel.class);
//        data = new Gson().fromJson(requireContext().getStringExtra("data"), ProfileGetUserDataResponseRetrofit.ProfileGetUserDataModelRetrofit.class);
        data = new Gson().fromJson(getArguments().getString("data"), ProfileGetUserDataResponseRetrofit.ProfileGetUserDataModelRetrofit.class);

//        spinner_state = findViewById(R.id.spinner_state);
//        spinner_district = findViewById(R.id.spinner_district);


//        init();
        init(view);

        //POST Method
        fetchStates();
        fetchDistrict();
        //GET Method
//        getDropDownData();

        spinner_state = view.findViewById(R.id.spinner_state);

        spinner_state.setOnSpinnerItemSelectedListener((OnSpinnerItemSelectedListener<String>) (oldIndex, oldItem, newIndex, newItem) -> {
            stateId = newIndex + 1;

//            getDropDownData();
            fetchStates();

            spinner_state.setError(null);
        });


        //-----------------------------------------------------------

//        binding.spinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                stateId = position + 1;
//                // getDropDownData();
//                fetchStates();
//
//                // Reset error message
////                binding.spinnerState.setError(null);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                // Handle case when nothing is selected
//            }
//        });

        //------------------------------------------------

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

//------------------------------------------------------------

//        spinner_state.setOnSpinnerItemSelectedListener((OnSpinnerItemSelectedListener<String>) (oldIndex, oldItem, newIndex, newItem) -> {
//            stateId = newIndex + 1;
////            getDropDownData();
//            fetchStates();
//
//            spinner_state.setError(null);
//        });

        //------------------------------------------------------------------------------

        spinner_district = view.findViewById(R.id.spinner_district);

        spinner_district.setOnClickListener(v -> {
            if (stateId == 0) {
                spinner_state.setError("Select State");

                fetchDistrict();

            } else spinner_district.showOrDismiss();
        });


        //-------------------------------------------------------------


//        spinner_district.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (stateId == 0) {
////                    spinner_state.setError("Select State");
//                    fetchDistrict();
//                } else {
//                    // Show or dismiss the district spinner
//                    // Handle this part according to your requirements
//                }
//            }
//        });

        //----------------------------------------------------------------

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


        getFCMToken();

        //Fragment
        View root = view.getRootView();
//        Activity
//        View root = view.findViewById(android.R.id.content);
        root.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // check if the touch is outside of the state view
                    int[] location = new int[2];
                    spinner_state.getLocationOnScreen(location);
                    int x = location[0];
                    int y = location[1];
                    int width = spinner_state.getWidth();
                    int height = spinner_state.getHeight();
                    if (!(event.getX() > x && event.getX() < x + width && event.getY() > y && event.getY() < y + height)) {
                        // dismiss the state view
                        spinner_state.dismiss();
                        spinner_district.dismiss();
//                        type.dismiss();
                        return true; // consume the event
                    }
                }
                return false; // do not consume the event
            }
        });

//        iv_back.setOnClickListener(view -> {
//            super.onBackPressed();
//        });

//        iv_close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dismiss();
//            }
//        });

        return view;
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//
//    }

    private void init(View view) {
//        iv_profile = findViewById(R.id.iv_profile);
        iv_close = view.findViewById(R.id.iv_close);
        backbtn = view.findViewById(R.id.backbtn);

        et_first_name = view.findViewById(R.id.et_first_name);
        et_email = view.findViewById(R.id.et_email);
        et_city = view.findViewById(R.id.et_city);
        spinner_state = view.findViewById(R.id.spinner_state);
        spinner_district = view.findViewById(R.id.spinner_district);

        btn_save = view.findViewById(R.id.btn_save);


//        backbtn.setOnClickListener(this);

        //Retrofit:---
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAllFieldsValidated())

                    updateProfileDetails();

//                    getDropDownData();

            }
        });

//        iv_profile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                selectImage();
//            }
//        });

        setdata();


        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        //HttpURLConnection:---
//        btn_save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                String state = "";
////                String district = "";
//
//                spinner_state.setError(null);
//                spinner_district.setError(null);
////            type.setError(null);
//
//                //            else if (Variables.userId == null || Variables.userId.isEmpty()) {
//                if (Variables.userId == null) {
//                    Toast.makeText(requireContext(), "Internal Error", Toast.LENGTH_SHORT).show();
//                    return;
//
//                }
//
//                else if (Variables.token == null || Variables.token.isEmpty()) {
//                    Toast.makeText(requireContext(), "Internal Error", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                else if (et_first_name.getText().toString().trim().isEmpty()) {
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
//
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
//
//
//                else {
//                    String fullnameStr = et_first_name.getText().toString().trim();
////                String phoneStr = phone_number.getText().toString().trim();
//                    String emailStr = et_email.getText().toString().trim();
////                String passwordStr = password.getText().toString().trim();
////                String cityStr = city.getText().toString().trim();
//                    String cityStr = String.valueOf(et_city.getText().toString().trim());
//                    String stateStr = String.valueOf(spinner_state.getSelectedIndex() + 1);
////                    String districtStr = String.valueOf(district.getSelectedIndex() + 1);
//                    String districtStr = String.valueOf(spinner_district.getSelectedIndex() + 1);
//
////                String typeStr = String.valueOf(GetDepartmentId(type.getText().toString()));
////                new RegistrationForm.ApiTask().execute(fullnameStr,phoneStr,emailStr,passwordStr,cityStr,typeStr,stateStr,districtStr,Variables.fcmToken);
//                    new ApiTask2().execute(Variables.userId, Variables.token, fullnameStr, emailStr, cityStr, stateStr, districtStr);
//
//                }
//            }
//        });

//        btn_save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isAllFieldsValidated())
//                    updateProfileDetails();
//
////                    getDropDownData();
//
//            }
//        });


    }


    private void setdata() {

        try {

            et_first_name.setText((CharSequence) data.getName());

            et_email.setText((CharSequence) data.getEmail());

            et_city.setText((CharSequence) data.getCity());

            spinner_state.setText((CharSequence) data.getState());

            spinner_district.setText((CharSequence) data.getDistrict());


//            binding.etDob.setText((CharSequence) data.getDob());
//            if (data.getGender().equalsIgnoreCase("male"))
//                binding.rbMale.setChecked(true);
//            if (data.getGender().equalsIgnoreCase("female"))
//                binding.rbFemale.setChecked(true);

//            Glide.with(EditProfileFragment.this).load(getIntent().getStringExtra("basepath") + data.getProfilePhoto()).error(R.drawable.profile_img).error(R.drawable.profile_img).into(binding.ivProfile);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Retrofit:-----
    private void updateProfileDetails() {

        CommonUtils.showLoading(requireContext(), "Please Wait", false);

        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);

//        spinner_state.setError(null);
//        spinner_district.setError(null);


//        if (!selectedPath.trim().isEmpty()) {
//            File file = new File(selectedPath);
//            RequestBody fbody = RequestBody.create(MediaType.parse("image/*"),
//                    file);
//            builder.addFormDataPart("file", file.getName(), fbody);
//        }

         if (Variables.userId == null) {
            Toast.makeText(requireContext(), "Internal Error", Toast.LENGTH_SHORT).show();
            return;

        }

         else if (Variables.token == null || Variables.token.isEmpty()) {
            Toast.makeText(requireContext(), "Internal Error", Toast.LENGTH_SHORT).show();
            return;
        }

        else if (et_first_name.getText().toString().trim().isEmpty()) {
            et_first_name.setError("Full name is required");
            ObjectAnimator.ofFloat(et_first_name, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
            et_first_name.requestFocus();
            return;
        } else if (et_email.getText().toString().trim().isEmpty()) {
            et_email.setError("Email is required");
            ObjectAnimator.ofFloat(et_email, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
            et_email.requestFocus();
            return;
        } else if (et_city.getText().toString().trim().isEmpty()) {
            et_city.setError("City is required");
            ObjectAnimator.ofFloat(et_city, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
            et_city.requestFocus();
            return;
        } else if (spinner_state.getText().toString().isEmpty()) {
            spinner_state.setError("State is required");
            ObjectAnimator.ofFloat(spinner_state, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
            spinner_state.requestFocus();
            return;

        } else if (spinner_district.getText().toString().isEmpty()) {
            spinner_district.setError("District is required");
            ObjectAnimator.ofFloat(spinner_district, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
            spinner_district.requestFocus();
            return;
        }

        else {
            String fullnameStr = et_first_name.getText().toString().trim();
//                String phoneStr = phone_number.getText().toString().trim();
            String emailStr = et_email.getText().toString().trim();
//                String passwordStr = password.getText().toString().trim();
//                String cityStr = city.getText().toString().trim();
            String cityStr = String.valueOf(et_city.getText().toString().trim());
            String stateStr = String.valueOf(spinner_state.getSelectedIndex() + 1);
            String districtStr = String.valueOf(spinner_district.getSelectedIndex() + 1);
//                String typeStr = String.valueOf(GetDepartmentId(type.getText().toString()));
//                new RegistrationForm.ApiTask().execute(fullnameStr,phoneStr,emailStr,passwordStr,cityStr,typeStr,stateStr,districtStr,Variables.fcmToken);

            new ApiTask2().execute((String) Variables.userId, Variables.token, fullnameStr, emailStr, cityStr, stateStr, districtStr);
//            new ApiTask2().execute(fullnameStr, emailStr, cityStr, (String) Variables.userId, Variables.token);


        }


        String state = "";
        String district = "";

        String country = "";

        try {
            //--------------------------------
//            state = spinner_state.getSelectedItem().toString();

//            state = binding.spinnerState.getSelectedItem().toString();
//             state = binding.spinnerState.getSelectedData().toString();
//            state = binding.spinnerState.getSpinnerSelectedItemBackground().toString();

//            Object selectedItem = binding.spinnerState.getSelectedItem();
//            if (selectedItem != null) {
//                state = selectedItem.toString();
//            }

//            state.setOnSpinnerItemSelectedListener((OnSpinnerItemSelectedListener<String>) (oldIndex, oldItem, newIndex, newItem) -> {
//                stateId = newIndex+1;
//                getDropDownData();
//                state.setError(null);
//            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //-------------------------------------------------
//            district = spinner_district.getSelectedItem().toString();

//            district = binding.spinnerDistrict.getSelectedItem().toString();
//            district = binding.spinnerDistrict.getSelectedData().toString();
//            district = binding.spinnerDistrict.getSpinnerSelectedItemBackground().toString();
//            district.setOnClickListener(v -> {
//                if (stateId == 0)
//                {
//                    state.setError("Select State");
//                }
//                else district.showOrDismiss();
//            });

        } catch (Exception e) {
            e.printStackTrace();
        }

//        try {
//            country = binding.spinnerCountry.getSelectedItem().toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String gender = "";
//        if (binding.rbMale.isChecked())
//            gender = "Male";
//        if (binding.rbFemale.isChecked())
//            gender = "Female";

        builder
//                .addFormDataPart("fcm_token", SharedPrefs.getInstance(this).getString(Constants.FCM_TOKEN))
                .addFormDataPart("user_id", SharedPrefs.getInstance(requireContext()).getString(Constants.USER_ID))
                .addFormDataPart("token", SharedPrefs.getInstance(requireContext()).getString(Constants.TOKEN))
                .addFormDataPart("name", et_first_name.getText().toString())

//                .addFormDataPart("city", binding.etCity.getText().toString())
//                .addFormDataPart("gender", gender)
                .addFormDataPart("email", et_email.getText().toString())
                .addFormDataPart("city", et_city.getText().toString())
                .addFormDataPart("state", state)
                .addFormDataPart("district", district)
//                .addFormDataPart("country", country)
                .build();


        Call<EditProfileUpdateDrailsUpdateModels> call = apiInterface.userDetailsUpdate(builder.build());
        call.enqueue(new Callback<EditProfileUpdateDrailsUpdateModels>() {
            @Override
            public void onResponse(Call<EditProfileUpdateDrailsUpdateModels> call, Response<EditProfileUpdateDrailsUpdateModels> response) {
                CommonUtils.hideLoading();
//                setResult(RESULT_OK);
                if (response.body() != null && response.body().getStatusCode()) {
                    if (response.body().getStatusCode()) {
                        Toast.makeText(requireContext(), "Profile Details Updated successfully", Toast.LENGTH_SHORT).show();
                        dismissListener.onDismiss();
                        dismiss();
                    } else {
                        Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<EditProfileUpdateDrailsUpdateModels> call, Throwable t) {
                CommonUtils.hideLoading();
                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

    }


    //    //HttpURLConnection:--
    @SuppressLint("StaticFieldLeak")
//    private class ApiTask2 extends AsyncTask<String, Void, String> {
    private class ApiTask2 extends AsyncTask<String, Void, String> {
//        Loading.show(EditProfileActivity .this);

        @Override
        protected String doInBackground(String... params) {
            //Creating array for parameters
            String[] field = new String[7];
            field[0] = "user_id";
            field[1] = "token";

            field[2] = "name";
//            field[1] = "mobile";
            field[3] = "email";
//            field[3] = "password";
            field[4] = "city";
//            field[5] = "type";
            field[5] = "state";
            field[6] = "district";
//            field[8] = "fcm_token";
            //Creating array for data
            String[] data = new String[7];
            data[0] = params[0];
            data[1] = params[1];
            data[2] = params[2];
            data[3] = params[3];
            data[4] = params[4];
            data[5] = params[5];
            data[6] = params[6];
//            data[7] = params[7];
//            data[8] = params[8];

            Log.e(TAG, Arrays.toString(data));
//            PutData putData = new PutData(Variables.BaseUrl + "registration", "POST", field, data);
            PutData putData = new PutData(Variables.BaseUrl + "userDetailsUpdate", "POST", field, data);

            if (putData.startPut()) {
                if (putData.onComplete()) {
                    // result = Api Result
                    String result = putData.getResult();
                    Log.e(TAG, putData.getResult());
                    return putData.getResult();
//                    return result;

                }
            }

            return null;
//            return "";
        }

        @Override
        protected void onPostExecute(String result) {

            if (result != null) {
                try {
                    Log.e(TAG, result);
                    JSONObject json = new JSONObject(result);
                    boolean statusCode = json.getBoolean("status_code");
                    String message = json.getString("message");

                    if (statusCode) {
                        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
                        getUserData(json);
                    } else {
                        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
//                        sendotp.setVisibility(View.VISIBLE);
                        btn_save.setVisibility(View.VISIBLE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
//                    sendotp.setVisibility(View.VISIBLE);
                    btn_save.setVisibility(View.VISIBLE);

                    Toast.makeText(requireContext(), "Network Error", Toast.LENGTH_SHORT).show();
                }
            } else {
//                sendotp.setVisibility(View.VISIBLE);
                btn_save.setVisibility(View.VISIBLE);

                Toast.makeText(requireContext(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //HttpURLConnection:--
    public void getUserData(JSONObject ResultJson) {
        try {
//            String token = ResultJson.getString("token");
            String status_code = ResultJson.getString("status_code");
            String privacyPolicy = ResultJson.getString("privacyPolicy");
            String termsAndConditions = ResultJson.getString("termsAndConditions");
            String returnRefundPolicy = ResultJson.getString("returnRefundPolicy");
            String security = ResultJson.getString("security");
            String aboutUs = ResultJson.getString("aboutUs");
            String message = ResultJson.getString("message");

//            JSONArray userDetailsArray = ResultJson.getJSONArray("user_details");
            JSONArray userDetailsArray = ResultJson.getJSONArray("data");


            JSONObject userDetails = userDetailsArray.getJSONObject(0);
//            int id = userDetails.getInt("id");
//            int departmentId = userDetails.getInt("department_id");
            String id = String.valueOf(userDetails.getInt("id"));
            String departmentId = String.valueOf(userDetails.getInt("department_id"));
            String userCode = userDetails.getString("user_code");
            String name = userDetails.optString("name", "");
            String lastName = userDetails.optString("last_name", "");
            String email = userDetails.optString("email", "");
            String password = userDetails.optString("password", "");
            String mobile = userDetails.optString("mobile", "");
            String gender = userDetails.optString("gender", "");
            String dob = userDetails.optString("dob", "");
            String attachment = userDetails.optString("attachment", "");
            String city = userDetails.optString("city", "");
            String postCode = userDetails.optString("postCode", "");
            String address = userDetails.optString("address", "");
            String state = userDetails.optString("state", "");
            String country = userDetails.optString("country", "");
            String district = userDetails.optString("district", "");
            String street = userDetails.optString("street", "");
            String about = userDetails.optString("about", "");
            String status = userDetails.optString("status", "");
            String mobileVerified = userDetails.optString("mobile_verified", "");
            String isVerified = userDetails.optString("is_verified", "");
            String logDateCreated = userDetails.optString("log_date_created", "");
            String createdBy = userDetails.optString("created_by", "");
            String logDateModified = userDetails.optString("log_date_modified", "");
            String modifiedBy = userDetails.optString("modified_by", "");
            String fcmToken = userDetails.optString("fcm_token", "");
            String deviceId = userDetails.optString("device_id", "");
            String allowEmail = userDetails.optString("allow_email", "");
            String allowSms = userDetails.optString("allow_sms", "");
            String allowPush = userDetails.optString("allow_push", "");

            // Store values in static variables
            Variables.clear();

            Variables.status_code = status_code;
            Variables.privacyPolicy = privacyPolicy;
            Variables.termsAndConditions = termsAndConditions;
            Variables.returnRefundPolicy = returnRefundPolicy;
            Variables.security = security;
            Variables.aboutUs = aboutUs;
            Variables.message = message;

//            Variables.token = token;
            Variables.id = Integer.valueOf(id);
            Variables.departmentId = Integer.valueOf(departmentId);

//            Variables.id = String.valueOf(id);
//            Variables.departmentId = String.valueOf(departmentId);
            Variables.userCode = userCode;
            Variables.name = name;
            Variables.lastName = lastName;
            Variables.email = email;
            Variables.password = password;
            Variables.mobile = mobile;
            Variables.gender = gender;
            Variables.dob = dob;
            Variables.attachment = attachment;
            Variables.city = city;
            Variables.postCode = postCode;
            Variables.address = address;
            Variables.state = state;
            Variables.country = country;
            Variables.district = district;
            Variables.street = street;
            Variables.about = about;
            Variables.status = status;
            Variables.mobileVerified = mobileVerified;
            Variables.isVerified = isVerified;
            Variables.logDateCreated = logDateCreated;
            Variables.createdBy = createdBy;
            Variables.logDateModified = logDateModified;
            Variables.modifiedBy = modifiedBy;
            Variables.fcmToken = fcmToken;
            Variables.deviceId = deviceId;
            Variables.allowEmail = allowEmail;
            Variables.allowSms = allowSms;
            Variables.allowPush = allowPush;

//            Log.e(TAG, Variables.id+Variables.token );

            btn_save.setVisibility(View.VISIBLE);
            Loading.hide();
//            if (RememberMe.isChecked())
//                rememberMe();
            startActivity(new Intent(requireContext(), EditProfileFragment2.class));
//            finish();
        } catch (JSONException e) {
            Log.e(TAG, e.toString());
            Toast.makeText(requireContext(), "Internal Error", Toast.LENGTH_SHORT).show();
            btn_save.setVisibility(View.VISIBLE);
//            LoginLayout.setVisibility(View.VISIBLE);
        }

    }



    //Retrofit:--
    private boolean isAllFieldsValidated() {

        if (TextUtils.isEmpty(et_first_name.getText().toString().trim())) {
            et_first_name.setError("Required");
            return false;
        }

        if (TextUtils.isEmpty(et_email.getText().toString().trim()) || !Patterns.EMAIL_ADDRESS.matcher(et_email.getText().toString()).matches()) {
            et_email.setError("Not Valid");
            return false;
        }

        if (TextUtils.isEmpty(et_city.getText().toString().trim())) {
            et_city.setError("Required");
            return false;
        }

//        if (TextUtils.isEmpty(spinner_state.getText().toString().isEmpty())) {
        if (spinner_state.getText().toString().isEmpty()) {
            spinner_state.setError("State is required");
            ObjectAnimator.ofFloat(spinner_state, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
            spinner_state.requestFocus();
            return false;

        }
        if (spinner_district.getText().toString().isEmpty()) {
            spinner_district.setError("District is required");
            ObjectAnimator.ofFloat(spinner_district, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
            spinner_district.requestFocus();
            return false;
        }

        return true;
    }


//Retrofit:---
    //    public void updateDetails(ProfileGetUserDataResponse.ProfileGetUserDataModel dat) {
    public void updateDetails(ProfileGetUserDataResponseRetrofit.ProfileGetUserDataModelRetrofit dat) {

        this.data = dat;
        et_first_name.setText((CharSequence) data.getName());
        et_email.setText((CharSequence) data.getEmail());
        et_city.setText((CharSequence) data.getCity());

        spinner_state.setText((CharSequence) data.getState());
        spinner_district.setText((CharSequence) data.getDistrict());


//        Glide.with(EditProfileActivity2.this).load(getIntent().getStringExtra("basepath") + data.getProfilePhoto()).error(R.drawable.profile_img).error(R.drawable.profile_img).into(iv_profile);

    }

    //Retrofit
    public void setDismissListener(DismissListener dismissListener) {
        this.dismissListener = dismissListener;
    }

//    @Override
//    public void onClick(View v) {
//
//    }


    //Retrofit
    public interface DismissListener {
        public void onDismiss();
    }

//    @Override
//    public void onClick(View view) {
//
//    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.iv_close:
////                resendOtp();
//                break;
////            case R.id.iv_back:
////                finish();
////                break;
//        }
//    }

//    public interface DismissListener {
//        public void onDismiss();
//    }

    //
    public void rememberMe() {
        // Get SharedPreferences.Editor object
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Set String value
        editor.putString("name", et_first_name.getText().toString());
        editor.putString("email", et_email.getText().toString());
        editor.putString("city", et_city.getText().toString());

        editor.putBoolean("rememberMe", true);
        // Apply changes
        editor.apply();
    }


    //
    public void getFCMToken() {
        FirebaseApp.initializeApp(requireContext());
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.d("firebase", "Fetching FCM Edit token failed", task.getException());
                            return;
                        }
                        // Get new FCM registration token
                        String token = task.getResult();
                        Log.d("firebase", "token" + token);
                        Variables.token = token;
                    }
                });
    }

    //Retrofit

//    public void getDropDownData() {
////        ApiService apiService = RetrofitClient.createService(ApiService.class);
//        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);
//
//
////        Call<JSONObject> call = apiService.getDropdownData();
////        Call<JSONObject> call = apiInterface.getDropdownData();
//        Call<JSONObject> call = apiInterface.getDropdownData2();
//
////        Call<GetDropdownDataReponsePrakruthiStateDistrict> call = apiInterface.getDropdownData2();
//
//
//        call.enqueue(new Callback<JSONObject>() {
////        call.enqueue(new Callback<GetDropdownDataReponsePrakruthiStateDistrict>() {
//
//            @Override
//            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
////            public void onResponse(Call<GetDropdownDataReponsePrakruthiStateDistrict> call, Response<GetDropdownDataReponsePrakruthiStateDistrict> response) {
//
//                if (response.isSuccessful()) {
//                    JSONObject jsonObj = response.body();
////                    GetDropdownDataReponsePrakruthiStateDistrict jsonObj = response.body();
//
//
//                    try {
//                        JSONArray states = jsonObj.getJSONArray("state");
//                        ArrayList<String> stateNames = new ArrayList<>();
//                        for (int i = 0; i < states.length(); i++) {
//                            JSONObject state = states.getJSONObject(i);
//                            stateNames.add(state.getString("name"));
//                        }
//                        spinner_state.setItems(stateNames);
//
//                        JSONArray districts = jsonObj.getJSONArray("district");
//                        districtNames.clear();
//                        for (int i = 0; i < districts.length(); i++) {
//                            JSONObject districtt = districts.getJSONObject(i);
//                            if (districtt.getInt("state_id") == stateId) {
//                                districtNames.add(districtt.getString("name"));
//                            }
//                        }
//                        spinner_district.setItems(districtNames);
//                    } catch (JSONException e) {
//                        Log.e(TAG, e.toString());
//                        Toast.makeText(EditProfileFragment2.this, "Network Error", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(EditProfileFragment2.this, "Network Error", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<JSONObject> call, Throwable t) {
//                Log.e(TAG, t.getMessage());
//                Toast.makeText(EditProfileFragment2.this, "Network Error", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

//    private class ApiTask2 extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            String userId = params[0];
//            String token = params[1];
//            String name = params[2];
//            String email = params[3];
//            String city = params[4];
//            String state = params[5];
//            String district = params[6];
//
////            ApiService apiService = RetrofitClient.createService(ApiService.class);
//            ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);
//
//
////            Call<String> call = apiService.updateUserDetails(userId, token, name, email, city, state, district);
//            Call<String> call = apiInterface.updateUserDetails(userId, token, name, email, city, state, district);
//
//
//            try {
//                Response<String> response = call.execute();
//                if (response.isSuccessful()) {
//                    return response.body();
//                } else {
//                    // Handle error response
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            if (result != null) {
//                try {
//                    JSONObject json = new JSONObject(result);
//                    boolean statusCode = json.getBoolean("status_code");
//                    String message = json.getString("message");
//
//                    if (statusCode) {
//                        Toast.makeText(EditProfileFragment2.this, message, Toast.LENGTH_SHORT).show();
//                        getUserData(json);
//                    } else {
//                        Toast.makeText(EditProfileFragment2.this, message, Toast.LENGTH_SHORT).show();
//                        btn_save.setVisibility(View.VISIBLE);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    btn_save.setVisibility(View.VISIBLE);
//                    Toast.makeText(EditProfileFragment2.this, "Network Error", Toast.LENGTH_SHORT).show();
//                }
//            } else {
//                btn_save.setVisibility(View.VISIBLE);
//                Toast.makeText(EditProfileFragment2.this, "Network Error", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }


    //HttpURLConnection
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
//                        Toast.makeText(EditProfileFragment2.this, "Network Error", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(EditProfileFragment2.this, "Network Error", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }.execute();
//    }
//
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
//                        Toast.makeText(EditProfileFragment2.this, message, Toast.LENGTH_SHORT).show();
//                        getUserData(json);
//                    } else {
//                        Toast.makeText(EditProfileFragment2.this, message, Toast.LENGTH_SHORT).show();
////                        sendotp.setVisibility(View.VISIBLE);
//                        btn_save.setVisibility(View.VISIBLE);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
////                    sendotp.setVisibility(View.VISIBLE);
//                    btn_save.setVisibility(View.VISIBLE);
//
//                    Toast.makeText(EditProfileFragment2.this, "Network Error", Toast.LENGTH_SHORT).show();
//                }
//            } else {
////                sendotp.setVisibility(View.VISIBLE);
//                btn_save.setVisibility(View.VISIBLE);
//
//                Toast.makeText(EditProfileFragment2.this, "Network Error", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

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
//            startActivity(new Intent(EditProfileFragment2.this, ProfileFragment.class));
//        } catch (JSONException e) {
//            Log.e(TAG, e.toString());
//            Toast.makeText(this, "System Error", Toast.LENGTH_SHORT).show();
////            sendotp.setVisibility(View.VISIBLE);
//            btn_save.setVisibility(View.VISIBLE);
//
//        }
//
//    }


    //    ArrayList<LocationTableResponse.LocationTable> citys = new ArrayList<>();
//    ArrayList<LocationTableResponse.LocationTable> states = new ArrayList<>();

    //getDropdownData GET Method
//    ArrayList<GetDropdownDataReponsePrakruthiStateDistrict.State> states = new ArrayList<>();
    ArrayList<GetDefaultDataPrakruthiStates.States> statess = new ArrayList<>();

    //getDefaultData POST Method

    //------
//    ArrayList<GetDropdownDataReponsePrakruthiStateDistrict.District> districts = new ArrayList<>();

    ArrayList<GetDefaultDataPrakruthiDistrict.District> districts = new ArrayList<>();
    //    ArrayList<GetDefaultDataPrakruthiDistrict.District> districtNames = new ArrayList<>();
    //districtNames


//    ArrayList<LocationTableResponse.LocationTable> districts = new ArrayList<>();
//    ArrayList<LocationTableResponse.LocationTable> countrys = new ArrayList<>();

//    private void fetchCities() {
//        CommonUtils.showLoading(EditProfileFragment2.this, "Please Wait", false);
//        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);
//        JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("user_id", SharedPrefs.getInstance(EditProfileFragment2.this).getString(Constants.USER_ID));
//        jsonObject.addProperty("token", SharedPrefs.getInstance(EditProfileFragment2.this).getString(Constants.TOKEN));
//        jsonObject.addProperty("table_name", "city");
//
//        Call<LocationTableResponse> call = apiInterface.getDefaultDataLoc(jsonObject);
//        call.enqueue(new Callback<LocationTableResponse>() {
//            @Override
//            public void onResponse(Call<LocationTableResponse> call, Response<LocationTableResponse> response) {
//                LocationTableResponse locationTableResponse = response.body();
//                citys = new ArrayList<>();
//                if (locationTableResponse != null && locationTableResponse.getLocationTables() != null) {
//                    citys.addAll(locationTableResponse.getLocationTables());
//                }
//                ArrayList<String> stateNames = new ArrayList<>();
//                stateNames.add("Select");
//                int selPos = 0;
//                for (LocationTableResponse.LocationTable locationTable : citys) {
//                    stateNames.add(locationTable.getName());
//                    if (data.getCity() != null && data.getCity().equals(locationTable.getName()))
//                        selPos = citys.indexOf(locationTable) + 1;
//                }
//                binding.spinnerCity.setAdapter(new ArrayAdapter<>(EditProfileFragment2.this, R.layout.list_units, stateNames));
//                binding.spinnerCity.setSelection(selPos);
//                CommonUtils.hideLoading();
//            }
//
//            @Override
//            public void onFailure(Call<LocationTableResponse> call, Throwable t) {
//                t.printStackTrace();
//                CommonUtils.hideLoading();
//            }
//        });
//
//    }

    private void fetchStates() {
        CommonUtils.showLoading(requireContext(), "Please Wait", false);
        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("table_name", "states");
//        jsonObject.addProperty("user_id", SharedPrefs.getInstance(EditProfileFragment2.this).getString(Constants.USER_ID));
//        jsonObject.addProperty("token", SharedPrefs.getInstance(EditProfileFragment2.this).getString(Constants.TOKEN));

        Call<GetDefaultDataPrakruthiStates> call = apiInterface.getDropdownData(jsonObject);
//        Call<GetDropdownDataReponsePrakruthiStateDistrict> call = apiInterface.getDropdownData(jsonObject);
//        Call<GetDropdownDataReponsePrakruthiStateDistrict> call = apiInterface.getDropdownData();
        call.enqueue(new Callback<GetDefaultDataPrakruthiStates>() {
            @Override
            public void onResponse(Call<GetDefaultDataPrakruthiStates> call, Response<GetDefaultDataPrakruthiStates> response) {

                GetDefaultDataPrakruthiStates getDropdownDataReponsePrakruthiMs = response.body();

                statess = new ArrayList<>();

//                GetDefaultDataPrakruthiStates getDropdownDataReponsePrakruthiMs = response.body();
//                if (getDropdownDataReponsePrakruthiMs != null && getDropdownDataReponsePrakruthiMs.getStates() != null) {
                if (getDropdownDataReponsePrakruthiMs != null && getDropdownDataReponsePrakruthiMs.getData() != null) {

//                    states.addAll(locationTableResponse.getLocationTables());
//                    states.addAll(getDropdownDataReponsePrakruthiMs.getState());
                    statess.addAll(getDropdownDataReponsePrakruthiMs.getData());
//                    if (jsonObj != null) {
//                    if (jsonObject != null) {
//
//                        try {
////                            JSONArray states = jsonObj.getJSONArray("state");
//                            JSONArray states = jsonObject.getJSONArray("state");
//                            ArrayList<String> stateNames = new ArrayList<>();
//                            for (int i = 0; i < states.length(); i++) {
//                                JSONObject state = states.getJSONObject(i);
//                                stateNames.add(state.getString("name"));
//                            }
//
//                            spinner_state.setItems(stateNames);
//
//
////                            JSONArray districts = jsonObj.getJSONArray("district");
//                            JSONArray districts = jsonObject.getJSONArray("district");
//                            districtNames.clear();
//                            for (int i = 0; i < districts.length(); i++) {
//                                JSONObject districtt = districts.getJSONObject(i);
//                                if (districtt.getInt("state_id") == stateId) {
//
//                                    districtNames.add(districtt.getString("name"));
//                                    spinner_district.setItems(districtNames);
//                                }
//                            }
//
//
//                        }catch (JSONException e) {
//                            Toast.makeText(requireContext(), "Network Error", Toast.LENGTH_SHORT).show();
//                        }
//                }

                    if (jsonObject != null) {

                        //                            JSONArray states = jsonObj.getJSONArray("state");
//                            JSONArray states = jsonObject.getJSONArray("state");
                        JsonArray states = jsonObject.get("state").getAsJsonArray();
                        ArrayList<String> stateNames = new ArrayList<>();
//                            for (int i = 0; i < states.length(); i++) {
//                                JSONObject state = states.getJSONObject(i);
//                                stateNames.add(state.getString("name"));
//                            }

                        for (int i = 0; i < states.size(); i++) {
                            JsonObject state = states.get(i).getAsJsonObject();
                            stateNames.add(state.get("name").getAsString());
                        }

                        spinner_state.setItems(stateNames);


//                            JSONArray districts = jsonObj.getJSONArray("district");
//                            JSONArray districts = jsonObject.getJSONArray("district");
                        JsonArray districts = jsonObject.get("district").getAsJsonArray();
                        districtNames.clear();

                        for (int i = 0; i < districts.size(); i++) {
                            JsonObject districtt = districts.get(i).getAsJsonObject();
//                                if (districtt.getInt("state_id") == stateId) {
                            if (districtt.get("state_id").getAsInt() == stateId) {

//                                    districtNames.add(districtt.getString("name"));
                                districtNames.add(districtt.get("name").getAsString());

                                spinner_district.setItems(districtNames);
                            }
                        }

                        //----------------------

//                            for (int i = 0; i < districts.size(); i++) {
//                                JSONObject districtt = districts.get(i).getAsJsonObject();
////                                if (districtt.getInt("state_id") == stateId) {
//                                if (districtt.get("state_id").getAsInt() == stateId) {
//
////                                    districtNames.add(districtt.getString("name"));
//                                    districtNames.add(districtt.get("name").getAsString());
//
//                                    spinner_district.setItems(districtNames);
//                                }
//                            }


                        //------------------
//                            for (int i = 0; i < districts.length(); i++) {
//                                JSONObject districtt = districts.getJSONObject(i);
//                                if (districtt.getInt("state_id") == stateId) {
//
//                                    districtNames.add(districtt.getString("name"));
//                                    spinner_district.setItems(districtNames);
//                                }
//                            }


                    }


                    else {
                        Toast.makeText(requireContext(), "Network Error", Toast.LENGTH_SHORT).show();
                    }

                }
//            }
//            .execute();
                ArrayList<String> stateNames = new ArrayList<>();
                stateNames.add("Select");
//                stateNames.add("Australia");
//                stateNames.add("Telangana");

                int selPos = 0;

                for (GetDefaultDataPrakruthiStates.States locationTable : statess) {
                    stateNames.add(locationTable.getName());
//                    if (data.getState().equals(locationTable.getName()))
                    if (data.getState() != null && data.getState().equals(locationTable.getName()))

                        selPos = statess.indexOf(locationTable) + 1;
                }

//                binding.spinnerState.setAdapter(new ArrayAdapter<>(requireContext(), R.layout.list_units, stateNames));

                //Retrofit:--Gasaver
//                spinner_state.setAdapter(new ArrayAdapter<>(requireContext(), R.layout.list_units, stateNames));

                //Retrofit:--Gasaver
//                binding.spinnerState.setSelection(selPos);
//                spinner_state.setSelection(selPos);

//                int selPos = 0;
//                for (int i = 0; i < statess.size(); i++) {
//                    GetDefaultDataPrakruthiStates.States locationTable = statess.get(i);
//                    stateNames.add(locationTable.getName());
////                    if (data.getState().equals(locationTable.getName())) {
//                    if (data.getState().equals(locationTable.getName())) {
//
//                        selPos = i + 1;
//                        break; // Exit the loop if a match is found
//                    }
//                }

//                CommonUtils.hideLoading();


                //----------------------





                //------------


                spinner_state.setOnSpinnerItemSelectedListener((OnSpinnerItemSelectedListener<String>) (oldIndex, oldItem, newIndex, newItem) -> {
                    stateId = newIndex + 1;

//            getDropDownData();
                    fetchStates();

                    spinner_state.setError(null);
//                    spinner_state.setError(selPos);
//
                });

            }

            @Override
            public void onFailure(Call<GetDefaultDataPrakruthiStates> call, Throwable t) {
                t.printStackTrace();
                CommonUtils.hideLoading();
            }
        });

    }

    //    private void fetchCountry() {
    private void fetchDistrict() {
        CommonUtils.showLoading(requireContext(), "Please Wait", false);
        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);
        JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("user_id", SharedPrefs.getInstance(EditProfileFragment2.this).getString(Constants.USER_ID));
//        jsonObject.addProperty("token", SharedPrefs.getInstance(EditProfileFragment2.this).getString(Constants.TOKEN));
//        jsonObject.addProperty("table_name", "country");
        jsonObject.addProperty("id", SharedPrefs.getInstance(requireContext()).getString(Constants.ID));
        jsonObject.addProperty("reference_id", "state_id");
        jsonObject.addProperty("table_name", "district");


//        Call<LocationTableResponse> call = apiInterface.getDefaultDataLoc(jsonObject);
//        Call<GetDropdownDataReponsePrakruthiStateDistrict> call = apiInterface.getDropdownData(jsonObject);
        Call<GetDefaultDataPrakruthiDistrict> call = apiInterface.getDropdownData1(jsonObject);

        call.enqueue(new Callback<GetDefaultDataPrakruthiDistrict>() {
            @Override
            public void onResponse(Call<GetDefaultDataPrakruthiDistrict> call, Response<GetDefaultDataPrakruthiDistrict> response) {
                districts = new ArrayList<>();
                GetDefaultDataPrakruthiDistrict getDropdownDataReponsePrakruthiMs = response.body();
//                if (getDropdownDataReponsePrakruthiMs != null && getDropdownDataReponsePrakruthiMs.getDistrict() != null) {
                if (getDropdownDataReponsePrakruthiMs != null && getDropdownDataReponsePrakruthiMs.getData() != null) {

                    districts.addAll(getDropdownDataReponsePrakruthiMs.getData());

                    if (jsonObject != null) {

                        //                            JSONArray states = jsonObj.getJSONArray("state");
//                            JSONArray states = jsonObject.getJSONArray("state");
                        JsonArray states = jsonObject.get("state").getAsJsonArray();
                        ArrayList<String> stateNames = new ArrayList<>();
//                            for (int i = 0; i < states.length(); i++) {
//                                JSONObject state = states.getJSONObject(i);
//                                stateNames.add(state.getString("name"));
//                            }

                        for (int i = 0; i < states.size(); i++) {
                            JsonObject state = states.get(i).getAsJsonObject();
                            stateNames.add(state.get("name").getAsString());
                        }

                        spinner_state.setItems(stateNames);


//                            JSONArray districts = jsonObj.getJSONArray("district");
//                            JSONArray districts = jsonObject.getJSONArray("district");
                        JsonArray districts = jsonObject.get("district").getAsJsonArray();
                        districtNames.clear();

                        for (int i = 0; i < districts.size(); i++) {
                            JsonObject districtt = districts.get(i).getAsJsonObject();
//                                if (districtt.getInt("state_id") == stateId) {
                            if (districtt.get("state_id").getAsInt() == stateId) {

//                                    districtNames.add(districtt.getString("name"));
                                districtNames.add(districtt.get("name").getAsString());

                                spinner_district.setItems(districtNames);
                            }
                        }

                        //----------------------

//                            for (int i = 0; i < districts.size(); i++) {
//                                JSONObject districtt = districts.get(i).getAsJsonObject();
////                                if (districtt.getInt("state_id") == stateId) {
//                                if (districtt.get("state_id").getAsInt() == stateId) {
//
////                                    districtNames.add(districtt.getString("name"));
//                                    districtNames.add(districtt.get("name").getAsString());
//
//                                    spinner_district.setItems(districtNames);
//                                }
//                            }


                        //------------------
//                            for (int i = 0; i < districts.length(); i++) {
//                                JSONObject districtt = districts.getJSONObject(i);
//                                if (districtt.getInt("state_id") == stateId) {
//
//                                    districtNames.add(districtt.getString("name"));
//                                    spinner_district.setItems(districtNames);
//                                }
//                            }


                    }

                }

                ArrayList<String> districtNames = new ArrayList<>();
//                stateNames.add("Select");
//                stateNames.add("Australia");
                districtNames.add("Medhak");


                int selPos = 0;
                for (GetDefaultDataPrakruthiDistrict.District locationTable : districts) {
                    districtNames.add(locationTable.getName());
                    if (data.getCountry() != null && data.getCountry().equals(locationTable.getName()))
                        selPos = districts.indexOf(locationTable) + 1;
                }
//                binding.spinnerDistrict.setAdapter(new ArrayAdapter<>(requireContext(), R.layout.list_units, districtNames));
//--------
//                spinner_district.setAdapter(new ArrayAdapter<>(requireContext(), R.layout.list_units, districtNames));

//                binding.spinnerDistrict.setSelection(selPos);
                //---------
//                spinner_district.setSelection(selPos);


                spinner_district.setOnClickListener(v -> {
                    if (stateId == 0) {
                        spinner_state.setError("Select State");
                    } else spinner_district.showOrDismiss();
                });


                CommonUtils.hideLoading();
            }

            @Override
            public void onFailure(Call<GetDefaultDataPrakruthiDistrict> call, Throwable t) {
                t.printStackTrace();
                CommonUtils.hideLoading();
            }
        });

    }

}