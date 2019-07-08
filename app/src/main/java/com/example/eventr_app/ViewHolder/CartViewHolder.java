package com.example.eventr_app.ViewHolder;

import android.view.View;
import android.widget.TextView;

import com.example.eventr_app.Interfaces.ItemClickListner;
import com.example.eventr_app.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtEventName, txtEventPrice, txtEventQuantity;
    private ItemClickListner itemClickListner;

    public CartViewHolder( View itemView)
    {
        super(itemView);

        txtEventName = itemView.findViewById(R.id.cart_event_name);
        txtEventPrice = itemView.findViewById(R.id.cart_event_price);
        txtEventQuantity = itemView.findViewById(R.id.cart_event_quantity);
    }

    @Override
    public void onClick(View v)
    {
        itemClickListner.onClick(v, getAdapterPosition(),false );
    }

    public void setItemClickListner(ItemClickListner itemClickListner)
    {
        this.itemClickListner = itemClickListner;
    }
}
