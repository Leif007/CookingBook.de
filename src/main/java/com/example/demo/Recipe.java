package com.example.demo;

public class Recipe {
    private String name;
    private String category;
    private int cookingTime; // in Minuten

    public Recipe() {
    }

    public Recipe(String name, String category, int cookingTime) {
        this.name = name;
        this.category = category;
        this.cookingTime = cookingTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }
}
