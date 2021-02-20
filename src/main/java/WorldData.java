import java.util.ArrayList;
import java.util.List;

public class WorldData {
    private ArrayList<Faction> allFactions;
    private double globalSatisfaction;
    private int industryPercentage;
    private int agriculturePercentage;
    private int treasury;
    private int foodUnits;
    private int globalPopulation;
    private String difficulty;
    private ArrayList<Event> events;

    public WorldData() {
        this.allFactions = new ArrayList<Faction>();
        this.events = new ArrayList<Event>();

    }
    public void calculateGlobalSatisfactionWithUpdate()
    {
        double totalScore = 0.0;
        for(Faction faction : this.allFactions)
        {
            totalScore += faction.getSatisfaction() * faction.getTotalSupporter();
        }
        this.globalSatisfaction = totalScore / globalPopulation;
    }

    public void calculateGlobalPopulationWithUpdate()
    {
        int population = 0;
        for(Faction faction : this.allFactions)
        {
            population += faction.getTotalSupporter();
        }
        this.globalPopulation = population;
    }

    public ArrayList<Faction> getAllFactions() {
        return allFactions;
    }

    public void setAllFactions(ArrayList<Faction> allFactions) {
        this.allFactions = allFactions;
    }

    public double getGlobalSatisfaction() {
        return globalSatisfaction;
    }

    public void setGlobalSatisfaction(int globalSatisfaction) {
        this.globalSatisfaction = globalSatisfaction;
    }

    public int getIndustryPercentage() {
        return industryPercentage;
    }

    public void setIndustryPercentage(int industryPercentage) {
        this.industryPercentage = industryPercentage;
    }

    public int getAgriculturePercentage() {
        return agriculturePercentage;
    }

    public void setAgriculturePercentage(int agriculturePercentage) {
        this.agriculturePercentage = agriculturePercentage;
    }

    public int getTreasury() {
        return treasury;
    }

    public void setTreasury(int treasury) {
        this.treasury = treasury;
    }

    public int getFoodUnits() {
        return foodUnits;
    }

    public void setFoodUnits(int foodUnits) {
        this.foodUnits = foodUnits;
    }

    public int getGlobalPopulation() {
        return globalPopulation;
    }

    public void setGlobalPopulation(int globalPopulation) {
        this.globalPopulation = globalPopulation;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }
}
