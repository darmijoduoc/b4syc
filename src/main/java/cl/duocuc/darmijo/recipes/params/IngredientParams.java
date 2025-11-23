package cl.duocuc.darmijo.recipes.params;

import lombok.Data;

@Data
public class IngredientParams {
    private String name;
    private double amount;
    private String unit;
    
    public IngredientParams() {
    }
    
    public IngredientParams(String name, double amount, String unit) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
    }
}
