package com.carlca.config;

import com.carlca.utils.EmptyStackTraceException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;

public class PackageName {

    public static class PackageNameNested {}

    private PackageName() {}

    public static final String JAVA = "java";

    public static String getCurrentPackage() {
        return PackageNameNested.class.getPackage().getName();
    }

    public static String getShortCurrentPackage() {
        String packageName = getCurrentPackage();
        ArrayList<String> parts = new ArrayList<>(Arrays.asList(packageName.split("\\.")));
        return parts.get(parts.size() - 1);
    }

    /*
    getConfigFolder: /Users/carlcaulkett/Library/Application Support/com.carlca.config
    getCurrentPackage: com.carlca.config
    getShortCurrentPackage: config
    getStackTrace:
    [java.lang.Thread, com.carlca.config.PackageName, com.carlca.MidiMix.MidiMixExtension,
     com.carlca.MidiMix.MidiMixExtension, com.carlca.MidiMix.MidiMixExtension,
     com.bitwig.extension.callback.ShortMidiMessageReceivedCallback,
     tPd, TnA, br, pzj, fUo, EwM, Vbz, pzj, Vbz, HRT, xNy, HRT]
    */

    public static String getApp() {
        ArrayList<String> trace = getStackTrace();
        return "";
    }

    public static ArrayList<String> getStackTrace() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        ArrayList<String> trace = new ArrayList<>();
        for (StackTraceElement stackTraceElement : stackTrace) {
            trace.add(stackTraceElement.getClassName());
        }
        return trace;
    }

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

    public static String getPackageName() {
        String packageName = "";
        ArrayList<String> stackTrace = PackageName.getStackTrace();
        String currentPackage = PackageName.getCurrentPackage();
        if (stackTrace.isEmpty()) { throw new EmptyStackTraceException(); }
        if (removeElements(stackTrace, JAVA)) {
            if (removeElements(stackTrace, currentPackage)) {
                if (stackTrace.isEmpty()) { throw new EmptyStackTraceException(); }
                packageName = stackTrace.get(0);
            }
        }
        return packageName;
    }
}
