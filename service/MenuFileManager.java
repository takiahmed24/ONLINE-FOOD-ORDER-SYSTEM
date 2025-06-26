package service;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MenuFileManager {

    public static String loadMenuFromFile(String filename) {
        String menu = ""; 
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                menu = menu + scanner.nextLine() + "\n"; 
            }
        } catch (IOException e) {
            menu = menu + "Could not load menu.\n";
        }
        return menu;
    }
}