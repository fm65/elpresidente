package game;

import java.util.Random;

public class WorldActions {
    private final String[] seasons = {"hiver","printemps","été","automne"};
    private final WorldData data;

    public WorldActions() {
        this.data = World.data;
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
             System.out.println("La satisfaction globale est passée de " + String.format("%.2f",oldGlobalSatisfaction)
                     + " à " + String.format("%.2f",this.data.getGlobalSatisfaction()));
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
         int randomIndex = random.nextInt(this.data.getEventList().size());
         System.out.println("Un nouvel évènement est arrivé ! \n");
         this.data.getEventList().get(randomIndex).displayChoices();
         System.out.println("\nVeuillez entrer le numéro de votre choix\n");
         EventChoice eventChoice = this.data.getEventList().get(randomIndex).choose();
         this.applyChoiceEffects(eventChoice);
     }

    public void applyChoiceEffects(EventChoice eventChoice)
    {
        eventChoice.applyEffects();
    }

     public void endYear()
     {
        System.out.println("L'année est finie, vous allez devoir prendre des décisions !");
        YearEnding yearEnding = new YearEnding();
        yearEnding.bribe();
        yearEnding.foodMarket();
        yearEnding.yearReview();
     }








}
