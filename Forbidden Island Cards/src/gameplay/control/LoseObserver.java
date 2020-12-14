package gameplay.control;
import enums.EndGameEnums;



public class LoseObserver implements Observer{

    private static boolean gameOver =false;
    private static boolean gameLost = false;
    private static String lossCondition;

    @Override
    public void update(int method) {
        gameOver=true;
        if(method>=0 && method<=6){
            gameLost=true;
        }
        switch (method) {
			case 0:
				lossCondition = EndGameEnums.TEMPLES.toString();
				break;
			case 1:
                lossCondition = EndGameEnums.CAVES.toString();
                break;
			case 2:
                lossCondition = EndGameEnums.PALACES.toString();
                break;
            case 3:
                lossCondition = EndGameEnums.GARDENS.toString();
                break;
			case 4:
                lossCondition = EndGameEnums.FOOLS.toString();
                break;
			case 5:
                lossCondition = EndGameEnums.NOSWIM.toString();
                break;
            case 6:
                lossCondition = EndGameEnums.WATERLEVEL.toString();
                break;
			default:
                lossCondition = EndGameEnums.FOOLS.toString();
        }
    }

    public boolean isGameOver(){
        return gameOver;
    }

    public boolean isGameLost(){
        return gameLost;
    }

    public String getLossCondition(){
        return lossCondition;
    }
}
