package com.example.demo;

import com.example.demo.config.StarWarsProperties;
import com.example.demo.data.Information;
import com.example.demo.data.Person;
import com.example.demo.data.Planet;
import com.example.demo.data.Starship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InformationServiceTest {
    private static final int DARTH_VADER_ID = 0;
    private static final int DEATH_STAR_ID = 1;
    private static final int LEIA_ID = 2;
    private static final int ALDERAAN_ID = 3;
    private static final int DARTH_VADER_STARSHIP_ID = 4;
    private static final Integer DEATH_STAR_CREW_COUNT = 100;

    @Mock
    StarWarsClient starWarsClient;
    @Mock
    StarWarsProperties properties;
    @InjectMocks
    InformationService informationService;

    @Mock
    Person mockDarthVader;
    @Mock
    Starship mockDarthVaderStarship;
    @Mock
    Starship mockDeathStar;
    @Mock
    Planet mockAlderaan;


    @BeforeEach
    void setUp() {
        // Config Props
        when(properties.getDarthVaderId()).thenReturn(DARTH_VADER_ID);
        when(properties.getDeathStarId()).thenReturn(DEATH_STAR_ID);
        lenient().when(properties.getLeiaId()).thenReturn(LEIA_ID);
        when(properties.getAlderaanId()).thenReturn(ALDERAAN_ID);

        // Darth Vader Setup
        when(mockDarthVader.getFirstStarshipId()).thenReturn(Optional.of(DARTH_VADER_STARSHIP_ID));

        // Death Star setup
        lenient().when(mockDeathStar.getCrew()).thenReturn(DEATH_STAR_CREW_COUNT.toString());

        // Alderaan setup
        lenient().when(mockAlderaan.getResidentIds()).thenReturn(List.of(LEIA_ID));

        // Client invocations Setup
        when(starWarsClient.getPerson(DARTH_VADER_ID)).thenReturn(mockDarthVader);
        when(starWarsClient.getSingleStarship(DARTH_VADER_STARSHIP_ID)).thenReturn(mockDarthVaderStarship);
        when(starWarsClient.getSingleStarship(DEATH_STAR_ID)).thenReturn(mockDeathStar);
        when(starWarsClient.getSinglePlanet(ALDERAAN_ID)).thenReturn(mockAlderaan);
    }

    @Test
    void shouldBuildInformationObjectWithCorrectFields() {
        Information actual = informationService.getInformation();

        Information expected = Information.builder()
                .darthVaderStarship(mockDarthVaderStarship)
                .deathStarCrew(DEATH_STAR_CREW_COUNT)
                .isLeiaOnPlanet(true)
                .build();

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void shouldBuildWithEmptyStarshipIfCannotGetDarthVaderShip() {
        when(starWarsClient.getSingleStarship(DARTH_VADER_STARSHIP_ID)).thenReturn(null);

        Information actual = informationService.getInformation();

        assertNull(actual.getDarthVaderStarship());
    }

    @Test
    void shouldBuildWithDefaultValuesIfCannotGetResources() {
        when(starWarsClient.getSingleStarship(DARTH_VADER_STARSHIP_ID)).thenReturn(null);
        when(starWarsClient.getSingleStarship(DEATH_STAR_ID)).thenReturn(null);
        when(starWarsClient.getSinglePlanet(ALDERAAN_ID)).thenReturn(null);

        Information actual = informationService.getInformation();

        assertNull(actual.getDarthVaderStarship());
        assertThat(actual.getDeathStarCrew()).isEqualTo(0);
        assertThat(actual.isLeiaOnPlanet()).isFalse();
    }
}
