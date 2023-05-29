package com.nighthawk.spring_portfolio.database.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PythonFileReader {
    public static String pythonReader() {
        try {
            // Create the ProcessBuilder and specify the command
            ProcessBuilder pb = new ProcessBuilder("python3",
                    "src/main/java/com/nighthawk/spring_portfolio/database/chat/Chat.py");

            // Redirect the output of the Python process
            pb.redirectErrorStream(true);

            // Start the process
            Process process = pb.start();

            // Get the InputStream of the process's output
            InputStream inputStream = process.getInputStream();

            // Read the output
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuilder output = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // Wait for the process to complete
            int exitCode = process.waitFor();

            // Print the output
            System.out.println("Exit Code: " + exitCode);
            System.out.println("Output:\n" + output.toString());

            return output.toString();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println(pythonReader());
    }
}