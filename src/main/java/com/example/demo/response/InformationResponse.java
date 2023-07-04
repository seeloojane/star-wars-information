package com.example.demo.response;

import com.example.demo.data.Information;
import com.example.demo.data.Starship;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@Setter
@Getter
@EqualsAndHashCode
public class InformationResponse {
    private StarshipTruncated starship;
    private int crew;
    // Use Boxed Boolean over primitive to circumvent built-in Jackson behaviour of removing "is"
    // from field name during serde
    private Boolean isLeiaOnPlanet;

    public static InformationResponse from(Information information) {
        return InformationResponse.builder()
                .starship(StarshipTruncated.from(information.getDarthVaderStarship()))
                .crew(information.getDeathStarCrew())
                .isLeiaOnPlanet(information.isLeiaOnPlanet())
                .build();
    }

    @Builder
    @Setter
    @Getter
    @EqualsAndHashCode
    @JsonInclude(NON_NULL)
    static class StarshipTruncated {
        String name;
        @JsonProperty("class")
        String starshipClass;
        String model;

        public static StarshipTruncated from(Starship starship) {
            if (starship == null) {
                return StarshipTruncated.builder().build();
            }
            return StarshipTruncated.builder()
                    .name(starship.getName())
                    .model(starship.getModel())
                    .starshipClass(starship.getStarshipClass())
                    .build();
        }
    }
}
