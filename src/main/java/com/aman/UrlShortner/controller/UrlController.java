package com.aman.UrlShortner.controller;

import com.aman.UrlShortner.dto.UrlDTO;
import com.aman.UrlShortner.entity.UrlEntity;
import com.aman.UrlShortner.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shortner")
@CrossOrigin(origins = "http://localhost:4200")
public class UrlController {

    @Autowired
    private UrlService urlService;


    @GetMapping("/{id}")
    public String getUrl(@PathVariable String id){
            return  urlService.getOriginalUrl(id);
}

    @PostMapping
    public UrlDTO generateShortUrl(@RequestBody String url){
        return  urlService.generateShortUrl(url);
    }
    @GetMapping(path="/validateUrl")
    public UrlEntity getValidateUrl(@RequestParam String url) {
        System.out.println("Url from front...." + url);
        return urlService.getValidUrl(url);
    }
}
