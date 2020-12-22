package testing;
import org.junit.Test;
import static org.junit.Assert.*;
import gameplay.control.LoseObserver;
import gameplay.model.*;
import setup.BoardSetup;
public class WaterMeterTest {
    
    @Test // Test for water meter from full deck drawn
    public void waterMeterLevelTest() {
        WaterMeter theWaterMeter = WaterMeter.getInstance();
        theWaterMeter.setWatermeter(0);
        GameModel theModel = GameModel.getInstance();
        for(int i=0;i<28;i++) // Entire deck pulled out
            theModel.dealTreasure();   
		assertEquals("Water Meter after starting at 0 and drawing full deck",3, WaterMeter.getWaterlevel());
    }

}
