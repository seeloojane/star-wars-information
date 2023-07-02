package com.example.demo.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PersonTest {
    ObjectMapper mapper = new ObjectMapper();

    @Test
    void shouldDeserializeAllFields() throws IOException {
        String json = """
                   {
                       "name": "someName",
                       "starships": [
                           "ship1",
                           "ship2"
                       ]
                   }
                """;

        Person person = mapper.readValue(json, Person.class);

        assertEquals("someName", person.getName());
        assertEquals(List.of("ship1", "ship2"), person.getStarships());
    }

    @Test
    void shouldDeserializeStarshipsAsEmptyListIfNone() throws IOException {
        String json = """
                   {
                       "name": "someName",
                       "starships": []
                   }
                """;

        Person person = mapper.readValue(json, Person.class);

        assertEquals("someName", person.getName());
        assertEquals(Collections.emptyList(), person.getStarships());
    }

    @Test
    void shouldReturnFirstStarshipIdIfPresent() {
        Person person = new Person("someName", List.of(
                "https://swapi.dev/api/starships/10/",
                "https://swapi.dev/api/starships/22/"
        ));

        Optional<Integer> result = person.getFirstStarshipId();

        assertTrue(result.isPresent());
        assertEquals(10, result.get());
    }

    @Test
    void shouldReturnEmptyOptionalIfNoStarships() {
        Person person = new Person("someName", Collections.emptyList());

        Optional<Integer> result = person.getFirstStarshipId();

        assertTrue(result.isEmpty());
    }
}