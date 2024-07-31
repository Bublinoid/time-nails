package ru.bublinoid.timenails.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ru.bublinoid.timenails.service.WhatsAppService;

import java.util.Map;

@RestController
@RequestMapping("/api/whatsapp")
public class WhatsAppController {

    @Autowired
    private WhatsAppService whatsAppService;

    @PostMapping("/send-template")
    public String sendWhatsAppTemplateMessage(@RequestBody Map<String, String> request) {
        String recipientPhoneNumber = request.get("to");
        String templateName = request.get("template");
        String languageCode = request.get("language");

        return whatsAppService.sendTemplateMessage(recipientPhoneNumber, templateName, languageCode);
    }
}
