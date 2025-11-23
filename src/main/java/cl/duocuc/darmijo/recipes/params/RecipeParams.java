package cl.duocuc.darmijo.recipes.params;

import lombok.Data;

import java.util.List;

@Data
public class RecipeParams {
    private String title;
    private String description;
    private List<IngredientParams> ingredients;
    private List<StepParams> steps;
    private List<ImageParams> images;
    private List<CommentParams> comments;
    
    private String duration;
    private String origin;
    
    public RecipeParams() {
    }
    
    public RecipeParams(String title, String description, List<IngredientParams> ingredients, List<StepParams> steps, List<ImageParams> images, List<CommentParams> comments, String duration, String origin) {
        this.title = title;
        this.description = description;
        this.ingredients = ingredients;
        this.steps = steps;
        this.images = images;
        this.comments = comments;
        this.duration = duration;
        this.origin = origin;
    }
}
