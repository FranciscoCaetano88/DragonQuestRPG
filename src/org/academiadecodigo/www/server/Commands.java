package org.academiadecodigo.www.server;

/**
 * Created by codecadet on 18/07/17.
 */
public enum Commands {

    //Player Commands
    CD("cd"),
    LS("ls"),
    PICK("pick"),
    MAP("map"),
    INVENTORY("inv"),
    ATTACK("attack"),
    DEFEND("defend"),
    INTERACT("interact"),

    //Server Commands
    PM("/pm"),
    KICK("/kick");

    private String command;

    Commands(String command) {
        this.command = command;
    }

    /**
     * Determines which Player Command is at stake
     *
     * @param command           String command
     * @return                  Commands
     */
    public static Commands whichCommand(String command) {

        switch (command) {

            case "cd":
                return CD;

            case "ls":
                return LS;

            case "pick":
                return PICK;

            case "map":
                return MAP;

            case "inv":
                return INVENTORY;

            case "attack":
                return ATTACK;

            case "defend":
                return DEFEND;

            case "interact":
                return INTERACT;

            case "/pm":
                return PM;

            case "/kick":
                return KICK;

        }

        return null;

    }

    public static void listCommands() {

        for (Commands pc : Commands.values()) {
            System.out.println(pc);

        }

    }

}
