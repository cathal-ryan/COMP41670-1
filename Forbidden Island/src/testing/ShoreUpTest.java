package testing;

import org.junit.*;
import static org.junit.Assert.*;
import java.awt.Point;

import player.*;
import setup.BoardSetup;
import board.Board;
import enums.TilesEnums;
import gameplay.model.GameModel;

public class ShoreUpTest {
	Player tester = new Player(0, "Test Player", 1);
	int startX = 3; int startY = 3;
	Point p = new Point(startX, startY);
	BoardSetup bset = new BoardSetup();

	public void start() {
		Point p = new Point(startX, startY);
		tester.setPawnPos(p);
		bset.setTiles();
	}

	@Test
	public void test_shoreUpNormalTile() {
		start();
		assertFalse("Attemt to shore up unflooded tile", tester.pawnShoreUp(p));
	}

	@Test
	public void test_shoreUpFloodedTile() {
		start();
		p = new Point(startX+1, startY);
		Board theBoard = Board.getInstance();
		TilesEnums t = theBoard.getTileName(p);
		theBoard.floodTile(t);
		assertTrue("Shore up flooded tile", tester.pawnShoreUp(p));
	}

	@Test
	public void test_shoreUpSunkTile() {
		start();
		p = new Point(startX+1, startY);
		Board theBoard = Board.getInstance();
		TilesEnums t = theBoard.getTileName(p);
		theBoard.floodTile(t);
		theBoard.floodTile(t);
		assertFalse("Attempt shore up sunken tile", tester.pawnShoreUp(p));
	}

	@Test
	public void test_shoreUpSeaTile() {
		start();
		p = new Point(0,2);
		Point p2 = new Point(0,1);
		tester.setPawnPos(p);
		assertFalse("Attempt to shore up sea tile", tester.pawnShoreUp(p2));
	}

	@Test
	public void test_shoreUpFarTile() {
		start();
		assertFalse("Attempt to shore up tile far away", tester.pawnShoreUp(new Point(5,3)));
	}

	@Test
	public void test_TileIsShoredUp() {
		start();
		Team theTeam = Team.getInstance();
		theTeam.addPlayer(tester);

		GameModel theGM = GameModel.getInstance();
		Board theBoard = Board.getInstance();
		theGM.setNextPlayer();

		assertFalse("Tile is not flooded", theBoard.isTileFlooded(p));
		assertFalse("Tile is not shored up", theGM.shoreUp(p));
		TilesEnums t = theBoard.getTileName(p);
		theBoard.floodTile(t);
		assertTrue("Tile is Flooded", theBoard.isTileFlooded(p));
		assertTrue("Tile is shored up", theGM.shoreUp(p));
		assertFalse("Tile is not flooded", theBoard.isTileFlooded(p));
	}
}