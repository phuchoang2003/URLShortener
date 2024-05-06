package com.demo.urlshorterner.controller;


import com.demo.urlshorterner.dto.ShortLinkDTO;
import com.demo.urlshorterner.entity.ShortLink;
import com.demo.urlshorterner.service.ShortLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@CrossOrigin
@RestController
@RequiredArgsConstructor
public class ShortLinkController {
    private final ShortLinkService shortLinkService;
    private static String httpUrl = "http://127.0.0.1:8080/";

    @PostMapping("/urls")
    public ResponseEntity<String> createLink(@RequestBody ShortLinkDTO shortLinkDTO){
            ShortLink shortLink = shortLinkService.createShortLink(shortLinkDTO);
            String shortUrl = httpUrl + shortLink.getShortUrl();
            return ResponseEntity.status(HttpStatus.CREATED).body(shortUrl);

    }


    @GetMapping("/{shortUrl}")
    public ResponseEntity<String> redirectOriginalLink(@PathVariable String shortUrl) {


        String redirectUrl = shortLinkService.redirectToLongUrl(shortUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, redirectUrl);

        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }
}
