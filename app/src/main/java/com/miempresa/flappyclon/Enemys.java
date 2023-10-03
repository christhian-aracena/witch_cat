package com.miempresa.flappyclon;

import android.graphics.Rect;
import android.widget.ImageView;

public abstract class Enemys {
    private float x, y;  // Posición del enemigo
    private float speed;  // Velocidad del enemigo
    private float sizeEnemy;   // Tamaño del enemigo

    public Enemys(float x, float y, float speed, float sizeEnemy) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.sizeEnemy = sizeEnemy;
    }

    public void moveEnemy(){}

    public boolean checkCollision(ImageView bird) {
        Rect birdRect = new Rect();
        bird.getHitRect(birdRect);

        Rect enemyRect = new Rect(
                (int) getX()+110,
                (int) getY()+110,
                (int) (getX() + getSizeEnemy()),
                (int) (getY() + getSizeEnemy())
        );

        return birdRect.intersect(enemyRect);
    }



    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getSizeEnemy() {
        return sizeEnemy;
    }

    public void setSizeEnemy(float sizeEnemy) {
        this.sizeEnemy = sizeEnemy;
    }
}
