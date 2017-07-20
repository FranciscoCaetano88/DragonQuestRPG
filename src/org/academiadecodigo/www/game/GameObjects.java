package org.academiadecodigo.www.game;

public class GameObjects implements Destructible{

    @Override
    public void takeHit(int attackPower) {}

    @Override
    public boolean isDestroyed() {
        return false;
    }

    public void postAlert(String message){

    }
}
