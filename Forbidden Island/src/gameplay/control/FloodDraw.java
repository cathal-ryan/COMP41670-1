package gameplay.control;
import gameplay.view.GameInputs;
import gameplay.view.GameOutputs;
    
/**
 * Flood Draw class manages drawing flood cards.
 * Each player should draw cards equal to water level
 * 
 * @author Cathal Ryan and Conor Kneafsey
 *
 */
public class FloodDraw {

    private GameOutputs     theOutputs; //I/O Access
    private GameInputs      theInputs;   
    private Controller      theController; // Access to game logic

    /**
     * Constructor for a Treasure Draw.
     */
    public FloodDraw() {
        theOutputs = new GameOutputs();
        theInputs = new GameInputs();    
        this.theController = Controller.getInstance();
    }

	/**
	 * Manages flood cards being drawn, takes cards equal to the flood meter. 
	 * Consistently checks if the game is over, as controller will perform logic
	 * if tile sink means game ends
	 */
    protected void doFloodDraw() {
        theOutputs.floodDrawTime();
        int waterLevel = theController.getWaterLevel(); // Water level found, this is amount of cards needed
        for(int i =0;i<waterLevel;i++) {
            theOutputs.cardsLeft(waterLevel-i);
            // Ask players if they want to use special cards before drawing flood
            boolean isSpecialPlayed = theController.enquirePlayers(false);
            if(theController.isGameOver()){
                return; //Game Over
            }
            // If they played special cards, player must enter in return to draw
            if(isSpecialPlayed){
                theOutputs.pressReturn();
                theInputs.confirm(); //
            }             
            theController.dealFloodCard();
            if(theController.isGameOver()){
                return; //Game Over
            }
        }    
        // Tell player turn is completely done.
        theOutputs.turnEndo();    
    }
}    

