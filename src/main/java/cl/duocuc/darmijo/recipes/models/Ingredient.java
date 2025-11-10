package cl.duocuc.darmijo.recipes.models;

public class Ingredient {
    private final String name;
    private final double amount;
    private final String unit;
    public Ingredient(String name, double amount, String unit) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
    }
    
    public String getName() {
        return name;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public String getUnit() {
        return unit;
    }
    
    public String toString() {
        return amount + " " + unit + " de " + name;
    }
}
