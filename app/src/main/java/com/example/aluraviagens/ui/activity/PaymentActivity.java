package com.example.aluraviagens.ui.activity;

import static com.example.aluraviagens.ui.activity.ActivitiesContants.PAYMENT_ACTIVITY_TITLE;
import static com.example.aluraviagens.ui.activity.ActivitiesContants.TRAVEL_PACKAGE_EXTRA_KEY;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aluraviagens.R;
import com.example.aluraviagens.model.TravelPackage;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class PaymentActivity extends AppCompatActivity {
    private TravelPackage travelPackage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        setTitle(PAYMENT_ACTIVITY_TITLE);

        Intent prevIntent = getIntent();
        if (prevIntent.hasExtra(TRAVEL_PACKAGE_EXTRA_KEY)) {
            travelPackage = (TravelPackage) prevIntent.getSerializableExtra(TRAVEL_PACKAGE_EXTRA_KEY);
            getPrice();

            configButtonFinish();
        }
    }

    private void configButtonFinish() {
        Button buttonFinish = findViewById(R.id.button_finish_payment);
        buttonFinish.setOnClickListener(view -> {
            Intent goToPurchaseResume = new Intent(this, PurchaseResumeActivity.class);
            goToPurchaseResume.putExtra(TRAVEL_PACKAGE_EXTRA_KEY, travelPackage);
            startActivity(goToPurchaseResume);
        });
    }

    private void getPrice() {
        TextView price = findViewById(R.id.price_payment);
        NumberFormat formatter = DecimalFormat.getCurrencyInstance(new Locale("pt", "br"));
        price.setText(formatter.format(travelPackage.getPrice()));
    }
}