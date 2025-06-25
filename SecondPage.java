import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import service.CartManager;
import java.io.FileWriter;
import java.io.PrintWriter;
import service.OrderFileManager;//SUMMARY
import service.MenuFileManager;//MENU
import java.io.File;
import java.io.IOException;
import java.util.Scanner;



public class SecondPage extends JFrame implements ActionListener {
    //LABEL
    JLabel welcomeLabel, infoLabel, cartLabel, totalLabel;
	//JTABLE
    JTable cartTable;
	//COMBO BOX [WITHOUT STRING COMPILER GIVES NOTES]
    JComboBox<String> itemComboBox;
	//BUTTON
    JButton addToCartButton, removeButton, updateButton, placeOrderButton, backButton;
	//DEFAULT TABLE
    DefaultTableModel tableModel;
	//SUMMARY
    JTextArea orderDisplayArea;
    JLabel orderSummaryLabel;
    //MENU
    JTextArea menuDisplayArea;
    JLabel menuLabel;
	
    String name, number, address;
    CartManager cartManager = new CartManager();

    public SecondPage(String name, String number, String address) {
        this.name = name;
        this.number = number;
        this.address = address;

        setTitle("Online Food Ordering - Buyer");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(120, 210, 255));
        ImageIcon icon = new ImageIcon("icon.jpg");
        setIconImage(icon.getImage());

        welcomeLabel = new JLabel("Welcome, " + name + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 22));
        welcomeLabel.setBounds(30, 20, 400, 30);
        add(welcomeLabel);

        infoLabel = new JLabel("Phone: " + number + " | Address: " + address);
        infoLabel.setBounds(30, 60, 600, 25);
        add(infoLabel);

        cartLabel = new JLabel("Your Cart");
        cartLabel.setFont(new Font("Arial", Font.BOLD, 16));
        cartLabel.setBounds(30, 100, 150, 25);
        add(cartLabel);
		
        //I USED DEFAULTTABLE TO GET SOME IN BUIT FEATURE BY DEFAULT
        //ONE DIMENTIONAL COLUMN

        String[] columns = {"Item", "Price", "Category"};
        tableModel = new DefaultTableModel(columns, 0);
        cartTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(cartTable);
        scrollPane.setBounds(30, 130, 700, 350);
        add(scrollPane);

        itemComboBox = new JComboBox<>();
        itemComboBox.setBounds(30, 500, 300, 25);
        add(itemComboBox);
        loadItems();
        //INSERT OR ADD TO CART
        addToCartButton = new JButton("Add to Cart");
        addToCartButton.setBounds(350, 500, 120, 25);
        addToCartButton.addActionListener(this);
        add(addToCartButton);
        //REMOVE
        removeButton = new JButton("Remove");
        removeButton.setBounds(480, 500, 100, 25);
        removeButton.addActionListener(this);
        add(removeButton);
        //UPDATE
        updateButton = new JButton("Update");
        updateButton.setBounds(590, 500, 100, 25);
        updateButton.addActionListener(this);
        add(updateButton);
        //TOTAL 
        totalLabel = new JLabel("Total: $0.0");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalLabel.setBounds(30, 550, 200, 30);
        add(totalLabel);
        //ORDER BUTTON
        placeOrderButton = new JButton("Place Order");
        placeOrderButton.setBounds(250, 550, 150, 30);
        placeOrderButton.addActionListener(this);
        add(placeOrderButton);
        // ORDER SUMMARY LABEL
        orderSummaryLabel = new JLabel("Order Summary:");
        orderSummaryLabel.setFont(new Font("Arial", Font.BOLD, 14));
        orderSummaryLabel.setBounds(750, 370, 200, 25);  
        add(orderSummaryLabel);

        // ORDER DISPLAY AREA inside JScrollPane
        orderDisplayArea = new JTextArea(); 
        orderDisplayArea.setEditable(false);
        orderDisplayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane orderScrollPane = new JScrollPane(orderDisplayArea);
        orderScrollPane.setBounds(750, 400, 220, 180);  // Set size of scroll area
        add(orderScrollPane);

		
		
      // MENU LABEL
      menuLabel = new JLabel("Menu:");
      menuLabel.setFont(new Font("Arial", Font.BOLD, 14));
      menuLabel.setBounds(750, 20, 200, 25);
      add(menuLabel);

      // MENU DISPLAY AREA inside JScrollPane
      menuDisplayArea = new JTextArea();
      menuDisplayArea.setEditable(false);
      menuDisplayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
      JScrollPane menuScrollPane = new JScrollPane(menuDisplayArea);
      menuScrollPane.setBounds(750, 50, 220, 300);  // Set size of scroll area
      add(menuScrollPane);


       // Load menu text from file using the external class
       String menuText = MenuFileManager.loadMenuFromFile("menu.txt");
       menuDisplayArea.setText(menuText);

       //BACK BUTTON
       backButton = new JButton("Back");
       backButton.setBounds(850, 620, 100, 30);
       backButton.addActionListener(new ActionListener() {
      
	    public void actionPerformed(ActionEvent e) {
        new Basepage().setVisible(true);
        setVisible(false); // dispose();
		}});
		add(backButton);
		
       setVisible(true);
    }

    
