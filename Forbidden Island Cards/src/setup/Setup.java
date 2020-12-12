package setup;
import java.util.Scanner;

public class Setup {
    
    private static Setup theSetup;
    
    // Setup Controllers
    private PlayerSetup      playerHandler;
    private CardSetup        cardHandler;
    private WaterMeterSetup  waterHandler;
    private BoardSetup       boardHandler;
    
    public static Setup getInstance(){
        if(theSetup == null){
            theSetup = new Setup();
        }
        return theSetup;
    }

    private Setup() {
        // Create instances of Player set up and card set up
        this.waterHandler   = new WaterMeterSetup();
        this.boardHandler   = new BoardSetup();
        this.playerHandler  = new PlayerSetup(boardHandler.getBoard());
        this.cardHandler    = new CardSetup();
    }
    

    public void doAllSetup(Scanner user) {
        welcomeScreen();
        waterHandler.createWaterLevel(user);
        boardHandler.setTiles();
        playerHandler.createAllPlayers(user); //does here fine
        cardHandler.dealCards(); // does here ok i think
        
    }

    public void welcomeScreen(){
        System.out.println("  █████▒▒█████   ██▀███   ▄▄▄▄    ██▓▓█████▄ ▓█████▄ ▓█████  ███▄    █     ██▓  ██████  ██▓    ▄▄▄       ███▄    █ ▓█████▄         ");
        System.out.println("▓██   ▒▒██▒  ██▒▓██ ▒ ██▒▓█████▄ ▓██▒▒██▀ ██▌▒██▀ ██▌▓█   ▀  ██ ▀█   █    ▓██▒▒██    ▒ ▓██▒   ▒████▄     ██ ▀█   █ ▒██▀ ██▌       ");
    	System.out.println("▒████ ░▒██░  ██▒▓██ ░▄█ ▒▒██▒ ▄██▒██▒░██   █▌░██   █▌▒███   ▓██  ▀█ ██▒   ▒██▒░ ▓██▄   ▒██░   ▒██  ▀█▄  ▓██  ▀█ ██▒░██   █▌       ");
    	System.out.println("░▓█▒  ░▒██   ██░▒██▀▀█▄  ▒██░█▀  ░██░░▓█▄   ▌░▓█▄   ▌▒▓█  ▄ ▓██▒  ▐▌██▒   ░██░  ▒   ██▒▒██░   ░██▄▄▄▄██ ▓██▒  ▐▌██▒░▓█▄   ▌       ");
    	System.out.println("░▒█░   ░ ████▓▒░░██▓ ▒██▒░▓█  ▀█▓░██░░▒████▓ ░▒████▓ ░▒████▒▒██░   ▓██░   ░██░▒██████▒▒░██████▒▓█   ▓██▒▒██░   ▓██░░▒████▓        ");
    	System.out.println(" ▒ ░   ░ ▒░▒░▒░ ░ ▒▓ ░▒▓░░▒▓███▀▒░▓   ▒▒▓  ▒  ▒▒▓  ▒ ░░ ▒░ ░░ ▒░   ▒ ▒    ░▓  ▒ ▒▓▒ ▒ ░░ ▒░▓  ░▒▒   ▓▒█░░ ▒░   ▒ ▒  ▒▒▓  ▒         ");
    	System.out.println(" ░       ░ ▒ ▒░   ░▒ ░ ▒░▒░▒   ░  ▒ ░ ░ ▒  ▒  ░ ▒  ▒  ░ ░  ░░ ░░   ░ ▒░    ▒ ░░ ░▒  ░ ░░ ░ ▒  ░ ▒   ▒▒ ░░ ░░   ░ ▒░ ░ ▒  ▒         ");
    	System.out.println(" ░ ░   ░ ░ ░ ▒    ░░   ░  ░    ░  ▒ ░ ░ ░  ░  ░ ░  ░    ░      ░   ░ ░     ▒ ░░  ░  ░    ░ ░    ░   ▒      ░   ░ ░  ░ ░  ░         ");
        System.out.println("           ░ ░     ░      ░       ░     ░       ░       ░  ░         ░     ░        ░      ░  ░     ░  ░         ░    ░            ");
        System.out.println("                               ░      ░       ░                                                                     ░      ");
    }
}