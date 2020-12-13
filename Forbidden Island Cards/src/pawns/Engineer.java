package pawns;

import enums.TilesEnums;

public class Engineer extends Pawn{

	public Engineer() {
		super();

	}
	@Override
	public String getPlayerType() {
		return "Engineer";
	}

	public TilesEnums startLoc() {
		return TilesEnums.BRONZE_GATE;
	}

}
