package com.miempresa.flappyclon;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer = new MediaPlayer();
    private MediaPlayer coinSound;  // Nuevo MediaPlayer para el sonido de la moneda
    private MediaPlayer hitSound; //sonido cuando el jugador ese tocado por el enemigo
    private boolean isLogicExecuted = false;
    Intent intentGameOver;

    ImageView fullHeart1;
    ImageView fullHeart2;
    ImageView fullHeart3;
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
            arrowEnemy6, arrowEnemy7, arrowEnemy8, arrowEnemy9, arrowEnemy10, arrowEnemy11, arrowEnemy12, arrowEnemy13, arrowEnemy14, arrowEnemy15,
            arrowEnemy16, arrowEnemy17, arrowEnemy18, arrowEnemy19, arrowEnemy20,
            arrowEnemy21, arrowEnemy22, arrowEnemy23, arrowEnemy24, arrowEnemy25,
            arrowEnemy26, arrowEnemy27, arrowEnemy28, arrowEnemy29, arrowEnemy30,
            arrowEnemy31, arrowEnemy32, arrowEnemy33, arrowEnemy34, arrowEnemy35,
            arrowEnemy36, arrowEnemy37, arrowEnemy38, arrowEnemy39, arrowEnemy40,
            arrowEnemy41, arrowEnemy42, arrowEnemy43, arrowEnemy44, arrowEnemy45,
            arrowEnemy46, arrowEnemy47, arrowEnemy48, arrowEnemy49, arrowEnemy50;

    //    polimorfismo para sobreescribir la subclase arrow de la clase Enemys
    private Enemys arrow, arrow1, arrow2, arrow3, arrow4, arrow5, arrow6, arrow7, arrow8, arrow9, arrow10,
            arrow11, arrow12, arrow13, arrow14, arrow15, arrow16, arrow17, arrow18, arrow19, arrow20,
            arrow21, arrow22, arrow23, arrow24, arrow25, arrow26, arrow27, arrow28, arrow29, arrow30,
            arrow31, arrow32, arrow33, arrow34, arrow35, arrow36, arrow37, arrow38, arrow39, arrow40,
            arrow41, arrow42, arrow43, arrow44, arrow45, arrow46, arrow47, arrow48, arrow49, arrow50;


    private ImageView coin1, coin2, coin3, coin4, coin5, coin6, coin7, coin8, coin9, coin10, coin11, coin12,
            coin13, coin14, coin15, coin16, coin17, coin18, coin19, coin20, coin21, coin22, coin23, coin24,
            coin25, coin26, coin27, coin28, coin29, coin30, coin31, coin32, coin33, coin34, coin35, coin36,
            coin37, coin38, coin39, coin40, coin41, coin42, coin43, coin44, coin45, coin46, coin47, coin48,
            coin49, coin50, coin51, coin52, coin53, coin54, coin55, coin56, coin57, coin58, coin59, coin60,
            coin61, coin62, coin63, coin64, coin65, coin66, coin67, coin68, coin69, coin70, coin71, coin72,
            coin73, coin74, coin75, coin76, coin77, coin78, coin79, coin80, coin81, coin82, coin83, coin84,
            coin85, coin86, coin87, coin88, coin89, coin90, coin91, coin92, coin93, coin94, coin95, coin96,
            coin97, coin98, coin99;


    private float enemySpeed = 24;

    private float sizeEnemy = 100;
    private TextView prueba;
    private ImageView birdEnemy1, birdEnemy2, birdEnemy3, birdEnemy4, birdEnemy5, birdEnemy6, birdEnemy7, birdEnemy8, birdEnemy9, birdEnemy10;
    private Enemys bird1, bird2, bird3, bird4, bird5, bird6, bird7, bird8, bird9, bird10;

    private float point1, point2, point3, point4;
    private float coinPoint1, coinPoint2, coinPoint3, coinPoint4;
    ImageView animationCoin;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);


        fullHeart1 =findViewById(R.id.heartOneFull);
        fullHeart2 =findViewById(R.id.heartTwoFull);
        fullHeart3 =findViewById(R.id.heartThreeFull);

        intentGameOver = new Intent(MainActivity.this, GameOver.class);

        //Se inicializa el reproductor multimedia
        mediaPlayer = MediaPlayer.create(this, R.raw.main_theme);
        mediaPlayer.setVolume(0.4f, 0.4f);


        // Inicializa el reproductor multimedia para el sonido de la moneda
        coinSound = MediaPlayer.create(this, R.raw.coin_sound);

        hitSound = MediaPlayer.create(this, R.raw.hit_enemy);

        Player player = new Player();
        // Calcula las coordenadas Y de los 4 puntos de paso en función del tamaño de la pantalla
        sizeMobile = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(sizeMobile);
        float screenHeight = sizeMobile.heightPixels;
        point1 = screenHeight * 0.1f;  // Porcentaje respecto a la pantalla (20%)
        point2 = screenHeight * 0.3f;  // Porcentaje respecto a la pantalla (40%)
        point3 = screenHeight * 0.5f;  // Porcentaje respecto a la pantalla (60%)
        point4 = screenHeight * 0.7f;  // Porcentaje respecto a la pantalla (80%)


        coinPoint1 = screenHeight * 0.2f;  // Porcentaje respecto a la pantalla (20%)
        coinPoint2 = screenHeight * 0.4f;  // Porcentaje respecto a la pantalla (40%)
        coinPoint3 = screenHeight * 0.5f;  // Porcentaje respecto a la pantalla (60%)
        coinPoint4 = screenHeight * 0.8f;  // Porcentaje respecto a la pantalla (80%)


        birdEnemy1 = findViewById(R.id.birdEnemy1);
        birdEnemy1.setBackgroundResource(R.drawable.bird_animation);
        // Obtiene la animación del ImageView
        AnimationDrawable animationDrawable = (AnimationDrawable) birdEnemy1.getBackground();
        animationDrawable.start();


        birdEnemy2 = findViewById(R.id.birdEnemy2);
        birdEnemy2.setBackgroundResource(R.drawable.bird_animation);
        // Obtiene la animación del ImageView
        animationDrawable = (AnimationDrawable) birdEnemy2.getBackground();
        animationDrawable.start();


        birdEnemy3 = findViewById(R.id.birdEnemy3);
        birdEnemy3.setBackgroundResource(R.drawable.bird_animation);
        // Obtiene la animación del ImageView
        animationDrawable = (AnimationDrawable) birdEnemy3.getBackground();
        animationDrawable.start();


        birdEnemy4 = findViewById(R.id.birdEnemy4);
        birdEnemy4.setBackgroundResource(R.drawable.bird_animation);
        // Obtiene la animación del ImageView
        animationDrawable = (AnimationDrawable) birdEnemy4.getBackground();
        animationDrawable.start();

        birdEnemy5 = findViewById(R.id.birdEnemy5);
        birdEnemy5.setBackgroundResource(R.drawable.bird_animation);
        // Obtiene la animación del ImageView
        animationDrawable = (AnimationDrawable) birdEnemy5.getBackground();
        animationDrawable.start();

        birdEnemy6 = findViewById(R.id.birdEnemy6);
        birdEnemy6.setBackgroundResource(R.drawable.bird_animation);
        // Obtiene la animación del ImageView
        animationDrawable = (AnimationDrawable) birdEnemy6.getBackground();
        animationDrawable.start();

        birdEnemy7 = findViewById(R.id.birdEnemy7);
        birdEnemy7.setBackgroundResource(R.drawable.bird_animation);
        // Obtiene la animación del ImageView
        animationDrawable = (AnimationDrawable) birdEnemy7.getBackground();
        animationDrawable.start();

        birdEnemy8 = findViewById(R.id.birdEnemy8);
        birdEnemy8.setBackgroundResource(R.drawable.bird_animation);
        // Obtiene la animación del ImageView
        animationDrawable = (AnimationDrawable) birdEnemy8.getBackground();
        animationDrawable.start();

        birdEnemy9 = findViewById(R.id.birdEnemy9);
        birdEnemy9.setBackgroundResource(R.drawable.bird_animation);
        // Obtiene la animación del ImageView
        animationDrawable = (AnimationDrawable) birdEnemy9.getBackground();
        animationDrawable.start();

        birdEnemy10 = findViewById(R.id.birdEnemy10);
        birdEnemy10.setBackgroundResource(R.drawable.bird_animation);
        // Obtiene la animación del ImageView
        animationDrawable = (AnimationDrawable) birdEnemy10.getBackground();
        animationDrawable.start();


