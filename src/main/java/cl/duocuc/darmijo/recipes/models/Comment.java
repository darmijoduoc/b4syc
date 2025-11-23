package cl.duocuc.darmijo.recipes.models;

import com.github.f4b6a3.ulid.Ulid;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Entity
@Table(name = "exp2_comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String ulid;
    private int recipeId;
    private String author;
    private String text;
    private int rating;
    
    private long getCreatedAt() {
        return Ulid.from(ulid).getTime();
    }
    
    public String getFormattedCreatedAt() {
        long timestamp = getCreatedAt();
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return zdt.format(formatter);
    }
}
