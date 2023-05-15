package com.carlca.config;

import java.io.IOException;

public class PackageName {

    public static void main(String[] args) throws IOException {
        PackageNameHelper helper = new PackageNameHelper();
        System.out.println(helper.getPackageName());
    }
}