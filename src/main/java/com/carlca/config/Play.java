package com.carlca.config;

import java.util.Objects;
import org.json.JSONObject;

public class Play {

    public static void main(String[] args) {
        JSONObject jo = new JSONObject();
        jo.put("name", "jon doe");
        jo.put("age", "22");
        jo.put("city", "chicago");
        System.out.printf("json: %s\n", jo.toString());
    }
}