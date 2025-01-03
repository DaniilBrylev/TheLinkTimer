package com.example.TheLinkTamer;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LinkService {

    // Словарь для хранения сокращённых ссылок
    private final Map<String, String> linkStorage = new HashMap<>();
    private int counter = 1;

    // Метод для сокращения URL
    public String shortenUrl(String url) {
        String shortLink = Integer.toString(counter++);
        linkStorage.put(shortLink, url);
        return shortLink;
    }

    // Метод для получения оригинальной ссылки
    public String getOriginalUrl(String shortLink) {
        return linkStorage.get(shortLink);
    }
}