package com.aman.UrlShortner.repository;

import com.aman.UrlShortner.entity.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface UrlRepository extends JpaRepository<UrlEntity, Integer> {


    @Query(value = "SELECT original_url FROM url_entity WHERE short_url = ?1", nativeQuery = true)
    String findByShortUrl(String id);


    @Query("SELECT ue FROM UrlEntity ue WHERE ue.createdOn < :expirationTime AND ue.status = :status")
    List<UrlEntity> findByCreatedOnBeforeAndStatus(@Param("expirationTime") LocalDateTime expirationTime, @Param("status") String status);



    @Query("SELECT ue FROM UrlEntity ue WHERE ue.shortUrl = :url")
    List<UrlEntity> findByShortUrll(@Param("url") String url);

    UrlEntity findByOriginalUrl(String url);
}

