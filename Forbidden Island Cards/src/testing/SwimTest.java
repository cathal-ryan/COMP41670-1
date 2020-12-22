package testing;

import static org.junit.Assert.*;
import java.awt.Point;

import org.junit.Test;

import board.Board;
import pawns.Pawn;
import player.Player;
import setup.BoardSetup;

public class SwimTest {

	@Test
	public void normalSwimTest() { // No special swim abilities player test
		Player tester = new Player(0, "Test Player", 1);
		Pawn testPawn = tester.getPawn();
		int startX = 3; int startY = 3;
		Point p = new Point(startX,startY);
		testPawn.setPos(p);
	    BoardSetup bset= new BoardSetup(); bset.setTiles();
		Board theBoard=Board.getInstance();
		for(int i=0;i<2;i++) {
			theBoard.floodTile(theBoard.getTileName(new Point (startX+1,startY)));
			theBoard.floodTile(theBoard.getTileName(new Point (startX,startY+1)));
			theBoard.floodTile(theBoard.getTileName(new Point (startX,startY-1)));
			theBoard.floodTile(theBoard.getTileName(p));
		}		

		assertTrue("Player's swim ability with only one option available",testPawn.canSwim());
		
		theBoard.floodTile(theBoard.getTileName(new Point (startX-1,startY)));
		theBoard.floodTile(theBoard.getTileName(new Point (startX-1,startY)));

		assertFalse("Player's swim ability with no options",testPawn.canSwim());

	}

	@Test
	public void diverPilotSwimTest() { //Swim ability with the diver and pilot
		Player tester = new Player(0, "Test Diver", 0);
		Player tester2 = new Player(0, "Test Pilot", 4 );
		Pawn testPawn = tester.getPawn();
		Pawn testPawn2 = tester2.getPawn();
		int startX = 3; int startY = 3;
		Point p = new Point(startX,startY);
		testPawn.setPos(p);
		testPawn2.setPos(p);
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
		assertTrue("Diver can swim with lots of tiles around him sunk",testPawn.canSwim());
		assertTrue("Pilot can swim with lots of tiles around him sunk",testPawn2.canSwim());
	}

	@Test
	public void explorerSwimTest() { // No special swim abilities player test
		Player tester = new Player(0, "Test Player", 2);
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
			theBoard.floodTile(theBoard.getTileName(p));
		}		

		assertTrue("Explorer can swim diagonally with adjacent tiles gone",testPawn.canSwim());

	}
}
