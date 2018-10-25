package com.epam.engx.cleancode.naming.task6;

public class Formatter {

    private static final String EDGE_SYMBOL = "+";
    private static final String VERTICAL_SYMBOL = "|";
    private static final String HORIZONTAL_SYMBOL = "-";
    private static final String DELIMITER = " _ ";

    public static void main(String[] args) {
        System.out.println(formatKyeValue("enable", "true"));
        System.out.println(formatKyeValue("name", "Bob"));
    }

    private static String formatKyeValue(String key, String value) {
        String content = key + DELIMITER + value;
        String minuses = repeat(HORIZONTAL_SYMBOL, content.length());
        return EDGE_SYMBOL +  minuses + EDGE_SYMBOL + "\n"
                + VERTICAL_SYMBOL + content + VERTICAL_SYMBOL + "\n" +
                EDGE_SYMBOL + minuses + EDGE_SYMBOL + "\n";
    }

    private static String repeat(String symbol, int limit) {
        String result = "";
        while(limit > 0) {
            result += symbol;
            limit--;
        }
        return result;
    }
}
