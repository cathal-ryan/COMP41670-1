package setup;

import java.util.Scanner;

import cards.DiscardPile;

/**Provides major communications with the player, with system.out
 * as a way of communicating
 * This can be changed if you wish by creating a different version of messenger e.g with a GUI
 */
public class SetupOutputs {


    public SetupOutputs() {}
    
    public void error(){
        System.out.println("Invalid input.");
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

}
