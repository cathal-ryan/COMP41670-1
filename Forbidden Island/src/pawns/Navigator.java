package pawns;

import enums.TilesEnums;

/**
 * Class for the Navigator which is a subclass of Pawn
 * Sets the pawns starting location and can return the
 * pawn type
 * 
 * @author Cathal Ryan and Conor Kneafsey
 */
public class Navigator extends Pawn{

	/**
	  * Constructor for Navigator. Implements the constructor
	  * from the parent class Pawn.
	  */
	public Navigator() {
		super();
	}

	/**
	  * Return a string with the type of pawn it is
	  * @return that it is a Navigator
	  */
	public String getPlayerType() {
		return "Navigator";
	}

	/**
	  * Return the name of the pawn's starting location
	  * @return Gold Gate - where Navigator starts
	  */
	public TilesEnums startLoc() {
		return TilesEnums.GOLD_GATE;
	}

}
