package com.example.TheLinkTamer.model1;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String originalUrl;

    @Column(nullable = false, unique = true)
    private String shortUrl;

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private int visitLimit;

    @Column(nullable = false)
    private int visitCount;

    @Column(nullable = false)
    private LocalDateTime expiryDate;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getOriginalUrl() { return originalUrl; }
    public void setOriginalUrl(String originalUrl) { this.originalUrl = originalUrl; }
    public String getShortUrl() { return shortUrl; }
    public void setShortUrl(String shortUrl) { this.shortUrl = shortUrl; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public int getVisitLimit() { return visitLimit; }
    public void setVisitLimit(int visitLimit) { this.visitLimit = visitLimit; }
    public int getVisitCount() { return visitCount; }
    public void setVisitCount(int visitCount) { this.visitCount = visitCount; }
    public LocalDateTime getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDateTime expiryDate) { this.expiryDate = expiryDate; }
}
