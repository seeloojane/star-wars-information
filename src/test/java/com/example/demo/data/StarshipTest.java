package com.example.demo.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StarshipTest {
    ObjectMapper mapper = new ObjectMapper();
    @Test
    public void shouldDeserializeAllFields() throws IOException {
        String json = """
        {
            "name": "someName",
            "model": "someModel",
            "starship_class": "someClass",
            "crew": "someCrew"
        }
     """;

        Starship starship = mapper.readValue(json, Starship.class);

        assertEquals("someName", starship.getName());
        assertEquals("someModel", starship.getModel());
        assertEquals("someClass", starship.getStarshipClass());
        assertEquals("someCrew", starship.getCrew());
    }
}