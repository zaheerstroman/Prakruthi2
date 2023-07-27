package com.prakruthi.ui.ui.profile;

import static android.content.ContentValues.TAG;
import static com.prakruthi.ui.Variables.city;
import static com.prakruthi.ui.Variables.district;
import static com.prakruthi.ui.Variables.email;
import static com.prakruthi.ui.Variables.name;
import static com.prakruthi.ui.Variables.state;
import static com.prakruthi.ui.Variables.token;
import static com.prakruthi.ui.Variables.userId;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.prakruthi.R;
import com.prakruthi.databinding.FragmentProfileHttpURLConnectionBinding;
import com.prakruthi.ui.APIs.FeedBackApi;
import com.prakruthi.ui.APIs.GetRecentViewProductsAPI;
import com.prakruthi.ui.APIs.GetUserData;
import com.prakruthi.ui.APIs.GetUserDataApi;
import com.prakruthi.ui.APIs.UserDetailsUpdate;
import com.prakruthi.ui.APIs.UserDetailsUpdateApi;
import com.prakruthi.ui.Login;
import com.prakruthi.ui.SplashScreen;
import com.prakruthi.ui.Variables;
import com.prakruthi.ui.misc.Loading;
import com.prakruthi.ui.ui.UserDetails;
import com.prakruthi.ui.ui.profile.myaddress.MyAddresses;
import com.prakruthi.ui.ui.profile.myorders.MyOrdersActivity;
import com.prakruthi.ui.ui.profile.mypayments.MyPaymentsActivity;
import com.prakruthi.ui.ui.profile.order_qty.OrderQtyActivity;
import com.prakruthi.ui.ui.profile.recentProducts.RecentProductAdaptor;
import com.prakruthi.ui.ui.profile.recentProducts.RecentProductModel;
import com.prakruthi.ui.utils.Constants;
import com.prakruthi.ui.utils.SharedPrefs;
import com.saadahmedsoft.popupdialog.PopupDialog;
import com.saadahmedsoft.popupdialog.Styles;
import com.saadahmedsoft.popupdialog.listener.OnDialogButtonClickListener;
import com.skydoves.powerspinner.OnSpinnerItemSelectedListener;
import com.skydoves.powerspinner.PowerSpinnerView;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
//public class ProfileFragmentHttpURLConnection extends Fragment implements FeedBackApi.OnFeedbackItemAPiHit, GetRecentViewProductsAPI.OnGetRecentViewProductsAPIHit, GetUserDataApi.OnGetUserDataApiHitFetchedListner, UserDetailsUpdateApi.OnUserDetailsUpdateApiGivesFetchedListner {
//public class ProfileFragmentHttpURLConnection extends Fragment implements FeedBackApi.OnFeedbackItemAPiHit, GetRecentViewProductsAPI.OnGetRecentViewProductsAPIHit, GetUserDataApi.OnGetUserDataApiHitFetchedListner, UserDetailsUpdateApi.OnUserDetailsUpdateApiGivesFetchedListner {
public class ProfileFragmentHttpURLConnection extends Fragment implements FeedBackApi.OnFeedbackItemAPiHit, GetRecentViewProductsAPI.OnGetRecentViewProductsAPIHit {

    GetUserDataApi.OnGetUserDataApiHitFetchedListner mListner;

    AppCompatButton sendotp, backbtn;

    ArrayList<String> districtNames = new ArrayList<>();

    //    PowerSpinnerView spinner_city, spinner_state, spinner_district;
//PowerSpinnerView spinner_city, state, district;
    PowerSpinnerView spinner_city, editTextState, editTextDistrict;


    int stateId = 0;

    public ShimmerRecyclerView myAddresses_personal_address_recyclerview_List;
    private FragmentProfileHttpURLConnectionBinding binding;
    public SharedPreferences sharedPreferences;

    AppCompatTextView tvRecentProducts, tvViewAll;

    AppCompatButton btn_add_new_address, login_http, Logout_http;

    public YourAdapter yourAdapter;

    ImageView iv_edit_http, iv_close;

    Context context;


    LinearLayout ll_site_visit_req_LastUpdated4_http;

    //    public ShimmerRecyclerView ProfileHomeProductsRecycler;
    public ShimmerRecyclerView ProfileHomeProductsRecycler_http;

    TextView tvProfileNameHttp, tvEmailHttp, tvPhoneHttp, tvRoleHttp, tvMyOrdersHttp, tvMyAddressHttp, tvMyWishlistHttp, tvPaymentsHttp, tvFeedbackHttp, tvSupportHttp, tvAboutUsHttp, tvTermsConditionsHttp, tvPrivacyPolicyHttp, tvRRPHttp, tvSecurityHttp;
    private ProfileGetUserDataResponse responseProfile_http;

    //    private ProfileGetUserDataResponseHttpURLConnectionAll.ProfileGetUserDataModel responseProfileHttpUTLConnection;
    private ProfileGetUserDataResponseHttpURLConnectionAll responseProfileHttpUTLConnection;

    private ProfileGetUserDataResponseAli profileGetUserDataResponseAli;

    //    public EditProfileFragment2 editProfileActivity2;
    public EditProfileFragmentHttpURLConnection editProfileFragmentHttpURLConnection;


    public ProfileFragmentHttpURLConnection() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    //    @SuppressLint("MissingInflatedId")
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

//        binding = FragmentProfileHttpURLConnectionBinding.inflate(inflater, container, false);

        View rootView = inflater.inflate(R.layout.fragment_profile_http_u_r_l_connection, container, false);

        iv_edit_http = rootView.findViewById(R.id.iv_edit_http);
//        iv_edit.setOnClickListener(this);

        tvProfileNameHttp = rootView.findViewById(R.id.tv_profile_name_http);
        tvEmailHttp = rootView.findViewById(R.id.tv_email_http);
        tvPhoneHttp = rootView.findViewById(R.id.tv_phone_http);
        tvRoleHttp = rootView.findViewById(R.id.tv_role_http);

        Logout_http = rootView.findViewById(R.id.Logout_http);
//        Logout.setOnClickListener(this);

        tvMyOrdersHttp = rootView.findViewById(R.id.tv_my_orders_http);
//        tvMyOrders.setOnClickListener(this);

        tvMyAddressHttp = rootView.findViewById(R.id.tv_my_address_http);
//        tvMyAddress.setOnClickListener(this);

        tvMyWishlistHttp = rootView.findViewById(R.id.tv_my_wishlist_http);
//        tvMyWishlist.setOnClickListener(this);

        tvPaymentsHttp = rootView.findViewById(R.id.tv_payments_http);
//        tvPayments.setOnClickListener(this);

        tvFeedbackHttp = rootView.findViewById(R.id.tv_feedback_http);
//        tvFeedback.setOnClickListener(this);

        tvSupportHttp = rootView.findViewById(R.id.tv_support_http);
//        tvSupport.setOnClickListener(this);

        tvAboutUsHttp = rootView.findViewById(R.id.tv_About_us_http);
//        tvAboutUs.setOnClickListener(this);

        tvPrivacyPolicyHttp = rootView.findViewById(R.id.tv_privacy_policy_http);
//        tvPrivacyPolicy.setOnClickListener(this);

