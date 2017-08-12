package com.testing.atul.howzit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.PopupMenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public class ListActivity extends Activity implements Filterable{

    String[] fullMovieDet;
    String[] filteredMovieDet;
    ListView mainList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mainList = (ListView)findViewById(R.id.main_list);
        try {
            extractFile();
        }catch(IOException e){
            e.printStackTrace();
        }
        adapter = new CustomAdapter(this,fullMovieDet);
        mainList.setAdapter(adapter);
    }

    public void extractFile() throws IOException{
        String temp;
        StringBuilder buf = new StringBuilder();
        InputStream inputStream = this.getResources().openRawResource(R.raw.movie_list);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        while ((temp = reader.readLine()) != null) {
            buf.append(temp);
        }
        inputStream.close();
        temp = buf.toString();
        fullMovieDet = temp.split(";");
    }

    public void filter(View v){
        PopupMenu popupMenu = new PopupMenu(this,v);
        popupMenu.inflate(R.menu.filter_popup);
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.m_dir:
                        Log.i("thrummed","director");
                        getFilter().filter("Suresh Krishna");
                        break;
                    case R.id.m_lang:
                        Log.i("thrummed","lang");
                        break;
                }
                return false;
            }
        });
    }

    public void sort(View v){
        AlertDialog dialog;
        CharSequence[] items = {"Name","Year","Director"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sort By");
        builder.setSingleChoiceItems(items,-1,new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, final int item) {
                if(item!=1) {
                    adapter.sort(new Comparator<String>() {
                        @Override
                        public int compare(String arg0, String arg1) {
                            String part0 = arg0.split(",")[item];
                            String part1 = arg1.split(",")[item];
                            return part0.compareTo(part1);
                        }
                    });
                    adapter.notifyDataSetChanged();
                }
                else{
                    final DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
                    adapter.sort(new Comparator<String>() {
                        @Override
                        public int compare(String arg0, String arg1) {
                            try {
                                String part0 = arg0.split(",")[item];
                                Date d0 = format.parse(part0);
                                String part1 = arg1.split(",")[item];
                                Date d1 = format.parse(part1);
                                return d0.compareTo(d1);
                            }
                            catch(ParseException e){
                                //TODO Vyom
                            }
                            return arg0.compareTo(arg1);
                        }
                    });
                    adapter.notifyDataSetChanged();
                }
                dialog.dismiss();
            }
        });
        dialog = builder.create();
        dialog.show();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredMovieDet = (String[])results.values;
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();
                ArrayList<String> FilteredArrayNames = new ArrayList<>();

                // perform your search here using the searchConstraint String.

                constraint = constraint.toString().toLowerCase();
                for (String dataNames : fullMovieDet) {
                    if (dataNames.toLowerCase().startsWith(constraint.toString())) {
                        FilteredArrayNames.add(dataNames);
                    }
                }

                results.count = FilteredArrayNames.size();
                results.values = FilteredArrayNames;
                Log.e("VALUES", results.values.toString());

                return results;
            }
        };
    }
}
