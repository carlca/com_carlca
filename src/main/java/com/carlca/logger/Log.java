package com.carlca.logger;

import java.io.*;
import java.net.*;
import com.carlca.config.Config;
import com.carlca.utils.Console;

public class Log {

    private static String appName;
    private static Socket socket;
    private static BufferedWriter writer;

    private Log() {
    }

    public static void init(String appName) {
        Log.appName = appName;
    }

    private static void initSockets() {
        Config config = new Config(Log.appName);
        Integer port = config.getLogPort();
        try {
            if (port > 0) {
                socket = new Socket("localhost", port);
                OutputStream outputStream = socket.getOutputStream();
                writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void send(String msg, Object... args) {
        initSockets();
        if (Console.hasFormattingPlaceholders(msg)) {
            sendMessage(String.format(msg, args));
        } else {
            sendMessage(msg);
        }
    }

    private static void sendMessage(String msg) {
        if (writer != null) {
            try {
                writer.write(msg + System.lineSeparator());
                writer.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}