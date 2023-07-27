package com.prakruthi.ui.ui.profile;

import static android.content.ContentValues.TAG;
import static com.prakruthi.ui.Variables.city;
import static com.prakruthi.ui.Variables.district;
import static com.prakruthi.ui.Variables.email;
import static com.prakruthi.ui.Variables.name;
import static com.prakruthi.ui.Variables.state;
import static com.prakruthi.ui.Variables.token;
import static com.prakruthi.ui.Variables.userCode;
import static com.prakruthi.ui.Variables.userId;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.prakruthi.R;
import com.prakruthi.databinding.FragmentEditProfile2Binding;
import com.prakruthi.databinding.FragmentProfileBinding;
import com.prakruthi.ui.APIs.FeedBackApi;
import com.prakruthi.ui.APIs.GetRecentViewProductsAPI;
import com.prakruthi.ui.APIs.GetUserDataApi;
import com.prakruthi.ui.APIs.UserDetailsUpdateApi;
import com.prakruthi.ui.HomeActivity;
import com.prakruthi.ui.Login;
import com.prakruthi.ui.SplashScreen;
import com.prakruthi.ui.Variables;
import com.prakruthi.ui.misc.Loading;
import com.prakruthi.ui.network.APIClient;
import com.prakruthi.ui.network.ApiInterface;
import com.prakruthi.ui.ui.profile.myaddress.MyAddresses;
import com.prakruthi.ui.ui.profile.myorders.MyOrdersActivity;
import com.prakruthi.ui.ui.profile.mypayments.MyPaymentsActivity;
import com.prakruthi.ui.ui.profile.order_qty.OrderQtyActivity;
import com.prakruthi.ui.ui.profile.recentProducts.RecentProductAdaptor;
import com.prakruthi.ui.ui.profile.recentProducts.RecentProductModel;
import com.prakruthi.ui.utils.CommonUtils;
import com.prakruthi.ui.utils.Constants;
import com.prakruthi.ui.utils.SharedPrefs;
import com.saadahmedsoft.popupdialog.PopupDialog;
import com.saadahmedsoft.popupdialog.Styles;
import com.saadahmedsoft.popupdialog.listener.OnDialogButtonClickListener;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//public class ProfileFragment extends Fragment implements FeedBackApi.OnFeedbackItemAPiHit , GetRecentViewProductsAPI.OnGetRecentViewProductsAPIHit, UserDetailsUpdateApi.OnUserDetailsUpdateApiGivesFetchedListner, View.OnClickListener {
//public class ProfileFragment extends Fragment implements FeedBackApi.OnFeedbackItemAPiHit, GetRecentViewProductsAPI.OnGetRecentViewProductsAPIHit, GetUserDataApi.OnGetUserDataApiHitFetchedListner, UserDetailsUpdateApi.OnUserDetailsUpdateApiGivesFetchedListner {

//TProperty
//public class ProfileFragment extends Fragment implements FeedBackApi.OnFeedbackItemAPiHit, GetRecentViewProductsAPI.OnGetRecentViewProductsAPIHit {
public class ProfileFragment extends Fragment implements FeedBackApi.OnFeedbackItemAPiHit, GetRecentViewProductsAPI.OnGetRecentViewProductsAPIHit, GetUserDataApi.OnGetUserDataApiHitFetchedListner, UserDetailsUpdateApi.OnUserDetailsUpdateApiGivesFetchedListner, View.OnClickListener {
//Gasaver
//   public class ProfileFragment extends AppCompatActivity implements FeedBackApi.OnFeedbackItemAPiHit, GetRecentViewProductsAPI.OnGetRecentViewProductsAPIHit, View.OnClickListener {

//    private ActivityProfileBinding binding;

    //    private FragmentProfileBinding binding;
    public SharedPreferences sharedPreferences;

    AppCompatTextView tvRecentProducts, tvViewAll;

    AppCompatButton btn_add_new_address, login, Logout;

    ImageView iv_edit;

    Context context;

    LinearLayout ll_site_visit_req_LastUpdated4;

    public ShimmerRecyclerView ProfileHomeProductsRecycler;

    TextView tvProfileName, tvEmail, tvPhone, tvRole,
            tvMyOrders, tvMyAddress, tvMyWishlist, tvPayments,
            tvFeedback, tvSupport, tvAboutUs, tvTermsConditions, tvPrivacyPolicy,

    tvRRP, tvSecurity;

    private ProfileGetUserDataResponse responseProfile;
    //    private ProfileGetUserDataResponse.ProfileGetUserDataModel responseProfile;
    //    private ProfileGetUserDataResponseRetrofit.ProfileGetUserDataModelRetrofit responseProfile1;
    private ProfileGetUserDataResponseRetrofit responseProfile1;

    //    public EditProfileFragment editProfileFragment;
//    public EditProfileActivity editProfileActivity;
//    public EditProfileActivity2 editProfileActivity2;
    public EditProfileFragment2 editProfileActivity2;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.fragment_profile);
//        SetTextViews();
//        FeedBackDialog();
//        GetUserDataApi();
//        UserDetailsUpdateApi();
//        return binding.getRoot();

//        SetTextViews();

//        SetClickListeners();

//        UserDetailsUpdateApi();

        //getUserData Api Retrofit
        fetchProfileDetails();


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //TProperty
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Inflate the layout for this fragment
//        binding = FragmentProfileBinding.inflate(inflater, container, false);
//        binding = FragmentProfileBinding.inflate(getLayoutInflater());

//        ivEdit.setOnClickListener(this);

//        iv_edit = view.findViewById(R.id.iv_edit);
//        iv_edit.setOnClickListener(this);


//        iv_edit.setOnClickListener(this);


//        SetTextViews();
//        SetClickListeners();
//        UserDetailsUpdateApi();

//        init(view);
//        fetchProfileDetails();

//        TextView tvProfileName = binding.tvProfileName;
//        TextView tvEmail = binding.tvEmail;
//        TextView tvPhone = binding.tvPhone;
//        TextView tvRole = binding.tvRole;

//        return binding.getRoot();

        iv_edit = view.findViewById(R.id.iv_edit);
        iv_edit.setOnClickListener(this);


