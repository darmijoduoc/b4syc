package cl.duocuc.darmijo.recipes.services;

import cl.duocuc.darmijo.recipes.models.Image;
import cl.duocuc.darmijo.recipes.models.Ingredient;
import cl.duocuc.darmijo.recipes.models.Recipe;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeService {
    
    private final List<Recipe> recipes;
    
    public RecipeService() {
        
        this.recipes = List.of(
            new Recipe("Spaghetti Bolognese", "A classic Italian pasta dish with rich meat sauce.", List.of(
                new Ingredient("Spaghetti", 200, "grams"),
                new Ingredient("Ground Beef", 300, "grams"),
                new Ingredient("Tomato Sauce", 400, "ml"),
                new Ingredient("Onion", 1, "medium"),
                new Ingredient("Garlic", 2, "cloves"),
                new Ingredient("Olive Oil", 2, "tablespoons"),
                new Ingredient("Salt", 1, "teaspoon"),
                new Ingredient("Black Pepper", 0.5, "teaspoon"),
                new Ingredient("Parmesan Cheese", 50, "grams")
            ), List.of(
                "Cook spaghetti according to package instructions until al dente. Drain and set aside.",
                "In a large skillet, heat olive oil over medium heat. Add chopped onion and minced garlic, sauté until translucent.",
                "Add ground beef to the skillet. Cook until browned, breaking it apart with a spoon.",
                "Pour in tomato sauce. Season with salt and black pepper. Simmer for 15-20 minutes.",
                "Serve sauce over cooked spaghetti. Garnish with grated Parmesan cheese."
            ), List.of(
                new Image("/images/spaghetti_01.png", "A plate of delicious Spaghetti Bolognese."),
                new Image("/images/spaghetti_02.png", "Ingredients for Spaghetti Bolognese."),
                new Image("/images/spaghetti_03.png", "Cooking Spaghetti Bolognese in a pan."),
                new Image("/images/spaghetti_04.png", "Final presentation of Spaghetti Bolognese."),
                new Image("/images/spaghetti_05.png", "Close-up of Spaghetti Bolognese with Parmesan cheese.")
            )),
            new Recipe("Chicken Curry", "A flavorful chicken curry with aromatic spices.", List.of(
                new Ingredient("Chicken", 500, "grams"),
                new Ingredient("Coconut Milk", 400, "ml"),
                new Ingredient("Curry Powder", 2, "tablespoons"),
                new Ingredient("Onion", 1, "medium"),
                new Ingredient("Garlic", 3, "cloves"),
                new Ingredient("Ginger", 1, "inch piece"),
                new Ingredient("Vegetable Oil", 2, "tablespoons"),
                new Ingredient("Salt", 1, "teaspoon"),
                new Ingredient("Cilantro", 10, "grams")
            ), List.of(
                "Heat vegetable oil in a large pot over medium heat. Add chopped onion, minced garlic, and grated ginger. Sauté until fragrant.",
                "Add chicken pieces to the pot. Cook until browned on all sides.",
                "Stir in curry powder and cook for another minute.",
                "Pour in coconut milk and bring to a simmer. Season with salt.",
                "Cover and cook for 20-25 minutes until chicken is cooked through.",
                "Garnish with chopped cilantro before serving."
            ), List.of(
                new Image("/images/chicken_curry_01.png", "A bowl of delicious Chicken Curry."),
                new Image("/images/chicken_curry_02.png", "Ingredients for Chicken Curry."),
                new Image("/images/chicken_curry_03.png", "Cooking Chicken Curry in a pot."),
                new Image("/images/chicken_curry_04.png", "Final presentation of Chicken Curry."),
                new Image("/images/chicken_curry_05.png", "Close-up of Chicken Curry with rice.")
            )),
            new Recipe("Pizza Margherita", "A simple and classic pizza topped with fresh tomatoes, mozzarella, and basil.", List.of(
                new Ingredient("Pizza Dough", 1, "base"),
                new Ingredient("Tomato Sauce", 150, "ml"),
                new Ingredient("Fresh Mozzarella", 200, "grams"),
                new Ingredient("Fresh Basil Leaves", 10, "grams"),
                new Ingredient("Olive Oil", 2, "tablespoons"),
                new Ingredient("Salt", 0.5, "teaspoon")
            ), List.of(
                "Preheat oven to 475°F (245°C).",
                "Roll out pizza dough on a floured surface to desired thickness.",
                "Spread tomato sauce evenly over the dough.",
                "Tear fresh mozzarella into pieces and distribute over the sauce.",
                "Bake in preheated oven for 10-12 minutes until crust is golden and cheese is bubbly.",
                "Remove from oven and top with fresh basil leaves and a drizzle of olive oil."
            ), List.of(
                new Image("/images/pizza_margherita_01.png", "A freshly baked Pizza Margherita."),
                new Image("/images/pizza_margherita_02.png", "Ingredients for Pizza Margherita."),
                new Image("/images/pizza_margherita_03.png", "Preparing Pizza Margherita.")
            ))
        );
    }
    
    public List<Recipe> getAllRecipes() {
        return recipes;
    }
    
    
    
    
}
