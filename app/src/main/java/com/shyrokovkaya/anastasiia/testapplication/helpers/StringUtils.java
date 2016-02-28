package com.shyrokovkaya.anastasiia.testapplication.helpers;

public class StringUtils {

    public static String capitalize(final String str) {
        return Character.toUpperCase(str.charAt(0)) + str.substring(1).toLowerCase();
    }
}
