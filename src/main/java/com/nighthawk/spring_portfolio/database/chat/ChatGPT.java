package com.nighthawk.spring_portfolio.database.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ChatGPT {
    public static void main(String[] args) throws IOException {
        String apiKey = "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";
        String prompt = "What is the meaning of life?";

        String apiUrl = "https://api.openai.com/v1/chat/completions";

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Bearer " + apiKey);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        String body = "{\"prompt\": \"" + prompt + "\"}";

        try (OutputStream outputStream = connection.getOutputStream()) {
            byte[] input = body.getBytes("utf-8");
            outputStream.write(input, 0, input.length);
        }

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                System.out.println(response.toString());
                // Process the response as per your requirements
            }
        } else {
            System.out.println("Request failed with code: " + responseCode);
        }

        connection.disconnect();
    }
}