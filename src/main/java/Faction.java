public class Faction {
    private String name;
    private int satisfaction;
    private int totalSupporter;
    private boolean isAlive;

    public Faction(String name, int satisfaction, int totalSupporter) {
        this.name = name;
        this.satisfaction = satisfaction;
        this.totalSupporter = totalSupporter;
        this.isAlive = true;
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
        this.satisfaction = satisfaction;
    }

    public int getTotalSupporter() {
        return totalSupporter;
    }

    public void setTotalSupporter(int totalSupporter) {
        this.totalSupporter = totalSupporter;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
