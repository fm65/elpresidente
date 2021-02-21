import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;

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
        this.jsonLoader = new LoadJSON("scenario/attackOnTitans.json",data);
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

        this.jsonLoader.extractEvents(eventsArray,this.data.getEvents());
        assertEquals(18, this.data.getEvents().size());
    }
    @org.junit.jupiter.api.Test
    public void testExtractChoices()
    {
        Event event = new Event("Des monstres géants approchent le royaume... Que faites-vous ?");
        jsonLoader.extractChoices(this.eventJSONObject,event);
        assertEquals(3,event.getChoices().size());
    }

    @org.junit.jupiter.api.Test
    public void testExtractEffects()
    {
        Choice choice = new Choice("J'envoie toute la force militaire du pays pour les éradiquer");
        jsonLoader.extractEffects(this.choiceJSONObject,choice);
        assertEquals(3,choice.getEffects().size());
    }

    @org.junit.jupiter.api.Test
    public void testExtractFactions()
    {
        jsonLoader.extractFactions(jsonFile);
        assert(this.data.getAllFactions().size() > 0);
        assertEquals(this.data.getGlobalPopulation(),80);
        assertEquals(this.data.getGlobalSatisfaction(),5100);
    }

    @org.junit.jupiter.api.Test
    public void testExtractData()
    {
        jsonLoader.extractData(jsonFile);
        assertEquals(this.data.getAgriculturePercentage(),40);
        assertEquals(this.data.getDifficulty(),"NORMAL");
        assertEquals(this.data.getIndustryPercentage(),35);
        assertEquals(this.data.getFoodUnits(),500);
    }
}
