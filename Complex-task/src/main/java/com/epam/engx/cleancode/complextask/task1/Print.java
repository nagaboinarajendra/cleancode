package com.epam.engx.cleancode.complextask.task1;


import com.epam.engx.cleancode.complextask.task1.thirdpartyjar.Command;
import com.epam.engx.cleancode.complextask.task1.thirdpartyjar.DataSet;
import com.epam.engx.cleancode.complextask.task1.thirdpartyjar.DatabaseManager;
import com.epam.engx.cleancode.complextask.task1.thirdpartyjar.View;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.singletonList;


public class Print implements Command {

    private static final String COLUMN_DELIMITER = "║";

    private final PrintTableBorder printFirstLine = new PrintTableBorder("╔", "╦", "╗");
    private final PrintTableBorder printMiddleLines = new PrintTableBorder("╠", "╬", "╣");
    private final PrintTableBorder printLastLine = new PrintTableBorder("╚", "╩", "╝");
    private final PrintTableBorder printFirstLineForEmptyTable = new PrintTableBorder("╔", "═", "╗");
    private final PrintTableBorder printLastLineForEmptyTable = new PrintTableBorder("╚", "═", "╝");

    private View view;
    private DatabaseManager manager;
    private String tableName;

    public Print(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }

    public boolean canProcess(String command) {
        return command.startsWith("print ");
    }

    public void process(String input) {
        String[] command = input.split(" ");
        if (command.length != 2) {
            throw new IllegalArgumentException("incorrect number of parameters.");
        }
        tableName = command[1];
        view.write(getTableString(manager.getTableData(tableName)));
    }

    private String getStringTableData(List<DataSet> dataSets) {
        int maxColumnSize = getMaxColumnSize(dataSets);
        int columnCount = getColumnCount(dataSets);
        String result = "";
        int row = 0;
        for (DataSet item : dataSets) {
            result = result + printRow(item.getValues(), maxColumnSize);
            if (row < dataSets.size() - 1) {
                result = result + printMiddleLines.print(maxColumnSize, columnCount);
            }
            row++;
        }
        result = result + printLastLine.print(maxColumnSize, columnCount);
        return result;
    }

    private <T> String printRow(List<T> values, int maxColumnSize) {
        String result = COLUMN_DELIMITER;
        for (T item : values) {
            int valuesLength = String.valueOf(item).length();
            int freeSpace = maxColumnSize - valuesLength;
            result = result + getSpaces(freeSpace) + String.valueOf(item)
                    + getSpaces(freeSpace + ((valuesLength % 2 == 0) ? 0 : 1)) + COLUMN_DELIMITER;
            
        }
        result += "\n";
        return result.toString();
    }

    private String getSpaces(int spaceCount) {
        String result = "";
        for (int j = 0; j < spaceCount / 2; j++) {
            result += " ";
        }
        return result;
    }

    private int getColumnCount(List<DataSet> dataSets) {
        return dataSets.get(0).getColumnNames().size();
    }

    private String getHeaderOfTheTable(List<DataSet> dataSets) {
        int columnCount = getColumnCount(dataSets);
        int maxColumnSize = getMaxColumnSize(dataSets);
        return printFirstLine.print(maxColumnSize, columnCount) +
                printRow(dataSets.get(0).getColumnNames(), maxColumnSize) +
                printMiddleLines.print(maxColumnSize, columnCount);
    }

    private String getTableString(List<DataSet> data) {
        if (getMaxColumnDataSize(data) == 0) {
            return getEmptyTable(tableName);
        } else {
            return getHeaderOfTheTable(data) + getStringTableData(data);
        }

    }

    private String getEmptyTable(final String tableName) {
        String emptyTableText = "║ Table '" + tableName + "' is empty or does not exist ║";
       return  printFirstLineForEmptyTable.print(emptyTableText.length() - 2, 1) +
                        emptyTableText + "\n" +
                        printLastLineForEmptyTable.print(emptyTableText.length() - 2, 1) ;

    }

    private int getMaxColumnDataSize(List<DataSet> dataSets) {
        int maxLength = 0;
        if (dataSets.size() > 0) {
            List<String> columnNames = dataSets.get(0).getColumnNames();
            maxLength = Math.max(getMaxLength(getAllValues(dataSets)), getMaxLength(columnNames));
        }
        return maxLength;
    }

    private int getMaxLength(List values) {
        int maxLength = 0;
        for (Object value : values) {
            maxLength = Math.max(String.valueOf(value).length(), maxLength);
        }
        return maxLength;
    }

    private List<Object> getAllValues(List<DataSet> dataSets) {
        List<Object> values = new ArrayList<>();
        for (DataSet dataSet : dataSets) {
            values.addAll(dataSet.getValues());
        }
        return values;
    }

    private int getMaxColumnSize(List<DataSet> dataSets) {
        return getAdjustedMax(getMaxColumnDataSize(dataSets));

    }

    private int getAdjustedMax(int maxColumnSize) {
        if (maxColumnSize % 2 == 0) {
            return maxColumnSize + 2;
        } else {
            return maxColumnSize + 3;
        }
    }

}
