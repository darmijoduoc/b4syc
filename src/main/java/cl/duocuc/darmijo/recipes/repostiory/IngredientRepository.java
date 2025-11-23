package cl.duocuc.darmijo.recipes.repostiory;

import cl.duocuc.darmijo.recipes.models.Ingredient;
import cl.duocuc.darmijo.recipes.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
    List<Ingredient> findByRecipeId(int recipeId);
}
