package com.miempresa.flappyclon;

public class Player {
    private int countCoins;
    private int life;
    public Player(){
        this.countCoins = 0;
        this.life = 3;
    }

    public int getCountCoins() {
        return countCoins;
    }

    public void setCountCoins(int countCoins) {
        if(countCoins ==1){ this.countCoins++;}

    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}
