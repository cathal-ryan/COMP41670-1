package testing;

import org.junit.*;
import static org.junit.Assert.*;
import java.awt.Point;

import setup.BoardSetup;
import player.Player;
import board.Board;
import enums.TilesEnums;

public class MoveTest {

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
		assertTrue("Move down", tester.movePawn('s'));
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
		assertTrue("Move left", tester.movePawn('a'));
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
		assertTrue("Move right", tester.movePawn('d'));
		assertEquals("pawn has moved position", startX+1, (int) tester.getPawnPos().getX());
		assertEquals("pawn has moved position", startY, (int) tester.getPawnPos().getY());
	}

	@Test
	public void test_moveBadInput() {
		Player tester = new Player(0, "Test Player", 1);
		int startX = 3; int startY = 3;
		Point p = new Point(startX, startY);
		tester.setPawnPos(p);
		BoardSetup bset = new BoardSetup();
		bset.setTiles();
		assertFalse("Bad Input, not w,a,s,d", tester.movePawn('g'));
		assertEquals("pawn has not moved position", startX, (int) tester.getPawnPos().getX());
		assertEquals("pawn has not moved position", startY, (int) tester.getPawnPos().getY());
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
		assertTrue("Move right", tester.movePawn('d'));
		assertEquals("pawn has moved position", startX+1, (int) tester.getPawnPos().getX());
		assertEquals("pawn has moved position", startY, (int) tester.getPawnPos().getY());
	}

	@Test
	public void test_moveToSunk() {
		Player tester = new Player(0, "Test Player", 1);
		int startX = 3; int startY = 3;
		Point p = new Point(startX, startY);
		Point p2 = new Point(startX, startY+1);
		tester.setPawnPos(p);
		BoardSetup bset = new BoardSetup();
		bset.setTiles();
		Board theBoard = Board.getInstance();
		TilesEnums t = theBoard.getTileName(p2);
		theBoard.floodTile(t);
		theBoard.floodTile(t);
		assertFalse("Move up", tester.movePawn('w'));
		assertEquals("pawn has not moved position", startX, (int) tester.getPawnPos().getX());
		assertEquals("pawn has not moved position", startY, (int) tester.getPawnPos().getY());
	}

	@Test
	public void test_moveToSea() {
		Player tester = new Player(0, "Test Player", 1);
		int startX = 1; int startY = 1;
		Point p = new Point(startX, startY);
		tester.setPawnPos(p);
		BoardSetup bset = new BoardSetup();
		bset.setTiles();
		assertFalse("Move down", tester.movePawn('s'));
		assertEquals("pawn has not moved position", startX, (int) tester.getPawnPos().getX());
		assertEquals("pawn has not moved position", startY, (int) tester.getPawnPos().getY());
	}

	@Test
	public void test_moveOffBoardLeft() {
		Player tester = new Player(0, "Test Player", 1);
		int startX = 0; int startY = 2;
		Point p = new Point(startX, startY);
		tester.setPawnPos(p);
		BoardSetup bset = new BoardSetup();
		bset.setTiles();
		assertFalse("Move off board left", tester.movePawn('a'));
		assertEquals("pawn has not moved position", startX, (int) tester.getPawnPos().getX());
		assertEquals("pawn has not moved position", startY, (int) tester.getPawnPos().getY());
	}

	@Test
	public void test_moveOffBoardRight() {
		Player tester = new Player(0, "Test Player", 1);
		int startX = 5; int startY = 2;
		Point p = new Point(startX, startY);
		tester.setPawnPos(p);
		BoardSetup bset = new BoardSetup();
		bset.setTiles();
		assertFalse("Move off board right", tester.movePawn('d'));
		assertEquals("pawn has not moved position", startX, (int) tester.getPawnPos().getX());
		assertEquals("pawn has not moved position", startY, (int) tester.getPawnPos().getY());
	}

	@Test
	public void test_moveOffBoardUp() {
		Player tester = new Player(0, "Test Player", 1);
		int startX = 3; int startY = 5;
		Point p = new Point(startX, startY);
		tester.setPawnPos(p);
		BoardSetup bset = new BoardSetup();
		bset.setTiles();
		assertFalse("Move off board up", tester.movePawn('w'));
		assertEquals("pawn has not moved position", startX, (int) tester.getPawnPos().getX());
		assertEquals("pawn has not moved position", startY, (int) tester.getPawnPos().getY());
	}

	@Test
	public void test_moveOffBoardDown() {
		Player tester = new Player(0, "Test Player", 1);
		int startX = 3; int startY = 0;
		Point p = new Point(startX, startY);
		tester.setPawnPos(p);
		BoardSetup bset = new BoardSetup();
		bset.setTiles();
		assertFalse("Move off board down", tester.movePawn('s'));
		assertEquals("pawn has not moved position", startX, (int) tester.getPawnPos().getX());
		assertEquals("pawn has not moved position", startY, (int) tester.getPawnPos().getY());
	}
}