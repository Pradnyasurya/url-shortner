package com.surya.urlshortener.domain.repositories;

import com.surya.urlshortener.domain.entities.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {

    List<ShortUrl> findByIsPrivateIsFalseOrderByCreatedAtDesc();
}
