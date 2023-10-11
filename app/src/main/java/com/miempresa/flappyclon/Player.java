package com.miempresa.flappyclon;

public class Player {
    private boolean perdiste;
    private int countCoins;
    private int life;
    public Player(){

        this.life = 3;
        this.countCoins = 0;
    }

    public int getCountCoins() {
        return countCoins;
    }

    public void setCountCoins(int countCoins) {
        this.countCoins++;

    }

    public boolean GameOver(){
        if(this.life<1){
            return perdiste=true;
        }
        else {return perdiste = false;}
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        if(this.life+life>3){
            this.life=3;
        }
        else {
            this.life++;
        }


    }

    public void setLifeDamage(){
        if (this.life > 0) {
            this.life--;
        }

    }
}
