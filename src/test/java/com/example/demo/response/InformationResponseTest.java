package com.example.demo.response;

import com.example.demo.data.Information;
import com.example.demo.data.Starship;
import com.example.demo.response.InformationResponse.StarshipTruncated;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InformationResponseTest {
    Starship TEST_STARSHIP = Starship.builder()
            .name("someStarshipName")
            .model("someModel")
            .starshipClass("someClass")
            .build();

    Information TEST_INFORMATION = Information.builder()
            .darthVaderStarship(TEST_STARSHIP)
            .deathStarCrew(100)
            .isLeiaOnPlanet(true)
            .build();

    @Nested
    class Build {
        @Test
        void shouldBuildInformationResponseFromInformation() {
            InformationResponse informationResponse = InformationResponse.from(TEST_INFORMATION);

            assertEquals(100, informationResponse.getCrew());
            assertTrue(informationResponse.getIsLeiaOnPlanet());

            StarshipTruncated starshipTruncated = informationResponse.getStarship();
            assertNotNull(starshipTruncated);
            assertEquals("someStarshipName", starshipTruncated.getName());
            assertEquals("someModel", starshipTruncated.getModel());
            assertEquals("someClass", starshipTruncated.getStarshipClass());
        }

        @Test
        void shouldBuildStarshipTruncatedObjectFromStarship() {
            StarshipTruncated starshipTruncated = StarshipTruncated.from(TEST_STARSHIP);

            assertEquals("someStarshipName", starshipTruncated.getName());
            assertEquals("someModel", starshipTruncated.getModel());
            assertEquals("someClass", starshipTruncated.getStarshipClass());
        }

        @Test
        void shouldBuildEmptyStarshipTruncatedObjectIfProvidedNull() {
            StarshipTruncated starshipTruncated = StarshipTruncated.from(null);

            assertNotNull(starshipTruncated);
            assertNull(starshipTruncated.getName());
            assertNull(starshipTruncated.getModel());
            assertNull(starshipTruncated.getStarshipClass());
        }
    }

    @Nested
    class Serialization {
        @Test
        void shouldSerializeInformationResponseWithCorrectFieldsAndStructure() throws JsonProcessingException {
            InformationResponse response = InformationResponse.from(TEST_INFORMATION);

            ObjectMapper mapper = new ObjectMapper();
            String actual = mapper.writeValueAsString(response);

            String expected = "{\"starship\":{\"name\":\"someStarshipName\",\"model\":\"someModel\",\"class\":\"someClass\"},"
                    + "\"crew\":100,\"isLeiaOnPlanet\":true}";
            assertEquals(expected, actual);
        }

        @Test
        void shouldSerializeInformationResponseWithEmptyStarshipObject() throws JsonProcessingException {
            Information emptyInformation = Information.builder().build();
            InformationResponse response = InformationResponse.from(emptyInformation);

            ObjectMapper mapper = new ObjectMapper();
            String actual = mapper.writeValueAsString(response);

            String expected = "\"starship\":{}";
            assertTrue(actual.contains(expected));
        }

        @Test
        void shouldSerializeInformationResponseWithCorrectDefaultsIfInformationIsEmpty() throws JsonProcessingException {
            Information emptyInformation = Information.builder().build();
            InformationResponse response = InformationResponse.from(emptyInformation);

            ObjectMapper mapper = new ObjectMapper();
            String actual = mapper.writeValueAsString(response);

            String expectedCrew = "\"crew\":0";
            String expectedLeia = "\"isLeiaOnPlanet\":false";
            assertTrue(actual.contains(expectedCrew));
            assertTrue(actual.contains(expectedLeia));
        }
    }
}