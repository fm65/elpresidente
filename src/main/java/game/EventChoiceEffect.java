package game;

import java.util.Random;

public class EventChoiceEffect {
    private String actionType;
    private String affectedObjectName;
    private int unitNumberChange;
    private WorldData data;

    public EventChoiceEffect(String actionType, String affectedObjectName, int unitNumberChange) {
        this.actionType = actionType;
        this.affectedObjectName = affectedObjectName;
        this.unitNumberChange = unitNumberChange;
        this.data = World.data;
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

    public void affectFactor()
    {
        if(this.affectedObjectName.toLowerCase() == "treasury")
        {
            this.affectTreasury();
        }
        else if(this.affectedObjectName.toLowerCase() == "foodunits")
        {
            this.affectFood();
        }
        else if(this.affectedObjectName.toLowerCase() == "industry")
        {
            this.affectIndustry();
        }
        else if(this.affectedObjectName.toLowerCase() == "agriculture")
        {
            this.affectAgriculture();
        }

    }
    private void affectTreasury()
    {
        int oldTreasury = this.data.getTreasury();
        int newTreasury = oldTreasury + this.unitNumberChange;
        this.data.setTreasury(newTreasury);
        System.out.println("Votre trésorerie est passée de " + oldTreasury + " à " + newTreasury);
    }
    private void affectFood()
    {
        int oldFoodUnits = data.getFoodUnits();
        int newFoodUnits = oldFoodUnits + this.unitNumberChange;
        if(newFoodUnits < 0)
        {
            newFoodUnits = 0;
        }
        this.data.setFoodUnits(newFoodUnits);
        System.out.println("Vos unités de nourriture sont passées de " + oldFoodUnits + " à " + newFoodUnits);
    }

    private void affectIndustry()
    {
        int oldIndustry = this.data.getIndustryPercentage();
        int newIndustry = oldIndustry + this.unitNumberChange;
        if(newIndustry + this.data.getAgriculturePercentage() > 100)
        {
            newIndustry = 100 - this.data.getAgriculturePercentage();
        }
        else if(newIndustry < 0)
        {
            newIndustry = 0;
        }
        this.data.setIndustryPercentage(newIndustry);
        System.out.println("Votre industrie est passée de " + oldIndustry + " à " + newIndustry);
    }

    private void affectAgriculture()
    {
        int oldAgriculture = this.data.getAgriculturePercentage();
        int newAgriculture = oldAgriculture + this.unitNumberChange;
        if(newAgriculture + this.data.getIndustryPercentage() > 100)
        {
            newAgriculture = 100 - this.data.getIndustryPercentage();
        }
        else if(newAgriculture < 0)
        {
            newAgriculture = 0;
        }
        this.data.setIndustryPercentage(newAgriculture);
        System.out.println("Votre industrie est passée de " + oldAgriculture + " à " + newAgriculture);
    }

    public void affectPartisans()
    {
        int oldGlobalPopulation = this.data.getGlobalPopulation();
        Random random = new Random();
        for(int i = 0; i < Integer.signum(unitNumberChange) * unitNumberChange; i++)
        {
            int index = random.nextInt(this.data.getFactionsList().size());
            Faction affectedFaction = this.data.getFactionsList().get(index);
            affectedFaction.setTotalPartisans(affectedFaction.getTotalPartisans()+Integer.signum(unitNumberChange));
        }
        this.data.calculateGlobalPopulationWithUpdate();
        System.out.println("Votre population est passée de " + oldGlobalPopulation + " à " + this.data.getGlobalPopulation());
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
