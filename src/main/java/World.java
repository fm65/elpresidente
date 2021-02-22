
public class World {
    private WorldData data;
    private WorldActions actions;
    private int yearNumber;
    private String scenarioName;
    private String filePath;
    public World() {
        this.data = new WorldData();
        this.actions = new WorldActions(this.data);
    }
    public void createData()
    {

    }

    public void createDataWithJSON(String filePath)
    {
        LoadJSON jsonLoader = new LoadJSON(filePath, this.data);
        jsonLoader.extractAll();
    }

    public void executeActions()
    {
        this.yearNumber = this.actions.iterateYears();
        System.out.println("Bravo, vous avez tenu " + yearNumber + " années");
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

    public int getYearNumber() {
        return yearNumber;
    }

    public void setYearNumber(int yearNumber) {
        this.yearNumber = yearNumber;
    }

    public String getScenarioName() {
        return scenarioName;
    }

    public void setScenarioName(String scenarioName) {
        this.scenarioName = scenarioName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public static void waitForEnter(String message)
    {
        System.out.println(message);
        try{System.in.read();}catch(Exception e){	e.printStackTrace();}
    }
}
