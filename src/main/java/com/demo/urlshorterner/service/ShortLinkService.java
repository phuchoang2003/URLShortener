package com.demo.urlshorterner.service;


import com.demo.urlshorterner.dto.ShortLinkDTO;
import com.demo.urlshorterner.entity.ShortLink;
import com.demo.urlshorterner.exception.InvalidUrl;
import com.demo.urlshorterner.repository.ShortLinkRepository;
import com.demo.urlshorterner.utils.MD5Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ShortLinkService {
    private final ShortLinkRepository shortLinkRepository;


    @Transactional
    public ShortLink createShortLink(ShortLinkDTO shortLinkDTO){
        String originalUrl = shortLinkDTO.originalUrl();
        ShortLink existingShortLink = shortLinkRepository.findShortUrlByOriginalUrl(originalUrl);
        if (existingShortLink != null) {
            return existingShortLink;
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(!isValidOriginalUrl(originalUrl)){
            throw new InvalidUrl("Your Url is invalid, must have http");
        }
        String shortUrl = generateRandomShortUrl(originalUrl);

        LocalDateTime currentTime = LocalDateTime.now();
        ShortLink shortLink = ShortLink
                .builder()
                .originalUrl(originalUrl)
                .createdAt(currentTime)
                .clickedCount(0L)
                .shortUrl(shortUrl)
                .build();


        return shortLinkRepository.save(shortLink);

    }


    @Transactional
    @Cacheable(key = "#shortUrl" , value = "urls")
    public String redirectToLongUrl(String shortUrl){
        try{
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ShortLink shortLink = shortLinkRepository.findShortLinkByShortUrl(shortUrl);
        if(shortLink==null){
            throw new InvalidUrl("Your Url is not found");
        }
        Long clickedCount = shortLink.getClickedCount();
        shortLink.setClickedCount(clickedCount+1);
        shortLinkRepository.save(shortLink);
        return shortLinkRepository.findOriginalUrlByShortUrl(shortUrl);
    }


    private boolean isValidOriginalUrl(String url) {
        return url.startsWith("http");
    }




    private String generateRandomShortUrl(String longURL) {
        String hash = MD5Utils.convert(longURL);
        int numberOfCharsInHash = hash.length();
        int counter = 0;
        String shortUrl="";
        while (counter <= numberOfCharsInHash - MD5Utils.SHORT_URL_CHAR_SIZE) {
            shortUrl = hash.substring(counter, counter + MD5Utils.SHORT_URL_CHAR_SIZE);
            String originalUrl = shortLinkRepository.findOriginalUrlByShortUrl(shortUrl);
            if (originalUrl == null || originalUrl.isEmpty()) {
                break;
            }
            counter++;
        }
        return shortUrl;
    }

}
