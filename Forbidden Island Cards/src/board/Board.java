package board;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.awt.Point;

import enums.TilesEnums;
import enums.TypeEnums;

public class Board {

	private static Board playBoard;
	private int rows;
	private int cols;
	private Point p;
	private HashMap<Point, Tile> boardTiles;

	public static Board getInstance(){
        if(playBoard == null){
            playBoard = new Board();
        }
        return playBoard;
    }

    private Board() {

    	this.rows = 6;
    	this.cols = 6;
    	this.boardTiles = new HashMap<Point, Tile>();

    	for(int x = 0; x < cols; x++) {
    		for(int y = 0; y < rows; y++) {
    			p = new Point(x,y);
    			boardTiles.put(p, new Tile(x,y));
    		}
    	}
    }

    public int getRows() {
    	return rows;
    }

    public int getCols() {
    	return cols;
    }

    public Tile getTile(Point coords) {
    	return boardTiles.get(coords);
	}
	
	public HashMap<Point, Tile> getBoardTiles(){
		return boardTiles;
	}

    public Tile getTile(TilesEnums name) {
    	for(Tile t : boardTiles.values()) {
    		if(t.getName() == name)
    			return t;
    	}
    	System.out.println("Error: Tile does not exist");
    	return null;
    }

    public TilesEnums getTileName(Point coords) {
    	return getTile(coords).getName();
    }

    public TypeEnums getTileType(Point coords) {
    	return getTile(coords).getType();
    }

    public Point getTilePos(TilesEnums name) {
    	return getTile(name).getPos();
    }

    public boolean isTileFlooded(Point coords) {
    	return getTile(coords).isFlooded();
    }

    public boolean isTileFlooded(TilesEnums name) {
    	return getTile(name).isFlooded();
    }

    public boolean isTileSunk(Point coords) {
    	return getTile(coords).isSunk();
    }

    public boolean isTileSunk(TilesEnums name) {
    	return getTile(name).isSunk();
    }

    public void setTile(Point coords, TilesEnums name) {
    	getTile(coords).setName(name);
    	getTile(coords).setType(name);
        getTile(coords).clean();
    }

    public void floodTile(TilesEnums name) {
    	getTile(name).flood();
    }

    public void shoreUpTile(TilesEnums name) {
    	getTile(name).shoreUp();
    }

    public void shoreUpTile(Point p) {
        getTile(p).shoreUp();
    }

	// returns a list of viable points, ie not sunk or sea
	public List<Point> getValidTiles() {
        List<Point> valid = new ArrayList<>();
        for(Point p: getBoardTiles().keySet()){
            if (getTileType(p) != TypeEnums.SEA){
                valid.add(p);
            }
        }
		return valid;
	}

	public List<Point> getSandbagsTiles() {
        List<Point> valid = new ArrayList<>();
        for(Point p: getBoardTiles().keySet()){
            if (getTileType(p) != TypeEnums.SEA && isTileFlooded(p)){
                valid.add(p);
            }
        }
		return valid;
	}
}
