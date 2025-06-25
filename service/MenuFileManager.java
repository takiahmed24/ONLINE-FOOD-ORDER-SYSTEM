package service;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MenuFileManager {

    public static String loadMenuFromFile(String filename) {
        StringBuilder menu = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                menu.append(scanner.nextLine()).append("\n");
            }
        } catch (IOException e) {
            menu.append("Could not load menu.\n");
        }
        return menu.toString();
    }
}
