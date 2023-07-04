package com.example.demo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NumberParserTest {

    @Test
    void shouldParseToInteger() {
        String crewCount = "1123456";
        assertThat(NumberParser.parseCrewCountString(crewCount)).isEqualTo(1123456);
    }

    @Test
    void shouldRemoveCommasAndParseToInteger() {
        String crewCount = "1,123,456";
        assertThat(NumberParser.parseCrewCountString(crewCount)).isEqualTo(1123456);
    }
}