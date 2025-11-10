package cl.duocuc.darmijo.app;

import cl.duocuc.darmijo.core.exceptions.ResourceNotFoundException;
import cl.duocuc.darmijo.recipes.models.Recipe;
import cl.duocuc.darmijo.recipes.services.RecipeService;
import cl.duocuc.darmijo.users.models.User;
import cl.duocuc.darmijo.users.service.JwtService;
import cl.duocuc.darmijo.users.service.UserService;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/")
public class AppController {
    @Resource
    private UserService userService;
    @Resource
    private JwtService jwtService;
    
    @Resource
    private RecipeService recipeService;
    
    @Resource
    AccessController accessController;
    
    @GetMapping
    public ModelAndView getHome() {
        List<Recipe> recipes = recipeService.getAllRecipes();
        return new ModelAndView("dashboard")
            .addObject("recipes", recipes);
    }
    
    @GetMapping("recipes")
    public ModelAndView getDashboard(
        @CookieValue(name="Authorization", required = false) String token,
        Model model
    ) {
        if(token == null) {
            return new ModelAndView("redirect:/login?no_session");
        }
        log.info("get dashboard {}", token);
        try {
            Claims claims = jwtService.verifyAndGetClaims(token);
            String email = claims.getSubject();
            User user = userService.getUserByEmail(email);
            List<Recipe> recipes = recipeService.getAllRecipes();
            String city = (String) claims.get("city");
            return new ModelAndView("recipes")
                .addObject("recipes", recipes)
                .addObject("user", user);
        } catch (Exception e) {
            //return accessController.logout();
            return new ModelAndView("redirect:/login");
        }
        
        
    }
    
    @GetMapping("recipe/{ulid}")
    public ModelAndView getRecipe(
        @CookieValue(name="Authorization") String token,
        @PathVariable(name="ulid") String ulid
    ) throws ResourceNotFoundException {
        log.info("get dashboard {}", token);
        Claims claims = jwtService.verifyAndGetClaims(token);
        Optional<Recipe> recipe = recipeService.getRecipeById(ulid);
        if(recipe.isEmpty()) throw new ResourceNotFoundException("Recipe not found");
        return new ModelAndView("recipe")
            .addObject("recipe", recipe.get());
    }
    
}
