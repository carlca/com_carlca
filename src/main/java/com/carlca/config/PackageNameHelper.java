package com.carlca.config;

public class PackageNameHelper {

    public String getPackageName() {
        return this.getClass().getPackage().getName();
    }
}
