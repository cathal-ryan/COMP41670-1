package enums;

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
	 * Constructor for room enum
	 * @param s String which will be set to the name of the room.
	 */
	private EndGameEnums(final String s){
		type = s;  // String for name of Room
	}
	/**
	 * returns the string name of the EoomEnum
	 * @return name of room which is a string.
	 */
	public String toString(){
		return type;
	}	

}