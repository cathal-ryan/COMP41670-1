package pawns;

import board.Board;
import enums.TilesEnums;

/**
 * Class for the Navigator which is a subclass of Pawn
 * Sets the pawns starting location and can return the
 * pawn type. Implements pilots ability if they swim
 * 
 * @author Cathal Ryan and Conor Kneafsey
 */
public class Pilot extends Pawn{

	/**
	  * Constructor for Pilot. Implements the constructor
	  * from the parent class Pawn.
	  */
	public Pilot() {
		super();
	}

	/**
	  * Return a string with the type of pawn it is
	  * @return that it is a Pilot
	  */
	public String getPlayerType() {
		return "Pilot";
	}

	/**
	  * Return the name of the pawn's starting location
	  * @return Fools Landing - where Pilot starts
	  */
	public TilesEnums startLoc() {
		return TilesEnums.FOOLS_LANDING;
	}

	/**
	  * Override the canSwim() method found in the parent class Pawn
	  * Implements the pilot's special swimming ability that allows it
	  * to fly to any tile
	  * Adds all the non sea or sunken tiles to the arrayList viableSwim
	  * as the pilot can fly to any tile
	  * @return true as the pilot can fly to any tile
	  */
	@Override
	public boolean canSwim() {
		viableSwim = Board.getInstance().getValidTiles();
		return true;
	}
}