//        binding.ivEdit.setOnClickListener(this);

        tvProfileName = view.findViewById(R.id.tv_profile_name);
        tvEmail = view.findViewById(R.id.tv_email);
        tvPhone = view.findViewById(R.id.tv_phone);
        tvRole = view.findViewById(R.id.tv_role);

        Logout = view.findViewById(R.id.Logout);
        Logout.setOnClickListener(this);


        tvMyOrders = view.findViewById(R.id.tv_my_orders);
        tvMyOrders.setOnClickListener(this);

        tvMyAddress = view.findViewById(R.id.tv_my_address);
        tvMyAddress.setOnClickListener(this);

        tvMyWishlist = view.findViewById(R.id.tv_my_wishlist);
        tvMyWishlist.setOnClickListener(this);

        tvPayments = view.findViewById(R.id.tv_payments);
        tvPayments.setOnClickListener(this);

        tvFeedback = view.findViewById(R.id.tv_feedback);
        tvFeedback.setOnClickListener(this);

        tvSupport = view.findViewById(R.id.tv_support);
        tvSupport.setOnClickListener(this);

        tvAboutUs = view.findViewById(R.id.tv_About_us);
        tvAboutUs.setOnClickListener(this);

        tvPrivacyPolicy = view.findViewById(R.id.tv_privacy_policy);
        tvPrivacyPolicy.setOnClickListener(this);

        tvTermsConditions = view.findViewById(R.id.tv_Terms_Conditions);
        tvTermsConditions.setOnClickListener(this);

        tvRRP = view.findViewById(R.id.tv_returnRefundPolicy);
        tvRRP.setOnClickListener(this);

        tvSecurity = view.findViewById(R.id.tv_security);
        tvSecurity.setOnClickListener(this);


        ProfileHomeProductsRecycler = view.findViewById(R.id.ProfileHomeProductsRecycler);
        ProfileHomeProductsRecycler.showShimmerAdapter();

//        ProfileHomeProductsRecycler.setOnClickListener(this);

        fetchProfileDetails();


//--------------------------------------
//        SetTextViews();
//        SetClickListeners();
//---------------------------------------

//        binding.ivEdit.setOnClickListener(v -> {
//            startActivity(new Intent(requireContext(), EditProfileFragment.class));
//        });

//        binding.ivEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EditProfileFragment editProfileFragment = new EditProfileFragment();
//                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.cv_station, editProfileFragment);
////                transaction.replace(R.id.layout_1, editProfileFragment);
//                transaction.addToBackStack(null);  // Optional: Add the transaction to the back stack
//                transaction.commit();
////                startActivity(new Intent(requireContext(), EditProfileFragment.class));
//            }
//        });

//        binding.ivEdit.setOnClickListener(v -> startActivity(new Intent(requireContext(), EditProfileFragment.class)));


//       Same Activity or Fragment will show Profile + Edit Profile it will be use:--------------
//        --------------------------------------------------------------------------------------------

//        binding.ivEdit.setOnClickListener(v -> {
//
//            SetTextViews();
//
//            // Create an AlertDialog Builder
//            androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(requireContext());
//            builder.setTitle("Edit Profile");
//
//            // Set the custom layout for the dialog
//            View dialogView = getLayoutInflater().inflate(R.layout.dialog_edit_profile, null);
//            builder.setView(dialogView);
//
//            // Find the EditText fields and checkbox in the dialog layout
//            EditText nameEditText = dialogView.findViewById(R.id.edittext_name);
//            EditText emailEditText = dialogView.findViewById(R.id.edittext_email);
//            EditText cityEditText = dialogView.findViewById(R.id.edittext_city);
//            EditText stateEditText = dialogView.findViewById(R.id.edittext_state);
//            EditText districtEditText = dialogView.findViewById(R.id.edittext_district);
//
//
//            // Set the positive and negative buttons
//            builder.setPositiveButton("Save", null); // Set null initially
//
//            builder.setNegativeButton("Cancel", (dialog, which) -> {
//                // Handle the cancel button click
//                // Dismiss the dialog
//                dialog.dismiss();
//            });
//
//            // Create and show the dialog
//            androidx.appcompat.app.AlertDialog dialog = builder.create();
//
//            // Override the onClickListener for the positive button
//            dialog.setOnShowListener(dialogInterface -> {
//                Button saveButton = dialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE);
//
//                saveButton.setOnClickListener(view -> {
//                    // Handle the save button click
//                    String name = nameEditText.getText().toString();
//                    String email = emailEditText.getText().toString();
//                    String city = cityEditText.getText().toString();
//                    String state = stateEditText.getText().toString();
//                    String district = districtEditText.getText().toString();
//
//
//                    // Check if any of the fields are empty
//                    boolean hasError = false;
//                    if (name.isEmpty()) {
//                        nameEditText.setError("Name is required");
//                        hasError = true;
//                    }
//                    if (email.isEmpty()) {
//                        emailEditText.setError("email is required");
//                        hasError = true;
//                    }
//                    if (city.isEmpty()) {
//                        cityEditText.setError("city is required");
//                        hasError = true;
//                    }
//                    if (state.isEmpty()) {
//                        stateEditText.setError("State is required");
//                        hasError = true;
//                    }
//                    if (district.isEmpty()) {
//                        districtEditText.setError("District is required");
//                        hasError = true;
//                    }
//
//
//                    if (!hasError) {
//                        // All fields are properly filled, perform the save operation
////                        SaveDeliveryAddressDetails saveDeliveryAddressDetails = new SaveDeliveryAddressDetails(this, name, city, state, country, address, postalCode, boolToInt(isDefault));
//                        UserDetailsUpdateApi userDetailsUpdateApi = new UserDetailsUpdateApi(this, name, email, city, state, district);
//                        userDetailsUpdateApi.HitUserDetailsUpdateApi();
//                        Loading.show(requireContext());
//                        // Dismiss the dialog
//                        dialog.dismiss();
//                    }
//                });
//            });
//
//            dialog.show();
//        });

//        SetClickListeners();

//        SetTextViews();

//        GetUserDataApi getUserDataApi = new GetUserDataApi(this);
////        getUserDataApi.execute();
//        getUserDataApi.HitGetUserDataApi();

//        GetUserDataApi getUserDataApi = new GetUserDataApi((GetUserDataApi.OnGetUserDataApiHitFetchedListner) this);
////        getUserDataApi.execute();
//        getUserDataApi.HitGetUserDataApi();
//
//        UserDetailsUpdateApi getDeliveryAddressDetails = new UserDetailsUpdateApi((UserDetailsUpdateApi.OnUserDetailsUpdateApiGivesFetchedListner) this, name, email, city, state, district, userId, token);
////        getDeliveryAddressDetails.execute();
//        getDeliveryAddressDetails.HitUserDetailsUpdateApi();

        sharedPreferences = requireActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);

//        SetTextViews();
//        SetClickListeners();

