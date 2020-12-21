package testing;

import static org.junit.Assert.assertEquals;

import java.awt.Point;
import java.lang.ProcessBuilder.Redirect.Type;

import org.junit.Test;

import board.Board;
import cards.Hand;
import cards.TreasureCard;
import enums.TilesEnums;
import enums.TreasureCardEnums;
import enums.TypeEnums;
import gameplay.control.Controller;
import gameplay.model.GameModel;
import gameplay.model.TreasureHandler;
import player.Player;
import player.Team;
import setup.BoardSetup;

public class GivingTreasureCardsTest {
    
	@Test // Check if correct players to give cards to is given
	public void gettingTradingPartners() {
        Team theTeam = Team.getInstance();
        BoardSetup bset = new BoardSetup();
        bset.setTiles();
        Board theBoard = Board.getInstance();
        Point p1 = theBoard.getTilePos(TilesEnums.CAVE_OF_EMBERS);
        Point p2 = theBoard.getTilePos(TilesEnums.BREAKERS_BRIDGE);
        theTeam.addPlayer(new Player(0, "Test Player1", 1));
        theTeam.addPlayer(new Player(1, "Test Player4", 5));
        theTeam.addPlayer(new Player(2, "Test Player2", 2));
        theTeam.addPlayer(new Player(3, "Test Player3", 3));

        GameModel theModel = GameModel.getInstance();
        theModel.setNextPlayer();
        theModel.getPlayer(0).getPawn().setPos(p1);
        theModel.getPlayer(1).getPawn().setPos(p2);
        theModel.getPlayer(2).getPawn().setPos(p1);
        theModel.getPlayer(3).getPawn().setPos(p1);

        theModel.getTradePartners();
        assertEquals("Model is not giving trade partners correctly", 2, theModel.getTradePartners().size());
        theModel.setNextPlayer();
        assertEquals("Model is not allowing messenger to give card to anyone", 3, theModel.getTradePartners().size());

    }

    @Test // Check if player can't trade if they dont have cards for it
	public void cardForTradeCheck() {

        GameModel theModel = GameModel.getInstance();

        
        
        
        assertEquals("Model is not recognising player does not have enough cards in hand", 3, theModel.capture());

        for(int i=0;i<5;i++)
            theModel.getCurrentPlayer().addCardtoHand(new TreasureCard(TreasureCardEnums.CRYSTAL_OF_FIRE) );

        assertEquals("Model is not allowing for treasure capture", 0, theModel.capture());

        Point p2 = theBoard.getTilePos(TilesEnums.BRONZE_GATE);
        theModel.getCurrentPlayer().getPawn().setPos(p2);

        assertEquals("Model is not recognising player is not on treasure tile", 2, theModel.capture());
        
        theModel.getCurrentPlayer().getPawn().setPos(p1);
        for(int i=0;i<5;i++)
            theModel.getCurrentPlayer().addCardtoHand(new TreasureCard(TreasureCardEnums.CRYSTAL_OF_FIRE) );
        TreasureHandler.setTreasureCapture(TypeEnums.FIRE);
        assertEquals("Model is not recognising treasure has already been captured", 1, theModel.capture());
    }


}
