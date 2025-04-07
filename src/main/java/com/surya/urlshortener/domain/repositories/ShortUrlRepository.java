package com.surya.urlshortener.domain.repositories;

import com.surya.urlshortener.domain.entities.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {

//    List<ShortUrl> findByIsPrivateIsFalseOrderByCreatedAtDesc();

    @Query("SELECT s FROM ShortUrl s left join fetch s.createdBy WHERE s.isPrivate = false ORDER BY s.createdAt DESC")
    List<ShortUrl> findPublicShortUrls();

    boolean existsByShortKey(String shortKey);
}
