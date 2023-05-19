package com.carlca.logger;

import java.io.*;
import java.net.*;
import com.carlca.config.Config;
import com.carlca.utils.Console;

public class Receiver {

    private static Boolean isReceiving;

    public static void main(String[] args) {
        try {
            int port = RandomSocketFinder.findRandomSocket();
            ServerSocket serverSocket = new ServerSocket(port);
            clearScreen();
            outputMessage("Receiver listening on port " + port);
            outputMessage("");
            Config config = new Config("MidiMix");
            config.setLogPort(port);
            // handle ctrl/c
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    config.setLogPort(0);
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }));

            while (!Thread.interrupted()) {
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String message = reader.readLine();
                outputMessage(message);
                reader.close();
            }
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void outputMessage(String msg) {
        if (Console.hasFormattingPlaceholders(msg)) {
            System.out.printf(msg);
        } else {
            System.out.println(msg);
        }
    }

    private static void clearScreen() {
        outputMessage("\033[H\033[2J");
        System.out.flush();
    }
}