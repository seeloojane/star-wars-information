package com.example.demo.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Starship {

    @JsonInclude(NON_NULL)
    private String name;

    @JsonInclude(NON_NULL)
    @JsonProperty("class")
    private String className;

    @JsonInclude(NON_NULL)
    private String model;
}
