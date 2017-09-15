package com.dihri.web.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Get chat page
 */
@Controller
public class ChatController {
    public static final String URL_CHAT="/chat";

    @GetMapping(value = URL_CHAT)
    public String getChat() {
        return "chat";
    }
}
