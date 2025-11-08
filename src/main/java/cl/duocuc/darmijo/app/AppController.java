package cl.duocuc.darmijo.app;

import cl.duocuc.darmijo.recipes.models.Recipe;
import cl.duocuc.darmijo.recipes.services.RecipeService;
import cl.duocuc.darmijo.users.service.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("app")
public class AppController {
    
    @Resource
    private JwtService jwtService;
    
    @Resource
    private RecipeService recipeService;
    
    @GetMapping("dashboard")
    public ModelAndView getDashboard(@CookieValue String token, Model model) {
        ModelAndView modelAndView = new ModelAndView("dashboard");
        log.info("get dashboard {}", token);
        Claims claims = jwtService.verifyAndGetClaims(token);
        List<Recipe> recipes = recipeService.getAllRecipes();
        String city = (String) claims.get("city");
        modelAndView.addObject("city", city);
        modelAndView.addObject("recipes", recipes);
        return modelAndView;
    }
    
}
