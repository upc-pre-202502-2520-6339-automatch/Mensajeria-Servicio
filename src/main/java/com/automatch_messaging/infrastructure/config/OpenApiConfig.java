package com.automatch_messaging.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI messagingServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Messaging Service API")
                        .description("Microservicio de mensajería entre comprador y vendedor (AutoMatch)")
                        .version("1.0.0"));
    }
}