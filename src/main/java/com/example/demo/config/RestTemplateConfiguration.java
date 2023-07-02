package com.example.demo.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {
    @Bean
    public RestTemplate restTemplate(StarWarsProperties starWarsProperties) {
        return new RestTemplateBuilder()
                .rootUri(starWarsProperties.getBaseUrl())
                .build();
    }
}
