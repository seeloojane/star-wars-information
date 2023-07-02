package com.example.demo;

import com.example.demo.data.Person;
import com.example.demo.data.Planet;
import com.example.demo.data.Starship;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
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
        } catch (Exception exception) {
            log.error("Error occurred when invoking API to get Starship details.", exception);
            return null;
        }
    }

    public Person getPerson(int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        try {
            return this.restTemplate
                    .exchange("/people/{id}", HttpMethod.GET, requestEntity, Person.class, id)
                    .getBody();
        } catch (Exception exception) {
            log.error("Error occurred when invoking API to get Person details.", exception);
            return null;
        }
    }

    public Planet getSinglePlanet(int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        try {
            return this.restTemplate
                    .exchange("/planets/{id}", HttpMethod.GET, requestEntity, Planet.class, id)
                    .getBody();
        } catch (Exception exception) {
            log.error("Error occurred when invoking API to get Planet details.", exception);
            return null;
        }
    }

}
