package testing;

import org.junit.Test;
import static org.junit.Assert.*;

import cards.TreasureCard;
import enums.TreasureCardEnums;
import gameplay.control.Controller;
import gameplay.model.GameModel;
import player.Player;
import player.Team;

public class PlayerEnquiryTest {

    @Test
	public void checkGettingPlayersWithSpecials() { //Testing if game can return players with heli lift/sandbags
        Team theTeam = Team.getInstance();
        theTeam.addPlayer(new Player(0, "Test Player1", 0));
        theTeam.addPlayer(new Player(1, "Test Player2", 1));
        theTeam.addPlayer(new Player(2, "Test Player3", 2));
        theTeam.addPlayer(new Player(3, "Test Player4", 3));
        GameModel theModel = GameModel.getInstance();
        for(int i=0;i<2;i++){
            theModel.getPlayer(0).addCardtoHand(new TreasureCard(TreasureCardEnums.SANDBAGS));
            theModel.getPlayer(1).addCardtoHand(new TreasureCard(TreasureCardEnums.HELICOPTER_LIFT));
        }
		assertEquals("Players with special cards detected", 2, theModel.getPlayerswithSpecials().size());
    }

    @Test
    public void checkPlayerChoiceforSpecial(){
        Controller theController = Controller.getInstance();
        System.out.println("Expected Output: Is there anyone who wants to play their special card?")
        System.out.println("Expected Input: 1");
        boolean choice = theController.chooseOrShowState(0,"no","Yes, play special card!");
        assertTrue("Player choice being identified", choice);

    }
    
}
