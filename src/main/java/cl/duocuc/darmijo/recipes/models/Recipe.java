package cl.duocuc.darmijo.recipes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.f4b6a3.ulid.Ulid;
import com.github.f4b6a3.ulid.UlidCreator;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private final String ulid;
    private final String title;
    private final String description;
    private final List<Ingredient> ingredients;
    private final List<String> steps;
    private final List<Image> images;
    private final String duration; // hh:mm
    private final String origin; // country or region
    
    
    public Recipe(String title, String description, List<Ingredient> ingredients, List<String> steps, List<Image> images, String duration, String origin) {
        this.ulid = UlidCreator.getUlid().toString();
        this.title = title;
        this.description = description;
        this.ingredients = ingredients;
        this.steps = steps;
        this.images = images;
        this.duration = duration;
        this.origin = origin;
    }
    
    public Recipe(String title, String description) {
        this.ulid = UlidCreator.getUlid().toString();
        this.title = title;
        this.description = description;
        this.ingredients = new ArrayList<>();
        this.steps = new ArrayList<>();;
        this.images = new ArrayList<>();;
        this.duration = "";
        this.origin = "";
    }
    
    public String getUlid() {
        return ulid;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public List<Ingredient> getIngredients() {
        return ingredients;
    }
    
    public List<String> getSteps() {
        return steps;
    }
    
    public List<Image> getImages() {
        return images;
    }
    
    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }
    
    public void addStep(String step) {
        this.steps.add(step);
    }
    
    public void addImage(Image image) {
        this.images.add(image);
    }
    
    public String getDuration() {
        return duration;
    }
    
    public String getOrigin() {
        return origin;
    }
}
