import java.util.ArrayList;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LoadJSON {
    private String fileName;
    private WorldData data;
    private JSONParser parser;

    public LoadJSON(String fileName, WorldData data) {
        this.fileName = fileName;
        this.data = data;
        this.parser = new JSONParser();
    }

    public void extractEvents(JSONArray eventsArray, ArrayList<Event> eventsList)
    {
        for(Object eventObject: eventsArray)
        {
            JSONObject eventJSONObject = (JSONObject) eventObject;
            Event newEvent = new Event((String)eventJSONObject.get("name"));
            this.extractChoices(eventJSONObject, newEvent);
            eventsList.add(newEvent);
        }
    }
    public void extractChoices(JSONObject eventObject, Event event)
    {
        JSONArray choicesArray = (JSONArray) eventObject.get("choices");
        for(Object choiceObject: choicesArray)
        {
            JSONObject choiceJSONObject = (JSONObject) choiceObject;
            Choice newChoice = new Choice((String)choiceJSONObject.get("choice"));
            if(choiceJSONObject.get("relatedEvents") != null)
            {
                JSONArray relatedEvents = (JSONArray) choiceJSONObject.get("relatedEvents");
                this.extractEvents(relatedEvents,newChoice.getRelatedEvents());
            }
            this.extractEffects(choiceJSONObject, newChoice);
            event.getChoices().add(newChoice);
        }

    }
    public void extractEffects(JSONObject choiceObject, Choice choice)
    {
        JSONArray effectsArray = (JSONArray) choiceObject.get("effects");
        for(Object effectObject : effectsArray)
        {
            JSONObject effectJSONObject = (JSONObject) effectObject;
            JSONObject actionJSONObject = (JSONObject) effectJSONObject.get(0);
            extractEffectsActions(actionJSONObject);
        }
    }
    public void extractEffectsActions(JSONObject actionsObject)
    {
        Iterator actionsIterator = actionsObject.keySet().iterator();
        ArrayList<Effect> effectList = new ArrayList<Effect>();
        String affectedType = actionsObject.toString();
        System.out.println(affectedType);
        while(actionsIterator.hasNext())
        {
            String affectedName = (String) actionsIterator.next();
            Effect effect = new Effect(affectedType,affectedName,(int) actionsObject.get(actionsIterator.next()));
            effectList.add(effect);
        }
    }
    public void extractFactions(JSONObject jsonFile)
    {
        ArrayList<Faction> allFactions = new ArrayList<Faction>();
        this.data.setAllFactions(allFactions);
        this.data.calculateGlobalPopulationWithUpdate();
        this.data.calculateGlobalSatisfactionWithUpdate();
    }
    public void extractData(JSONObject jsonFile)
    {
        this.data.setAgriculturePercentage(0);
        this.data.setDifficulty("");
        this.data.setFoodUnits(0);
        this.data.setIndustryPercentage(0);
        this.data.setGlobalPopulation(0);
        this.data.setTreasury(0);
    }
    public void extractAll()
    {
        try(FileReader reader = new FileReader(this.fileName)) {
            JSONObject jsonFile = (JSONObject) parser.parse(reader);
            JSONArray eventsArray = (JSONArray) jsonFile.get("events");
            this.extractFactions(jsonFile);
            this.extractData(jsonFile);
            this.extractEvents(eventsArray, this.data.getEvents());
        }
        catch (FileNotFoundException e) {
            System.out.println("The file could not be found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