        tvTermsConditionsHttp = rootView.findViewById(R.id.tv_Terms_Conditions_http);
//        tvTermsConditions.setOnClickListener(this);

        tvRRPHttp = rootView.findViewById(R.id.tv_returnRefundPolicy_http);
//        tvRRP.setOnClickListener(this);

        tvSecurityHttp = rootView.findViewById(R.id.tv_security_http);
//        tvSecurity.setOnClickListener(this);

        Logout_http = rootView.findViewById(R.id.Logout_http);
//        Logout_http.setOnClickListener(this);

        ProfileHomeProductsRecycler_http = rootView.findViewById(R.id.ProfileHomeProductsRecycler_http);
//        ProfileHomeProductsRecycler_http.setOnClickListener(this);
        ProfileHomeProductsRecycler_http.showShimmerAdapter();

        iv_close = rootView.findViewById(R.id.iv_close);

        backbtn = rootView.findViewById(R.id.backbtn);

//        editTextState  = view.findViewById(R.id.editTextState);
//        editTextDistrict = view.findViewById(R.id.editTextDistrict);

        editTextState  = rootView.findViewById(R.id.editTextState);
        //        editTextState.setOnClickListener(this);

        editTextDistrict = rootView.findViewById(R.id.editTextDistrict);
        //        editTextDistrict.setOnClickListener(this);


//        ProfileHomeProductsRecycler = view.findViewById(R.id.ProfileHomeProductsRecycler);
//        ProfileHomeProductsRecycler.showShimmerAdapter();

//        setdata();
        SetTextViews();
//        SetClickListeners();
        UserDetailsUpdateApi();

        //Retrofit
        //fetchProfileDetails();

//        GetUserDataApi getUserDataApi = new GetUserDataApi(this);
////        getUserDataApi.execute();
//        getUserDataApi.HitGetUserDataApi();


////        UserDetailsUpdateApi getDeliveryAddressDetails = new UserDetailsUpdateApi(this, userId, token, name, email, city, state, district);
//        UserDetailsUpdateApi getDeliveryAddressDetails = new UserDetailsUpdateApi(this, Variables.userId, Variables.token, name, email, city, state, district);
//
////        getDeliveryAddressDetails.execute();
//        getDeliveryAddressDetails.HitUserDetailsUpdateApi();


        GetRecentViewProductsAPI getRecentViewProductsAPI = new GetRecentViewProductsAPI(this);
        getRecentViewProductsAPI.HitRecentApi();


//        sharedPreferences = requireActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
//        sharedPreferences = requireActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
        sharedPreferences = requireActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);

        SetTextViews();
//        SetClickListeners();
//        getDropDownData(editTextState,editTextDistrict);


//        editTextState.setOnSpinnerItemSelectedListener((OnSpinnerItemSelectedListener<String>) (oldIndex, oldItem, newIndex, newItem) -> {
//            stateId = newIndex + 1;
////            getDropDownData();
//            getDropDownData(editTextState,editTextDistrict);
//            editTextState.setError(null);
//        });
//
//        editTextDistrict.setOnClickListener(v -> {
//            if (stateId == 0) {
//                editTextState.setError("Select State");
//            } else editTextDistrict.showOrDismiss();
//        });

        // Access the root view of the fragment
//        View root = view.getRootView();
//        View root = rootView.getRootView();


/*
//        View root = view.findViewById(android.R.id.content);
        View root = rootView.findViewById(android.R.id.content);

        root.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // check if the touch is outside of the state view
                    int[] location = new int[2];
                    editTextState.getLocationOnScreen(location);
                    int x = location[0];
                    int y = location[1];
                    int width = editTextState.getWidth();
                    int height = editTextState.getHeight();
                    //                    if (!(event.getX() > x && event.getX() < x + width && event.getY() > y && event.getY() < y )) {
                    return !(event.getX() > x && event.getX() < x + width && event.getY() > y && event.getY() < y + height); // consume the event
                }
                return false; // do not consume the event
            }
        });
*/

//        backbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                super.onBackPressed();
//
//            }
//        });


//        iv_close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//            }
//        });

//        binding.Logout.setOnClickListener(v -> {
//            //Gasaver Retrofit
//            SharedPrefs.getInstance(requireContext()).clearSharedPrefs();
//
//            //
//            Variables.clear();
//            // Get SharedPreferences.Editor object
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putBoolean("rememberMe", false);
//            // Apply changes
//            editor.apply();
//            startActivity(new Intent(requireContext(), Login.class));
//            requireActivity().finish();
//
//            //Gasaver
//            Intent intent1 = new Intent(requireContext(), Login.class);
//            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent1);
//        });

        //---------------------------------------------------------------------------------------------

