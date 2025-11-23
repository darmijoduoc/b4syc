package cl.duocuc.darmijo.recipes.services;

import cl.duocuc.darmijo.recipes.models.*;
import cl.duocuc.darmijo.recipes.params.*;
import cl.duocuc.darmijo.recipes.repostiory.*;
import com.github.f4b6a3.ulid.UlidCreator;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InitService {
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final StepRepository stepRepository;
    private final ImageRepository imageRepository;
    private final CommentRepository commentRepository;
    
    private final List<RecipeParams> initialRecipes = List.of(
        new RecipeParams(
            "Espaguetis a la Boloñesa",
            "Un clásico plato de pasta italiana con una rica salsa de carne.",
            List.of(
                new IngredientParams("Espaguetis", 200, "gramos"),
                new IngredientParams("Carne molida", 300, "gramos"),
                new IngredientParams("Salsa de tomate", 400, "ml"),
                new IngredientParams("Cebolla", 1, "mediana"),
                new IngredientParams("Ajo", 2, "dientes"),
                new IngredientParams("Aceite de oliva", 2, "cucharadas"),
                new IngredientParams("Sal", 1, "cucharadita"),
                new IngredientParams("Pimienta negra", 0.5, "cucharadita"),
                new IngredientParams("Queso parmesano", 50, "gramos")
            ),
            List.of(
                new StepParams("Cocina los espaguetis según las instrucciones del paquete hasta que estén al dente. Escurre y reserva."),
                new StepParams("En una sartén grande, calienta el aceite de oliva a fuego medio. Agrega la cebolla picada y el ajo triturado, sofríe hasta que estén transparentes."),
                new StepParams("Agrega la carne molida a la sartén y cocina hasta que esté dorada, desmenuzándola con una cuchara."),
                new StepParams("Vierte la salsa de tomate. Sazona con sal y pimienta negra. Cocina a fuego lento durante 15-20 minutos."),
                new StepParams("Sirve la salsa sobre los espaguetis cocidos. Decora con queso parmesano rallado.")
            ),
            List.of(
                new ImageParams("/images/spaghetti_01.png", "Un plato de deliciosos espaguetis a la boloñesa."),
                new ImageParams("/images/spaghetti_02.png", "Ingredientes para preparar espaguetis a la boloñesa."),
                new ImageParams("/images/spaghetti_03.png", "Cocinando espaguetis a la boloñesa en una sartén."),
                new ImageParams("/images/spaghetti_04.png", "Presentación final de los espaguetis a la boloñesa."),
                new ImageParams("/images/spaghetti_05.png", "Primer plano de los espaguetis a la boloñesa con queso parmesano.")
            ),
            List.of(
                new CommentParams("Juan", "¡Deliciosa receta! A mi familia le encantó.",5),
                new CommentParams("María", "Muy fácil de seguir y el resultado fue excelente.", 4),
                new CommentParams("Carlos", "Perfecta para una cena rápida entre semana.", 5)
            ),
            "00:45", "Italia"
        ),
        new RecipeParams(
            "Pollo al Curry",
            "Un sabroso curry de pollo con especias aromáticas.",
            List.of(
                new IngredientParams("Pollo", 500, "gramos"),
                new IngredientParams("Leche de coco", 400, "ml"),
                new IngredientParams("Polvo de curry", 2, "cucharadas"),
                new IngredientParams("Cebolla", 1, "mediana"),
                new IngredientParams("Ajo", 3, "dientes"),
                new IngredientParams("Jengibre", 1, "trozo de 2 cm"),
                new IngredientParams("Aceite vegetal", 2, "cucharadas"),
                new IngredientParams("Sal", 1, "cucharadita")
            ),
            List.of(
                new StepParams("Corta el pollo en trozos medianos."),
                new StepParams("En una sartén grande, calienta el aceite vegetal a fuego medio. Agrega la cebolla picada, el ajo triturado y el jengibre rallado. Sofríe hasta que estén dorados."),
                new StepParams("Agrega el polvo de curry y cocina por un minuto para liberar los aromas."),
                new StepParams("Incorpora el pollo y cocina hasta que esté sellado por todos lados."),
                new StepParams("Vierte la leche de coco y sazona con sal. Cocina a fuego lento durante 20-25 minutos hasta que el pollo esté tierno y la salsa haya espesado.")
            ),
            List.of(
                new ImageParams("/images/chicken_curry_01.png", "Un plato de sabroso pollo al curry."),
                new ImageParams("/images/chicken_curry_02.png", "Ingredientes frescos para preparar pollo al curry."),
                new ImageParams("/images/chicken_curry_03.png", "Cocinando pollo al curry en una sartén."),
                new ImageParams("/images/chicken_curry_04.png", "Presentación final del pollo al curry."),
                new ImageParams("/images/chicken_curry_05.png", "Primer plano del pollo al curry con arroz.")
            ),
            List.of(
                new CommentParams("Ana", "El mejor pollo al curry que he probado.", 5),
                new CommentParams("Luis", "Sencillo y delicioso, ¡gracias por la receta!", 4),
                new CommentParams("Sofía", "Perfecto para una comida reconfortante.", 4)
            ),
            "00:40", "India"
        ),
        new RecipeParams(
            "Ensalada César",
            "Una clásica ensalada César con aderezo cremoso y crujientes crotones.",
            List.of(
                new IngredientParams("Lechuga romana", 1, "unidad"),
                new IngredientParams("Pechuga de pollo", 200, "gramos"),
                new IngredientParams("Queso parmesano", 50, "gramos"),
                new IngredientParams("Pan", 2, "rebanadas"),
                new IngredientParams("Aceite de oliva", 2, "cucharadas"),
                new IngredientParams("Ajo", 1, "diente"),
                new IngredientParams("Yema de huevo", 1, "unidad"),
                new IngredientParams("Jugo de limón", 2, "cucharadas"),
                new IngredientParams("Mostaza Dijon", 1, "cucharadita"),
                new IngredientParams("Salsa Worcestershire", 1, "cucharadita"),
                new IngredientParams("Sal y pimienta", 0, "al gusto")
            ),
            List.of(
                new StepParams("Corta la lechuga romana en trozos medianos y lávala bien."),
                new StepParams("Cocina la pechuga de pollo a la parrilla o sartén hasta que esté bien cocida. Luego córtala en tiras."),
                new StepParams("Para los crotones, corta el pan en cubos pequeños y saltéalos en una sartén con aceite de oliva y ajo picado hasta que estén dorados y crujientes."),
                new StepParams("Prepara el aderezo mezclando la yema de huevo, jugo de limón, mostaza Dijon, salsa Worcestershire, sal y pimienta. Agrega aceite de oliva poco a poco hasta obtener una consistencia cremosa."),
                new StepParams("En un bol grande, mezcla la lechuga, el pollo, los crotones y el queso parmesano rallado. Vierte el aderezo sobre la ensalada y mezcla bien antes de servir.")
            ),
            List.of(
                new ImageParams("/images/caesar_salad_01.png", "Un plato fresco de ensalada César."),
                new ImageParams("/images/caesar_salad_02.png", "Ingredientes para preparar ensalada César."),
                new ImageParams("/images/caesar_salad_03.png", "Preparando los crotones para la ensalada César.")
            ),
            List.of(
                new CommentParams("Pedro", "La ensalada César más deliciosa que he probado.", 5),
                new CommentParams("Lucía", "Fresca y perfecta para el verano.", 4),
                new CommentParams("Marta", "Me encantó la combinación de sabores y texturas.", 5)
            ),
            "00:30", "Italia"
        )
    );
    
    @PostConstruct
    private void init() {
        initialRecipes.forEach(this::createRecipeUsingRecipeParams);
    }
    
    private void createRecipeUsingRecipeParams(RecipeParams recipeParams) {
        Recipe recipe = new Recipe();
        recipe.setUlid(UlidCreator.getUlid().toString());
        recipe.setTitle(recipeParams.getTitle());
        recipe.setDescription(recipeParams.getDescription());
        recipe.setDuration(recipeParams.getDuration());
        recipe.setOrigin(recipeParams.getOrigin());
        recipeRepository.save(recipe);
        
        recipeParams.getIngredients().forEach(params -> {
            Ingredient ingredient = new Ingredient();
            ingredient.setUlid(UlidCreator.getUlid().toString());
            ingredient.setRecipeId(recipe.getId());
            ingredient.setName(params.getName());
            ingredient.setAmount(params.getAmount());
            ingredient.setUnit(params.getUnit());
            ingredientRepository.save(ingredient);
        });
        
        recipeParams.getSteps().forEach(params -> {
            Step step = new Step();
            step.setUlid(UlidCreator.getUlid().toString());
            step.setRecipeId(recipe.getId());
            step.setDescription(params.getDescription());
            stepRepository.save(step);
        });
        
        recipeParams.getImages().forEach(params -> {
            Image image = new Image();
            image.setUlid(UlidCreator.getUlid().toString());
            image.setRecipeId(recipe.getId());
            image.setUrl(params.getUrl());
            image.setDescription(params.getDescription());
            imageRepository.save(image);
        });
        
        recipeParams.getComments().forEach(params -> {
            Comment comment = new Comment();
            comment.setUlid(UlidCreator.getUlid().toString());
            comment.setRecipeId(recipe.getId());
            comment.setAuthor(params.getAuthor());
            comment.setText(params.getText());
            comment.setRating(params.getRating());
            commentRepository.save(comment);
        });
    }
}
