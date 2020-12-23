package board;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.awt.Point;

import enums.TilesEnums;
import enums.TypeEnums;

/**
  * Board class for the game board. Implemented as a singleton
  * as there should only be one instance of the board
  * Creates a hashmap of tiles with coordinates as the key,
  * can get and set the tile name and type, and can flood or
  * shore up a tile
  * 
  * @author Cathal Ryan and Conor Kneafsey
  */

public class Board {

	private static Board playBoard;
	private int rows;
	private int cols;
	private Point p;
	private HashMap<Point, Tile> boardTiles; // Tiles with coords as key

    /** getInstance singleton method gets single instance
      * of the Board
      * @return Singleton Board Object
      */
	public static Board getInstance(){
        if(playBoard == null){
            playBoard = new Board();
        }
        return playBoard;
    }

    /** Constructor for Board. Sets up all tiles and their coordinates
      * Set to private as it is Singleton
      */
    private Board() {

    	this.rows = 6;
    	this.cols = 6;
    	this.boardTiles = new HashMap<Point, Tile>();

        // Initalise a tile in every position
    	for(int x = 0; x < cols; x++) {
    		for(int y = 0; y < rows; y++) {
    			p = new Point(x,y);
    			boardTiles.put(p, new Tile(x,y));
    		}
    	}
    }

    /** Returns the Board rows
      * @return rows - the amount of rows of the board
      */
    public int getRows() {
    	return rows;
    }

    /** Returns the Board columnds
      * @return cols - the amount of columns of the board
      */
    public int getCols() {
    	return cols;
    }

    /** Returns the Tile from coordinates
      * @return Tile - a tile from the Board
      */
    public Tile getTile(Point coords) {
    	return boardTiles.get(coords);
	}
	
    /** Returns Hashmap containing the tiles
      * @return boardTiles - Hashmap with tiles
      */
	public HashMap<Point, Tile> getBoardTiles(){
		return boardTiles;
	}

    /** Returns the Tile from its name
      * @return retTile - a tile from the Board
      */
    public Tile getTile(TilesEnums name) {
        Tile retTile = new Tile(0,0);
    	for(Tile t : boardTiles.values()) {
    		if(t.getName() == name) {
    			retTile = t;
                break;
            }
    	}
    	return retTile;
    }

    /** Returns the tile name as a TilesEnums
      * @return TilesEnums - an enum containing the tile names
      */
    public TilesEnums getTileName(Point coords) {
    	return getTile(coords).getName();
    }

    /** Returns the tile type
      * @return TypeEnums- an enum containing the tile types
      */
    public TypeEnums getTileType(Point coords) {
    	return getTile(coords).getType();
    }

    /** Returns the position of a tile
      * @return Point - the coordinates of a tile
      */
    public Point getTilePos(TilesEnums name) {
    	return getTile(name).getPos();
    }

    /** Checks if a tile is flooded using coordinates
      * @return boolean - true if tile is flooded
      */
    public boolean isTileFlooded(Point coords) {
    	return getTile(coords).isFlooded();
    }

    /** Checks if a tile is flooded using tile name
      * @return boolean - true if tile is flooded
      */
    public boolean isTileFlooded(TilesEnums name) {
    	return getTile(name).isFlooded();
    }

    /** Checks if a tile has sunk using coordinates
      * @return boolean - true if tile has sunk
      */
    public boolean isTileSunk(Point coords) {
    	return getTile(coords).isSunk();
    }

    /** Checks if a tile has sunk using tile name
      * @return boolean - true if tile has sunk
      */
    public boolean isTileSunk(TilesEnums name) {
    	return getTile(name).isSunk();
    }

    /** Sets a tiles name, type and resets it if flooded
      * or sunk.
      * Uses the methods setName(), setType() and clean()
      * found in the Tile class
      */
    public void setTile(Point coords, TilesEnums name) {
    	getTile(coords).setName(name);
    	getTile(coords).setType(name);
        getTile(coords).clean();
    }

    /** Floods a tile from a tile name
      * Uses the method flood() found in the Tile class
      */    
    public void floodTile(TilesEnums name) {
    	getTile(name).flood();
    }

    /** Shores up a tile from a tile name if it is flooded
      * Uses the method shoreUp() found in the Tile class
      */  
    public void shoreUpTile(TilesEnums name) {
    	getTile(name).shoreUp();
    }

    /** Shores up a tile from coordinatesif it is flooded
      * Uses the method shoreUp() found in the Tile class
      */  
    public void shoreUpTile(Point p) {
        getTile(p).shoreUp();
    }

	/** returns a list of viable points, ie not sunk or sea
      * @return valid - list of all valid tiles
      */
	public List<Point> getValidTiles() {
        List<Point> valid = new ArrayList<>();
        for(Point point: getBoardTiles().keySet()){
            if (getTileType(point) != TypeEnums.SEA){
                valid.add(point);
            }
        }
		return valid;
	}

    /** return a list of viable tiles sandbag can be used on
      * @return valid - list of all flooded tiles
      */
	public List<Point> getSandbagsTiles() {
        List<Point> valid = new ArrayList<>();
        for(Point point: getBoardTiles().keySet()){
            if (getTileType(point) != TypeEnums.SEA && isTileFlooded(point)){
                valid.add(point);
            }
        }
		return valid;
	}
}
