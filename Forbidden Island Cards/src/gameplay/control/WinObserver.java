package gameplay.control;

import java.util.Observable;
import java.util.Observer;

import javax.security.auth.Subject;

import gameplay.model.GameModel;



public class WinObserver implements Observer {

    private GameModel theGS;

    public WinObserver(GameModel game){
        this.theGS = game;
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        // TODO Auto-generated method stub

    }
    
}
