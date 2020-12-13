package pawns;

import enums.TilesEnums;

public class Messenger extends Pawn{


	public Messenger() {

		super();

	}
	@Override
	public String getPlayerType() {
		return "Messenger";
	}

	public TilesEnums startLoc() {
		return TilesEnums.SILVER_GATE;
	}

}
