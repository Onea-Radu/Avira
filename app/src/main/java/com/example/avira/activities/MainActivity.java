package com.example.avira.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.avira.R;
import com.example.avira.adapters.ProductsAdapter;
import com.example.avira.data.Product;
import com.example.avira.runnables.RgbColorRunnable;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static Handler handler = handler = new Handler(Looper.getMainLooper())
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case 1://change title rgb color
                    title.setTextColor((int)msg.obj);
                    break;
                default:
                    break;
            }
        }
    };
    public static TextView title;
    public GridView productGridView;
    public ProductsAdapter productsAdapter;
    public ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title = findViewById(R.id.title);
        new Thread(new RgbColorRunnable()).start();
        getProducts();
    }

    public void initUI() {
        productGridView = findViewById(R.id.productGridView);
        productsAdapter = new ProductsAdapter(this, products);
        productGridView.setAdapter(productsAdapter);
    }

    public void getProducts() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Products").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                products=new ArrayList<>();
                for (DocumentSnapshot ds : queryDocumentSnapshots.getDocuments()) {
                    products.add(new Product(ds.get("Name").toString(), Integer.parseInt(ds.get("Price").toString()), (String) ds.get("Img").toString()));
                }
                initUI();
            }
        });
    }
}
