package board;

import java.util.Collections;
import java.util.Stack;

import enums.TilesEnums;

public class TileStack {
	
	/** Set a stack for all the tile names
	  */
	private Stack<TilesEnums> enumStack = new Stack<TilesEnums>();

	/** Constructor for TileStack. Push all tile names into stack
	 */
	public TileStack() {
		    enumStack.push(TilesEnums.FOOLS_LANDING);
		    enumStack.push(TilesEnums.TEMPLE_OF_THE_SUN);
		    enumStack.push(TilesEnums.CORAL_PALACE);
		    enumStack.push(TilesEnums.TEMPLE_OF_THE_MOON);
		    enumStack.push(TilesEnums.OBSERVATORY);
		    enumStack.push(TilesEnums.BREAKERS_BRIDGE);
		    enumStack.push(TilesEnums.TIDAL_PALACE);
		    enumStack.push(TilesEnums.CAVE_OF_SHADOWS);
		    enumStack.push(TilesEnums.WHISPERING_GARDEN);
		    enumStack.push(TilesEnums.CAVE_OF_EMBERS);
		    enumStack.push(TilesEnums.PHANTOM_ROCK);
		    enumStack.push(TilesEnums.HOWLING_GARDEN);
		    enumStack.push(TilesEnums.SILVER_GATE);
		    enumStack.push(TilesEnums.IRON_GATE);
		    enumStack.push(TilesEnums.BRONZE_GATE);
		    enumStack.push(TilesEnums.MISTY_MARSH);
		    enumStack.push(TilesEnums.COPPER_GATE);
		    enumStack.push(TilesEnums.LOST_LAGOON);
		    enumStack.push(TilesEnums.CLIFFS_OF_ABANDON);
		    enumStack.push(TilesEnums.CRIMSON_FOREST);
		    enumStack.push(TilesEnums.DUNES_OF_DECEPTION);
		    enumStack.push(TilesEnums.GOLD_GATE);
		    enumStack.push(TilesEnums.TWILIGHT_HOLLOW);
		    enumStack.push(TilesEnums.WATCHTOWER);
		    Collections.shuffle(enumStack);
	}

	/** return the tile names within stack
	  * @return TilesEnums - an enum containing tile name
	  */
	public TilesEnums pop() {
		return enumStack.pop();
	}

	/** return the size of the stack stack
	  * @return int - size of stack
	  */
	public int size() {
		return enumStack.size();
	}
}