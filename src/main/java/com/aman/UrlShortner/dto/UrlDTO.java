package com.aman.UrlShortner.dto;

import com.aman.UrlShortner.entity.UrlEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlDTO {
    private String id;
    private String shortUrl;
    private String originalUrl;
    private LocalDateTime createdOn;
    private String status;


    public UrlDTO(UrlEntity  url) {
        this.id = String.valueOf(url.getId());
        this.shortUrl = url.getShortUrl();
        this.originalUrl = url.getOriginalUrl();
        this.createdOn = url.getCreatedOn();
        this.status = url.getStatus();

    }
}
