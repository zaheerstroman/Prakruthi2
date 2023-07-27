package com.prakruthi.ui.ui.profile.order_qty;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.prakruthi.R;
import com.prakruthi.ui.ui.profile.myorders.MyOrdersAdaptor;
import com.prakruthi.ui.ui.profile.myorders.MyOrdersModal;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class OrdersQtyAdaptor extends RecyclerView.Adapter<OrdersQtyAdaptor.ViewHolder> {

    ArrayList<OrdersQtyModal> ordersQtys;

    public OrdersQtyAdaptor(ArrayList<OrdersQtyModal> ordersQtys){
        this.ordersQtys = ordersQtys;
    }


    @NonNull
    @Override
    public OrdersQtyAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myorders_qty_listlayout, parent, false);
        return new OrdersQtyAdaptor.ViewHolder(view);
//        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersQtyAdaptor.ViewHolder holder, int position) {
        OrdersQtyModal ordersQtyModal = ordersQtys.get(position);

        holder.MyOrdersQtyProductName.setText(ordersQtyModal.getName());
        holder.MyOrdersQtyProductSubInformation.setText(ordersQtyModal.getProduct_id());

        Glide.with(holder.itemView.getContext())
                .load(ordersQtyModal.getAttachment())
                .placeholder(R.drawable.baseline_circle_24)
                .into(holder.MyOrdersQtyProductImage);

        holder.QuantityEditText.setText(ordersQtyModal.getQuantity());




    }

    @Override
    public int getItemCount() {
//        return 0;
        return ordersQtys.size();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView MyOrdersQtyProductImage;
        TextView MyOrdersQtyProductName,MyOrdersQtyProductSubInformation;
        TextView MyOrdersQtyProductNextBtn;
        EditText QuantityEditText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            MyOrdersQtyProductImage = itemView.findViewById(R.id.MyOrdersQtyProductImage);
            MyOrdersQtyProductName = itemView.findViewById(R.id.MyOrdersQtyProductName);
            MyOrdersQtyProductSubInformation = itemView.findViewById(R.id.MyOrdersQtyProductSubInformation);
            MyOrdersQtyProductNextBtn = itemView.findViewById(R.id.MyOrdersQtyProductNextBtn);
            QuantityEditText = itemView.findViewById(R.id.QuantityEditText);
            MyOrdersQtyProductName.setSelected(true);
        }
    }


}

