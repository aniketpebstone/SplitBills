package org.aniket.splitbills.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import org.aniket.splitbills.R;
import org.aniket.splitbills.model.Person;

import java.util.List;

public class SpinnerAdapter<T> extends ArrayAdapter<T> {
    LayoutInflater layoutInflater;

    public SpinnerAdapter(Activity context, int resouceId, int textviewId, List<T> list) {
        super(context, resouceId, textviewId, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return rowview(convertView, position);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return rowview(convertView, position);
    }

    private View rowview(View convertView, int position) {
        T rowItem = getItem(position);
        viewHolder holder;
        View rowview = convertView;
        if (rowview == null) {
            holder = new viewHolder();
            layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowview = layoutInflater.inflate(R.layout.spinner_list_item, null, false);
            holder.txtTitle = rowview.findViewById(R.id.tv_name);
            rowview.setTag(holder);
        } else {
            holder = (viewHolder) rowview.getTag();
        }
        if (rowItem instanceof Person)
            holder.txtTitle.setText(((Person) rowItem).getName());

        return rowview;
    }


    private class viewHolder {
        TextView txtTitle;
    }
}