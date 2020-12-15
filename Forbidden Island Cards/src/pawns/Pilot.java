package pawns;

import enums.TilesEnums;

public class Pilot extends Pawn{


	public Pilot() {
		super();
	}

	@Override
	public String getPlayerType() {
		return "Pilot";
	}

	public TilesEnums startLoc() {
		return TilesEnums.FOOLS_LANDING;
	}

}
