package gameplay;

public class WaterMeter {
	private static int watermeter;

	public int getWatermeter() {
		return watermeter;
	}

	public static void setWatermeter() {
		System.out.println("Oh no! The Water Rises!");
		watermeter ++;
	}
	
}
