package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllRecipes() throws Exception {
        mockMvc.perform(get("/gerichte"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateRecipe() throws Exception {
        Recipe rezept = new Recipe("Pizza", "Italienisch", 30);

        mockMvc.perform(post("/gerichte")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rezept)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Pizza"));
    }

    @Test
    void testUpdateRecipe() throws Exception {
        // Erst erstellen
        Recipe rezept = new Recipe("Nudeln", "Italienisch", 15);
        String res = mockMvc.perform(post("/gerichte")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rezept)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Recipe gespeichertesRezept = objectMapper.readValue(res, Recipe.class);

        // Dann updaten
        gespeichertesRezept.setCookingTime(20);
        mockMvc.perform(put("/gerichte/" + gespeichertesRezept.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(gespeichertesRezept)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cookingTime").value(20));
    }

    @Test
    void testDeleteRecipe() throws Exception {
        // Erst erstellen
        Recipe rezept = new Recipe("Salat", "Vegan", 10);
        String res = mockMvc.perform(post("/gerichte")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rezept)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Recipe gespeichertesRezept = objectMapper.readValue(res, Recipe.class);

        // Dann löschen
        mockMvc.perform(delete("/gerichte/" + gespeichertesRezept.getId()))
                .andExpect(status().isOk());
    }
}
