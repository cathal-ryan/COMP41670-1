package gameplay.control;

import enums.EndGameEnums;
import gameplay.model.GameModel;



public class WinObserver implements Observer {

    private static boolean gameOver;
    private static boolean gameWon;
    private static String winCondition;

    @Override
    public void update(int method) {
        gameWon=true;
        if(method==7){
            winCondition = EndGameEnums.HELICOPTER.toString();
        }
    }

	public boolean isGameWon() {
		return gameWon;
    }
    
    public String getWinCondition(){
        return winCondition;
    }
    
}
