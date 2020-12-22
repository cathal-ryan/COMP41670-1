package enums;

/**
 * Class holding the enumerated names of the tiles in Forbidden Island.
 * @author Cathal Ryan and Conor Kneafsey
 * @version 1.0
 *
 */
public enum TilesEnums {
	FOOLS_LANDING		("  Fool's Landing  "),
	TEMPLE_OF_THE_SUN 	("Temple of the Sun "),
	CORAL_PALACE 		("   Coral Palace   "), 
	TEMPLE_OF_THE_MOON  ("Temple of the Moon"), 
	OBSERVATORY         ("   Observatory    "), 
	BREAKERS_BRIDGE     (" Breaker's Bridge "), 
	TIDAL_PALACE      	("   Tidal Palace   "),
	CAVE_OF_SHADOWS     (" Cave of Shadows  "),
	WHISPERING_GARDEN   ("Whispering Garden "),
	CAVE_OF_EMBERS      ("  Cave of Embers  "),
	PHANTOM_ROCK        ("   Phantom Rock   "),
	HOWLING_GARDEN      ("  Howling Garden  "),
	SILVER_GATE        	("   Silver Gate    "),
	IRON_GATE        	("    Iron Gate     "),
	BRONZE_GATE        	("   Bronze Gate    "),
	MISTY_MARSH        	("   Misty Marsh    "),
	COPPER_GATE        	("   Copper Gate    "),
	LOST_LAGOON        	("   Lost Lagoon    "),
	CLIFFS_OF_ABANDON   ("Cliffs of Abandon "),
	CRIMSON_FOREST      ("  Crimson Forest  "),
	DUNES_OF_DECEPTION  ("Dunes of Deception"),
	GOLD_GATE        	("    Gold Gate     "),
	TWILIGHT_HOLLOW     (" Twilight Hollow  "),
	WATCHTOWER        	("    Watchtower    "),
	SEA		       		("       Sea        ");

	private final String name;
	/**
	 * Constructor for TilesEnums enum
	 * @param s String which contains name of tile
	 */
	private TilesEnums(final String s){
		name = s;  // String for name of Tile
	}
	

	/**
	 * returns the name of the Tile
	 * @return name - String with name of Tile
	 */
	public String toString(){
		return name;
	}	

}
