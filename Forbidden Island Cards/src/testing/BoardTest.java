package testing;

import org.junit.*;
import static org.junit.Assert.*;
import java.awt.Point;

import board.*;
import enums.TilesEnums;

public class BoardTest {

	private Board theBoard;

	@Test
	public void test_OutOfBounds() {
		theBoard = Board.getInstance();
		assertNull("Check if (-1,-1) is out of bounds", theBoard.getTile(new Point(-1,-1)));
		assertNotNull("Check if (3,3) is initialised", theBoard.getTile(new Point(3,3)));
		assertNull("Check if (6,6) is out of bounds", theBoard.getTile(new Point(6,6)));
	}
	
	@Test
	public void test_checkTileAdded() {
		theBoard = Board.getInstance();
		Tile expected = new Tile(0,0);
		Tile actual = theBoard.getTile(new Point(0,0));
		assertSame("Check if names are the same", expected.getName(), actual.getName());
		assertSame("Check if types are the same", expected.getType(), actual.getType());
		boolean test = actual.getPos().equals(expected.getPos());
		assertTrue("Check if position is the same", test);
	}

	@Test
	public void test_checkTileChanged() {
		theBoard = Board.getInstance();
		Tile t = new Tile(2,2);
		t.setName(TilesEnums.CAVE_OF_SHADOWS);
		t.setType(TilesEnums.CAVE_OF_SHADOWS);
		theBoard.setTile(new Point(2,2),TilesEnums.CAVE_OF_SHADOWS);
		Tile actual = theBoard.getTile(new Point(2,2));
		assertSame("Check if names are the same", t.getName(), actual.getName());
		assertSame("Check if types are the same", t.getType(), actual.getType());
	}

	@Test
	public void test_checkTileFlooded() {
		theBoard = Board.getInstance();
		Point p = new Point(5,5);
		theBoard.setTile(p,TilesEnums.CAVE_OF_SHADOWS);
		assertFalse("Check tile is initlialised not flooded", theBoard.isTileFlooded(TilesEnums.CAVE_OF_SHADOWS));
		theBoard.floodTile(TilesEnums.CAVE_OF_SHADOWS);
		assertTrue("Check tile is now flooded", theBoard.isTileFlooded(TilesEnums.CAVE_OF_SHADOWS));
	}

	@Test
	public void test_shoreUpTile() {
		theBoard = Board.getInstance();
		Point p = new Point(2,1);
		theBoard.setTile(p,TilesEnums.BRONZE_GATE);
		theBoard.floodTile(TilesEnums.BRONZE_GATE);
		assertTrue("Check tile is flooded", theBoard.isTileFlooded(TilesEnums.BRONZE_GATE));
		theBoard.shoreUpTile(p);
		assertFalse("Check tile is shored up using pos", theBoard.isTileFlooded(TilesEnums.BRONZE_GATE));
		theBoard.floodTile(TilesEnums.BRONZE_GATE);
		assertTrue("Check tile is flooded again", theBoard.isTileFlooded(TilesEnums.BRONZE_GATE));
		theBoard.shoreUpTile(TilesEnums.BRONZE_GATE);
		assertFalse("Check tile is shored up using name", theBoard.isTileFlooded(TilesEnums.BRONZE_GATE));
	}

	@Test
	public void test_checkSank() {
		theBoard = Board.getInstance();
		Point p = new Point(3,1);
		theBoard.setTile(p,TilesEnums.IRON_GATE);
		theBoard.floodTile(TilesEnums.IRON_GATE);
		assertFalse("Check if tile is sunk after 1 flood", theBoard.isTileSunk(TilesEnums.IRON_GATE));
		theBoard.floodTile(TilesEnums.IRON_GATE);
		assertTrue("Check if tile is sunk after 2 floods", theBoard.isTileSunk(TilesEnums.IRON_GATE));
	}
}