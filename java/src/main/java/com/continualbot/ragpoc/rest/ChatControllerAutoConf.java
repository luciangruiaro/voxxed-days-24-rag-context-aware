package com.continualbot.ragpoc.rest;

import com.continualbot.ragpoc.helper.MessageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ChatControllerAutoConf {

    private static final Logger logger = LoggerFactory.getLogger(MessageHelper.class);
    private final OpenAiChatClient chatClient;

    @Autowired
    public ChatControllerAutoConf(OpenAiChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/rag/prompt")
    public Map<String, String> generate(@RequestParam(value = "inputMessage", defaultValue = "Tell me something about Romania.") String inputMessage) {
        logger.info("inputMessage: {}", inputMessage);
        return Map.of("generation", chatClient.call(inputMessage));
    }

}