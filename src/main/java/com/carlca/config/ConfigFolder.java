package com.carlca.config;

import java.util.prefs.Preferences;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class ConfigFolder {

    private ConfigFolder() {}

    public static String getConfigFolderRoot() {
        Preferences prefs = Preferences.userRoot();
        String os = System.getProperty("os.name").toLowerCase();
        Path home = null;
        if (os.contains("win")) {
            home = Paths.get(prefs.get("APPDATA", ""), "config");
        } else if (os.contains("mac")) {
            home = Paths.get(prefs.get("user.home", ""), "Library/Application Support");
        } else {
            home = Paths.get(prefs.get("user.home", ""), ".config");
        }
        String root;
        root = home.toString();
        return root;
    }
}