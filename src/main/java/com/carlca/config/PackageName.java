package com.carlca.config;

public class PackageName {

    public static class PackageNameNested {}

    private PackageName() {}

    public static String getFullName() {
        return PackageNameNested.class.getPackage().getName();
    }

    public static String getShortName() {
        String packageName = getFullName();
        String[] parts = packageName.split("\\.");
        return parts[parts.length - 1];
    }
}
