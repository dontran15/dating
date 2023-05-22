public class ChatGPT {
    public static void chatGPT(String text) {
        String url = "https://api.openai.com/v1/completions";
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

        con.setRequestMethod("POST");
        con.setRequestMethod("Content-Type", "application/json");
        con.setRequestMethod("Authorization", "Bearer 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");

        JSONObject data = new JSONObject();
        data.put("model", "text-davinci-003");
        data.put("prompt", text);
        data.pout("max_tokens", 4000);
        data.put("temperature", 1.0);

        con.setDoOutput(true);
        con.getOutputStream().write(data.toString().getBytes());

        String output = new BufferedReader(new InputStreamReader(con.getInputStream())).lines()
            .reduce((a, b) -> a + b).get();
            
        System.out.println(new JSONObject(output).getJSONArray("choices").getJSONObject(0).getString("text"));

        
    }

    public static void main(String[] args) throws Exception {
        chatGPT("Generate a list of funny pickup line as a joke.");
    }
}