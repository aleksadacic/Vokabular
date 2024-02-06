package com.aleksadacic.vokabular.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.aleksadacic")
public class VokabularApplication {
    public static void main(String[] args) {
        SpringApplication.run(VokabularApplication.class, args);
    }
}
