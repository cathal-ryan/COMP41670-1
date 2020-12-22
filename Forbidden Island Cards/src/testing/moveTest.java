package testing;

import org.junit.*;
import static org.junit.Assert.*;

import setup.Setup;

public class moveTest() {

	@testingublic void test_normalMovement() {
		Player tester = new Player(0, "Test Player", 1);
		Pawn testPawn = tester.getPawn();
		int startX = 3; int startY = 3;
		Point p = new Point(startX, startY);
		testPawn.setPos(p);
		BoardSetup bset = new BoardSetup();
		bset.setTiles();
		Board theBoard = Board.getInstance();

		
	}
}