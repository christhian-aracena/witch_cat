package com.miempresa.flappyclon;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Coins extends Enemys{
    Coins(float x, float y, float speed, float sizeEnemy, Context context) {
        super(x, y, speed, sizeEnemy);
        setX(x);
        setY(y);

        // Configurar el tamaño de la vista (ajústalo según tus necesidades)
        ImageView coinImageView = new ImageView(context);
        coinImageView.setLayoutParams(new ViewGroup.LayoutParams((int) sizeEnemy, (int) sizeEnemy));
    }

    @Override
    public void moveEnemy() {
        setX(getX() - getSpeed());

    }


}
