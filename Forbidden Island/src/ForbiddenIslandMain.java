import gameplay.control.GameManager;
import setup.Setup;


public class ForbiddenIslandMain {
    public static void main(String[] args) {
		
        Setup.getInstance().doAllSetup();
        
        GameManager.getInstance().doGameplay();
        
    }
}
