package cl.duocuc.darmijo.recipes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.f4b6a3.ulid.Ulid;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "exp1_recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(unique = true)
    public String ulid;
    public String name;
    
    @JsonIgnore
    public String hashedPassword;
    
    public long createdAt() {
        return Ulid.from(ulid).getTime();
    }
}
