package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class HtmlController {

    private final TemplateEngine templateEngine;

    public HtmlController(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/convertToHtml")
    public ResponseEntity<String> convertToHtml(@RequestBody String content) {
        Context context = new Context();
        context.setVariable("content", content);

        String htmlContent = templateEngine.process("template", context);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.html"))) {
            writer.write(htmlContent);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to save HTML file", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("HTML file saved successfully", HttpStatus.OK);
    }
}
