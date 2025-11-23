package cl.duocuc.darmijo.recipes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.f4b6a3.ulid.Ulid;
import com.github.f4b6a3.ulid.UlidCreator;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Comments;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "exp2_recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String ulid;
    @Column(unique = true)
    private String title;
    private String description;
    @Transient
    private List<Ingredient> ingredients;
    @Transient
    private List<Step> steps;
    @Transient
    private List<Image> images;
    @Transient
    private List<Comment> comments;
    private String duration; // hh:mm
    private String origin; // country or region
    
    
    
    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }
    public void addIngredients(List<Ingredient> ingredients) {
        this.ingredients.addAll(ingredients);
    }
    
    public void addStep(Step step) {
        this.steps.add(step);
    }
    public void addSteps(List<Step> steps) {
        this.steps.addAll(steps);
    }
    
    public void addImage(Image image) {
        this.images.add(image);
    }
    public void addImages(List<Image> images) {
        this.images.addAll(images);
    }
    
    public void addComments(List<Comment> comments) {
        this.comments.addAll(comments);
    }
    
    public long getCreatedAt() {
        return Ulid.from(ulid).getTime();
    }
    
    public double getRating() {
        if(this.comments == null || this.comments.size() == 0) return 0.0;
        double total = comments.stream().mapToDouble(Comment::getRating).sum();
        return BigDecimal.valueOf(total / this.comments.size()).divide(BigDecimal.ONE, 2, RoundingMode.DOWN).doubleValue();
    }
 
}
