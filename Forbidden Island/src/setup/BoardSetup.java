package setup;

import java.awt.Point;

import board.Board;
import board.TileStack;
import enums.TilesEnums;

/**
  * BoardSetup class for the game board
  * Gets an instance of the board and sets the tiles up
  * Puts sea tiles on the edges and randomises the tiles 
  * using the TileStack class
  * 
  * @author Cathal Ryan and Conor Kneafsey
  */
public class BoardSetup {
	private Board theBoard;
	private Point p;

	/**
	  * Constructor for BoardSetup
	  * gets an instance of the board
	  */
	public BoardSetup() {
		this.theBoard = Board.getInstance();
	}

	/** 
	  * Sets all tiles in the board grid as either sea tiles
	  * or the board game tiles using the setTile method in
	  * the Board class. 
	  * The sea tiles will always be located in the same place
	  * The tile locations are all within the TileStack class
	  * which also shuffles them. setTiles() simply pops the 
	  * TilesEnums from the TileStack and sets them on the board 
	  * with a coordinate
	  */
	public void setTiles() {
		TileStack names = new TileStack();
		for(int x=0; x < theBoard.getCols(); x++) {
			for(int y=0; y < theBoard.getRows(); y++) {
				p = new Point(x,y);
				if(p.equals(new Point(0,0)))
					theBoard.setTile(p, TilesEnums.SEA);

				else if(p.equals(new Point(1,0)))
					theBoard.setTile(p, TilesEnums.SEA);

				else if(p.equals(new Point(4,0)))
					theBoard.setTile(p, TilesEnums.SEA);

				else if(p.equals(new Point(5,0)))
					theBoard.setTile(p, TilesEnums.SEA);

				else if(p.equals(new Point(0,1)))
					theBoard.setTile(p, TilesEnums.SEA);

				else if(p.equals(new Point(5,1)))
					theBoard.setTile(p, TilesEnums.SEA);

				else if(p.equals(new Point(0,4)))
					theBoard.setTile(p, TilesEnums.SEA);

				else if(p.equals(new Point(5,4)))
					theBoard.setTile(p, TilesEnums.SEA);

				else if(p.equals(new Point(0,5)))
					theBoard.setTile(p, TilesEnums.SEA);

				else if(p.equals(new Point(1,5)))
					theBoard.setTile(p, TilesEnums.SEA);

				else if(p.equals(new Point(4,5)))
					theBoard.setTile(p, TilesEnums.SEA);

				else if(p.equals(new Point(5,5)))
					theBoard.setTile(p, TilesEnums.SEA);

				else 
					theBoard.setTile(p, names.pop());
			}
		}
	}
}