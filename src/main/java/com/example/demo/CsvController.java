package com.example.demo;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CsvController {

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/download-csv")
    public ResponseEntity<byte[]> downloadCSV(
            @RequestParam("selectedMonth") String selectedMonth,
            @RequestParam("selectedFile") String selectedFile) throws IOException {

        // Process the selected month and file as needed
        System.out.println("Selected Month: " + selectedMonth);
        System.out.println("Selected File: " + selectedFile);

        // Dummy data for CSV generation
        List<String[]> data = Arrays.asList(
                new String[] { "Name", "Age", "City" },
                new String[] { "Alice", "30", "New York" },
                new String[] { "Bob", "25", "Los Angeles" },
                new String[] { "Charlie", "35", "Chicago" });

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (String[] row : data) {
            String line = Arrays.stream(row)
                    .collect(Collectors.joining(",")) + "\n";
            byteArrayOutputStream.write(line.getBytes(StandardCharsets.UTF_8));
        }

        byte[] bytes = byteArrayOutputStream.toByteArray();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=data.csv");

        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }
}
