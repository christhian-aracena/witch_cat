package com.miempresa.flappyclon;

public class ArrowEnemy extends Enemys{

    ArrowEnemy(float x, float y, float speed, float sizeEnemy){
        super(x, y, speed, sizeEnemy);

    }


    @Override
    public void moveEnemy() {
        setX(getX() - getSpeed());

    }

    @Override
    public boolean checkCollisionWithPlayer(float playerX, float playerY, float playerSize){

        return true;
    }
}

