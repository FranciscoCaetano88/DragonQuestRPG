package org.academiadecodigo.www.game.map;

/**
 * Created by codecadet on 12/07/17.
 */
public enum MapZones {

    HOME("Home"),

    FOREST("Forest"),
        TREE_OF_LIFE("Tree Of Life"),
        RIVER_FLOW("River Flow"),
            CATARACT_FALLS("Cataract Falls"),

    MOUNTAIN("Mountain"),
        SNOW_FALL("Snow Fall"),
        ICY_PEAK("Icy Peak"),
            MOUNT_NEVERLAST("Mount Neverlast"),

    PLAIN("Plain"),
        CLIFF_EASTWOOD("Cliff Eastwood"),
        ELEPHANT_CEMETERY("Elephant Cemetery"),
            DEATH_END("Death End"),
            VOLCANO_HOLE("Volcano Hole"),
                THE_FORGE("The Forge"),
                CASTLE_OF_FLAMES("Castle of Flames");

    private String zone;

    MapZones(String zone) {
        this.zone = zone;
    }



}
