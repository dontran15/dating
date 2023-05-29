package com.nighthawk.spring_portfolio.database.chat;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
        String model = "gpt"; // change to configure if response is to davinci (generic) or gpt (sentiment)
        String response = Chat.chatGPTRizz(prompt, model);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/queryTest")
    public ResponseEntity<Object> queryTest(@RequestBody final Map<String, Object> map)
            throws MalformedURLException, IOException {

        String prompt = (String) map.get("prompt");
        String response = ChatPyReader.pythonReader(prompt);

        System.out.println(response);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
