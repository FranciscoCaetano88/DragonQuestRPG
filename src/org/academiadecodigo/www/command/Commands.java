package org.academiadecodigo.www.command;

import java.util.HashMap;
import java.util.Map;

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
    COMMANDS("/commands"),
    EXIT("/exit");

    private String command;

    private static final Map<String, Commands> COMMAND_MAP = new HashMap<>();

    private Commands(String command) {
        this.command = command;
    }

    static {
        for (Commands c : values()) {
            COMMAND_MAP.put(c.getCommand(), c);
        }
    }

    public String getCommand() {
        return command;
    }

    public static Commands getByValue(String command) {
        return COMMAND_MAP.get(command);
    }

    public String toString() {
        return name();
    }

}
