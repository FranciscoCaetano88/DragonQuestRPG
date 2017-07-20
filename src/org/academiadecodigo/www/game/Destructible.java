package org.academiadecodigo.www.game;

public interface Destructible {

    void takeHit(int attackPower);
    boolean isDestroyed();
}