//        Animacion de moneda de conteo
        animationCoin = findViewById(R.id.animation);
        animationCoin.setBackgroundResource(R.drawable.coin_animation);
        // Obtiene la animación del ImageView
        animationDrawable = (AnimationDrawable) animationCoin.getBackground();
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

        coin51 = findViewById(R.id.coinCincuentauno);
        coin51.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin51.getBackground();
        animationDrawable.start();

        coin52 = findViewById(R.id.coinCincuentaidos);
        coin52.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin52.getBackground();
        animationDrawable.start();

        coin53 = findViewById(R.id.coinCincuentaitres);
        coin53.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin53.getBackground();
        animationDrawable.start();

        coin54 = findViewById(R.id.coinCincuentaicuatro);
        coin54.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin54.getBackground();
        animationDrawable.start();

        coin55 = findViewById(R.id.coinCincuentaicinco);
        coin55.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin55.getBackground();
        animationDrawable.start();

        coin56 = findViewById(R.id.coinCincuentaiseis);
        coin56.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin56.getBackground();
        animationDrawable.start();

        coin57 = findViewById(R.id.coinCincuentaisiete);
        coin57.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin57.getBackground();
        animationDrawable.start();

        coin58 = findViewById(R.id.coinCincuentaiocho);
        coin58.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin58.getBackground();
        animationDrawable.start();

        coin59 = findViewById(R.id.coinCincuentainueve);
        coin59.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin59.getBackground();
        animationDrawable.start();

        coin60 = findViewById(R.id.coinSesenta);
        coin60.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin60.getBackground();
        animationDrawable.start();

        coin61 = findViewById(R.id.coinSesentaiuno);
        coin61.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin61.getBackground();
        animationDrawable.start();

        coin62 = findViewById(R.id.coinSesentaidos);
        coin62.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin62.getBackground();
        animationDrawable.start();

        coin63 = findViewById(R.id.coinSesentaitres);
        coin63.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin63.getBackground();
        animationDrawable.start();

        coin64 = findViewById(R.id.coinSesentaicuatro);
        coin64.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin64.getBackground();
        animationDrawable.start();

        coin65 = findViewById(R.id.coinSesentaicinco);
        coin65.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin65.getBackground();
        animationDrawable.start();

        coin66 = findViewById(R.id.coinSesentaiseis);
        coin66.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin66.getBackground();
        animationDrawable.start();

        coin67 = findViewById(R.id.coinSesentaisiete);
        coin67.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin67.getBackground();
        animationDrawable.start();

        coin68 = findViewById(R.id.coinSesentaiocho);
        coin68.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin68.getBackground();
        animationDrawable.start();

        coin69 = findViewById(R.id.coinSesentainueve);
        coin69.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin69.getBackground();
        animationDrawable.start();

        coin70 = findViewById(R.id.coinSetenta);
        coin70.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin70.getBackground();
        animationDrawable.start();

        coin71 = findViewById(R.id.coinSetentaiuno);
        coin71.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin71.getBackground();
        animationDrawable.start();

        coin72 = findViewById(R.id.coinSetentaidos);
        coin72.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin72.getBackground();
        animationDrawable.start();

        coin73 = findViewById(R.id.coinSetentaitres);
        coin73.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin73.getBackground();
        animationDrawable.start();

        coin74 = findViewById(R.id.coinSetentaicuatro);
        coin74.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin74.getBackground();
        animationDrawable.start();

        coin75 = findViewById(R.id.coinSetentaicinco);
        coin75.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin75.getBackground();
        animationDrawable.start();

        coin76 = findViewById(R.id.coinSetentaiseis);
        coin76.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin76.getBackground();
        animationDrawable.start();

        coin77 = findViewById(R.id.coinSetentaisiete);
        coin77.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin77.getBackground();
        animationDrawable.start();

        coin78 = findViewById(R.id.coinSetentaiocho);
        coin78.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin78.getBackground();
        animationDrawable.start();

        coin79 = findViewById(R.id.coinSetentainueve);
        coin79.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin79.getBackground();
        animationDrawable.start();

        coin80 = findViewById(R.id.coinOchenta);
        coin80.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin80.getBackground();
        animationDrawable.start();

        coin81 = findViewById(R.id.coinOchentaiuno);
        coin81.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin81.getBackground();
        animationDrawable.start();

        coin82 = findViewById(R.id.coinOchentaidos);
        coin82.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin82.getBackground();
        animationDrawable.start();

        coin83 = findViewById(R.id.coinOchentaitres);
        coin83.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin83.getBackground();
        animationDrawable.start();

        coin84 = findViewById(R.id.coinOchentaicuatro);
        coin84.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin84.getBackground();
        animationDrawable.start();

        coin85 = findViewById(R.id.coinOchentaicinco);
        coin85.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin85.getBackground();
        animationDrawable.start();

        coin86 = findViewById(R.id.coinOchentaiseis);
        coin86.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin86.getBackground();
        animationDrawable.start();

        coin87 = findViewById(R.id.coinOchentaisiete);
        coin87.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin87.getBackground();
        animationDrawable.start();

        coin88 = findViewById(R.id.coinOchentaiocho);
        coin88.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin88.getBackground();
        animationDrawable.start();

        coin89 = findViewById(R.id.coinOchentainueve);
        coin89.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin89.getBackground();
        animationDrawable.start();

        coin90 = findViewById(R.id.coinNoventa);
        coin90.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin90.getBackground();
        animationDrawable.start();

        coin91 = findViewById(R.id.coinNoventaiuno);
        coin91.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin91.getBackground();
        animationDrawable.start();

        coin92 = findViewById(R.id.coinNoventaidos);
        coin92.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin92.getBackground();
        animationDrawable.start();

        coin93 = findViewById(R.id.coinNoventaitres);
        coin93.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin93.getBackground();
        animationDrawable.start();

        coin94 = findViewById(R.id.coinNoventaicuatro);
        coin94.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin94.getBackground();
        animationDrawable.start();

        coin95 = findViewById(R.id.coinNoventaicinco);
        coin95.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin95.getBackground();
        animationDrawable.start();

        coin96 = findViewById(R.id.coinNoventaiseis);
        coin96.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin96.getBackground();
        animationDrawable.start();

        coin97 = findViewById(R.id.coinNoventaisiete);
        coin97.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin97.getBackground();
        animationDrawable.start();

        coin98 = findViewById(R.id.coinNoventaiocho);
        coin98.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin98.getBackground();
        animationDrawable.start();

        coin99 = findViewById(R.id.coinNoventainueve);
        coin99.setBackgroundResource(R.drawable.coin_animation);
        animationDrawable = (AnimationDrawable) coin99.getBackground();
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
        arrowEnemy11 = findViewById(R.id.arrow_enemy11);
        arrowEnemy12 = findViewById(R.id.arrow_enemy12);
        arrowEnemy13 = findViewById(R.id.arrow_enemy13);
        arrowEnemy14 = findViewById(R.id.arrow_enemy14);
        arrowEnemy15 = findViewById(R.id.arrow_enemy15);
        arrowEnemy16 = findViewById(R.id.arrow_enemy16);
        arrowEnemy17 = findViewById(R.id.arrow_enemy17);
        arrowEnemy18 = findViewById(R.id.arrow_enemy18);
        arrowEnemy19 = findViewById(R.id.arrow_enemy19);
        arrowEnemy20 = findViewById(R.id.arrow_enemy20);
        arrowEnemy21 = findViewById(R.id.arrow_enemy21);
        arrowEnemy22 = findViewById(R.id.arrow_enemy22);
        arrowEnemy23 = findViewById(R.id.arrow_enemy23);
        arrowEnemy24 = findViewById(R.id.arrow_enemy24);
        arrowEnemy25 = findViewById(R.id.arrow_enemy25);
        arrowEnemy26 = findViewById(R.id.arrow_enemy26);
        arrowEnemy27 = findViewById(R.id.arrow_enemy27);
        arrowEnemy28 = findViewById(R.id.arrow_enemy28);
        arrowEnemy29 = findViewById(R.id.arrow_enemy29);
        arrowEnemy30 = findViewById(R.id.arrow_enemy30);
        arrowEnemy31 = findViewById(R.id.arrow_enemy31);
        arrowEnemy32 = findViewById(R.id.arrow_enemy32);
        arrowEnemy33 = findViewById(R.id.arrow_enemy33);
        arrowEnemy34 = findViewById(R.id.arrow_enemy34);
        arrowEnemy35 = findViewById(R.id.arrow_enemy35);
        arrowEnemy36 = findViewById(R.id.arrow_enemy36);
        arrowEnemy37 = findViewById(R.id.arrow_enemy37);
        arrowEnemy38 = findViewById(R.id.arrow_enemy38);
        arrowEnemy39 = findViewById(R.id.arrow_enemy39);
        arrowEnemy40 = findViewById(R.id.arrow_enemy40);
        arrowEnemy41 = findViewById(R.id.arrow_enemy41);
        arrowEnemy42 = findViewById(R.id.arrow_enemy42);
        arrowEnemy43 = findViewById(R.id.arrow_enemy43);
        arrowEnemy44 = findViewById(R.id.arrow_enemy44);
        arrowEnemy45 = findViewById(R.id.arrow_enemy45);
        arrowEnemy46 = findViewById(R.id.arrow_enemy46);
        arrowEnemy47 = findViewById(R.id.arrow_enemy47);
        arrowEnemy48 = findViewById(R.id.arrow_enemy48);
        arrowEnemy49 = findViewById(R.id.arrow_enemy49);
        arrowEnemy50 = findViewById(R.id.arrow_enemy50);


        //        Instancias bird

        bird1 = new Bird(5000, point1, enemySpeed, 100);
        bird2 = new Bird(6000, point2, enemySpeed, 100);
        bird3 = new Bird(10000, point1, enemySpeed, 100);
        bird4 = new Bird(17500, point2, 33, 100);
        bird5 = new Bird(18000, point2, enemySpeed, 100);
        bird6 = new Bird(20000, point4, enemySpeed, 100);
        bird7 = new Bird(20000, point1, enemySpeed, 100);
        bird8 = new Bird(25500, point1, enemySpeed, 100);
        bird9 = new Bird(25500, point4, enemySpeed, 100);
        bird10 = new Bird(27500, point2, enemySpeed, 100);


        arrow = new ArrowEnemy(3000, point4, enemySpeed, sizeEnemy);
        arrow1 = new ArrowEnemy(4000, point3, enemySpeed, sizeEnemy);
        arrow2 = new ArrowEnemy(5000, point4, enemySpeed, sizeEnemy);
        arrow3 = new ArrowEnemy(20750, point1, 38, sizeEnemy);
        arrow4 = new ArrowEnemy(7000, point2, enemySpeed, sizeEnemy);
        arrow5 = new ArrowEnemy(8000, point4, enemySpeed, sizeEnemy);
        arrow6 = new ArrowEnemy(9000, point1, enemySpeed, sizeEnemy);
        arrow7 = new ArrowEnemy(10000, point4, enemySpeed, sizeEnemy);
        arrow8 = new ArrowEnemy(11000, point3, enemySpeed, sizeEnemy);
        arrow9 = new ArrowEnemy(12000, point1, enemySpeed, sizeEnemy);
        arrow10 = new ArrowEnemy(13000, point4, enemySpeed, sizeEnemy);

        arrow11 = new ArrowEnemy(14000, point3, enemySpeed, sizeEnemy);
        arrow12 = new ArrowEnemy(15000, point4, enemySpeed, sizeEnemy);
        arrow13 = new ArrowEnemy(16000, point3 + 80, enemySpeed, sizeEnemy);
        arrow14 = new ArrowEnemy(17000, point4, enemySpeed, sizeEnemy);
        arrow15 = new ArrowEnemy(18000, point3, enemySpeed, sizeEnemy);
        arrow16 = new ArrowEnemy(18000, point1, enemySpeed, sizeEnemy);
        arrow17 = new ArrowEnemy(14000, point1 - 100, enemySpeed, sizeEnemy);
        arrow18 = new ArrowEnemy(15000, point1 - 100, enemySpeed, sizeEnemy);
        arrow19 = new ArrowEnemy(16000, point1 - 100, enemySpeed, sizeEnemy);
        arrow20 = new ArrowEnemy(17000, point1 - 100, enemySpeed, sizeEnemy);
        arrow21 = new ArrowEnemy(19000, point4, enemySpeed, sizeEnemy);
        arrow22 = new ArrowEnemy(19500, point4, enemySpeed, sizeEnemy);
        arrow23 = new ArrowEnemy(19000, point1, enemySpeed, sizeEnemy);
        arrow24 = new ArrowEnemy(19500, point1, enemySpeed, sizeEnemy);

        arrow25 = new ArrowEnemy(20700, point1, enemySpeed, sizeEnemy);
        arrow26 = new ArrowEnemy(20700, point2, enemySpeed, sizeEnemy);
        arrow27 = new ArrowEnemy(21000, point1, enemySpeed, sizeEnemy);
        arrow28 = new ArrowEnemy(21000, point2, enemySpeed, sizeEnemy);
        arrow29 = new ArrowEnemy(21700, point4, enemySpeed, sizeEnemy);
        arrow30 = new ArrowEnemy(21700, point3, enemySpeed, sizeEnemy);
        arrow31 = new ArrowEnemy(22000, point4, enemySpeed, sizeEnemy);
        arrow32 = new ArrowEnemy(22000, point3, enemySpeed, sizeEnemy);

        arrow33 = new ArrowEnemy(22700, point1, enemySpeed, sizeEnemy);
        arrow34 = new ArrowEnemy(22700, point2, enemySpeed, sizeEnemy);
        arrow35 = new ArrowEnemy(23000, point1, enemySpeed, sizeEnemy);
        arrow36 = new ArrowEnemy(23000, point2, enemySpeed, sizeEnemy);
        arrow37 = new ArrowEnemy(23700, point4, enemySpeed, sizeEnemy);
        arrow38 = new ArrowEnemy(23700, point3, enemySpeed, sizeEnemy);
        arrow39 = new ArrowEnemy(24000, point4, enemySpeed, sizeEnemy);
        arrow40 = new ArrowEnemy(24000, point3, enemySpeed, sizeEnemy);

        arrow41 = new ArrowEnemy(44000, point4, enemySpeed, sizeEnemy);
        arrow42 = new ArrowEnemy(45000, point3, enemySpeed, sizeEnemy);
        arrow43 = new ArrowEnemy(46000, point4, enemySpeed, sizeEnemy);
        arrow44 = new ArrowEnemy(47000, point1, enemySpeed, sizeEnemy);
        arrow45 = new ArrowEnemy(48000, point4, enemySpeed, sizeEnemy);
        arrow46 = new ArrowEnemy(49000, point2, enemySpeed, sizeEnemy);
        arrow47 = new ArrowEnemy(50000, point4, enemySpeed, sizeEnemy);
        arrow48 = new ArrowEnemy(51000, point3, enemySpeed, sizeEnemy);
        arrow49 = new ArrowEnemy(52000, point1, enemySpeed, sizeEnemy);
        arrow50 = new ArrowEnemy(53000, point4, enemySpeed, sizeEnemy);


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
        Coins coin_20 = new Coins(11500, coinPoint4, enemySpeed, 40, MainActivity.this);
        Coins coin_21 = new Coins(11700, coinPoint4, enemySpeed, 40, MainActivity.this);
        Coins coin_22 = new Coins(11900, coinPoint4, enemySpeed, 40, MainActivity.this);
        Coins coin_23 = new Coins(12400, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_24 = new Coins(12600, coinPoint1, enemySpeed, 40, MainActivity.this);

        Coins coin_25 = new Coins(12800, coinPoint1, enemySpeed, 40, MainActivity.this);
        Coins coin_26 = new Coins(13000, coinPoint1, enemySpeed, 40, MainActivity.this);
        Coins coin_27 = new Coins(13000, coinPoint1, enemySpeed, 40, MainActivity.this);
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
        Coins coin_50 = new Coins(20070, coinPoint2 + 63, enemySpeed, 40, MainActivity.this);


        Coins coin_51 = new Coins(14000, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_52 = new Coins(14400, coinPoint3, enemySpeed, 40, MainActivity.this);
        Coins coin_53 = new Coins(14800, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_54 = new Coins(15200, coinPoint3, enemySpeed, 40, MainActivity.this);
        Coins coin_55 = new Coins(15600, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_56 = new Coins(16000, coinPoint3, enemySpeed, 40, MainActivity.this);
        Coins coin_57 = new Coins(16400, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_58 = new Coins(16800, coinPoint3, enemySpeed, 40, MainActivity.this);
        Coins coin_59 = new Coins(17200, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_60 = new Coins(17600, coinPoint3, enemySpeed, 40, MainActivity.this);
        Coins coin_61 = new Coins(18000, coinPoint4, enemySpeed, 40, MainActivity.this);
        Coins coin_62 = new Coins(18300, coinPoint4, enemySpeed, 40, MainActivity.this);
        Coins coin_63 = new Coins(18600, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_64 = new Coins(21190, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_65 = new Coins(21270, coinPoint3, enemySpeed, 40, MainActivity.this);
        Coins coin_66 = new Coins(21350, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_67 = new Coins(21430, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_68 = new Coins(21510, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_69 = new Coins(21590, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_70 = new Coins(21670, coinPoint1, enemySpeed, 40, MainActivity.this);
        Coins coin_71 = new Coins(21750, coinPoint1, enemySpeed, 40, MainActivity.this);
        Coins coin_72 = new Coins(21830, coinPoint1, enemySpeed, 40, MainActivity.this);
        Coins coin_73 = new Coins(21910, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_74 = new Coins(21990, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_75 = new Coins(22070, coinPoint1, enemySpeed, 40, MainActivity.this);
        Coins coin_76 = new Coins(22150, coinPoint1, enemySpeed, 40, MainActivity.this);
        Coins coin_77 = new Coins(22230, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_78 = new Coins(22310, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_79 = new Coins(22390, coinPoint1, enemySpeed, 40, MainActivity.this);
        Coins coin_80 = new Coins(22470, coinPoint1, enemySpeed, 40, MainActivity.this);
        Coins coin_81 = new Coins(22550, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_82 = new Coins(22630, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_83 = new Coins(22710, coinPoint1, enemySpeed, 40, MainActivity.this);
        Coins coin_84 = new Coins(22790, coinPoint1, enemySpeed, 40, MainActivity.this);
        Coins coin_85 = new Coins(22870, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_86 = new Coins(22950, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_87 = new Coins(23030, coinPoint1, enemySpeed, 40, MainActivity.this);
        Coins coin_88 = new Coins(23110, coinPoint1, enemySpeed, 40, MainActivity.this);
        Coins coin_89 = new Coins(23190, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_90 = new Coins(23270, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_91 = new Coins(23350, coinPoint1, enemySpeed, 40, MainActivity.this);
        Coins coin_92 = new Coins(23430, coinPoint1, enemySpeed, 40, MainActivity.this);
        Coins coin_93 = new Coins(23510, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_94 = new Coins(23590, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_95 = new Coins(23670, coinPoint1, enemySpeed, 40, MainActivity.this);
        Coins coin_96 = new Coins(23750, coinPoint1, enemySpeed, 40, MainActivity.this);
        Coins coin_97 = new Coins(23830, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_98 = new Coins(23910, coinPoint2, enemySpeed, 40, MainActivity.this);
        Coins coin_99 = new Coins(23990, coinPoint3, enemySpeed, 40, MainActivity.this);

        prueba.setText("0/99");

        birdEnemy1.setX(bird1.getX());
        birdEnemy1.setY(bird1.getY());

        birdEnemy2.setX(bird2.getX());
        birdEnemy2.setY(bird2.getY());

        birdEnemy3.setX(bird3.getX());
        birdEnemy3.setY(bird3.getY());

        birdEnemy4.setX(bird4.getX());
        birdEnemy4.setY(bird4.getY());

        birdEnemy5.setX(bird5.getX());
        birdEnemy5.setY(bird5.getY());

        birdEnemy6.setX(bird6.getX());
        birdEnemy6.setY(bird6.getY());

        birdEnemy7.setX(bird7.getX());
        birdEnemy7.setY(bird7.getY());

        birdEnemy8.setX(bird8.getX());
        birdEnemy8.setY(bird8.getY());

        birdEnemy9.setX(bird9.getX());
        birdEnemy9.setY(bird9.getY());

        birdEnemy10.setX(bird10.getX());
        birdEnemy10.setY(bird10.getY());


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

        // Continue the pattern for coins 51 to 99
        coin51.setX(coin_51.getX());
        coin51.setY(coin_51.getY());

        coin52.setX(coin_52.getX());
        coin52.setY(coin_52.getY());

        coin53.setX(coin_53.getX());
        coin53.setY(coin_53.getY());

        coin54.setX(coin_54.getX());
        coin54.setY(coin_54.getY());

        coin55.setX(coin_55.getX());
        coin55.setY(coin_55.getY());

        coin56.setX(coin_56.getX());
        coin56.setY(coin_56.getY());

        coin57.setX(coin_57.getX());
        coin57.setY(coin_57.getY());

        coin58.setX(coin_58.getX());
        coin58.setY(coin_58.getY());

        coin59.setX(coin_59.getX());
        coin59.setY(coin_59.getY());

        coin60.setX(coin_60.getX());
        coin60.setY(coin_60.getY());

        coin61.setX(coin_61.getX());
        coin61.setY(coin_61.getY());

        coin62.setX(coin_62.getX());
        coin62.setY(coin_62.getY());

        coin63.setX(coin_63.getX());
        coin63.setY(coin_63.getY());

        coin64.setX(coin_64.getX());
        coin64.setY(coin_64.getY());

        coin65.setX(coin_65.getX());
        coin65.setY(coin_65.getY());

        coin66.setX(coin_66.getX());
        coin66.setY(coin_66.getY());

        coin67.setX(coin_67.getX());
        coin67.setY(coin_67.getY());

        coin68.setX(coin_68.getX());
        coin68.setY(coin_68.getY());

        coin69.setX(coin_69.getX());
        coin69.setY(coin_69.getY());

        coin70.setX(coin_70.getX());
        coin70.setY(coin_70.getY());

        coin71.setX(coin_71.getX());
        coin71.setY(coin_71.getY());

        coin72.setX(coin_72.getX());
        coin72.setY(coin_72.getY());

        coin73.setX(coin_73.getX());
        coin73.setY(coin_73.getY());

        coin74.setX(coin_74.getX());
        coin74.setY(coin_74.getY());

        coin75.setX(coin_75.getX());
        coin75.setY(coin_75.getY());

        coin76.setX(coin_76.getX());
        coin76.setY(coin_76.getY());

        coin77.setX(coin_77.getX());
        coin77.setY(coin_77.getY());

        coin78.setX(coin_78.getX());
        coin78.setY(coin_78.getY());

        coin79.setX(coin_79.getX());
        coin79.setY(coin_79.getY());

        coin80.setX(coin_80.getX());
        coin80.setY(coin_80.getY());

        coin81.setX(coin_81.getX());
        coin81.setY(coin_81.getY());

        coin82.setX(coin_82.getX());
        coin82.setY(coin_82.getY());

        coin83.setX(coin_83.getX());
        coin83.setY(coin_83.getY());

        coin84.setX(coin_84.getX());
        coin84.setY(coin_84.getY());

        coin85.setX(coin_85.getX());
        coin85.setY(coin_85.getY());

        coin86.setX(coin_86.getX());
        coin86.setY(coin_86.getY());

        coin87.setX(coin_87.getX());
        coin87.setY(coin_87.getY());

        coin88.setX(coin_88.getX());
        coin88.setY(coin_88.getY());

        coin89.setX(coin_89.getX());
        coin89.setY(coin_89.getY());

        coin90.setX(coin_90.getX());
        coin90.setY(coin_90.getY());

        coin91.setX(coin_91.getX());
        coin91.setY(coin_91.getY());

        coin92.setX(coin_92.getX());
        coin92.setY(coin_92.getY());

        coin93.setX(coin_93.getX());
        coin93.setY(coin_93.getY());

        coin94.setX(coin_94.getX());
        coin94.setY(coin_94.getY());

        coin95.setX(coin_95.getX());
        coin95.setY(coin_95.getY());

        coin96.setX(coin_96.getX());
        coin96.setY(coin_96.getY());

        coin97.setX(coin_97.getX());
        coin97.setY(coin_97.getY());

        coin98.setX(coin_98.getX());
        coin98.setY(coin_98.getY());

        coin99.setX(coin_99.getX());
        coin99.setY(coin_99.getY());


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

        arrowEnemy11.setX(arrow11.getX());
        arrowEnemy11.setY(arrow11.getY());

        arrowEnemy12.setX(arrow12.getX());
        arrowEnemy12.setY(arrow12.getY());

        arrowEnemy13.setX(arrow13.getX());
        arrowEnemy13.setY(arrow13.getY());

        arrowEnemy14.setX(arrow14.getX());
        arrowEnemy14.setY(arrow14.getY());

        arrowEnemy15.setX(arrow15.getX());
        arrowEnemy15.setY(arrow15.getY());

        arrowEnemy16.setX(arrow16.getX());
        arrowEnemy16.setY(arrow16.getY());

        arrowEnemy17.setX(arrow17.getX());
        arrowEnemy17.setY(arrow17.getY());

        arrowEnemy18.setX(arrow18.getX());
        arrowEnemy18.setY(arrow18.getY());

        arrowEnemy19.setX(arrow19.getX());
        arrowEnemy19.setY(arrow19.getY());

        arrowEnemy20.setX(arrow20.getX());
        arrowEnemy20.setY(arrow20.getY());

        arrowEnemy21.setX(arrow21.getX());
        arrowEnemy21.setY(arrow21.getY());

        arrowEnemy22.setX(arrow22.getX());
        arrowEnemy22.setY(arrow22.getY());

        arrowEnemy23.setX(arrow23.getX());
        arrowEnemy23.setY(arrow23.getY());

        arrowEnemy24.setX(arrow24.getX());
        arrowEnemy24.setY(arrow24.getY());

        arrowEnemy25.setX(arrow25.getX());
        arrowEnemy25.setY(arrow25.getY());

        arrowEnemy26.setX(arrow26.getX());
        arrowEnemy26.setY(arrow26.getY());

        arrowEnemy27.setX(arrow27.getX());
        arrowEnemy27.setY(arrow27.getY());

        arrowEnemy28.setX(arrow28.getX());
        arrowEnemy28.setY(arrow28.getY());

        arrowEnemy29.setX(arrow29.getX());
        arrowEnemy29.setY(arrow29.getY());

        arrowEnemy30.setX(arrow30.getX());
        arrowEnemy30.setY(arrow30.getY());

        arrowEnemy31.setX(arrow31.getX());
        arrowEnemy31.setY(arrow31.getY());

        arrowEnemy32.setX(arrow32.getX());
        arrowEnemy32.setY(arrow32.getY());

        arrowEnemy33.setX(arrow33.getX());
        arrowEnemy33.setY(arrow33.getY());

        arrowEnemy34.setX(arrow34.getX());
        arrowEnemy34.setY(arrow34.getY());

        arrowEnemy35.setX(arrow35.getX());
        arrowEnemy35.setY(arrow35.getY());

        arrowEnemy36.setX(arrow36.getX());
        arrowEnemy36.setY(arrow36.getY());

        arrowEnemy37.setX(arrow37.getX());
        arrowEnemy37.setY(arrow37.getY());

        arrowEnemy38.setX(arrow38.getX());
        arrowEnemy38.setY(arrow38.getY());

        arrowEnemy39.setX(arrow39.getX());
        arrowEnemy39.setY(arrow39.getY());

        arrowEnemy40.setX(arrow40.getX());
        arrowEnemy40.setY(arrow40.getY());

        arrowEnemy41.setX(arrow41.getX());
        arrowEnemy41.setY(arrow41.getY());

        arrowEnemy42.setX(arrow42.getX());
        arrowEnemy42.setY(arrow42.getY());

        arrowEnemy43.setX(arrow43.getX());
        arrowEnemy43.setY(arrow43.getY());

        arrowEnemy44.setX(arrow44.getX());
        arrowEnemy44.setY(arrow44.getY());

        arrowEnemy45.setX(arrow45.getX());
        arrowEnemy45.setY(arrow45.getY());

        arrowEnemy46.setX(arrow46.getX());
        arrowEnemy46.setY(arrow46.getY());

        arrowEnemy47.setX(arrow47.getX());
        arrowEnemy47.setY(arrow47.getY());

        arrowEnemy48.setX(arrow48.getX());
        arrowEnemy48.setY(arrow48.getY());

        arrowEnemy49.setX(arrow49.getX());
        arrowEnemy49.setY(arrow49.getY());

        arrowEnemy50.setX(arrow50.getX());
        arrowEnemy50.setY(arrow50.getY());


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
            @SuppressLint("deprecation")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        if (!gameStarted) {
                            startGame();
                            mediaPlayer.start();

                        }
                        jump();
                        break;
                    case MotionEvent.ACTION_UP:
                        // Se hace nada al soltar
                        break;
                }

                return true;
            }

            private void startGame() {
                // Reproducir la música
                gameStarted = true;
                birdSpeed = -1;
                birdY = (int) character.getY();
                handler.postDelayed(gameRunnable, 20);
            }

            private void jump() {
                birdSpeed = -32;
            }

            private void SetHeart() {
                if (!isLogicExecuted) {
                    if (player.getLife() == 2) {
                        fullHeart3.setVisibility(View.INVISIBLE);
                    } else if (player.getLife() == 1) {
                        fullHeart2.setVisibility(View.INVISIBLE);
                        fullHeart3.setVisibility(View.INVISIBLE);
                    } else if (player.getLife() == 0) {
                        fullHeart1.setVisibility(View.INVISIBLE);
                        fullHeart2.setVisibility(View.INVISIBLE);
                        fullHeart3.setVisibility(View.INVISIBLE);



                        animationCoin.clearAnimation();
                        coin1.clearAnimation();
                        coin2.clearAnimation();
                        coin3.clearAnimation();
                        coin4.clearAnimation();
                        coin5.clearAnimation();
                        coin6.clearAnimation();
                        coin7.clearAnimation();
                        coin8.clearAnimation();
                        coin9.clearAnimation();
                        coin10.clearAnimation();
                        coin11.clearAnimation();
                        coin12.clearAnimation();
                        coin13.clearAnimation();
                        coin14.clearAnimation();
                        coin15.clearAnimation();
                        coin16.clearAnimation();
                        coin17.clearAnimation();
                        coin18.clearAnimation();
                        coin19.clearAnimation();
                        coin20.clearAnimation();
                        coin21.clearAnimation();
                        coin22.clearAnimation();
                        coin23.clearAnimation();
                        coin24.clearAnimation();
                        coin25.clearAnimation();
                        coin26.clearAnimation();
                        coin27.clearAnimation();
                        coin28.clearAnimation();
                        coin29.clearAnimation();
                        coin30.clearAnimation();
                        coin31.clearAnimation();
                        coin32.clearAnimation();
                        coin33.clearAnimation();
                        coin34.clearAnimation();
                        coin35.clearAnimation();
                        coin36.clearAnimation();
                        coin37.clearAnimation();
                        coin38.clearAnimation();
                        coin39.clearAnimation();
                        coin40.clearAnimation();
                        coin41.clearAnimation();
                        coin42.clearAnimation();
                        coin43.clearAnimation();
                        coin44.clearAnimation();
                        coin45.clearAnimation();
                        coin46.clearAnimation();
                        coin47.clearAnimation();
                        coin48.clearAnimation();
                        coin49.clearAnimation();
                        coin50.clearAnimation();
                        coin51.clearAnimation();
                        coin52.clearAnimation();
                        coin53.clearAnimation();
                        coin54.clearAnimation();
                        coin55.clearAnimation();
                        coin56.clearAnimation();
                        coin57.clearAnimation();
                        coin58.clearAnimation();
                        coin59.clearAnimation();
                        coin60.clearAnimation();
                        coin61.clearAnimation();
                        coin62.clearAnimation();
                        coin63.clearAnimation();
                        coin64.clearAnimation();
                        coin65.clearAnimation();
                        coin66.clearAnimation();
                        coin67.clearAnimation();
                        coin68.clearAnimation();
                        coin69.clearAnimation();
                        coin70.clearAnimation();
                        coin71.clearAnimation();
                        coin72.clearAnimation();
                        coin73.clearAnimation();
                        coin74.clearAnimation();
                        coin75.clearAnimation();
                        coin76.clearAnimation();
                        coin77.clearAnimation();
                        coin78.clearAnimation();
                        coin79.clearAnimation();
                        coin80.clearAnimation();
                        coin81.clearAnimation();
                        coin82.clearAnimation();
                        coin83.clearAnimation();
                        coin84.clearAnimation();
                        coin85.clearAnimation();
                        coin86.clearAnimation();
                        coin87.clearAnimation();
                        coin88.clearAnimation();
                        coin89.clearAnimation();
                        coin90.clearAnimation();
                        coin91.clearAnimation();
                        coin92.clearAnimation();
                        coin93.clearAnimation();
                        coin94.clearAnimation();
                        coin95.clearAnimation();
                        coin96.clearAnimation();
                        coin97.clearAnimation();
                        coin98.clearAnimation();
                        coin99.clearAnimation();


                        arrowEnemy.clearAnimation();
                        arrowEnemy1.clearAnimation();
                        arrowEnemy2.clearAnimation();
                        arrowEnemy3.clearAnimation();
                        arrowEnemy4.clearAnimation();
                        arrowEnemy5.clearAnimation();
                        arrowEnemy6.clearAnimation();
                        arrowEnemy7.clearAnimation();
                        arrowEnemy8.clearAnimation();
                        arrowEnemy9.clearAnimation();
                        arrowEnemy10.clearAnimation();
                        arrowEnemy11.clearAnimation();
                        arrowEnemy12.clearAnimation();
                        arrowEnemy13.clearAnimation();
                        arrowEnemy14.clearAnimation();
                        arrowEnemy15.clearAnimation();
                        arrowEnemy16.clearAnimation();
                        arrowEnemy17.clearAnimation();
                        arrowEnemy18.clearAnimation();
                        arrowEnemy19.clearAnimation();
                        arrowEnemy20.clearAnimation();
                        arrowEnemy21.clearAnimation();
                        arrowEnemy22.clearAnimation();
                        arrowEnemy23.clearAnimation();
                        arrowEnemy24.clearAnimation();
                        arrowEnemy25.clearAnimation();
                        arrowEnemy26.clearAnimation();
                        arrowEnemy27.clearAnimation();
                        arrowEnemy28.clearAnimation();
                        arrowEnemy29.clearAnimation();
                        arrowEnemy30.clearAnimation();
                        arrowEnemy31.clearAnimation();
                        arrowEnemy32.clearAnimation();
                        arrowEnemy33.clearAnimation();
                        arrowEnemy34.clearAnimation();
                        arrowEnemy35.clearAnimation();
                        arrowEnemy36.clearAnimation();
                        arrowEnemy37.clearAnimation();
                        arrowEnemy38.clearAnimation();
                        arrowEnemy39.clearAnimation();
                        arrowEnemy40.clearAnimation();
                        arrowEnemy41.clearAnimation();
                        arrowEnemy42.clearAnimation();
                        arrowEnemy43.clearAnimation();
                        arrowEnemy44.clearAnimation();
                        arrowEnemy45.clearAnimation();
                        arrowEnemy46.clearAnimation();
                        arrowEnemy47.clearAnimation();
                        arrowEnemy48.clearAnimation();
                        arrowEnemy49.clearAnimation();
                        arrowEnemy50.clearAnimation();
                        backgroundImage.clearAnimation();
                        backgroundImage2.clearAnimation();
                        backgroundImage3.clearAnimation();
                        mediaPlayer.stop();
                        coinSound.stop();
                        hitSound.stop();
                        arrowEnemy.clearAnimation();
                        arrowEnemy1.clearAnimation();
                        arrowEnemy2.clearAnimation();
                        arrowEnemy3.clearAnimation();
                        arrowEnemy4.clearAnimation();
                        arrowEnemy5.clearAnimation();
                        arrowEnemy6.clearAnimation();
                        arrowEnemy7.clearAnimation();
                        arrowEnemy8.clearAnimation();
                        arrowEnemy9.clearAnimation();
                        arrowEnemy10.clearAnimation();
                        arrowEnemy11.clearAnimation();
                        arrowEnemy12.clearAnimation();
                        arrowEnemy13.clearAnimation();
                        arrowEnemy14.clearAnimation();
                        arrowEnemy15.clearAnimation();
                        arrowEnemy16.clearAnimation();
                        arrowEnemy17.clearAnimation();
                        arrowEnemy18.clearAnimation();
                        arrowEnemy19.clearAnimation();
                        arrowEnemy20.clearAnimation();
                        arrowEnemy21.clearAnimation();
                        arrowEnemy22.clearAnimation();
                        arrowEnemy23.clearAnimation();
                        arrowEnemy24.clearAnimation();
                        arrowEnemy25.clearAnimation();
                        arrowEnemy26.clearAnimation();
                        arrowEnemy27.clearAnimation();
                        arrowEnemy28.clearAnimation();
                        arrowEnemy29.clearAnimation();
                        arrowEnemy30.clearAnimation();
                        arrowEnemy31.clearAnimation();
                        arrowEnemy32.clearAnimation();
                        arrowEnemy33.clearAnimation();
                        arrowEnemy34.clearAnimation();
                        arrowEnemy35.clearAnimation();
                        arrowEnemy36.clearAnimation();
                        arrowEnemy37.clearAnimation();
                        arrowEnemy38.clearAnimation();
                        arrowEnemy39.clearAnimation();
                        arrowEnemy40.clearAnimation();
                        arrowEnemy41.clearAnimation();
                        arrowEnemy42.clearAnimation();
                        arrowEnemy43.clearAnimation();
                        arrowEnemy44.clearAnimation();
                        arrowEnemy45.clearAnimation();
                        arrowEnemy46.clearAnimation();
                        arrowEnemy47.clearAnimation();
                        arrowEnemy48.clearAnimation();
                        arrowEnemy49.clearAnimation();
                        arrowEnemy50.clearAnimation();

                        startActivity(intentGameOver);
                        finish();
                        // Marcar la lógica como ejecutada
                        isLogicExecuted = true;
                    }

                }
            }




            private final Runnable gameRunnable = new Runnable() {
                @Override
                public void run() {

                    SetHeart();

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

                    bird1.moveEnemy();
                    birdEnemy1.setX(bird1.getX());
                    birdEnemy1.setY(bird1.getY());

                    bird2.moveEnemy();
                    birdEnemy2.setX(bird2.getX());
                    birdEnemy2.setY(bird2.getY());

                    bird3.moveEnemy();
                    birdEnemy3.setX(bird3.getX());
                    birdEnemy3.setY(bird3.getY());

                    bird4.moveEnemy();
                    birdEnemy4.setX(bird4.getX());
                    birdEnemy4.setY(bird4.getY());

                    bird5.moveEnemy();
                    birdEnemy5.setX(bird5.getX());
                    birdEnemy5.setY(bird5.getY());

                    bird6.moveEnemy();
                    birdEnemy6.setX(bird6.getX());
                    birdEnemy6.setY(bird6.getY());

                    bird7.moveEnemy();
                    birdEnemy7.setX(bird7.getX());
                    birdEnemy7.setY(bird7.getY());

                    bird8.moveEnemy();
                    birdEnemy8.setX(bird8.getX());
                    birdEnemy8.setY(bird8.getY());

                    bird9.moveEnemy();
                    birdEnemy9.setX(bird9.getX());
                    birdEnemy9.setY(bird9.getY());

                    bird10.moveEnemy();
                    birdEnemy10.setX(bird10.getX());
                    birdEnemy10.setY(bird10.getY());


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

                    // Continue the pattern for moving and updating positions for coins 51 to 99
                    coin_51.moveEnemy();
                    coin51.setX(coin_51.getX());
                    coin51.setY(coin_51.getY());

                    coin_52.moveEnemy();
                    coin52.setX(coin_52.getX());
                    coin52.setY(coin_52.getY());

                    coin_53.moveEnemy();
                    coin53.setX(coin_53.getX());
                    coin53.setY(coin_53.getY());

                    coin_54.moveEnemy();
                    coin54.setX(coin_54.getX());
                    coin54.setY(coin_54.getY());

                    coin_55.moveEnemy();
                    coin55.setX(coin_55.getX());
                    coin55.setY(coin_55.getY());

                    coin_56.moveEnemy();
                    coin56.setX(coin_56.getX());
                    coin56.setY(coin_56.getY());

                    coin_57.moveEnemy();
                    coin57.setX(coin_57.getX());
                    coin57.setY(coin_57.getY());

                    coin_58.moveEnemy();
                    coin58.setX(coin_58.getX());
                    coin58.setY(coin_58.getY());

                    coin_59.moveEnemy();
                    coin59.setX(coin_59.getX());
                    coin59.setY(coin_59.getY());

                    coin_60.moveEnemy();
                    coin60.setX(coin_60.getX());
                    coin60.setY(coin_60.getY());

                    coin_61.moveEnemy();
                    coin61.setX(coin_61.getX());
                    coin61.setY(coin_61.getY());

                    coin_62.moveEnemy();
                    coin62.setX(coin_62.getX());
                    coin62.setY(coin_62.getY());

                    coin_63.moveEnemy();
                    coin63.setX(coin_63.getX());
                    coin63.setY(coin_63.getY());

                    coin_64.moveEnemy();
                    coin64.setX(coin_64.getX());
                    coin64.setY(coin_64.getY());

                    coin_65.moveEnemy();
                    coin65.setX(coin_65.getX());
                    coin65.setY(coin_65.getY());

                    coin_66.moveEnemy();
                    coin66.setX(coin_66.getX());
                    coin66.setY(coin_66.getY());

                    coin_67.moveEnemy();
                    coin67.setX(coin_67.getX());
                    coin67.setY(coin_67.getY());

                    coin_68.moveEnemy();
                    coin68.setX(coin_68.getX());
                    coin68.setY(coin_68.getY());

                    coin_69.moveEnemy();
                    coin69.setX(coin_69.getX());
                    coin69.setY(coin_69.getY());

                    coin_70.moveEnemy();
                    coin70.setX(coin_70.getX());
                    coin70.setY(coin_70.getY());

                    coin_71.moveEnemy();
                    coin71.setX(coin_71.getX());
                    coin71.setY(coin_71.getY());

                    coin_72.moveEnemy();
                    coin72.setX(coin_72.getX());
                    coin72.setY(coin_72.getY());

                    coin_73.moveEnemy();
                    coin73.setX(coin_73.getX());
                    coin73.setY(coin_73.getY());

                    coin_74.moveEnemy();
                    coin74.setX(coin_74.getX());
                    coin74.setY(coin_74.getY());

                    coin_75.moveEnemy();
                    coin75.setX(coin_75.getX());
                    coin75.setY(coin_75.getY());

                    coin_76.moveEnemy();
                    coin76.setX(coin_76.getX());
                    coin76.setY(coin_76.getY());

                    coin_77.moveEnemy();
                    coin77.setX(coin_77.getX());
                    coin77.setY(coin_77.getY());

                    coin_78.moveEnemy();
                    coin78.setX(coin_78.getX());
                    coin78.setY(coin_78.getY());

                    coin_79.moveEnemy();
                    coin79.setX(coin_79.getX());
                    coin79.setY(coin_79.getY());

                    coin_80.moveEnemy();
                    coin80.setX(coin_80.getX());
                    coin80.setY(coin_80.getY());

                    coin_81.moveEnemy();
                    coin81.setX(coin_81.getX());
                    coin81.setY(coin_81.getY());

                    coin_82.moveEnemy();
                    coin82.setX(coin_82.getX());
                    coin82.setY(coin_82.getY());

                    coin_83.moveEnemy();
                    coin83.setX(coin_83.getX());
                    coin83.setY(coin_83.getY());

                    coin_84.moveEnemy();
                    coin84.setX(coin_84.getX());
                    coin84.setY(coin_84.getY());

                    coin_85.moveEnemy();
                    coin85.setX(coin_85.getX());
                    coin85.setY(coin_85.getY());

                    coin_86.moveEnemy();
                    coin86.setX(coin_86.getX());
                    coin86.setY(coin_86.getY());

                    coin_87.moveEnemy();
                    coin87.setX(coin_87.getX());
                    coin87.setY(coin_87.getY());

                    coin_88.moveEnemy();
                    coin88.setX(coin_88.getX());
                    coin88.setY(coin_88.getY());

                    coin_89.moveEnemy();
                    coin89.setX(coin_89.getX());
                    coin89.setY(coin_89.getY());

                    coin_90.moveEnemy();
                    coin90.setX(coin_90.getX());
                    coin90.setY(coin_90.getY());

                    coin_91.moveEnemy();
                    coin91.setX(coin_91.getX());
                    coin91.setY(coin_91.getY());

                    coin_92.moveEnemy();
                    coin92.setX(coin_92.getX());
                    coin92.setY(coin_92.getY());

                    coin_93.moveEnemy();
                    coin93.setX(coin_93.getX());
                    coin93.setY(coin_93.getY());

                    coin_94.moveEnemy();
                    coin94.setX(coin_94.getX());
                    coin94.setY(coin_94.getY());

                    coin_95.moveEnemy();
                    coin95.setX(coin_95.getX());
                    coin95.setY(coin_95.getY());

                    coin_96.moveEnemy();
                    coin96.setX(coin_96.getX());
                    coin96.setY(coin_96.getY());

                    coin_97.moveEnemy();
                    coin97.setX(coin_97.getX());
                    coin97.setY(coin_97.getY());

                    coin_98.moveEnemy();
                    coin98.setX(coin_98.getX());
                    coin98.setY(coin_98.getY());

                    coin_99.moveEnemy();
                    coin99.setX(coin_99.getX());
                    coin99.setY(coin_99.getY());


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

                    arrow11.moveEnemy();
                    arrowEnemy11.setX(arrow11.getX());
                    arrowEnemy11.setY(arrow11.getY());

                    arrow12.moveEnemy();
                    arrowEnemy12.setX(arrow12.getX());
                    arrowEnemy12.setY(arrow12.getY());

                    arrow13.moveEnemy();
                    arrowEnemy13.setX(arrow13.getX());
                    arrowEnemy13.setY(arrow13.getY());

                    arrow14.moveEnemy();
                    arrowEnemy14.setX(arrow14.getX());
                    arrowEnemy14.setY(arrow14.getY());

                    arrow15.moveEnemy();
                    arrowEnemy15.setX(arrow15.getX());
                    arrowEnemy15.setY(arrow15.getY());

                    arrow16.moveEnemy();
                    arrowEnemy16.setX(arrow16.getX());
                    arrowEnemy16.setY(arrow16.getY());

                    arrow17.moveEnemy();
                    arrowEnemy17.setX(arrow17.getX());
                    arrowEnemy17.setY(arrow17.getY());

                    arrow18.moveEnemy();
                    arrowEnemy18.setX(arrow18.getX());
                    arrowEnemy18.setY(arrow18.getY());

                    arrow19.moveEnemy();
                    arrowEnemy19.setX(arrow19.getX());
                    arrowEnemy19.setY(arrow19.getY());

                    arrow20.moveEnemy();
                    arrowEnemy20.setX(arrow20.getX());
                    arrowEnemy20.setY(arrow20.getY());

                    arrow21.moveEnemy();
                    arrowEnemy21.setX(arrow21.getX());
                    arrowEnemy21.setY(arrow21.getY());

                    arrow22.moveEnemy();
                    arrowEnemy22.setX(arrow22.getX());
                    arrowEnemy22.setY(arrow22.getY());

                    arrow23.moveEnemy();
                    arrowEnemy23.setX(arrow23.getX());
                    arrowEnemy23.setY(arrow23.getY());

                    arrow24.moveEnemy();
                    arrowEnemy24.setX(arrow24.getX());
                    arrowEnemy24.setY(arrow24.getY());

                    arrow25.moveEnemy();
                    arrowEnemy25.setX(arrow25.getX());
                    arrowEnemy25.setY(arrow25.getY());

                    arrow26.moveEnemy();
                    arrowEnemy26.setX(arrow26.getX());
                    arrowEnemy26.setY(arrow26.getY());

                    arrow27.moveEnemy();
                    arrowEnemy27.setX(arrow27.getX());
                    arrowEnemy27.setY(arrow27.getY());

                    arrow28.moveEnemy();
                    arrowEnemy28.setX(arrow28.getX());
                    arrowEnemy28.setY(arrow28.getY());

                    arrow29.moveEnemy();
                    arrowEnemy29.setX(arrow29.getX());
                    arrowEnemy29.setY(arrow29.getY());

                    arrow30.moveEnemy();
                    arrowEnemy30.setX(arrow30.getX());
                    arrowEnemy30.setY(arrow30.getY());

                    arrow31.moveEnemy();
                    arrowEnemy31.setX(arrow31.getX());
                    arrowEnemy31.setY(arrow31.getY());

                    arrow32.moveEnemy();
                    arrowEnemy32.setX(arrow32.getX());
                    arrowEnemy32.setY(arrow32.getY());

                    arrow33.moveEnemy();
                    arrowEnemy33.setX(arrow33.getX());
                    arrowEnemy33.setY(arrow33.getY());

                    arrow34.moveEnemy();
                    arrowEnemy34.setX(arrow34.getX());
                    arrowEnemy34.setY(arrow34.getY());

                    arrow35.moveEnemy();
                    arrowEnemy35.setX(arrow35.getX());
                    arrowEnemy35.setY(arrow35.getY());

                    arrow36.moveEnemy();
                    arrowEnemy36.setX(arrow36.getX());
                    arrowEnemy36.setY(arrow36.getY());

                    arrow37.moveEnemy();
                    arrowEnemy37.setX(arrow37.getX());
                    arrowEnemy37.setY(arrow37.getY());

                    arrow38.moveEnemy();
                    arrowEnemy38.setX(arrow38.getX());
                    arrowEnemy38.setY(arrow38.getY());

                    arrow39.moveEnemy();
                    arrowEnemy39.setX(arrow39.getX());
                    arrowEnemy39.setY(arrow39.getY());

                    arrow40.moveEnemy();
                    arrowEnemy40.setX(arrow40.getX());
                    arrowEnemy40.setY(arrow40.getY());

                    arrow41.moveEnemy();
                    arrowEnemy41.setX(arrow41.getX());
                    arrowEnemy41.setY(arrow41.getY());

                    arrow42.moveEnemy();
                    arrowEnemy42.setX(arrow42.getX());
                    arrowEnemy42.setY(arrow42.getY());

                    arrow43.moveEnemy();
                    arrowEnemy43.setX(arrow43.getX());
                    arrowEnemy43.setY(arrow43.getY());

                    arrow44.moveEnemy();
                    arrowEnemy44.setX(arrow44.getX());
                    arrowEnemy44.setY(arrow44.getY());

                    arrow45.moveEnemy();
                    arrowEnemy45.setX(arrow45.getX());
                    arrowEnemy45.setY(arrow45.getY());

                    arrow46.moveEnemy();
                    arrowEnemy46.setX(arrow46.getX());
                    arrowEnemy46.setY(arrow46.getY());

                    arrow47.moveEnemy();
                    arrowEnemy47.setX(arrow47.getX());
                    arrowEnemy47.setY(arrow47.getY());

                    arrow48.moveEnemy();
                    arrowEnemy48.setX(arrow48.getX());
                    arrowEnemy48.setY(arrow48.getY());

                    arrow49.moveEnemy();
                    arrowEnemy49.setX(arrow49.getX());
                    arrowEnemy49.setY(arrow49.getY());

                    arrow50.moveEnemy();
                    arrowEnemy50.setX(arrow50.getX());
                    arrowEnemy50.setY(arrow50.getY());





                    if (arrow.checkCollision(character) && !arrow.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow.setCollected(true);
                        player.GameOver();
                    } else if (arrow1.checkCollision(character) && !arrow1.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow1.setCollected(true);
                        player.GameOver();
                    }else if (arrow2.checkCollision(character) && !arrow2.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow2.setCollected(true);
                        player.GameOver();
                    }else if (arrow3.checkCollision(character) && !arrow3.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow3.setCollected(true);
                        player.GameOver();
                    } else if (arrow4.checkCollision(character) && !arrow4.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow4.setCollected(true);
                        player.GameOver();
                    } else if (arrow5.checkCollision(character) && !arrow5.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow5.setCollected(true);
                        player.GameOver();
                    } else if (arrow6.checkCollision(character) && !arrow6.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow6.setCollected(true);
                        player.GameOver();
                    } else if (arrow7.checkCollision(character) && !arrow7.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow7.setCollected(true);
                        player.GameOver();
                    } else if (arrow8.checkCollision(character) && !arrow8.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow8.setCollected(true);
                        player.GameOver();
                    } else if (arrow9.checkCollision(character) && !arrow9.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow9.setCollected(true);
                        player.GameOver();
                    } else if (arrow10.checkCollision(character) && !arrow10.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow10.setCollected(true);
                        player.GameOver();
                    }else if (arrow11.checkCollision(character) && !arrow11.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow11.setCollected(true);
                        player.GameOver();
                    } else if (arrow12.checkCollision(character) && !arrow12.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow12.setCollected(true);
                        player.GameOver();
                    } else if (arrow13.checkCollision(character) && !arrow13.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow13.setCollected(true);
                        player.GameOver();
                    } else if (arrow14.checkCollision(character) && !arrow14.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow14.setCollected(true);
                        player.GameOver();
                    } else if (arrow15.checkCollision(character) && !arrow15.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow15.setCollected(true);
                        player.GameOver();
                    } else if (arrow16.checkCollision(character) && !arrow16.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow16.setCollected(true);
                        player.GameOver();
                    } else if (arrow17.checkCollision(character) && !arrow17.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow17.setCollected(true);
                        player.GameOver();
                    } else if (arrow18.checkCollision(character) && !arrow18.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow18.setCollected(true);
                        player.GameOver();
                    } else if (arrow19.checkCollision(character) && !arrow19.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow19.setCollected(true);
                        player.GameOver();
                    } else if (arrow20.checkCollision(character) && !arrow20.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow20.setCollected(true);
                        player.GameOver();
                    } else if (arrow21.checkCollision(character) && !arrow21.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow21.setCollected(true);
                        player.GameOver();
                    } else if (arrow22.checkCollision(character) && !arrow22.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow22.setCollected(true);
                        player.GameOver();
                    } else if (arrow23.checkCollision(character) && !arrow23.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow23.setCollected(true);
                        player.GameOver();
                    } else if (arrow24.checkCollision(character) && !arrow24.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow24.setCollected(true);
                        player.GameOver();
                    } else if (arrow25.checkCollision(character) && !arrow25.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow25.setCollected(true);
                        player.GameOver();
                    } else if (arrow26.checkCollision(character) && !arrow26.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow26.setCollected(true);
                        player.GameOver();
                    } else if (arrow27.checkCollision(character) && !arrow27.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow27.setCollected(true);
                        player.GameOver();
                    } else if (arrow28.checkCollision(character) && !arrow28.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow28.setCollected(true);
                        player.GameOver();
                    } else if (arrow29.checkCollision(character) && !arrow29.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow29.setCollected(true);
                        player.GameOver();
                    } else if (arrow30.checkCollision(character) && !arrow30.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow30.setCollected(true);
                        player.GameOver();

                    } else if (arrow21.checkCollision(character) && !arrow21.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow21.setCollected(true);
                        player.GameOver();
                    } else if (arrow22.checkCollision(character) && !arrow22.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow22.setCollected(true);
                        player.GameOver();
                    } else if (arrow23.checkCollision(character) && !arrow23.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow23.setCollected(true);
                        player.GameOver();
                    } else if (arrow24.checkCollision(character) && !arrow24.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow24.setCollected(true);
                        player.GameOver();
                    } else if (arrow25.checkCollision(character) && !arrow25.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow25.setCollected(true);
                        player.GameOver();
                    } else if (arrow26.checkCollision(character) && !arrow26.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow26.setCollected(true);
                        player.GameOver();
                    } else if (arrow27.checkCollision(character) && !arrow27.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow27.setCollected(true);
                        player.GameOver();
                    } else if (arrow28.checkCollision(character) && !arrow28.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow28.setCollected(true);
                        player.GameOver();
                    } else if (arrow29.checkCollision(character) && !arrow29.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow29.setCollected(true);
                        player.GameOver();
                    } else if (arrow30.checkCollision(character) && !arrow30.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow30.setCollected(true);
                        player.GameOver();
                    }else if (arrow31.checkCollision(character) && !arrow31.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow31.setCollected(true);
                        player.GameOver();
                    } else if (arrow32.checkCollision(character) && !arrow32.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow32.setCollected(true);
                        player.GameOver();
                    } else if (arrow33.checkCollision(character) && !arrow33.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow33.setCollected(true);
                        player.GameOver();
                    } else if (arrow34.checkCollision(character) && !arrow34.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow34.setCollected(true);
                        player.GameOver();
                    } else if (arrow35.checkCollision(character) && !arrow35.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow35.setCollected(true);
                        player.GameOver();
                    } else if (arrow36.checkCollision(character) && !arrow36.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow36.setCollected(true);
                        player.GameOver();
                    } else if (arrow37.checkCollision(character) && !arrow37.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow37.setCollected(true);
                        player.GameOver();
                    } else if (arrow38.checkCollision(character) && !arrow38.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow38.setCollected(true);
                        player.GameOver();
                    } else if (arrow39.checkCollision(character) && !arrow39.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow39.setCollected(true);
                        player.GameOver();
                    } else if (arrow40.checkCollision(character) && !arrow40.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow40.setCollected(true);
                        player.GameOver();
                    } else if (arrow41.checkCollision(character) && !arrow41.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow41.setCollected(true);
                        player.GameOver();
                    } else if (arrow42.checkCollision(character) && !arrow42.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow42.setCollected(true);
                        player.GameOver();
                    } else if (arrow43.checkCollision(character) && !arrow43.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow43.setCollected(true);
                        player.GameOver();
                    } else if (arrow44.checkCollision(character) && !arrow44.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow44.setCollected(true);
                        player.GameOver();
                    } else if (arrow45.checkCollision(character) && !arrow45.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow45.setCollected(true);
                        player.GameOver();
                    } else if (arrow46.checkCollision(character) && !arrow46.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow46.setCollected(true);
                        player.GameOver();
                    } else if (arrow47.checkCollision(character) && !arrow47.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow47.setCollected(true);
                        player.GameOver();
                    } else if (arrow48.checkCollision(character) && !arrow48.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow48.setCollected(true);
                        player.GameOver();
                    } else if (arrow49.checkCollision(character) && !arrow49.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow49.setCollected(true);
                        player.GameOver();
                    } else if (arrow50.checkCollision(character) && !arrow50.isCollected()) {
                        hitSound.seekTo(0);
                        hitSound.start();
                        player.setLifeDamage();
                        arrow50.setCollected(true);
                        player.GameOver();
                    }else


                    if (!coin_1.isCollected() && coin_1.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin1.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_1.setCollected(true);
                    } else if (!coin_2.isCollected() && coin_2.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin2.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_2.setCollected(true);
                    } else if (!coin_3.isCollected() && coin_3.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin3.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_3.setCollected(true);
                    } else if (!coin_4.isCollected() && coin_4.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin4.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_4.setCollected(true);
                    } else if (!coin_5.isCollected() && coin_5.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin5.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_5.setCollected(true);
                    } else if (!coin_6.isCollected() && coin_6.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin6.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_6.setCollected(true);
                    } else if (!coin_7.isCollected() && coin_7.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin7.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_7.setCollected(true);
                    } else if (!coin_8.isCollected() && coin_8.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin8.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_8.setCollected(true);
                    } else if (!coin_9.isCollected() && coin_9.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin9.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_9.setCollected(true);
                    } else if (!coin_10.isCollected() && coin_10.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin10.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_10.setCollected(true);
                    } else if (!coin_11.isCollected() && coin_11.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin11.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_11.setCollected(true);
                    } else if (!coin_12.isCollected() && coin_12.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin12.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_12.setCollected(true);
                    } else if (!coin_13.isCollected() && coin_13.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin13.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_13.setCollected(true);
                    } else if (!coin_14.isCollected() && coin_14.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin14.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_14.setCollected(true);
                    } else if (!coin_15.isCollected() && coin_15.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin15.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_15.setCollected(true);
                    } else if (!coin_16.isCollected() && coin_16.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin16.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_16.setCollected(true);
                    } else if (!coin_17.isCollected() && coin_17.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin17.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_17.setCollected(true);
                    } else if (!coin_18.isCollected() && coin_18.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin18.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_18.setCollected(true);
                    } else if (!coin_19.isCollected() && coin_19.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin19.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_19.setCollected(true);
                    } else if (!coin_20.isCollected() && coin_20.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin20.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_20.setCollected(true);
                    } else if (!coin_21.isCollected() && coin_21.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin21.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_21.setCollected(true);
                    } else if (!coin_22.isCollected() && coin_22.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin22.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_22.setCollected(true);
                    } else if (!coin_23.isCollected() && coin_23.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin23.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_23.setCollected(true);
                    } else if (!coin_24.isCollected() && coin_24.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin24.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_24.setCollected(true);
                    } else if (!coin_25.isCollected() && coin_25.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin25.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_25.setCollected(true);
                    } else if (!coin_26.isCollected() && coin_26.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin26.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_26.setCollected(true);
                    } else if (!coin_27.isCollected() && coin_27.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin27.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_27.setCollected(true);
                    } else if (!coin_28.isCollected() && coin_28.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin28.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_28.setCollected(true);
                    } else if (!coin_29.isCollected() && coin_29.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin29.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_29.setCollected(true);
                    } else if (!coin_30.isCollected() && coin_30.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin30.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_30.setCollected(true);
                    } else if (!coin_31.isCollected() && coin_31.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin31.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_31.setCollected(true);
                    } else if (!coin_32.isCollected() && coin_32.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin32.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_32.setCollected(true);
                    } else if (!coin_33.isCollected() && coin_33.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin33.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_33.setCollected(true);
                    } else if (!coin_34.isCollected() && coin_34.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin34.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_34.setCollected(true);
                    } else if (!coin_35.isCollected() && coin_35.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin35.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_35.setCollected(true);
                    } else if (!coin_36.isCollected() && coin_36.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin36.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_36.setCollected(true);
                    } else if (!coin_37.isCollected() && coin_37.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin37.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_37.setCollected(true);
                    } else if (!coin_38.isCollected() && coin_38.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin38.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_38.setCollected(true);
                    } else if (!coin_39.isCollected() && coin_39.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin39.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_39.setCollected(true);
                    } else if (!coin_40.isCollected() && coin_40.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin40.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_40.setCollected(true);
                    } else if (!coin_41.isCollected() && coin_41.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin41.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_41.setCollected(true);
                    } else if (!coin_42.isCollected() && coin_42.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin42.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_42.setCollected(true);
                    } else if (!coin_43.isCollected() && coin_43.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin43.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_43.setCollected(true);
                    } else if (!coin_44.isCollected() && coin_44.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin44.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_44.setCollected(true);
                    } else if (!coin_45.isCollected() && coin_45.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin45.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_45.setCollected(true);
                    } else if (!coin_46.isCollected() && coin_46.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin46.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_46.setCollected(true);
                    } else if (!coin_47.isCollected() && coin_47.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin47.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_47.setCollected(true);
                    } else if (!coin_48.isCollected() && coin_48.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin48.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_48.setCollected(true);
                    } else if (!coin_49.isCollected() && coin_49.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin49.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_49.setCollected(true);
                    } else if (!coin_50.isCollected() && coin_50.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin50.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_50.setCollected(true);
                    } else if (!coin_51.isCollected() && coin_51.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin51.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_51.setCollected(true);
                    } else if (!coin_52.isCollected() && coin_52.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin52.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_52.setCollected(true);
                    } else if (!coin_53.isCollected() && coin_53.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin53.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_53.setCollected(true);
                    } else if (!coin_54.isCollected() && coin_54.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin54.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_54.setCollected(true);
                    } else if (!coin_55.isCollected() && coin_55.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin55.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_55.setCollected(true);
                    } else if (!coin_56.isCollected() && coin_56.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin56.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_56.setCollected(true);
                    } else if (!coin_57.isCollected() && coin_57.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin57.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_57.setCollected(true);
                    } else if (!coin_58.isCollected() && coin_58.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin58.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_58.setCollected(true);
                    } else if (!coin_59.isCollected() && coin_59.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin59.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_59.setCollected(true);
                    } else if (!coin_60.isCollected() && coin_60.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin60.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_60.setCollected(true);
                    } else if (!coin_61.isCollected() && coin_61.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin61.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_61.setCollected(true);
                    } else if (!coin_62.isCollected() && coin_62.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin62.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_62.setCollected(true);
                    } else if (!coin_63.isCollected() && coin_63.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin63.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_63.setCollected(true);
                    } else if (!coin_64.isCollected() && coin_64.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin64.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_64.setCollected(true);
                    } else if (!coin_65.isCollected() && coin_65.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin65.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_65.setCollected(true);
                    } else if (!coin_66.isCollected() && coin_66.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin66.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_66.setCollected(true);
                    } else if (!coin_67.isCollected() && coin_67.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin67.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_67.setCollected(true);
                    } else if (!coin_68.isCollected() && coin_68.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin68.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_68.setCollected(true);
                    } else if (!coin_69.isCollected() && coin_69.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin69.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_69.setCollected(true);
                    } else if (!coin_70.isCollected() && coin_70.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin70.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_70.setCollected(true);
                    } else if (!coin_71.isCollected() && coin_71.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin71.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_71.setCollected(true);
                    } else if (!coin_72.isCollected() && coin_72.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin72.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_72.setCollected(true);
                    } else if (!coin_73.isCollected() && coin_73.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin73.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_73.setCollected(true);
                    } else if (!coin_74.isCollected() && coin_74.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin74.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_74.setCollected(true);
                    } else if (!coin_75.isCollected() && coin_75.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin75.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_75.setCollected(true);
                    } else if (!coin_76.isCollected() && coin_76.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin76.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_76.setCollected(true);
                    } else if (!coin_77.isCollected() && coin_77.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin77.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_77.setCollected(true);
                    } else if (!coin_78.isCollected() && coin_78.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin78.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_78.setCollected(true);
                    } else if (!coin_79.isCollected() && coin_79.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin79.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_79.setCollected(true);
                    } else if (!coin_80.isCollected() && coin_80.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin80.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_80.setCollected(true);
                    } else if (!coin_81.isCollected() && coin_81.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin81.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_81.setCollected(true);
                    } else if (!coin_82.isCollected() && coin_82.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin82.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_82.setCollected(true);
                    } else if (!coin_83.isCollected() && coin_83.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin83.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_83.setCollected(true);
                    } else if (!coin_84.isCollected() && coin_84.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        player.setCountCoins(addCoinValue);
                        coin_84.setCollected(true);
                    } else if (!coin_85.isCollected() && coin_85.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin85.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_85.setCollected(true);
                    } else if (!coin_86.isCollected() && coin_86.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin86.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_86.setCollected(true);
                    } else if (!coin_87.isCollected() && coin_87.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin87.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_87.setCollected(true);
                    } else if (!coin_88.isCollected() && coin_88.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin88.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_88.setCollected(true);
                    } else if (!coin_89.isCollected() && coin_89.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin89.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_89.setCollected(true);
                    } else if (!coin_90.isCollected() && coin_90.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin90.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_90.setCollected(true);
                    } else if (!coin_91.isCollected() && coin_91.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin91.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_91.setCollected(true);
                    } else if (!coin_92.isCollected() && coin_92.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin92.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_92.setCollected(true);
                    } else if (!coin_93.isCollected() && coin_93.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin93.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_93.setCollected(true);
                    } else if (!coin_94.isCollected() && coin_94.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin94.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_94.setCollected(true);
                    } else if (!coin_95.isCollected() && coin_95.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin95.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_95.setCollected(true);
                    } else if (!coin_96.isCollected() && coin_96.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin96.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_96.setCollected(true);
                    } else if (!coin_97.isCollected() && coin_97.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin97.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_97.setCollected(true);
                    } else if (!coin_98.isCollected() && coin_98.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin98.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_98.setCollected(true);
                    } else if (!coin_99.isCollected() && coin_99.checkCollision(character)) {
                        coinSound.seekTo(0);
                        coinSound.start();
                        coin99.setVisibility(View.INVISIBLE);
                        player.setCountCoins(addCoinValue);
                        coin_99.setCollected(true);
                    }


                    String getValue = String.valueOf(player.getCountCoins());
                    prueba.setText(getValue + "/99");
//


                    handler.postDelayed(this, 20);
                }
            };
        });

    }
//    @Override
//    protected void onStop() {
//        super.onStop();
//        mediaPlayer.release();
//        mediaPlayer.stop();
//        coinSound.stop();
//        hitSound.stop();
//        backgroundImage2.clearAnimation();
//        backgroundImage.clearAnimation();
//        backgroundImage3.clearAnimation();
//
//        animationCoin.clearAnimation();
//
//
//    }
//    @Override
//    protected void onPause() {
//        super.onPause();
//        mediaPlayer.release();
//        mediaPlayer.stop();
//        coinSound.stop();
//        hitSound.stop();
//        backgroundImage2.clearAnimation();
//        backgroundImage.clearAnimation();
//        backgroundImage3.clearAnimation();
//
//        coin1.clearAnimation();
//        coin2.clearAnimation();
//        coin3.clearAnimation();
//
//    }
//    @Override
//    protected void onStop() {
//        super.onStop();
//
//        // Libera recursos cuando se destruye la actividad
//        if (mediaPlayer != null) {
//            mediaPlayer.release();
//            mediaPlayer.stop();
//            coinSound.stop();
//            hitSound.stop();
//            mediaPlayer = null;
//        }
//    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//
//        // Libera recursos cuando se destruye la actividad
//        if (mediaPlayer != null) {
//            mediaPlayer.release();
//            mediaPlayer.stop();
//            coinSound.stop();
//            hitSound.stop();
//            mediaPlayer = null;
//        }
//    }




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
    @Override
    protected void onStop() {
        super.onStop();

        if (mediaPlayer != null) {
            mediaPlayer.stop();

        }
        if (coinSound != null) {
            coinSound.stop();


        }
        if (hitSound != null) {
            hitSound.stop();

        }

        coin1.clearAnimation();
        coin2.clearAnimation();
        coin3.clearAnimation();
        coin4.clearAnimation();
        coin5.clearAnimation();
        coin6.clearAnimation();
        coin7.clearAnimation();
        coin8.clearAnimation();
        coin9.clearAnimation();
        coin10.clearAnimation();
        coin11.clearAnimation();
        coin12.clearAnimation();
        coin13.clearAnimation();
        coin14.clearAnimation();
        coin15.clearAnimation();
        coin16.clearAnimation();
        coin17.clearAnimation();
        coin18.clearAnimation();
        coin19.clearAnimation();
        coin20.clearAnimation();
        coin21.clearAnimation();
        coin22.clearAnimation();
        coin23.clearAnimation();
        coin24.clearAnimation();
        coin25.clearAnimation();
        coin26.clearAnimation();
        coin27.clearAnimation();
        coin28.clearAnimation();
        coin29.clearAnimation();
        coin30.clearAnimation();
        coin31.clearAnimation();
        coin32.clearAnimation();
        coin33.clearAnimation();
        coin34.clearAnimation();
        coin35.clearAnimation();
        coin36.clearAnimation();
        coin37.clearAnimation();
        coin38.clearAnimation();
        coin39.clearAnimation();
        coin40.clearAnimation();
        coin41.clearAnimation();
        coin42.clearAnimation();
        coin43.clearAnimation();
        coin44.clearAnimation();
        coin45.clearAnimation();
        coin46.clearAnimation();
        coin47.clearAnimation();
        coin48.clearAnimation();
        coin49.clearAnimation();
        coin50.clearAnimation();
        coin51.clearAnimation();
        coin52.clearAnimation();
        coin53.clearAnimation();
        coin54.clearAnimation();
        coin55.clearAnimation();
        coin56.clearAnimation();
        coin57.clearAnimation();
        coin58.clearAnimation();
        coin59.clearAnimation();
        coin60.clearAnimation();
        coin61.clearAnimation();
        coin62.clearAnimation();
        coin63.clearAnimation();
        coin64.clearAnimation();
        coin65.clearAnimation();
        coin66.clearAnimation();
        coin67.clearAnimation();
        coin68.clearAnimation();
        coin69.clearAnimation();
        coin70.clearAnimation();
        coin71.clearAnimation();
        coin72.clearAnimation();
        coin73.clearAnimation();
        coin74.clearAnimation();
        coin75.clearAnimation();
        coin76.clearAnimation();
        coin77.clearAnimation();
        coin78.clearAnimation();
        coin79.clearAnimation();
        coin80.clearAnimation();
        coin81.clearAnimation();
        coin82.clearAnimation();
        coin83.clearAnimation();
        coin84.clearAnimation();
        coin85.clearAnimation();
        coin86.clearAnimation();
        coin87.clearAnimation();
        coin88.clearAnimation();
        coin89.clearAnimation();
        coin90.clearAnimation();
        coin91.clearAnimation();
        coin92.clearAnimation();
        coin93.clearAnimation();
        coin94.clearAnimation();
        coin95.clearAnimation();
        coin96.clearAnimation();
        coin97.clearAnimation();
        coin98.clearAnimation();
        coin99.clearAnimation();

        arrowEnemy.clearAnimation();
        arrowEnemy1.clearAnimation();
        arrowEnemy2.clearAnimation();
        arrowEnemy3.clearAnimation();
        arrowEnemy4.clearAnimation();
        arrowEnemy5.clearAnimation();
        arrowEnemy6.clearAnimation();
        arrowEnemy7.clearAnimation();
        arrowEnemy8.clearAnimation();
        arrowEnemy9.clearAnimation();
        arrowEnemy10.clearAnimation();
        arrowEnemy11.clearAnimation();
        arrowEnemy12.clearAnimation();
        arrowEnemy13.clearAnimation();
        arrowEnemy14.clearAnimation();
        arrowEnemy15.clearAnimation();
        arrowEnemy16.clearAnimation();
        arrowEnemy17.clearAnimation();
        arrowEnemy18.clearAnimation();
        arrowEnemy19.clearAnimation();
        arrowEnemy20.clearAnimation();
        arrowEnemy21.clearAnimation();
        arrowEnemy22.clearAnimation();
        arrowEnemy23.clearAnimation();
        arrowEnemy24.clearAnimation();
        arrowEnemy25.clearAnimation();
        arrowEnemy26.clearAnimation();
        arrowEnemy27.clearAnimation();
        arrowEnemy28.clearAnimation();
        arrowEnemy29.clearAnimation();
        arrowEnemy30.clearAnimation();
        arrowEnemy31.clearAnimation();
        arrowEnemy32.clearAnimation();
        arrowEnemy33.clearAnimation();
        arrowEnemy34.clearAnimation();
        arrowEnemy35.clearAnimation();
        arrowEnemy36.clearAnimation();
        arrowEnemy37.clearAnimation();
        arrowEnemy38.clearAnimation();
        arrowEnemy39.clearAnimation();
        arrowEnemy40.clearAnimation();
        arrowEnemy41.clearAnimation();
        arrowEnemy42.clearAnimation();
        arrowEnemy43.clearAnimation();
        arrowEnemy44.clearAnimation();
        arrowEnemy45.clearAnimation();
        arrowEnemy46.clearAnimation();
        arrowEnemy47.clearAnimation();
        arrowEnemy48.clearAnimation();
        arrowEnemy49.clearAnimation();
        arrowEnemy50.clearAnimation();


    }






}
