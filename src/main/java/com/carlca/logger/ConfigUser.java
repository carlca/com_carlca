package com.carlca.logger;

import java.nio.file.*;

import com.carlca.config.*;

public class ConfigUser {

    public static void main(String[] args) {
        String configRoot = ConfigFolder.getConfigFolderRoot();
        String shortName = PackageName.getShortName();
        String fullName = PackageName.getFullName();
        Path shortHome = Paths.get(configRoot, shortName);
        Path fullHome = Paths.get(configRoot, fullName);
        System.out.printf("Short: %s\n", shortHome);
        System.out.printf("Full: %s\n", fullHome);
    }
}
