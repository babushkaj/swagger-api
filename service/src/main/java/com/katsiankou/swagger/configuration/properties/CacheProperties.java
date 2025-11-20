package com.katsiankou.swagger.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "com.katsiankou.cache")
public record CacheProperties(List<CacheProperty> config) {
}
