package com.example.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecipeController {

    @GetMapping("/gerichte")
    public List<String> getGerichte() {
        return List.of("Pasta Carbonara", "Ratatouille", "Tiramisu", "Spaghetti Bolognese", "Lasagne");
    }
}
