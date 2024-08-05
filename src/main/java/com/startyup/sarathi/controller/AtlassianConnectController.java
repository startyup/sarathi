package com.startyup.sarathi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AtlassianConnectController {

    @GetMapping("/atlassian-connect.json")
    public String getDescriptor() {
        return "{\n" +
                "  \"key\": \"com.startyup.sarathi\",\n" +
                "  \"name\": \"Sarathi\",\n" +
                "  \"baseUrl\": \"https://sarathi.dev\",\n" +
                "  \"authentication\": {\n" +
                "    \"type\": \"jwt\"\n" +
                "  },\n" +
                "  \"lifecycle\": {\n" +
                "    \"installed\": \"/installed\",\n" +
                "    \"uninstalled\": \"/uninstalled\"\n" +
                "  },\n" +
                "  \"scopes\": [\n" +
                "    \"READ\",\n" +
                "    \"WRITE\"\n" +
                "  ],\n" +
                "  \"modules\": {\n" +
                "    \"generalPages\": [\n" +
                "      {\n" +
                "        \"url\": \"/hello-world\",\n" +
                "        \"name\": {\n" +
                "          \"value\": \"Hello World Page\"\n" +
                "        },\n" +
                "        \"key\": \"hello-world-page\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";
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
