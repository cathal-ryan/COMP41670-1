package testing;

import org.junit.*;
import static org.junit.Assert.*;
import java.awt.Point;

import board.*;
import enums.TilesEnums;
import enums.TypeEnums;
import setup.BoardSetup;

public class BoardTest {

	private Board theBoard;

	@Test
	public void OutOfBounds() {
		theBoard = Board.getInstance();
		BoardSetup theSetup = new BoardSetup();
		theSetup.setTiles();
		assertNull("Check if (-1,-1) is out of bounds", theBoard.getTile(new Point(-1,-1)));
		assertNotNull("Check if (3,3) is initialised", theBoard.getTile(new Point(3,3)));
		assertNull("Check if (6,6) is out of bounds", theBoard.getTile(new Point(6,6)));
	}
	
	@Test
	public void checkTileAdded() {
		theBoard = Board.getInstance();
		BoardSetup theSetup = new BoardSetup();
		theSetup.setTiles();
		Tile expected = new Tile(0,0);
		Tile actual = theBoard.getTile(new Point(0,0));
		assertSame("Check if names are the same", expected.getName(), actual.getName());
		assertSame("Check if types are the same", expected.getType(), actual.getType());
		boolean test = actual.getPos().equals(expected.getPos());
		assertTrue("Check if position is the same", test);
	}

	@Test
	public void checkTileChanged() {
		theBoard = Board.getInstance();
		BoardSetup theSetup = new BoardSetup();
		theSetup.setTiles();
		Tile t = new Tile(2,2);
		t.setName(TilesEnums.CAVE_OF_SHADOWS);
		t.setType(TilesEnums.CAVE_OF_SHADOWS);
		Tile actual = theBoard.getTile(TilesEnums.CAVE_OF_SHADOWS);
		assertSame("Check if names are the same", t.getName(), actual.getName());
		assertSame("Check if types are the same", t.getType(), actual.getType());
	}

	@Test
	public void checkTileFlooded() {
		theBoard = Board.getInstance();
		BoardSetup theSetup = new BoardSetup();
		theSetup.setTiles();
		assertFalse("Check tile is initlialised not flooded", theBoard.isTileFlooded(TilesEnums.CAVE_OF_SHADOWS));
		theBoard.floodTile(TilesEnums.CAVE_OF_SHADOWS);
		assertTrue("Check tile is now flooded", theBoard.isTileFlooded(TilesEnums.CAVE_OF_SHADOWS));
	}

	@Test
	public void shoreUpTile() {
		theBoard = Board.getInstance();
		BoardSetup theSetup = new BoardSetup();
		theSetup.setTiles();
		Point p = theBoard.getTilePos(TilesEnums.BRONZE_GATE);
		theBoard.floodTile(TilesEnums.BRONZE_GATE);
		assertTrue("Check tile is flooded", theBoard.isTileFlooded(TilesEnums.BRONZE_GATE));
		theBoard.shoreUpTile(p);
		assertFalse("Check tile is shored up using pos", theBoard.isTileFlooded(p));
		theBoard.floodTile(TilesEnums.BRONZE_GATE);
		assertTrue("Check tile is flooded again", theBoard.isTileFlooded(TilesEnums.BRONZE_GATE));
		theBoard.shoreUpTile(TilesEnums.BRONZE_GATE);
		assertFalse("Check tile is shored up using name", theBoard.isTileFlooded(TilesEnums.BRONZE_GATE));
	}

	@Test
	public void checkSank() {
		theBoard = Board.getInstance();
		BoardSetup theSetup = new BoardSetup();
		theSetup.setTiles();
		theBoard.floodTile(TilesEnums.IRON_GATE);
		assertFalse("Check if tile is sunk after 1 flood", theBoard.isTileSunk(TilesEnums.IRON_GATE));
		theBoard.floodTile(TilesEnums.IRON_GATE);
		assertTrue("Check if tile is sunk after 2 floods", theBoard.isTileSunk(TilesEnums.IRON_GATE));
	}

	@Test
	public void cantShoreSank() {
		theBoard = Board.getInstance();
		BoardSetup theSetup = new BoardSetup();
		theSetup.setTiles();
		theBoard.floodTile(TilesEnums.WATCHTOWER);
		theBoard.floodTile(TilesEnums.WATCHTOWER);
		assertTrue("Check tile is sunk", theBoard.isTileSunk(TilesEnums.WATCHTOWER));
		theBoard.shoreUpTile(TilesEnums.WATCHTOWER);
		assertTrue("Check tile is still sunk", theBoard.isTileSunk(TilesEnums.WATCHTOWER));
	}

	@Test
	public void sunkTileIsSea() {
		theBoard = Board.getInstance();
		BoardSetup theSetup = new BoardSetup();
		theSetup.setTiles();
		Point p = theBoard.getTilePos(TilesEnums.FOOLS_LANDING);
		assertNotSame("Check not sea before sinking", TypeEnums.SEA, theBoard.getTileType(p));
		theBoard.floodTile(TilesEnums.FOOLS_LANDING);
		theBoard.floodTile(TilesEnums.FOOLS_LANDING);
		assertTrue("Tile should be sunk", theBoard.isTileSunk(TilesEnums.FOOLS_LANDING));
		assertSame("Check tile is now sea", TypeEnums.SEA, theBoard.getTileType(p));
	}

	@Test
	public void allTilesSet() {
		theBoard = Board.getInstance();
		BoardSetup theSetup = new BoardSetup();
		Point p = new Point(5,3);
		theBoard.setTile(p, TilesEnums.SEA);
		assertSame("Check tiles are Sea", TypeEnums.SEA, theBoard.getTileType(new Point(1,0)));
		assertSame("Check tiles are Sea", TypeEnums.SEA, theBoard.getTileType(p));
		theSetup.setTiles();
		assertNotSame("Check tile has been changed from sea", TypeEnums.SEA, theBoard.getTileType(p));
	}

	@Test
	public void tilesRandomised() {
		theBoard = Board.getInstance();
		BoardSetup theSetup = new BoardSetup();
		theSetup.setTiles();
		Point p = new Point(1,4);
		TilesEnums name = theBoard.getTileName(p);
		theSetup.setTiles();
		TilesEnums name2 = theBoard.getTileName(p);
		assertNotSame("Check tilename at same pos is different", name, name2);
	}
}