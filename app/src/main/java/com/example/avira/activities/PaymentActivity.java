package com.example.avira.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.avira.R;
import com.example.avira.data.Product;

public class PaymentActivity extends AppCompatActivity {

    public Product product;
    public ImageView productImageView;
    public TextView productDescriptionTextView;
    public Spinner paymentMethodDropMenu;
    public LinearLayout cashLayout;
    public EditText cardDetailsEditText;
    public Button payButton;
    public View space1;
    public View space2;
    public static TextView amountPayedTextView;
    public static TextView changeTextView;
    public ImageView[] dollarBills;
    public TextView[] dollarBillsNr;
    public int[] billsValues;
    public int[] billsCount;
    public static int ammountPaid;
    public int productPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        //get display screen size
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        //set the size and position of the activity
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.x = 0;
        params.height = (int)(height * 0.8);
        params.width = (int)(width * 0.9);
        params.y = 0;
        this.getWindow().setAttributes(params);
        this.setFinishOnTouchOutside(true);

        product = (Product) getIntent().getSerializableExtra("product");
        productPrice = product.getPrice();
        initUI();
    }
    public void initUI()
    {
        productImageView = findViewById(R.id.paymentProductImage);
        productDescriptionTextView = findViewById(R.id.productName);
        Glide.with(this).load(product.getImageUrl()).into(productImageView);
        productDescriptionTextView.setText(product.getName() + " price:" + product.getPrice() + " $");
        paymentMethodDropMenu = findViewById(R.id.paymentMethodSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.payment_methods, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paymentMethodDropMenu.setAdapter(adapter);
        paymentMethodDropMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0:
                        setCashLayout();
                        break;
                    case 1:
                        setCardLayout();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        cashLayout = findViewById(R.id.cashLayout);
        cardDetailsEditText = findViewById(R.id.cardDetailsEditText);
        payButton = findViewById(R.id.payButton);
        space1 = findViewById(R.id.space1);
        space2 = findViewById(R.id.space2);
        amountPayedTextView = findViewById(R.id.amountPayedTextView);
        changeTextView = findViewById(R.id.changeTextView);
        ImageView one_dollar_bill = findViewById(R.id.one_dollar_bill); ImageView five_dollar_bill = findViewById(R.id.five_dollar_bill);
        ImageView ten_dollar_bill = findViewById(R.id.ten_dollar_bill); ImageView twenty_dollar_bill = findViewById(R.id.twenty_dollar_bill);
        ImageView fifty_dollar_bill = findViewById(R.id.fifty_dollar_bill); ImageView one_hundred_dollar_bill = findViewById(R.id.one_hundred_dollar_bill);
        dollarBills = new ImageView[]{one_dollar_bill, five_dollar_bill, ten_dollar_bill, twenty_dollar_bill, fifty_dollar_bill, one_hundred_dollar_bill};
        TextView one_dollar_billNr = findViewById(R.id.one_dollar_billNr); TextView five_dollar_billNr = findViewById(R.id.five_dollar_billNr);
        TextView ten_dollar_billNr = findViewById(R.id.ten_dollar_billNr); TextView twenty_dollar_billNr = findViewById(R.id.twenty_dollar_billNr);
        TextView fifty_dollar_billNr = findViewById(R.id.fifty_dollar_billNr); TextView one_hundred_dollar_billNr = findViewById(R.id.one_hundred_dollar_billNr);
        dollarBillsNr = new TextView[]{one_dollar_billNr, five_dollar_billNr, ten_dollar_billNr, twenty_dollar_billNr, fifty_dollar_billNr, one_hundred_dollar_billNr};
        billsValues = new int[]{1, 5, 10, 20, 50, 100};
        billsCount = new int[6];
        for(ImageView imageView : dollarBills)
        {
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageView bill = (ImageView) v;
                    switch (bill.getId())
                    {
                        case R.id.one_dollar_bill:
                            dollarBillsNr[0].setText(++billsCount[0] + "");
                            break;
                        case R.id.five_dollar_bill:
                            dollarBillsNr[1].setText(++billsCount[1] + "");
                            break;
                        case R.id.ten_dollar_bill:
                            dollarBillsNr[2].setText(++billsCount[2] + "");
                            break;
                        case R.id.twenty_dollar_bill:
                            dollarBillsNr[3].setText(++billsCount[3] + "");
                            break;
                        case R.id.fifty_dollar_bill:
                            dollarBillsNr[4].setText(++billsCount[4] + "");
                            break;
                        case R.id.one_hundred_dollar_bill:
                            dollarBillsNr[5].setText(++billsCount[5] + "");
                            break;
                        default:
                            break;
                    }
                    ammountPaid = getAmmountPaid(billsValues, billsCount);
                    PaymentActivity.amountPayedTextView.setText("Amount payed: " + ammountPaid + " $");
                    if(ammountPaid >= productPrice)
                        giveChange(ammountPaid, productPrice);
                }
            });
        }
    }
    public void setCashLayout()
    {
        cashLayout.setVisibility(View.VISIBLE);
        cardDetailsEditText.setVisibility(View.GONE);
        space1.setVisibility(View.GONE);
        payButton.setVisibility(View.GONE);
        amountPayedTextView.setVisibility(View.VISIBLE);
        space2.setVisibility(View.GONE);
    }
    public void setCardLayout()
    {
        cashLayout.setVisibility(View.GONE);
        cardDetailsEditText.setVisibility(View.VISIBLE);
        space1.setVisibility(View.VISIBLE);
        payButton.setVisibility(View.VISIBLE);
        space2.setVisibility(View.VISIBLE);
        amountPayedTextView.setVisibility(View.GONE);
        changeTextView.setVisibility(View.GONE);
    }
    public void giveChange(int ammountPaid, int price)
    {
        int change = ammountPaid - price;
        String changeText = "Total change: " + change + "\n";
        int[] changeBillsCount = new int[6];
        for(int i = 5; i >= 0; i--) {
            changeBillsCount[i] += change / billsValues[i];
            change -= changeBillsCount[i] * billsValues[i];
            if(changeBillsCount[i] > 0)
                changeText += changeBillsCount[i] + " " + billsValues[i] + "$ bills ";
        }
        changeTextView.setVisibility(View.VISIBLE);
        changeTextView.setText(changeText);
        billsCount = new int[6];
    }
    public int getAmmountPaid(int[] billsValues, int[] billsCount)
    {
        int s = 0;
        for(int i = 0; i < 6; i++)
            s += billsCount[i] * billsValues[i];
        return s;
    }
}
