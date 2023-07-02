package com.example.demo.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlanetTest {
    ObjectMapper mapper = new ObjectMapper();

    @Test
    void shouldInitialiseWithEmptyResidentList() {
        Planet p = new Planet();

        assertNotNull(p.getResidents());
        assertTrue(p.getResidents().isEmpty());
    }

    @Test
    void shouldDeserializeAllFields() throws IOException {
        String json = """
                   {
                       "name": "someName",
                       "residents": [
                           "r1",
                           "r2"
                       ]
                   }
                """;

        Planet planet = mapper.readValue(json, Planet.class);

        assertEquals("someName", planet.getName());
        assertEquals(List.of("r1", "r2"), planet.getResidents());
    }

    @Test
    void shouldDeserializeStarshipsAsEmptyListIfNone() throws IOException {
        String json = """
                   {
                       "name": "someName",
                       "residents": []
                   }
                """;

        Planet planet = mapper.readValue(json, Planet.class);

        assertEquals("someName", planet.getName());
        assertEquals(Collections.emptyList(), planet.getResidents());
    }

    @Test
    void shouldReturnListOfResidentIdsIfPresent() {
        Planet planet = new Planet("someName", List.of(
                "https://swapi.dev/api/people/5/",
                "https://swapi.dev/api/people/68/",
                "https://swapi.dev/api/people/81/"
        ));

        List<Integer> list = planet.getResidentIds();

        assertEquals(List.of(5, 68, 81), list);
    }
}