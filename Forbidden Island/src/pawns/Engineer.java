package pawns;

import enums.TilesEnums;

/**
 * Class for the Engineer which is a subclass of Pawn
 * Sets the pawns starting location and can return the
 * pawn type
 * 
 * @author Cathal Ryan and Conor Kneafsey
 */
public class Engineer extends Pawn{

	/**
	  * Constructor for Engineer. Implements the constructor
	  * from the parent class Pawn.
	  */
	public Engineer() {
		super();

	}

	/**
	  * Return a string with the type of pawn it is
	  * @return that it is an Engineer
	  */
	public String getPlayerType() {
		return "Engineer";
	}

	/**
	  * Return the name of the pawn's starting location
	  * @return Bronze Gate - where Engineer starts
	  */
	public TilesEnums startLoc() {
		return TilesEnums.BRONZE_GATE;
	}

}
