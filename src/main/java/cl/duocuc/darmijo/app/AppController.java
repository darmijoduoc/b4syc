package cl.duocuc.darmijo.app;

import cl.duocuc.darmijo.core.exceptions.ResourceNotFoundException;
import cl.duocuc.darmijo.recipes.models.Recipe;
import cl.duocuc.darmijo.recipes.services.RecipeService;
import cl.duocuc.darmijo.users.models.User;
import cl.duocuc.darmijo.users.service.JwtService;
import cl.duocuc.darmijo.users.service.UserService;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        @CookieValue(name="Authorization", required = true) String token,
        Model model
    ) {
        if(token == null) return new ModelAndView("redirect:/login?no_session");
        try {
            Claims claims = jwtService.verifyAndGetClaims(token);
            String email = claims.getSubject();
            User user = userService.getUserByEmail(email);
            List<Recipe> recipes = recipeService.getAllRecipes();
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
        HttpServletRequest request,
        @CookieValue(name="Authorization", required = true) String token,
        @PathVariable(name="ulid") String ulid,
        HttpServletResponse httpServletResponse) throws ResourceNotFoundException {
        if(token == null) return new ModelAndView("redirect:/login?no_session");
        Claims claims = jwtService.verifyAndGetClaims(token);
        Optional<Recipe> recipe = recipeService.getRecipeByUlid(ulid);
        if(recipe.isEmpty()) {
            return new ModelAndView("redirect:/recipes");
        }
        
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        String baseUrl = scheme + "://" + serverName + ((serverPort == 80 || serverPort == 443) ? "" : ":" + serverPort);
        
        return new ModelAndView("recipe")
            .addObject("recipe", recipe.get())
            .addObject("jwt", jwtService.createWithSubject("readonly"))
            .addObject("baseUrl", baseUrl);

    }
    
    @PostMapping("recipe/{ulid}/comments")
    public ModelAndView postComment(
        HttpServletRequest request,
        @CookieValue(name="Authorization", required = true) String token,
        @PathVariable(name="ulid") String ulid,
        @RequestParam("comment") String comment,
        @RequestParam("rating") int rating,
        HttpServletResponse httpServletResponse) throws ResourceNotFoundException {
        if(token == null) return new ModelAndView("redirect:/login?no_session");
        Claims claims = jwtService.verifyAndGetClaims(token);
        User user = userService.getUserByEmail(claims.getSubject());
        recipeService.commentRecipe(ulid, user.getDisplayName(), comment, rating);
        
        return new ModelAndView("redirect:/recipe/" + ulid);
    }
    
    @GetMapping("shared_recipe/{ulid}")
    public ModelAndView getSharedRecipe(
        @RequestParam(name="jwt", required = true) String token,
        @PathVariable(name="ulid") String ulid
    ) throws ResourceNotFoundException {
        if(token == null) return new ModelAndView("redirect:/login?no_session");
        jwtService.verifyAndGetClaims(token);
        Optional<Recipe> recipe = recipeService.getRecipeByUlid(ulid);
        if(recipe.isEmpty()) throw new ResourceNotFoundException("Recipe not found");
        return new ModelAndView("shared_recipe")
            .addObject("recipe", recipe.get());
    }
    
}
