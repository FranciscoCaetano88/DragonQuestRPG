package org.academiadecodigo.www.game.map;

import org.academiadecodigo.www.game.enemy.Enemy;
import org.academiadecodigo.www.game.item.Item;

import java.util.List;

public class Map {

    private MapZones mapZones;

    private List<Item> items;
    private List<Enemy> enemies;

    private boolean mapBlocker = true;

    public Map(MapZones mapZones, List<Enemy> enemies, List<Item> items) {
        this.mapZones = mapZones;
        this.items = items;
        this.enemies = enemies;

    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<Item> getItems() {
        return items;
    }

    public MapZones getMapZones() {
        return mapZones;
    }

    public boolean getMapBlocker() {
        return mapBlocker;
    }

    public void setMapBlocker(boolean mapBlocker) {
        this.mapBlocker = mapBlocker;
    }

    @Override
    public String toString() {
        return "Map{" +
                "mapZones=" + mapZones +
                ", items=" + items +
                ", enemies=" + enemies +
                ", mapBlocker=" + mapBlocker +
                '}';
    }

}
