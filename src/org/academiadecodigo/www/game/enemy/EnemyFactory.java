package org.academiadecodigo.www.game.enemy;

import java.util.ArrayList;
import java.util.List;

public class EnemyFactory {

    private List<Enemy> enemyList;

    public EnemyFactory() {
        this.enemyList = new ArrayList<>();

    }

    /**
     * Creates a specified quantity of enemy, of a specified enemy type.
     * @param enemyType         The type of Enemy.
     * @param quantity          The quantity of Enemies to create.
     */
    public void createEnemy(EnemyType enemyType, int quantity) {

        for (int i = 0; i < quantity; i++) {

            switch (enemyType) {
                case MONSTER:
                    enemyList.add(new Enemy(EnemyType.MONSTER, 7, 2, 7));
                    break;
                case DRAGON:
                    enemyList.add(new Enemy(EnemyType.DRAGON, 12,12,16));
                    break;
                case DWARF:
                    enemyList.add(new Enemy(EnemyType.DWARF,5,1,3));
                    break;
                case ELF:
                    enemyList.add(new Enemy(EnemyType.ELF,9,8,10));
                    break;
                case MERCENARY:
                    enemyList.add(new Enemy(EnemyType.MERCENARY,8,10,5));
                    break;
                case TROLL:
                    enemyList.add(new Enemy(EnemyType.TROLL,8,10,5));
                    break;
                default:
                    System.err.println("Something really bad happened.");

            }

        }

    }

    /**
     * Returns a List of Enemies.
     * @return      Returns the list of Enemies created.
     */
    public List<Enemy> getEnemyList() {
        return enemyList;
    }

}
