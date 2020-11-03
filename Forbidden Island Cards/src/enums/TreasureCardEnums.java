package enums;

public enum TreasureCardEnums {
	EARTH_STONE     ("Earth Stone"     ),
	STATUE_OF_THE_WIND ("Statue of the Wind"),
	CRYSTAL_OF_FIRE ("Crystal of Fire" ), 
	OCEANS_CHALICE  ("Ocean's Chalice"  ), 
	SANDBAGS  ("Sandbags"  ), 
	HELICOPTER_LIFT  ("Helicopter Lift"  ), 
	WATERS_RISE  ("Waters Rise"  );


	private final String name;
	/**
	 * Constructor for room enum
	 * @param s String which will be set to the name of the room.
	 */
	private TreasureCardEnums(final String s){
		this.name = s;  // String for name of Room
	}
	

/**
 * returns the string name of the EoomEnum
 * @return name of room which is a string.
 */
	public String toString(){
		return name ;
	}	

}


