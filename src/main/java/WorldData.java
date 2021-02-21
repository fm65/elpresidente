import java.util.ArrayList;

public class WorldData {
    private ArrayList<Faction> factionsList;
    private double globalSatisfaction;
    private int industryPercentage;
    private int agriculturePercentage;
    private int treasury;
    private int foodUnits;
    private int globalPopulation;
    private String difficulty;
    private ArrayList<Event> events;

    public WorldData() {
        this.factionsList = new ArrayList<Faction>();
        this.events = new ArrayList<Event>();

    }
    public void calculateGlobalSatisfactionWithUpdate()
    {
        double totalScore = 0.0;
        for(Faction faction : this.factionsList)
        {
            totalScore += faction.getSatisfaction() * faction.getTotalPartisans();
        }
        this.globalSatisfaction = totalScore / this.globalPopulation;
    }

    public void calculateGlobalPopulationWithUpdate()
    {
        int population = 0;
        for(Faction faction : this.factionsList)
        {
            population += faction.getTotalPartisans();
        }
        this.globalPopulation = population;
    }

    public ArrayList<Faction> getFactionsList() {
        return factionsList;
    }

    public void setFactionsList(ArrayList<Faction> allFactions) {
        this.factionsList = allFactions;
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
