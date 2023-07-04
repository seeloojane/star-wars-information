package com.example.demo.data;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Information {
    private Starship darthVaderStarship;
    private int deathStarCrew;
    private boolean isLeiaOnPlanet;
}
