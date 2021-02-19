public class World {
    private WorldData data;
    private WorldActions actions;
    private int turnNumber;
    private String scenarioName;
    public World() {
    }
    public void createData()
    {

    }

    public void createDataWithJSON()
    {
        LoadJSON jsonLoader = new LoadJSON(scenarioName, this.data);
        jsonLoader.extractEvents();
        jsonLoader.extractChoices();
        jsonLoader.extractEffects();
        jsonLoader.extractFactions();
        jsonLoader.extractData();
    }

    public void executeActions()
    {
        this.actions.iterateYears(this.data);
    }
    public void getScenarios()
    {
        //read the name of the files in scenario folder
    }
    public WorldData getData() {
        return this.data;
    }

    public void setData(WorldData data) {
        this.data = data;
    }

    public WorldActions getActions() {
        return actions;
    }

    public void setActions(WorldActions actions) {
        this.actions = actions;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public void setTurnNumber(int turnNumber) {
        this.turnNumber = turnNumber;
    }
}
