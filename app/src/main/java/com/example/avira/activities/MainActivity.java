package com.example.avira.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.example.avira.R;
import com.example.avira.adapters.ProductsAdapter;
import com.example.avira.data.Product;

public class MainActivity extends AppCompatActivity {

    public GridView productGridView;
    public ProductsAdapter productsAdapter;
    public Product[] products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    public void initUI() {
        productGridView = findViewById(R.id.productGridView);
        productsAdapter = new ProductsAdapter(this, getProducts());
        productGridView.setAdapter(productsAdapter);
    }

    public Product[] getProducts()
    {
        return new Product[]{
                new Product("Avira Prime", 75, "https://sisecure.com/wp-content/uploads/2018/11/Review-of-Avira-Prime.png"),
                new Product("Antivirus Pro", 35, "https://assets.prod.cms.avira.com/cache-buster-1568375808/tmp/image-thumbnails/products/cms/boxshots/en/image-thumb__231__width960original/avira-antivirus-pro.png"),
                new Product("Phantom VPN", 50, "https://images-na.ssl-images-amazon.com/images/I/61dmK6UI0SL._AC_.jpg"),
                new Product("Password Manager", 20, "https://assets.prod.cms.avira.com/cache-buster-1562840440/tmp/image-thumbnails/products/cms/boxshots/en/image-thumb__1387__width145f/avira-password-manager-pro.png"),
                new Product("Optimizer", 10, "https://sanet.pics/storage-4/0418/95Xy87DPgR5kLAh7mZmMOVhmQWQna6Jk.jpg"),
                new Product("System Speedup", 25, "https://www.electropc.ro/images/detailed/6/650x650-system-speedup-pro.png"),
        };
    }
}
