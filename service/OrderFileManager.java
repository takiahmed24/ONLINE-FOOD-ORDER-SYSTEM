package service;

import javax.swing.table.DefaultTableModel;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class OrderFileManager {

    public static String saveOrderToFile(String name, String number, String address, DefaultTableModel tableModel, double total) {
        StringBuilder orderSummary = new StringBuilder();
        orderSummary.append("=== New Order ===\n");
        orderSummary.append("Name: ").append(name).append("\n");
        orderSummary.append("Phone: ").append(number).append("\n");
        orderSummary.append("Address: ").append(address).append("\n");
        orderSummary.append("Items:\n");

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String item = tableModel.getValueAt(i, 0).toString();
            String price = tableModel.getValueAt(i, 1).toString();
            String category = tableModel.getValueAt(i, 2).toString();
            orderSummary.append("- ").append(item).append(" | $").append(price).append(" | ").append(category).append("\n");
        }

        orderSummary.append("Total: $").append(total).append("\n");
        orderSummary.append("=======================\n\n");

        // Save to file
        try (FileWriter writer = new FileWriter("order.txt", true);
             PrintWriter out = new PrintWriter(writer)) {
            out.print(orderSummary.toString());
        } catch (IOException e) {
            return "Error saving order: " + e.getMessage();
        }

        return orderSummary.toString();
    }
}
