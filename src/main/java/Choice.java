import java.util.ArrayList;

public class Choice {
    private ArrayList<Effect> effectList;
    private ArrayList<Event> relatedEventsList;
    private String description;

    public Choice(String description) {
        this.description = description;
        this.effectList = new ArrayList<Effect>();
        this.relatedEventsList = new ArrayList<Event>();
    }

    public void applyEffects(WorldData data)
    {
        if(this.effectList.size() > 0)
        {
            for(Effect effect : effectList)
            {
                if(effect.getActionType().equals("actionOnFaction"))
                {
                    Faction faction = data.factionExists(effect.getAffectedObjectName());
                    if(faction != null)
                    {
                        effect.affectFaction(faction);
                    }
                }
                else if(effect.getActionType().equals("actionOnFactor"))
                {
                    effect.affectFactor(data);
                }
                else if(effect.getActionType().equals("partisans"))
                {
                    effect.affectPartisans(data);
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
    public ArrayList<Effect> getEffectList() {
        return effectList;
    }

    public void setEffectList(ArrayList<Effect> effectList) {
        this.effectList = effectList;
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
