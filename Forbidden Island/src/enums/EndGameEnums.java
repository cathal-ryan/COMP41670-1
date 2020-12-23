package enums;

/**
 * Class holding the enumerated conditions to end the Forbidden Island game.
 * @author Cathal Ryan and Conor Kneafsey
 * @version 1.0
 *
 */
public enum EndGameEnums {
	TEMPLES 			("Both Temple tiles have sunk!"),
	CAVES 	            ("Both Cave tiles have sunk!"),
	PALACES 			("Both Palace tiles have sunk!"),
	GARDENS			    ("Both Garden tiles have sunk!"), 
	FOOLS 			    ("Fool's Landing has sunk!"),
	NOSWIM			    ("There's nowhere you can swim to!"),
	WATERLEVEL		    ("The water level has reached 5!"),
	HELICOPTER		    ("With all 4 treasures captured, the team has succesfully flown off Fools' Landing!");

	private final String type;
	/**
	 * Constructor for EndGameEnums
	 * @param s String which will be set to the end game description
	 */
	private EndGameEnums(final String s){
		type = s;  // String for end game description
	}
	/**
	 * returns the string describing how the game ended
	 * @return type - how game ended
	 */
	public String toString(){
		return type;
	}	

}