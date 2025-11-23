package cl.duocuc.darmijo.recipes.repostiory;

import cl.duocuc.darmijo.recipes.models.Image;
import cl.duocuc.darmijo.recipes.models.Ingredient;
import cl.duocuc.darmijo.recipes.models.Step;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    List<Image> findByRecipeId(int recipeId);
}
