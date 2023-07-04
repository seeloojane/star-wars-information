package com.example.demo;

import com.example.demo.config.StarWarsProperties;
import com.example.demo.data.Information;
import com.example.demo.data.Person;
import com.example.demo.data.Planet;
import com.example.demo.data.Starship;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class InformationService {
    private static final int DEFAULT_DEATH_STAR_CREW_COUNT = 0;
    private static final boolean DEFAULT_LEIA_ON_PLANET = false;

    private final StarWarsClient starWarsClient;
    private final StarWarsProperties properties;

    public Information getInformation() {

        Starship darthVaderStarship = getDarthVaderStarship();
        int deathStarCrewCount = getDeathStarCrewCount();
        boolean isLeiaOnPlanet = checkLeiaOnAlderaan();

        return Information.builder()
                .darthVaderStarship(darthVaderStarship)
                .deathStarCrew(deathStarCrewCount)
                .isLeiaOnPlanet(isLeiaOnPlanet)
                .build();
    }

    private Starship getDarthVaderStarship() {
        Person darthVader = starWarsClient.getPerson(properties.getDarthVaderId());
        Optional<Integer> starshipIdOptional = darthVader.getFirstStarshipId();
        return starshipIdOptional.map(starWarsClient::getSingleStarship).orElse(null);
    }

    private int getDeathStarCrewCount() {
        Starship deathStar = starWarsClient.getSingleStarship(properties.getDeathStarId());
        if (deathStar != null) {
            return NumberParser.parseCrewCountString(deathStar.getCrew());
        } else {
            return DEFAULT_DEATH_STAR_CREW_COUNT;
        }
    }

    private boolean checkLeiaOnAlderaan() {
        Planet alderaan = starWarsClient.getSinglePlanet(properties.getAlderaanId());
        if (alderaan != null) {
            return alderaan.getResidentIds().stream().anyMatch(id -> id == properties.getLeiaId());
        } else {
            return DEFAULT_LEIA_ON_PLANET;
        }
    }
}

