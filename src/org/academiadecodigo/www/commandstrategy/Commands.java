package org.academiadecodigo.www.commandstrategy;

public enum Commands {

    //Player command
    CD("cd"),
    LS("ls"),
    PICK("pick"),
    MAP("map"),
    INVENTORY("inv"),
    ATTACK("attack"),
    DEFEND("defend"),
    INTERACT("interact"),

    //Server command
    PM("/pm"),
    KICK("/kick"),
    LIST("/list"),
    COMMANDS("/commands");


    private String command;

    Commands(String command) {
        this.command = command;
    }

    /**
     * Determines which Player commandstrategy is at stake
     *
     * @param command           String command
     * @return                  command
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

            case "/list":
                return LIST;

            case "/commands":
                return COMMANDS;

        }

        return null;

    }

    public String getCommand() {
        return command;
    }

}
