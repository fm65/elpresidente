import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import game.Event;
import game.EventChoice;
import game.LoadJSON;
import game.WorldData;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JSONTest {
    private LoadJSON jsonLoader;
    private WorldData data;
    private JSONObject jsonFile;
    private JSONParser parser;
    private JSONObject eventJSONObject;
    private JSONObject choiceJSONObject;
    JSONArray eventsArray;
    @BeforeEach
    protected void setUp ()  {
        this.data = new WorldData();
        this.jsonLoader = new LoadJSON("scenario/attackOnTitans.json");
        this.parser = new JSONParser();
        try(FileReader reader = new FileReader("scenario/attackOnTitans.json")){
            this.jsonFile  = (JSONObject) this.parser.parse(reader);
            this.eventsArray = (JSONArray) jsonFile.get("events");
            this.eventJSONObject = (JSONObject) this.eventsArray.get(0);
            JSONArray choiceArray = (JSONArray) this.eventJSONObject.get("choices");
            this.choiceJSONObject = (JSONObject) choiceArray.get(0);
        }catch (FileNotFoundException e) {
            System.out.println("The file could not be found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    public void testExtractEvents()
    {

        this.jsonLoader.extractEvents(eventsArray,this.data.getEventList());
        assertEquals(18, this.data.getEventList().size());
    }
    @org.junit.jupiter.api.Test
    public void testExtractChoices()
    {
        Event event = new Event("Des monstres géants approchent le royaume... Que faites-vous ?");
        jsonLoader.extractEventChoices(this.eventJSONObject,event);
        assertEquals(3,event.getEventChoices().size());
    }

    @org.junit.jupiter.api.Test
    public void testExtractEffects()
    {
        EventChoice eventChoice = new EventChoice("J'envoie toute la force militaire du pays pour les éradiquer");
        jsonLoader.extractChoiceEffects(this.choiceJSONObject, eventChoice);
        assertEquals(3, eventChoice.getEventChoiceEffectList().size());
    }

    @org.junit.jupiter.api.Test
    public void testExtractFactions()
    {
        JSONObject startParametersJSONObject = (JSONObject) this.jsonFile.get("gameStartParameters");
        JSONObject difficultyParametersJSONObject = (JSONObject) startParametersJSONObject.get(startParametersJSONObject.keySet().iterator().next());
        JSONObject factionsJSONObject = (JSONObject) difficultyParametersJSONObject.get("factions");
        jsonLoader.extractFactions(factionsJSONObject, this.data.getFactionsList());
        assertEquals(8,this.data.getFactionsList().size());
        assertEquals(80, this.data.getGlobalPopulation());
        assertEquals(63.75,this.data.getGlobalSatisfaction());
    }

    @org.junit.jupiter.api.Test
    public void testExtractStartParameters()
    {
        JSONObject startParametersJSONObject = (JSONObject) this.jsonFile.get("gameStartParameters");
        jsonLoader.extractStartParameters(startParametersJSONObject);
        assertEquals(this.data.getAgriculturePercentage(),40);
        assertEquals(this.data.getDifficulty(),"NORMAL");
        assertEquals(this.data.getIndustryPercentage(),35);
        assertEquals(this.data.getFoodUnits(),500);
    }
}
