package com.aman.UrlShortner.service;

import com.aman.UrlShortner.dto.UrlDTO;
import com.aman.UrlShortner.entity.UrlEntity;


public interface UrlService {
    String getOriginalUrl(String id);

    UrlDTO generateShortUrl(String url);



    UrlEntity getValidUrl(String url);
}
