package com.primefuel.fulltank.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class FullTankPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(FullTankPlatformApplication.class, args);
    }

}
