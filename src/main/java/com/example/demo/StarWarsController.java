package com.example.demo;

import com.example.demo.response.InformationResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
@Slf4j
class StarWarsController {

    private final InformationService informationService;

    @GetMapping("/information")
    public ResponseEntity<InformationResponse> getInformation() {
        try {
            InformationResponse response = InformationResponse.from(informationService.getInformation());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error occurred while handling GET /information endpoint.", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
