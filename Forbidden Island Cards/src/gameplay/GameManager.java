package gameplay;

import java.util.Scanner;

import cards.Deck;
import player.Player;
import player.Team;

public class GameManager {

    private static GameManager theGM;
    
    // Other variables
    private boolean    gameOver=false;
    private Team players;
    
    public static GameManager getInstance(){
        if(theGM == null){
            theGM = new GameManager();
        }
        return theGM;
    }
    
    private GameManager() {
        this.gameOver     = false;
        this.players      = Team.getInstance();
    }
    
    public void doGameplay(Scanner inputScanner) {
        PlayerActions currentPActions;
        TreasureDraw currentTreasure;
        FloodDraw currentFlood;
        while (!gameOver) { // Main loop for doing PlayerTurns
            for (Player i: players.getAllPlayers()) {
                
                currentPActions = new PlayerActions(i,inputScanner);    // Make a new PlayerTurn
                currentPActions.doActions();                            // Let it handle the turn
                if(currentPActions.seeIfWon()){
                    System.out.println("Congrats! You win!");
                    gameOver=true;
                    break;
                }
                
                currentTreasure = new TreasureDraw(i,inputScanner);    // Make a new PlayerTurn
                currentTreasure.doTreasureDraw();                            // Let it handle the turn    
                if(currentTreasure.seeIfLost()){
                    System.out.println("Sorry! You lose! The Flood Meter went too High :(");
                    gameOver=true;
                    break;
                }
                if(currentTreasure.seeIfWon()){
                    System.out.println("Congrats! You win!");
                    gameOver=true;
                    break;
                }
                
                currentFlood = new FloodDraw(inputScanner);    // Make a new PlayerTurn
                currentFlood.doFloodDraw();                            // Let it handle the turn    
                if(currentFlood.seeIfLost()){
                    System.out.println("Sorry! You lose!");
                    gameOver=true;
                    break;
                }
                if(currentFlood.seeIfWon()){
                    System.out.println("Congrats! You win!");
                    gameOver=true;
                    break;
                }
                System.out.println("-------------------------------------------------");
            }
        }
    }
}
