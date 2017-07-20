package org.academiadecodigo.www.game.item;

public enum ItemType {
    BASIC_SHIELD(KindOfItem.DEFENSE),
    BASIC_AXE(KindOfItem.WEAPON),
    BREAD(KindOfItem.HEALING),
    RUSTY_KNIFE(KindOfItem.DEFAULT),
    STICK(KindOfItem.DEFAULT),
    CLOTH(KindOfItem.DEFAULT),
    SMALL_ROCK(KindOfItem.DEFAULT),
    TORCH(KindOfItem.DEFAULT),
    LIGHTED_TORCH(KindOfItem.DEFAULT),
    SCROLL(KindOfItem.DEFAULT),
    PILE_OF_LEAVES(KindOfItem.DEFAULT);

    private KindOfItem itemKind;

    ItemType (KindOfItem itemKind) {
        this.itemKind = itemKind;
    }

    public ItemType whichItem(String itemType) {

        for (ItemType it : ItemType.values()) {
            if (it.name().equals(itemType)) {
                return ItemType.valueOf(itemType);

            }

        }

        return null;

    }

}


