package com.example.TheLinkTamer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/shorten")
public class LinkController {

    private final LinkService linkService;

    @Autowired
    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    // Эндпоинт для сокращения ссылки
    @PostMapping
    public String shortenLink(@RequestParam("url") String url) {
        String shortLink = linkService.shortenUrl(url);
        return "http://localhost:8080/" + shortLink;  // Вернём сокращённую ссылку
    }

    // Эндпоинт для перехода по сокращённой ссылке
    @GetMapping("/{shortLink}")
    public ResponseEntity<Void> redirectToOriginalLink(@PathVariable String shortLink) {
        String originalUrl = linkService.getOriginalUrl(shortLink);
        if (originalUrl == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(originalUrl))
                .build();
    }
}
