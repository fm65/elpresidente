package game;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Event {
    private ArrayList<EventChoice> eventChoices;
    private String description;

    public Event(String description) {
        this.description = description;
        this.eventChoices = new ArrayList<EventChoice>();
    }

    public void displayChoices()
    {
        System.out.println(this.description);
        System.out.println("\nQu'allez vous faire?\n");
        for(int i = 0; i < this.eventChoices.size(); i++)
        {
            System.out.println(i+1 + ") " + this.eventChoices.get(i).getDescription());
        }
    }

    public EventChoice choose()
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
        if(choiceIndex <= this.eventChoices.size() && choiceIndex > 0)
        {
            return this.eventChoices.get(choiceIndex - 1);
        }
        else
        {
            System.out.println("Ce chiffre n'est pas bon, veuillez entrer votre choix à  nouveau.");
            this.choose();
        }
        //if related events, then add the related events to the worldata event list
        return null;
    }
    public ArrayList<EventChoice> getEventChoices() {
        return eventChoices;
    }

    public void setEventChoices(ArrayList<EventChoice> eventChoices) {
        this.eventChoices = eventChoices;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
