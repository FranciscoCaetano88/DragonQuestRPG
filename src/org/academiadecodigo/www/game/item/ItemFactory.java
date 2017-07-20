package org.academiadecodigo.www.game.item;

import java.util.ArrayList;
import java.util.List;

public class ItemFactory {

    private List<Item> itemList;

    public ItemFactory() {
        this.itemList = new ArrayList<>();
    }

    /**
     * @param itemType      The type of Item.
     * @param quantity      The quantity of Items to create.
     */
    public void createItem(ItemType itemType, int quantity) {

        for (int i = 0; i < quantity; i++) {

            switch (itemType) {
                case BASIC_AXE:
                    itemList.add(new Item("Basic Axe", 3, KindOfItem.WEAPON));
                    break;
                case BREAD:
                    itemList.add(new Item("Bread", 3, KindOfItem.HEALING));
                    break;
                case CLOTH:
                    itemList.add(new Item("Cloth", 0, KindOfItem.DEFAULT));
                    break;
                case STICK:
                    itemList.add(new Item("Stick", 0, KindOfItem.DEFAULT));
                    break;
                case TORCH:
                    itemList.add(new Item("Torch", 0, KindOfItem.DEFAULT));
                    break;
                case SCROLL:
                    itemList.add(new Item("Scroll", 0, KindOfItem.DEFAULT));
                    break;
                case BASIC_SHIELD:
                    itemList.add(new Item("Basic Shield", 2, KindOfItem.DEFENSE));
                    break;
                case SMALL_ROCK:
                    itemList.add(new Item("Small Roch", 0, KindOfItem.DEFAULT));
                    break;
                case RUSTY_KNIFE:
                    itemList.add(new Item("Rusty Knife", 0, KindOfItem.DEFAULT));
                    break;
                case LIGHTED_TORCH:
                    itemList.add(new Item("Lighted Torch", 0, KindOfItem.DEFAULT));
                    break;
                case PILE_OF_LEAVES:
                    itemList.add(new Item("Pile of Leaves", 0, KindOfItem.DEFAULT));
                    break;
                default:
                    System.out.println("Something went really wrong...");
                    break;

            }

        }

    }

    /**
     * Returns a list of Items.
     * @return          Returns the list of Items created.
     */
    public List<Item> getItemList() {
        return itemList;
    }
}
