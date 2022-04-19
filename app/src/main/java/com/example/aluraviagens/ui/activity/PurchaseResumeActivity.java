package com.example.aluraviagens.ui.activity;

import static com.example.aluraviagens.ui.activity.ActivitiesContants.PURCHASE_RESUME_ACTIVITY_TITLE;
import static com.example.aluraviagens.ui.activity.ActivitiesContants.TRAVEL_PACKAGE_EXTRA_KEY;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aluraviagens.R;
import com.example.aluraviagens.model.TravelPackage;
import com.example.aluraviagens.util.ResourcesUtil;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PurchaseResumeActivity extends AppCompatActivity {
    private TravelPackage travelPackage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_resume);
        setTitle(PURCHASE_RESUME_ACTIVITY_TITLE);

        Intent prevIntent = getIntent();
        if (prevIntent.hasExtra(TRAVEL_PACKAGE_EXTRA_KEY)) {
            travelPackage = (TravelPackage) prevIntent.getSerializableExtra(TRAVEL_PACKAGE_EXTRA_KEY);
            setImage();
            setLocalName();
            setDate();
            setPrice();
        }
    }

    private void setPrice() {
        TextView price = findViewById(R.id.price_purchase);
        NumberFormat formatter = DecimalFormat.getCurrencyInstance(new Locale("pt", "br"));
        price.setText(formatter.format(travelPackage.getPrice()));
    }

    private void setDate() {
        TextView date = findViewById(R.id.date_purchase);
        Calendar dateGo = Calendar.getInstance();
        Calendar dateReturn = Calendar.getInstance();
        dateReturn.add(Calendar.DATE, travelPackage.getDays());
        SimpleDateFormat brazilianDate = new SimpleDateFormat("dd/MM", Locale.getDefault());
        String dateGoString = brazilianDate.format(dateGo.getTime());
        String dateReturnString = brazilianDate.format(dateReturn.getTime());
        int yearGo = dateGo.get(Calendar.YEAR);
        int yearReturn = dateReturn.get(Calendar.YEAR);
        String allDate = dateGoString + " - " + dateReturnString + " de " + yearGo + (yearGo != yearReturn ? "/" + yearReturn : "");
        date.setText(allDate);
    }

    private void setLocalName() {
        TextView localName = findViewById(R.id.local_name_purchase);
        localName.setText(travelPackage.getPlace());
    }

    private void setImage() {
        ImageView image = findViewById(R.id.image_purchase);
        Drawable travelPackageDrawable = ResourcesUtil.getDrawable(this, travelPackage.getImage());
        image.setImageDrawable(travelPackageDrawable);
    }
}