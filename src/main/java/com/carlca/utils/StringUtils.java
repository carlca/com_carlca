package com.carlca.utils;

public class StringUtils {

    public static String data1Hex(ShortMidiMessage msg) {
        return String.format("%1$02X", msg.getData1());
    }

    public static String data2Hex(ShortMidiMessage msg) {
        return String.format("%1$02X", msg.getData2());
    }


}
