package com.example.saolab.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @PostMapping("/answers")
    public String test(@RequestParam int a1, @RequestParam int a2, @RequestParam int a3, @RequestParam int a4, @RequestParam int a5,
                       @RequestParam int a6, @RequestParam int a7, @RequestParam int a8, @RequestParam int a9, @RequestParam int a10, Model model) {
        int y = a1 + a2 + a3 + a4 + a5 + a6 + a7 + a8 + a9 + a10;
        int n = 10 - y;
        model.addAttribute("y", y);
        model.addAttribute("n", n);
        return "answers";
    }
}
