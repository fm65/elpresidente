import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class JSONTest {
    private LoadJSON jsonLoader;
    private WorldData data;
    @BeforeEach
    protected void setUp ()  {
        this.data = new WorldData();
        this.jsonLoader = new LoadJSON("../../../scenario/attackOnTitans.json",data);
    }

    @org.junit.jupiter.api.Test
    public void testExtractEvents()
    {
        this.jsonLoader.extractEvents();
        assert(this.data.getEvents().size() > 0);
    }
    @org.junit.jupiter.api.Test
    public void testExtractChoices()
    {
        jsonLoader.extractChoices();
        for (Event event:this.data.getEvents()) {
            assert(event.getChoices().size() > 0 && event.getChoices().size() < 5);
        }
    }

    @org.junit.jupiter.api.Test
    public void testExtractEffects()
    {
        jsonLoader.extractEffects();
        for (Event event:this.data.getEvents()) {
            for(Choice choice : event.getChoices())
            {
                assert(choice.getEffects().size() > 0 );
            }
        }
    }

    @org.junit.jupiter.api.Test
    public void testExtractFactions()
    {
        jsonLoader.extractFactions();
        assert(this.data.getAllFactions().size() > 0);
        assertEquals(this.data.getGlobalPopulation(),80);
        assertEquals(this.data.getGlobalSatisfaction(),5100);
    }

    @org.junit.jupiter.api.Test
    public void testExtractData()
    {
        jsonLoader.extractData();
        assertEquals(this.data.getAgriculturePercentage(),40);
        assertEquals(this.data.getDifficulty(),"NORMAL");
        assertEquals(this.data.getIndustryPercentage(),35);
        assertEquals(this.data.getFoodUnits(),500);
    }
}
