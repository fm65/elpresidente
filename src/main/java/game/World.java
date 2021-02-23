package game;

import java.util.InputMismatchException;
import java.util.Scanner;

public class World {
    public static final WorldData data = new WorldData();
    private WorldActions actions;
    private int yearNumber;
    private String scenarioName;
    private String filePath;
    public World() {
        this.actions = new WorldActions();
    }
    public void createData()
    {

    }

    public void createDataWithJSON(String filePath)
    {
        LoadJSON jsonLoader = new LoadJSON(filePath);
        jsonLoader.extractAll();
    }

    public void executeActions()
    {
        this.yearNumber = this.actions.iterateYears();
        System.out.println("Bravo, vous avez tenu " + yearNumber + " années");
    }
    public void selectDifficulty()
    {
        System.out.println("Quelle difficulté souhaitez vous ?");
        System.out.println("1) Facile");
        System.out.println("2) Normal");
        System.out.println("3) Difficile");
        System.out.println("\nVeuillez entrer le chiffre qui correspond à la difficulté que vous désirez");
        int choice = World.scanInteger();
        switch (choice){
            case 1:
                World.data.setDifficultyFactor(0.5);
                break;
            case 2:
                World.data.setDifficultyFactor(1);
                break;
            case 3:
                World.data.setDifficultyFactor(2);
                break;
            default:
                System.out.println("Mauvais chiffre, veuillez réessayer.");
                this.selectDifficulty();
                break;
        }

    }
    public static void waitForEnter(String message)
    {
        System.out.println(message);
        try{System.in.read();}catch(Exception e){	e.printStackTrace();}
    }

    public static int scanInteger()
    {
        Scanner input = new Scanner(System.in);
        int scannedInt = 0;
        try
        {
            scannedInt = input.nextInt();
        }catch (InputMismatchException exception)
        {
            System.out.println("Je n'ai pas compris, veuillez réessayer.");
            scannedInt = scanInteger();
        }
        return scannedInt;
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


}
