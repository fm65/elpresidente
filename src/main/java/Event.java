import java.util.ArrayList;

public class Event {
    private ArrayList<Choice> choices;
    private String Description;

    public Event(String description) {
        Description = description;
    }

    public void displayChoices()
    {
        for(int i = 0; i < choices.size(); i++)
        {
            System.out.println(choices.get(i).getDescription());
        }
    }

    public void makeChoice()
    {

    }

    public void applyChoiceEffects(Choice choice)
    {

    }

    public ArrayList<Choice> getChoices() {
        return choices;
    }

    public void setChoices(ArrayList<Choice> choices) {
        this.choices = choices;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
