package service;

import javax.swing.table.DefaultTableModel;

public class CartManager {
    private double total = 0.0;

    public void addItem(DefaultTableModel model, String name, double price, String category) {
        model.addRow(new Object[]{name, price, category});//FOR ADDING ROW
        total += price;
    }

    public void removeItem(DefaultTableModel model, int row) {
        if (row >= 0 && row < model.getRowCount()) {
            double price = Double.parseDouble(model.getValueAt(row, 1).toString());
            total -= price;
            model.removeRow(row);
        }
    }

    public void updateItem(DefaultTableModel model, int row, String name, double newPrice, String category) {
        double oldPrice = Double.parseDouble(model.getValueAt(row, 1).toString());
        total = total - oldPrice + newPrice;

        model.setValueAt(name, row, 0);
        model.setValueAt(newPrice, row, 1);
        model.setValueAt(category, row, 2);
    }

    public double getTotal() {
        return total;
    }
}
