package pawns;

import enums.TilesEnums;

/**
 * Class for the Messenger which is a subclass of Pawn
 * Sets the pawns starting location and can return the
 * pawn type
 * 
 * @author Cathal Ryan and Conor Kneafsey
 */
public class Messenger extends Pawn{

	/**
	  * Constructor for Messenger. Implements the constructor
	  * from the parent class Pawn.
	  */
	public Messenger() {
		super();

	}

	/**
	  * Return a string with the type of pawn it is
	  * @return that it is a Messenger
	  */
	public String getPlayerType() {
		return "Messenger";
	}

	/**
	  * Return the name of the pawn's starting location
	  * @return Silver Gate - where Messenger starts
	  */
	public TilesEnums startLoc() {
		return TilesEnums.SILVER_GATE;
	}

}
