package testing;

import static org.junit.Assert.*;
import java.awt.Point;
import java.util.List;

import org.junit.Test;

import board.Board;
import gameplay.view.GameOutputs;
import pawns.Pawn;
import player.Player;
import setup.BoardSetup;

public class SwimTest {

	@Test
	public void normalSwimTest() {
		Player tester = new Player(0, "Test Player", 1);
		Pawn testPawn = tester.getPawn();
		int startX = 3; int startY = 3;
		Point p = new Point(startX,startY);
		testPawn.setPos(p);
	    BoardSetup bset= new BoardSetup(); bset.setTiles();
		Board theBoard=Board.getInstance();
		for(int i=0;i<2;i++) {
			theBoard.floodTile(theBoard.getTileName(new Point (startX-2,startY)));
			theBoard.floodTile(theBoard.getTileName(new Point (startX+1,startY)));
			theBoard.floodTile(theBoard.getTileName(new Point (startX,startY+1)));
			theBoard.floodTile(theBoard.getTileName(new Point (startX,startY-1)));
			theBoard.floodTile(theBoard.getTileName(p));
		}		

		assertFalse("Incorrect swim",testPawn.canSwim());
	}

	@Test
	public void diverSwimTest() {
		Player tester = new Player(0, "Test Diver", 0);
		Pawn testPawn = tester.getPawn();
		int startX = 3; int startY = 3;
		Point p = new Point(startX,startY);
		testPawn.setPos(p);
	    BoardSetup bset= new BoardSetup(); bset.setTiles();
		Board theBoard=Board.getInstance();
		for(int i=0;i<2;i++) {
			theBoard.floodTile(theBoard.getTileName(new Point (startX-2,startY)));
			theBoard.floodTile(theBoard.getTileName(new Point (startX+1,startY)));
			theBoard.floodTile(theBoard.getTileName(new Point (startX,startY+1)));
			theBoard.floodTile(theBoard.getTileName(new Point (startX,startY-1)));
			theBoard.floodTile(theBoard.getTileName(new Point (1,3)));
			theBoard.floodTile(theBoard.getTileName(new Point (2,2)));
			theBoard.floodTile(theBoard.getTileName(new Point (2,4)));
			theBoard.floodTile(theBoard.getTileName(new Point (3,1)));
			theBoard.floodTile(theBoard.getTileName(new Point (3,5)));
			theBoard.floodTile(theBoard.getTileName(new Point (4,2)));
			theBoard.floodTile(theBoard.getTileName(new Point (4,4)));
			theBoard.floodTile(theBoard.getTileName(new Point (5,3)));
			theBoard.floodTile(theBoard.getTileName(p));
		}		
		assertTrue("Sunk conidtions",testPawn.canSwim());
	}


    public static void main(String[] args) {
		Player tester = new Player(0, "Test Diver", 0);
		Pawn testPawn = tester.getPawn();
		int startX = 3; int startY = 3;
		Point p = new Point(startX,startY);
		testPawn.setPos(p);
	    BoardSetup bset= new BoardSetup(); bset.setTiles();
		Board theBoard=Board.getInstance();
		for(int i=0;i<2;i++) {
			theBoard.floodTile(theBoard.getTileName(new Point (startX-1,startY)));
			theBoard.floodTile(theBoard.getTileName(new Point (startX+1,startY)));
			theBoard.floodTile(theBoard.getTileName(new Point (startX,startY+1)));
			theBoard.floodTile(theBoard.getTileName(new Point (startX,startY-1)));
			theBoard.floodTile(theBoard.getTileName(new Point (1,3)));
			theBoard.floodTile(theBoard.getTileName(new Point (2,2)));
			theBoard.floodTile(theBoard.getTileName(new Point (2,4)));
			theBoard.floodTile(theBoard.getTileName(new Point (3,1)));
			theBoard.floodTile(theBoard.getTileName(new Point (3,5)));
			theBoard.floodTile(theBoard.getTileName(new Point (4,2)));
			theBoard.floodTile(theBoard.getTileName(new Point (4,4)));
			theBoard.floodTile(theBoard.getTileName(new Point (5,3)));
			theBoard.floodTile(theBoard.getTileName(p));
		}	
		testPawn.canSwim();
		List<Point> swims = testPawn.getViableSwims();
		GameOutputs theGO = new GameOutputs();
		theGO.whereSwim(swims);
		assertTrue("Sunk conidtions",testPawn.canSwim());
	}	
}
