package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/gerichte") // Gruppiert GET und POST unter /gerichte
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;

    // Holt alle gespeicherten Rezepte aus der DB
    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    // Fügt ein neues Rezept hinzu (POST aus dem Frontend)
    @PostMapping
    public Recipe createRecipe(@RequestBody Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @PutMapping("/{id}")
    public Recipe updateRecipe(@PathVariable Long id, @RequestBody Recipe updatedRecipe) {
        return recipeRepository.findById(id).map(recipe -> {
            recipe.setName(updatedRecipe.getName());
            recipe.setCategory(updatedRecipe.getCategory());
            recipe.setCookingTime(updatedRecipe.getCookingTime());
            return recipeRepository.save(recipe);
        }).orElseThrow(() -> new RuntimeException("Rezept nicht gefunden mit ID: " + id));
    }

    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable Long id) {
        recipeRepository.deleteById(id);
    }
}
