package gameplay;

import cards.FloodDiscardPile;
import cards.TreasureDeck;

public class WaterMeter {
	private static int watermeter;
	private static int[] waterlevels = {2,2,3,3,3,4,4,5,5,9999};
	private static WaterMeter theWaterMeter;

	public static WaterMeter getInstance(){
        if(theWaterMeter == null){
        	theWaterMeter = new WaterMeter();
			watermeter=0;
        }
        return theWaterMeter;
    }
	
	public static int getWaterlevel() {
		return waterlevels[watermeter];
	}

	public static void setWatermeter(int k) {
		watermeter=watermeter+k;
		if(watermeter>9){
			GameManager.getInstance().
		}
	}
	public static void cardDrawn() {
		System.out.println("Oh no! The Water Rises!!");
		setWatermeter(1);
		FloodDiscardPile.getInstance().putbackall();
	}
	
}
