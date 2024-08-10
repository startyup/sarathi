package com.startyup.sarathi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
public class AtlassianConnectController {

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping("/atlassian-connect.json")
    public ResponseEntity<String> getDescriptor() {
        try {
            Resource resource = resourceLoader.getResource("classpath:static/atlassian-connect.json");
            byte[] jsonBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
            String jsonContent = new String(jsonBytes, StandardCharsets.UTF_8);
            return new ResponseEntity<>(jsonContent, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to load JSON file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/hello-world")
    public ModelAndView helloWorld() {
        return new ModelAndView("hello-world");
    }

    @PostMapping("/installed")
    public ResponseEntity<String> handleInstalled(@RequestBody String body) {
        // Handle the installed lifecycle event
        return ResponseEntity.ok("App installed");
    }

    @PostMapping("/uninstalled")
    public ResponseEntity<String> handleUninstalled(@RequestBody String body) {
        // Handle the uninstalled lifecycle event
        return ResponseEntity.ok("App uninstalled");
    }
}
