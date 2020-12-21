package setup;

import gameplay.model.WaterMeter;

public class WaterMeterSetup {

    private WaterMeter setupWaterMeter;
    boolean validWaterLevel = false;

    public WaterMeterSetup() {
		this.setupWaterMeter = WaterMeter.getInstance();
	}

	protected void createWaterLevel() {
		SetupInputs inputs = new SetupInputs();
		SetupOutputs outputs = new SetupOutputs();
		int level=0;
		outputs.waterOptions();
		int water = inputs.setBetween(true);
		setupWaterMeter.setWatermeter(water);
	}
}
