package com.miempresa.flappyclon;

import android.graphics.Rect;
import android.widget.ImageView;

public class Goal extends Enemys{

    private boolean collected = false;

    public Goal(float x, float y, float speed, float sizeEnemy){
        super(x,y,speed,sizeEnemy);
        setX(x);
        setY(y);
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
                (int) getX()+300,
                (int) getY(),
                (int) (getX() + getSizeEnemy()),
                (int) (getY() + getSizeEnemy())
        );

        return birdRect.intersect(enemyRect);
    }

}
