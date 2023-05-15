package com.carlca.config;

import java.io.IOException;

public class ConfigPath {

    public static String getPath() {
        String userHome = System.getProperty("user.home");
        String sep = System.getProperty("file.separator");
        String path;
        path = userHome + sep + "." + PackageName.getShortName();
        return path;
    }

    public static void main(String[] args) throws IOException {
         System.out.printf("Config path: %s\n", new ConfigPath().getPath());
    }
}