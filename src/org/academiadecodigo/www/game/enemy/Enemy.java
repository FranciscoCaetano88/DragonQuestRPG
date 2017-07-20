package org.academiadecodigo.www.game.enemy;


import org.academiadecodigo.www.game.Destructible;
import org.academiadecodigo.www.game.player.Player;

/**
 * Created by codecadet on 10/07/2017.
 */
public class Enemy implements Destructible {

    private EnemyType enemyType;
    private int shield;
    private int health;
    private int attackPower;

    /**
     * Constructs an Enemy from a specified type.
     * @param enemyType
     */

    public Enemy(EnemyType enemyType, int health, int shield, int attackPower) {
        this.enemyType = enemyType;
        this.health = health;
        this.shield = shield;
        this.attackPower = attackPower;
    }

    /**
     * It calls the method that prints out an attack message and causes damage to players.
     */
    public void attack(Player player) {
        player.setHealth(player.getHealth() - attackPower);

    }

    /**
     * It calls the method that prints a defense message and takes health from the enemy.
     * It also verifies if the shield has been destroyed or not and causes damage to the enemy if it is destroyed.
     */
    private void defend(int playerAttackPower) {

        if (!isDestroyed()) {
            shield -= playerAttackPower;
            if (shield <= 0) {
                takeHit(Math.abs(shield));

            }

        }

    }

    /**
     * It shows the current health of the enemy.
     * @return int health
     */

    public int getHealth() {
        return health;
    }

    /**
     * It shows the current shield of the enemy.
     * @return int shield
     */
    public int getShield() {
        return shield;
    }

    /**
     * It shows the amount of attacking power the enemy has.
     * @return
     */
    public int getAttackPower() {
        return attackPower;
    }

    /**
     * Represents the players incoming damage to the enemy.
     * @param playerAttackPower
     */
    @Override
    public void takeHit(int playerAttackPower) {
        if (health <= 0) {
            return;
        }

        if (shield > 0) {
            defend(playerAttackPower);
            return;
        }

        health -= playerAttackPower;

    }

    /**
     * It verifies if the enemy is dead.
     * @return boolean
     */
    @Override
    public boolean isDestroyed() {
        return health <= 0;
    }

    @Override
    public String toString() {
        return "Enemy{" +
                "enemyType=" + enemyType +
                ", shield=" + shield +
                ", health=" + health +
                ", attackPower=" + attackPower +
                '}';
    }

}
