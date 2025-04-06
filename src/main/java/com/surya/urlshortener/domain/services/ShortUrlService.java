package com.surya.urlshortener.domain.services;

import com.surya.urlshortener.domain.entities.ShortUrl;
import com.surya.urlshortener.domain.repositories.ShortUrlRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShortUrlService {

    private final ShortUrlRepository shortUrlRepository;

    public ShortUrlService(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }

    public List<ShortUrl> getAllPublicShortUrls() {
        return shortUrlRepository.findByIsPrivateIsFalseOrderByCreatedAtDesc();
    }

}
