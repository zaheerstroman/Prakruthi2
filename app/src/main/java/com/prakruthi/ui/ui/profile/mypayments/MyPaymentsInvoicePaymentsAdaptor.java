package com.prakruthi.ui.ui.profile.mypayments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.prakruthi.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyPaymentsInvoicePaymentsAdaptor extends RecyclerView.Adapter<MyPaymentsInvoicePaymentsAdaptor.ViewHolder> {

    //    List<MyPaymentsInvoiceProductModel> myPaymentsInvoiceProductModels;
    ArrayList<MyPaymentsInvoiceProductModel> myPaymentsInvoiceProductModels;

    //    public MyPaymentsInvoicePaymentsAdaptor(List<MyPaymentsInvoiceProductModel> myPaymentsInvoiceProductModels) {
    public MyPaymentsInvoicePaymentsAdaptor(ArrayList<MyPaymentsInvoiceProductModel> myPaymentsInvoiceProductModels) {

        this.myPaymentsInvoiceProductModels = myPaymentsInvoiceProductModels;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return null;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mypayments_invoiceproductslistlayout, parent, false);
        return new MyPaymentsInvoicePaymentsAdaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        MyPaymentsInvoiceProductModel myPaymentsInvoiceProductModel = myPaymentsInvoiceProductModels.get(position);

        holder.MyPaymentsProductInvoiceProductsName.setText("# Invoice Number" + myPaymentsInvoiceProductModel.invoice_id);

        holder.MyPaymentsProductInvoicePaymentDateInformation.setText(myPaymentsInvoiceProductModel.getLogged_date());

        Glide.with(holder.itemView.getContext())
                .load(myPaymentsInvoiceProductModel.getAttachment())
                .placeholder(R.drawable.baseline_circle_24)
                .into(holder.MyPaymentsProductInvoiceProductsImage);

    }

    @Override
    public int getItemCount() {
//        return 0;
        return myPaymentsInvoiceProductModels.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView MyPaymentsProductImage, MyPaymentsProductInvoiceProductsImage;
        TextView MyPaymentsProductInvoiceProductsName, MyPaymentsProductInvoicePaymentDateInformation;
        ImageButton MyPaymentsProductInvoicePaymentsNextBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            MyPaymentsProductInvoiceProductsImage = itemView.findViewById(R.id.MyPaymentsProductInvoiceProductsImage);
            MyPaymentsProductInvoiceProductsName = itemView.findViewById(R.id.MyPaymentsProductInvoiceProductsName);
            MyPaymentsProductInvoicePaymentDateInformation = itemView.findViewById(R.id.MyPaymentsProductInvoicePaymentDateInformation);
            MyPaymentsProductInvoicePaymentsNextBtn = itemView.findViewById(R.id.MyPaymentsProductInvoicePaymentsNextBtn);
        }
    }

}
