package com.karthik.table;

import org.apache.commons.lang3.StringUtils;

public class TablePrinter {

    public static void printTable(String[][] data) {
        int[] columnWidths = new int[data[0].length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                columnWidths[j] = Math.max(columnWidths[j], data[i][j].length());
            }
        }

        StringBuilder table = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                table.append(StringUtils.rightPad(data[i][j], columnWidths[j], " "));
            }
            table.append("\n");
        }

        System.out.println(table.toString());
    }

    public static void main(String[] args) {
        String[][] data = {
                {"Name", "Age", "City"},
                {"Alice", "25", "New York"},
                {"Bob", "30", "Los Angeles"},
                {"Charlie", "28", "Chicago"}
        };

        printTable(data);
    }
}