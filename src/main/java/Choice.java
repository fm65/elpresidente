import java.util.ArrayList;

public class Choice {
    private ArrayList<Effect> effects;
    private String description;

    public Choice(String description) {
        this.description = description;
    }

    public ArrayList<Effect> getEffects() {
        return effects;
    }

    public void setEffects(ArrayList<Effect> effects) {
        this.effects = effects;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
