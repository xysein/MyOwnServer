package com.example.MyWebServer.controllers;

import com.example.MyWebServer.entities.Role;
import com.example.MyWebServer.entities.User;
import com.example.MyWebServer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String getRegistrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerNewUser(@RequestParam String userName, @RequestParam String password, Model model) {

        if (userRepository.findByName(userName) != null) {
            model.addAttribute("message", "User already exists!");
            return "registration";
        }

        userRepository.save(new User(userName, password, true, Collections.singleton(Role.USER)));

        return "login";
    }
}
