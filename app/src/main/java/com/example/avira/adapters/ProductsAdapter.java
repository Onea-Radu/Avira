package com.example.avira.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.avira.data.Product;
import com.example.avira.uiObjects.ProductView;

public class ProductsAdapter extends BaseAdapter {

    public Context context;
    public Product[] products;
    public ProductsAdapter(Context context, Product[] products)
    {
        this.context = context;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.length;
    }

    @Override
    public Object getItem(int position) {
        return products[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return new ProductView(context, products[position]);
    }
}
