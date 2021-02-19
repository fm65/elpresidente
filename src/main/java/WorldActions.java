import java.util.List;

public class WorldActions {
    private final String[] seasons = {"hiver","printemps","été","automne"};
    private WorldData data;


    public void iterateYears(WorldData data){

    }
     private boolean iterateSeasons()
     {
         for(int i = 0; i < seasons.length; i++)
         {
             this.callEvent(seasons[i]);
         }
         return true;
     }

     private boolean iterateYears()
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

     private void callEvent(String season)
     {
         //call a random event, coherent with the current season
     }

     private void endYear()
     {
         this.bribe();
         this.foodMarket();
         this.yearReview();
         this.updatePopulation();
     }

    private void bribe() {

    }

    private void yearReview() {

    }

    private void foodMarket() {

    }

    private void updatePopulation() {
        this.data.setGlobalPopulation(0);
    }






}
