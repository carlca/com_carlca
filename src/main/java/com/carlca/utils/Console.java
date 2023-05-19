package com.carlca.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Console {

    public static boolean hasFormattingPlaceholders(String text) {
        Pattern pattern = Pattern.compile("%[\\w%]");
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }

}
