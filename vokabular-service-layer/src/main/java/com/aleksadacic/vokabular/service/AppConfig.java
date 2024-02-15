package com.aleksadacic.vokabular.service;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("com.aleksadacic")
@EntityScan(basePackages = "com.aleksadacic")
@EnableJpaRepositories(basePackages = "com.aleksadacic")
public class AppConfig {
    // Other configurations
}
