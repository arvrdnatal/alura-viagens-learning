package com.example.aluraviagens.ui.activity;

import static com.example.aluraviagens.ui.activity.ActivitiesContants.TRAVEL_PACKAGES_LIST_ACTIVITY_TITLE;
import static com.example.aluraviagens.ui.activity.ActivitiesContants.TRAVEL_PACKAGE_EXTRA_KEY;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aluraviagens.R;
import com.example.aluraviagens.dao.TravelPackageDAO;
import com.example.aluraviagens.model.TravelPackage;
import com.example.aluraviagens.ui.adapter.TravelPackagesListAdapter;

import java.util.List;

public class TravelPackagesListActivity extends AppCompatActivity {
    private List<TravelPackage> travelPackageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_packages_list);
        setTitle(TRAVEL_PACKAGES_LIST_ACTIVITY_TITLE);

        TravelPackageDAO dao = new TravelPackageDAO();
        travelPackageList = dao.list();

        configTravelPackagesList();
    }

    private void configTravelPackagesList() {
        ListView travelPackageListView = findViewById(R.id.list_travel_packages);
        travelPackageListView.setAdapter(new TravelPackagesListAdapter(this, travelPackageList));
        travelPackageListView.setOnItemClickListener((parent, view, position, id) -> {
            TravelPackage travelPackage = travelPackageList.get(position);
            Intent goToResume = new Intent(this, TravelPackageResumeActivity.class);
            goToResume.putExtra(TRAVEL_PACKAGE_EXTRA_KEY, travelPackage);
            startActivity(goToResume);
        });
    }
}