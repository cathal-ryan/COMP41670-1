package gameplay;

import java.util.Scanner;

import cards.Deck;
import player.Player;
import player.PlayerList;

public class GameManager {

    private static GameManager theGM;
    
    // Other variables
    private boolean    gameOver=false;
    private boolean    winners=false;
    private PlayerList players;
    
    public static GameManager getInstance(){
        if(theGM == null){
            theGM = new GameManager();
        }
        return theGM;
    }
    
    private GameManager() {
        this.gameOver     = false;
        this.winners      = false;
        this.players      = PlayerList.getInstance();
    }
    
    public void doGameplay(Scanner inputScanner) {
        PlayerTurn currentTurn;
        while (!gameOver && !winners) { // Main loop for doing PlayerTurns
            for (Player i: players.getAllPlayers()) {
                currentTurn = new PlayerTurn(i,inputScanner);    // Make a new PlayerTurn
                currentTurn.doTurn();                            // Let it handle the turn
            }
        }
    }
}
