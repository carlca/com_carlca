package com.carlca.config;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Properties;

public class Config {

    public Config(String appName) {
        this.appName = appName;
        this.InitFolder();
    }

    private static Integer os;
    private static final Integer WINDOWS = 0;
    private static final Integer MACOS = 1;
    private static final Integer LINUX = 2;

    private final String appName;

    public String getAppName() {
        return appName;
    }

    public String getConfigFolder() {
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
        folder = Objects.requireNonNull(folder).resolve(this.getAppName());
        return Objects.toString(folder, "");
    }

    private void InitFolder() {
        if (this.appName.isEmpty()) {
            throw
        }
        String folder = this.getConfigFolder();
    }

    private Integer getOs() {
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