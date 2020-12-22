package setup;

import gameplay.model.WaterMeter;

public class WaterMeterSetup {

	@SuppressWarnings("unused")
    private WaterMeter setupWaterMeter;
    boolean validWaterLevel = false;

    public WaterMeterSetup() {
		this.setupWaterMeter = WaterMeter.getInstance();
	}

	protected void createWaterLevel() {
		SetupInputs inputs = new SetupInputs();
		SetupOutputs outputs = new SetupOutputs();
		outputs.waterOptions();
		int water = inputs.setBetween(true);
		WaterMeter.setWatermeter(water);
	}
}
