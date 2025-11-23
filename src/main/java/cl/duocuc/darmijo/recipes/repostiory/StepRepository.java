package cl.duocuc.darmijo.recipes.repostiory;

import cl.duocuc.darmijo.recipes.models.Ingredient;
import cl.duocuc.darmijo.recipes.models.Recipe;
import cl.duocuc.darmijo.recipes.models.Step;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StepRepository extends JpaRepository<Step, Integer> {
    List<Step> findByRecipeId(int recipeId);
}
