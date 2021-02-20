import java.util.ArrayList;

public class LoadJSON {
    private String fileName;
    private WorldData data;

    public LoadJSON(String fileName, WorldData data) {
        this.fileName = fileName;
        this.data = data;
    }

    public void extractEvents()
    {
        ArrayList<Event> events = new ArrayList<Event>();
        this.data.setEvents(events);
    }
    public void extractChoices()
    {
        ArrayList<Choice> choices = new ArrayList<Choice>();
        for(int i = 0;i < this.data.getEvents().size(); i++)
        {
            this.data.getEvents().get(i).setChoices(choices);
        }

    }
    public void extractEffects()
    {
        ArrayList<Effect> effects = new ArrayList<Effect>();
        for(int i = 0;i < this.data.getEvents().size(); i++)
        {
            for(int j = 0; j < this.data.getEvents().get(i).getChoices().size(); j++)
            {
                this.data.getEvents().get(i).getChoices().get(j).setEffects(effects);
            }

        }

    }
    public void extractFactions()
    {
        ArrayList<Faction> allFactions = new ArrayList<Faction>();
        this.data.setAllFactions(allFactions);
        this.data.calculateGlobalPopulationWithUpdate();
        this.data.calculateGlobalSatisfactionWithUpdate();
    }
    public void extractData()
    {
        this.data.setAgriculturePercentage(0);
        this.data.setDifficulty("");
        this.data.setFoodUnits(0);
        this.data.setIndustryPercentage(0);
        this.data.setGlobalPopulation(0);
        this.data.setTreasury(0);
    }
    public void readFile()
    {

    }

}
