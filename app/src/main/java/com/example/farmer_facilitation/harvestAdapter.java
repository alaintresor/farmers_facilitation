package com.example.farmer_facilitation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class harvestAdapter extends ArrayAdapter<Harvests> {
    Context context;
    int resource;
    List<Harvests> Harvests;
    public harvestAdapter(@NonNull Context context, int resource, List<Harvests> Harvests) {
        super(context, resource, Harvests);
        this.context=context;
        this.Harvests=Harvests;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(resource, null, false);
        TextView date=view.findViewById(R.id.Hdate);
        TextView harvest=view.findViewById(R.id.har);
        TextView price=view.findViewById(R.id.Uprice);
        TextView quantity=view.findViewById(R.id.quantityHere);
        Harvests harvests=Harvests.get(position);
        date.setText(harvests.getDate());
        harvest.setText(harvests.getHarvest());
        price.setText(harvests.getUprice());
        quantity.setText(harvests.getQuantity());
        return view;
    }
}
