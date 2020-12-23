package enums;

/**
 * Class holding the enumerated treasure cards Forbidden Island game.
 * @author Cathal Ryan and Conor Kneafsey
 * @version 1.0
 *
 */
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
	 * Constructor for TreasureCardEnums
	 * @param s String containing the treasure card description
	 */
	private TreasureCardEnums(final String s){
		this.name = s;  // String of treasure card description
	}
	

/**
 * returns the description of the treasure card
 * @return name - description of treasure card
 */
	public String toString(){
		return name ;
	}	

}