//        if (iv_edit_http != null) {
//            iv_edit_http.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//        iv_edit_http.setOnClickListener(v -> {
//
//
////            startActivity(new Intent(requireContext(), EditProfileFragment2.class));
////            startActivity(new Intent(requireContext(), EditProfileFragmentHttpURLConnection.class));
//
//
////            Intent i = new Intent(Intent.ACTION_VIEW);
////                    i.putExtra("SELECTED_POS", 0);
////                    i.putExtra("SELECTED_POS", 1);
////                    startActivity(i);
//
////            if (responseProfile1.getData() != null) {
////            if (responseProfile != null) {
////            if (responseProfile_http != null) {
//            if (responseProfile_http == null) {
//                Log.e("TAG", "Response profile is null. Make sure it is initialized correctly.");
//
////                startActivity(new Intent(requireContext(), EditProfileFragmentHttpURLConnection.class));
//                return;
//            }
//
//            ProfileGetUserDataResponse.ProfileGetUserDataModel data = responseProfile_http.getData();
////                ProfileGetUserDataResponseRetrofit.ProfileGetUserDataModelRetrofit data = responseProfile1.getData();
//
//
////                    Intent intent = new Intent(requireContext(), EditProfileActivity2.class);
////                    Intent intent = new Intent(requireContext(), EditProfileFragment2.class);
//
//            if (data != null) {
//
////                    editProfileActivity2 = new EditProfileFragment2();
//                editProfileFragmentHttpURLConnection = new EditProfileFragmentHttpURLConnection();
//
//
////                    intent.putExtra("data", new Gson().toJson(editProfileActivity2));
//
//                Bundle bundle = new Bundle();
//
//                bundle.putString("data", new Gson().toJson(responseProfile_http.getData()));
//                editProfileFragmentHttpURLConnection.setArguments(bundle);
//
////                    editProfileActivity2.setDismissListener(new EditProfileFragment2.DismissListener() {
//                editProfileFragmentHttpURLConnection.setDismissListener(new EditProfileFragmentHttpURLConnection.DismissListener() {
//
//                    //EditProfileActivity2
//                    @Override
//                    public void onDismiss() {
//                        //Retrofit
////                            fetchProfileDetails();
//
//                        UserDetailsUpdateApi();
//
//
//                    }
//                });
//
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
////                    fragmentTransaction.replace(R.id.layout_http_url_layout_http, editProfileFragmentHttpURLConnection, "EditProfileFragment2");
////                    fragmentTransaction.replace(R.id.layout_http_url_layout_http, editProfileFragmentHttpURLConnection, "EditProfileFragmentHttpURLConnection");
//                fragmentTransaction.replace(R.id.layout_http_url_layout_http_edit_profile, editProfileFragmentHttpURLConnection, "EditProfileFragmentHttpURLConnection");
//
//
//                fragmentTransaction.addToBackStack(null); // Add this line if you want the fragment to be added to the back stack
//                fragmentTransaction.commit();
//
////                editProfileActivity2.show(getParentFragmentManager(), "");
//
//                new ProfileFragmentHttpURLConnection().ApiTaskProHttp();
//
//            } else {
//                // Handle the case when 'data' is null
//                Log.e("TAG", "Profile data is null.");
//            }
//
////            }
//
////            else{
////                // Handle the case when 'responseProfile_http' is null
////                Log.e("TAG", "Response profile is null.");
////            }
//
//
//            //
//            //
//            //        });
//
////                }
////                startActivity(i);
//        });
//            startActivity(i);

        //---------------------------------------------------------------------------------

        iv_edit_http.setOnClickListener(v -> {
//            GetUserDataApi getUserDataApi1 = new GetUserDataApi(new GetUserDataApi.OnGetUserDataApiHitFetchedListner() {
//            GetUserData getUserDataApi1 = new GetUserData(new GetUserData.OnGetUserDataFetchedListener() {
//            GetUserData getUserDataApi = new GetUserData(new GetUserData.OnGetUserDataFetchedListener() {
            GetUserData getUserData = new GetUserData(new GetUserData.OnGetUserDataFetchedListener() {

                @Override
//                public void OnGetUserDataResultApiGivesSuccess(ArrayList<ProfileGetUserDataResponse.ProfileGetUserDataModel> profileGetUserDataModels) {
//                public void onUserDataFetched(ArrayList<ProfileGetUserDataResponse.ProfileGetUserDataModel> profileGetUserDataModels) {
                public void onUserDataFetched() {

                    try {
                        requireActivity().runOnUiThread(() -> {
                            iv_edit_http.setClickable(true);
                            showDialog();

//                            SetTextViews();

//                            startActivity(new Intent(requireContext(), EditProfileFragmentHttpURLConnection.class));

//                            GetUserDataApi getUserDataApi = new GetUserDataApi(this);
//                            getUserDataApi.HitGetUserDataApi();

//                            UserDetailsUpdateApi userDetaialsUpdateApi = new UserDetailsUpdateApi(this, userId, token, name, email, city, state, district);
//                            userDetaialsUpdateApi.HitUserDetailsUpdateApi();
                        });
                    } catch (Exception ignore) {
                        requireActivity().runOnUiThread(() -> {
//                            binding.EditProfile.setClickable(true);
//                            binding.ivEditHttp.setClickable(true);
                            iv_edit_http.setClickable(true);

                        });

                    }

                }

                @Override
                public void onUserDataFetchError(String error) {
//                public void OnGetUserDataResultApiGivesError(String error) {
                    requireActivity().runOnUiThread(() -> {
                        try {
                            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show();
//                            binding.EditProfile.setClickable(true);
//                            binding.ivEditHttp.setClickable(true);
                            iv_edit_http.setClickable(true);


                        } catch (Exception ignore) {

                        }
                    });

                }
            });
            getUserData.HitGetUserDataApi();
//            getUserDataApi.HitGetUserDataApi();
//            getUserDataApi1.HitGetUserDataApi();
//            binding.EditProfile.setClickable(false);
//            binding.ivEditHttp.setClickable(false);
            iv_edit_http.setClickable(false);


        });


        //----------------------------------------------------------------------------------------------------

//        if (iv_edit_http != null) {
////        binding.ivEdit.setOnClickListener(v -> {
//            iv_edit_http.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    //---------------
////            startActivity(new Intent(requireContext(), EditProfileFragment2.class));
//                    startActivity(new Intent(requireContext(), EditProfileFragmentHttpURLConnection.class));
//
//
//                    Intent i = new Intent(Intent.ACTION_VIEW);
//
//                    //if (responseProfile1.getData() != null) {
//                    if (responseProfile_http != null) {
//                        ProfileGetUserDataResponse.ProfileGetUserDataModel data = responseProfile_http.getData();
//
//                        if (data != null) {
//                            // Create a new instance of EditProfileFragment and pass the data as arguments
////                    editProfileActivity2 = new EditProfileFragment2();
//                            editProfileFragmentHttpURLConnection = new EditProfileFragmentHttpURLConnection();
//
//                            Bundle bundle = new Bundle();
//                            bundle.putString("data", new Gson().toJson(data));
//                            editProfileFragmentHttpURLConnection.setArguments(bundle);
//
////                    editProfileActivity2.setDismissListener(new EditProfileFragment2.DismissListener() {
//                            editProfileFragmentHttpURLConnection.setDismissListener(new EditProfileFragmentHttpURLConnection.DismissListener() {
//
//                                //EditProfileActivity2
//                                @Override
//                                public void onDismiss() {
////                            fetchProfileDetails();
//                                    UserDetailsUpdateApi();
//                                }
//                            });
//
//                            FragmentManager fragmentManager = getSupportFragmentManager();
//                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                            fragmentTransaction.replace(R.id.layout_http_url_layout_http, editProfileFragmentHttpURLConnection, "EditProfileFragmentHttpURLConnection");
//                            fragmentTransaction.addToBackStack(null); // Add this line if you want the fragment to be added to the back stack
//                            fragmentTransaction.commit();
//
//                            //                editProfileActivity2.show(getParentFragmentManager(), "");
//
//
////                    Login
////            Api(username,password);
//
////                    EditProfile
////                    new ProfileFragment().ApiTaskPro();
////                    new ProfileFragmentHttpURLConnection().ApiTaskPro();
//                            new ProfileFragmentHttpURLConnection().ApiTaskProHttp();
//
//
//                        } else {
//                            // Handle the case when 'data' is null
//                            Log.e("TAG", "Profile data is null.");
//                        }
//
//
//                    }
//                }
//
//                //
//                //
//                //        });
//
//
//            });
//
//        }

        //---------------------------------------------------------------------------


//        if (binding.LogoutHttp != null) {
//            binding.LogoutHttp.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {

//        binding.LogoutHttp.setOnClickListener(v ->
        Logout_http.setOnClickListener(v ->


        {
            //Gasaver Retrofit
            SharedPrefs.getInstance(requireContext()).clearSharedPrefs();

            //
            Variables.clear();
            // Get SharedPreferences.Editor object
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("rememberMe", false);
            // Apply changes
            editor.apply();
            startActivity(new Intent(requireContext(), Login.class));
            requireActivity().finish();

            //Gasaver
            Intent intent1 = new Intent(requireContext(), Login.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent1);
        });

        tvMyOrdersHttp.setOnClickListener(v ->

//        if (tvMyOrdersHttp != null) {
//            binding.tvMyOrdersHttp.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {

                startActivity(new Intent(requireContext(), MyOrdersActivity.class)));

//          intentMyOrders = new Intent(requireContext(), MyOrdersActivity.class);
//          intentMyOrders.putExtra("SELECTED_POS", 0);
//          startActivity(intentMyOrders);

//                }
//            });
//        }


