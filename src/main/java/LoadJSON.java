import java.util.ArrayList;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LoadJSON {
    private String filePath;
    private WorldData data;
    private JSONParser parser;

    public LoadJSON(String filePath, WorldData data) {
        this.filePath = filePath;
        this.data = data;
        this.parser = new JSONParser();
    }

    public void extractEvents(JSONArray eventsJSONArray, ArrayList<Event> eventsList)
    {
        for(Object eventObject: eventsJSONArray)
        {
            JSONObject eventJSONObject = (JSONObject) eventObject;
            Event newEvent = new Event((String)eventJSONObject.get("name").toString());
            this.extractChoices(eventJSONObject, newEvent);
            eventsList.add(newEvent);
        }
    }
    public void extractChoices(JSONObject eventJSONObject, Event event)
    {
        JSONArray choicesJSONArray = (JSONArray) eventJSONObject.get("choices");
        for(Object choiceObject: choicesJSONArray)
        {
            JSONObject choiceJSONObject = (JSONObject) choiceObject;
            Choice newChoice = new Choice((String)choiceJSONObject.get("choice"));
            if(choiceJSONObject.get("relatedEvents") != null)
            {
                JSONArray relatedEventsJSONArray = (JSONArray) choiceJSONObject.get("relatedEvents");
                this.extractEvents(relatedEventsJSONArray,newChoice.getRelatedEventsList());
            }
            this.extractEffects(choiceJSONObject, newChoice);
            event.getChoices().add(newChoice);
        }

    }
    public void extractEffects(JSONObject choiceJSONObject, Choice choice)
    {
        JSONArray effectsArray = (JSONArray) choiceJSONObject.get("effects");
        for(Object effectObject : effectsArray)
        {
            JSONObject effectJSONObject = (JSONObject) effectObject;
            for(Object actionObject : effectJSONObject.keySet())
            {
                String affectedType = actionObject.toString();
                JSONObject actionJSONObject;
                if(effectJSONObject.get(affectedType) instanceof Long)
                {
                    actionJSONObject = effectJSONObject;

                }
                else
                {
                    actionJSONObject = (JSONObject) effectJSONObject.get(affectedType);
                }
                extractEffectsActions(actionJSONObject, choice.getEffectList(),affectedType );
            }

        }
    }
    public void extractEffectsActions(JSONObject actionsJSONObject, ArrayList<Effect> effectList, String affectedType)
    {
        for(Object nameObject : actionsJSONObject.keySet())
        {
            String affectedName = nameObject.toString();
            Long unitNumberChange = (Long) actionsJSONObject.get(affectedName);
            //System.out.println("affectedType : " + affectedType + " affectedName : " + affectedName + " unitNumberChange : " + unitNumberChange.intValue());
            Effect effect = new Effect(affectedType, affectedName, unitNumberChange.intValue());
            effectList.add(effect);
        }
    }
    public void extractFactions(JSONObject factionsJSONObject, ArrayList<Faction> factionsList)
    {
        for(Object factionObject : factionsJSONObject.keySet())
        {
            JSONObject factionJSONObject = (JSONObject) factionsJSONObject.get(factionObject);
            String factionName = (String) factionsJSONObject.get("name");
            Long factionSatisfaction = (Long) factionJSONObject.get("satisfactionPercentage");
            Long factionPartisans = (Long) factionJSONObject.get("numberOfPartisans");
            Faction newFaction = new Faction(factionName, factionSatisfaction.intValue(), factionPartisans.intValue());
            factionsList.add(newFaction);
        }
        this.data.calculateGlobalPopulationWithUpdate();
        this.data.calculateGlobalSatisfactionWithUpdate();
    }
    public void extractStartParameters(JSONObject startParametersJSONObject)
    {
        String difficultyName = (String) startParametersJSONObject.keySet().iterator().next();
        JSONObject difficultyJSONObject = (JSONObject) startParametersJSONObject.get(difficultyName);
        Long agriculturePercentage = (Long) difficultyJSONObject.get("agriculturePercentage");
        Long industryPercentage = (Long) difficultyJSONObject.get("industryPercentage");
        Long treasury = (Long) difficultyJSONObject.get("treasury");
        Long foodUnits = (Long) difficultyJSONObject.get("foodUnits");
        this.data.setAgriculturePercentage(agriculturePercentage.intValue());
        this.data.setDifficulty(difficultyName);
        this.data.setFoodUnits(foodUnits.intValue());
        this.data.setIndustryPercentage(industryPercentage.intValue());
        this.data.setTreasury(treasury.intValue());
    }
    public void extractAll()
    {
        try(FileReader reader = new FileReader(this.filePath)) {
            JSONObject jsonFile = (JSONObject) parser.parse(reader);
            JSONArray eventsArray = (JSONArray) jsonFile.get("events");
            JSONObject startParametersJSONObject = (JSONObject) jsonFile.get("gameStartParameters");
            JSONObject difficultyParametersJSONObject = (JSONObject) startParametersJSONObject.get(startParametersJSONObject.keySet().iterator().next());
            JSONObject factionsJSONObject = (JSONObject) difficultyParametersJSONObject.get("factions");
           // System.out.println("factions"  + factionsJSONObject.toString());
            this.extractFactions(factionsJSONObject, this.data.getFactionsList());
            this.extractStartParameters(startParametersJSONObject);
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
