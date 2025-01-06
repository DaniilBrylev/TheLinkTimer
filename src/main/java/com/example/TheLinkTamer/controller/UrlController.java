package com.example.TheLinkTamer.controller;

import com.example.TheLinkTamer.model1.User;
import com.example.TheLinkTamer.service.UrlService;
import com.example.TheLinkTamer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/api/urls")
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
    public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String shortUrl) {
        return urlService.getOriginalUrl(shortUrl)
                .map(originalUrl -> ResponseEntity.status(302).location(URI.create(originalUrl)).<Void>build())
                .orElse(ResponseEntity.notFound().build());
    }

}
