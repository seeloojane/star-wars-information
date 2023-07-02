package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.logging.Logger;

@Controller
class StarWarsController {

    @GetMapping("/information")
    public ResponseEntity<Object> getInformation() {
        return ResponseEntity.ok().build();
    }

}