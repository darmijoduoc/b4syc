package cl.duocuc.darmijo.recipes.services;

import cl.duocuc.darmijo.recipes.models.Image;
import cl.duocuc.darmijo.recipes.models.Ingredient;
import cl.duocuc.darmijo.recipes.models.Recipe;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RecipeService {
    
    private final List<Recipe> recipes;
    
    public RecipeService() {
        
        this.recipes = List.of(
            new Recipe("Espaguetis a la Boloñesa", "Un clásico plato de pasta italiana con una rica salsa de carne.", List.of(
                new Ingredient("Espaguetis", 200, "gramos"),
                new Ingredient("Carne molida", 300, "gramos"),
                new Ingredient("Salsa de tomate", 400, "ml"),
                new Ingredient("Cebolla", 1, "mediana"),
                new Ingredient("Ajo", 2, "dientes"),
                new Ingredient("Aceite de oliva", 2, "cucharadas"),
                new Ingredient("Sal", 1, "cucharadita"),
                new Ingredient("Pimienta negra", 0.5, "cucharadita"),
                new Ingredient("Queso parmesano", 50, "gramos")
            ), List.of(
                "Cocina los espaguetis según las instrucciones del paquete hasta que estén al dente. Escurre y reserva.",
                "En una sartén grande, calienta el aceite de oliva a fuego medio. Agrega la cebolla picada y el ajo triturado, sofríe hasta que estén transparentes.",
                "Agrega la carne molida a la sartén y cocina hasta que esté dorada, desmenuzándola con una cuchara.",
                "Vierte la salsa de tomate. Sazona con sal y pimienta negra. Cocina a fuego lento durante 15-20 minutos.",
                "Sirve la salsa sobre los espaguetis cocidos. Decora con queso parmesano rallado."
            ), List.of(
                new Image("/images/spaghetti_01.png", "Un plato de deliciosos espaguetis a la boloñesa."),
                new Image("/images/spaghetti_02.png", "Ingredientes para preparar espaguetis a la boloñesa."),
                new Image("/images/spaghetti_03.png", "Cocinando espaguetis a la boloñesa en una sartén."),
                new Image("/images/spaghetti_04.png", "Presentación final de los espaguetis a la boloñesa."),
                new Image("/images/spaghetti_05.png", "Primer plano de los espaguetis a la boloñesa con queso parmesano.")
            )),
            
            new Recipe("Pollo al Curry", "Un sabroso curry de pollo con especias aromáticas.", List.of(
                new Ingredient("Pollo", 500, "gramos"),
                new Ingredient("Leche de coco", 400, "ml"),
                new Ingredient("Polvo de curry", 2, "cucharadas"),
                new Ingredient("Cebolla", 1, "mediana"),
                new Ingredient("Ajo", 3, "dientes"),
                new Ingredient("Jengibre", 1, "trozo de 2 cm"),
                new Ingredient("Aceite vegetal", 2, "cucharadas"),
                new Ingredient("Sal", 1, "cucharadita"),
                new Ingredient("Cilantro fresco", 10, "gramos")
            ), List.of(
                "Calienta el aceite vegetal en una olla grande a fuego medio. Agrega la cebolla picada, el ajo triturado y el jengibre rallado. Sofríe hasta que desprenda aroma.",
                "Añade los trozos de pollo y cocina hasta que estén dorados por todos lados.",
                "Incorpora el polvo de curry y cocina un minuto más.",
                "Vierte la leche de coco y lleva a ebullición. Sazona con sal.",
                "Tapa y cocina durante 20-25 minutos, hasta que el pollo esté bien cocido.",
                "Decorar con cilantro picado antes de servir."
            ), List.of(
                new Image("/images/chicken_curry_01.png", "Un plato de delicioso pollo al curry."),
                new Image("/images/chicken_curry_02.png", "Ingredientes para el pollo al curry."),
                new Image("/images/chicken_curry_03.png", "Cocinando pollo al curry en una olla."),
                new Image("/images/chicken_curry_04.png", "Presentación final del pollo al curry."),
                new Image("/images/chicken_curry_05.png", "Primer plano del pollo al curry con arroz.")
            )),
            
            new Recipe("Pizza Margarita", "Una pizza simple y clásica con tomates frescos, mozzarella y albahaca.", List.of(
                new Ingredient("Masa para pizza", 1, "base"),
                new Ingredient("Salsa de tomate", 150, "ml"),
                new Ingredient("Mozzarella fresca", 200, "gramos"),
                new Ingredient("Hojas de albahaca fresca", 10, "gramos"),
                new Ingredient("Aceite de oliva", 2, "cucharadas"),
                new Ingredient("Sal", 0.5, "cucharadita")
            ), List.of(
                "Precalienta el horno a 245°C (475°F).",
                "Extiende la masa para pizza sobre una superficie enharinada al grosor deseado.",
                "Unta la salsa de tomate de manera uniforme sobre la masa.",
                "Desmenuza la mozzarella y distribúyela sobre la salsa.",
                "Hornea durante 10-15 minutos, hasta que la masa esté dorada y el queso burbujeante.",
                "Saca del horno y añade las hojas de albahaca y un chorrito de aceite de oliva antes de servir."
            ), List.of(
                new Image("/images/pizza_margherita_01.png", "Una pizza Margarita recién horneada."),
                new Image("/images/pizza_margherita_02.png", "Ingredientes para la pizza Margarita."),
                new Image("/images/pizza_margherita_03.png", "Preparación de la pizza Margarita.")
            ))
        );
    }
    
    public List<Recipe> getAllRecipes() {
        return recipes;
    }
    
    public Optional<Recipe> getRecipeById(String id) {
        return recipes.stream()
            .filter(recipe -> Objects.equals(recipe.getUlid(), id))
            .findFirst();
    }
    
}
