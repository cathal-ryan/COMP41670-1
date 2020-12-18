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
    private DrawTile drawnTiles;

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
        this.drawnTiles = new DrawTile();
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

    public Tile getTile(Point coords) {
    	return boardTiles.get(coords);
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
    }

    public void floodTile(TilesEnums name) {
    	getTile(name).flood();
    }

    public void shoreUpTile(TilesEnums name) {
    	getTile(name).shoreUp();
    }

    public void drawBoard() {
        for(int y=getCols()-1; y>=0; y--) {
            for(int x=getRows()-1; x>=0; x--) { 
                Point p = new Point(x,y);
                drawnTiles.createTile(getTile(p));
            }
            drawnTiles.dispAllRows();
        }
    }

    public static void main(String[] args) {
    	Board thisBoard = Board.getInstance();
		Point boardPos = new Point(3,1);

    	System.out.println("\nTile name is: " + thisBoard.getTileName(boardPos));
		System.out.println("Tile type is: " + thisBoard.getTileType(boardPos));

		thisBoard.setTile(boardPos, TilesEnums.IRON_GATE);
		System.out.println("\nTile name is: " + thisBoard.getTileName(boardPos));
		System.out.println("Tile type is: " + thisBoard.getTileType(boardPos));
		System.out.println("Tile position is: " + thisBoard.getTilePos(TilesEnums.IRON_GATE));
		System.out.println("Is it flooded?: " + thisBoard.isTileFlooded(boardPos));
		System.out.println("Is it sunk?: " + thisBoard.isTileSunk(boardPos));
        thisBoard.getTile(boardPos);

		thisBoard.floodTile(TilesEnums.IRON_GATE);
		System.out.println("\nIs it flooded?: " + thisBoard.isTileFlooded(boardPos));

		thisBoard.shoreUpTile(TilesEnums.IRON_GATE);
		System.out.println("\nIs it flooded?: " + thisBoard.isTileFlooded(boardPos));

		thisBoard.floodTile(TilesEnums.IRON_GATE);
		System.out.println("\nHas it sank?: " + thisBoard.isTileSunk(boardPos));
		thisBoard.floodTile(TilesEnums.IRON_GATE);
		System.out.println("Has it sank?: " + thisBoard.isTileSunk(boardPos));
		
		System.out.println("\nTile name is: " + thisBoard.getTileName(boardPos));
		System.out.println("Tile type is: " + thisBoard.getTileType(boardPos));

		thisBoard.shoreUpTile(TilesEnums.IRON_GATE);
		System.out.println("\nIs it flooded?: " + thisBoard.isTileFlooded(boardPos));

        thisBoard.drawBoard();
    }
	
}
