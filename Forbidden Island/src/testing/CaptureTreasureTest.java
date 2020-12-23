package testing;
import static org.junit.Assert.assertEquals;
import java.awt.Point;
import org.junit.Test;
import board.Board;
import cards.Hand;
import cards.TreasureCard;
import enums.TilesEnums;
import enums.TreasureCardEnums;
import enums.TypeEnums;
import gameplay.model.GameModel;
import gameplay.model.TreasureHandler;
import player.*;
import setup.BoardSetup;

public class CaptureTreasureTest {
    
	@Test // Check if discarding cards for treasures is functioning
	public void discardforTreasureTest() {
		Player tester = new Player(0, "Test Player", 1);
        Hand testHand = tester.getHand();
        tester.addCardtoHand(new TreasureCard(TreasureCardEnums.EARTH_STONE) );
        tester.addCardtoHand(new TreasureCard(TreasureCardEnums.EARTH_STONE) );
        tester.addCardtoHand(new TreasureCard(TreasureCardEnums.OCEANS_CHALICE) );
        tester.addCardtoHand(new TreasureCard(TreasureCardEnums.EARTH_STONE) );
        tester.addCardtoHand(new TreasureCard(TreasureCardEnums.EARTH_STONE) );
        testHand.discardforTreasure(TypeEnums.EARTH);
        assertEquals("Cards left after discarding 4 for treasure from hand of 5", 1, testHand.getCards().size());
	}

    @Test // Check if model is returning the right mode for our treasure query based on our state
	public void captureModeTest() {
        Team theTeam = Team.getInstance();
        BoardSetup bset = new BoardSetup();
        bset.setTiles();
        Board theBoard = Board.getInstance();
        Point p1 = theBoard.getTilePos(TilesEnums.CAVE_OF_EMBERS);
        theTeam.addPlayer(new Player(0, "Test Player", 1));
        GameModel theModel = GameModel.getInstance();
        theModel.setNextPlayer();
        theModel.getCurrentPlayer().getPawn().setPos(p1);
        
        assertEquals("Player does not have enough cards in hand", 3, theModel.capture());

        for(int i=0;i<5;i++)
            theModel.getCurrentPlayer().addCardtoHand(new TreasureCard(TreasureCardEnums.CRYSTAL_OF_FIRE) );

        assertEquals("Player can capture treasure", 0, theModel.capture());

        Point p2 = theBoard.getTilePos(TilesEnums.BRONZE_GATE);
        theModel.getCurrentPlayer().getPawn().setPos(p2);

        assertEquals("Player is not on a treasure tile", 2, theModel.capture());
        
        theModel.getCurrentPlayer().getPawn().setPos(p1);
        for(int i=0;i<5;i++)
            theModel.getCurrentPlayer().addCardtoHand(new TreasureCard(TreasureCardEnums.CRYSTAL_OF_FIRE) );
        TreasureHandler.setTreasureCapture(TypeEnums.FIRE);
        assertEquals("Treasure has already been captured", 1, theModel.capture());
    }


}