//        binding.Logout.setOnClickListener(v -> {
        Logout.setOnClickListener(v -> {

            Variables.clear();
            // Get SharedPreferences.Editor object
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("rememberMe", false);
            // Apply changes
            editor.apply();
            startActivity(new Intent(requireContext(), Login.class));
            requireActivity().finish();
        });

        iv_edit.setOnClickListener(v -> {
//        iv_edit.setOnClickListener(v -> {
//            startActivity(new Intent(requireContext(), EditProfileFragment2.class));
//            if (responseProfile1.getData() != null) {
//            if (responseProfile != null) {
            if (responseProfile1 != null) {


//                ProfileGetUserDataResponse.ProfileGetUserDataModel data = responseProfile.getData();
                ProfileGetUserDataResponseRetrofit.ProfileGetUserDataModelRetrofit data = responseProfile1.getData();


                //                    Intent intent = new Intent(requireContext(), EditProfileActivity2.class);
                //                    Intent intent = new Intent(requireContext(), EditProfileFragment2.class);

                if (data != null) {

                    editProfileActivity2 = new EditProfileFragment2();

                    //                    intent.putExtra("data", new Gson().toJson(editProfileActivity2));

                    Bundle bundle = new Bundle();

                    bundle.putString("data", new Gson().toJson(responseProfile1.getData()));
                    editProfileActivity2.setArguments(bundle);

                    editProfileActivity2.setDismissListener(new EditProfileFragment2.DismissListener() {
                        //EditProfileActivity2
                        @Override
                        public void onDismiss() {
                            fetchProfileDetails();
                        }
                    });

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.iv_edit, editProfileActivity2, "EditProfileFragment2");
                    fragmentTransaction.addToBackStack(null); // Add this line if you want the fragment to be added to the back stack
                    fragmentTransaction.commit();

//                editProfileActivity2.show(getParentFragmentManager(), "");

                    new ProfileFragment().ApiTaskPro();

                } else {
                    // Handle the case when 'data' is null
                    Log.e("TAG", "Profile data is null.");
                }

            }

            //
            //
            //        });


        });


        //TProperty
        return view;

//        return binding.getRoot();
    }

    @SuppressLint("StaticFieldLeak")
    public void ApiTaskPro() {
        Loading.show(requireContext());
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                //Creating array for parameters
                String[] field = new String[2];
                field[0] = "user_id";
                field[1] = "token";
                //Creating array for data
                String[] data = new String[2];
//                data[0] = usernameString;
//                data[1] = passwordString;
//                data[0] = params[0];
//                data[1] =  params[1];
//                data[0] = userId;
                data[0] = String.valueOf(Variables.id);
                data[1] = token;
                PutData putData = new PutData(Variables.BaseUrl + "getUserData", "POST", field, data);
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
//                            username.setError("Invalid");
//                            password.setError("Invalid");
//                            ObjectAnimator.ofFloat(username, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
//                            username.requestFocus();
//                            ObjectAnimator.ofFloat(password, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
//                            password.requestFocus();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        login.setVisibility(View.VISIBLE);
                        Toast.makeText(requireContext(), "Network Error", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    login.setVisibility(View.VISIBLE);
                    Toast.makeText(requireContext(), "Network Error", Toast.LENGTH_SHORT).show();
                }
                login.setVisibility(View.VISIBLE);
                Loading.hide();
            }
        }.execute();

    }

    private FragmentManager getSupportFragmentManager() {
//        return null;
        return null;

    }


    public void SetTextViews() {

//        binding.txtId.setText("");
//        binding.txtId.append(String.valueOf(Variables.name));

//        binding.tvProfileName.setText("");
        tvProfileName.setText("");

//        binding.tvProfileName.append(String.valueOf(name));
        tvProfileName.append(String.valueOf(name));

//        binding.tvEmail.setText("");
        tvEmail.setText("");

//        binding.tvEmail.append(String.valueOf(Variables.email));
        tvEmail.append(String.valueOf(Variables.email));


//        binding.tvPhone.setText("");
        tvPhone.setText("");

//        binding.tvPhone.append(String.valueOf(Variables.phoneNumber));
//        binding.tvPhone.append(String.valueOf(Variables.mobile));
        tvPhone.append(String.valueOf(Variables.mobile));


//        binding.tvRole.setText("");
        tvRole.setText("");

//        binding.tvRole.append(String.valueOf(Variables.userCode));
        tvRole.append(String.valueOf(Variables.userCode));


//        binding.ProfileHomeProductsRecycler.showShimmerAdapter();
        ProfileHomeProductsRecycler.showShimmerAdapter();

//        binding.tvTermsConditions.setSelected(true);
        tvTermsConditions.setSelected(true);


        GetRecentViewProductsAPI getRecentViewProductsAPI = new GetRecentViewProductsAPI(this);
        getRecentViewProductsAPI.HitRecentApi();

//        GetUserDataApi getUserDataApi = new GetUserDataApi(this);
//        getUserDataApi.HitGetUserDataApi();
//
//        UserDetailsUpdateApi userDetailsUpdateApi = new UserDetailsUpdateApi(this, Variables.userId, Variables.token, name, email, city, state, district);
//        userDetailsUpdateApi.HitUserDetailsUpdateApi();

        GetUserDataApi getUserDataApi = new GetUserDataApi((GetUserDataApi.OnGetUserDataApiHitFetchedListner) this);
        getUserDataApi.HitGetUserDataApi();

//        UserDetailsUpdateApi userDetailsUpdateApi = new UserDetailsUpdateApi((UserDetailsUpdateApi.OnUserDetailsUpdateApiGivesFetchedListner) this, Variables.userId, Variables.token, name, email, city, state, district);
//        userDetailsUpdateApi.HitUserDetailsUpdateApi();

//        userDetailsUpdateApi.execute();

    }


    //Retrofit:-----
    private void fetchProfileDetails() {

        CommonUtils.showLoading(requireContext(), "Please Wait", false);

        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);


        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("user_id", SharedPrefs.getInstance(requireContext()).getString(Constants.USER_ID))
                .addFormDataPart("token", SharedPrefs.getInstance(requireContext()).getString(Constants.TOKEN))
                .build();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("user_id", SharedPrefs.getInstance(requireContext()).getString(Constants.USER_ID));
        jsonObject.addProperty("token", SharedPrefs.getInstance(requireContext()).getString(Constants.TOKEN));

        Call<ProfileGetUserDataResponseRetrofit> call = apiInterface.fetchProfileDetails(jsonObject);
