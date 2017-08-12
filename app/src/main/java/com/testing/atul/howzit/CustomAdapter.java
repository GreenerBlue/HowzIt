package com.testing.atul.howzit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


class CustomAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] fullDet;

    CustomAdapter(Context context, String[] fullDet){
        super(context,-1, fullDet);
        this.context = context;
        this.fullDet = fullDet;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_element, parent, false);

        TextView filmName = (TextView) rowView.findViewById(R.id.list_film_name);
        filmName.setText(fullDet[position].split(",")[0]);
        TextView filmYear = (TextView) rowView.findViewById(R.id.list_film_year);
        filmYear.setText(fullDet[position].split(",")[1]);
        TextView filmDir = (TextView) rowView.findViewById(R.id.list_film_dir);
        filmDir.setText(fullDet[position].split(",")[2]);
        TextView filmChar = (TextView) rowView.findViewById(R.id.list_char_name);
        filmChar.setText(fullDet[position].split(",")[3]);
        TextView filmLang = (TextView) rowView.findViewById(R.id.list_film_lang);
        filmLang.setText(fullDet[position].split(",")[4]);

        return rowView;
    }

}
