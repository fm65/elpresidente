public class Effect {
    private String affectedFaction;
    private String affectedRessource;
    private int unitNumberChange;

    public Effect(String affectedFaction, String affectedRessource, int unitNumberChange) {
        this.affectedFaction = affectedFaction;
        this.affectedRessource = affectedRessource;
        this.unitNumberChange = unitNumberChange;
    }

    public String getAffectedFaction() {
        return affectedFaction;
    }

    public void setAffectedFaction(String affectedFaction) {
        this.affectedFaction = affectedFaction;
    }

    public String getAffectedRessource() {
        return affectedRessource;
    }

    public void setAffectedRessource(String affectedRessource) {
        this.affectedRessource = affectedRessource;
    }

    public int getUnitNumberChange() {
        return unitNumberChange;
    }

    public void setUnitNumberChange(int unitNumberChange) {
        this.unitNumberChange = unitNumberChange;
    }
}
