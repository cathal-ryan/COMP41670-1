package testing;

import org.junit.Test;

import board.Board;

import static org.junit.Assert.*;


import cards.FloodDiscardPile;
import gameplay.model.WaterMeter;
import player.Team;
import setup.Setup;

public class SetupTests {

    @SuppressWarnings("static-access")
    @Test // Test to see does helicopter lift move player properly
    public void testAlltheSetup() { // Each part of set up relies on each other so test each sequentially
        Setup setup = Setup.getInstance();
        System.out.println("Expected input for number of players: 2");
        System.out.println("Expected input name of player1: test1");
        System.out.println("Expected input name of player2: test2");
        System.out.println("Expected input for difficulty: 3");
        setup.doAllSetup();

        Board theBoard = Board.getInstance();
        assertFalse("Board has been set up",theBoard.getBoardTiles().isEmpty());

        WaterMeter theWM = WaterMeter.getInstance();
        assertTrue("WaterMeter has been set up",theWM.getWaterlevel()==3);

        Team theTeam = Team.getInstance();
        assertEquals("Team has been set up",2,theTeam.getNumPlayers());
        assertNotSame("Players have different characters",theTeam.getPlayer(0).getChar(),theTeam.getPlayer(1).getChar());
        assertEquals("Player name 1 correct","test1",theTeam.getPlayer(0).getName());
        assertEquals("Player name 2 correct","test2",theTeam.getPlayer(1).getName());

        FloodDiscardPile floodPile = FloodDiscardPile.getInstance();
        
        int handSize1 = theTeam.getPlayer(0).getHand().getCards().size();
        int handSize2 = theTeam.getPlayer(1).getHand().getCards().size();
        
        assertFalse("Flood Pile has been set up",floodPile.Discarded.isEmpty());
        assertEquals("Hands have 2 cards each",2,(handSize1+handSize2)/2);
	}
}
