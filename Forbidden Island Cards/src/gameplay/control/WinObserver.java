package gameplay.control;

import enums.EndGameEnums;

/**
 * Win Observer implements Observer pattern to tell when game is won.
 * 
 * @author Cathal Ryan and Conor Kneafsey
 *
 */
public class WinObserver implements Observer {

    private static boolean      gameWon=false;
    private static String       winCondition; // How game won
    

	/**
	 * Function allowing those to be observed to update observer of a change in state, in this case, a win
     * @param int method allows for selection of a particular method of winning, so game knows how win occurs
	 */
    @Override
    public void update(int method) {
    	// Set game as wo
        gameWon=true;
        // In our case, game can only be won in one method. Allows for potential refactoring for alternative
        // routes to victory.
        if(method==7){
            winCondition = EndGameEnums.HELICOPTER.toString();
        }
    }

	//===========================================================
	// Getters and Setters
	//===========================================================
	/**
	 * Returns if Game Won
	 * @return boolean gameOver is the Game Won
	 */
	public boolean isGameWon() {
		return gameWon;
    }

	/**
	 * Returns how Game Won
	 * @return String winCondition how game was won
	 */
    public String getWinCondition(){
        return winCondition;
    }
    
}
