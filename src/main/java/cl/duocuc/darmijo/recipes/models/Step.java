package cl.duocuc.darmijo.recipes.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "exp2_step")
public class Step {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String ulid;
    private int recipeId;
    private String description;
}
