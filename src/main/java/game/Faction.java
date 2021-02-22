package game;

public class Faction {
    private String name;
    private int satisfaction;
    private int totalPartisans;
    private boolean isAlive;

    public Faction(String name, int satisfaction, int totalPartisans) {
        this.name = name;
        this.satisfaction = satisfaction;
        this.totalPartisans = totalPartisans;
        this.isAlive = true;
    }

    public int getBribePrice()
    {
        return totalPartisans * 15;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(int satisfaction) {
        if(satisfaction <= 0)
        {
           this.satisfaction = 0;
        }
        else if(satisfaction > 100)
        {
            this.satisfaction = 100;
        }
        else
        {
            this.satisfaction = satisfaction;
        }
    }

    public int getTotalPartisans() {
        return totalPartisans;
    }

    public void setTotalPartisans(int totalPartisans) {
        this.totalPartisans = totalPartisans;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        if(alive)
        {
            isAlive = alive;
        }
        else
        {
            System.out.println("La faction " + this.name + " a atteint une satisfaction de 0. Elle ne pourra plus remonter");
        }
    }
}
