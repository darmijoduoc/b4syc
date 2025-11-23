package cl.duocuc.darmijo.recipes.models;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "exp2_ingredient")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String ulid;
    private int recipeId;
    private String name;
    private double amount;
    private String unit;
    
    public Ingredient() {}
    
    public Ingredient(String name, double amount, String unit) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
    }
    
    
}
