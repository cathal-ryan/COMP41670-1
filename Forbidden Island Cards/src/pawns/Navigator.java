package pawns;

import enums.TilesEnums;

public class Navigator extends Pawn{


	public Navigator() {
		super();
	}
	@Override
	public String getPlayerType() {
		return "Navigator";
	}

	public TilesEnums startLoc() {
		return TilesEnums.GOLD_GATE;
	}

}
