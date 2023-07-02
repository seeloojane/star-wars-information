package com.example.demo.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("sw")
@Component
@NoArgsConstructor
@Getter
@Setter
public class StarWarsProperties {
    private String baseUrl;
    private String darthVaderId;
    private String leiaId;
    private String deathStarId;
    private String alderaanId;
}
