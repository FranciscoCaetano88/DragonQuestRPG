package org.academiadecodigo.www.client;

import org.academiadecodigo.www.command.CommandParser;
import org.academiadecodigo.www.command.Commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {

    final private int PORT = 6969;
    final private String HOST = "localhost";
    private CommandParser commandParser;

    public static void main(String[] args) {
        Client client = new Client();
        client.start();

    }

    public Client() {
        this.commandParser = new CommandParser();
    }

    public void start() {

        try {

            Socket clientSocket = new Socket(HOST, PORT);
            System.out.println("****** CLIENT CONNECTED TO HOST: " + HOST + " PORT: " + clientSocket.getPort() + " ******");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String msg;

            ExecutorService fixedPool = Executors.newFixedThreadPool(4);
            fixedPool.submit(new ClientRunnable(clientSocket));

            while (true) {
                msg = in.readLine();

                if (msg == null) {
                    Thread.currentThread().interrupt();
                    clientSocket.close();
                    System.exit(-1);

                }

                System.out.println(msg);

            }

        } catch (IOException e) {
            System.err.println("****** SOCKET CLOSED ****** " + e.getMessage());

        }

    }

    private class ClientRunnable implements Runnable {

        private Socket clientSocket;

        public ClientRunnable(Socket clientSocket) {
            this.clientSocket = clientSocket;

        }

        @Override
        public void run() {

            try {

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                while (true) {

                    String message = getMessage();
                    out.println(message);

                    commandHandler(message);

                }

            } catch (IOException e) {
                System.err.println("****** FAILED TO WRITE MESSAGE ****** " + e.getMessage());

            }

        }

        private String getMessage() {
            Scanner reader = new Scanner(System.in);
            return reader.nextLine();

        }

        private void commandHandler(String message) {

            commandParser.split(message);
            Commands command = Commands.getByValue(commandParser.getCommand());

            if (command == null) {
                return;
            }

            switch (command) {

                case CD:
                    System.out.println("CD");
                    break;

                case LS:
                    System.out.println("LS");
                    break;

                case MAP:
                    System.out.println("MAP");
                    break;

                case PICK:
                    System.out.println("PICK");
                    break;

                case INTERACT:
                    System.out.println("INTERACT");
                    break;

                case INVENTORY:
                    System.out.println("INVENTORY");
                    break;

                case ATTACK:
                    System.out.println("ATTACK");
                    break;

                case DEFEND:
                    System.out.println("DEFEND");
                    break;

                case EXIT:
                    Thread.currentThread().interrupt();
                    System.exit(-1);
                    break;


            }

        }

    }

}
