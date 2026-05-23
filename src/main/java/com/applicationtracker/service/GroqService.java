package com.applicationtracker.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Service
public class GroqService {

    @Value("${groq.api.key}")
    private String apiKey;

    private final String API_URL = "https://api.groq.com/openai/v1/chat/completions";

    public String askAI(String prompt){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.setBearerAuth(apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        String body =  """
        {
            "model":"llama-3.3-70b-versatile",
            "messages":[
            {
            "role":"user",
            "content":"%s"
            }
            ]
        }
        """.formatted(prompt);
        HttpEntity<String> entity =
                new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(
                API_URL,entity
                ,String.class
        );

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());

            JsonNode contentNode = root
                    .path("choices")
                    .get(0)
                    .get("message")
                    .get("content");

            return contentNode.asText();

        }catch (Exception e){
            return "Error parsing AI response";
        }


    }



}