//        tvMyAddressHttp.setOnClickListener(v ->
        if (tvMyAddressHttp != null) {
            tvMyAddressHttp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startActivity(new Intent(requireContext(), MyAddresses.class));
                }
            });
        }

        //        if (Variables.departmentId != 2) {
        //2 place me user_id rakhna:-----
//        if (String.valueOf(Variables.departmentId).equals("2")) {
        if (Variables.departmentId != 2) {


//            binding.tvMyWishlistHttp.setText("quantity");
            tvMyWishlistHttp.setText("quantity");

            Drawable top = getResources().getDrawable(R.drawable.baseline_warehouse_24);
//            binding.tvMyWishlistHttp.setCompoundDrawables(null, top, null, null);
            tvMyWishlistHttp.setCompoundDrawables(null, top, null, null);

//            binding.tvMyWishlistHttp.setOnClickListener(v -> {
            tvMyWishlistHttp.setOnClickListener(v -> {

                startActivity(new Intent(requireContext(), OrderQtyActivity.class));
            });

        } else {
//            binding.tvMyWishlistHttp.setOnClickListener(v -> {
            tvMyWishlistHttp.setOnClickListener(v -> {

                //Comment
                BottomNavigationView bottomNavigationView;
                bottomNavigationView = (BottomNavigationView) requireActivity().findViewById(R.id.nav_view);
                bottomNavigationView.setSelectedItemId(R.id.navigation_wishlist);

                startActivity(new Intent(requireContext(), OrderQtyActivity.class));
            });
        }

//        binding.tvPaymentsHttp.setOnClickListener(v ->
        if (tvPaymentsHttp != null) {
//            binding.tvPaymentsHttp.setOnClickListener(new View.OnClickListener() {
            tvPaymentsHttp.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    startActivity(new Intent(requireContext(), MyPaymentsActivity.class));
                }
            });
        }

//        tvFeedbackHttp.setOnClickListener(v ->{
        if (tvFeedbackHttp != null) {
//            binding.tvFeedbackHttp.setOnClickListener(new View.OnClickListener() {
            tvFeedbackHttp.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    FeedBackDialog();
                }
            });
        }

//        binding.tvFeedbackHttp.setOnClickListener(v -> {
//            FeedBackDialog();
//        });

//        tvFeedbackHttp.setOnClickListener(v -> {
//            FeedBackDialog();
//        });


        if (tvPrivacyPolicyHttp != null) {
            tvPrivacyPolicyHttp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(requireContext(), PrivacyPolicyActivity.class));
                    Intent i = new Intent(Intent.ACTION_VIEW);
//            i.setData(Uri.parse(Constants.PRIVACY_POLICY_URL));
                    i.setData(Uri.parse(Constants.PRIVACY_POLICY_URL1));

                    startActivity(i);
                }
            });
        }

        if (tvTermsConditionsHttp != null) {
            tvTermsConditionsHttp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(requireContext(), TermNConditionWebViewActivity.class));
                    Intent i = new Intent(Intent.ACTION_VIEW);
//            i.setData(Uri.parse(Constants.TERMS_COND_URL));
                    i.setData(Uri.parse(Constants.TERMS_COND_URL1));
                    startActivity(i);
                }
            });
        }

        if (tvAboutUsHttp != null) {
            tvAboutUsHttp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(requireContext(), AboutUsWebViewActivity.class));
                    Intent i = new Intent(Intent.ACTION_VIEW);
//            i.setData(Uri.parse(Constants.TERMS_COND_URL));
                    i.setData(Uri.parse(Constants.ABOUTUS));
                    startActivity(i);
                }
            });
        }

        if (tvSupportHttp != null) {
            tvSupportHttp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startActivity(new Intent(requireContext(), SupportActivity.class));
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    startActivity(i);
                }

            });
        }

        if (tvRRPHttp != null) {
            tvRRPHttp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(requireContext(), ReturnRefundPolicyActivity.class));
                    Intent i = new Intent(Intent.ACTION_VIEW);
//            i.setData(Uri.parse(Constants.TERMS_COND_URL));
                    i.setData(Uri.parse(Constants.ABOUTUS));
                    startActivity(i);
                }
            });
        }


        if (tvSecurityHttp != null) {
            tvSecurityHttp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startActivity(new Intent(requireContext(), SecurityActivity.class));
                    Intent i = new Intent(Intent.ACTION_VIEW);
//            i.setData(Uri.parse(Constants.TERMS_COND_URL));
                    i.setData(Uri.parse(Constants.ABOUTUS));
                    startActivity(i);
                }
            });
        }

        if (ll_site_visit_req_LastUpdated4_http != null) {
            ll_site_visit_req_LastUpdated4_http.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPrefs.getInstance(getActivity()).clearSharedPrefs();
                    Intent intent1 = new Intent(getActivity(), SplashScreen.class);
                    intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent1);
                }
            });
        }


//        return binding.getRoot();

