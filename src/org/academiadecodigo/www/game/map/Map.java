package org.academiadecodigo.www.game.map;

import org.academiadecodigo.www.FileManager;
import org.academiadecodigo.www.game.enemy.Enemy;
import org.academiadecodigo.www.game.item.Item;
import org.academiadecodigo.www.game.story.StoryLine;
import org.academiadecodigo.www.game.story.StoryLineParser;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Map implements StoryLine {

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

    @Override
    public void initStoryLine() {

        FileManager fileManager = new FileManager();
        StoryLineParser storyParser = new StoryLineParser();

        String fileContent = "";

        try {
            fileContent = fileManager.read(this.mapZones.name());
        } catch (IOException e) {
            System.err.println("Failed to read file " + e.getMessage());
        }

        storyParser.split(fileContent);

        System.out.println("Press [ENTER] to continue");
        switch (this.mapZones) {
            case HOME:
                home(storyParser);
                break;

            case PLAIN:
                break;

            case FOREST:
                break;

            case ICY_PEAK:
                break;

            case MOUNTAIN:
                break;

        }

    }

    private void home(StoryLineParser storyParser) {
        int storyIndex = 0;
        Scanner sc = new Scanner(System.in);
        String s = "";

        while (storyIndex < storyParser.getLine().length) {
            System.out.print(storyParser.getLineFrom(storyIndex++));
            s = sc.nextLine();

            if (storyIndex == 10) {
                while (!s.equals("/commands")) {
                    s = sc.nextLine();

                }

            }

            if (storyIndex == 14) {
                while (!s.equals("ls")) {
                    s = sc.nextLine();
                }
            }

            if (storyIndex == 21) {
                while ((!s.equals("interact") && (!s.equals("pick") && (!s.equals("attack"))))) {
                    s = sc.nextLine();
                }

            }

        }

    }

}
