package com.katsiankou.swagger.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "com.katsiankou.swagger")
public record SwaggerProperties(String apiTitle, String version, String description) {
}
