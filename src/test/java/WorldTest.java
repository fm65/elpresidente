import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class WorldTest {
    private World myWorld;
    @BeforeEach
    protected void setUp ()  {
        this.myWorld = new World();
        this.myWorld.setScenarioName("Attack on Titans");
    }


    @Test
    public void testWorldCreationWithScenario()
    {
        this.myWorld.createDataWithJSON("scenario/attackOnTitans.json");

    }
    @Test
    public void testWorldCreationWithoutScenario()
    {
        this.myWorld.createData();
    }
    @Test
    public void testIterateSeasons()
    {
        this.myWorld.createDataWithJSON("scenario/attackOnTitans.json");
        this.myWorld.getActions().iterateSeasons();
    }

    @Test
    public void testIterateYears()
    {

    }

    @Test
    public void testBribe()
    {

    }

    @Test
    public void testFoodMarket()
    {

    }

    @Test
    public void testUpdatePopulation()
    {

    }

    @org.junit.jupiter.api.Test
    public void testCallEvent()
    {

    }

    @org.junit.jupiter.api.Test
    public void testEndYear()
    {

    }
}