//        return view;
        return rootView;

    }

    private void showDialog() {


        // Create a custom dialog
        final Dialog dialog = new Dialog(requireContext());
        dialog.setContentView(R.layout.profile_edit_dialog);
        dialog.setCancelable(true);


        // Set the dialog's window width to match_parent
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(layoutParams);

        // Get references to the UI elements in the custom dialog
        EditText editTextName = dialog.findViewById(R.id.editTextName);
        EditText editTextEmail = dialog.findViewById(R.id.editTextEmail);
        EditText editTextCity = dialog.findViewById(R.id.editTextCity);

//        PowerSpinnerView editTextState = dialog.findViewById(R.id.editTextState);
//        PowerSpinnerView editTextDistrict = dialog.findViewById(R.id.editTextDistrict);

        editTextState = dialog.findViewById(R.id.editTextState);
        editTextDistrict = dialog.findViewById(R.id.editTextDistrict);

        Button buttonSubmit = dialog.findViewById(R.id.btn_save);

        getDropDownData(editTextState, editTextDistrict);
        // Add click listener to the Submit button
        editTextName.setText(UserDetails.name);
        editTextEmail.setText(UserDetails.email);
        editTextCity.setText(UserDetails.city);
        editTextState.setText(UserDetails.state);
        editTextDistrict.setText(UserDetails.district);

        buttonSubmit.setOnClickListener(v -> {

            editTextState.setError(null);
            editTextDistrict.setError(null);

            // Check if any of the EditText fields are empty
            boolean hasEmptyFields = false;
            if (TextUtils.isEmpty(editTextName.getText())) {
                editTextName.setError("Name cannot be empty");
                hasEmptyFields = true;
            }
            if (TextUtils.isEmpty(editTextEmail.getText())) {
                editTextEmail.setError("Email cannot be empty");
                hasEmptyFields = true;
            }
            if (TextUtils.isEmpty(editTextCity.getText())) {
                editTextCity.setError("City cannot be empty");
                hasEmptyFields = true;
            }
            if (TextUtils.isEmpty(editTextState.getText())) {
                editTextState.setError("State cannot be empty");

                ObjectAnimator.ofFloat(editTextState, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
                editTextState.requestFocus();

                hasEmptyFields = true;
            }
            if (TextUtils.isEmpty(editTextDistrict.getText())) {
                editTextDistrict.setError("District cannot be empty");

                ObjectAnimator.ofFloat(editTextDistrict, "translationX", 0, -10, 10, -10, 10, -10, 10, -10, 10, 0).start();
                editTextDistrict.requestFocus();

                hasEmptyFields = true;
            }

            // If any EditText field is empty, return without dismissing the dialog
            if (hasEmptyFields) {
                return;
            } else {
                UserDetailsUpdate userDetailsUpdate = new UserDetailsUpdate(
//                UserDetailsUpdateApi userDetailsUpdate = new UserDetailsUpdateApi(

                        editTextName.getText().toString(),
                        editTextEmail.getText().toString(),
                        editTextCity.getText().toString(),
                        editTextState.getText().toString(),
                        editTextDistrict.getText().toString(),

                        new UserDetailsUpdate.OnUserDetailsUpdateListener() {
//                        new UserDetailsUpdateApi.OnUserDetailsUpdateApiGivesFetchedListner() {

                            @Override
                            public void onUserDetailsUpdate(String messaage) {
//                            public void OnUserDetailsUpdateApiGivesFetched(String messaage) {

                                requireActivity().runOnUiThread(() -> {
                                    try {
                                        Variables.name = editTextName.getText().toString();
//                                binding.txtId.setText(Variables.name);
//                                binding.tvProfileNameHttp.setText(Variables.name);
                                        tvProfileNameHttp.setText(Variables.name);

                                        email = editTextEmail.getText().toString();
                                        tvEmailHttp.setText(email);

//                                        city = editTextCity.getText().toString();
//                                        tvCityHttp.setText(city);

                                        Toast.makeText(requireContext(), messaage, Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                        Loading.hide();
                                    } catch (Exception ignore) {
                                        Loading.hide();
                                    }

                                });

                            }

                            @Override
                            public void onUserDetailsError(String error) {
//                            public void OnUserDetailsUpdateApiGivesError(String error) {

                                requireActivity().runOnUiThread(() -> {
                                    try {
                                        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                        Loading.hide();
                                    } catch (Exception ignore) {
                                        Loading.hide();
                                    }
                                });

                            }
                        });

                userDetailsUpdate.HitUserDetailsUpdateApi();
                Loading.show(requireContext());
            }

            // All fields are filled, you can handle the submit logic here
            // For example, you can get the values from the EditText fields and save them
            // to your profile.

            dialog.dismiss(); // Close the dialog after processing
        });


        dialog.show();
    }

    @SuppressLint("StaticFieldLeak")
    public void getDropDownData(PowerSpinnerView State, PowerSpinnerView District) {
        new AsyncTask<Void, Void, JSONObject>() {
            @Override
            protected JSONObject doInBackground(Void... voids) {
                FetchData fetchData = new FetchData("https://houseofspiritshyd.in/prakruthi/admin/api/getDropdownData");
                if (fetchData.startFetch()) {
                    if (fetchData.onComplete()) {
                        String result = fetchData.getResult();
                        Log.i("getDropDownData", result);
                        try {
                            JSONObject jsonObj = new JSONObject(result);
                            return jsonObj;
//                            return new JSONObject(result);
                        } catch (JSONException e) {
                            return null;
                        }
                    }
                    return null;
                }
                return null;
            }

            @Override
            protected void onPostExecute(JSONObject jsonObj) {
                super.onPostExecute(jsonObj);

                if (jsonObj != null) {
                    try {

                        JSONArray states = jsonObj.getJSONArray("state");
                        ArrayList<String> stateNames = new ArrayList<>();
                        for (int i = 0; i < states.length(); i++) {
                            JSONObject state = states.getJSONObject(i);
                            stateNames.add(state.getString("name"));
                        }
                        State.setItems(stateNames);

                        JSONArray districts = jsonObj.getJSONArray("district");
                        districtNames.clear();

//                        ArrayList<String> districtNames = new ArrayList<>();
                        for (int i = 0; i < districts.length(); i++) {
                            JSONObject districtt = districts.getJSONObject(i);

                            if (districtt.getInt("state_id") == stateId) {

                                districtNames.add(districtt.getString("name"));
//                                spinner_district.setItems(districtNames);
                                District.setItems(districtNames);

                            }


                            districtNames.add(districtt.getString("name"));
                        }
                        District.setItems(districtNames);


                    } catch (JSONException e) {
                        Toast.makeText(requireContext(), "Network Error", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(requireContext(), "Network Error", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }


    //HttpURLConnection:---

    public void SetTextViews() {
//        binding.txtId.setText("");
//        binding.txtId.append(String.valueOf(Variables.name));

//        binding.tvProfileNameHttp.setText("");
//        binding.tvProfileNameHttp.append(String.valueOf(name));

        tvProfileNameHttp.setText("");
        tvProfileNameHttp.append(String.valueOf(name));

//        binding.tvEmailHttp.setText("");
//        binding.tvEmailHttp.append(String.valueOf(Variables.email));

        tvEmailHttp.setText("");
        tvEmailHttp.append(String.valueOf(Variables.email));

//        binding.tvPhoneHttp.setText("");
////        binding.tvPhone.append(String.valueOf(Variables.phoneNumber));
//        binding.tvPhoneHttp.append(String.valueOf(Variables.mobile));

        tvPhoneHttp.setText("");
//        binding.tvPhone.append(String.valueOf(Variables.phoneNumber));
        tvPhoneHttp.append(String.valueOf(Variables.mobile));

//        binding.tvRoleHttp.setText("");
//        binding.tvRoleHttp.append(String.valueOf(Variables.userCode));

        tvRoleHttp.setText("");
        tvRoleHttp.append(String.valueOf(Variables.userCode));

//        binding.ProfileHomeProductsRecyclerHttp.showShimmerAdapter();
//        binding.tvTermsConditionsHttp.setSelected(true);

        ProfileHomeProductsRecycler_http.showShimmerAdapter();
        tvTermsConditionsHttp.setSelected(true);

//        binding.tvTermsConditionsHttp.setSelected(true);

        GetRecentViewProductsAPI getRecentViewProductsAPI = new GetRecentViewProductsAPI(this);
        getRecentViewProductsAPI.HitRecentApi();

//        GetUserDataApi getUserDataApi = new GetUserDataApi(this);
//        getUserDataApi.HitGetUserDataApi();

//        UserDetailsUpdateApi userDetailsUpdateApi = new UserDetailsUpdateApi(this, userId, token, name, email, city, state, district);
//        UserDetailsUpdateApi userDetailsUpdateApi = new UserDetailsUpdateApi(this, Variables.userId, Variables.token, name, email, city, state, district);
//        UserDetailsUpdateApi userDetailsUpdateApi = new UserDetailsUpdateApi(this, name, email, city, state, district, userId, token);

//        UserDetailsUpdateApi userDetailsUpdateApi = new UserDetailsUpdateApi(this,  userId, token, name, email, city, state, district);
//        userDetailsUpdateApi.HitUserDetailsUpdateApi();

//        userDetailsUpdateApi.execute();

        //Retrofit:-------
        if (editProfileFragmentHttpURLConnection != null && editProfileFragmentHttpURLConnection.isVisible()) {
            editProfileFragmentHttpURLConnection.updateDetails(responseProfile_http.getData());
//            editProfileActivity2.updateDetails((ProfileGetUserDataResponseRetrofit.ProfileGetUserDataModelRetrofit) responseProfileHttpUTLConnection.getData());

        }


    }

    @SuppressLint("StaticFieldLeak")
    public void ApiTaskProHttp() {
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
//                        return result;

                        //                        return result;

                        //(Or):---------

//                        Log.e(com.google.firebase.messaging.Constants.TAG, result);

                        try {
                            // Parse JSON response
                            JSONObject jsonObject = new JSONObject(result);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

//                        ArrayList<RecentProductModel> recentProductModels = new ArrayList<>();
//                        List<ProfileGetUserDataResponse.ProfileGetUserDataModel> profileGetUserDataModels = new List<>();
//                        List<ProfileGetUserDataResponse.ProfileGetUserDataModel> profileGetUserDataModels = new ArrayList<>();
                            ArrayList<ProfileGetUserDataResponse.ProfileGetUserDataModel> profileGetUserDataModels = new ArrayList<>();

                            // Loop through JSON array and create Address objects
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject obj = jsonArray.getJSONObject(i);

                                boolean statusCode = jsonObject.getBoolean("status_code");
                                String message = jsonObject.getString("message");
                                String privacyPolicy = jsonObject.getString("privacyPolicy");
                                String termsAndConditions = jsonObject.getString("termsAndConditions");
//
//                            if (statusCode)
//                            {
//                                handleResponse("Thanks For Your Feedback");
//                            }
//                            else
//                            {
//                                handleResponse("Internal Error");
//                            }

//                            int id = obj.getInt("id");
                                String id = obj.getString("id");
//                            int department_id = obj.getInt("department_id");
                                String department_id = obj.getString("department_id");
                                String user_code = obj.getString("user_code");

                                String name = obj.getString("name");
                                String last_name = obj.getString("last_name");
                                String email = obj.getString("email");
                                String password = obj.getString("password");
                                String mobile = obj.getString("mobile");
                                String gender = obj.getString("gender");
                                String dob = obj.getString("dob");
                                String attachment = obj.getString("attachment");
                                String city = obj.getString("city");
                                String postCode = obj.getString("postCode");
                                String address = obj.getString("address");
                                String state = obj.getString("state");
                                String country = obj.getString("country");
                                String district = obj.getString("district");
                                String street = obj.getString("street");
                                String about = obj.getString("about");
                                String status = obj.getString("status");
                                String mobile_verified = obj.getString("mobile_verified");
                                String is_verified = obj.getString("is_verified");
                                String log_date_created = obj.getString("log_date_created");
                                String created_by = obj.getString("created_by");
                                String log_date_modified = obj.getString("log_date_modified");
                                String modified_by = obj.getString("modified_by");
                                String fcm_token = obj.getString("fcm_token");
                                String device_id = obj.getString("device_id");
                                String allow_email = obj.getString("allow_email");
                                String allow_sms = obj.getString("allow_sms");
                                String allow_push = obj.getString("allow_push");

//                            int Defualt = obj.getInt("is_default");

//                            int id = obj.getInt("id");

//                            if (Defualt == 1) {
//                                Variables.address = address;
//                            }
//                            Boolean statusCode, String privacyPolicy, String termsAndConditions, String message, int id, Integer departmentId, String userCode, String name, String lastName, String email, String password, String mobile, String gender, String dob, String attachment, String city, String postCode, String address, String state, String country, String district, String street, String about, String status, String mobileVerified, String isVerified, String logDateCreated, String createdBy, String logDateModified, String modifiedBy, String fcmToken, String deviceId, String allowEmail, String allowSms, String allowPush
//                            profileGetUserDataList.add(new ProfileGetUserDataResponse(id, departmentId, userCode, name, lastName, email, password, mobile, gender, dob, attachment, city, postCode, address, state, country, district, street, about, status, mobileVerified, isVerified, logDateCreated, createdBy, logDateModified, modifiedBy, fcmToken, deviceId, allowEmail, allowSms, allowPush));
                                profileGetUserDataModels.add(new ProfileGetUserDataResponse.ProfileGetUserDataModel(id, department_id, user_code, name, last_name, email, password, mobile, gender, dob, attachment, city, postCode, address, state, country, district, street, about, status, mobile_verified, is_verified, log_date_created, created_by, log_date_modified, modified_by, fcm_token, device_id, allow_email, allow_sms, allow_push));
//                                profileGetUserDataModels.add(new ProfileGetUserDataResponse.ProfileGetUserDataModel(user_code, name, email, mobile, city, state, district));

                            }
//                        mListner.OnGetUserDataResultApiGivesSuccess(profileGetUserDataList);
                            mListner.OnGetUserDataResultApiGivesSuccess(profileGetUserDataModels);


//                        handleResponseAdd(message);
                        } catch (JSONException e) {
                            e.printStackTrace();
//                        handleError("Internal Error");
//                            mListner.OnGetUserDataResultApiGivesError("Internal Error");

                        }
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
                        login_http.setVisibility(View.VISIBLE);
                        Toast.makeText(requireContext(), "Network Error", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    login_http.setVisibility(View.VISIBLE);
                    Toast.makeText(requireContext(), "Network Error", Toast.LENGTH_SHORT).show();
                }
                login_http.setVisibility(View.VISIBLE);
                Loading.hide();
            }
        }.execute();

        GetRecentViewProductsAPI getRecentViewProductsAPI = new GetRecentViewProductsAPI(this);
        getRecentViewProductsAPI.HitRecentApi();

//        GetUserDataApi getUserDataApi = new GetUserDataApi(this);
//        getUserDataApi.HitGetUserDataApi();
//
////        UserDetailsUpdateApi userDetailsUpdateApi = new UserDetailsUpdateApi(this, userId, token, name, email, city, state, district);
//        UserDetailsUpdateApi userDetailsUpdateApi = new UserDetailsUpdateApi(this, Variables.userId, Variables.token, name, email, city, state, district);
//
//        userDetailsUpdateApi.HitUserDetailsUpdateApi();

    }

    public void refreshFragment() {
        // Perform operations to refresh the data
        // For example, make an API request or update data from a local source

        // Update the UI to reflect the refreshed data
        // Update views, adapters, or reload the fragment's content

        // For example, if you're using a RecyclerView:
        yourAdapter.notifyDataSetChanged();


    }

    // Example usage: call this method to refresh the fragment
    public void refreshButtonClicked() {
        refreshFragment();
    }


//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//
//    }


    public void UserDetailsUpdateApi() {

//        GetUserDataApi getUserDataApi = new GetUserDataApi(this);
//        getUserDataApi.HitGetUserDataApi();
//
////        UserDetailsUpdateApi userDetaialsUpdateApi = new UserDetailsUpdateApi(this, userId, token, name, email, city, state, district);
//        UserDetailsUpdateApi userDetaialsUpdateApi = new UserDetailsUpdateApi(this, Variables.userId, Variables.token, name, email, city, state, district);
//
//        userDetaialsUpdateApi.HitUserDetailsUpdateApi();
////        userDetaialsUpdateApi.execute();

        GetRecentViewProductsAPI getRecentViewProductsAPI = new GetRecentViewProductsAPI(this);
        getRecentViewProductsAPI.HitRecentApi();

    }

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


//    public void SetClickListeners() {
//
//        iv_edit_http.setOnClickListener(v -> {
//
//
//            //---------------
////            startActivity(new Intent(requireContext(), EditProfileFragment2.class));
//            startActivity(new Intent(requireContext(), EditProfileFragmentHttpURLConnection.class));
//
//
//            //if (responseProfile1.getData() != null) {
//            if (responseProfile_http != null) {
//                ProfileGetUserDataResponse.ProfileGetUserDataModel data = responseProfile_http.getData();
//
//                if (data != null) {
//                    // Create a new instance of EditProfileFragment and pass the data as arguments
////                    editProfileActivity2 = new EditProfileFragment2();
//                    editProfileFragmentHttpURLConnection = new EditProfileFragmentHttpURLConnection();
//
//                    Bundle bundle = new Bundle();
//                    bundle.putString("data", new Gson().toJson(data));
//                    editProfileFragmentHttpURLConnection.setArguments(bundle);
//
////                    editProfileActivity2.setDismissListener(new EditProfileFragment2.DismissListener() {
//                    editProfileFragmentHttpURLConnection.setDismissListener(new EditProfileFragmentHttpURLConnection.DismissListener() {
//
//                        //EditProfileActivity2
//                        @Override
//                        public void onDismiss() {
////                            fetchProfileDetails();
//                            UserDetailsUpdateApi();
//                        }
//                    });
//
//
////                    Login
////            Api(username,password);
//
////                    EditProfile
////                    new ProfileFragment().ApiTaskPro();
////                    new ProfileFragmentHttpURLConnection().ApiTaskPro();
//                    new ProfileFragmentHttpURLConnection().ApiTaskProHttp();
//
//
//                } else {
//                    // Handle the case when 'data' is null
//                    Log.e("TAG", "Profile data is null.");
//                }
//
//
//            }
//
//
//            //
//            //
//            //        });
//
//
//        });
//
//
////        binding.Logout.setOnClickListener(v -> {
////            //Gasaver Retrofit
////            SharedPrefs.getInstance(requireContext()).clearSharedPrefs();
////
////            //
////            Variables.clear();
////            // Get SharedPreferences.Editor object
////            SharedPreferences.Editor editor = sharedPreferences.edit();
////            editor.putBoolean("rememberMe", false);
////            // Apply changes
////            editor.apply();
////            startActivity(new Intent(requireContext(), Login.class));
////            requireActivity().finish();
////
////            //Gasaver
////            Intent intent1 = new Intent(requireContext(), Login.class);
////            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
////            startActivity(intent1);
////        });
////
////
////        binding.tvMyOrders.setOnClickListener(v -> startActivity(new Intent(requireContext(), MyOrdersActivity.class)));
////
////
////        binding.tvMyAddress.setOnClickListener(v -> {
////            startActivity(new Intent(requireContext(), MyAddresses.class));
////        });
////
////        //        if (Variables.departmentId != 2) {
////        //2 place me user_id rakhna:-----
////        if (String.valueOf(Variables.departmentId).equals("2")) {
////
////            binding.tvMyWishlist.setText("quantity");
////            Drawable top = getResources().getDrawable(R.drawable.baseline_warehouse_24);
////            binding.tvMyWishlist.setCompoundDrawables(null, top, null, null);
////            binding.tvMyWishlist.setOnClickListener(v -> {
////                startActivity(new Intent(requireContext(), OrderQtyActivity.class));
////            });
////        } else {
////            binding.tvMyWishlist.setOnClickListener(v -> {
////
////                //Comment
////                BottomNavigationView bottomNavigationView;
////                bottomNavigationView = (BottomNavigationView) requireActivity().findViewById(R.id.nav_view);
////                bottomNavigationView.setSelectedItemId(R.id.navigation_wishlist);
////
////                startActivity(new Intent(requireContext(), OrderQtyActivity.class));
////            });
////        }
////
////        binding.tvPayments.setOnClickListener(v ->
////                startActivity(new Intent(requireContext(), MyPaymentsActivity.class))
////        );
////
////        if (tvFeedback != null) {
////            binding.tvFeedback.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    FeedBackDialog();
////                }
////            });
////        }
////
////        if (tvPrivacyPolicy != null) {
////            tvPrivacyPolicy.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
//////                    startActivity(new Intent(requireContext(), PrivacyPolicyActivity.class));
////                    Intent i = new Intent(Intent.ACTION_VIEW);
//////            i.setData(Uri.parse(Constants.PRIVACY_POLICY_URL));
////                    i.setData(Uri.parse(Constants.PRIVACY_POLICY_URL1));
////
////                    startActivity(i);
////                }
////            });
////        }
////
////        if (tvTermsConditions != null) {
////            tvTermsConditions.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
//////                    startActivity(new Intent(requireContext(), TermNConditionWebViewActivity.class));
////                    Intent i = new Intent(Intent.ACTION_VIEW);
//////            i.setData(Uri.parse(Constants.TERMS_COND_URL));
////                    i.setData(Uri.parse(Constants.TERMS_COND_URL1));
////                    startActivity(i);
////                }
////            });
////        }
////
////        if (tvAboutUs != null) {
////            tvAboutUs.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    startActivity(new Intent(requireContext(), AboutUsWebViewActivity.class));
////                    Intent i = new Intent(Intent.ACTION_VIEW);
//////            i.setData(Uri.parse(Constants.TERMS_COND_URL));
////                    i.setData(Uri.parse(Constants.ABOUTUS));
////                    startActivity(i);
////                }
////            });
////        }
////
////        if (tvAboutUs != null) {
////            tvSupport.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////
////                    startActivity(new Intent(requireContext(), SupportActivity.class));
////                    Intent i = new Intent(Intent.ACTION_VIEW);
////                    startActivity(i);
////                }
////
////            });
////        }
////
////        if (tvRRP != null) {
////            tvRRP.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    startActivity(new Intent(requireContext(), ReturnRefundPolicyActivity.class));
////                    Intent i = new Intent(Intent.ACTION_VIEW);
//////            i.setData(Uri.parse(Constants.TERMS_COND_URL));
////                    i.setData(Uri.parse(Constants.ABOUTUS));
////                    startActivity(i);
////                }
////            });
////        }
////
////
////        if (tvSecurity != null) {
////            tvSecurity.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////
////                    startActivity(new Intent(requireContext(), SecurityActivity.class));
////                    Intent i = new Intent(Intent.ACTION_VIEW);
//////            i.setData(Uri.parse(Constants.TERMS_COND_URL));
////                    i.setData(Uri.parse(Constants.ABOUTUS));
////                    startActivity(i);
////                }
////            });
////        }
////
////        if (ll_site_visit_req_LastUpdated4 != null) {
////            ll_site_visit_req_LastUpdated4.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    SharedPrefs.getInstance(getActivity()).clearSharedPrefs();
////                    Intent intent1 = new Intent(getActivity(), SplashScreen.class);
////                    intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
////                    startActivity(intent1);
////                }
////            });
////        }
//
//    }


    private FragmentManager getSupportFragmentManager() {
//        return null;
        return null;

    }

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

    @Override
    public void OnGetRecentViewProductsAPIGivesResult
            (ArrayList<RecentProductModel> recentProductModels) {
        try {
            requireActivity().runOnUiThread(() -> {
//                binding.ProfileHomeProductsRecyclerHttp.setLayoutManager(new GridLayoutManager(requireContext(), 2));
//                binding.ProfileHomeProductsRecyclerHttp.setAdapter(new RecentProductAdaptor(recentProductModels));
//                binding.ProfileHomeProductsRecyclerHttp.hideShimmerAdapter();

                ProfileHomeProductsRecycler_http.setLayoutManager(new GridLayoutManager(requireContext(), 2));
                ProfileHomeProductsRecycler_http.setAdapter(new RecentProductAdaptor(recentProductModels));
                ProfileHomeProductsRecycler_http.hideShimmerAdapter();
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
//                binding.ProfileHomeProductsRecyclerHttp.hideShimmerAdapter();
//                binding.tvRecentProductsHttp.setVisibility(View.GONE);
//                binding.tvViewAllHttp.setVisibility(View.GONE);
                ProfileHomeProductsRecycler_http.hideShimmerAdapter();
                tvRecentProducts.setVisibility(View.GONE);
                tvViewAll.setVisibility(View.GONE);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

//    @Override
//    public void OnUserDetailsUpdateApiGivesFetched(String message) {
//        requireActivity().runOnUiThread(() -> {
//            Loading.hide();
//
//            SetTextViews();
//            UserDetailsUpdateApi();
//
//
//            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
//
//            GetUserDataApi getUserDataApi = new GetUserDataApi(this);
////            getUserDataApi.execute();
//            getUserDataApi.HitGetUserDataApi();
//
//            PopupDialog.getInstance(requireContext()).setStyle(Styles.SUCCESS).setHeading("Well Done").setDescription("Successfully" + " Edit Profile").setCancelable(false).showDialog(new OnDialogButtonClickListener() {
//                @Override
//                public void onDismissClicked(Dialog dialog) {
//                    super.onDismissClicked(dialog);
//                }
//            });
//            Loading.hide();
//        });
//
//    }
//
//    private void runOnUiThread(Object well_done) {
//    }
//
//    public int boolToInt(boolean value) {
//        return value ? 1 : 0;
//    }
//
//    @Override
//    public void OnUserDetailsUpdateApiGivesError(String error) {
//        requireActivity().runOnUiThread(() -> {
//
//            SetTextViews();
//            UserDetailsUpdateApi();
//
//            requireActivity().runOnUiThread(() -> Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show());
////        Loading.hide();
//            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show();
//
//            PopupDialog.getInstance(requireContext()).setStyle(Styles.FAILED).setHeading("Uh-Oh").setDescription("Unexpected error occurred." + " Try again later.").setCancelable(false).showDialog(new OnDialogButtonClickListener() {
//                @Override
//                public void onDismissClicked(Dialog dialog) {
//                    super.onDismissClicked(dialog);
//                }
//            });
//        });
//
////        SetTextViews();
//    }

//    @Override
//    public void OnUserDetailsUpdateApiGivesFetched(ArrayList<ProfileGetUserDataResponse.ProfileGetUserDataModel> addressList) {
////                myAddresses_personal_address_recyclerview_List.setLayoutManager(new LinearLayoutManager(requireContext()));
////        myAddresses_personal_address_recyclerview_List.setAdapter(new YourAdapter(addressList));
//    }

//    @Override
//    public void OnGetUserDataResultApiGivesSuccess
//            (ArrayList<ProfileGetUserDataResponse.ProfileGetUserDataModel> profileGetUserDataModels) {
//        myAddresses_personal_address_recyclerview_List.setLayoutManager(new LinearLayoutManager(requireContext()));
//        myAddresses_personal_address_recyclerview_List.setAdapter(new YourAdapter(profileGetUserDataModels));
//        try {
//            requireActivity().runOnUiThread(() -> {
////                UserDetailsUpdateApi();
//                Log.e("TAG", String.valueOf(profileGetUserDataModels));
//
//
//                GetUserDataApi getUserDataApi = new GetUserDataApi(this);
//                getUserDataApi.HitGetUserDataApi();
//
////                UserDetailsUpdateApi userDetaialsUpdateApi = new UserDetailsUpdateApi(this, userId, token, name, email, city, state, district);
////                UserDetailsUpdateApi userDetaialsUpdateApi = new UserDetailsUpdateApi(this, Variables.userId, Variables.token, name, email, city, state, district);
//                UserDetailsUpdateApi userDetaialsUpdateApi = new UserDetailsUpdateApi(this, userId, token, name, email, city, state, district);
//
//                userDetaialsUpdateApi.HitUserDetailsUpdateApi();
//            });
//        } catch (Exception e) {
//            Log.e("TAG", e.toString());
//        }
//
//        PopupDialog.getInstance(requireContext()).setStyle(Styles.SUCCESS).setHeading("Well Done").setDescription("Successfully" + " Profile").setCancelable(false).showDialog(new OnDialogButtonClickListener() {
//            @Override
//            public void onDismissClicked(Dialog dialog) {
//                super.onDismissClicked(dialog);
//            }
//        });
//
//    }
//
//    @Override
//    public void OnGetUserDataResultApiGivesError(String error) {
//        try {
//            requireActivity().runOnUiThread(() -> {
//
//                UserDetailsUpdateApi();
//
//                try {
//
////                    Loading.hide();
//                    Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show();
////                    Toast.makeText(requireContext(), "No Data Found", Toast.LENGTH_SHORT).show();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                PopupDialog.getInstance(requireContext()).setStyle(Styles.FAILED).setHeading("Uh-Oh").setDescription("Unexpected error occurred." + " Try again later.").setCancelable(false).showDialog(new OnDialogButtonClickListener() {
//                    @Override
//                    public void onDismissClicked(Dialog dialog) {
//                        super.onDismissClicked(dialog);
//                    }
//                });
//
//            });
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


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
//            Variables.id = id;
//            Variables.departmentId = departmentId;

//            Variables.id = Integer.valueOf(String.valueOf(id));
//            Variables.departmentId = Integer.valueOf(String.valueOf(departmentId));

            Variables.id = Integer.valueOf(String.valueOf(id));
            Variables.departmentId = Integer.valueOf(String.valueOf(departmentId));
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

            iv_edit_http.setVisibility(View.VISIBLE);

            Variables.name = tvProfileNameHttp.getText().toString();
            Variables.email = tvEmailHttp.getText().toString();
            Variables.mobile = tvPhoneHttp.getText().toString();
            Variables.userCode = tvRoleHttp.getText().toString();

            Loading.hide();
//            if (RememberMe.isChecked())
//                rememberMe();
//            startActivity(new Intent(requireContext(), EditProfileFragment2.class));
            startActivity(new Intent(requireContext(), EditProfileFragmentHttpURLConnection.class));


//            finish();
        } catch (JSONException e) {
            Log.e(TAG, e.toString());
            Toast.makeText(requireContext(), "Internal Error", Toast.LENGTH_SHORT).show();
            iv_edit_http.setVisibility(View.VISIBLE);
//            LoginLayout.setVisibility(View.VISIBLE);
        }

    }


}