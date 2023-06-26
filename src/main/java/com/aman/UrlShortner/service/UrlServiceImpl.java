package com.aman.UrlShortner.service;

import com.aman.UrlShortner.dto.UrlDTO;
import com.aman.UrlShortner.entity.UrlEntity;
import com.aman.UrlShortner.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.aman.UrlShortner.logic.GenerateShortUrl.getShortUrl;
import static com.aman.UrlShortner.logic.GenerateShortUrl.validateUrl;
@Service
public class UrlServiceImpl implements UrlService{

    @Autowired
    private UrlRepository urlRepository;


    @Override
    public String getOriginalUrl(String id) {
        return urlRepository.findByShortUrl(id);
    }

    @Override
    public UrlDTO generateShortUrl(String url) {
        if (!validateUrl(url)) {
            System.out.println("Your URL is not valid");
            return null;
        }

        UrlEntity existingUrlEntity = urlRepository.findByOriginalUrl(url);
        System.out.println("existingUrlEntity..." + existingUrlEntity);
        if (existingUrlEntity != null && !"invalid".equals(existingUrlEntity.getStatus())) {

            return new UrlDTO("Duplicate URL", existingUrlEntity.getShortUrl(), existingUrlEntity.getOriginalUrl(), existingUrlEntity.getCreatedOn(), existingUrlEntity.getStatus());
//            return null;
        }

        UrlEntity urlObj = new UrlEntity();
        urlObj.setOriginalUrl(url);
        urlObj.setShortUrl(getShortUrl(url));
        urlObj.setCreatedOn(LocalDateTime.now());
        urlObj.setStatus("valid");

        UrlEntity savedUrlEntity = urlRepository.save(urlObj);

        return new UrlDTO(savedUrlEntity);
    }


    @Override
    public UrlEntity getValidUrl(String url) {
        List<UrlEntity> urlEntities = urlRepository.findByShortUrll(url);
        if (!urlEntities.isEmpty()) {
            return urlEntities.get(0);
        }
        return null;
    }

}
