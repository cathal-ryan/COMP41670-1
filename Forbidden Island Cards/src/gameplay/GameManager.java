package gameplay;

import java.util.Scanner;

import cards.Deck;
import player.Player;
import player.PlayerList;

public class GameManager {

    private static GameManager theGM;
    
    // Other variables
    private boolean    gameOver=false;
    private PlayerList players;
    
    public static GameManager getInstance(){
        if(theGM == null){
            theGM = new GameManager();
        }
        return theGM;
    }
    
    private GameManager() {
        this.gameOver     = false;
        this.players      = PlayerList.getInstance();
    }
    
    public void doGameplay(Scanner inputScanner) {
        PlayerActions currentPActions;
        TreasureDraw currentTreasure;
        TreasureHandler treasures;
        FloodDraw currentFlood;
        boolean winners;
        boolean losers;
        while (!gameOver) { // Main loop for doing PlayerTurns
            for (Player i: players.getAllPlayers()) {
                currentPActions = new PlayerActions(i,inputScanner);    // Make a new PlayerTurn
                currentPActions.doActions();                            // Let it handle the turn
                winners  = currentPActions.seeIfWon();
                if(winners){
                    System.out.println("Congrats! You win!");
                    gameOver=true;
                    break;
                }
                currentTreasure = new TreasureDraw(i,inputScanner);    // Make a new PlayerTurn
                currentTreasure.doTreasureDraw();                            // Let it handle the turn    
                losers = currentTreasure.seeIfLost();
                if(losers){
                    System.out.println("Sorry! You lose! The Flood Meter went too High :(");
                    gameOver=true;
                    break;
                }
                currentFlood = new FloodDraw(i,inputScanner);    // Make a new PlayerTurn
                currentFlood.doFloodDraw();                            // Let it handle the turn    
                losers = currentFlood.seeIfLost();
                if(losers){
                    System.out.println("Sorry! You lose!");
                    gameOver=true;
                    break;
                }
            }
        }
    }
}
