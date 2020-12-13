package gameplay.control;
import java.util.Scanner;
import player.Player;
import player.Team;

public class GameManager {

    private static GameManager theGM;
    
    // Other variables
    private boolean    gameOver=false;
    private Controller theController;

    public static GameManager getInstance(){
        if(theGM == null){
            theGM = new GameManager();
        }
        return theGM;
    }
    
    private GameManager() {
        this.gameOver     = false;
        this.theController = Controller.getInstance();
    }
    
    public void doGameplay() {
        PlayerActions currentPActions;
        TreasureDraw currentTreasure;
        FloodDraw currentFlood;
        while (!theController.isGameOver()) { // Main loop for doing PlayerTurns
                theController.newTurn();
                currentPActions = new PlayerActions();    // Make a new PlayerTurn
                while(!theController.getTurnOver()){
                    currentPActions.doActions();                          // Let it handle the turn
                    // if(theController.isGameWon()){
                    //     gameOver=true;
                    //     break;
                    // }
                }
                currentTreasure = new TreasureDraw();    // Make a new PlayerTurn
                currentTreasure.doTreasureDraw();                            // Let it handle the turn    
                // if(currentTreasure.seeIfLost()){
                //     System.out.println("Sorry! You lose! The Flood Meter went too High :(");
                //     gameOver=true;
                //     break;
                // }
                // if(currentTreasure.seeIfWon()){
                //     System.out.println("Congrats! You win!");
                //     gameOver=true;
                //     break;
                // }
                
                currentFlood = new FloodDraw();    // Make a new PlayerTurn
                currentFlood.doFloodDraw();                            // Let it handle the turn    
                // if(currentFlood.seeIfLost()){
                //     System.out.println("Sorry! You lose!");
                //     gameOver=true;
                //     break;
                // }
                // if(currentFlood.seeIfWon()){
                //     System.out.println("Congrats! You win!");
                //     gameOver=true;
                //     break;
                // }
        }
    }
}
