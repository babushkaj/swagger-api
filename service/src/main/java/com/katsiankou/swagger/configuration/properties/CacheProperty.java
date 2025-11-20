package com.katsiankou.swagger.configuration.properties;

public record CacheProperty(String name, int expireAfterWriteHours, int maximumSize) {
}
