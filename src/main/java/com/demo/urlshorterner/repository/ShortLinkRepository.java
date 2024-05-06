package com.demo.urlshorterner.repository;

import com.demo.urlshorterner.entity.ShortLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ShortLinkRepository extends JpaRepository<ShortLink,Long> {

    @Query("SELECT short_link.originalUrl FROM ShortLink short_link  WHERE short_link.shortUrl = :shortUrl")
    String findOriginalUrlByShortUrl(String shortUrl);

    ShortLink findShortUrlByOriginalUrl(String originalUrl);

    ShortLink findShortLinkByShortUrl(String shortUrl);

}
