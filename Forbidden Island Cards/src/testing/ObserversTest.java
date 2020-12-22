package testing;

import org.junit.Test;

import board.Board;

import static org.junit.Assert.*;

import java.awt.Point;

import cards.TreasureCard;
import enums.TilesEnums;
import enums.TreasureCardEnums;
import enums.TypeEnums;
import gameplay.control.Controller;
import gameplay.control.LoseObserver;
import gameplay.control.Observer;
import gameplay.control.WinObserver;
import gameplay.model.GameModel;
import gameplay.model.TreasureHandler;
import gameplay.model.WaterMeter;
import player.Player;
import player.Team;
import setup.BoardSetup;

public class ObserversTest {
    
    @Test // Test to see if players can win by playing helicopter lift
    public void testDoPlayersWin() {
        Team theTeam = Team.getInstance();
        BoardSetup bset = new BoardSetup();
        bset.setTiles();
        Board theBoard = Board.getInstance();
        theTeam.addPlayer(new Player(0, "Test Player1", 0));
        theTeam.addPlayer(new Player(1, "Test Player2", 1));
        GameModel theModel = GameModel.getInstance();
        TreasureHandler theTH = TreasureHandler.getInstance();
        theTH.setTreasureCapture(TypeEnums.EARTH);
        theTH.setTreasureCapture(TypeEnums.FIRE);
        theTH.setTreasureCapture(TypeEnums.WATER);
        theTH.setTreasureCapture(TypeEnums.WIND);
        Point p = theBoard.getTilePos(TilesEnums.FOOLS_LANDING);
        theModel.getPlayer(0).getPawn().setPos(p);
        theModel.getPlayer(1).getPawn().setPos(p);
        theModel.getPlayer(0).addCardtoHand(new TreasureCard(TreasureCardEnums.HELICOPTER_LIFT));
        WinObserver winner = new WinObserver();

		assertFalse("Game not won before heli lift played", winner.isGameWon());

        Controller.getInstance().useHelicopterLift(theModel.getPlayer(0));
        
		assertTrue("Game won after heli lift played", winner.isGameWon());
    }

    @Test // Test to see if players can lose by two tiles of a treasure being sunk
    public void testTwoTreasureTilesSink() {
        BoardSetup bset = new BoardSetup();
        bset.setTiles();
        Board theBoard = Board.getInstance();
        LoseObserver loser = new LoseObserver();
		assertFalse("Game not lost before tiles sunk", loser.isGameLost());

        for(int i=0;i<2;i++){
            theBoard.floodTile(TilesEnums.CAVE_OF_EMBERS);
            theBoard.floodTile(TilesEnums.CAVE_OF_SHADOWS);
        }
        GameModel theModel = GameModel.getInstance();
        theModel.dealFlood();        
		assertTrue("Sinking of the 2 cave cards should lose game", loser.isGameLost());
    }
    
    @Test // Test to see if players can lose by water meter rising too high
    public void waterMeterTooHighTest() {
        BoardSetup bset = new BoardSetup();
        WaterMeter theWM = WaterMeter.getInstance();
        bset.setTiles();
        Board theBoard = Board.getInstance();
        LoseObserver loser = new LoseObserver();
		assertFalse("Game not lost before tiles sunk", loser.isGameLost());

        for(int i=0;i<2;i++){
            theBoard.floodTile(TilesEnums.CAVE_OF_EMBERS);
            theBoard.floodTile(TilesEnums.CAVE_OF_SHADOWS);
        }
        GameModel theModel = GameModel.getInstance();
        theModel.dealFlood();        
		assertTrue("Sinking of the 2 cave cards should lose game", loser.isGameLost());
    }
}
