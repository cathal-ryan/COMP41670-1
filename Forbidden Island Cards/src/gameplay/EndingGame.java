package gameplay;

import java.util.Observable;
import java.util.Observer;

import javax.security.auth.Subject;



public class EndingGame implements Observer {

    private GameState theGS;

    public EndingGame(GameState game){
        this.theGS = game;
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        // TODO Auto-generated method stub

    }
    
}
