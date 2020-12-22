package testing;

import org.junit.Test;

import board.Board;

import static org.junit.Assert.*;

import java.awt.Point;

import cards.TreasureCard;
import enums.TilesEnums;
import enums.TreasureCardEnums;
import gameplay.model.GameModel;
import player.Player;
import player.Team;
import setup.BoardSetup;

public class SandbagsTest {
    
    @Test // Test to see if detecting the player has sandbags in their hand
    public void checkPlayerHasSandbags() {
        Player player = new Player(0, "Test", 0);
        player.addCardtoHand(new TreasureCard(TreasureCardEnums.SANDBAGS));
        Team theTeam = Team.getInstance();
        theTeam.addPlayer(player);
        GameModel theGameModel = GameModel.getInstance();
        assertTrue("Has Sandbags been succesfully spotted in hand", theGameModel.checkHasCard(player,false));
    }

    @Test // Test to see if sandbags ready tiles are returned
    public void getSandbagsTilesTest() {
        BoardSetup bset = new BoardSetup();
        bset.setTiles();
        Board theBoard = Board.getInstance();

        theBoard.floodTile(TilesEnums.TEMPLE_OF_THE_MOON);

        assertEquals("Size of the list of tiles that can be sandbags after flooding 1", 1,theBoard.getSandbagsTiles().size());

    }
    
    @Test // Test to see does sandbags flood tile properly
    public void usingSandbagsTest() {
        BoardSetup bset = new BoardSetup();
        bset.setTiles();
        Board theBoard = Board.getInstance();
        theBoard.floodTile(TilesEnums.TEMPLE_OF_THE_MOON);
        GameModel theGameMod = GameModel.getInstance();
        Point p1 = theBoard.getTilePos(TilesEnums.TEMPLE_OF_THE_MOON);

        assertTrue("Flooded Status of tile before performing sandbags upon it", theBoard.isTileFlooded(p1));

        theGameMod.useSandbags(p1);

        assertFalse("Flooded Status of tile after performing sandbags upon it", theBoard.isTileFlooded(p1));

	}

}
