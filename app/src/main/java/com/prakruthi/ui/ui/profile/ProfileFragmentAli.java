//package com.prakruthi.ui.ui.profile;
//
//import android.annotation.SuppressLint;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.ActionBar;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.GridLayoutManager;
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.app.Dialog;
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.graphics.drawable.Drawable;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Looper;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.WindowManager;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.prakruthi.R;
//import com.prakruthi.databinding.FragmentProfileAliBinding;
//import com.prakruthi.ui.APIs.FeedBackApi;
//import com.prakruthi.ui.APIs.GetRecentViewProductsAPI;
//import com.prakruthi.ui.APIs.GetUserData;
//import com.prakruthi.ui.APIs.UserDetailsUpdate;
//import com.prakruthi.ui.Login;
//import com.prakruthi.ui.Variables;
//import com.prakruthi.ui.misc.Loading;
//import com.prakruthi.ui.ui.UserDetails;
//import com.prakruthi.ui.ui.profile.myaddress.MyAddresses;
//import com.prakruthi.ui.ui.profile.myorders.MyOrdersActivity;
//import com.prakruthi.ui.ui.profile.mypayments.MyPaymentsActivity;
//import com.prakruthi.ui.ui.profile.order_qty.OrderQtyActivity;
//import com.prakruthi.ui.ui.profile.recentProducts.RecentProductAdaptor;
//import com.prakruthi.ui.ui.profile.recentProducts.RecentProductModel;
//import com.skydoves.powerspinner.PowerSpinnerView;
//import com.vishnusivadas.advanced_httpurlconnection.FetchData;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//
///**
// * An example full-screen activity that shows and hides the system UI (i.e.
// * status bar and navigation/system bar) with user interaction.
// */
//public class ProfileFragmentAli extends Fragment implements FeedBackApi.OnFeedbackItemAPiHit , GetRecentViewProductsAPI.OnGetRecentViewProductsAPIHit{
//    private FragmentProfileAliBinding binding;
//
//    public SharedPreferences sharedPreferences;
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//
//        binding = FragmentProfileAliBinding.inflate(inflater, container, false);
//
//        sharedPreferences = requireActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
//
//        SetTextViews();
//        SetClickListeners();
//
//        binding.Logout.setOnClickListener(v -> {
//            Variables.clear();
//            // Get SharedPreferences.Editor object
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putBoolean("rememberMe",false);
//            // Apply changes
//            editor.apply();
//            startActivity(new Intent(requireContext(), Login.class));
//            requireActivity().finish();
//        });
//
//
//        return binding.getRoot();
//
//    }
//
//    public void FeedBackDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
//        builder.setTitle("Feedback");
//
//        // Create the EditText
//        final EditText editText = new EditText(requireContext());
//        builder.setView(editText);
//
//        // Add the submit button
//        builder.setPositiveButton("Submit", null); // Set the click listener to null for now
//
//        // Create and show the dialog
//        AlertDialog dialog = builder.create();
//        dialog.show();
//
//        // Get the button from the dialog's view
//        Button submitButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
//        submitButton.setOnClickListener(view -> {
//            // Handle the submit button click
//            String feedback = editText.getText().toString();
//
//            if (feedback.isEmpty()) {
//                editText.setError("Feedback cannot be empty");
//            } else {
//                // Do something with the feedback
//                FeedBackApi feedBackApi = new FeedBackApi(this, feedback);
//                feedBackApi.FeedbackHitApi();
//                dialog.dismiss(); // Dismiss the dialog after handling the click
//            }
//        });
//    }
//
//    public void SetTextViews()
//    {
//        binding.txtId.setText("");
//        binding.txtId.append(String.valueOf(Variables.name));
//        binding.ProfileHomeProductsRecycler.showShimmerAdapter();
//        binding.tvTermsConditions.setSelected(true);
//        GetRecentViewProductsAPI getRecentViewProductsAPI = new GetRecentViewProductsAPI(this);
//        getRecentViewProductsAPI.HitRecentApi();
//    }
//
//
//    public void SetClickListeners()
//    {
//
//        binding.EditProfile.setOnClickListener(view -> {
//            GetUserData getUserData = new GetUserData(new GetUserData.OnGetUserDataFetchedListener() {
//                @Override
//                public void onUserDataFetched() {
//                    try {
//                        requireActivity().runOnUiThread( () -> {
//                            binding.EditProfile.setClickable(true);
//                            showDialog();
//                        } );
//                    }
//                    catch (Exception ignore)
//                    {
//                        requireActivity().runOnUiThread( () -> {
//                            binding.EditProfile.setClickable(true);
//                        } );
//
//                    }
//
//                }
//
//                @Override
//                public void onUserDataFetchError(String error) {
//                    requireActivity().runOnUiThread( () -> {
//                        try {
//                            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show();
//                            binding.EditProfile.setClickable(true);
//                        }
//                        catch (Exception ignore)
//                        {
//
//                        }
//                    } );
//
//                }
//            });
//            getUserData.HitGetUserDataApi();
//            binding.EditProfile.setClickable(false);
//
//        });
//        binding.tvMyAddress.setOnClickListener(v -> {
//            startActivity(new Intent(requireContext(), MyAddresses.class));
//        });
//        binding.tvFeedback.setOnClickListener(v -> {
//            FeedBackDialog();
//        });
//        if (Variables.departmentId != 2)
//        {
//            binding.tvMyWishlist.setText("quantity");
//            Drawable top = getResources().getDrawable(R.drawable.baseline_warehouse_24);
//            binding.tvMyWishlist.setCompoundDrawables(null,top,null,null);
//            binding.tvMyWishlist.setOnClickListener(v -> {
//                startActivity(new Intent(requireContext(), OrderQtyActivity.class));
//            });
//        }
//        else
//        {
//            binding.tvMyWishlist.setOnClickListener(v -> {
////                BottomNavigationView bottomNavigationView;
////                bottomNavigationView = (BottomNavigationView) requireActivity().findViewById(R.id.nav_view);
////                bottomNavigationView.setSelectedItemId(R.id.navigation_wishlist);
//                startActivity(new Intent(requireContext(), OrderQtyActivity.class));
//            });
//        }
//        binding.tvMyOrders.setOnClickListener(v -> startActivity(new Intent(requireContext(), MyOrdersActivity.class)));
//        binding.tvPayments.setOnClickListener(v -> startActivity(new Intent(requireContext(), MyPaymentsActivity.class)));
//    }
//
//
//    private void showDialog() {
//        // Create a custom dialog
//        final Dialog dialog = new Dialog(requireContext());
//        dialog.setContentView(R.layout.profile_edit_dialog);
//        dialog.setCancelable(true);
//
//
//        // Set the dialog's window width to match_parent
//        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
//        layoutParams.copyFrom(dialog.getWindow().getAttributes());
//        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
//        dialog.getWindow().setAttributes(layoutParams);
//
//        // Get references to the UI elements in the custom dialog
//        EditText editTextName = dialog.findViewById(R.id.editTextName);
//        EditText editTextEmail = dialog.findViewById(R.id.editTextEmail);
//        EditText editTextCity = dialog.findViewById(R.id.editTextCity);
//
//        PowerSpinnerView editTextState = dialog.findViewById(R.id.editTextState);
//        PowerSpinnerView editTextDistrict = dialog.findViewById(R.id.editTextDistrict);
//
//        Button buttonSubmit = dialog.findViewById(R.id.btn_save);
//
//        getDropDownData(editTextState,editTextDistrict);
//        // Add click listener to the Submit button
//        editTextName.setText(UserDetails.name);
//        editTextEmail.setText(UserDetails.email);
//        editTextCity.setText(UserDetails.city);
//        editTextState.setText(UserDetails.state);
//        editTextDistrict.setText(UserDetails.district);
//        buttonSubmit.setOnClickListener(v -> {
//            // Check if any of the EditText fields are empty
//            boolean hasEmptyFields = false;
//            if (TextUtils.isEmpty(editTextName.getText())) {
//                editTextName.setError("Name cannot be empty");
//                hasEmptyFields = true;
//            }
//            if (TextUtils.isEmpty(editTextEmail.getText())) {
//                editTextEmail.setError("Email cannot be empty");
//                hasEmptyFields = true;
//            }
//            if (TextUtils.isEmpty(editTextCity.getText())) {
//                editTextCity.setError("City cannot be empty");
//                hasEmptyFields = true;
//            }
//            if (TextUtils.isEmpty(editTextState.getText())) {
//                editTextState.setError("State cannot be empty");
//                hasEmptyFields = true;
//            }
//            if (TextUtils.isEmpty(editTextDistrict.getText())) {
//                editTextDistrict.setError("District cannot be empty");
//                hasEmptyFields = true;
//            }
//
//            // If any EditText field is empty, return without dismissing the dialog
//            if (hasEmptyFields) {
//                return;
//            }
//            else
//            {
//                UserDetailsUpdate userDetailsUpdate = new UserDetailsUpdate(
//                        editTextName.getText().toString(),
//                        editTextEmail.getText().toString(),
//                        editTextCity.getText().toString(),
//                        editTextState.getText().toString(),
//                        editTextDistrict.getText().toString(),
//                        new UserDetailsUpdate.OnUserDetailsUpdateListener() {
//                    @Override
//                    public void onUserDetailsUpdate(String messaage) {
//                        requireActivity().runOnUiThread( () -> {
//                            try {
//                                Variables.name = editTextName.getText().toString();
//                                binding.txtId.setText(Variables.name);
//                                Toast.makeText(requireContext(), messaage, Toast.LENGTH_SHORT).show();
//                                dialog.dismiss();
//                                Loading.hide();
//                            }
//                            catch (Exception ignore)
//                            {
//                                Loading.hide();
//                            }
//
//                        } );
//
//                    }
//
//                    @Override
//                    public void onUserDetailsError(String error) {
//                        requireActivity().runOnUiThread( () -> {
//                            try {
//                                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show();
//                                dialog.dismiss();
//                                Loading.hide();
//                            }
//                            catch (Exception ignore)
//                            {
//                                Loading.hide();
//                            }
//                        } );
//
//                    }
//                });
//                userDetailsUpdate.HitUserDetailsUpdateApi();
//                Loading.show(requireContext());
//            }
//
//            // All fields are filled, you can handle the submit logic here
//            // For example, you can get the values from the EditText fields and save them
//            // to your profile.
//
//            dialog.dismiss(); // Close the dialog after processing
//        });
//
//
//        dialog.show();
//    }
//
//
//    @SuppressLint("StaticFieldLeak")
//    public void getDropDownData(PowerSpinnerView State , PowerSpinnerView District) {
//        new AsyncTask<Void, Void, JSONObject>() {
//            @Override
//            protected JSONObject doInBackground(Void... voids) {
//                FetchData fetchData = new FetchData("https://houseofspiritshyd.in/prakruthi/admin/api/getDropdownData");
//                if (fetchData.startFetch()) {
//                    if (fetchData.onComplete()) {
//                        String result = fetchData.getResult();
//                        Log.i("getDropDownData", result);
//                        try {
//                            return new JSONObject(result);
//                        } catch (JSONException e) {
//                            return null;
//                        }
//                    }
//                    return null;
//                }
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(JSONObject jsonObj) {
//                super.onPostExecute(jsonObj);
//
//                if (jsonObj != null) {
//                    try {
//
//                        JSONArray states = jsonObj.getJSONArray("state");
//                        ArrayList<String> stateNames = new ArrayList<>();
//                        for (int i = 0; i < states.length(); i++) {
//                            JSONObject state = states.getJSONObject(i);
//                            stateNames.add(state.getString("name"));
//                        }
//                        State.setItems(stateNames);
//
//
//                        JSONArray districts = jsonObj.getJSONArray("district");
//
//                        ArrayList<String> districtNames = new ArrayList<>();
//                        for (int i = 0; i < districts.length(); i++) {
//                            JSONObject districtt = districts.getJSONObject(i);
//                            districtNames.add(districtt.getString("name"));
//                        }
//                        District.setItems(districtNames);
//
//
//                    } catch (JSONException e) {
//                        Toast.makeText(requireContext(), "Network Error", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(requireContext(), "Network Error", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }.execute();
//    }
//
//
//
//
//    @Override
//    public void OnProfileItemFeedback(String description) {
//        try {
//            requireActivity().runOnUiThread( () -> {
//                Toast.makeText(requireContext(), description, Toast.LENGTH_SHORT).show();
//            } );
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//
//    }
//
//    @Override
//    public void OnProfileItemFeedbackAPiGivesError(String error) {
//        try {
//            requireActivity().runOnUiThread( () -> {
//                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show();
//            } );
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//
//    }
//
//    @Override
//    public void OnGetRecentViewProductsAPIGivesResult(ArrayList<RecentProductModel> recentProductModels) {
//        try {
//            requireActivity().runOnUiThread( () -> {
//                binding.ProfileHomeProductsRecycler.setLayoutManager(new GridLayoutManager(requireContext(),2));
//                binding.ProfileHomeProductsRecycler.setAdapter(new RecentProductAdaptor(recentProductModels));
//                binding.ProfileHomeProductsRecycler.hideShimmerAdapter();
//            } );
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//
//    }
//
//    @Override
//    public void OnGetRecentViewProductsAPIGivesError(String error) {
//        try {
//            requireActivity().runOnUiThread( () -> {
//                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show();
//                binding.ProfileHomeProductsRecycler.hideShimmerAdapter();
//                binding.tvRecentProducts.setVisibility(View.GONE);
//                binding.tvViewAll.setVisibility(View.GONE);
//            } );
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//
//    }
//}