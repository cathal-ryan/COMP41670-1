package testing;

import org.junit.*;
import static org.junit.Assert.*;
import java.awt.Point;

import setup.BoardSetup;
import player.Player;

public class moveTest {

	@Test
	public void test_moveUp() {
		Player tester = new Player(0, "Test Player", 1);
		int startX = 3; int startY = 3;
		Point p = new Point(startX, startY);
		tester.setPawnPos(p);
		BoardSetup bset = new BoardSetup();
		bset.setTiles();
		assertTrue("Move up", tester.movePawn('w'));
		assertEquals("pawn has moved position", startX, (int) tester.getPawnPos().getX());
		assertEquals("pawn has moved position", startY+1, (int) tester.getPawnPos().getY());
	}

	@Test
	public void test_moveDown() {
		Player tester = new Player(0, "Test Player", 1);
		int startX = 3; int startY = 3;
		Point p = new Point(startX, startY);
		tester.setPawnPos(p);
		BoardSetup bset = new BoardSetup();
		bset.setTiles();
		assertTrue("Move up", tester.movePawn('s'));
		assertEquals("pawn has moved position", startX, (int) tester.getPawnPos().getX());
		assertEquals("pawn has moved position", startY-1, (int) tester.getPawnPos().getY());
	}

	@Test
	public void test_moveLeft() {
		Player tester = new Player(0, "Test Player", 1);
		int startX = 3; int startY = 3;
		Point p = new Point(startX, startY);
		tester.setPawnPos(p);
		BoardSetup bset = new BoardSetup();
		bset.setTiles();
		assertTrue("Move up", tester.movePawn('a'));
		assertEquals("pawn has moved position", startX-1, (int) tester.getPawnPos().getX());
		assertEquals("pawn has moved position", startY, (int) tester.getPawnPos().getY());
	}

	@Test
	public void test_moveRight() {
		Player tester = new Player(0, "Test Player", 1);
		int startX = 3; int startY = 3;
		Point p = new Point(startX, startY);
		tester.setPawnPos(p);
		BoardSetup bset = new BoardSetup();
		bset.setTiles();
		assertTrue("Move up", tester.movePawn('d'));
		assertEquals("pawn has moved position", startX+1, (int) tester.getPawnPos().getX());
		assertEquals("pawn has moved position", startY, (int) tester.getPawnPos().getY());
	}

	@Test
	public void test_moveToFlooded() {
		Player tester = new Player(0, "Test Player", 1);
		int startX = 3; int startY = 3;
		Point p = new Point(startX, startY);
		Point p2 = new Point(startX+1, startY);
		tester.setPawnPos(p);
		BoardSetup bset = new BoardSetup();
		bset.setTiles();
		Board theBoard = Board.getInstance();
		TilesEnums t = theBoard.getTileName(p2);
		theBoard.floodTile(t);
		a
	}
}