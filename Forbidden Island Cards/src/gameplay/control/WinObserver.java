package gameplay.control;

import gameplay.model.GameModel;



public class WinObserver implements Observer {

    private GameModel theGS;

    public WinObserver(GameModel game){
        this.theGS = game;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }
    
}
