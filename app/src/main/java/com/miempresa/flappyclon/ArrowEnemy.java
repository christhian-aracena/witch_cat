package com.miempresa.flappyclon;

public class ArrowEnemy extends Enemys{

    private boolean collected = false;

    ArrowEnemy(float x, float y, float speed, float sizeEnemy){
        super(x, y, speed, sizeEnemy);
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

}

