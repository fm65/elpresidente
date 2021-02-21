public class Effect {
    private String affectedObjectType;
    private String affectedObjectName;
    private int unitNumberChange;

    public Effect(String affectedObjectType, String affectedObjectName, int unitNumberChange) {
        this.affectedObjectType = affectedObjectType;
        this.affectedObjectName = affectedObjectName;
        this.unitNumberChange = unitNumberChange;
    }

    public String getAffectedObjectType() {
        return affectedObjectType;
    }

    public void setAffectedObjectType(String affectedObjectType) {
        this.affectedObjectType = affectedObjectType;
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
