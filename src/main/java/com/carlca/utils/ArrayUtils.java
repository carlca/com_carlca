package com.carlca.utils;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayUtils {

    public static Boolean removeElements(ArrayList<String> list, String item) {
        // this should remove all members of list which start with element
        boolean found = false;
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            if (element.trim().startsWith(item.trim())) {
                iterator.remove();
                found = true;
            }
        }
        return found;
    }

    public static ArrayList<String> getStackTrace() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        ArrayList<String> trace = new ArrayList<>();
        for (StackTraceElement stackTraceElement : stackTrace) {
            trace.add(stackTraceElement.getClassName());
        }
        return trace;
    }

}
