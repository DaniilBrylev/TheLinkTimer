package com.example.TheLinkTamer.service;

import com.example.TheLinkTamer.model1.Url;
import com.example.TheLinkTamer.model1.User;
import com.example.TheLinkTamer.repository1.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UrlService {
    private final UrlRepository urlRepository;

    @Autowired
    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String shortenUrl(String originalUrl, User user, int visitLimit, int hoursToLive) {
        String shortUrl = UUID.randomUUID().toString().substring(0, 8);

        Url url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setShortUrl(shortUrl);
        url.setUser(user);
        url.setVisitLimit(visitLimit);
        url.setVisitCount(0);
        url.setExpiryDate(LocalDateTime.now().plusHours(hoursToLive));

        urlRepository.save(url);
        return shortUrl;
    }

    public Optional<String> getOriginalUrl(String shortUrl) {
        Optional<Url> urlOpt = urlRepository.findByShortUrl(shortUrl);

        if (urlOpt.isPresent()) {
            Url url = urlOpt.get();
            if (url.getVisitCount() >= url.getVisitLimit() || url.getExpiryDate().isBefore(LocalDateTime.now())) {
                return Optional.empty();
            }
            url.setVisitCount(url.getVisitCount() + 1);
            urlRepository.save(url);
            return Optional.of(url.getOriginalUrl());
        }
        return Optional.empty();
    }

    @Scheduled(fixedRate = 3600000) // Every hour
    public void deleteExpiredUrls() {
        urlRepository.findAll().forEach(url -> {
            if (url.getExpiryDate().isBefore(LocalDateTime.now())) {
                urlRepository.delete(url);
            }
        });
    }
}
