package com.miempresa.flappyclon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Winner extends AppCompatActivity {
    private int nCountCoin;
    private TextView nCount;
    private MediaPlayer mediaPlayer;
    private Button btnTryAgain, btnGoHome;
    private Intent intentTryAgain, intentGoHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        btnTryAgain = findViewById(R.id.btn_try_again);
        btnGoHome = findViewById(R.id.btn_home);
        intentGoHome = new Intent(Winner.this, MainMenu.class);
        intentTryAgain = new Intent(Winner.this, MainActivity.class);

        mediaPlayer = new MediaPlayer();

        mediaPlayer = MediaPlayer.create(this, R.raw.win_sound);
        mediaPlayer.start();


        nCount = findViewById(R.id.nCountCoin);





        Intent intent = getIntent();
        nCountCoin = intent.getIntExtra("coins",0);

        nCount.setText("Monedas recogidas: "+nCountCoin);



        btnGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                }
                startActivity(intentGoHome);

            }
        });

        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                }
                startActivity(intentTryAgain);
            }
        });

    }
}