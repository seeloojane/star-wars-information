package com.example.demo.data;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class InformationResponse {
    private Starship starship;
    private int crew;
    private boolean isLeiaOnPlanet;
}
