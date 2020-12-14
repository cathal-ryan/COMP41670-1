package gameplay.control;

public class GameManager {

    private static GameManager theGM;
    // Other variables
    private boolean    gameOver=false;
    private Controller theController;
    private LoseObserver losing = new LoseObserver();
    private WinObserver winning = new WinObserver();

    public static GameManager getInstance(){
        if(theGM == null){
            theGM = new GameManager();
        }
        return theGM;
    }
    
    private GameManager() {
        this.gameOver     = false;
        this.theController = Controller.getInstance();
        this.losing = new LoseObserver();
        this.winning = new WinObserver();
    }
    
    public void doGameplay() {
        PlayerActions currentPActions;
        TreasureDraw currentTreasure;
        FloodDraw currentFlood;
        while (!theController.isGameOver()) { // Main loop for doing PlayerTurns
            theController.newTurn();
            
            currentPActions = new PlayerActions();    // Make a new PlayerTurn
            currentPActions.doActions();                          // Let it handle the turn
            
            if(!theController.isGameOver()){
                currentTreasure = new TreasureDraw();    // Make a new PlayerTurn
                currentTreasure.doTreasureDraw();                            // Let it handle the turn    
            }
            if(!theController.isGameOver()){
                currentFlood = new FloodDraw();    // Make a new PlayerTurn
                currentFlood.doFloodDraw();    
            }  
            if(theController.isGameOver()){
                theController.gameOverPrompt();
            }                        
        }
    }
}
