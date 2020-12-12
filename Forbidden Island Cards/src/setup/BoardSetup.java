package setup;

import java.awt.Point;

import board.Board;
import enums.TileStack;
import enums.TilesEnums;

public class BoardSetup {
	private Board theBoard;
	private Point p;

	public BoardSetup() {
		this.theBoard = Board.getInstance();
	}

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

	public void displayBoard() {
		for(int x=0; x < theBoard.getCols(); x++) {
			for(int y=0; y < theBoard.getRows(); y++) {
				p = new Point(x,y);
				System.out.println("\nTile Pos is: " +p.toString());
				System.out.println("Tile name is: " +theBoard.getTileName(p));
				System.out.println("Tile type is: " +theBoard.getTileType(p));
			}
		}
	}

	public static void main(String[] args) {
		BoardSetup bSet = new BoardSetup();
		bSet.setTiles();
		bSet.displayBoard();
	}
}