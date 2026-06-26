package com.doumo.app.controller;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String home() {
        return "Spring Boot Francisco Rios Castillo Api";
    }
}