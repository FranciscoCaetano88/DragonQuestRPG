package org.academiadecodigo.www.game.player;

public interface Playable {

    int getHealth();

    String getInstruction(String instruction);

    boolean isDead();

}
