package org.academiadecodigo.www.command;

public class CommandParser {

    private String[] command;
    private String text = "";

    /**
     * Splits a String
     * @param command    String type
     */
    public void split(String command) {

        if (command.equals("") || (command.trim().isEmpty())) {
            System.out.println("****** command not Found ******");
            return;
        }

        if (command.split(" ").length == 0) {
            this.command[0] = command;
            return;
        }

        this.command = command.split(" ");

    }

    /**
     * Returns a command
     * @return          String type
     */
    public String getCommand() {

        if (command == null) {
            return null;
        }

        return command[0];
    }

    /**
     * Returns an Action
     * @return          String type
     */
    public String getActionCommand() {

        if (command.length == 1) {
            return null;
        }

        return command[1];
    }

    /**
     * Returns a phrase
     * @return          String type
     */
    public String getText() {
        text = "";

        for (int i = 2; i < command.length; i++) {
            text = text + command[i] + " ";
        }

        return text;

    }

}
