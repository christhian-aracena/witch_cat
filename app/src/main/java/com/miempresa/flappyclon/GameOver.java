package com.miempresa.flappyclon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameOver extends AppCompatActivity {
    MediaPlayer gameOverMusic;
   private  Button btnTryAgain, btnGoHome;
   private Intent intentTryAgain, intentGoHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);

        btnTryAgain = findViewById(R.id.btn_try_again);
        btnGoHome = findViewById(R.id.btn_home);
        intentGoHome = new Intent(GameOver.this, MainMenu.class);
        intentTryAgain = new Intent(GameOver.this, MainActivity.class);


        btnGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        if (gameOverMusic != null) {
            gameOverMusic.stop();
            gameOverMusic.release();
            gameOverMusic = null;
        }
                startActivity(intentGoHome);

            }
        });

        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (gameOverMusic != null) {
                    gameOverMusic.stop();
                    gameOverMusic.release();
                    gameOverMusic = null;
                }
                startActivity(intentTryAgain);
            }
        });

        gameOverMusic = new MediaPlayer();

        gameOverMusic = MediaPlayer.create(this, R.raw.game_over);
        gameOverMusic.start();



    }


    @Override
    protected void onStop() {
        super.onStop();
        if (gameOverMusic != null) {
            gameOverMusic.stop();
            gameOverMusic.release();
            gameOverMusic = null;
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (gameOverMusic != null) {
            gameOverMusic.stop();
            gameOverMusic.release();
            gameOverMusic = null;
        }
    }
}