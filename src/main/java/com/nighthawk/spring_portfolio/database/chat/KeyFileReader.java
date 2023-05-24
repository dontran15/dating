package com.nighthawk.spring_portfolio.database.chat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class KeyFileReader {
    public static String getKey() {
        String filePath = "src/main/java/com/nighthawk/spring_portfolio/database/chat/key.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Process first line in the file (key)
                return line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "error";
    }

    // public static void main(String[] args) {
    // String filePath =
    // "src/main/java/com/nighthawk/spring_portfolio/database/chat/key.txt";
    // try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
    // String line;
    // while ((line = br.readLine()) != null) {
    // // Process each line in the file
    // System.out.println(line);
    // }
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // }
}