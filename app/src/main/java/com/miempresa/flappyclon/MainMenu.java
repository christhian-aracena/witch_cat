package com.miempresa.flappyclon;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainMenu extends AppCompatActivity {

    private Button btn_start;
    private ImageView femaleCharacter, maleCharacter;
    private RadioButton femaleRButton, maleRButton;
    private RadioGroup radioGroup;
    private ObjectAnimator characterAnimator;
    int check;
    private MediaPlayer mediaPlayer;
    private MediaPlayer selectCharacterSound;
    Intent newIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);

        // Inicializa el reproductor multimedia
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.intro_theme);

        mediaPlayer.setVolume(1.9f, 1.9f);

        // Reproduce la música
        mediaPlayer.start();
        mediaPlayer.setLooping(true);

        selectCharacterSound = MediaPlayer.create(this, R.raw.select_character);
        btn_start = findViewById(R.id.btn_start);
        femaleCharacter = findViewById(R.id.female_witch);
        maleCharacter = findViewById(R.id.male_witch);
        femaleRButton = findViewById(R.id.witch_girl_selected);
        maleRButton = findViewById(R.id.witch_boy_selected);
        radioGroup = findViewById(R.id.rGroup);
        newIntent = new Intent(MainMenu.this, MainActivity.class);


        // Llama al método para especificar el personaje seleccionado por defecto
        animationMenu(femaleCharacter);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                mediaPlayer.release();

                if (!femaleRButton.isChecked() && !maleRButton.isChecked()) {
                    check = 1;
                } else {
                    if (femaleRButton.isChecked()) {
                        check = 1;
                    } else if (maleRButton.isChecked()) {
                        check = 2;
                    }
                }
                newIntent.putExtra("seleccion", check);
                startActivity(newIntent);
//                finish();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.witch_girl_selected) {
                    selectCharacterSound.start();
                    stopAnimator();
                    animationMenu(femaleCharacter);
                } else if (i == R.id.witch_boy_selected) {
                    selectCharacterSound.start();
                    stopAnimator();
                    animationMenu(maleCharacter);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Liberar recursos del reproductor multimedia al destruir la actividad
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        // Liberar recursos del reproductor de sonido de selección
        if (selectCharacterSound != null) {
            selectCharacterSound.release();
            selectCharacterSound = null;
        }
    }
    public void animationMenu(ImageView character) {
        // Crear un ObjectAnimator para la propiedad "translationY"
        characterAnimator = ObjectAnimator.ofFloat(character, "translationY", 0f, 100f);

        // Configurar la duración de la animación en milisegundos
        characterAnimator.setDuration(800); // 1000 milisegundos = 1 segundo

        // Configurar el LinearInterpolator para obtener un movimiento suave
        characterAnimator.setInterpolator(new LinearInterpolator());

        // Configurar la repetición infinita
        characterAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        characterAnimator.setRepeatMode(ObjectAnimator.REVERSE);

        // Iniciar la animación
        characterAnimator.start();
    }

    private void stopAnimator() {
        if (characterAnimator != null && characterAnimator.isRunning()) {
            characterAnimator.cancel();
            if (femaleCharacter != null) {
                femaleCharacter.setTranslationY(0f);
            }
            if (maleCharacter != null) {
                maleCharacter.setTranslationY(0f);
            }
        }
    }
}
