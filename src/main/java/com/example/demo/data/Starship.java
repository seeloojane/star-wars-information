package com.example.demo.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Starship {
    private String name;
    @JsonProperty("starship_class")
    private String starshipClass;
    private String model;
    private String crew;
}
