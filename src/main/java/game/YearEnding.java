package game;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class YearEnding {
    WorldData data;

    public YearEnding() {
        this.data = World.data;
    }
    public void bribe() {
        System.out.println("Souhaitez vous donner un pot de vin à une faction ?");
        System.out.println("Pour rappel, si vous payez 15$ par partisan d'une faction, la satisfaction de cette faction augmentera de 10%");
        System.out.println("Vous disposez actuellement de " + this.data.getTreasury() + "$");
        int i = 1;
        for(Faction faction : this.data.getFactionsList())
        {
            System.out.print("\n" + i + ") " + faction.getName());
            System.out.print(" partisans : " + faction.getTotalPartisans() + " satisfaction : " + faction.getSatisfaction());
            System.out.println("\n\tPrix du pot de vin : " + 15*faction.getTotalPartisans());
            i++;
        }
        System.out.println(i + ") Ne pas faire de pot de vin");
        if(this.bribeChoice())
        {
            this.bribe();
        }

    }

    public boolean bribeChoice()
    {
        int choice = 0;
        ArrayList<Faction> factionsList = this.data.getFactionsList();
        Scanner input = new Scanner(System.in);
        try
        {
            choice = input.nextInt();
        }catch (InputMismatchException exception)
        {
            System.out.println("Je n'ai pas compris, veuillez réessayer.");
            this.bribeChoice();
        }
        choice --;
        if(choice < factionsList.size() && choice > 0)
        {
            this.bribeFaction(factionsList.get(choice));
        }
        else if(choice == factionsList.size())
        {
            System.out.println("Vous avez choisi de ne pas faire de pot de vin.");
            System.out.println("Passons à la suite");
            return false;
        }
        else
        {
            System.out.println("Mauvais chiffre, veuillez réessayer");
            this.bribeChoice();
        }
        return true;
    }

    private void bribeFaction(Faction faction)
    {
        System.out.println("Vous avez choisi de faire un pot de vin à la faction " + faction.getName());
        if(faction.getBribePrice() > this.data.getTreasury())
        {
            System.out.println("Il semble que vous n'ayiez pas assez d'argent pour faire ça.\nVeuillez réessayer");
            this.bribeChoice();
            return;
        }
        this.data.setTreasury(this.data.getTreasury() - faction.getBribePrice());
        int oldSatisfaction = faction.getSatisfaction();
        double newSatisfactionDouble = oldSatisfaction * 1.1;
        int newSatisfaction = (int) newSatisfactionDouble;
        faction.setSatisfaction(newSatisfaction);
        System.out.println("La nouvelle satisfaction de " + faction.getName() + " est de " + faction.getSatisfaction());
    }

    public void yearReview() {
        this.reviewAgriculture();
        this.reviewIndustry();
        this.reviewFood();
    }
    private void reviewAgriculture()
    {
        int foodGenerated = this.data.getAgriculturePercentage() * 40;
        int foodNeeded = this.data.getGlobalPopulation()*4;
        if(foodGenerated > foodNeeded)
        {
            this.increasePopulation();
        }
    }
    private void increasePopulation() {
        System.out.println("Bonne nouvelle : votre agriculture est excédentaire, et il y a donc de la natalité sur votre île.");
        Random random = new Random();
        float populationIncreasePercentage = 1 + (random.nextInt(10)/(float)100);
        int newPopulation  = (int) (this.data.getGlobalPopulation() * populationIncreasePercentage);
        System.out.println("Votre population passe de " + this.data.getGlobalPopulation() + " à " + newPopulation);
        this.data.setGlobalPopulation(newPopulation);
    }
    private void reviewIndustry()
    {
        System.out.println("Bonne nouvelle : votre industrie vous rapporte de l'argent.");
        int newTreasury  = this.data.getTreasury() + this.data.getIndustryPercentage() * 10;
        System.out.print("Votre trésorerie passe de " + this.data.getTreasury() + " à " + newTreasury);
        this.data.setTreasury(newTreasury);
    }

    public void foodMarket() {
        System.out.println("Souhaitez vous acheter de la nourriture ? Pour rappel cela coûte 8$ par portion.");
        System.out.println("Vous possédez actuellement " + this.data.getFoodUnits() +
                " portions de nourriture, pour une population totale de " + this.data.getGlobalPopulation());
        System.out.println("Tapez 0 si vous ne souhaitez rien acheter");
        Scanner input = new Scanner(System.in);
        int newFoodUnits = 0;
        try
        {
            newFoodUnits = input.nextInt();
        }catch (InputMismatchException exception)
        {
            System.out.println("Je n'ai pas compris, veuillez réessayer.");
            this.foodMarket();
        }
        if(newFoodUnits == 0)
        {
            System.out.println("Vous avez décidé de ne rien acheter.");
            return;
        }
        int cost = newFoodUnits * 8;
        System.out.println("Vous avez décidé d'acheter " + newFoodUnits + " unités de nourriture.");
        System.out.println("Cela vous coûte " + cost + "$");

        if(this.data.getTreasury() > cost)
        {
            this.data.setFoodUnits(this.data.getFoodUnits() + newFoodUnits);
            this.data.setTreasury(this.data.getTreasury() - cost);
            System.out.println("Vous avez maintenant " + this.data.getFoodUnits() + " portions de nourriture");
            System.out.println("Votre trésorerie est de " + this.data.getTreasury() + "$");
        }
        else
        {
            System.out.println("Oups, il semble que vous n'ayez pas assez d'argent pour acheter tout ça.\nVeuillez réessayer");
            this.foodMarket();
        }

    }

    public void reviewFood()
    {
        int foodNeeded = this.data.getGlobalPopulation() * 4;
        int newFoodUnits = this.data.getFoodUnits() - foodNeeded;
        if(newFoodUnits < 0)
        {
            this.decreasePopulation(newFoodUnits);
            newFoodUnits = 0;
        }
        this.data.setFoodUnits(newFoodUnits);
    }

    private void decreasePopulation(int foodUnits)
    {
        float missingFoodPortion = foodUnits / 4;
        int missingFoodPortionInteger = (int) missingFoodPortion;
        Random random = new Random();
        for(int i = 0; i < missingFoodPortion; i++)
        {
            int randomIndex = random.nextInt(this.data.getFactionsList().size());
            Faction faction = this.data.getFactionsList().get(randomIndex);
            faction.setTotalPartisans(faction.getTotalPartisans() - 1);
        }
        int satisfactionDecrease = missingFoodPortionInteger * 2;
        for(Faction faction: this.data.getFactionsList())
        {
            int newSatisfaction = faction.getSatisfaction() - satisfactionDecrease;
            faction.setSatisfaction(newSatisfaction);
            if(newSatisfaction <= 0)
            {
                faction.setAlive(false);
            }
        }
    }


}
