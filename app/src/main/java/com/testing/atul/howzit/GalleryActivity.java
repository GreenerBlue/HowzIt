package com.testing.atul.howzit;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class GalleryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        GridView gallery = (GridView)findViewById(R.id.gallery_grid);
        int[] list = {R.drawable.ic_gallery,R.drawable.ic_list,
                R.drawable.ic_notes,R.drawable.ic_music};
        GridAdapter adapter = new GridAdapter(this,list);
        gallery.setAdapter(adapter);
    }
}

class GridAdapter extends BaseAdapter{

    private Context context;
    private final int[] images;

    GridAdapter(Context context, int[] images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {

            LayoutInflater infl = LayoutInflater.from(context);
            convertView = infl.inflate(R.layout.gallery_grid_element, parent, false);
        }
        ImageView imageView = (ImageView)convertView.findViewById(R.id.gallery_image);
        imageView.setImageResource(images[position]);

        return convertView;
    }
}