import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ScenarioSelector {


    public ScenarioSelector() {
    }

    public String selectScenario()
    {
        File scenarioDirectory = new File("./scenario");
        String[] fileNamesArray = scenarioDirectory.list();
        this.listScenarios(fileNamesArray);
        System.out.println("\nVeuillez rentrer le numéro du scénario auquel vous voulez jouer\n");
        Scanner input = new Scanner(System.in);
        int fileIndex = 0;
        try {
            fileIndex  = input.nextInt();
        }catch(InputMismatchException exception)
        {
            System.out.println("Je n'ai pas compris, veuillez réessayer.");
            this.selectScenario();
        }

        System.out.println("Vous avez choisi " + fileIndex + ": ");
        if(fileIndex <= fileNamesArray.length && fileIndex > 0)
        {
            this.displayScenarioNameStoryDifficulty(fileNamesArray[fileIndex-1]);
            return "scenario/" + fileNamesArray[fileIndex-1];
        }
        else if(fileIndex == fileNamesArray.length + 1 )
        {
            System.out.print("Revenir au menu précédent");
            return null;
        }
        else
        {
            System.out.println("Ce chiffre n'est pas bon, merci de réessayer.");
            this.selectScenario();
        }
        return null;
    }
    public void listScenarios(String[] fileNamesArray)
    {
        int i = 1;
        for(String fileName : fileNamesArray)
        {
            System.out.println("\t\t===========");
            System.out.println(i + ") ");
            this.displayScenarioNameStoryDifficulty(fileName);
            i++;
        }
        System.out.println("\t\t===========");
        System.out.println(i + ") Revenir au menu");
    }

    public void displayScenarioNameStoryDifficulty(String fileName)
    {
        try(FileReader reader = new FileReader("scenario/"+fileName)) {
            JSONParser parser = new JSONParser();
            JSONObject jsonFile = (JSONObject) parser.parse(reader);
            JSONObject startParametersJSONObject = (JSONObject) jsonFile.get("gameStartParameters");


            System.out.println(jsonFile.get("name"));
            System.out.println(jsonFile.get("story"));
            System.out.println("Difficulté : " + startParametersJSONObject.keySet().iterator().next());
        }
        catch (FileNotFoundException e) {
            System.out.println("Le fichier n'a pas pu être trouvé");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
