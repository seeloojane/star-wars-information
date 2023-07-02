package com.example.demo.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;

class StarshipTest {
    ObjectMapper mapper = new ObjectMapper();
    @Test
    public void nameOfClassFieldIsChangedAfterSerialization() throws IOException {
        Starship starship = new Starship("some-n", "some-c", "some-m");

        String serialized = mapper.writeValueAsString(starship);

        assertThat(serialized, not(containsString("className")));
        assertThat(serialized, containsString("class"));
    }

    @Test
    public void shouldSerializeAllFieldsIfPresent() throws IOException {
        Starship starship = new Starship("some-n", "some-c", "some-m");

        String serialized = mapper.writeValueAsString(starship);

        assertThat(serialized, containsString("some-n"));
        assertThat(serialized, containsString("some-c"));
        assertThat(serialized, containsString("some-m"));
    }
    @Test
    public void shouldNotSerializeFieldIfEmpty() throws IOException {
        Starship starship = new Starship();

        String serialized = mapper.writeValueAsString(starship);

        assertThat(serialized, not(containsString("name")));
        assertThat(serialized, not(containsString("class")));
        assertThat(serialized, not(containsString("model")));
    }
}