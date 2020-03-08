package com.example.avira.uiObjects;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.example.avira.R;
import com.example.avira.data.Product;

public class ProductView extends CardView
{
    public Product product;
    public ImageView productImageView;
    public TextView productDescriptionTextView;
    public ProductView(Context context, AttributeSet attributeSet) { super(context, attributeSet); }
    public ProductView(@NonNull Context context, Product product) {
        super(context);
        this.product = product;
        inflate(context, R.layout.product_layout, this);
        this.productImageView = findViewById(R.id.productImage);
        this.productDescriptionTextView = findViewById(R.id.productDescription);
        populateProductView(product);
    }
    public void populateProductView(Product product)
    {
        Glide.with(this).load(product.getImageUrl()).into(productImageView);
        productDescriptionTextView.setText(product.getName() + " " + product.getPrice() + "$");
    }
}