//        Call<ProfileGetUserDataResponseRetrofit> call = apiInterface.fetchProfileDetails(requestBody);


        call.enqueue(new Callback<ProfileGetUserDataResponseRetrofit>() {
            @Override
            public void onResponse(Call<ProfileGetUserDataResponseRetrofit> call, Response<ProfileGetUserDataResponseRetrofit> response) {
                CommonUtils.hideLoading();
                try {
                    responseProfile1 = response.body();
                    Log.e("TAG", String.valueOf(responseProfile1.getData()));

                    if (responseProfile1 != null && responseProfile1.getData() != null) {

                        // binding.tvRole.setText(responseProfile.getData().getUserCode());
                        // binding.tvRole.setText(String.valueOf(responseProfile.getData().getUserCode()));

                        if (responseProfile1.getData().getName() != null) {

//                        binding.tvProfileName.setText(String.valueOf(responseProfile1.getData().getName()));
//                        tvProfileName.setText(String.valueOf(responseProfile1.getData().getName()));
                            tvProfileName.setText(responseProfile1.getData().getName());
                            tvProfileName.setText("");


                        }
//                    else binding.tvProfileName.setText("");
                        else tvProfileName.setText("");


                        if (responseProfile1.getData().getEmail() != null) {
//                        binding.tvEmail.setText(String.valueOf(responseProfile1.getData().getEmail()));
//                        tvEmail.setText(String.valueOf(responseProfile1.getData().getEmail()));
                            tvEmail.setText(responseProfile1.getData().getEmail());
                            tvEmail.setText("");


                        } else
//                        binding.tvEmail.setText("");
                            tvEmail.setText("");


                        if (responseProfile1.getData().getMobile() != null) {
//                        binding.tvPhone.setText(String.valueOf(responseProfile1.getData().getMobile()));
//                        tvPhone.setText(String.valueOf(responseProfile1.getData().getMobile()));
                            tvPhone.setText(responseProfile1.getData().getMobile());
                            tvPhone.setText("");


                        }
//                    else binding.tvPhone.setText("");
                        else tvPhone.setText("");


                        if (responseProfile1.getData().getUserCode() != null) {
//                        binding.tvRole.setText(String.valueOf(responseProfile1.getData().getUserCode()));
//                        tvRole.setText(String.valueOf(responseProfile1.getData().getUserCode()));
                            tvRole.setText(responseProfile1.getData().getUserCode());


                        } else
//                        binding.tvRole.setText("");
                            tvRole.setText("");


//                    if (responseProfile1.getRewardPoints() != null)
//                    {
//                        binding.txtRewardPoints.setText(String.valueOf(responseProfile1.getRewardPoints()));
//                    }else binding.txtRewardPoints.setText("");


//                    try {
//                        SharedPrefs.getInstance(requireContext()).saveBoolean(Constants.allow_email, responseProfile.getData().getAllow_mail().equalsIgnoreCase("Yes"));
//                        SharedPrefs.getInstance(requireContext()).saveBoolean(Constants.allow_sms, responseProfile.getData().getAllow_sms().equalsIgnoreCase("Yes"));
//                        SharedPrefs.getInstance(requireContext()).saveBoolean(Constants.allow_push, responseProfile.getData().getAllow_push().equalsIgnoreCase("Yes"));
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }


//                    Glide.with(ProfileFragment.this).load(response.body().getBase_path() + response.body().getData().getProfilePhoto())
//                            .error(R.drawable.profile_img).error(R.drawable.profile_img).into(binding.ivProfileImg);
                    }

                    //Retrofit:---
                    if (editProfileActivity2 != null && editProfileActivity2.isVisible()) {
                        editProfileActivity2.updateDetails(responseProfile1.getData());
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ProfileGetUserDataResponseRetrofit> call, Throwable t) {
                CommonUtils.hideLoading();
            }
        });

//        GetUserDataApi getUserDataApi = new GetUserDataApi((GetUserDataApi.OnGetUserDataApiHitFetchedListner) this);
//        getUserDataApi.HitGetUserDataApi();
//
//        UserDetailsUpdateApi userDetailsUpdateApi = new UserDetailsUpdateApi((UserDetailsUpdateApi.OnUserDetailsUpdateApiGivesFetchedListner) this, Variables.userId, Variables.token, name, email, city, state, district);
//        userDetailsUpdateApi.HitUserDetailsUpdateApi();

        GetRecentViewProductsAPI getRecentViewProductsAPI = new GetRecentViewProductsAPI(this);
        getRecentViewProductsAPI.HitRecentApi();

    }

    public void SetClickListeners() {

//        binding.tvMyOrders.setOnClickListener(v -> startActivity(new Intent(requireContext(), MyOrdersActivity.class)));
        tvMyOrders.setOnClickListener(v -> startActivity(new Intent(requireContext(), MyOrdersActivity.class)));

        //        binding.tvMyAddress.setOnClickListener(v -> {
        tvMyAddress.setOnClickListener(v -> {

            startActivity(new Intent(requireContext(), MyAddresses.class));
        });

        if (Variables.departmentId != 2) {
//            binding.tvMyWishlist.setText("quantity");
            tvMyWishlist.setText("quantity");

            Drawable top = getResources().getDrawable(R.drawable.baseline_warehouse_24);
//            binding.tvMyWishlist.setCompoundDrawables(null, top, null, null);
            tvMyWishlist.setCompoundDrawables(null, top, null, null);

//            binding.tvMyWishlist.setOnClickListener(v -> {
            tvMyWishlist.setOnClickListener(v -> {

                startActivity(new Intent(requireContext(), OrderQtyActivity.class));
            });
        } else {
//            binding.tvMyWishlist.setOnClickListener(v -> {
            tvMyWishlist.setOnClickListener(v -> {


                //Comment
                BottomNavigationView bottomNavigationView;
                bottomNavigationView = (BottomNavigationView) requireActivity().findViewById(R.id.nav_view);
                bottomNavigationView.setSelectedItemId(R.id.navigation_wishlist);

                startActivity(new Intent(requireContext(), OrderQtyActivity.class));
            });
        }


//        binding.tvPayments.setOnClickListener(v -> startActivity(new Intent(requireContext(), MyPaymentsActivity.class)));
        tvPayments.setOnClickListener(v -> startActivity(new Intent(requireContext(), MyPaymentsActivity.class)));


//        binding.tvFeedback.setOnClickListener(v -> {
        tvFeedback.setOnClickListener(v -> {

            FeedBackDialog();
        });

        tvPrivacyPolicy.setOnClickListener(v -> {

            startActivity(new Intent(requireContext(), PrivacyPolicyActivity.class));
            Intent i = new Intent(Intent.ACTION_VIEW);
//            i.setData(Uri.parse(Constants.PRIVACY_POLICY_URL));
            i.setData(Uri.parse(Constants.PRIVACY_POLICY_URL1));

            startActivity(i);
        });

        tvTermsConditions.setOnClickListener(v -> {

            startActivity(new Intent(requireContext(), TermNConditionWebViewActivity.class));
            Intent i = new Intent(Intent.ACTION_VIEW);
//            i.setData(Uri.parse(Constants.TERMS_COND_URL));
            i.setData(Uri.parse(Constants.TERMS_COND_URL1));
            startActivity(i);
        });

        tvAboutUs.setOnClickListener(v -> {

            startActivity(new Intent(requireContext(), AboutUsWebViewActivity.class));
            Intent i = new Intent(Intent.ACTION_VIEW);
//            i.setData(Uri.parse(Constants.TERMS_COND_URL));
            i.setData(Uri.parse(Constants.ABOUTUS));
            startActivity(i);
        });

        tvSupport.setOnClickListener(v -> {

            startActivity(new Intent(requireContext(), SupportActivity.class));
            Intent i = new Intent(Intent.ACTION_VIEW);
            startActivity(i);

        });

        tvRRP.setOnClickListener(v -> {

            startActivity(new Intent(requireContext(), ReturnRefundPolicyActivity.class));
            Intent i = new Intent(Intent.ACTION_VIEW);
//            i.setData(Uri.parse(Constants.TERMS_COND_URL));
            i.setData(Uri.parse(Constants.ABOUTUS));
            startActivity(i);
        });

        tvSecurity.setOnClickListener(v -> {

            startActivity(new Intent(requireContext(), SecurityActivity.class));
            Intent i = new Intent(Intent.ACTION_VIEW);
//            i.setData(Uri.parse(Constants.TERMS_COND_URL));
            i.setData(Uri.parse(Constants.ABOUTUS));
            startActivity(i);
        });


//        ll_site_visit_req_LastUpdated4.setOnClickListener(v -> {
//
//            SharedPrefs.getInstance(getActivity()).clearSharedPrefs();
//            Intent intent1 = new Intent(getActivity(), SplashScreen.class);
//            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent1);
//        });


//        binding.ivEdit.setOnClickListener(v -> startActivity(new Intent(requireContext(), EditProfileFragment.class)));
//        binding.ivEdit.setOnClickListener(v -> startActivity(new Intent(requireContext(), EditProfileActivity.class)));

//        binding.ivEdit.setOnClickListener(v -> startActivity(new Intent(requireContext(), EditProfileActivity2.class)));
//        iv_edit.setOnClickListener(v -> {
//            startActivity(new Intent(requireContext(), EditProfileFragment2.class));
//            if (responseProfile1.getData() != null) {
//
////                    Intent intent = new Intent(requireContext(), EditProfileActivity2.class);
////                    Intent intent = new Intent(requireContext(), EditProfileFragment2.class);
//
//                editProfileActivity2 = new EditProfileFragment2();
//
////                    intent.putExtra("data", new Gson().toJson(editProfileActivity2));
//
//                Bundle bundle = new Bundle();
//
//                bundle.putString("data", new Gson().toJson(responseProfile1.getData()));
//                editProfileActivity2.setArguments(bundle);
//
//                editProfileActivity2.setDismissListener(new EditProfileFragment2.DismissListener() {
//                    //EditProfileActivity2
//                    @Override
//                    public void onDismiss() {
//                        fetchProfileDetails();
//                    }
//                });
//                editProfileActivity2.show(getParentFragmentManager(), "");
//            }
////
////
////        });
//
//
//        });
    }

    //                if (responseProfile != null) {


