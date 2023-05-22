package com.nighthawk.spring_portfolio.database.chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import java.util.ArrayList;

public class ChatGPT {
    public static void chatGPTTest(String text) {
        String url = "https://api.openai.com/v1/completions";
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

        con.setRequestMethod("POST");
        con.setRequestMethod("Content-Type", "application/json");
        con.setRequestMethod("Authorization", "Bearer " + (String) env.secret);

        JSONObject data = new JSONObject();
        data.put("model", "gpt-3.5-turbo");
        data.put("prompt", text);
        data.pout("max_tokens", 4000);
        data.put("temperature", 1.0);

        con.setDoOutput(true);
        con.getOutputStream().write(data.toString().getBytes());

        String output = new BufferedReader(new InputStreamReader(con.getInputStream())).lines()
            .reduce((a, b) -> a + b).get();
            
        System.out.println(new JSONObject(output).getJSONArray("choices").getJSONObject(0).getString("text"));
    }

    public static String chatGPT(String text) {
        String url = "https://api.openai.com/v1/completions";
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

     

    public static Object generateMultiple(String prompt, int responses) {
        return null;
    }   con.setRequestMethod("POST");
        con.setRequestMethod("Content-Type", "application/json");
        con.setRequestMethod("Authorization", "Bearer " + (String) env.secret);

        JSONObject data = new JSONObject();
        data.put("model", "gpt-3.5-turbo");
        data.put("prompt", text);
        data.pout("max_tokens", 4000);
        data.put("temperature", 1.0);

        con.setDoOutput(true);
        con.getOutputStream().write(data.toString().getBytes());

        String output = new BufferedReader(new InputStreamReader(con.getInputStream())).lines()
            .reduce((a, b) -> a + b).get();
            
        return new JSONObject(output).getJSONArray("choices").getJSONObject(0).getString("text");
    }

    public static ArrayList<String> generateMultiple(String prompt, int responses) {
        ArrayList<String> responses = new ArrayList<String>();
        for (int i = 0; i < responses; i++) {
            responses.add(chatGPT(prompt));
        }
        return responses;
    }

    public static void main(String[] args) throws Exception {
        chatGPTTest("Generate a list of funny pickup line as a joke.");
    }

}