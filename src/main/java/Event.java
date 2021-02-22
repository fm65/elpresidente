import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Event {
    private ArrayList<Choice> choices;
    private String description;

    public Event(String description) {
        this.description = description;
        this.choices = new ArrayList<Choice>();
    }

    public void displayChoices()
    {
        System.out.println(this.description);
        System.out.println("\nQu'allez vous faire?\n");
        for(int i = 0; i < this.choices.size(); i++)
        {
            System.out.println(i+1 + ") " + this.choices.get(i).getDescription());
        }
    }

    public Choice choose()
    {
        Scanner input = new Scanner(System.in);
        int choiceIndex = 0;
        try {
            choiceIndex  = input.nextInt();
        }catch(InputMismatchException exception)
        {
            System.out.println("Je n'ai pas compris, veuillez réessayer.");
            this.choose();
        }
        if(choiceIndex <= this.choices.size() && choiceIndex > 0)
        {
            return this.choices.get(choiceIndex - 1);
        }
        else
        {
            System.out.println("Ce chiffre n'est pas bon, veuillez entrer votre choix à  nouveau.");
            this.choose();
        }
        //if related events, then add the related events to the worldata event list
        return null;
    }
    public ArrayList<Choice> getChoices() {
        return choices;
    }

    public void setChoices(ArrayList<Choice> choices) {
        this.choices = choices;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
