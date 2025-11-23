package cl.duocuc.darmijo.recipes.repostiory;

import cl.duocuc.darmijo.recipes.models.Comment;
import cl.duocuc.darmijo.recipes.models.Step;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByRecipeId(int recipeId);
}
