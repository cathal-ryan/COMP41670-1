package setup;

/**Takes major communication to the player for setup
* Can be easily refactored to a GUI in line with MVC framework for main game
*/
public class SetupOutputs {


    public SetupOutputs() {}

    /**Limits set boundaries for selection
    */
    protected void numError(int i,int k){
        System.out.println("Please enter in a valid number between "+i+" and "+k);
    }

    protected void welcomeScreen(){
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
        System.out.println("Welcome to Forbidden Island - by Conor Kneafsey and Cathal Ryan");
    }


    protected void waterOptions(){
        System.out.println("What difficulty level would you like to play at? The options are: ");
        System.out.println("[1] - Novice, Flood Level set to 1");
        System.out.println("[2] - Normal, Flood Level set to 2");
        System.out.println("[3] - Elite, Flood Level set to 3");
        System.out.println("[4] - Legendary, Flood Level set to 4");
    }

    protected void howManyPlaying(){
        System.out.println("\nHow many people are playing? (must be between 2 and 4)");
    }

    /**Get name for player i
     * @param i index of player
    */
	protected void selectName(int i) {
        System.out.println("\nPlayer "+(i+1)+"...\nEnter your name:");
	}

	protected void nameError() {
        System.out.println("A player with this name already exists. Please enter in a different name: ");
	}

    /**Give player back their name, their player type and symbol, and their special ability.
     * @param name Player's name
     * @param playerType string including the player's type and their symbol
    */
	protected void playerAndType(String name, String playerType) {
        System.out.println(name+"'s adventurer is: "+playerType+" ");
        switch (playerType) {
	        case "Diver D":
	            System.out.println("You are a Diver, and so can swim to the nearest tile when a tile under you sinks!");
	            break;
	        case "Explorer E":
	            System.out.println("You are an Explorer, and so can swim diagonally when sunk!");
	            break;
	        case "Pilot P":
	            System.out.println("You are a Pilot, and so can fly anywhere on the board when forced to swim!");
                break;
            case "Navigator N":
                System.out.println("You are a Navigator, and so you have no special abilities. You're the \"Hufflepuff\" of Forbidden Island!");
                break;
            case "Engineer G":
                System.out.println("You are an Engineer, and so can shore up 2 tiles for 1 action!");
                break;    
            case "Messenger M":
                System.out.println("You are a Messenger, and so can give cards to players not on the same tile as you!");
                break;    
	        default:
	            System.out.println("Have fun playing Forbidden Island! Note: This should not appear!");
	    }
	}

	protected void setupOver() {
        System.out.println("\n Now, let's play FORBIDDEN ISLAND !");
        System.out.println("----------------------------------------------------------");
    }
    
    /**Tells player which tile has been flooded.
     * @param floodName name of tile flooded
    */
	protected void flooded(String floodName) {
        System.out.println(floodName +" has been flooded!");
	}
}
