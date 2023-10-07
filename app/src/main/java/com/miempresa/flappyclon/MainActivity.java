package com.miempresa.flappyclon;

import static java.security.AccessController.getContext;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
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
    int addCoinValue = 1;

    //enemigos
    private ImageView arrowEnemy, arrowEnemy1, arrowEnemy2, arrowEnemy3, arrowEnemy4, arrowEnemy5,
            arrowEnemy6, arrowEnemy7, arrowEnemy8, arrowEnemy9, arrowEnemy10;
    private ArrowEnemy arrow, arrow1, arrow2, arrow3, arrow4, arrow5, arrow6, arrow7, arrow8, arrow9, arrow10;

    private ImageView coin1, coin2, coin3, coin4, coin5, coin6, coin7, coin8, coin9, coin10, coin11, coin12,
            coin13, coin14, coin15, coin16, coin17, coin18, coin19, coin20, coin21, coin22, coin23, coin24,
            coin25, coin26, coin27, coin28, coin29, coin30, coin31, coin32, coin33, coin34, coin35, coin36,
            coin37, coin38, coin39, coin40, coin41, coin42, coin43, coin44, coin45, coin46, coin47, coin48,
            coin49, coin50;

    private float enemySpeed = 24;

    private float sizeEnemy = 100;
    private TextView prueba;
    private ImageView BirdEnemy;

    private float point1, point2, point3, point4;
    private float coinPoint1, coinPoint2, coinPoint3, coinPoint4;
    ImageView animationCoin;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);

        Player player = new Player();
        // Calcula las coordenadas Y de los 4 puntos de paso en función del tamaño de la pantalla
        sizeMobile = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(sizeMobile);
        float screenHeight = sizeMobile.heightPixels;
        point1 = screenHeight * 0.1f;  // Porcentaje respecto a la pantalla (20%)
        point2 = screenHeight * 0.3f;  // Porcentaje respecto a la pantalla (40%)
        point3 = screenHeight * 0.5f;  // Porcentaje respecto a la pantalla (60%)
        point4 = screenHeight * 0.7f;  // Porcentaje respecto a la pantalla (80%)


        coinPoint1 = screenHeight * 0.2f ;  // Porcentaje respecto a la pantalla (20%)
        coinPoint2 = screenHeight * 0.4f  ;  // Porcentaje respecto a la pantalla (40%)
        coinPoint3 = screenHeight * 0.5f ;  // Porcentaje respecto a la pantalla (60%)
        coinPoint4 = screenHeight * 0.8f;  // Porcentaje respecto a la pantalla (80%)



        BirdEnemy =findViewById(R.id.birdEnemy);
        BirdEnemy.setBackgroundResource(R.drawable.bird_animation);
        // Obtiene la animación del ImageView
        AnimationDrawable animationDrawable = (AnimationDrawable) BirdEnemy.getBackground();
        animationDrawable.start();
        animationCoin = findViewById(R.id.animation);
        animationCoin.setBackgroundResource(R.drawable.coin_animation);
        // Obtiene la animación del ImageView
        animationDrawable = (AnimationDrawable) animationCoin.getBackground();

        // Inicia la animación
        animationDrawable.start();

        backgroundImage = findViewById(R.id.bg_bottom);
        backgroundImage2 = findViewById(R.id.bg_bottom_continuation);
        backgroundImage3 = findViewById(R.id.bg_bottom_continuation);
        prueba = findViewById(R.id.textView);
        pressLayout = findViewById(R.id.bg_top);
        character = findViewById(R.id.bird);
        gameLayout = findViewById(R.id.press);
        coin1 = findViewById(R.id.coinUno);
        coin1.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin1.getBackground();
        animationDrawable.start();
        coin2 = findViewById(R.id.coinDos);
        coin2.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin2.getBackground();
        animationDrawable.start();
        coin1 = findViewById(R.id.coinUno);
        coin1.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin1.getBackground();
        animationDrawable.start();
        coin2 = findViewById(R.id.coinDos);
        coin2.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin2.getBackground();
        animationDrawable.start();
        coin3 = findViewById(R.id.coinTres);
        coin3.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin3.getBackground();
        animationDrawable.start();
        coin4 = findViewById(R.id.coinCuatro);
        coin4.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin4.getBackground();
        animationDrawable.start();
        coin5 = findViewById(R.id.coinCinco);
        coin5.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin5.getBackground();
        animationDrawable.start();
        coin6 = findViewById(R.id.coinSeis);
        coin6.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin6.getBackground();
        animationDrawable.start();
        coin7 = findViewById(R.id.coinSiete);
        coin7.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin7.getBackground();
        animationDrawable.start();
        coin8 = findViewById(R.id.coinOcho);
        coin8.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin8.getBackground();
        animationDrawable.start();
        coin9 = findViewById(R.id.coinNueve);
        coin9.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin9.getBackground();
        animationDrawable.start();
        coin10 = findViewById(R.id.coinDiez);
        coin10.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin10.getBackground();
        animationDrawable.start();
        coin11 = findViewById(R.id.coinOnce);
        coin11.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin11.getBackground();
        animationDrawable.start();
        coin12 = findViewById(R.id.coinDoce);
        coin12.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin12.getBackground();
        animationDrawable.start();
        coin13 = findViewById(R.id.coinTrece);
        coin13.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin13.getBackground();
        animationDrawable.start();
        coin14 = findViewById(R.id.coinCatorce);
        coin14.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin14.getBackground();
        animationDrawable.start();
        coin15 = findViewById(R.id.coinQuince);
        coin15.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin15.getBackground();
        animationDrawable.start();
        coin16 = findViewById(R.id.coinDieciseis);
        coin16.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin16.getBackground();
        animationDrawable.start();
        coin17 = findViewById(R.id.coinDiecisiete);
        coin17.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin17.getBackground();
        animationDrawable.start();
        coin18 = findViewById(R.id.coinDieciocho);
        coin18.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin18.getBackground();
        animationDrawable.start();
        coin19 = findViewById(R.id.coinDiecinueve);
        coin19.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin19.getBackground();
        animationDrawable.start();
        coin20 = findViewById(R.id.coinVeinte);
        coin20.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin20.getBackground();
        animationDrawable.start();
        coin21 = findViewById(R.id.coinVeintiuno);
        coin21.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin21.getBackground();
        animationDrawable.start();
        coin22 = findViewById(R.id.coinVeintidos);
        coin22.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin22.getBackground();
        animationDrawable.start();
        coin23 = findViewById(R.id.coinVeintitres);
        coin23.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin23.getBackground();
        animationDrawable.start();
        coin24 = findViewById(R.id.coinVeinticuatro);
        coin24.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin24.getBackground();
        animationDrawable.start();
        coin25 = findViewById(R.id.coinVeinticinco);
        coin25.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin25.getBackground();
        animationDrawable.start();
        coin26 = findViewById(R.id.coinVeintiseis);
        coin26.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin26.getBackground();
        animationDrawable.start();
        coin27 = findViewById(R.id.coinVeintisiete);
        coin27.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin27.getBackground();
        animationDrawable.start();
        coin28 = findViewById(R.id.coinVeintiocho);
        coin28.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin28.getBackground();
        animationDrawable.start();
        coin29 = findViewById(R.id.coinVeintinueve);
        coin29.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin29.getBackground();
        animationDrawable.start();
        coin30 = findViewById(R.id.coinTreinta);
        coin30.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin30.getBackground();
        animationDrawable.start();
        coin31 = findViewById(R.id.coinTreintaiuno);
        coin31.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin31.getBackground();
        animationDrawable.start();
        coin32 = findViewById(R.id.coinTreintaidos);
        coin32.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin32.getBackground();
        animationDrawable.start();
        coin33 = findViewById(R.id.coinTreintaitres);
        coin33.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin33.getBackground();
        animationDrawable.start();
        coin34 = findViewById(R.id.coinTreintaicuatro);
        coin34.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin34.getBackground();
        animationDrawable.start();
        coin35 = findViewById(R.id.coinTreintaicinco);
        coin35.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin35.getBackground();
        animationDrawable.start();
        coin36 = findViewById(R.id.coinTreintaiseis);
        coin36.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin36.getBackground();
        animationDrawable.start();
        coin37 = findViewById(R.id.coinTreintaisiete);
        coin37.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin37.getBackground();
        animationDrawable.start();
        coin38 = findViewById(R.id.coinTreintaiocho);
        coin38.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin38.getBackground();
        animationDrawable.start();
        coin39 = findViewById(R.id.coinTreintainueve);
        coin39.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin39.getBackground();
        animationDrawable.start();
        coin40 = findViewById(R.id.coinCuarenta);
        coin40.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin40.getBackground();
        animationDrawable.start();
        coin41 = findViewById(R.id.coinCuarentaiuno);
        coin41.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin41.getBackground();
        animationDrawable.start();
        coin42 = findViewById(R.id.coinCuarentaidos);
        coin42.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin42.getBackground();
        animationDrawable.start();
        coin43 = findViewById(R.id.coinCuarentaitres);
        coin43.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin43.getBackground();
        animationDrawable.start();
        coin44 = findViewById(R.id.coinCuarentaicuatro);
        coin44.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin44.getBackground();
        animationDrawable.start();
        coin45 = findViewById(R.id.coinCuarentaicinco);
        coin45.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin45.getBackground();
        animationDrawable.start();
        coin46 = findViewById(R.id.coinCuarentaiseis);
        coin46.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin46.getBackground();
        animationDrawable.start();
        coin47 = findViewById(R.id.coinCuarentaisiete);
        coin47.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin47.getBackground();
        animationDrawable.start();
        coin48 = findViewById(R.id.coinCuarentaiocho);
        coin48.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin48.getBackground();
        animationDrawable.start();
        coin49 = findViewById(R.id.coinCuarentainueve);
        coin49.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin49.getBackground();
        animationDrawable.start();
        coin50 = findViewById(R.id.coinCincuenta);
        coin50.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin50.getBackground();
        animationDrawable.start();



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


        Coins coin_1 = new Coins(6100, coinPoint4, enemySpeed, 40, MainActivity.this);
        Coins coin_2 = new Coins(6300, coinPoint4, enemySpeed, 40, MainActivity.this);
        Coins coin_3 = new Coins(6500, coinPoint4, enemySpeed, 40, MainActivity.this);
        Coins coin_4 = new Coins(7500, coinPoint3, enemySpeed, 40, MainActivity.this);
        Coins coin_5 = new Coins(7700, coinPoint3, enemySpeed, 40, MainActivity.this);
        Coins coin_6 = new Coins(7900, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_7 = new Coins(8100, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_8 = new Coins(8300, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_9 = new Coins(8500, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_10 = new Coins(9000, coinPoint4, enemySpeed, 40, MainActivity.this);
        Coins coin_11 = new Coins(9200, coinPoint4, enemySpeed, 40, MainActivity.this);
        Coins coin_12 = new Coins(9400, coinPoint4, enemySpeed, 40, MainActivity.this);


        Coins coin_13 = new Coins(10000, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_14 = new Coins(10200, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_15 = new Coins(10400, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_16 = new Coins(10600, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_17 = new Coins(10800, coinPoint3, enemySpeed, 40, MainActivity.this);
        Coins coin_18 = new Coins(11000, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_19 = new Coins(11200, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_20 = new Coins(18300, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_21 = new Coins(18500, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_22 = new Coins(19000, coinPoint4, enemySpeed, 40, MainActivity.this);
        Coins coin_23 = new Coins(19200, coinPoint4, enemySpeed, 40, MainActivity.this);
        Coins coin_24 = new Coins(19400, coinPoint4, enemySpeed, 40, MainActivity.this);

        Coins coin_25 = new Coins(20000, coinPoint4, enemySpeed, 40, MainActivity.this);
        Coins coin_26 = new Coins(20200, coinPoint4, enemySpeed, 40, MainActivity.this);
        Coins coin_27 = new Coins(20400, coinPoint4, enemySpeed, 40, MainActivity.this);
        Coins coin_28 = new Coins(20600, coinPoint3, enemySpeed, 40, MainActivity.this);
        Coins coin_29 = new Coins(20800, coinPoint3, enemySpeed, 40, MainActivity.this);
        Coins coin_30 = new Coins(21000, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_31 = new Coins(21200, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_32 = new Coins(21400, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_33 = new Coins(21600, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_34 = new Coins(21800, coinPoint1, enemySpeed, 40, MainActivity.this);
        Coins coin_35 = new Coins(22000, coinPoint1, enemySpeed, 40, MainActivity.this);
        Coins coin_36 = new Coins(22200, coinPoint1, enemySpeed, 40, MainActivity.this);
        Coins coin_37 = new Coins(22400, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_38 = new Coins(22600, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_39 = new Coins(22800, coinPoint1, enemySpeed, 40, MainActivity.this);
        Coins coin_40 = new Coins(23000, coinPoint1, enemySpeed, 40, MainActivity.this);
        Coins coin_41 = new Coins(23200, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_42 = new Coins(23400, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_43 = new Coins(23600, coinPoint1, enemySpeed, 40, MainActivity.this);
        Coins coin_44 = new Coins(23800, coinPoint1, enemySpeed, 40, MainActivity.this);
        Coins coin_45 = new Coins(24000, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_46 = new Coins(24200, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_47 = new Coins(24400, coinPoint1, enemySpeed, 40, MainActivity.this);
        Coins coin_48 = new Coins(24600, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_49 = new Coins(24800, coinPoint3, enemySpeed, 40, MainActivity.this);
        Coins coin_50 = new Coins(25000, coinPoint4, enemySpeed, 40, MainActivity.this);

        prueba.setText("0/99");


        coin1.setX(coin_1.getX());
        coin1.setY(coin_1.getY());

        coin2.setX(coin_2.getX());
        coin2.setY(coin_2.getY());

        coin3.setX(coin_3.getX());
        coin3.setY(coin_3.getY());

        coin4.setX(coin_4.getX());
        coin4.setY(coin_4.getY());

        coin5.setX(coin_5.getX());
        coin5.setY(coin_5.getY());

        coin6.setX(coin_6.getX());
        coin6.setY(coin_6.getY());

        coin7.setX(coin_7.getX());
        coin7.setY(coin_7.getY());

        coin8.setX(coin_8.getX());
        coin8.setY(coin_8.getY());

        coin9.setX(coin_9.getX());
        coin9.setY(coin_9.getY());

        coin10.setX(coin_10.getX());
        coin10.setY(coin_10.getY());

        coin11.setX(coin_11.getX());
        coin11.setY(coin_11.getY());

        coin12.setX(coin_12.getX());
        coin12.setY(coin_12.getY());

        coin13.setX(coin_13.getX());
        coin13.setY(coin_13.getY());

        coin14.setX(coin_14.getX());
        coin14.setY(coin_14.getY());

        coin15.setX(coin_15.getX());
        coin15.setY(coin_15.getY());

        coin16.setX(coin_16.getX());
        coin16.setY(coin_16.getY());

        coin17.setX(coin_17.getX());
        coin17.setY(coin_17.getY());

        coin18.setX(coin_18.getX());
        coin18.setY(coin_18.getY());

        coin19.setX(coin_19.getX());
        coin19.setY(coin_19.getY());

        coin20.setX(coin_20.getX());
        coin20.setY(coin_20.getY());

        coin21.setX(coin_21.getX());
        coin21.setY(coin_21.getY());

        coin22.setX(coin_22.getX());
        coin22.setY(coin_22.getY());

        coin23.setX(coin_23.getX());
        coin23.setY(coin_23.getY());

        coin24.setX(coin_24.getX());
        coin24.setY(coin_24.getY());

        coin25.setX(coin_25.getX());
        coin25.setY(coin_25.getY());

        coin26.setX(coin_26.getX());
        coin26.setY(coin_26.getY());

        coin27.setX(coin_27.getX());
        coin27.setY(coin_27.getY());

        coin28.setX(coin_28.getX());
        coin28.setY(coin_28.getY());

        coin29.setX(coin_29.getX());
        coin29.setY(coin_29.getY());

        coin30.setX(coin_30.getX());
        coin30.setY(coin_30.getY());

        coin31.setX(coin_31.getX());
        coin31.setY(coin_31.getY());

        coin32.setX(coin_32.getX());
        coin32.setY(coin_32.getY());

        coin33.setX(coin_33.getX());
        coin33.setY(coin_33.getY());

        coin34.setX(coin_34.getX());
        coin34.setY(coin_34.getY());

        coin35.setX(coin_35.getX());
        coin35.setY(coin_35.getY());

        coin36.setX(coin_36.getX());
        coin36.setY(coin_36.getY());

        coin37.setX(coin_37.getX());
        coin37.setY(coin_37.getY());

        coin38.setX(coin_38.getX());
        coin38.setY(coin_38.getY());

        coin39.setX(coin_39.getX());
        coin39.setY(coin_39.getY());

        coin40.setX(coin_40.getX());
        coin40.setY(coin_40.getY());

        coin41.setX(coin_41.getX());
        coin41.setY(coin_41.getY());

        coin42.setX(coin_42.getX());
        coin42.setY(coin_42.getY());

        coin43.setX(coin_43.getX());
        coin43.setY(coin_43.getY());

        coin44.setX(coin_44.getX());
        coin44.setY(coin_44.getY());

        coin45.setX(coin_45.getX());
        coin45.setY(coin_45.getY());

        coin46.setX(coin_46.getX());
        coin46.setY(coin_46.getY());

        coin47.setX(coin_47.getX());
        coin47.setY(coin_47.getY());

        coin48.setX(coin_48.getX());
        coin48.setY(coin_48.getY());

        coin49.setX(coin_49.getX());
        coin49.setY(coin_49.getY());

        coin50.setX(coin_50.getX());
        coin50.setY(coin_50.getY());


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
                    coin1.setY(coin_1.getY());

                    coin_2.moveEnemy();
                    coin2.setX(coin_2.getX());
                    coin2.setY(coin_2.getY());

                    coin_3.moveEnemy();
                    coin3.setX(coin_3.getX());
                    coin3.setY(coin_3.getY());

                    coin_4.moveEnemy();
                    coin4.setX(coin_4.getX());
                    coin4.setY(coin_4.getY());

                    coin_5.moveEnemy();
                    coin5.setX(coin_5.getX());
                    coin5.setY(coin_5.getY());

                    coin_6.moveEnemy();
                    coin6.setX(coin_6.getX());
                    coin6.setY(coin_6.getY());

                    coin_7.moveEnemy();
                    coin7.setX(coin_7.getX());
                    coin7.setY(coin_7.getY());

                    coin_8.moveEnemy();
                    coin8.setX(coin_8.getX());
                    coin8.setY(coin_8.getY());

                    coin_9.moveEnemy();
                    coin9.setX(coin_9.getX());
                    coin9.setY(coin_9.getY());

                    coin_10.moveEnemy();
                    coin10.setX(coin_10.getX());
                    coin10.setY(coin_10.getY());

                    coin_11.moveEnemy();
                    coin11.setX(coin_11.getX());
                    coin11.setY(coin_11.getY());

                    coin_12.moveEnemy();
                    coin12.setX(coin_12.getX());
                    coin12.setY(coin_12.getY());

                    coin_13.moveEnemy();
                    coin13.setX(coin_13.getX());
                    coin13.setY(coin_13.getY());

                    coin_14.moveEnemy();
                    coin14.setX(coin_14.getX());
                    coin14.setY(coin_14.getY());

                    coin_15.moveEnemy();
                    coin15.setX(coin_15.getX());
                    coin15.setY(coin_15.getY());

                    coin_16.moveEnemy();
                    coin16.setX(coin_16.getX());
                    coin16.setY(coin_16.getY());

                    coin_17.moveEnemy();
                    coin17.setX(coin_17.getX());
                    coin17.setY(coin_17.getY());

                    coin_18.moveEnemy();
                    coin18.setX(coin_18.getX());
                    coin18.setY(coin_18.getY());

                    coin_19.moveEnemy();
                    coin19.setX(coin_19.getX());
                    coin19.setY(coin_19.getY());

                    coin_20.moveEnemy();
                    coin20.setX(coin_20.getX());
                    coin20.setY(coin_20.getY());

                    coin_21.moveEnemy();
                    coin21.setX(coin_21.getX());
                    coin21.setY(coin_21.getY());

                    coin_22.moveEnemy();
                    coin22.setX(coin_22.getX());
                    coin22.setY(coin_22.getY());

                    coin_23.moveEnemy();
                    coin23.setX(coin_23.getX());
                    coin23.setY(coin_23.getY());

                    coin_24.moveEnemy();
                    coin24.setX(coin_24.getX());
                    coin24.setY(coin_24.getY());

                    coin_25.moveEnemy();
                    coin25.setX(coin_25.getX());
                    coin25.setY(coin_25.getY());

                    coin_26.moveEnemy();
                    coin26.setX(coin_26.getX());
                    coin26.setY(coin_26.getY());

                    coin_27.moveEnemy();
                    coin27.setX(coin_27.getX());
                    coin27.setY(coin_27.getY());

                    coin_28.moveEnemy();
                    coin28.setX(coin_28.getX());
                    coin28.setY(coin_28.getY());

                    coin_29.moveEnemy();
                    coin29.setX(coin_29.getX());
                    coin29.setY(coin_29.getY());

                    coin_30.moveEnemy();
                    coin30.setX(coin_30.getX());
                    coin30.setY(coin_30.getY());

                    coin_31.moveEnemy();
                    coin31.setX(coin_31.getX());
                    coin31.setY(coin_31.getY());

                    coin_32.moveEnemy();
                    coin32.setX(coin_32.getX());
                    coin32.setY(coin_32.getY());

                    coin_33.moveEnemy();
                    coin33.setX(coin_33.getX());
                    coin33.setY(coin_33.getY());

                    coin_34.moveEnemy();
                    coin34.setX(coin_34.getX());
                    coin34.setY(coin_34.getY());

                    coin_35.moveEnemy();
                    coin35.setX(coin_35.getX());
                    coin35.setY(coin_35.getY());

                    coin_36.moveEnemy();
                    coin36.setX(coin_36.getX());
                    coin36.setY(coin_36.getY());

                    coin_37.moveEnemy();
                    coin37.setX(coin_37.getX());
                    coin37.setY(coin_37.getY());

                    coin_38.moveEnemy();
                    coin38.setX(coin_38.getX());
                    coin38.setY(coin_38.getY());

                    coin_39.moveEnemy();
                    coin39.setX(coin_39.getX());
                    coin39.setY(coin_39.getY());

                    coin_40.moveEnemy();
                    coin40.setX(coin_40.getX());
                    coin40.setY(coin_40.getY());

                    coin_41.moveEnemy();
                    coin41.setX(coin_41.getX());
                    coin41.setY(coin_41.getY());

                    coin_42.moveEnemy();
                    coin42.setX(coin_42.getX());
                    coin42.setY(coin_42.getY());

                    coin_43.moveEnemy();
                    coin43.setX(coin_43.getX());
                    coin43.setY(coin_43.getY());

                    coin_44.moveEnemy();
                    coin44.setX(coin_44.getX());
                    coin44.setY(coin_44.getY());

                    coin_45.moveEnemy();
                    coin45.setX(coin_45.getX());
                    coin45.setY(coin_45.getY());

                    coin_46.moveEnemy();
                    coin46.setX(coin_46.getX());
                    coin46.setY(coin_46.getY());

                    coin_47.moveEnemy();
                    coin47.setX(coin_47.getX());
                    coin47.setY(coin_47.getY());

                    coin_48.moveEnemy();
                    coin48.setX(coin_48.getX());
                    coin48.setY(coin_48.getY());

                    coin_49.moveEnemy();
                    coin49.setX(coin_49.getX());
                    coin49.setY(coin_49.getY());

                    coin_50.moveEnemy();
                    coin50.setX(coin_50.getX());
                    coin50.setY(coin_50.getY());


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


                    if (arrow.checkCollision(character) || arrow1.checkCollision(character)|| arrow2.checkCollision(character)|| arrow3.checkCollision(character)|| arrow4.checkCollision(character)|| arrow5.checkCollision(character)|| arrow6.checkCollision(character)|| arrow7.checkCollision(character)|| arrow8.checkCollision(character)|| arrow9.checkCollision(character)|| arrow10.checkCollision(character)) {

                    } else if (!coin_1.isCollected() && coin_1.checkCollision(character)) {
                        coin1.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_1.setCollected(true);
                    } else if (!coin_2.isCollected() && coin_2.checkCollision(character)) {
                        coin2.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_2.setCollected(true);
                    }else if(!coin_3.isCollected() && coin_3.checkCollision(character)){
                        coin3.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_3.setCollected(true);
                    }else if(!coin_4.isCollected() && coin_4.checkCollision(character)){
                        coin4.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_4.setCollected(true);
                    }else if(!coin_5.isCollected() && coin_5.checkCollision(character)){
                        coin5.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_5.setCollected(true);
                    }else if(!coin_6.isCollected() && coin_6.checkCollision(character)){
                        coin6.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_6.setCollected(true);
                    }else if(!coin_7.isCollected() && coin_7.checkCollision(character)){
                        coin7.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_7.setCollected(true);
                    }else if(!coin_8.isCollected() && coin_8.checkCollision(character)){
                        coin8.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_8.setCollected(true);
                    }else if(!coin_9.isCollected() && coin_9.checkCollision(character)){
                        coin9.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_9.setCollected(true);
                    }else if(!coin_10.isCollected() && coin_10.checkCollision(character)){
                        coin10.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_10.setCollected(true);
                    }else if(!coin_11.isCollected() && coin_11.checkCollision(character)){
                        coin11.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_11.setCollected(true);
                    }else if(!coin_12.isCollected() && coin_12.checkCollision(character)){
                        coin12.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_12.setCollected(true);
                    }else if (!coin_13.isCollected() && coin_13.checkCollision(character)) {
                        coin13.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_13.setCollected(true);
                    } else if (!coin_14.isCollected() && coin_14.checkCollision(character)) {
                        coin14.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_14.setCollected(true);
                    } else if (!coin_15.isCollected() && coin_15.checkCollision(character)) {
                        coin15.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_15.setCollected(true);
                    } else if (!coin_16.isCollected() && coin_16.checkCollision(character)) {
                        coin16.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_16.setCollected(true);
                    } else if (!coin_17.isCollected() && coin_17.checkCollision(character)) {
                        coin17.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_17.setCollected(true);
                    } else if (!coin_18.isCollected() && coin_18.checkCollision(character)) {
                        coin18.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_18.setCollected(true);
                    } else if (!coin_19.isCollected() && coin_19.checkCollision(character)) {
                        coin19.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_19.setCollected(true);
                    } else if (!coin_20.isCollected() && coin_20.checkCollision(character)) {
                        coin20.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_20.setCollected(true);
                    } else if (!coin_21.isCollected() && coin_21.checkCollision(character)) {
                        coin21.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_21.setCollected(true);
                    } else if (!coin_22.isCollected() && coin_22.checkCollision(character)) {
                        coin22.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_22.setCollected(true);
                    } else if (!coin_23.isCollected() && coin_23.checkCollision(character)) {
                        coin23.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_23.setCollected(true);
                    } else if (!coin_24.isCollected() && coin_24.checkCollision(character)) {
                        coin24.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_24.setCollected(true);
                    } else if (!coin_25.isCollected() && coin_25.checkCollision(character)) {
                        coin25.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_25.setCollected(true);
                    } else if (!coin_26.isCollected() && coin_26.checkCollision(character)) {
                        coin26.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_26.setCollected(true);
                    } else if (!coin_27.isCollected() && coin_27.checkCollision(character)) {
                        coin27.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_27.setCollected(true);
                    } else if (!coin_28.isCollected() && coin_28.checkCollision(character)) {
                        coin28.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_28.setCollected(true);
                    } else if (!coin_29.isCollected() && coin_29.checkCollision(character)) {
                        coin29.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_29.setCollected(true);
                    } else if (!coin_30.isCollected() && coin_30.checkCollision(character)) {
                        coin30.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_30.setCollected(true);
                    } else if (!coin_31.isCollected() && coin_31.checkCollision(character)) {
                        coin31.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_31.setCollected(true);
                    } else if (!coin_32.isCollected() && coin_32.checkCollision(character)) {
                        coin32.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_32.setCollected(true);
                    } else if (!coin_33.isCollected() && coin_33.checkCollision(character)) {
                        coin33.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_33.setCollected(true);
                    } else if (!coin_34.isCollected() && coin_34.checkCollision(character)) {
                        coin34.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_34.setCollected(true);
                    } else if (!coin_35.isCollected() && coin_35.checkCollision(character)) {
                        coin35.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_35.setCollected(true);
                    } else if (!coin_36.isCollected() && coin_36.checkCollision(character)) {
                        coin36.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                       coin_36.setCollected(true);
                    } else if (!coin_37.isCollected() && coin_37.checkCollision(character)) {
                        coin37.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_37.setCollected(true);
                    } else if (!coin_38.isCollected() && coin_38.checkCollision(character)) {
                        coin38.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_38.setCollected(true);
                    } else if (!coin_39.isCollected() && coin_39.checkCollision(character)) {
                        coin39.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_39.setCollected(true);
                    } else if (!coin_40.isCollected() && coin_40.checkCollision(character)) {
                        coin40.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_40.setCollected(true);
                    } else if (!coin_41.isCollected() && coin_41.checkCollision(character)) {
                        coin41.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_41.setCollected(true);
                    } else if (!coin_42.isCollected() && coin_42.checkCollision(character)) {
                        coin42.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_42.setCollected(true);
                    } else if (!coin_43.isCollected() && coin_43.checkCollision(character)) {
                        coin43.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_43.setCollected(true);
                    } else if (!coin_44.isCollected() && coin_44.checkCollision(character)) {
                        coin44.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_44.setCollected(true);
                    } else if (!coin_45.isCollected() && coin_45.checkCollision(character)) {
                        coin45.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_45.setCollected(true);
                    } else if (!coin_46.isCollected() && coin_46.checkCollision(character)) {
                        coin46.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_46.setCollected(true);
                    } else if (!coin_47.isCollected() && coin_47.checkCollision(character)) {
                        coin47.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_47.setCollected(true);
                    } else if (!coin_48.isCollected() && coin_48.checkCollision(character)) {
                        coin48.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_48.setCollected(true);
                    } else if (!coin_49.isCollected() && coin_49.checkCollision(character)) {
                        coin49.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_49.setCollected(true);
                    } else if (!coin_50.isCollected() && coin_50.checkCollision(character)) {
                        coin50.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_50.setCollected(true);
                  }

                    String getValue = String.valueOf(player.getCountCoins());
                    prueba.setText(getValue+"/99");
//




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
