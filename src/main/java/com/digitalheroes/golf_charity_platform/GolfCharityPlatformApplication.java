package com.digitalheroes.golf_charity_platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableJpaRepositories("com.digitalheroes.golf_charity_platform.repository")
@EntityScan("com.digitalheroes.golf_charity_platform.model")
public class GolfCharityPlatformApplication {
    public static void main(String[] args) {
        SpringApplication.run(GolfCharityPlatformApplication.class, args);
    }
}