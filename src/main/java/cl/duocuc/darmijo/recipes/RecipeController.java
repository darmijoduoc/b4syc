package cl.duocuc.darmijo.recipes;

import cl.duocuc.darmijo.recipes.services.RecipeService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("recipes")
public class RecipeController { 
    
    @Resource
    private RecipeService recipeService;
    
    public ModelAndView getPage() {
        ModelAndView modelAndView = new ModelAndView("recipes_page.html");
        return modelAndView;
    }
}
