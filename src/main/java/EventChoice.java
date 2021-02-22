import java.util.ArrayList;

public class EventChoice {
    private ArrayList<EventChoiceEffect> eventChoiceEffectList;
    private ArrayList<Event> relatedEventsList;
    private String description;

    public EventChoice(String description) {
        this.description = description;
        this.eventChoiceEffectList = new ArrayList<EventChoiceEffect>();
        this.relatedEventsList = new ArrayList<Event>();
    }

    public void applyEffects(WorldData data)
    {
        if(this.eventChoiceEffectList.size() > 0)
        {
            for(EventChoiceEffect eventChoiceEffect : eventChoiceEffectList)
            {
                if(eventChoiceEffect.getActionType().equals("actionOnFaction"))
                {
                    Faction faction = data.factionExists(eventChoiceEffect.getAffectedObjectName());
                    if(faction != null)
                    {
                        eventChoiceEffect.affectFaction(faction);
                    }
                }
                else if(eventChoiceEffect.getActionType().equals("actionOnFactor"))
                {
                    eventChoiceEffect.affectFactor(data);
                }
                else if(eventChoiceEffect.getActionType().equals("partisans"))
                {
                    eventChoiceEffect.affectPartisans(data);
                }
                else
                {
                    System.out.println("Rien ne se passe...");
                }
            }
        }
        if(this.relatedEventsList.size() > 0)
        {

        }
    }
    public ArrayList<EventChoiceEffect> getEventChoiceEffectList() {
        return eventChoiceEffectList;
    }

    public void setEventChoiceEffectList(ArrayList<EventChoiceEffect> eventChoiceEffectList) {
        this.eventChoiceEffectList = eventChoiceEffectList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Event> getRelatedEventsList() {
        return relatedEventsList;
    }

    public void setRelatedEventsList(ArrayList<Event> relatedEventsList) {
        this.relatedEventsList = relatedEventsList;
    }
}
