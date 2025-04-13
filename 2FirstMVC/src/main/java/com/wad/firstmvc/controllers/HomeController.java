package com.wad.firstmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    // Maps the root ("/") to "index.html"
    @GetMapping
    public String home() {
        return "index";
    }
}
