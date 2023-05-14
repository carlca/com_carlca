package com.carlca.config;

import java.util.Objects;
import java.util.Properties;

public class Play {

    public static void main(String[] args) {
        String rootPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
        String appConfigPath = rootPath + "app.properties";
        String catalogConfigPath = rootPath + "catalog";
        System.out.printf("rootPath: %s\n", rootPath);
        String currentDirectory = System.getProperty("user.dir");
        System.out.printf("user.dir:  %s\n", currentDirectory);
    }
}
