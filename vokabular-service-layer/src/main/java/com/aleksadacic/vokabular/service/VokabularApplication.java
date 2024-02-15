package com.aleksadacic.vokabular.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.aleksadacic")
public class VokabularApplication {
    public static void main(String[] args) {
        SpringApplication.run(VokabularApplication.class, args);
    }
}
