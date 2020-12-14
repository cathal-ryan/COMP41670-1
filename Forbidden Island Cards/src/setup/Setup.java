package setup;

import gameplay.model.GameModel;


public class Setup {
    
    private static Setup theSetup;
    
    // Setup Controllers
    private PlayerSetup      playerHandler;
    private CardSetup        cardHandler;
    private WaterMeterSetup  waterHandler;
    private SetupOutputs setupOutputs;
    private SetupInputs setupInputs;
    private GameModel theGameModel;
    private BoardSetup       boardHandler;
    
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
        this.boardHandler   = new BoardSetup();
        this.playerHandler  = new PlayerSetup();
        this.cardHandler    = new CardSetup();
    }
    

    public void doAllSetup() {
        setupOutputs.welcomeScreen();
        boardHandler.setTiles();
        waterHandler.createWaterLevel(setupInputs,setupOutputs);
        playerHandler.createAllPlayers(); // does here fine
        cardHandler.dealCards(); // does here ok i think
        theGameModel = GameModel.getInstance();
        setupOutputs.setupOver();
    }
}