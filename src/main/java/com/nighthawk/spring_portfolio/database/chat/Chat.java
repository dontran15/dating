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

    private static String secret = System.getenv("secret");

    public static String chatGPTTest(String text, String key) throws MalformedURLException, IOException {
        String url = "https://api.openai.com/v1/completions";
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + key);

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

    public String chatGPTPickUpLines(String text) throws MalformedURLException, IOException {
        String url = "https://api.openai.com/v1/engines/davinci-codex/completions";
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

    /*
     * ignore might use for general chat w/ chatgpt (use double prompt to check if
     * love prompt or not)
     */
    // public String chatGPTGeneral(String text, String key) throws
    // MalformedURLException, IOException {
    // String url = "https://api.openai.com/v1/engines/davinci-codex/completions";
    // HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

    // con.setRequestMethod("POST");
    // con.setRequestProperty("Content-Type", "application/json");
    // con.setRequestProperty("Authorization", "Bearer " + key);

    // JSONObject data = new JSONObject();
    // data.put("model", "text-davinci-003");
    // data.put("prompt", text);
    // data.put("max_tokens", 4000);
    // data.put("temperature", 1.0);

    // con.setDoOutput(true);
    // con.getOutputStream().write(data.toString().getBytes());

    // String output = new BufferedReader(new
    // InputStreamReader(con.getInputStream())).lines()
    // .reduce((a, b) -> a + b).get();

    // return new
    // JSONObject(output).getJSONArray("choices").getJSONObject(0).getString("text");
    // }

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
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a prompt: ");
        String key = sc.nextLine();
        sc.close();
        chatGPTTest("Generate a list of pickup lines.", key);
    }
}