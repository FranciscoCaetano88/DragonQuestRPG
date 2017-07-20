package org.academiadecodigo.www.game.enemy;

/**
 * Created by codecadet on 11/07/2017.
 */
public enum EnemyType {
    MONSTER("MONSTER"),
    DRAGON("DRAGON"),
    DWARF("DWARF"),
    ELF("ELF"),
    MERCENARY("MERCENARY"),
    TROLL("TROLL");

    private String name;

    /**
     * Constructs the enemy type enum.
     * @param name
     */
    EnemyType(String name) {
        this.name = name;

    }

}