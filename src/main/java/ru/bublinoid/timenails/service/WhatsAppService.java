package ru.bublinoid.timenails.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class WhatsAppService {

    @Value("${whatsapp.api-url}")
    private String apiUrl;

    @Value("${whatsapp.access-token}")
    private String accessToken;

    private final RestTemplate restTemplate;

    public WhatsAppService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String sendTemplateMessage(String recipientPhoneNumber, String templateName, String languageCode) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.set("Content-Type", "application/json");

        Map<String, Object> message = new HashMap<>();
        message.put("messaging_product", "whatsapp");
        message.put("to", recipientPhoneNumber);
        message.put("type", "template");

        Map<String, Object> template = new HashMap<>();
        template.put("name", templateName);

        Map<String, String> language = new HashMap<>();
        language.put("code", languageCode);

        template.put("language", language);
        message.put("template", template);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(message, headers);

        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, request, String.class);

        return response.getBody();
    }
}
