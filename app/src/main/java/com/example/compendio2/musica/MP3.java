package com.example.compendio2.musica;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.compendio2.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class MP3 extends Activity implements Button.OnClickListener {

    TextView tv1;
    Button bt1, bt2, bt3, bt4, bt5;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp3);

        tv1 = (TextView) findViewById(R.id.textView);
        bt1 = (Button) findViewById(R.id.play);
        bt2 = (Button) findViewById(R.id.pausa);
        bt3 = (Button) findViewById(R.id.stop);
        bt4 = (Button) findViewById(R.id.power);
        bt5 = (Button) findViewById(R.id.escoger);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.escoger:



                /**
                 AnimatorSet animaBoton= new AnimatorSet();
                 ObjectAnimator fader= ObjectAnimator.ofFloat(bt5,"alpha",0f,1f);
                 fader.setDuration(3000);
                 animaBoton.play(fader);
                 animaBoton.start();
                 Intent escoge= new Intent(this,EscogerTema.class);
                 startActivity(escoge);*/

                break;

            case R.id.play:
                //Bundle datos = this.getIntent().getExtras();
                //String tema = datos.getString("cancion" );
                mediaPlayer = MediaPlayer.create(this, R.raw.musica);
                mediaPlayer.start();
                break;

            case R.id.pausa:
                int posicion = mediaPlayer.getCurrentPosition();
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                } else {
                    mediaPlayer.seekTo(posicion);
                    mediaPlayer.start();
                }
                break;

            case R.id.stop:
                mediaPlayer.stop();
                break;

            case R.id.power:
                mediaPlayer.stop();

        }


    }


}
