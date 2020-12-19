package gameplay.model;

import cards.FloodDiscardPile;

public class WaterMeter {
	private static int watermeter;
	//private static int[] waterlevels = {0,1,2,3,4,5};
	private static int[] waterlevels = {90,91,92,93,94,95};
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
	}
	public static void cardDrawn() {
		setWatermeter(1);
		FloodDiscardPile.getInstance().putbackall();
	}
	
}
