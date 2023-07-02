package com.example.demo;

import com.example.demo.data.Starship;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class StarWarsClient {

    private final RestTemplate restTemplate;

    public StarWarsClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.rootUri("https://swapi.dev/api").build();
    }

    public Starship getSingleStarship(int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        try {
            return this.restTemplate
                    .exchange("/starships/{id}", HttpMethod.GET, requestEntity, Starship.class, id)
                    .getBody();
        } catch (HttpClientErrorException exception) {
            return null;
        }
    }

}
