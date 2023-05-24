package com.nighthawk.spring_portfolio.database.chat;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("api/chat")
public class ChatApiController {
    private Chat chatGPT = new Chat();

    @PostMapping("/generateLines") // to do generate response based on profile
    public ResponseEntity<Object> generatePickupLines(@RequestBody final Map<String, Object> map)
            throws MalformedURLException, IOException {

        String prompt = (String) map.get("prompt");
        int responses = Integer.valueOf((String) map.get("responses"));

        ArrayList<String> response;

        if (prompt == null || prompt.equals("")) {
            response = (ArrayList<String>) (chatGPT.generateMultiple(responses, null));
        } else {
            response = (ArrayList<String>) (chatGPT.generateMultiple(responses, prompt));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // to do, do an overall api on any prompt, as long as it's related to love
    // (requires two prompts to check using gpt)
    @PostMapping("/generate")
    public ResponseEntity<Object> generateLoveAdvice(@RequestBody final Map<String, Object> map)
            throws MalformedURLException, IOException {

        String prompt = (String) map.get("prompt");
        // String secret = (String) map.get("secret");
        String secret = KeyFileReader.getKey();

        String response = Chat.chatGPTTest(prompt, secret);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
