package com.example.demo;

import com.example.demo.data.Information;
import com.example.demo.data.Starship;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = StarWarsController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
class StarWarsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    InformationService informationService;

    @Test
    void getInformationReturns200WithResponse() throws Exception {
        when(informationService.getInformation()).thenReturn(
                Information.builder()
                        .darthVaderStarship(Starship.builder()
                                .crew("0")
                                .model("someModel")
                                .starshipClass("someClass")
                                .build()
                        )
                        .deathStarCrew(10)
                        .isLeiaOnPlanet(true)
                        .build()
        );

        String expectedResponse = "{\"starship\":{\"model\":\"someModel\",\"class\":\"someClass\"},\"crew\":10,\"isLeiaOnPlanet\":true}";
        mockMvc.perform(MockMvcRequestBuilders.get("/information"))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }

    @Test
    void getInformationReturns500IfErrorsOccur() throws Exception {
        when(informationService.getInformation()).thenThrow(new RuntimeException("some error"));

        mockMvc.perform(MockMvcRequestBuilders.get("/information"))
                .andExpect(status().is5xxServerError());
    }
}