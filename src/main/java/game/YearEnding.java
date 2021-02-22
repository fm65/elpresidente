package game;

import java.util.ArrayList;
import java.util.InputMismatchException;
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

    }
    public void reviewAgriculture()
    {

    }
    public void reviewIndustry()
    {

    }

    public void foodMarket() {
        System.out.println("Souhaitez vous acheter de la nourriture ? Pour rappel cela coûte 8$ par portion.\nTapez 0 si vous ne souhaitez rien acheter");
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

    public void updatePopulation() {
        this.data.setGlobalPopulation(0);
    }
}
