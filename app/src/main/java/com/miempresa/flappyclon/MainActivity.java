package com.miempresa.flappyclon;

import static java.security.AccessController.getContext;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    DisplayMetrics sizeMobile;
    private ImageView character;
    private RelativeLayout gameLayout;
    private int birdY;
    private int birdSpeed;
    private boolean gameStarted = false;
    private static final int SET_CHARACTER_POSITION_X = 300;
    private static final int SET_CHARACTER_POSITION_Y = 100;
    private ImageView backgroundImage;
    private ImageView backgroundImage2;
    private ImageView backgroundImage3;
    private int screenWidth;
    private int currentPosition = 0;
    private int currentPosition2 = 0;
    private int currentPosition3 = 0;
    private ImageView pressLayout;
    private int margin = -18; //

    //enemigos
    private ImageView arrowEnemy, arrowEnemy1, arrowEnemy2, arrowEnemy3, arrowEnemy4, arrowEnemy5,
            arrowEnemy6, arrowEnemy7, arrowEnemy8, arrowEnemy9, arrowEnemy10;
    private ArrowEnemy arrow, arrow1, arrow2, arrow3, arrow4, arrow5, arrow6, arrow7, arrow8, arrow9, arrow10;

    private ImageView coin1, coin2;
    private float enemySpeed = 24;

    private float sizeEnemy = 100;
    private TextView prueba;

    private float point1, point2, point3, point4;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);


        // Calcula las coordenadas Y de los 4 puntos de paso en función del tamaño de la pantalla
        sizeMobile = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(sizeMobile);
        float screenHeight = sizeMobile.heightPixels;
        point1 = screenHeight * 0.1f;  // Porcentaje arbitrario (20%)
        point2 = screenHeight * 0.3f;  // Porcentaje arbitrario (40%)
        point3 = screenHeight * 0.5f;  // Porcentaje arbitrario (60%)
        point4 = screenHeight * 0.7f;  // Porcentaje arbitrario (80%)

        backgroundImage = findViewById(R.id.bg_bottom);
        backgroundImage2 = findViewById(R.id.bg_bottom_continuation);
        backgroundImage3 = findViewById(R.id.bg_bottom_continuation);
        prueba = findViewById(R.id.textView);
        pressLayout = findViewById(R.id.bg_top);
        character = findViewById(R.id.bird);
        gameLayout = findViewById(R.id.press);
        coin1 = findViewById(R.id.coinUno);
        arrowEnemy = findViewById(R.id.arrow_enemy);
        arrowEnemy1 = findViewById(R.id.arrow_enemy1);
        arrowEnemy2 = findViewById(R.id.arrow_enemy2);
        arrowEnemy3 = findViewById(R.id.arrow_enemy3);
        arrowEnemy4 = findViewById(R.id.arrow_enemy4);
        arrowEnemy5 = findViewById(R.id.arrow_enemy5);
        arrowEnemy6 = findViewById(R.id.arrow_enemy6);
        arrowEnemy7 = findViewById(R.id.arrow_enemy7);
        arrowEnemy8 = findViewById(R.id.arrow_enemy8);
        arrowEnemy9 = findViewById(R.id.arrow_enemy9);
        arrowEnemy10 = findViewById(R.id.arrow_enemy10);

        arrow = new ArrowEnemy(3000, point4, enemySpeed, sizeEnemy);
        arrow1 = new ArrowEnemy(4000, point3, enemySpeed, sizeEnemy);
        arrow2 = new ArrowEnemy(5000, point4, enemySpeed, sizeEnemy);
        arrow3 = new ArrowEnemy(6000, point2, enemySpeed, sizeEnemy);
        arrow4 = new ArrowEnemy(7000, point2, enemySpeed, sizeEnemy);
        arrow5 = new ArrowEnemy(8000, point4, enemySpeed, sizeEnemy);
        arrow6 = new ArrowEnemy(9000, point1, enemySpeed, sizeEnemy);
        arrow7 = new ArrowEnemy(10000, point4, enemySpeed, sizeEnemy);
        arrow8 = new ArrowEnemy(11000, point3, enemySpeed, sizeEnemy);
        arrow9 = new ArrowEnemy(12000, point1, enemySpeed, sizeEnemy);
        arrow10 = new ArrowEnemy(13000, point4, enemySpeed, sizeEnemy);


        Coins coin_1 = new Coins(1000, point1, enemySpeed, 200, MainActivity.this);



        coin1.setX(coin_1.getX());
        coin1.setY(coin_1.getY());

        arrowEnemy.setX(arrow.getX());
        arrowEnemy.setY(arrow.getY());

        arrowEnemy1.setX(arrow1.getX());
        arrowEnemy1.setY(arrow1.getY());

        arrowEnemy2.setX(arrow1.getX());
        arrowEnemy2.setY(arrow1.getY());

        arrowEnemy3.setX(arrow1.getX());
        arrowEnemy3.setY(arrow1.getY());

        arrowEnemy4.setX(arrow1.getX());
        arrowEnemy4.setY(arrow1.getY());

        arrowEnemy5.setX(arrow1.getX());
        arrowEnemy5.setY(arrow1.getY());

        arrowEnemy6.setX(arrow1.getX());
        arrowEnemy6.setY(arrow1.getY());

        arrowEnemy7.setX(arrow1.getX());
        arrowEnemy7.setY(arrow1.getY());

        arrowEnemy8.setX(arrow1.getX());
        arrowEnemy8.setY(arrow1.getY());

        arrowEnemy9.setX(arrow1.getX());
        arrowEnemy9.setY(arrow1.getY());

        arrowEnemy10.setX(arrow1.getX());
        arrowEnemy10.setY(arrow1.getY());

        character.setX(SET_CHARACTER_POSITION_X);
        character.setY(SET_CHARACTER_POSITION_Y);

        Intent intent = getIntent();
        int getVariable = intent.getIntExtra("seleccion", 0);
        setCharacterImage(getVariable);
        sizeMobile = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(sizeMobile);
        screenWidth = sizeMobile.widthPixels;



        currentPosition = 0;
        currentPosition2 = screenWidth - 15;
        currentPosition3 = screenWidth - 15;

        moveBackground();
        gameStarted = false;
        gameLayout.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        if (!gameStarted) {
                            startGame();
                        }
                        jump();
                        break;
                    case MotionEvent.ACTION_UP:
                        // Se ha soltado la pantalla (no hagas nada al soltar)
                        break;
                }

                return true;
            }

            private void startGame() {
                gameStarted = true;
                birdSpeed = -1;
                birdY = (int) character.getY();
                handler.postDelayed(gameRunnable, 20);
            }

            private void jump() {
                birdSpeed = -32;
            }

            private Runnable gameRunnable = new Runnable() {
                @Override
                public void run() {
                    birdSpeed += 2;
                    birdY += birdSpeed;

                    if (birdY < 0) {
                        birdY = 0;
                        birdSpeed = 0;
                    } else if (birdY > gameLayout.getHeight() - character.getHeight()) {
                        birdY = gameLayout.getHeight() - character.getHeight();
                        birdSpeed = 0;
                    }

                    character.setY(birdY);

                    coin_1.moveEnemy();
                    coin1.setX(coin_1.getX());
                    coin1.setX(coin_1.getX());

                    arrow.moveEnemy();
                    arrowEnemy.setX(arrow.getX());
                    arrowEnemy.setY(arrow.getY());

                    arrow1.moveEnemy();
                    arrowEnemy1.setX(arrow1.getX());
                    arrowEnemy1.setY(arrow1.getY());

                    arrow2.moveEnemy();
                    arrowEnemy2.setX(arrow2.getX());
                    arrowEnemy2.setY(arrow2.getY());

                    arrow3.moveEnemy();
                    arrowEnemy3.setX(arrow3.getX());
                    arrowEnemy3.setY(arrow3.getY());

                    arrow4.moveEnemy();
                    arrowEnemy4.setX(arrow4.getX());
                    arrowEnemy4.setY(arrow4.getY());

                    arrow5.moveEnemy();
                    arrowEnemy5.setX(arrow5.getX());
                    arrowEnemy5.setY(arrow5.getY());

                    arrow6.moveEnemy();
                    arrowEnemy6.setX(arrow6.getX());
                    arrowEnemy6.setY(arrow6.getY());

                    arrow7.moveEnemy();
                    arrowEnemy7.setX(arrow7.getX());
                    arrowEnemy7.setY(arrow7.getY());

                    arrow8.moveEnemy();
                    arrowEnemy8.setX(arrow8.getX());
                    arrowEnemy8.setY(arrow8.getY());

                    arrow9.moveEnemy();
                    arrowEnemy9.setX(arrow9.getX());
                    arrowEnemy9.setY(arrow9.getY());

                    arrow10.moveEnemy();
                    arrowEnemy10.setX(arrow10.getX());
                    arrowEnemy10.setY(arrow10.getY());

                    if (coin_1.checkCollision(character) || arrow.checkCollision(character) || arrow1.checkCollision(character)|| arrow2.checkCollision(character)|| arrow3.checkCollision(character)|| arrow4.checkCollision(character)|| arrow5.checkCollision(character)|| arrow6.checkCollision(character)|| arrow7.checkCollision(character)|| arrow8.checkCollision(character)|| arrow9.checkCollision(character)|| arrow10.checkCollision(character)) {
                        prueba.setText("colisionando");
                    } else {
                        prueba.setText("");
                    }

                    handler.postDelayed(this, 20);
                }
            };
        });
    }

    private void setCharacterImage(int seleccion) {
        switch (seleccion) {
            case 1:
                character.setImageResource(R.drawable.witch_cat_girl_v2);
                break;
            case 2:
                character.setImageResource(R.drawable.witch_cat_v2);
                break;
            default:
                character.setImageResource(R.drawable.witch_cat_girl_v2);
                break;
        }
    }

    private void moveBackground() {
        backgroundImage.postOnAnimationDelayed(new Runnable() {
            @Override
            public void run() {
                currentPosition -= 13;
                backgroundImage.setX(currentPosition);

                currentPosition2 -= 13;
                backgroundImage2.setX(currentPosition2);

                currentPosition3 -= 13;
                backgroundImage3.setX(currentPosition3);

                if (currentPosition < -screenWidth - margin) {
                    currentPosition = screenWidth + margin;
                }

                if (currentPosition2 < -screenWidth - margin) {
                    currentPosition2 = screenWidth + margin;
                }

                if (currentPosition3 < -screenWidth - margin) {
                    currentPosition3 = screenWidth + margin;
                }

                moveBackground();
            }
        }, 16);
    }
}
