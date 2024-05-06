package com.demo.urlshorterner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class UrlshorternerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlshorternerApplication.class, args);
	}

}
