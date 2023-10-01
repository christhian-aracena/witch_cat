package com.miempresa.flappyclon;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.renderscript.Sampler;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private ImageView bird;
    private RelativeLayout gameLayout;
    private int birdY;
    private int birdSpeed;
    private boolean gameStarted = false;

    private static final int SET_POSITION_X=300;
    private static final int SET_POSITION_Y=100;
    private ImageView backgroundImage;
    private ImageView backgroundImage2;
    private ImageView backgroundImage3;
    private int screenWidth;
    private int currentPosition = 0;
    private int currentPosition2 = 0;
    private int currentPosition3 = 0;
    private ImageView pressLayout;
    private int margin = -18; //


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);

        backgroundImage = findViewById(R.id.bg_bottom);
        backgroundImage2 = findViewById(R.id.bg_bottom_continuation);
        backgroundImage3 = findViewById(R.id.bg_bottom_continuation);
        pressLayout = findViewById(R.id.bg_top);
        bird = findViewById(R.id.bird);
        gameLayout = findViewById(R.id.press);
        bird.setX(SET_POSITION_X);
        bird.setY(SET_POSITION_Y);

        Intent intent = getIntent();
        int getVariable = intent.getIntExtra("seleccion", 0);
        setCharacterImage(getVariable);
        DisplayMetrics sizeMobile = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(sizeMobile);
        screenWidth = sizeMobile.widthPixels;

        // Ajusta las posiciones iniciales según el ancho de la pantalla
        currentPosition = 0; // Establece la posición inicial de la primera imagen
        currentPosition2 = screenWidth - 15;
        currentPosition3 = screenWidth - 15;

        // Llama a esta función para ambas imágenes con las posiciones iniciales respectivas
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


                return true; // Devuelve true para indicar que el evento ha sido manejado
            }

            private void startGame() {
                gameStarted = true;
                birdSpeed = -1;
                birdY = (int) bird.getY();
                handler.postDelayed(gameRunnable, 20);
            }

            private void jump() {
                birdSpeed = -30;
            }

            private Runnable gameRunnable = new Runnable() {
                @Override
                public void run() {
                    // Actualizar la posición del pájaro
                    birdSpeed += 2; // Gravedad
                    birdY += birdSpeed;

                    // Verificar si el pájaro golpea la parte superior o inferior de la pantalla
                    if (birdY < 0) {
                        birdY = 0;
                        birdSpeed = 0;
                    } else if (birdY > gameLayout.getHeight() - bird.getHeight()) {
                        birdY = gameLayout.getHeight() - bird.getHeight();
                        birdSpeed = 0;
                    }

                    // Actualizar la posición del pájaro en la pantalla
                    bird.setY(birdY);

                    // Programar la próxima actualización
                    handler.postDelayed(this, 20);
                }
            };
        });


    }

    private void setCharacterImage(int seleccion) {
        switch (seleccion) {
            case 1:
                // Elegir la imagen para la gata mujer
                bird.setImageResource(R.drawable.witch_cat_girl_v2); // Reemplaza "gata_mujer" con el nombre de tu recurso de imagen
                break;
            case 2:
                // Elegir la imagen para el gato hombre
                bird.setImageResource(R.drawable.witch_cat_v2); // Reemplaza "gato_hombre" con el nombre de tu recurso de imagen
                break;
            default:
                // Por defecto, elige alguna imagen (puedes ajustar esto según tus necesidades)
                bird.setImageResource(R.drawable.witch_cat_girl_v2);
                break;
        }
    }

    private void moveBackground() {
        backgroundImage.postOnAnimationDelayed(new Runnable() {
            @Override
            public void run() {
                // Actualiza la posición de la imagen 1
                currentPosition -= 13; // Ajusta la velocidad de movimiento según tus necesidades
                backgroundImage.setX(currentPosition);

                // Actualiza la posición de la imagen 2
                currentPosition2 -= 13; // Ajusta la velocidad de movimiento según tus necesidades
                backgroundImage2.setX(currentPosition2);

                // Actualiza la posición de la imagen 3
                currentPosition3 -= 13; // Ajusta la velocidad de movimiento según tus necesidades
                backgroundImage3.setX(currentPosition3);

                // Si la imagen 1 se movió completamente hacia la izquierda, reinicia su posición
                if (currentPosition < -screenWidth - margin) {
                    currentPosition = screenWidth + margin; // Ajusta esto según el ancho de la pantalla
                }

                // Si la imagen 2 se movió completamente hacia la izquierda, reinicia su posición
                if (currentPosition2 < -screenWidth - margin) {
                    currentPosition2 = screenWidth + margin; // Ajusta esto según el ancho de la pantalla
                }

                // Si la imagen 3 se movió completamente hacia la izquierda, reinicia su posición
                if (currentPosition3 < -screenWidth - margin) {
                    currentPosition3 = screenWidth + margin; // Ajusta esto según el ancho de la pantalla
                }

                // Vuelve a llamar al bucle
                moveBackground();
            }
        }, 16); // 16ms es aproximadamente 60 FPS (milisegundos por fotograma)
    }
}
