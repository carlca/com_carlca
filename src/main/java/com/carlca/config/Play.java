package com.carlca.config;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;

public class Play {

    public static String extractClassName(ArrayList<String> list, String input) {
        for (String element : list) {
            if (element.trim().startsWith(input.trim())) {
                String[] parts = element.split("\\.");
                return parts[parts.length - 1];
            }
        }
        return null; // or any default value if class name not found
    }

    public static String test() {
        ArrayList<String> list = new ArrayList<>();
        list.add("java.lang.Thread");
        list.add("com.carlca.config.PackageName");
        list.add("com.carlca.MidiMix.MidiMixExtension");

        String input = "com.carlca.config";
        return extractClassName(list, input);
    }
}