package com.example.demo;

import org.springframework.web.bind.annotation.CrossOrigin; // ⬅️ wichtig!
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecipeController {

    @GetMapping("/gerichte")
    public List<Recipe> getGerichte() {
        return List.of(
                new Recipe("Pasta Carbonara", "Italienisch", 20),
                new Recipe("Ratatouille", "Französisch", 45),
                new Recipe("Tiramisu", "Dessert", 30),
                new Recipe("Spaghetti Bolognese", "Italienisch", 25),
                new Recipe("Lasagne4", "Italienisch", 40)
        );
    }
}
