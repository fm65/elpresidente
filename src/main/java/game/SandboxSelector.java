package game;

import java.util.Random;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SandboxSelector {
    public String selectSandbox()
    {
        File sandboxDirectory = new File("./sandbox");
        String[] fileNamesArray = sandboxDirectory.list();
        this.listSandbox(fileNamesArray);
        Random random = new Random();
        int lowerBound = 1;
        int upperBound = 4;
        int fileIndex = random.nextInt(upperBound-lowerBound) + lowerBound;
        //int fileIndex = 1;
        System.out.println("Random generated " + fileIndex + ": ");
        if(fileIndex <= fileNamesArray.length && fileIndex > 0)
        {
            this.displaySandboxNameStory(fileNamesArray[fileIndex-1]);
            return "sandbox/" + fileNamesArray[fileIndex-1];
        }
        else if(fileIndex == fileNamesArray.length + 1 )
        {
            System.out.print("Revenir au menu précédent");
            return null;
        }
        else
        {
            System.out.println("Ce chiffre n'est pas bon, merci de réessayer.");
            this.selectSandbox();
        }
        return null;
    }
    public void listSandbox(String[] fileNamesArray)
    {
        int i = 1;
        for(String fileName : fileNamesArray)
        {
            System.out.println("\t\t===========");
            System.out.println(i + ") ");
            this.displaySandboxNameStory(fileName);
            i++;
        }
        System.out.println("\t\t===========");
        System.out.println(i + ") Revenir au menu");
    }

    public void displaySandboxNameStory(String fileName)
    {
        try(FileReader reader = new FileReader("sandbox/"+fileName)) {
            JSONParser parser = new JSONParser();
            JSONObject jsonFile = (JSONObject) parser.parse(reader);
            JSONObject startParametersJSONObject = (JSONObject) jsonFile.get("gameStartParameters");


            System.out.println(jsonFile.get("name"));
            System.out.println(jsonFile.get("story"));
            // System.out.println("Difficulté : " + startParametersJSONObject.keySet().iterator().next());
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