//        binding.ivEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (responseProfile != null) {
//
////                    EditProfileFragment editProfileFragment = new EditProfileFragment();
////                    editProfileFragment = new EditProfileFragment();
////                    FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
//////                    transaction.replace(R.id.cv_station, editProfileFragment);
////                    transaction.replace(R.id.layout_1, editProfileFragment);
////                    transaction.addToBackStack(null);  // Optional: Add the transaction to the back stack
////                    transaction.commit();
//////                startActivity(new Intent(requireContext(), EditProfileFragment.class));
////
////                    Intent intent = new Intent(Intent.ACTION_VIEW);
////                    intent = new Intent(requireContext(), EditProfileFragment.class);
////
////                    Bundle bundle = new Bundle();
////                    startActivityForResult(intent,102);
//
//                    EditProfileActivity editProfileActivity = new EditProfileActivity();
//                    editProfileActivity = new EditProfileActivity();
//                    FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
////                    transaction.replace(R.id.cv_station, editProfileFragment);
////                    transaction.replace(R.id.layout_1, editProfileActivity);
//                    transaction.addToBackStack(null);  // Optional: Add the transaction to the back stack
//                    transaction.commit();
////                startActivity(new Intent(requireContext(), EditProfileFragment.class));
//
//                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                    intent = new Intent(requireContext(), EditProfileActivity.class);
//
//                    Bundle bundle = new Bundle();
//                    startActivityForResult(intent,102);
//                }
//            }
//        });

//    }


//    public void UserDetailsUpdateApi() {
//
//        GetUserDataApi getUserDataApi = new GetUserDataApi(this);
//        getUserDataApi.HitGetUserDataApi();
//
//        UserDetailsUpdateApi userDetaialsUpdateApi = new UserDetailsUpdateApi(this, Variables. userId, Variables.token, name, email, city, state, district);
//        userDetaialsUpdateApi.HitUserDetailsUpdateApi();
//////        userDetaialsUpdateApi.execute();
//
//        GetRecentViewProductsAPI getRecentViewProductsAPI = new GetRecentViewProductsAPI(this);
//        getRecentViewProductsAPI.HitRecentApi();
//    }

//    public void UserDetailsUpdateApi()
//    {
//        UserDetailsUpdateApi userDetaialsUpdateApi = new UserDetailsUpdateApi(this);
//        userDetaialsUpdateApi.execute();
//    }

    public void FeedBackDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Feedback");

        // Create the EditText
        final EditText editText = new EditText(requireContext());
        builder.setView(editText);

        // Add the submit button
        builder.setPositiveButton("Submit", null); // Set the click listener to null for now

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();

        // Get the button from the dialog's view
        Button submitButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        submitButton.setOnClickListener(view -> {
            // Handle the submit button click
            String feedback = editText.getText().toString();

            if (feedback.isEmpty()) {
                editText.setError("Feedback cannot be empty");
            } else {
                // Do something with the feedback
                FeedBackApi feedBackApi = new FeedBackApi(this, feedback);
                feedBackApi.FeedbackHitApi();
                dialog.dismiss(); // Dismiss the dialog after handling the click
            }
        });
    }


