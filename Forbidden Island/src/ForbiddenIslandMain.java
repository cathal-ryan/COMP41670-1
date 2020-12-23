import gameplay.control.GameManager;
import setup.Setup;


public class ForbiddenIslandMain {
    public static void main(String[] args) {
		
        Setup.getInstance().doAllSetup();		// Set-Up
        
        GameManager.getInstance().doGameplay();	// Playing the game.
        
    }
}
