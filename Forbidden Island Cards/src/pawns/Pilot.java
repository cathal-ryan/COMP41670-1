package pawns;

import board.Board;
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

	@Override
	public boolean canSwim() {
		viableSwim = Board.getInstance().getValidTiles();
		return true;
	}
}
