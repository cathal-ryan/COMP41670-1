package gameplay.model;

import cards.FloodDiscardPile;
/**
 * Singleton Water Meter class, represents the Water Meter and Water Level,
 * allows for it to be increased and to return level its at
 * and allows for new treasures to be captured
 * @author Cathal Ryan and Conor Kneafsey
 *
 */
public class WaterMeter {
	
	// water levels array can be altered to allow for a larger range of allowed waters rise cards to be drawn
	private static int watermeter; // Current level on water meter
	private static int[] waterlevels = {0,1,2,3,4,5}; // Water Levels allowed
	private static WaterMeter theWaterMeter;

    /**
     * getInstance singleton method returns the single instance
     * of the WaterMeter object.
     * @return The single WaterMeter object
     */
	public static WaterMeter getInstance(){
        if(theWaterMeter == null){
        	theWaterMeter = new WaterMeter();
			watermeter=0;
        }
        return theWaterMeter;
    }
	
    /**
     * cardDrawn increments the flood meter, and tells the discard pile to shuffle
     * itself and add back to the deck
     */
	public static void cardDrawn() {
		setWatermeter(1);
		FloodDiscardPile.getInstance().putbackall();
	}
	
    /**
     * getWaterLevel returns the current water level, ie how flood many cards must be drawn
     * @return Integer current water level
     */
	public static int getWaterlevel() {
		return waterlevels[watermeter];
	}

    /**
     * setWaterMeter increments water Meter by necessary amount.
     * @param Integer amount of increase water level by
     */
	public static void setWatermeter(int k) {
		watermeter=watermeter+k;
	}

}
