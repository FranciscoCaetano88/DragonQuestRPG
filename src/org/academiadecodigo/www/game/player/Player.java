package org.academiadecodigo.www.game.player;

import org.academiadecodigo.www.game.Destructible;
import org.academiadecodigo.www.game.item.Item;
import org.academiadecodigo.www.game.item.KindOfItem;

import java.util.ArrayList;

public class Player implements Playable {

    /**
     * Player properties
     */

    private String name;

    private int health = 100;
    private int attackingPower = 1;
    private int shield = 0;

    private ArrayList<Item> inventory;

    private boolean dead;

    public Player(String name) {
        this.name = name;
        this.dead = false;
        inventory = new ArrayList<>();
    }

    /**
     * Player attacks and sets a casedDamage to be accessed by the Game
     *
     * @param destructible
     */
    public void attack(Destructible destructible) {

        if (destructible.isDestroyed()) {
            System.out.println("Enemy is already dead");
            return;
        }

        destructible.takeHit(attackingPower);

    }

    /**
     * Player defends from de attacking power from the Destructibles
     */
    private void defend(int monsterAttackPower) {

        if (!isDead()) {
            shield -= monsterAttackPower;
            if (shield <= 0) {
                takeHit(Math.abs(shield));

            }

        }

    }

    public void takeHit(int monsterAttackPower) {
        if (health <= 0) {
            return;
        }

        if (shield > 0) {
            defend(monsterAttackPower);
            return;
        }

        health -= monsterAttackPower;

    }

    /**
     * Lists Player inventory and the item index to use in equipGear() method
     */
    public void listInventory() {

        if (inventory.size() == 0) {
            System.out.println("I have nothing in my inventory");
            return;
        }

        for (int i = 0; i < inventory.size(); i++) {
            System.out.println(inventory.get(i) + " INDEX: " + i);

        }
    }

    /**
     * Interact with Item and set it to use
     *
     * @param index
     */
    public void equipGear(int index) {

        if ((index < inventory.size()) && (index >= 0)) {
            if (inventory.get(index).getItemKind() == KindOfItem.WEAPON) {
                upgradeAttackingPower(index);
                return;
            }

            if (inventory.get(index).getItemKind() == KindOfItem.DEFENSE) {
                upgradeBasicDefense(index);
                return;
            }

            if (inventory.get(index).getItemKind() == KindOfItem.HEALING) {
                upgradeHealthValue(index);
                return;

            }

            System.out.println("I can't use that item.");
            return;

        }

        System.out.println("There's nothing in this slot.");
    }

    /**
     * TODO: NEED TO THINK ABOUT THIS
     */
    public void unequip() {

    }

    /**
     * reset player stats and inventory when player is dead
     * TODO: Health and shield should be variable. They depend on the bonuses
     */
    private void rest() {
        health = 100;
        shield = 0;

    }

    /**
     * Updates player shield value
     *
     * @param index ArrayList index value
     */
    private void upgradeBasicDefense(int index) {
        shield = shield + inventory.get(index).getBonus();
        System.out.println(inventory.get(index).toString() + " is equiped");
        System.out.println("Your basic defense is now " + shield);
    }

    /**
     * Updates player attacking power value
     *
     * @param index ArrayList index value
     */
    private void upgradeAttackingPower(int index) {
        attackingPower = attackingPower + inventory.get(index).getBonus();
        System.out.println(inventory.get(index).toString() + " is equiped");
        System.out.println("Your power attack is now up to " + attackingPower + " Health Points");
    }

    /**
     * Updates player health value
     *
     * @param index ArrayList index value
     */
    private void upgradeHealthValue(int index) {
        health = health + inventory.get(index).getBonus();
        System.out.println("You have been healed");
        System.out.println("Your health is now " + health);
    }

    /**
     * Adds an item to the player inventory
     *
     * @param item
     */
    public void addToInventory(Item item) {
        inventory.add(item);
        System.out.println("Added " + item + " to my inventory");
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public int getShield() {
        return shield;
    }

    public int getAttackingPower() {
        return attackingPower;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public String getInstruction(String instructions) {
        return null;
    }

    /**
     * @return if player is dead or not
     */
    @Override
    public boolean isDead() {
        return health <= 0;
    }

    /**
     * When player is dead, set it to true
     *
     * @param dead
     */
    public void setDead(boolean dead) {
        this.dead = dead;
    }

    /**
     * @return players properties
     */
    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", attacking power: " + attackingPower +
                ", basic defense: " + shield +
                ", inventory: " + inventory +
                '}';
    }

    /**
     * Different places where players can be moved
     *
     * @param direction where player can move
     */
    public void move(String direction) {
        switch (direction) {
            case "north":
                System.out.println("You moved NORTH");
                break;
            case "south":
                System.out.println("You moved SOUTH");
                break;
            case "west":
                System.out.println("You moved WEST");
                break;
            case "east":
                System.out.println("You moved EAST");
                break;
            default:
                System.out.println("Not a valid direction");
                break;
        }

    }

    /**
     * What directions are available for players to take
     */
    public enum Directions {
        NORTH("north"),
        SOUTH("south"),
        WEST("west"),
        EAST("east");

        private String direction;

        Directions (String direction) {
            this.direction = direction;
        }
    }
}
