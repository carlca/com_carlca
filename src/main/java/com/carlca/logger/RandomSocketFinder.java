package com.carlca.logger;

import java.net.*;

public class RandomSocketFinder {
 
    private static final int MAX_PORT = 65535; // maximum possible port number

    public static int findRandomSocket() {
        int port;
        do {
            port = (int) (Math.random() * MAX_PORT) + 1;
        } while (!isPortAvailable(port));
        return port;
    }

    private static boolean isPortAvailable(int port) {
        try (ServerSocket serverSocket = new ServerSocket()) {
            serverSocket.setReuseAddress(false);
            serverSocket.bind(new InetSocketAddress(port));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}