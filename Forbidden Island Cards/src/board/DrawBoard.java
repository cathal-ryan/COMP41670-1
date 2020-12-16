package board;

import java.util.ArrayList;
import java.util.List;
import java.awt.Point;

import board.Board;
import board.DrawTile;

public class DrawBoard {

	private Board theBoard;
	private DrawTile drawnTiles;

	public DrawBoard() {
		this.theBoard = Board.getInstance();
		this.drawnTiles = new DrawTile();
		drawFullBoard();
	}

	public void drawFullBoard() {
		for(int y=theBoard.getCols()-1; y>=0; y--) {
			for(int x=theBoard.getRows()-1; x>=0; x--) { 
				Point p = new Point(x,y);
				drawnTiles.createTile(theBoard.getTile(p));
			}
			drawnTiles.dispAllRows();
		}
	}

	public static void main(String[] args) {
		DrawBoard test = new DrawBoard();
	}
}