package setup;
import java.util.Scanner;

public class Setup {
    
    private static Setup theSetup;
    
    // Setup Controllers
    private PlayerSetup      playerHandler;
    private CardSetup        cardHandler;
    
    public static Setup getInstance(){
        if(theSetup == null){
            theSetup = new Setup();
        }
        return theSetup;
    }

    private Setup() {
    	// Create instances of Player set up and card set up
        this.playerHandler  = new PlayerSetup();
        this.cardHandler    = new CardSetup();
    }
    

    public void doAllSetup(Scanner user) {
    	welcomeScreen();
        playerHandler.createAllPlayers(user); //does here fine
        cardHandler.dealCards(); // does here ok i think
        
    }

    public void welcomeScreen(){
    	System.out.println("Welcome to the Software Project!");
    }
}