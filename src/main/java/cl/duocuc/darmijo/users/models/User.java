package cl.duocuc.darmijo.users.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.f4b6a3.ulid.Ulid;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Table(name = "exp1_user")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(unique = true)
    public String ulid;
    public String displayName;
    @Column(unique = true)
    public String email;
    public String roles;
    
    @JsonIgnore
    public String hashedPassword;
    
    public long createdAt() {
        return Ulid.from(ulid).getTime();
    }
}
