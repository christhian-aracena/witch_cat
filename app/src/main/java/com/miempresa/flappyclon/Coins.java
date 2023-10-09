package com.miempresa.flappyclon;

import android.content.Context;
import android.graphics.Rect;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Coins extends Enemys{

    private boolean collected = false;

    Coins(float x, float y, float speed, float sizeEnemy, Context context) {
        super(x, y, speed, sizeEnemy);
        setX(x);
        setY(y);


        ImageView coinImageView = new ImageView(context);
        coinImageView.setLayoutParams(new ViewGroup.LayoutParams((int) sizeEnemy, (int) sizeEnemy));
    }

    @Override
    public boolean isCollected() {
        return collected;
    }
    @Override
    public void setCollected(boolean collected) {
        this.collected = collected;
    }
    @Override
    public void moveEnemy() {
        setX(getX() - getSpeed());

    }

    @Override
    public boolean checkCollision(ImageView bird) {
        Rect birdRect = new Rect();
        bird.getHitRect(birdRect);

        Rect enemyRect = new Rect(
                (int) getX(),
                (int) getY(),
                (int) (getX() + getSizeEnemy()),
                (int) (getY() + getSizeEnemy())
        );

        return birdRect.intersect(enemyRect);
    }


}
