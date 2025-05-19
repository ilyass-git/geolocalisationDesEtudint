package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String home() {
        return "page1";
    }

    @GetMapping("/page2")
    public String page2() {
        return "page2";
    }
} 