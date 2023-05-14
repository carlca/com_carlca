package com.carlca.config;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ListProperties {

    public static void main(String[] args) throws IOException {
        Properties p = System.getProperties();
        Enumeration<Object> keys = p.keys();
        while (keys.hasMoreElements()) {
            String key = (String)keys.nextElement();
            String value = (String)p.get(key);
            System.out.println(key + ": " + value);
        }
        System.out.println((new File(".")).getAbsolutePath());
        System.out.println((new File(".")).getCanonicalPath());
    }
}
