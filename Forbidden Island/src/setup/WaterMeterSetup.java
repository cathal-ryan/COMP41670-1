package setup;

import gameplay.model.WaterMeter;

/**
 * Class to handle setting up of the Water Meter for a game of Forbidden Island
 * Essentially sets the difficulty level of the game.
 * Methods are protected, they shouldn't be used outside of setup.
 * 
 * @author  Cathal Ryan and Conor Kneafsey
 */
public class WaterMeterSetup {

	//Variable set up
	@SuppressWarnings("unused")
    private WaterMeter setupWaterMeter;
    boolean validWaterLevel = false;

	/**
	 * Constructor
	 */
    public WaterMeterSetup() {
		this.setupWaterMeter = WaterMeter.getInstance();
	}

	/**
	 * Sets the water level for the water meter
	 */
	protected void createWaterLevel() {
		SetupInputs inputs = new SetupInputs();
		SetupOutputs outputs = new SetupOutputs();
		//Gets an integer selection for water meter between 1 and 4, and sets the water meter
		outputs.waterOptions();
		int water = inputs.setBetween(true);
		WaterMeter.setWatermeter(water);
	}
}
