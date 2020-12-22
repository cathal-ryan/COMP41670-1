package setup;

/**
 * Singleton Facade to handle all required setup for playing Forbidden Island
 * 
 * @author  Cathal Ryan and Conor Kneafsey
 */
public class Setup {
    
    private static Setup theSetup;
    
    // The setup classes
    private PlayerSetup         playerHandler;
    private CardSetup           cardHandler;
    private WaterMeterSetup     waterHandler;
    private SetupOutputs        setupOutputs;
    private BoardSetup          boardHandler;
    
    /**getInstance of Setup class singleton
     * @return instance of Setup class
     */
    public static Setup getInstance(){
        if(theSetup == null){
            theSetup = new Setup();
        }
        return theSetup;
    }

    /**Constructor class for Setup
     */
    private Setup() {
        setupOutputs = new SetupOutputs();
        this.waterHandler   = new WaterMeterSetup();
        this.boardHandler   = new BoardSetup();
        this.playerHandler  = new PlayerSetup();
        this.cardHandler    = new CardSetup();
    }
    
    /**Handles the setup for us
     */
    public void doAllSetup() {
        setupOutputs.welcomeScreen(); // Greet the user
        boardHandler.setTiles();
        waterHandler.createWaterLevel();
        playerHandler.createAllPlayers(); 
        cardHandler.dealCards(); 
        setupOutputs.setupOver();
    }
}