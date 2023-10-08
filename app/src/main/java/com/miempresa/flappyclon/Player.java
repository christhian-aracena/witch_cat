package com.miempresa.flappyclon;

public class Player {
    private int countCoins;
    private int life;
    public Player(){

        this.life = 3;
    }

    public int getCountCoins() {
        return countCoins;
    }

    public void setCountCoins(int countCoins) {
        this.countCoins++;

    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        if(this.life>3){
            this.life=3;
        }

        this.life += life;
    }
}
