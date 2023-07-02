package com.example.demo.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    ObjectMapper mapper = new ObjectMapper();
    @Test
    public void shouldDeserializeAllFields() throws IOException {
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
    public void shouldDeserializeStarshipsAsEmptyListIfNone() throws IOException {
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
}