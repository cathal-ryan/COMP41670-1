package enums;

/**
 * Class holding the enumerated types for tile types in Forbidden Island.
 * @author Cathal Ryan and Conor Kneafsey
 * @version 1.0
 *
 */
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
	 * Constructor for Type Enum
	 * @param s String which will be set to the type.
	 */
	private TypeEnums(final String s){
		type = s;  // String for name of Type
	}
	

	/**
	 * returns the string name of TypeEnum
	 * @return type as a string.
	 */
	@Override
	public String toString(){
		return type;
	}	

}
