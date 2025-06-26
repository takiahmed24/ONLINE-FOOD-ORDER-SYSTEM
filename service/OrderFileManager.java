package service;

import javax.swing.table.DefaultTableModel;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class OrderFileManager {
public static String saveOrderToFile(String name, String number, String address, DefaultTableModel tableModel, double total) {
    String orderSummary = "=== New Order ===\n" +
                          "Name: " + name + "\n" +
                          "Phone: " + number + "\n" +
                          "Address: " + address + "\n" +
                          "Items:\n";

    for (int i = 0; i < tableModel.getRowCount(); i++) {
        String item = tableModel.getValueAt(i, 0).toString();
        String price = tableModel.getValueAt(i, 1).toString();
        String category = tableModel.getValueAt(i, 2).toString();

        orderSummary += "- " + item + " | $" + price + " | " + category + "\n";
    }

    orderSummary += "Total: $" + total + "\n";
    orderSummary += "=======================\n\n";

    try (FileWriter writer = new FileWriter("order.txt", true);
         PrintWriter out = new PrintWriter(writer)) {
        out.print(orderSummary);
    } catch (IOException e) {
        return "Error saving order: " + e.getMessage();
    }

    return orderSummary;
}}