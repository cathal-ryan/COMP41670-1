package testing;

import static org.junit.Assert.assertEquals;

import java.awt.Point;

import org.junit.Test;

import board.Board;
import cards.TreasureCard;
import enums.TilesEnums;
import enums.TreasureCardEnums;
import gameplay.model.GameModel;
import player.Player;
import player.Team;
import setup.BoardSetup;

public class GivingTreasureCardsTest {
    
	@Test // Check if correct players to give cards to is given based on tiles
	public void gettingTradingPartners() {
        Team theTeam = Team.getInstance();
        BoardSetup bset = new BoardSetup();
        bset.setTiles();
        Board theBoard = Board.getInstance();
        Point p1 = theBoard.getTilePos(TilesEnums.CAVE_OF_EMBERS);
        Point p2 = theBoard.getTilePos(TilesEnums.BREAKERS_BRIDGE);
        theTeam.addPlayer(new Player(0, "Test Player1", 1));
        theTeam.addPlayer(new Player(1, "Test Player4", 5)); // Messenger
        theTeam.addPlayer(new Player(2, "Test Player2", 2));
        theTeam.addPlayer(new Player(3, "Test Player3", 3));

        GameModel theModel = GameModel.getInstance();
        theModel.setNextPlayer();
        theModel.getPlayer(0).getPawn().setPos(p1);
        theModel.getPlayer(1).getPawn().setPos(p2);
        theModel.getPlayer(2).getPawn().setPos(p1);
        theModel.getPlayer(3).getPawn().setPos(p1);

        theModel.getTradePartners();
        assertEquals("Number of Players sharing tile at p1", 2, theModel.getTradePartners().size());
        theModel.setNextPlayer();
        assertEquals("Number of Players other than Messenger in the game", 3, theModel.getTradePartners().size());

    }

    @Test // Test for succcesful transfer of treasure cards from players.
	public void successfulTradeCheck() {
        Team theTeam = Team.getInstance();
        theTeam.addPlayer(new Player(0, "Test Player0", 1));
        Player p1 = new Player(1, "Test Player1", 2);
        GameModel theModel = GameModel.getInstance();
        theModel.setNextPlayer();
        theModel.addCardfromDeck( new TreasureCard(TreasureCardEnums.CRYSTAL_OF_FIRE));
        theModel.addCardfromDeck( new TreasureCard(TreasureCardEnums.CRYSTAL_OF_FIRE));
        theModel.addCardfromDeck( new TreasureCard(TreasureCardEnums.CRYSTAL_OF_FIRE));

        theModel.addCardfromPlayerA(p1,0);

        assertEquals("Player gained 1 card so should have 1 card", 1, p1.showHand().size());

        assertEquals("Player had 3 and gave 1 so should have 2", 2, theModel.getCurrentPlayer().showHand().size());

    }


}
