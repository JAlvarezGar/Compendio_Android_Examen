package com.example.compendio2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.compendio2.camara.Camara;
import com.example.compendio2.internet.Internet;
import com.example.compendio2.musica.MP3;
import com.example.compendio2.video.Video;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void internet(View view) {

        Intent internetIntent= new Intent(this, Internet.class);
        startActivity(internetIntent);
    }

    public void camara(View view) {

        Intent camaraIntent= new Intent(this, Camara.class);
        startActivity(camaraIntent);
    }

    public void musica(View view) {

        Intent musicaIntent= new Intent(this, MP3.class);
        startActivity(musicaIntent);
    }

    public void video(View view) {

        Intent videoIntent= new Intent(this, Video.class);
        startActivity(videoIntent);

    }
}
