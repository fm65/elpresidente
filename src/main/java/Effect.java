import java.util.Random;

public class Effect {
    private String actionType;
    private String affectedObjectName;
    private int unitNumberChange;

    public Effect(String actionType, String affectedObjectName, int unitNumberChange) {
        this.actionType = actionType;
        this.affectedObjectName = affectedObjectName;
        this.unitNumberChange = unitNumberChange;
    }

    public void affectFaction(Faction faction)
    {
        int newSatisfaction = faction.getSatisfaction() + this.unitNumberChange;
        if(newSatisfaction <= 0)
        {
            faction.setAlive(false);
            faction.setSatisfaction(0);
        }
        else if(newSatisfaction > 100)
        {
            faction.setSatisfaction(100);
        }
        else
        {
            faction.setSatisfaction(newSatisfaction);
        }
        System.out.println("Nouvelle satisfaction de " + faction.getName() + " : " + faction.getSatisfaction());
    }

    public void affectFactor(WorldData data)
    {
        if(this.affectedObjectName.toLowerCase() == "treasury")
        {
            this.affectTreasury(data);
        }
        else if(this.affectedObjectName.toLowerCase() == "foodunits")
        {
            this.affectFood(data);
        }
        else if(this.affectedObjectName.toLowerCase() == "industry")
        {
            this.affectIndustry(data);
        }
        else if(this.affectedObjectName.toLowerCase() == "agriculture")
        {
            this.affectAgriculture(data);
        }
    }
    public void affectTreasury(WorldData data)
    {
        int newTreasury = data.getTreasury() + this.unitNumberChange;
        data.setTreasury(newTreasury);
    }
    public void affectFood(WorldData data)
    {
        int newFoodUnits = data.getFoodUnits() + this.unitNumberChange;
        if(newFoodUnits < 0)
        {
            data.setFoodUnits(0);
        }
        else
        {
            data.setFoodUnits(newFoodUnits);
        }
    }

    public void affectIndustry(WorldData data)
    {
        int newIndustry = data.getIndustryPercentage() + this.unitNumberChange;
        if(newIndustry + data.getAgriculturePercentage() > 100)
        {
            newIndustry = 100 - data.getAgriculturePercentage();
        }
        else if(newIndustry < 0)
        {
            newIndustry = 0;
        }
        data.setIndustryPercentage(newIndustry);
    }

    public void affectAgriculture(WorldData data)
    {
        int newAgriculture = data.getAgriculturePercentage() + this.unitNumberChange;
        if(newAgriculture + data.getIndustryPercentage() > 100)
        {
            newAgriculture = 100 - data.getIndustryPercentage();
        }
        else if(newAgriculture < 0)
        {
            newAgriculture = 0;
        }
        data.setIndustryPercentage(newAgriculture);
    }

    public void affectPartisans(WorldData data)
    {
        Random random = new Random();
        for(int i = 0; i < Integer.signum(unitNumberChange) * unitNumberChange; i++)
        {
            int index = random.nextInt(data.getFactionsList().size());
            Faction affectedFaction = data.getFactionsList().get(index);
            affectedFaction.setTotalPartisans(affectedFaction.getTotalPartisans()+Integer.signum(unitNumberChange));
        }
    }
    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getAffectedObjectName() {
        return affectedObjectName;
    }

    public void setAffectedObjectName(String affectedObjectName) {
        this.affectedObjectName = affectedObjectName;
    }

    public int getUnitNumberChange() {
        return unitNumberChange;
    }

    public void setUnitNumberChange(int unitNumberChange) {
        this.unitNumberChange = unitNumberChange;
    }
}
