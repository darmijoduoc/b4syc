package cl.duocuc.darmijo.recipes.params;

import lombok.Data;

@Data
public class ImageParams {
    private String url;
    private String description;
    
    public ImageParams() {
    }
    
    public ImageParams(String url, String description) {
        this.url = url;
        this.description = description;
    }
}
