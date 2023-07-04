package com.example.demo;

import com.example.demo.config.StarWarsProperties;
import com.example.demo.data.Person;
import com.example.demo.data.Planet;
import com.example.demo.data.Starship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withResourceNotFound;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(StarWarsClient.class)
@AutoConfigureWebClient()
class StarWarsClientTest {
    private static final String BASE_URL = "https://example.com";

    @MockBean
    StarWarsProperties properties;
    @Autowired
    private StarWarsClient starWarsClient;
    @Autowired
    private MockRestServiceServer mockRestServiceServer;

    @BeforeEach
    void setUp() {
        when(properties.getBaseUrl()).thenReturn(BASE_URL);
        mockRestServiceServer.reset();
    }

    @Test
    void shouldFetchStarshipWithGivenId() {
        int mockId = 1;
        String json = """
                   {
                       "name": "Death Star",
                       "model": "DS-1 Orbital Battle Station",
                       "starship_class": "Deep Space Mobile Battlestation",
                       "crew": "342,953"
                   }
                """;

        this.mockRestServiceServer
                .expect(requestTo("/starships/" + mockId))
                .andRespond(withSuccess(json, MediaType.APPLICATION_JSON));

        Starship result = starWarsClient.getSingleStarship(mockId);

        mockRestServiceServer.verify();
        assertNotNull(result);
        assertEquals("Death Star", result.getName());
        assertEquals("DS-1 Orbital Battle Station", result.getModel());
        assertEquals("Deep Space Mobile Battlestation", result.getStarshipClass());
        assertEquals("342,953", result.getCrew());
    }

    @Test
    void shouldReturnNullIfStarshipNotFound() {
        int notFoundId = -1;

        this.mockRestServiceServer
                .expect(requestTo("/starships/" + notFoundId))
                .andRespond(withResourceNotFound());

        Starship result = starWarsClient.getSingleStarship(notFoundId);

        mockRestServiceServer.verify();
        assertNull(result);
    }

    @Test
    void shouldFetchPersonWithGivenId() {
        int mockId = 1;
        String json = """
                   {
                       "name": "Leia",
                        "starships": [
                            "https://example/starships/5/"
                        ]
                   }
                """;

        this.mockRestServiceServer
                .expect(requestTo("/people/" + mockId))
                .andRespond(withSuccess(json, MediaType.APPLICATION_JSON));

        Person result = starWarsClient.getPerson(mockId);

        mockRestServiceServer.verify();
        assertNotNull(result);
        assertEquals("Leia", result.getName());
        assertEquals(List.of("https://example/starships/5/"), result.getStarships());
    }

    @Test
    void shouldReturnNullIfPersonNotFound() {
        int notFoundId = -1;

        this.mockRestServiceServer
                .expect(requestTo("/people/" + notFoundId))
                .andRespond(withResourceNotFound());

        Person result = starWarsClient.getPerson(notFoundId);

        mockRestServiceServer.verify();
        assertNull(result);
    }

    @Test
    void shouldFetchPlanetWithGivenId() {
        int mockId = 1;
        String json = """
                   {
                       "name": "Alderaan",
                        "residents": [
                            "https://example/people/5/"
                        ]
                   }
                """;

        this.mockRestServiceServer
                .expect(requestTo("/planets/" + mockId))
                .andRespond(withSuccess(json, MediaType.APPLICATION_JSON));

        Planet result = starWarsClient.getSinglePlanet(mockId);

        mockRestServiceServer.verify();
        assertNotNull(result);
        assertEquals("Alderaan", result.getName());
        assertEquals(List.of("https://example/people/5/"), result.getResidents());
    }

    @Test
    void shouldReturnNullIfPlanetNotFound() {
        int notFoundId = -1;

        this.mockRestServiceServer
                .expect(requestTo("/planets/" + notFoundId))
                .andRespond(withResourceNotFound());

        Planet result = starWarsClient.getSinglePlanet(notFoundId);

        mockRestServiceServer.verify();
        assertNull(result);
    }
}
