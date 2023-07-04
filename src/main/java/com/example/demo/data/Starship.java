package com.example.demo.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Starship {
    private String name;
    @JsonProperty("starship_class")
    private String starshipClass;
    private String model;
    private String crew;
}
