package com.carlca.logger;

import java.nio.file.*;

import com.carlca.config.*;

public class ConfigUser {

    public static void main(String[] args) {
        String configFolder = ConfigFolder.getConfigFolder();
        System.out.printf("configFolder: %s\n", configFolder);
    }
}
