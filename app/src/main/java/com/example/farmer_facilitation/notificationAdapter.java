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

public class notificationAdapter extends ArrayAdapter<MyNotifications> {

    Context context;
    int resource;
    List<MyNotifications> MyNotifications;
    public notificationAdapter(Context context, int resource, List<MyNotifications> MyNotifications)
    {
        super(context,resource,MyNotifications);
        this.context=context;
        this.resource=resource;
        this.MyNotifications=MyNotifications;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(resource, null, false);
        TextView date=view.findViewById(R.id.date);
        TextView subject=view.findViewById(R.id.subject);
        final MyNotifications myNotifications=MyNotifications.get(position);
        subject.setText(myNotifications.getSubject());
        date.setText(myNotifications.getDate());
        return view;
    }
}