//
//    public void onStart() {
//        super.onStart();
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//    }
//
//
//    @Override
//    public void onPause() {
//        super.onPause();
//    }
//
//////    @Override
//////    public void onRestart() {
//////        super.onRestart();
//////    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
////        binding = null;
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
////        binding = null;
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
////        binding = null;
//    }

    @Override
    public void OnProfileItemFeedback(String description) {
        try {
            requireActivity().runOnUiThread(() -> {
                Toast.makeText(requireContext(), description, Toast.LENGTH_SHORT).show();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void OnProfileItemFeedbackAPiGivesError(String error) {
        try {
            requireActivity().runOnUiThread(() -> {
                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //------------------------
    @Override
    public void OnGetRecentViewProductsAPIGivesResult
    (ArrayList<RecentProductModel> recentProductModels) {
        try {
            requireActivity().runOnUiThread(() -> {
//                binding.ProfileHomeProductsRecycler.setLayoutManager(new GridLayoutManager(requireContext(), 2));
                ProfileHomeProductsRecycler.setLayoutManager(new GridLayoutManager(requireContext(), 2));

//                binding.ProfileHomeProductsRecycler.setAdapter(new RecentProductAdaptor(recentProductModels));
                ProfileHomeProductsRecycler.setAdapter(new RecentProductAdaptor(recentProductModels));

//                binding.ProfileHomeProductsRecycler.hideShimmerAdapter();
                ProfileHomeProductsRecycler.hideShimmerAdapter();

            });
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    @Override
    public void OnGetRecentViewProductsAPIGivesError(String error) {
        try {
            requireActivity().runOnUiThread(() -> {
                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show();
//                binding.ProfileHomeProductsRecycler.hideShimmerAdapter();
                ProfileHomeProductsRecycler.hideShimmerAdapter();

//                binding.tvRecentProducts.setVisibility(View.GONE);
                tvRecentProducts.setVisibility(View.GONE);

//                binding.tvViewAll.setVisibility(View.GONE);
                tvViewAll.setVisibility(View.GONE);

            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //-------------------------


//    @Override
//    public void onClick(View v) {
//
//    }


//    public void getUserData(JSONObject ResultJson)
//    {
//        try {
//            String token = ResultJson.getString("token");
//            JSONArray userDetailsArray = ResultJson.getJSONArray("user_details");
//            JSONObject userDetails = userDetailsArray.getJSONObject(0);
//            int id = userDetails.getInt("id");
//            int departmentId = userDetails.getInt("department_id");
//            String userCode = userDetails.optString("user_code", "");
//            String name = userDetails.optString("name", "");
//            String lastName = userDetails.optString("last_name", "");
//            String email = userDetails.optString("email", "");
//            String password = userDetails.optString("password", "");
//            String mobile = userDetails.optString("mobile", "");
//            String gender = userDetails.optString("gender", "");
//            String dob = userDetails.optString("dob", "");
//            String attachment = userDetails.optString("attachment", "");
//            String city = userDetails.optString("city", "");
//            String postCode = userDetails.optString("postCode", "");
//            String address = userDetails.optString("address", "");
//            String state = userDetails.optString("state", "");
//            String country = userDetails.optString("country", "");
//            String district = userDetails.optString("district", "");
//            String street = userDetails.optString("street", "");
//            String about = userDetails.optString("about", "");
//            String status = userDetails.optString("status", "");
//            String mobileVerified = userDetails.optString("mobile_verified", "");
//            String isVerified = userDetails.optString("is_verified", "");
//            String logDateCreated = userDetails.optString("log_date_created", "");
//            String createdBy = userDetails.optString("created_by", "");
//            String logDateModified = userDetails.optString("log_date_modified", "");
//            String modifiedBy = userDetails.optString("modified_by", "");
//            String fcmToken = userDetails.optString("fcm_token", "");
//            String deviceId = userDetails.optString("device_id", "");
//            String allowEmail = userDetails.optString("allow_email", "");
//            String allowSms = userDetails.optString("allow_sms", "");
//            String allowPush = userDetails.optString("allow_push", "");
//
//            // Store values in static variables
//            Variables.clear();
//            Variables.token = token;
//            Variables.id = id;
//            Variables.departmentId = departmentId;
//            Variables.userCode = userCode;
//            Variables.name = name;
//            Variables.lastName = lastName;
//            Variables.email = email;
//            Variables.password = password;
//            Variables.mobile = mobile;
//            Variables.gender = gender;
//            Variables.dob = dob;
//            Variables.attachment = attachment;
//            Variables.city = city;
//            Variables.postCode = postCode;
//            Variables.address = address;
//            Variables.state = state;
//            Variables.country = country;
//            Variables.district = district;
//            Variables.street = street;
//            Variables.about = about;
//            Variables.status = status;
//            Variables.mobileVerified = mobileVerified;
//            Variables.isVerified = isVerified;
//            Variables.logDateCreated = logDateCreated;
//            Variables.createdBy = createdBy;
//            Variables.logDateModified = logDateModified;
//            Variables.modifiedBy = modifiedBy;
//            Variables.fcmToken = fcmToken;
//            Variables.deviceId = deviceId;
//            Variables.allowEmail = allowEmail;
//            Variables.allowSms = allowSms;
//            Variables.allowPush = allowPush;
//
////            Log.e(TAG, Variables.id+Variables.token );
//
//            iv_edit.setVisibility(View.VISIBLE);
//            Loading.hide();
////            if (RememberMe.isChecked())
////                rememberMe();
//            startActivity(new Intent(requireContext(), HomeActivity.class));
////            finish();
//        }
//        catch (JSONException e) {
//            Log.e(TAG, e.toString() );
//            Toast.makeText(requireContext(), "Internal Error", Toast.LENGTH_SHORT).show();
//            iv_edit.setVisibility(View.VISIBLE);
////            LoginLayout.setVisibility(View.VISIBLE);
//        }
//
//    }

    public void getUserData(JSONObject ResultJson) {
        try {
            JSONArray resultArray = ResultJson.getJSONArray("result");
            ArrayList<ProfileGetUserDataResponse.ProfileGetUserDataModel> profileGetUserDataModels = new ArrayList<>();
            for (int i = 0; i < resultArray.length(); i++) {

//                String token = resultObject.getString("token");

                String status_code = ResultJson.getString("status_code");
                String privacyPolicy = ResultJson.getString("privacyPolicy");
                String termsAndConditions = ResultJson.getString("termsAndConditions");
                String returnRefundPolicy = ResultJson.getString("returnRefundPolicy");
                String security = ResultJson.getString("security");
                String aboutUs = ResultJson.getString("aboutUs");
                String message = ResultJson.getString("message");

//                JSONArray userDetailsArray = ResultJson.getJSONArray("result");
                JSONArray userDetailsArray = ResultJson.getJSONArray("data");

                JSONObject userDetails = userDetailsArray.getJSONObject(0);

                JSONObject resultObject = resultArray.getJSONObject(i);
                int id = resultObject.getInt("id");
                int departmentId = resultObject.getInt("department_id");
//                int userId = resultObject.getInt("user_id");
                String userId = String.valueOf(resultObject.getInt("user_id"));

//                String apiToken = resultObject.getString("api_token");
//                String user_code = resultObject.getString("user_code");

                String userCode = resultObject.optString("user_code", "");


                String name = resultObject.optString("name", "");
                String lastName = resultObject.optString("last_name", "");
                String email = resultObject.optString("email", "");
                String password = resultObject.optString("password", "");
                String mobile = resultObject.optString("mobile", "");
                String gender = resultObject.optString("gender", "");
                String dob = resultObject.optString("dob", "");
                String attachment = resultObject.optString("attachment", "");
                String city = resultObject.optString("city", "");
                String postCode = resultObject.optString("postCode", "");
                String address = resultObject.optString("address", "");
                String state = resultObject.optString("state", "");
                String country = resultObject.optString("country", "");
                String district = resultObject.optString("district", "");
                String street = resultObject.optString("street", "");
                String about = resultObject.optString("about", "");
                String status = resultObject.optString("status", "");
                String mobileVerified = resultObject.optString("mobile_verified", "");
                String isVerified = resultObject.optString("is_verified", "");
                String logDateCreated = resultObject.optString("log_date_created", "");
                String createdBy = resultObject.optString("created_by", "");
                String logDateModified = resultObject.optString("log_date_modified", "");
                String modifiedBy = resultObject.optString("modified_by", "");
                String fcmToken = resultObject.optString("fcm_token", "");
                String deviceId = resultObject.optString("device_id", "");
                String allowEmail = resultObject.optString("allow_email", "");
                String allowSms = resultObject.optString("allow_sms", "");
                String allowPush = resultObject.optString("allow_push", "");


                // Store values in static variables
                Variables.clear();

                Variables.status_code = status_code;
                Variables.privacyPolicy = privacyPolicy;
                Variables.termsAndConditions = termsAndConditions;
                Variables.returnRefundPolicy = returnRefundPolicy;
                Variables.security = security;
                Variables.aboutUs = aboutUs;
                Variables.message = message;

//                Variables.token = token;
                Variables.id = id;
                Variables.departmentId = departmentId;
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


            }
//            sendotp.setVisibility(View.VISIBLE);
            iv_edit.setVisibility(View.VISIBLE);
//            Variables.phoneNumber = phone_number.getText().toString();
            Variables.name = tvProfileName.getText().toString();
            Variables.email = tvEmail.getText().toString();
            Variables.mobile = tvPhone.getText().toString();
            Variables.userCode = tvRole.getText().toString();

//            Variables.city = et_city.getText().toString();
//            Variables.state = spinner_state.getText().toString();
//            Variables.district = spinner_district.getText().toString();

//            startActivity(new Intent(RegistrationForm.this, OTP_Verification.class));
            startActivity(new Intent(requireContext(), ProfileFragment.class));
        } catch (JSONException e) {
            Log.e(TAG, e.toString());
            Toast.makeText(requireContext(), "System Error", Toast.LENGTH_SHORT).show();
//            sendotp.setVisibility(View.VISIBLE);
            iv_edit.setVisibility(View.VISIBLE);

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_edit:

//                if (responseProfile != null) {
                if (responseProfile1.getData() != null) {

//                    Intent intent = new Intent(requireContext(), EditProfileActivity2.class);
//                    Intent intent = new Intent(requireContext(), EditProfileFragment2.class);

                    editProfileActivity2 = new EditProfileFragment2();

//                    intent.putExtra("data", new Gson().toJson(editProfileActivity2));

                    Bundle bundle = new Bundle();

                    bundle.putString("data", new Gson().toJson(responseProfile1.getData()));
                    editProfileActivity2.setArguments(bundle);

                    editProfileActivity2.setDismissListener(new EditProfileFragment2.DismissListener() {
                        //EditProfileActivity2
                        @Override
                        public void onDismiss() {
                            fetchProfileDetails();
                        }
                    });
//                    editProfileActivity2.show(getParentFragmentManager(), "");


//------------------------------------------------------------

//                    intent.putExtra("reward_points", (responseProfile.getRewardPoints()));
//                    intent.putExtra("bar_code", (responseProfile.getBarCode()));
//                    intent.putExtra("basepath", (responseProfile.getBase_path()));
//                    intent.putExtra("data", new Gson().toJson(responseProfile.getData()));

//                    Bundle bundle = new Bundle();
//                    editProfileFragment.setArguments(bundle);
//                    editProfileFragment.setDismissListener(new EditProfileFragment.DismissListener() {
//                        @Override
//                        public void onDismiss() {
//                            fetchProfileDetails();
//                        }
//                    });
//                    editProfileFragment.show(getParentFragmentManager(), "");


//                    startActivityForResult(intent,102);
                }
                break;


            case R.id.ll_site_visit_req_LastUpdated4:
                SharedPrefs.getInstance(getActivity()).clearSharedPrefs();
                Intent intent1 = new Intent(getActivity(), SplashScreen.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent1);
                break;

//        binding.tvMyOrders.setOnClickListener(v -> startActivity(new Intent(requireContext(), MyOrdersActivity.class)));
            case R.id.tv_my_orders:

//                tvMyOrders.setOnClickListener(v ->
                startActivity(new Intent(requireContext(), MyOrdersActivity.class));
                break;
//            );

            case R.id.tv_my_address:

//                tvMyAddress.setOnClickListener(v -> {
                startActivity(new Intent(requireContext(), MyAddresses.class));
                break;

//            });

            case R.id.tv_my_wishlist:
                if (Variables.departmentId != 2) {
//            binding.tvMyWishlist.setText("quantity");
                    tvMyWishlist.setText("quantity");

                    Drawable top = getResources().getDrawable(R.drawable.baseline_warehouse_24);
//            binding.tvMyWishlist.setCompoundDrawables(null, top, null, null);
                    tvMyWishlist.setCompoundDrawables(null, top, null, null);

//            binding.tvMyWishlist.setOnClickListener(v -> {
//                tvMyWishlist.setOnClickListener(v -> {

                    startActivity(new Intent(requireContext(), OrderQtyActivity.class));
//                });
                } else {
//            binding.tvMyWishlist.setOnClickListener(v -> {
//                tvMyWishlist.setOnClickListener(v -> {


                    //Comment
                    BottomNavigationView bottomNavigationView;
                    bottomNavigationView = (BottomNavigationView) requireActivity().findViewById(R.id.nav_view);
                    bottomNavigationView.setSelectedItemId(R.id.navigation_wishlist);

                    startActivity(new Intent(requireContext(), OrderQtyActivity.class));
//                });
                    break;
                }


//        binding.tvPayments.setOnClickListener(v -> startActivity(new Intent(requireContext(), MyPaymentsActivity.class)));
            case R.id.tv_payments:
//                tvPayments.setOnClickListener(v ->
                startActivity(new Intent(requireContext(), MyPaymentsActivity.class));
                break;
//            );


            //        binding.tvFeedback.setOnClickListener(v -> {
            case R.id.tv_feedback:
//                tvFeedback.setOnClickListener(v -> {
                FeedBackDialog();
                break;

//            });

            case R.id.tv_support:
//                tvFeedback.setOnClickListener(v -> {
                startActivity(new Intent(requireContext(), SupportActivity.class));
                Intent i = new Intent(Intent.ACTION_VIEW);

//                i.setData(Uri.parse(Constants.PRIVACY_POLICY_URL));
                break;

//            });

            case R.id.tv_privacy_policy:

                i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(Constants.PRIVACY_POLICY_URL));
                i.setData(Uri.parse(Constants.PRIVACY_POLICY_URL1));

                startActivity(i);
                break;

//            case R.id.layout_terms:
            case R.id.tv_Terms_Conditions:

                i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(Constants.TERMS_COND_URL));
                i.setData(Uri.parse(Constants.TERMS_COND_URL1));
                startActivity(i);
                break;


            case R.id.tv_About_us:

                i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(Constants.TERMS_COND_URL));
                i.setData(Uri.parse(Constants.ABOUTUS));
                startActivity(i);
                break;

            case R.id.tv_returnRefundPolicy:

                i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(Constants.TERMS_COND_URL));
                i.setData(Uri.parse(Constants.RETURN_REFUND_POLICY_URL1));
                startActivity(i);
                break;

            case R.id.tv_security:

                i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(Constants.TERMS_COND_URL));
                i.setData(Uri.parse(Constants.SECURITY));
                startActivity(i);
                break;


//            case R.id.tv_Terms_Conditions:
////            tvTermsConditions.setOnClickListener(v -> {
//
//                startActivity(new Intent(requireContext(), TermNConditionWebViewActivity.class));
//                 i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(Constants.TERMS_COND_URL));
//                startActivity(i);
//                break;
////            });
//
//            case R.id.tv_privacy_policy:
////            tvPrivacyPolicy.setOnClickListener(v -> {
//                startActivity(new Intent(requireContext(), PrivacyPolicyActivity.class));
//                 i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(Constants.TERMS_COND_URL));
//                startActivity(i);
//                break;
////            });
//
//            case R.id.ll_site_visit_req_LastUpdated4:
////                ll_site_visit_req_LastUpdated4.setOnClickListener(v -> {
//                SharedPrefs.getInstance(getActivity()).clearSharedPrefs();
//                 intent1 = new Intent(getActivity(), SplashScreen.class);
//                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intent1);
//                break;
////            });


        }
    }


    @Override
    public void OnUserDetailsUpdateApiGivesFetched(String message) {
        requireActivity().runOnUiThread(() -> {
            Loading.hide();

            SetTextViews();
//            UserDetailsUpdateApi();
            fetchProfileDetails();

            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();

            GetUserDataApi getUserDataApi = new GetUserDataApi(this);
//            getUserDataApi.execute();
            getUserDataApi.HitGetUserDataApi();

            PopupDialog.getInstance(requireContext())
                    .setStyle(Styles.SUCCESS)
                    .setHeading("Well Done")
                    .setDescription("Successfully" +
                            " Edit Profile")
                    .setCancelable(false)
                    .showDialog(new OnDialogButtonClickListener() {
                        @Override
                        public void onDismissClicked(Dialog dialog) {
                            super.onDismissClicked(dialog);
                        }
                    });
            Loading.hide();
        });

    }

//    private void runOnUiThread(Object well_done) {
//    }

    public int boolToInt(boolean value) {
        return value ? 1 : 0;
    }


    @Override
    public void OnUserDetailsUpdateApiGivesError(String error) {
        requireActivity().runOnUiThread(() -> {

            SetTextViews();
//        UserDetailsUpdateApi();
            fetchProfileDetails();

            requireActivity().runOnUiThread(() ->
                    Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show());
//        Loading.hide();
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show();

            PopupDialog.getInstance(requireContext())
                    .setStyle(Styles.FAILED)
                    .setHeading("Uh-Oh")
                    .setDescription("Unexpected error occurred." +
                            " Try again later.")
                    .setCancelable(false)
                    .showDialog(new OnDialogButtonClickListener() {
                        @Override
                        public void onDismissClicked(Dialog dialog) {
                            super.onDismissClicked(dialog);
                        }
                    });
        });


    }


    @Override
    public void OnGetUserDataResultApiGivesSuccess(ArrayList<ProfileGetUserDataResponse.ProfileGetUserDataModel> profileGetUserDataModels) {
//        myAddresses_personal_address_recyclerview_List.setLayoutManager(new LinearLayoutManager(requireContext()));
//        myAddresses_personal_address_recyclerview_List.setAdapter(new YourAdapter(profileGetUserDataModels));
        try {
            requireActivity().runOnUiThread(() -> {

                SetTextViews();
//                UserDetailsUpdateApi();
                fetchProfileDetails();

                Log.e("TAG", String.valueOf(profileGetUserDataModels));

//                Loading.hide();
//                requireActivity().runOnUiThread(this::getUserVisibleHint);
//                requireActivity().runOnUiThread(Loading::hide);

                GetUserDataApi getUserDataApi = new GetUserDataApi(this);
                getUserDataApi.HitGetUserDataApi();

//                UserDetailsUpdateApi userDetaialsUpdateApi = new UserDetailsUpdateApi(this, name, email, city, state, district, Variables.token, userId);
//                userDetaialsUpdateApi.HitUserDetailsUpdateApi();

//                UserDetailsUpdateApi();

            });
        } catch (Exception e) {
            Log.e("TAG", e.toString());
        }

        PopupDialog.getInstance(requireContext())
                .setStyle(Styles.SUCCESS)
                .setHeading("Well Done")
                .setDescription("Successfully" +
                        " Profile")
                .setCancelable(false)
                .showDialog(new OnDialogButtonClickListener() {
                    @Override
                    public void onDismissClicked(Dialog dialog) {
                        super.onDismissClicked(dialog);
                    }
                });

    }

    @Override
    public void OnGetUserDataResultApiGivesError(String error) {
        try {
            requireActivity().runOnUiThread(() -> {

                SetTextViews();
//                UserDetailsUpdateApi();
                fetchProfileDetails();

                try {

//                    Loading.hide();
                    Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(requireContext(), "No Data Found", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                PopupDialog.getInstance(requireContext())
                        .setStyle(Styles.FAILED)
                        .setHeading("Uh-Oh")
                        .setDescription("Unexpected error occurred." +
                                " Try again later.")
                        .setCancelable(false)
                        .showDialog(new OnDialogButtonClickListener() {
                            @Override
                            public void onDismissClicked(Dialog dialog) {
                                super.onDismissClicked(dialog);
                            }
                        });
//                UserDetailsUpdateApi();
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}