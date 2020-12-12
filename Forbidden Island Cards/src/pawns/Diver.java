package pawns;

import enums.TilesEnums;

public class Diver extends Pawn{

	public Diver() {
		super();

	}
	@Override
	public String getPlayerType() {
		return "Diver";
	}

	public TilesEnums startLoc() {
		return TilesEnums.IRON_GATE;
	}

}
