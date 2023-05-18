package com.carlca.logger;

import java.io.*;
import java.net.*;
import com.carlca.config.Config;

public class Receiver {

    public static void main(String[] args) {
        try {
            int port = RandomSocketFinder.findRandomSocket();
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Receiver listening on port " + port);
            Config config = new Config("MidiMix");
            config.setLogPort(port);
            // handle ctrl/c
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
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
                System.out.println("Received message: " + message);

                reader.close();
                socket.close();
            }
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}