package com.example.aluraviagens.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aluraviagens.R;
import com.example.aluraviagens.model.TravelPackage;
import com.example.aluraviagens.util.DaysUtil;
import com.example.aluraviagens.util.ResourcesUtil;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class TravelPackagesListAdapter extends BaseAdapter {
    private final Context context;
    private final List<TravelPackage> travelPackages;

    public TravelPackagesListAdapter(Context context, List<TravelPackage> travelPackages) {
        this.context = context;
        this.travelPackages = travelPackages;
    }

    @Override
    public int getCount() {
        return travelPackages.size();
    }

    @Override
    public TravelPackage getItem(int position) {
        return travelPackages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_travel_package, parent, false);
        } else {
            view = convertView;
        }

        TravelPackage travelPackage = travelPackages.get(position);

        TextView name = view.findViewById(R.id.local_name);
        name.setText(travelPackage.getPlace());

        ImageView image = view.findViewById(R.id.local_image);
        Drawable imagePackage = ResourcesUtil.getDrawable(context, travelPackage.getImage());
        image.setImageDrawable(imagePackage);

        TextView days = view.findViewById(R.id.local_days);
        String numberOfDays = DaysUtil.formatToString(travelPackage.getDays());
        days.setText(numberOfDays);

        TextView price = view.findViewById(R.id.local_price);
        NumberFormat formatter = DecimalFormat.getCurrencyInstance(new Locale("pt", "br"));
        price.setText(formatter.format(travelPackage.getPrice()));

        return view;
    }
}
