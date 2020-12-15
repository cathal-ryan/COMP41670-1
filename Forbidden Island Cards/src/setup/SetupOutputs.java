package setup;

import java.util.Scanner;

import cards.DiscardPile;

/**Provides major communications with the player, with system.out
 * as a way of communicating
 * This can be changed if you wish by creating a different version of messenger e.g with a GUI
 */
public class SetupOutputs {


    public SetupOutputs() {

    }

    public void numError(int i,int k){
        System.out.println("Please enter in a valid number between "+i+" and "+k);
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
        System.out.println("Welcome to Forbidden Island - by Conor Kneafsey and Cathal Ryan");
    }


    public void waterOptions(){
        System.out.println("What difficulty level would you like to play at? The options are: ");
        System.out.println("[1] - Novice, Flood Level set to 1");
        System.out.println("[2] - Normal, Flood Level set to 2");
        System.out.println("[3] - Elite, Flood Level set to 3");
        System.out.println("[4] - Legendary, Flood Level set to 4");
    }

    public void howManyPlaying(){
        System.out.println("\nHow many people are playing? (must be between 2 and 4)");
    }

	public void selectName(int i) {
        System.out.println("\nPlayer "+(i+1)+"...\nEnter your name:");
	}

	public void nameError() {
        System.out.println("A player with this name already exists. Please enter in a different name: ");
	}

	public void playerAndType(String name, String playerType) {
        System.out.println(name+"'s adventurer is: "+playerType+" ");
	}

	public void setupOver() {
        System.out.println("\n Now, let's play FORBIDDEN ISLAND !");
        System.out.println("----------------------------------------------------------");
	}

	public void flooded(String floodName) {
        System.out.println(floodName +" has been flooded!");
	}
}