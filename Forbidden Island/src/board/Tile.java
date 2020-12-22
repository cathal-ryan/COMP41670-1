package board;

import java.awt.Point;

import enums.TilesEnums;
import enums.TypeEnums;

public class Tile {

	private TilesEnums tileName;
	private TypeEnums tileType;
	private Point pos;
	private Point up;
	private Point down;
	private Point left;
	private Point right;
	private boolean flooded;
	private boolean sunk;

	/** Constructor for Tile. Sets up a tile as a sea
	  * tile and assigns it coordinates
      */
	public Tile(int x, int y) {
		this.tileName = TilesEnums.SEA;
		this.tileType = TypeEnums.SEA;

		this.pos = new Point(x,y);

		this.flooded = false;
		this.sunk = false;
	}

	/** return the name of the tile
	  * @return tileName - name of tile
	  */
	public TilesEnums getName() {
		return tileName;
	}

	/** return the type of the tile
	  * @return tileType - type of tile
	  */
	public TypeEnums getType() {
		return tileType;
	}
	
	/** return the position of the tile
	  * @return pos - Point with tile coordinates
	  */
	public Point getPos() {
		return pos;
	}

	/** check if the tile is flooded
	  * @return boolean - true if tile is flooded
	  */
	public boolean isFlooded() {
		return flooded;
	}

	/** check if the tile has sunk
	  * @return boolean - true if tile has sunk
	  */
	public boolean isSunk() {
		return sunk;
	}

	/** Flood a tile. If already flooded, sink the tile
	  */
	public void flood() {
		if(isFlooded())
			sink();
		else
			flooded = true;
	}

	/** Shore up a tile if it has not sunk
	  */
	public void shoreUp() {
		if(!isSunk())
			flooded = false;
	}

	/** Sink a tile and set its type to Sea
	  */
	private void sink() {
		sunk = true;
		setType(TilesEnums.SEA);
	}

	/** set the name of a tile from the given name
	  */
	public void setName(TilesEnums name) {
		tileName = name;
	}

	/** Set the tile type given its name. Special types for treasure tiles,
	  * fools landing and sea tiles
	  */
	public void setType(TilesEnums name) {
		switch(name) {
			case FOOLS_LANDING:
				tileType = TypeEnums.FOOLS_LANDING;
				break;
			case TEMPLE_OF_THE_MOON:
				tileType = TypeEnums.EARTH;
				break;
			case TEMPLE_OF_THE_SUN:
				tileType = TypeEnums.EARTH;
				break;
			case WHISPERING_GARDEN:
				tileType = TypeEnums.WIND;
				break;
			case HOWLING_GARDEN:
				tileType = TypeEnums.WIND;
				break;
			case CAVE_OF_EMBERS:
				tileType = TypeEnums.FIRE;
				break;
			case CAVE_OF_SHADOWS:
				tileType = TypeEnums.FIRE;
				break;
			case CORAL_PALACE:
				tileType = TypeEnums.WATER;
				break;
			case TIDAL_PALACE:
				tileType = TypeEnums.WATER;
				break;
			case SEA:
				tileType = TypeEnums.SEA;
				break;
			default:
				tileType = TypeEnums.NORMAL;
		}
	}

	/** resets tile so it is no longer flooded or sank
	  */
	public void clean() {
		flooded = false;
		sunk = false;
	}
}