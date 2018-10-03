package com.beaufortfairmont.invoicemanager.models;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties
public class ApplicationConfiguration {
    private String baseUrl;
}
