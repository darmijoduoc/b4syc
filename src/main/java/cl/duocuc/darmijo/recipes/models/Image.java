package cl.duocuc.darmijo.recipes.models;

public class Image {
    private final String url;
    private final String description;
    
    public Image(String url, String description) {
        this.url = url;
        this.description = description;
    }
    
    public String getUrl() {
        return url;
    }
    
    public String getDescription() {
        return description;
    }
}
