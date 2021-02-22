import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class WorldActions {
    private final String[] seasons = {"hiver","printemps","été","automne"};
    private WorldData data;

    public WorldActions(WorldData data) {
        this.data = data;
    }

    public boolean iterateSeasons()
     {
         for(int i = 0; i < seasons.length; i++)
         {
             World.waitForEnter("\nAppuyez sur entrée pour passer à la saison suivante");
             this.callEvent(seasons[i]);
             double oldGlobalSatisfaction = this.data.getGlobalSatisfaction();
             this.data.calculateGlobalPopulationWithUpdate();
             this.data.calculateGlobalSatisfactionWithUpdate();
             System.out.println("La satisfaction globale est passée de " + oldGlobalSatisfaction + " à " + this.data.getGlobalSatisfaction());
             if(this.data.getGlobalSatisfaction() < 50)
             {
                 System.out.println("La satisfaction a trop descendu, vous avez perdu");
                 return false;
             }
         }
         World.waitForEnter("\nAppuyez sur entrée pour finir l'année");
         return true;
     }

     public int iterateYears()
     {
         int i = 1;
         while(iterateSeasons())
         {
                this.endYear();
                i++;
         }
         return i;
     }

     public void callEvent(String season)
     {
         Random random = new Random();
         int randomIndex = random.nextInt(this.data.getEvents().size());
         System.out.println("Un nouvel évènement est arrivé ! \n");
         this.data.getEvents().get(randomIndex).displayChoices();
         System.out.println("\nVeuillez entrer le numéro de votre choix\n");
         EventChoice eventChoice = this.data.getEvents().get(randomIndex).choose();
         this.applyChoiceEffects(eventChoice);
     }

     public void endYear()
     {
        System.out.println("L'année est finie, vous allez devoir prendre des décisions !");
         this.bribe();
         this.foodMarket();
         this.yearReview();
         this.updatePopulation();
     }

    public void bribe() {
        System.out.println("Souhaitez vous donner un pot de vin à une faction ?");
        System.out.println("Pour rappel, si vous payez 15$ par partisan d'une faction, la satisfaction de cette faction augmentera de 10%");
        System.out.println("Vous disposez actuellement de " + data.getTreasury() + "$");
        int i = 1;
        for(Faction faction : this.data.getFactionsList())
        {
            System.out.print("\n" + i + ") " + faction.getName());
            System.out.print(" partisans : " + faction.getTotalPartisans() + " satisfaction : " + faction.getSatisfaction());
            System.out.println("\n\tPrix du pot de vin : " + 15*faction.getTotalPartisans());
            i++;
        }
        System.out.println(i + ") Ne pas faire de pot de vin");
        if(this.bribeChoice())
        {
            this.bribe();
        }

    }

    public boolean bribeChoice()
    {
        int choice = 0;
        ArrayList<Faction> factionsList = this.data.getFactionsList();
        Scanner input = new Scanner(System.in);
        try
        {
            choice = input.nextInt();
        }catch (InputMismatchException exception)
        {
            System.out.println("Je n'ai pas compris, veuillez réessayer.");
            this.bribeChoice();
        }
        choice --;
        if(choice < factionsList.size() && choice > 0)
        {
            this.bribeFaction(factionsList.get(choice));
        }
        else if(choice == factionsList.size())
        {
            System.out.println("Vous avez choisi de ne pas faire de pot de vin.");
            System.out.println("Passons à la suite");
            return false;
        }
        else
        {
            System.out.println("Mauvais chiffre, veuillez réessayer");
            this.bribeChoice();
        }
        return true;
    }

    public void bribeFaction(Faction faction)
    {
        System.out.println("Vous avez choisi de faire un pot de vin à la faction " + faction.getName());
        int oldSatisfaction = faction.getSatisfaction();
        double newSatisfactionDouble = oldSatisfaction * 1.1;
        int newSatisfaction = (int) newSatisfactionDouble;
        faction.setSatisfaction(newSatisfaction);
        System.out.println("La nouvelle satisfaction de " + faction.getName() + " est de " + faction.getSatisfaction());
    }

    public void yearReview() {

    }

    public void foodMarket() {

    }

    public void applyChoiceEffects(EventChoice eventChoice)
    {
        eventChoice.applyEffects(this.data);
    }

    public void updatePopulation() {
        this.data.setGlobalPopulation(0);
    }






}
