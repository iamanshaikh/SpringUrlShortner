package com.aman.UrlShortner.scheduler;

import com.aman.UrlShortner.entity.UrlEntity;
import com.aman.UrlShortner.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class UrlValidity {

    private static final int TIME = 5;
    private static final String INVALID = "invalid";
    private static final String VALID = "valid";


    @Autowired
    private UrlRepository urlRepository;


    @Scheduled(fixedRate = 60000)
    public void cleanupExpiredUrls() {
        LocalDateTime expirationTime = LocalDateTime.now().minus(TIME, ChronoUnit.MINUTES);
        List<UrlEntity> expiredUrls = urlRepository.findByCreatedOnBeforeAndStatus(expirationTime,VALID);
        System.out.println("Working................" + expiredUrls);
        for (UrlEntity expiredUrl : expiredUrls) {
            System.out.println("Working");
            LocalDateTime currentTime = LocalDateTime.now();
            LocalDateTime urlExpiryTime = expiredUrl.getCreatedOn().plusMinutes(TIME);
            System.out.println("Working......urlExpiryTime.........." + urlExpiryTime);
            if (currentTime.isAfter(urlExpiryTime)) {
                expiredUrl.setStatus(INVALID);
                urlRepository.save(expiredUrl);
            }
        }
    }
}
