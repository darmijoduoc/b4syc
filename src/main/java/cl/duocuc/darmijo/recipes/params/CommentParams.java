package cl.duocuc.darmijo.recipes.params;

import lombok.Data;

@Data
public class CommentParams {
    private String author;
    private String text;
    private int rating;
    
    public CommentParams() {
    }
    
    public CommentParams(String author, String text, int rating) {
        this.author = author;
        this.text = text;
        this.rating = rating;
    }
}
