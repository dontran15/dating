package com.nighthawk.spring_portfolio.database.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.Scanner;

public class Chat {

    private static String secret = System.getenv("SECRET");

    public static String chatGPTTest(String text) throws MalformedURLException, IOException {
        String url = "https://api.openai.com/v1/completions";
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + KeyFileReader.getKey());

        JSONObject data = new JSONObject();
        data.put("model", "text-davinci-003");
        data.put("prompt", text);
        data.put("max_tokens", 4000);
        data.put("temperature", 1.0);

        con.setDoOutput(true);
        con.getOutputStream().write(data.toString().getBytes());

        String output = new BufferedReader(new InputStreamReader(con.getInputStream())).lines()
                .reduce((a, b) -> a + b).get();

        return (new JSONObject(output).getJSONArray("choices").getJSONObject(0).getString("text"));
    }

    public static String chatGPTRizz(String text) throws MalformedURLException, IOException {
        return "";
    }

    public String chatGPTPickUpLines(String text) throws MalformedURLException, IOException {
        String url = "https://api.openai.com/v1/completions";
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + secret);

        JSONObject data = new JSONObject();
        data.put("model", "text-davinci-003");
        data.put("prompt", "Please generate one pickup line related to the topic of " + text + ".");
        data.put("max_tokens", 4000);
        data.put("temperature", 1.0);

        con.setDoOutput(true);
        con.getOutputStream().write(data.toString().getBytes());

        String output = new BufferedReader(new InputStreamReader(con.getInputStream())).lines()
                .reduce((a, b) -> a + b).get();

        return new JSONObject(output).getJSONArray("choices").getJSONObject(0).getString("text");
    }

    public ArrayList<String> generateMultiple(int responses, String prompt) throws MalformedURLException, IOException {
        ArrayList<String> response = new ArrayList<String>();
        String topic = prompt;

        if (prompt == null || prompt.equals("")) {
            topic = "anything";
        }

        for (int i = 0; i < responses; i++) {
            response.add(chatGPTPickUpLines(topic));
        }
        return response;
    }

    public static void main(String[] args) throws Exception {
        // NOTE: you need to create a file called key.txt that has key (get from me or
        // Bailey) AND MAKE SURE it's in .gitignore or bailey will be very angry
        chatGPTTest("Generate a list of pickup lines.");
    }
}