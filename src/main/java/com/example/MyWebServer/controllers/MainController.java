package com.example.MyWebServer.controllers;

import com.example.MyWebServer.entities.Message;
import com.example.MyWebServer.entities.User;
import com.example.MyWebServer.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/")
    public String greeting(Model model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Model model) {
        model.addAttribute("messages", messageRepository.findAll());
        return "main";
    }

    @PostMapping("/main")
    public String addMessage(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag, Model model) {

        messageRepository.save(new Message(text, tag, user));

        model.addAttribute("messages", messageRepository.findAll());

        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Model model) {
        Iterable<Message> messageList;

        if (filter == null || filter.trim().isEmpty()) {
            messageList = messageRepository.findAll();
        } else {
            messageList = messageRepository.findByTag(filter);
        }

        model.addAttribute("messages", messageList);

        return "main";
    }
}