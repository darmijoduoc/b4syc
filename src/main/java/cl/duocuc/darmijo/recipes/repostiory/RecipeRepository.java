package cl.duocuc.darmijo.recipes.repostiory;

import cl.duocuc.darmijo.recipes.models.Recipe;
import cl.duocuc.darmijo.users.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    Optional<Recipe> findByUlid(String ulid);
}
