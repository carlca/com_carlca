package com.carlca.config;

import org.json.JSONObject;

public class Play {

    public static void main(String[] args) {
        JSONObject jo = new JSONObject();
        jo.put("name", "jon doe");
        jo.put("age", "22");
        jo.put("city", "chicago");
        System.out.printf("json: %s\n", jo.toString());

        Config config = new Config("Play");
        System.out.printf("getConfigFolder: %s\n", config.getConfigFolder());
        System.out.printf("getPackageName: %s\n", config.getAppName());

    }
}