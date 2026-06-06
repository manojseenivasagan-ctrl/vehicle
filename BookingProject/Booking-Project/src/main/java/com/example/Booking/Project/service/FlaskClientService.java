package com.example.Booking.Project.service;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class FlaskClientService {

    private final RestTemplate restTemplate = new RestTemplate();

    public Map uploadImage(MultipartFile file) throws IOException {

        String flaskUrl = "http://localhost:5000/predict";

        // Body
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        });

        // Headers (SPRING HttpHeaders)
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // Request
        HttpEntity<MultiValueMap<String, Object>> request =
                new HttpEntity<>(body, headers);

        // Call Flask
        return restTemplate.postForObject(flaskUrl, request, Map.class);
    }
}
