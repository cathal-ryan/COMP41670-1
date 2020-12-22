package testing;

import static org.junit.Assert.*;
import org.junit.Test;
import gameplay.model.GameModel;
import player.Player;
import player.Team;

public class TurnsTest {

    @Test
	public void testPlayerTurnsandActions() { //Testing if game can return players with heli lift/sandbags
        Team theTeam = Team.getInstance();
        theTeam.addPlayer(new Player(0, "Test Player1", 0));
        theTeam.addPlayer(new Player(1, "Test Player2", 1));
        GameModel theModel = GameModel.getInstance();

        theController.newTurn()

        theModel.setTurnOver(true);
        theModel.setNextPlayer();

}
