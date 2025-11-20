package com.katsiankou.swagger.configuration;

import com.katsiankou.swagger.configuration.properties.SwaggerProperties;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(SwaggerProperties swaggerProperties) {
        return new OpenAPI()
            .info(new Info()
                .title(swaggerProperties.apiTitle())
                .version(swaggerProperties.version())
                .description(swaggerProperties.description()));
    }
}
