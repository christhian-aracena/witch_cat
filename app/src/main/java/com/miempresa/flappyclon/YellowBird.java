package com.miempresa.flappyclon;

public class YellowBird extends Enemys{

    public YellowBird(float x, float y, float speed, float sizeEnemy){
        super(x, y, speed, sizeEnemy);
        setX(x);
        setY(y);
    }

    @Override
    public void moveEnemy() {
        setX(getX() - getSpeed());

    }
}
