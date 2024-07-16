package com.example.demo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LinkController {

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/link")
    public String getLink() {
        return "{\"url\": \"https://trickuweb.com/excel-file.xlsx\"}";
    }
}
