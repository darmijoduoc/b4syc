package cl.duocuc.darmijo.recipes.services;

import cl.duocuc.darmijo.core.exceptions.ResourceNotFoundException;
import cl.duocuc.darmijo.recipes.models.*;
import cl.duocuc.darmijo.recipes.params.IngredientParams;
import cl.duocuc.darmijo.recipes.params.RecipeParams;
import cl.duocuc.darmijo.recipes.repostiory.*;
import com.github.f4b6a3.ulid.UlidCreator;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Comments;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeService {
    
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final StepRepository stepRepository;
    private final ImageRepository imageRepository;
    private final CommentRepository commentRepository;

    
    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = recipeRepository.findAll();
        recipes.forEach(this::composeRecipe);
        return recipes;
    }
    
    public Optional<Recipe> getRecipeById(int id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        recipe.ifPresent(this::composeRecipe);
        return recipe;
    }
    
    public Optional<Recipe> getRecipeByUlid(String ulid) {
        Optional<Recipe> recipe = recipeRepository.findByUlid(ulid);
        recipe.ifPresent(this::composeRecipe);
        return recipe;
    }
    
    public void composeRecipe (Recipe recipe) {
        List<Ingredient> ingredients = ingredientRepository.findByRecipeId(recipe.getId());
        List<Step> steps = stepRepository.findByRecipeId(recipe.getId());
        List<Image> images = imageRepository.findByRecipeId(recipe.getId());
        List<Comment> comments = commentRepository.findByRecipeId(recipe.getId());
        recipe.setIngredients(ingredients);
        recipe.setSteps(steps);
        recipe.setImages(images);
        recipe.setComments(comments);
    }
    
    public void commentRecipe (String recipeUlid, String author, String comment, int rating) throws ResourceNotFoundException {
        Optional<Recipe> optionalRecipe = getRecipeByUlid(recipeUlid);
        if(optionalRecipe.isPresent()) {
            Recipe recipe = optionalRecipe.get();
            Comment newComment = new Comment();
            newComment.setUlid(UlidCreator.getUlid().toString());
            newComment.setRecipeId(recipe.getId());
            newComment.setAuthor(author);
            newComment.setText(comment);
            newComment.setRating(rating);
            commentRepository.save(newComment);
        } else {
            throw new ResourceNotFoundException("Recipe not found");
        }
    }
    
}
