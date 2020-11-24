package board;

import java.util.HashMap;
import java.util.Arrays;
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
    	// this.coords = new Point();

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

    private Tile getTile(Point coords) {
    	return boardTiles.get(coords);
    }

    public TilesEnums getTileName(Point coords) {
    	return getTile(coords).getName();
    }

    public TypeEnums getTileType(Point coords) {
    	return getTile(coords).getType();
    }

    public Point getTilePos(Point coords) {
    	return getTile(coords).getPos();
    }

    public boolean isTileFlooded(Point coords) {
    	return getTile(coords).isFlooded();
    }

    public boolean isTileSunk(Point coords) {
    	return getTile(coords).isSunk();
    }

    public void setTile(Point coords, TilesEnums name) {
    	getTile(coords).setType(name);
    }

    public void floodTile(Point coords) {
    	getTile(coords).flood();
    }

    public void shoreUpTile(Point coords) {
    	getTile(coords).shoreUp();
    }

    public static void main(String[] args) {
    	Board thisBoard = new Board();
		Point boardPos = new Point(0,0);

    	System.out.println("Tile name is: " + thisBoard.getTileName(boardPos));
		System.out.println("Tile type is: " + thisBoard.getTileType(boardPos));

		thisBoard.setTile(boardPos, TilesEnums.CAVE_OF_SHADOWS);
		System.out.println("Tile name is: " + thisBoard.getTileName(boardPos));
		System.out.println("Tile type is: " + thisBoard.getTileType(boardPos));

		System.out.println("Tile position is: " + thisBoard.getTilePos(boardPos).toString());

		System.out.println("Is it flooded?: " + thisBoard.isTileFlooded(boardPos));
		System.out.println("Is it sunk?: " + thisBoard.isTileSunk(boardPos));

		thisBoard.floodTile(boardPos);
		System.out.println("Is it flooded?: " + thisBoard.isTileFlooded(boardPos));

		thisBoard.shoreUpTile(boardPos);
		System.out.println("Is it flooded?: " + thisBoard.isTileFlooded(boardPos));

		thisBoard.floodTile(boardPos);
		thisBoard.floodTile(boardPos);
		System.out.println("Has it sank?: " + thisBoard.isTileSunk(boardPos));
    }
	
}