//LOADITEMS METHOD WHICH WILL TAKE ITEMS FROM MENU.TXT
  
		private void loadItems() {
			
    try (Scanner scanner = new Scanner(new File("menu.txt"))) 
	{
        while (scanner.hasNextLine()) {
			
            String line = scanner.nextLine();
			
            String[] parts = line.split(",");
			
            if (parts.length == 2) {
				
                String itemName = parts[0].trim();
				
                String price = parts[1].trim();
				
                itemComboBox.addItem(itemName + " - " + price);
            }
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Could not load items: " + e.getMessage());
    }
}

		
    
   
      //To show Items Category this method will be USED

    private String getItemCategory(String name) {
		
        String lower = name.toLowerCase();//ANY WAY it's typed it will become lowerrrrrrr
		
        if (lower.contains("burger") || lower.contains("fries") || lower.contains("hot dog")) return "SNACK";
		
        if (lower.contains("ice") || lower.contains("cake") || lower.contains("brownie")) return "DESERT";
		
        return "DRINK";
    }

    public void actionPerformed(ActionEvent e) {
        try {
            String selectedItem = (String) itemComboBox.getSelectedItem();
			
            if (e.getSource() == addToCartButton) {
				
                if (selectedItem == null) throw new Exception("Choose an item first.");
				
                String[] parts = selectedItem.split(" - ");//IT WILL MAKE THE FOOD NAME AND PRICE SPLIT IN TWO DIFFFERENT STRING
				
                cartManager.addItem(tableModel, parts[0].trim(), Double.parseDouble(parts[1]), getItemCategory(parts[0]));
				
            } else if (e.getSource() == removeButton) 
			{
			   int row = cartTable.getSelectedRow();
			   
                if (row >= 0) cartManager.removeItem(tableModel, row);
            }
			else if (e.getSource() == updateButton) {
				
                int row = cartTable.getSelectedRow();
				
                if (row >= 0 && selectedItem != null)
					{
                    String[] parts = selectedItem.split(" - ");
					
                    cartManager.updateItem(tableModel, row, parts[0].trim(), Double.parseDouble(parts[1]), getItemCategory(parts[0]));
                }
            }
			else if (e.getSource() == placeOrderButton)
				{
                if (tableModel.getRowCount() == 0)
                    JOptionPane.showMessageDialog(this, "Cart is empty!");
                else { 
				String summary = OrderFileManager.saveOrderToFile(name, number, address, tableModel, cartManager.getTotal());
                orderDisplayArea.setText(summary); //WILL SHOW SUMMARY


                    JOptionPane.showMessageDialog(this, "Order Placed. Total: $" + cartManager.getTotal());
                  
				  new Basepage().setVisible(true);
                 
				 setVisible(false);//Dispose();
                }
            }
            totalLabel.setText("Total: $" + cartManager.getTotal());
        }
		catch (Exception ex) {
			
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}