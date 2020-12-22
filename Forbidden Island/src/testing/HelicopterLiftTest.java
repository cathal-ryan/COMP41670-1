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

public class HelicopterLiftTest {
    
    @Test // Test to see if detecting the player has sandbags in their hand
    public void checkPlayerHasHeliLift() {
        Player player = new Player(0, "Test", 0);
        player.addCardtoHand(new TreasureCard(TreasureCardEnums.HELICOPTER_LIFT));
        Team theTeam = Team.getInstance();
        theTeam.addPlayer(player);
        GameModel theGameModel = GameModel.getInstance();
        assertTrue("Has Heli lift been succesfully spotted in hand", theGameModel.checkHasCard(player,true));
    }

    @Test // Test to see if sandbags ready tiles are returned
    public void getHelicopterTilesTest() {
        BoardSetup bset = new BoardSetup();
        bset.setTiles();
        Board theBoard = Board.getInstance();

        theBoard.floodTile(TilesEnums.CAVE_OF_EMBERS);
        theBoard.floodTile(TilesEnums.CAVE_OF_EMBERS);

        assertEquals("Size of the list of tiles you can heli lift to after sinking 1", 23,theBoard.getValidTiles().size());

    }
    
    @Test // Test to see does helicopter lift move player properly
    public void usingHelicopterTest() {
        BoardSetup bset = new BoardSetup();
        bset.setTiles();
        Board theBoard = Board.getInstance();
        GameModel theGameMod = GameModel.getInstance();
        Point p1 = theBoard.getTilePos(TilesEnums.CAVE_OF_EMBERS);
        Player player1 = new Player(0,"test",0);

        assertNotEquals("Player shouldn't be on tile before helicopter lifting there",p1, player1.getPawnPos());

        theGameMod.heliMovePlayer(player1, p1);

        assertEquals("Player should be on tile after helicopter lifting there",p1, player1.getPawnPos());

	}

}
