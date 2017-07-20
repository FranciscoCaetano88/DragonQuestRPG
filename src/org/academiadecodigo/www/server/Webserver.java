package org.academiadecodigo.www.server;

import org.academiadecodigo.www.command.CommandParser;
import org.academiadecodigo.www.command.Commands;
import org.academiadecodigo.www.FileManager;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Webserver {

    final private int PORT = 6969;
    private CommandParser commandParser;
    private FileManager fileManager;

    //State: Shared and mutable
    private CopyOnWriteArrayList<ClientHandler> list;

    public static void main(String[] args) {
        Webserver webserver = new Webserver();
        webserver.start();

    }

    public Webserver() {
        this.commandParser = new CommandParser();
        this.fileManager = new FileManager();

    }

    private void start() {

        try {

            list = new CopyOnWriteArrayList<>();
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("****** SERVER OPENED PORT: " + PORT + " ******\n");

            while (true) {

                Socket clientSocket = serverSocket.accept();

                //Puts Client in Blocking List
                ClientHandler currentClientHandler = new ClientHandler(clientSocket);
                list.add(currentClientHandler);

                ExecutorService fixedPool = Executors.newFixedThreadPool(10);
                fixedPool.submit(currentClientHandler);

            }

        } catch (IOException e) {
            System.err.println("****** SOCKET CLOSED ****** " + e.getMessage());

        }

    }

    private void sendAll(String msg) {
        for (ClientHandler c : list) {
            c.send(msg);

        }

    }

    private class ClientHandler implements Runnable {

        private Socket clientSocket;
        private String userName;
        private String password;

        private ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
            this.userName = "";
            this.password = "";

        }

        @Override
        public void run() {

            try {

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                //Username Check
                String username = "";
                while (username.trim().equals("")) {
                    send("Please Insert your Username:");
                    username = in.readLine();

                    username = validate(username);
                }
                this.userName = username;

                /*//Password Check
                String password = "";
                while (password.trim().equals("")) {
                    send("Please Enter your Password:");
                    password = in.readLine();

                    password = validateP(password);
                }
                this.password = password;

                //File Check Users
                if (!checkUser()) {
                    fileManager.write("users", userName + ":" + password);
                }*/

                logInMessage();

                //Begin Game Thread
                String msg = "";

                while (true) {
                    msg = in.readLine();

                    if (msg == null) {
                        System.out.println("****** " + userName + ": logged out ******\n");
                        removeFromList();
                        break;
                    }

                    if (msg.trim().equals("")) {
                        continue;
                    }

                    handle(msg);

                }

            } catch (IOException e) {
                System.err.println("****** FAILED TO RECEIVE MESSAGE ****** " + e.getMessage());

            }

        }

        /**
         * Remove client from the list
         */
        private void removeFromList() {
            list.remove(this);

        }

        /**
         * Method to validate a user name in the register
         * @param userName                  String
         * @return                          String
         */
        private String validate(String userName) {

            if (userName.split(" ").length > 1) {
                send("Your Username can't contain blank spaces");
                return "";
            }

            if (userName.length() > 10) {
                send("Your Username has to be less than 10 characters");
                return "";
            }

            for (ClientHandler ch : list) {
                if (ch.getUserName().equals(userName)) {
                    send("This username already exists");
                    return "";
                }
            }

            return userName;

        }

        /**
         * Simple message when user logs in
         */
        private void logInMessage() {
            send("****** Your username is: " + userName + " ******");
            System.out.println("****** " + userName + ": logged in ******");
            sendAll("****** " + userName + " logged in ******");
        }

        /**
         * Lists all clients logged in
         */
        private void listClients() {
            for (ClientHandler ch : list) {
                send(ch.getUserName());

            }

        }

        /**
         * TODO: Need to think about this
        private String validateP(String password) {
            if (password.length() > 10) {
                send("Your Password has to be less than 10 characters");
                return "";
            }


            if (!this.password.equals(password)) {
                send("Wrong password");
                return "";
            }

            return password;
        }*/



        /**
         * TODO: Check if users already logged in, (saved in a file)
        private boolean checkUser() {

            try {
                String user = fileManager.read("users");
                String[] line = user.split("\n");
                String[] users;

                for (int i = 0; i < line.length; i++) {
                    users = line[i].split(":");
                    if (line[i].equals(userName)) {
                        System.out.println(list.get(i).userName);
                        return true;
                    }
                }

            } catch (IOException e) {
                System.err.println("File not found!" + e.getMessage());

            }

            return false;

        }*/


        /**
         * Handles command actions
         * @param msg       String
         */
        private void handle(String msg) {

            commandParser.split(msg);

            Commands command = Commands.which(commandParser.getCommand());

            if (command == null) {
                sendAll("< " + userName + "_> " + msg);
                return;
            }


            switch (command) {

                case PM:
                    sendPrivate(commandParser.getActionCommand(), commandParser.getText());
                    break;

                case LIST:
                    listClients();
                    break;

                case COMMANDS:
                    listCommands();
                    break;

                case EXIT:
                    send("****** LOGING OUT ******");
                    try {
                        this.clientSocket.close();
                        removeFromList();
                    } catch (IOException e) {
                        System.err.println("Failed to close Socket " + e.getMessage());
                    }

            }

        }

        /**
         * Lists all commands
         */
        private void listCommands() {
            for (Commands c : Commands.values()) {
                send(c.getCommand());

            }

        }

        /**
         * Send a private message to a specific user
         * @param username                  String
         * @param msg                       String
         */
        private void sendPrivate(String username, String msg) {

            for (ClientHandler ch : list) {
                if (ch.getUserName().equals(username)) {

                    try {
                        PrintWriter out = new PrintWriter(ch.getClientSocket().getOutputStream(), true);
                        out.println("private message: < " + this.userName + "_> " + msg);

                    } catch (IOException e) {
                        System.err.println("Found a problem with the client socket" + e.getMessage());

                    }

                }

            }

        }

        /**
         * Send a reply of the message
         * @param msg           String
         */
        private void send(String msg) {

            try {
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                out.println(msg);

            } catch (IOException e) {
                System.err.println("****** FAILED TO WRITE MESSAGE ****** " + e.getMessage());

            }

        }

        private Socket getClientSocket() {
            return clientSocket;
        }

        private String getUserName() {
            return this.userName;
        }

        private String getPassword() {
            return password;
        }

    }

}
