package com.klumusic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class KluMusicApplication {
    public static void main(String[] args) {
        SpringApplication.run(KluMusicApplication.class, args);
    }
}




