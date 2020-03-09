package com.example.avira.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.avira.activities.PaymentActivity;
import com.example.avira.data.Product;
import com.example.avira.uiObjects.ProductView;

import java.util.ArrayList;

public class ProductsAdapter extends BaseAdapter {

    public Context context;
    public ArrayList<Product> products;
    public ProductsAdapter(Context context, ArrayList<Product> products)
    {
        this.context = context;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        ProductView productView = new ProductView(context, products.get(position));
        productView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PaymentActivity.class);
                intent.putExtra("product", products.get(position));
                context.startActivity(intent);
            }
        });
        return productView;
    }
}
