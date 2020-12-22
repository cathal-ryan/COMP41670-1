package testing;

import static org.junit.Assert.*;
import org.junit.Test;

import gameplay.control.Controller;
import gameplay.model.GameModel;
import player.Player;
import player.Team;

public class TurnsTest {

    @Test
	public void testPlayerTurns() { //Testing if game can return players with heli lift/sandbags
        Team theTeam = Team.getInstance();
        Player p0 = new Player(0, "Test Player0", 0);
        Player p1 = new Player(1, "Test Player1", 1);
        theTeam.addPlayer(p0);
        theTeam.addPlayer(p1);
        GameModel theModel = GameModel.getInstance();
        Controller theController = Controller.getInstance();
        theModel.setTurnOver(false); 
        theModel.setActionsLeft(); // Reset actions
        theModel.setNextPlayer(); //  Set new player

        assertEquals("Current player is player 0 for first turn", p0, theModel.getCurrentPlayer());

        theModel.setTurnOver(true);

        assertTrue("Turn has finished after setting over", theModel.getTurnOver());

        theModel.setTurnOver(false); // Set turn over to false, turn is on
        theModel.setActionsLeft(); // Reset actions
        theModel.setNextPlayer(); //Set new player

        assertEquals("Current player is player 1 for next turn", p1, theModel.getCurrentPlayer());
    
        assertEquals("Actions for new turn", 3, theModel.getActionsLeft());

        theModel.decreaseActions();

        assertEquals("Actions after completing action", 2, theModel.getActionsLeft());

    }
}
