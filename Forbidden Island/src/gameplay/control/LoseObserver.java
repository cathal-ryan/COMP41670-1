package gameplay.control;
import enums.EndGameEnums;

/**
 * Lose Observer implements Observer pattern to tell when the game is lost.
 * 
 * @author Cathal Ryan and Conor Kneafsey
 *
 */
public class LoseObserver implements Observer{

    private static boolean  gameOver =  false;
    private static boolean  gameLost =  false;
    private static String   lossCondition;	// How the game was lost

	/**
	 * Function allowing those to be observed to update observer of a change in state, in this case, a loss
     * @param int method allows for selection of a particular method of losing, so game knows how loss occurs
	 */
    @Override
    public void update(int method) {
        gameOver=true; //Game Over
        
        // If the method value conforms to this inequality, then the game is not only over, but is also lost
        // The lossCondition is updated accordingly
        if(method>=0 && method<=6){
            gameLost=true;
        }
        // Set how game lost
        switch (method) {
			case 0: // Both Temple Tiles Sunk
				lossCondition = EndGameEnums.TEMPLES.toString();
				break;
			case 1: // Both Cave Tiles Sunk
                lossCondition = EndGameEnums.CAVES.toString();
                break;
			case 2: // Both Palace Tiles Sunk
                lossCondition = EndGameEnums.PALACES.toString();
                break;
            case 3: // Both Garden Tiles Sunk
                lossCondition = EndGameEnums.GARDENS.toString();
                break;
			case 4: // Fools Landing Tile Sunk
                lossCondition = EndGameEnums.FOOLS.toString();
                break;
			case 5: // Player cannot swim
                lossCondition = EndGameEnums.NOSWIM.toString();
                break;
            case 6: // Water Level gone too high
                lossCondition = EndGameEnums.WATERLEVEL.toString();
                break;
			default: 
                lossCondition = EndGameEnums.FOOLS.toString();
        }
    }
    
	//===========================================================
	// Getters and Setters
	//===========================================================
	/**
	 * Returns if Game Over
	 * @return boolean gameOver is the Game Over
	 */
    public boolean isGameOver(){
        return gameOver;
    }
    
	/**
	 * Returns if Game Lost
	 * @return boolean gameLost is the game lost
	 */
    public boolean isGameLost(){
        return gameLost;
    }
    
	/**
	 * Returns how game was lost
	 * @return String lossCondition, how game lost
	 */
    public String getLossCondition(){
        return lossCondition;
    }
}
