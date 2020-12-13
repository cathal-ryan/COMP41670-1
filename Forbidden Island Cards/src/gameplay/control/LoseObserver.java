package gameplay.control;

import gameplay.model.GameModel;
import enums.EndGameEnums;



public class LoseObserver implements Observer{

    private static boolean gameOver;
    private static boolean gameLost;
    private static boolean gameWon;
    private static Enum lossCondition;

    @Override
    public void update(int method) {
        gameOver=true;
        gameLost=true;
        System.out.println("Game is lost!");
        switch (method) {
			case 0:
				lossCondition = EndGameEnums.CAVES;
				break;
			case 1:
                lossCondition = EndGameEnums.CAVES;
                break;
			case 2:
                lossCondition = EndGameEnums.CAVES;
                break;
            case 3:
                lossCondition = EndGameEnums.CAVES;
                break;
			case 4:
                lossCondition = EndGameEnums.CAVES;
                break;
			case 5:
                lossCondition = EndGameEnums.CAVES;
                break;
            case 6:
                lossCondition = EndGameEnums.CAVES;
                break;
            case 7:
                lossCondition = EndGameEnums.CAVES;
                break;
            case 8:
                lossCondition = EndGameEnums.CAVES;
                break;
			default:
				System.out.println("I shouldn't be here!");

        this.gameLost = true;
        this.gameOver = true;
    }

    public boolean isGameOver(){
        return gameOver;
    }
}
