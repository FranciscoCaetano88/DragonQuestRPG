package org.academiadecodigo.www.command;

/**
 * Created by codecadet on 20/07/17.
 */
public class CommandService {

    private Commands command;

    public void init(String command, String action, String message) {

        Commands.which(command);


    }

}
