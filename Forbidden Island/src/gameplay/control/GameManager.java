package gameplay.control;

/**
 * Singleton game manager class. Manages the over-arching flow of the game,
 * once it has been set up.
 * Initiates the player turns and their drawing of flood and treasure
 * cards, checking for whether the game is over at each interval.
 *  
 * @author Cathal Ryan and Conor Kneafsey
 * @version 1.0
 *
 */
public class GameManager {

    // ===========================================================
    // Setup Variables
    // ===========================================================
    private static GameManager 	theGM; //Self Singleton
    private Controller 			theController; //Access to main game logic
    
    /**
     * getInstance singleton method gets single instance
     * of GameManager.
     * @return Singleton gameManager object
     */
    public static GameManager getInstance(){
        if(theGM == null){
            theGM = new GameManager();
        }
        return theGM;
    }
    
    /**
     * The private GameManager constructor
     */
    private GameManager() {
        this.theController = Controller.getInstance();
    }
    
    /**
     * Allows for gameplay, starts player turns and drawing of cards
     * Also controls exit status when game over.
     */
    public void doGameplay() {
        PlayerActions currentPActions;
        TreasureDraw currentTreasure;
        FloodDraw currentFlood;
        while (!theController.isGameOver()) { // Main player turn loop
            theController.newTurn();
            
            currentPActions = new PlayerActions();    // Allow players to complete actions
            currentPActions.doActions();              // Let it handle the turn
            
            if(!theController.isGameOver()){			 // Consistently check if the game is over.
                currentTreasure = new TreasureDraw();    // New Treasure Draw
                currentTreasure.doTreasureDraw();        // Let it handle the draw    
            }
            if(!theController.isGameOver()){	   
                currentFlood = new FloodDraw();    // New flood draw
                currentFlood.doFloodDraw();        // Let it handle flood draw
            }  
            if(theController.isGameOver()){			// Check if the game has ended, if so return message.
                theController.gameOverPrompt();
            }                        
        }
    }
}
