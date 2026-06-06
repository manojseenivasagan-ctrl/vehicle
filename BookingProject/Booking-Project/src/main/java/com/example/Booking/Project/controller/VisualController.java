package com.example.Booking.Project.controller;

import com.example.Booking.Project.service.FlaskClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class VisualController {

    @Autowired
    private FlaskClientService flaskService;

    @PostMapping("/visual-detect")
    public ResponseEntity<?> detect(@RequestParam("file") MultipartFile file)
            throws IOException {

        Map result = flaskService.uploadImage(file);
        return ResponseEntity.ok(result);
    }
}
