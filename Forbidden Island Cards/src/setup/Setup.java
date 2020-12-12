package setup;
import java.util.Scanner;


public class Setup {
    
    private static Setup theSetup;
    
    // Setup Controllers
    private PlayerSetup      playerHandler;
    private CardSetup        cardHandler;
    private WaterMeterSetup  waterHandler;
    private SetupOutputs setupOutputs;
    private SetupInputs setupInputs;
    
    public static Setup getInstance(){
        if(theSetup == null){
            theSetup = new Setup();
        }
        return theSetup;
    }

    private Setup() {
        // Create instances of Player set up and card set up
        setupInputs = new SetupInputs();
        setupOutputs = new SetupOutputs();
        this.waterHandler   = new WaterMeterSetup();
        this.playerHandler  = new PlayerSetup();
        this.cardHandler    = new CardSetup();
    }
    

    public void doAllSetup(Scanner user) {
        setupOutputs.welcomeScreen();
        waterHandler.createWaterLevel(setupInputs,setupOutputs);
        playerHandler.createAllPlayers(user); // does here fine
        cardHandler.dealCards(); // does here ok i think
    }
}