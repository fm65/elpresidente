import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ScenarioSelectorTest {

    private ScenarioSelector scenarioSelector;
    @BeforeEach
    protected void setUp ()  {
        this.scenarioSelector = new ScenarioSelector();
    }
    @Test
    public void testSelectScenariosIndexZero()
    {
        String scenarioIndex = "0";
        ByteArrayInputStream bais = new ByteArrayInputStream(scenarioIndex.getBytes());
        System.setIn(bais);
        assertThrows(NoSuchElementException.class, () -> this.scenarioSelector.selectScenario());
    }
    @Test
    public void testSelectScenariosIndexWithNoNumber()
    {
        String scenarioIndex = "l";
        ByteArrayInputStream bais = new ByteArrayInputStream(scenarioIndex.getBytes());
        System.setIn(bais);
        assertThrows(NoSuchElementException.class, () -> this.scenarioSelector.selectScenario());
    }
    @Test
    public void testSelectScenariosQuit()
    {
        String scenarioIndex = "4";
        ByteArrayInputStream bais = new ByteArrayInputStream(scenarioIndex.getBytes());
        System.setIn(bais);
        this.scenarioSelector.selectScenario();
    }
    @Test
    public void testSelectScenariosIndexOne()
    {
        String scenarioIndex = "1";
        ByteArrayInputStream bais = new ByteArrayInputStream(scenarioIndex.getBytes());
        System.setIn(bais);
        this.scenarioSelector.selectScenario();
    }

}
