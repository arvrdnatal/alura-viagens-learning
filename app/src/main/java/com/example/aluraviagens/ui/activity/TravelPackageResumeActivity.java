package com.example.aluraviagens.ui.activity;

import static com.example.aluraviagens.ui.activity.ActivitiesContants.TRAVEL_PACKAGE_EXTRA_KEY;
import static com.example.aluraviagens.ui.activity.ActivitiesContants.TRAVEL_PACKAGE_RESUME_ACTIVITY_TITLE;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aluraviagens.R;
import com.example.aluraviagens.model.TravelPackage;
import com.example.aluraviagens.util.DaysUtil;
import com.example.aluraviagens.util.ResourcesUtil;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TravelPackageResumeActivity extends AppCompatActivity {
    private TravelPackage travelPackage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_package_resume);
        setTitle(TRAVEL_PACKAGE_RESUME_ACTIVITY_TITLE);

        Intent prevIntent = getIntent();
        if (prevIntent.hasExtra(TRAVEL_PACKAGE_EXTRA_KEY)) {
            travelPackage = (TravelPackage) prevIntent.getSerializableExtra(TRAVEL_PACKAGE_EXTRA_KEY);
            setLocalName();
            setImage();
            setDays();
            setPrice();
            setDate();

            configButtonMakePayment();
        }
    }

    private void configButtonMakePayment() {
        Button buttonMakePayment = findViewById(R.id.button_make_payment);
        buttonMakePayment.setOnClickListener((View view) -> {
            Intent goToPayment = new Intent(this, PaymentActivity.class);
            goToPayment.putExtra(TRAVEL_PACKAGE_EXTRA_KEY, travelPackage);
            startActivity(goToPayment);
        });
    }

    private void setDate() {
        TextView date = findViewById(R.id.date_package);
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

    private void setPrice() {
        TextView price = findViewById(R.id.price_package);
        NumberFormat formatter = DecimalFormat.getCurrencyInstance(new Locale("pt", "br"));
        price.setText(formatter.format(travelPackage.getPrice()));
    }

    private void setDays() {
        TextView days = findViewById(R.id.days_package);
        String numberOfDays = DaysUtil.formatToString(travelPackage.getDays());
        days.setText(numberOfDays);
    }

    private void setImage() {
        ImageView image = findViewById(R.id.image_package);
        Drawable travelPackageDrawable = ResourcesUtil.getDrawable(this, travelPackage.getImage());
        image.setImageDrawable(travelPackageDrawable);
    }

    private void setLocalName() {
        TextView localName = findViewById(R.id.local_name_package);
        localName.setText(travelPackage.getPlace());
    }
}