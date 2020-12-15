package enums;

public enum TypeEnums {
	NORMAL 			("   Normal Tile    "),
	FOOLS_LANDING 	("  Fool's Landing  "),
	EARTH 			("   Earth Stone    "),
	WIND			("Statue of the Wind"), 
	FIRE 			(" Crystal of Fire  "),
	WATER			(" Ocean's Chalice  "),
	SEA				("     Sea Tile     ");

	private final String type;
	/**
	 * Constructor for room enum
	 * @param s String which will be set to the name of the room.
	 */
	private TypeEnums(final String s){
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
