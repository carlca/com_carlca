package com.carlca.config;

import com.carlca.utils.ConfigPathException;
import com.carlca.utils.EmptyAppNameException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;

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

    public Integer getLogPort() {
        JSONObject jsonObject = getJsonObject();
        return jsonObject.getInt("logPort");
    }

    public void setLogPort(Integer logPort) {
        JSONObject jsonObject = getJsonObject();
        jsonObject.put("logPort", logPort);
        setJsonObject(jsonObject);
    }

    private JSONObject getJsonObject() {
        try {
            Path path = this.getConfigPath();
            if (Files.exists(path)) {
                String content = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
                return new JSONObject(content);
            }
            return new JSONObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setJsonObject(JSONObject jsonObject) {
        try {
            Files.write(this.getConfigPath(), jsonObject.toString(4).getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    private Path getConfigPath() {
        String folder = this.getConfigFolder();
        return Paths.get(folder).resolve("config.json");
    }

    private void InitFolder() {
        if (this.appName.isEmpty()) {
            throw new EmptyAppNameException();
        }
        Path path = Paths.get(this.getConfigFolder());
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
        } catch (Exception e){
            throw new ConfigPathException(String.format("Could not construct folder: %s", path));
        }
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
