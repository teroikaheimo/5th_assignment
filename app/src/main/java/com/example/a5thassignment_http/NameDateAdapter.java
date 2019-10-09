package com.example.a5thassignment_http;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NameDateAdapter extends ArrayAdapter {
    private List<NameDate> rowItems;

    public NameDateAdapter(Context context, ArrayList<NameDate> dates) {
        super(context, 0, dates);
        this.rowItems = dates;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the item to be displayed
        final NameDate rowItem = rowItems.get(position);

        // IF null then inflate the layout. Otherwise just update layout contents.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Get the layout components
        TextView datetimeText = convertView.findViewById(R.id.text_date);

        // Set view text to Date string
        datetimeText.setText(rowItem.getDateString());


        return convertView;
    }
}
