package com.example.basicapp1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/basicapp")
public class BasicApp {

    @GetMapping("/")
    public String indexe() {
        return "index";
    }
    
}
