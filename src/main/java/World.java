import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Scanner;

public class World {
    private WorldData data;
    private WorldActions actions;
    private int turnNumber;
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
        this.actions.iterateYears();
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
}
