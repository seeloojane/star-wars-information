package com.example.demo;

import lombok.experimental.UtilityClass;

@UtilityClass
public class NumberParser {

    public static int parseCrewCountString(String count) {
        return Integer.parseInt(count.replaceAll(",", ""));
    }
}
