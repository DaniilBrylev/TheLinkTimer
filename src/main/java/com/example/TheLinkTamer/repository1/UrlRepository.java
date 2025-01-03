package com.example.TheLinkTamer.repository1;

import com.example.TheLinkTamer.model1.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> findByShortUrl(String shortUrl);

    Optional<Url> findByOriginalUrl(String originalUrl);
}
