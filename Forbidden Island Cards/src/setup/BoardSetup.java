package setup;

import java.awt.Point;

import board.Board;
import enums.TileStack;
import enums.TilesEnums;
import gameplay.view.GameOutputs;

public class BoardSetup {
	private Board theBoard;
	private Point p;
	private SetupOutputs setupOuts;

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

	public static void main(String[] args) {
		BoardSetup bSet = new BoardSetup();
		GameOutputs gameOutputs = new GameOutputs();
		Board theBoard = Board.getInstance();
		bSet.setTiles();
		gameOutputs.printBoard();
		theBoard.floodTile(TilesEnums.SILVER_GATE);
		theBoard.floodTile(TilesEnums.SILVER_GATE);
		theBoard.floodTile(TilesEnums.CORAL_PALACE);
		theBoard.floodTile(TilesEnums.CORAL_PALACE);
		gameOutputs.printBoard();
	}
}