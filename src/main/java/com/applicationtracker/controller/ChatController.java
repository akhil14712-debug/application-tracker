package com.applicationtracker.controller;


import com.applicationtracker.dto.ChatRequet;
import com.applicationtracker.service.GroqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private GroqService groqService;

    @PostMapping
    public String chat(@RequestBody ChatRequet chatRequet){
        return groqService.askAI(chatRequet.getPrompt());
    }
}
