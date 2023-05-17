package com.carlca.config;

import java.util.Objects;
import java.util.Properties;
import java.util.prefs.Preferences;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class ConfigFolder {

    private static Integer os;
    private static final Integer WINDOWS = 0;
    private static final Integer MACOS = 1;
    private static final Integer LINUX = 2;

    public static String getConfigFolder() {
        Integer os = getOs();
        Path folder = null;
        Properties props = System.getProperties();
        if (Objects.equals(os, WINDOWS)) {
            folder = Paths.get((String)props.get("APPDATA")).resolve("config");
        } else if (Objects.equals(os, MACOS)) {
            folder = Paths.get((String)props.get("user.home")).resolve("Library/Application Support");
        } else if (Objects.equals(os, LINUX)) {
            folder = Paths.get((String)props.get("user.home")).resolve(".config");
        }
        folder = Objects.requireNonNull(folder).resolve(PackageName.getCurrentPackage());
        return Objects.toString(folder, "");
    }

    private static Integer getOs() {
        String osName = System.getProperty("os.name").toLowerCase();
        Integer os;
        if (osName.contains("win")) {
            os = WINDOWS;
        } else if (osName.contains("mac")) {
            os = MACOS;
        } else {
            os = LINUX;
        }
        return os;
    }
}