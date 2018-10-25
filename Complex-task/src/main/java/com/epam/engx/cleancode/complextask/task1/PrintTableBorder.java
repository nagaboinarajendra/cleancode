package com.epam.engx.cleancode.complextask.task1;

public class PrintTableBorder {
    
    private static final String ROW_DELIMITER = "═";
    
    private String startSymbol;
    private String conectionSymbol;
    private String endSymbol;
    public PrintTableBorder(String startSymbol, String borderSymbol, String endSymbol) {
        this.startSymbol = startSymbol;
        this.conectionSymbol = borderSymbol;
        this.endSymbol = endSymbol;
    }

    public String print(int maxColumnSize, int columnCount) {
    	String result;
    	result = startSymbol + printColumns(maxColumnSize, columnCount) + endSymbol + "\n";
        return result;
    }

    private String printColumns(int maxColumnSize, int columnCount) {
    	String result = "";
        for (int j = 1; j < columnCount; j++) {
        	result = result + printColumn(maxColumnSize) + conectionSymbol;
        }
        result = result + printColumn(maxColumnSize);
        return result;
    }

    private String printColumn(int maxColumnSize) {
    	String result = "";
        for (int i = 0; i < maxColumnSize; i++) {
        	result = result + ROW_DELIMITER;
        }
        return result;
    }

}
