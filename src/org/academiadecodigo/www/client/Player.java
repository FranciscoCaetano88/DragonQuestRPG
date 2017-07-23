package org.academiadecodigo.www.client;

import org.academiadecodigo.www.game.item.Item;
import org.academiadecodigo.www.game.map.MapZones;

import java.util.LinkedList;
import java.util.List;

public class Player extends Client {

    private List<Item> inventory;

    private MapZones mapZones;

    public Player() {
        this.inventory = new LinkedList<>();

    }

    public void addToInventory(Item item) {
        inventory.add(item);
    }

    public void removeFromInvetory(String itemName) {

        for (Item i : inventory) {
            if (i.getName().equals(itemName)) {
                inventory.remove(i);
            }

        }

    }

    public void listInventory() {

        for (Item i : inventory) {
            System.out.println(i.getName());
        }

    }

    public void setMapZones(MapZones mapZones) {
        this.mapZones = mapZones;
    }

    public MapZones getMapZones() {
        return mapZones;
    }

}
