package com.prakruthi.ui.ui.profile.mypayments;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prakruthi.R;
import com.prakruthi.ui.Variables;
import com.prakruthi.ui.ui.productPage.ProductPage;
import com.prakruthi.ui.ui.profile.myorders.MyOrdersAdaptor;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyPaymentsAdaptor extends RecyclerView.Adapter<MyPaymentsAdaptor.ViewHolder> {

    //    List<MyPaymentsModal> myPaymentsModals;
    ArrayList<MyPaymentsModal> myPaymentsModals;


//    public MyPaymentsAdaptor(List<MyPaymentsModal> myPaymentsModals) {
//        this.myPaymentsModals = myPaymentsModals;
//    }

    public MyPaymentsAdaptor(ArrayList<MyPaymentsModal> myPaymentsModals) {
        this.myPaymentsModals = myPaymentsModals;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mypaymentslistlayout, parent, false);
        return new MyPaymentsAdaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        MyPaymentsModal myPaymentsModal = myPaymentsModals.get(position);

        holder.MyPaymentsProductName.setText("Invoice # " + myPaymentsModal.invoice_id);
        holder.MyPaymentsProductSubInformation.setText(myPaymentsModal.getPayment_date());

        holder.itemView.setOnClickListener(v -> {
            holder.itemView.getContext().startActivity(new Intent(holder.itemView.getContext(), MyPaymentsInvoiceProductsActivity.class)
//                    .putExtra("invoice_id",String.valueOf(myPaymentsModal.getInvoice_id())));
                    .putExtra("invoiceId", String.valueOf(myPaymentsModal.getInvoice_id())));

        });


    }

    @Override
    public int getItemCount() {
        return myPaymentsModals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView MyPaymentsProductImage;
        TextView MyPaymentsProductName, MyPaymentsProductSubInformation;
        ImageButton MyPaymentsProductNextBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            MyPaymentsProductImage = itemView.findViewById(R.id.MyPaymentsProductImage);
            MyPaymentsProductName = itemView.findViewById(R.id.MyPaymentsProductName);
            MyPaymentsProductSubInformation = itemView.findViewById(R.id.MyPaymentsProductSubInformation);
            MyPaymentsProductNextBtn = itemView.findViewById(R.id.MyPaymentsProductNextBtn);
        }
    }
}
