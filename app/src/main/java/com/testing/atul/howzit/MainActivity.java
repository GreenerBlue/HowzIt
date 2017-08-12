package com.testing.atul.howzit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setContentView(R.layout.grid_main);

        ImageButton button1 = (ImageButton)findViewById(R.id.imageButton1);
        button1.setOnClickListener(this);
        ImageButton button2 = (ImageButton)findViewById(R.id.imageButton2);
        button2.setOnClickListener(this);
        ImageButton button3 = (ImageButton)findViewById(R.id.imageButton3);
        button3.setOnClickListener(this);
        ImageButton button4 = (ImageButton)findViewById(R.id.imageButton4);
        button4.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch(v.getId()){
            case R.id.imageButton1:
                Toast.makeText(getApplicationContext(),"List button", Toast.LENGTH_SHORT).show();
                intent = new Intent(this,ListActivity.class);
                startActivity(intent);
                break;

            case R.id.imageButton2:
                Toast.makeText(getApplicationContext(),"Music button", Toast.LENGTH_SHORT).show();
                intent = new Intent(this,MusicActivity.class);
                startActivity(intent);
                break;

            case R.id.imageButton3:
                Toast.makeText(getApplicationContext(),"Gallery button", Toast.LENGTH_SHORT).show();
                /*
                intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, RESULT_CANCELED); */
                intent = new Intent(this,GalleryActivity.class);
                startActivity(intent);

                break;

            case R.id.imageButton4:
                Toast.makeText(getApplicationContext(),"Notes button", Toast.LENGTH_SHORT).show();
                intent = new Intent(this,NotesActivity.class);
                startActivity(intent);
                break;
        }

    }
}
