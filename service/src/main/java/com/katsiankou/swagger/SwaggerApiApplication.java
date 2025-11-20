package com.katsiankou.swagger;

import com.katsiankou.swagger.configuration.properties.CacheProperties;
import com.katsiankou.swagger.configuration.properties.SwaggerProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties({SwaggerProperties.class, CacheProperties.class})
public class SwaggerApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwaggerApiApplication.class, args);
    }

}
