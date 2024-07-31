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
    public String sendWhatsAppTemplateMessage(@RequestParam String to, @RequestParam String template, @RequestParam String language) {
        return whatsAppService.sendTemplateMessage(to, template, language);
    }
}
