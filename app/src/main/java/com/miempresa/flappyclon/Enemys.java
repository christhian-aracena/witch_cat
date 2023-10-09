package com.miempresa.flappyclon;

import android.graphics.Rect;
import android.widget.ImageView;

public abstract class Enemys {
    protected float x, y;  // Posición del enemigo
    protected float speed;  // Velocidad del enemigo
    protected float sizeEnemy;   // Tamaño del enemigo
    private boolean collected = false;

    public Enemys(float x, float y, float speed, float sizeEnemy) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.sizeEnemy = sizeEnemy;
    }
    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }


    protected void moveEnemy(){}

    protected boolean checkCollision(ImageView bird) {
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



    protected float getX() {
        return x;
    }

    protected void setX(float x) {
        this.x = x;
    }

    protected float getY() {
        return y;
    }

    protected void setY(float y) {
        this.y = y;
    }

    protected float getSpeed() {
        return speed;
    }

    protected void setSpeed(float speed) {
        this.speed = speed;
    }

    protected float getSizeEnemy() {
        return sizeEnemy;
    }

    protected void setSizeEnemy(float sizeEnemy) {
        this.sizeEnemy = sizeEnemy;
    }
}
