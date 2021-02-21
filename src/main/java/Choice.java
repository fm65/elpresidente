import java.util.ArrayList;

public class Choice {
    private ArrayList<Effect> effects;
    private ArrayList<Event> relatedEvents;
    private String description;

    public Choice(String description) {
        this.description = description;
        this.effects = new ArrayList<Effect>();
        this.relatedEvents = new ArrayList<Event>();
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

    public ArrayList<Event> getRelatedEvents() {
        return relatedEvents;
    }

    public void setRelatedEvents(ArrayList<Event> relatedEvents) {
        this.relatedEvents = relatedEvents;
    }
}
