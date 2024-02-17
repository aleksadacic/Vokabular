package com.aleksadacic.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:generator.properties")
public class AppConfigLoader {
    @Value("${pkg.business}")
    private String businessPackage;
    @Value("${pkg.writer}")
    private String writerPackage;
    @Value("${pkg.source}")
    private String sourcePackage;
    @Value("${resource.model.export.config}")
    private String exportConfigPath;
    @Value("${resource.model.export}")
    private String exportFilePath;
}
