package com.nighthawk.spring_portfolio.database.chat;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

@RestController
@RequestMapping("api/chat")
public class ChatGPTApiController {

    private ChatGPT chatGPT = new ChatGPT();

    @GetMapping("/generate")
    public ResponseEntity<Object> generatePickupLines(@RequestParam String
    prompt, @RequestParam int responses) throws MalformedURLException, IOException {

        ArrayList<String> response;

        if (prompt == null || prompt.equals("")) {
            response = (ArrayList<String>) (chatGPT.generateMultiple(responses, null));
        } else {
            response = (ArrayList<String>)(chatGPT.generateMultiple(responses, prompt));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
}

}
