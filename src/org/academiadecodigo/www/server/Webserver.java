package org.academiadecodigo.www.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Webserver {

    final private int PORT = 6969;

    //State: Shared and mutable
    private CopyOnWriteArrayList<ClientHandler> list;

    public static void main(String[] args) {
        Webserver webserver = new Webserver();
        webserver.start();

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
        private CommandParser commandParser;

        private ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
            this.commandParser = new CommandParser();

        }

        @Override
        public void run() {

            try {

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                send("Please Insert your username:");
                userName = in.readLine();

                send("****** Your username is: " + userName + " ******");
                System.out.println("****** " + userName + ": logged in ******\n");
                sendAll("****** " + userName + " logged in channel ******");

                String msg;

                while (true) {
                    msg = in.readLine();

                    validateCommand(msg);

                    if (msg == null) {
                        System.out.println("****** " + userName + ": logged out ******");
                        break;

                    }

                    sendAll("< " + userName + "_> " + msg);

                }

            } catch (IOException e) {
                System.err.println("****** FAILED TO RECEIVE MESSAGE ****** " + e.getMessage());

            }

        }

        private void validateCommand(String msg) {
            commandParser.split(msg);

            Commands command = Commands.whichCommand(commandParser.getCommand());

            if (command == null) {
                return;
            }

            switch (command) {

                case PM:
                    System.out.println("****** I'm HERE ******");

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

