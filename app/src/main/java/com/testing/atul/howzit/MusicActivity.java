package com.testing.atul.howzit;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MusicActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        String[] punch = extractFile().split(";");

        ListView mainList = (ListView)findViewById(R.id.music_list);
        MusicAdapter adapter = new MusicAdapter(this,punch);
        mainList.setAdapter(adapter);
    }

    private String extractFile(){
        BufferedReader reader = null;
        StringBuilder text = new StringBuilder();

        try {
            reader = new BufferedReader(
                    new InputStreamReader(getResources().openRawResource(R.raw.punch)));

            String mLine;
            while ((mLine = reader.readLine()) != null) {
                text.append(mLine);
                text.append('\n');
            }
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(),"Error reading file!",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return text.toString();
    }
}

class MusicAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] song;

    MusicAdapter(Context context, String[] song) {
        super(context,-1,song);
        this.context = context;
        this.song = song;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView;
        rowView = inflater.inflate(R.layout.music_list_element, parent, false);

        TextView songa = (TextView)rowView.findViewById(R.id.music_name);
        songa.setText(song[position]);

        return rowView;
    }

}