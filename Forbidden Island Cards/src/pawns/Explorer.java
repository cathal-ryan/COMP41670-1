package pawns;

import enums.TilesEnums;

public class Explorer extends Pawn{

	public Explorer() {
		super();

	}
	@Override
	public String getPlayerType() {
		return "Explorer";
	}

	public TilesEnums startLoc() {
		return TilesEnums.COPPER_GATE;
	}

}
