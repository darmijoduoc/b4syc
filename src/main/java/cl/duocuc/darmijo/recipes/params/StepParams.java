package cl.duocuc.darmijo.recipes.params;

import lombok.Data;

@Data
public class StepParams {
    
    private String description;
    
    public StepParams() {
    }
    
    public StepParams(String description) {
        this.description = description;
    }
}
