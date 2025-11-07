package cl.duocuc.darmijo.recipes;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("recipes")
public class RecipeController { 
    
    public String getPage() {
        return "recipes_page";
    }
}
