import java.util.Random;

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
             this.callEvent(seasons[i]);
         }
         return true;
     }

     public boolean iterateYears()
     {
         int i = 1;
         while(iterateSeasons())
         {
             if(i % 4 == 0)
             {
                this.endYear();
             }
         }
         return false;
     }

     public void callEvent(String season)
     {
         Random random = new Random();
         int randomIndex = random.nextInt(this.data.getEvents().size());
         System.out.println("Un nouvel évènement est arrivé ! \n");
         this.data.getEvents().get(randomIndex).displayChoices();
         System.out.println("\nVeuillez entrer le numéro de votre choix\n");
         Choice choice = this.data.getEvents().get(randomIndex).choose();
         this.applyChoiceEffects(choice);
     }

     public void endYear()
     {
         this.bribe();
         this.foodMarket();
         this.yearReview();
         this.updatePopulation();
     }

    public void bribe() {

    }

    public void yearReview() {

    }

    public void foodMarket() {

    }

    public void applyChoiceEffects(Choice choice)
    {
        choice.applyEffects(this.data);
    }

    public void updatePopulation() {
        this.data.setGlobalPopulation(0);
    }






}
