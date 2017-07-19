package org.academiadecodigo.www.server;

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

        }

        @Override
        public void run() {

            try {

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                //Username Check
                while (userName.trim().equals("")) {
                    send("Please Insert your Username:");
                    userName = in.readLine();

                    validateUsername();
                }

                //Password Check
                while (password.trim().equals("")) {
                    send("Please Enter your Password:");
                    password = in.readLine();

                    validatePassword();
                }

                //File Check Users
                if (!checkUser()) {
                    fileManager.write("users", userName);
                }

                logInMessage();

                //Begin Game Thread
                String msg = "";

                while (true) {
                    msg = in.readLine();

                    if (msg == null) {
                        System.out.println("****** " + userName + ": logged out ******\n");
                        removeClient();
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

        private void logInMessage() {
            send("****** Your username is: " + userName + " ******");
            System.out.println("****** " + userName + ": logged in ******");
            sendAll("****** " + userName + " logged in ******");
        }

        private void removeClient() {
            for (ClientHandler ch : list) {
                if (ch.getUserName().equals(userName)) {
                    list.remove(this);
                }
            }
        }

        private boolean checkUser() {

            try {
                String user = fileManager.read("users");
                String[] users = user.split("\n");

                for (int i = 0; i < users.length; i++) {
                    if (users[i].equals(userName)) {
                        System.out.println(list.get(i).userName);
                        return true;
                    }
                }

            } catch (IOException e) {
                System.err.println("File not found!" + e.getMessage());

            }

            return false;

        }

        private void handle(String msg) {

            commandParser.split(msg);

            Commands command = Commands.whichCommand(commandParser.getCommand());

            if (command == null) {
                sendAll("< " + userName + "_> " + msg);
                return;
            }

            switch (command) {

                case PM:
                    System.out.println("Send message to this Username: " + commandParser.getActionCommand());
                    System.out.println("The message is: " + commandParser.getText());
                    break;

            }

        }

        private void validateUsername() {

            if (userName.split(" ").length > 1) {
                send("Your Username can't contain blank spaces");
                userName = "";
            }

            if (userName.length() > 10) {
                send("Your Username has to be less than 10 characters");
                userName = "";
            }

        }

        private void validatePassword() {
            if ((password.length() < 3) && (password.length() > 10)) {
                send("Your Password has to be less than 10 characters");
                password = "";
            }
        }

        private void send(String msg) {

            try {
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                out.println(msg);

            } catch (IOException e) {
                System.err.println("****** FAILED TO WRITE MESSAGE ****** " + e.getMessage());

            }

        }

        private String getUserName() {
            return this.userName;
        }

        private void closeCommand() {
            list.remove(this);

        }

        private void listCommand() {
            for (ClientHandler c : list) {
                send(c.getUserName());

            }

        }


        /*
        private void logCommand() {

            try {
                FileReader reader = new FileReader(FILE_PATH);
                BufferedReader fileBReader = new BufferedReader(reader);

                String line = "";

                send("##LOG##");
                while ((line = fileBReader.readLine()) != null) {
                    send(line);

                }

            } catch (FileNotFoundException e) {
                System.err.println("****** CAN'T READ FILE ****** " + e.getMessage());

            } catch (IOException e) {
                System.err.println("****** CAN'T READ LINE FROM BUFFER ****** " + e.getMessage());

            }

        }*/

    }

}
