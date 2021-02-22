import java.util.Random;

public class EventChoiceEffect {
    private String actionType;
    private String affectedObjectName;
    private int unitNumberChange;

    public EventChoiceEffect(String actionType, String affectedObjectName, int unitNumberChange) {
        this.actionType = actionType;
        this.affectedObjectName = affectedObjectName;
        this.unitNumberChange = unitNumberChange;
    }

    public void affectFaction(Faction faction)
    {
        if(faction.isAlive() == false)
        {
            System.out.println("La satisfaction de la faction " + faction.getName() + " ne peut plus changer, car elle est à 0");
            return;
        }
        int oldSatisfaction = faction.getSatisfaction();
        int newSatisfaction = oldSatisfaction + this.unitNumberChange;
        if(newSatisfaction <= 0)
        {
            faction.setAlive(false);
            newSatisfaction = 0;
        }
        else if(newSatisfaction > 100)
        {
            newSatisfaction = 100;
        }
        faction.setSatisfaction(newSatisfaction);
        System.out.println("La satisfaction de " + faction.getName() + " est passée de " + oldSatisfaction + " à " + faction.getSatisfaction());
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
        int oldTreasury = data.getTreasury();
        int newTreasury = oldTreasury + this.unitNumberChange;
        data.setTreasury(newTreasury);
        System.out.println("Votre trésorerie est passée de " + oldTreasury + " à " + newTreasury);
    }
    public void affectFood(WorldData data)
    {
        int oldFoodUnits = data.getFoodUnits();
        int newFoodUnits = oldFoodUnits + this.unitNumberChange;
        if(newFoodUnits < 0)
        {
            newFoodUnits = 0;
        }
        data.setFoodUnits(newFoodUnits);
        System.out.println("Vos unités de nourriture sont passées de " + oldFoodUnits + " à " + newFoodUnits);
    }

    public void affectIndustry(WorldData data)
    {
        int oldIndustry = data.getIndustryPercentage();
        int newIndustry = oldIndustry + this.unitNumberChange;
        if(newIndustry + data.getAgriculturePercentage() > 100)
        {
            newIndustry = 100 - data.getAgriculturePercentage();
        }
        else if(newIndustry < 0)
        {
            newIndustry = 0;
        }
        data.setIndustryPercentage(newIndustry);
        System.out.println("Votre industrie est passée de " + oldIndustry + " à " + newIndustry);
    }

    public void affectAgriculture(WorldData data)
    {
        int oldAgriculture = data.getAgriculturePercentage();
        int newAgriculture = oldAgriculture + this.unitNumberChange;
        if(newAgriculture + data.getIndustryPercentage() > 100)
        {
            newAgriculture = 100 - data.getIndustryPercentage();
        }
        else if(newAgriculture < 0)
        {
            newAgriculture = 0;
        }
        data.setIndustryPercentage(newAgriculture);
        System.out.println("Votre industrie est passée de " + oldAgriculture + " à " + newAgriculture);
    }

    public void affectPartisans(WorldData data)
    {
        int oldGlobalPopulation = data.getGlobalPopulation();
        Random random = new Random();
        for(int i = 0; i < Integer.signum(unitNumberChange) * unitNumberChange; i++)
        {
            int index = random.nextInt(data.getFactionsList().size());
            Faction affectedFaction = data.getFactionsList().get(index);
            affectedFaction.setTotalPartisans(affectedFaction.getTotalPartisans()+Integer.signum(unitNumberChange));
        }
        data.calculateGlobalPopulationWithUpdate();
        System.out.println("Votre population est passée de " + oldGlobalPopulation + " à " + data.getGlobalPopulation());
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
