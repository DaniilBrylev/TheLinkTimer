package com.example.TheLinkTamer.controller;

import com.example.TheLinkTamer.model1.User;
import com.example.TheLinkTamer.service.UrlService;
import com.example.TheLinkTamer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UrlController {
    private final UrlService urlService;
    private final UserService userService;

    @Autowired
    public UrlController(UrlService urlService, UserService userService) {
        this.urlService = urlService;
        this.userService = userService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestBody Map<String, Object> payload) {
        String originalUrl = (String) payload.get("originalUrl");
        int visitLimit = (int) payload.get("visitLimit");
        int hoursToLive = (int) payload.get("hoursToLive");
        String userUuid = (String) payload.get("userUuid");

        User user = userService.getOrCreateUser(userUuid);
        String shortUrl = urlService.shortenUrl(originalUrl, user, visitLimit, hoursToLive);
        return ResponseEntity.ok(shortUrl);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<String> getOriginalUrl(@PathVariable String shortUrl) {
        return urlService.getOriginalUrl(shortUrl)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
