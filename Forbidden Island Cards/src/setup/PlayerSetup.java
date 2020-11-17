package setup;
import java.util.*; 
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import player.Diver;
import player.Engineer;
import player.Explorer;
import player.Messenger;
import player.Navigator;
import player.Pilot;
import player.Player;
import player.PlayerList;

public class PlayerSetup {

	private PlayerList playerList;
	private Boolean validNumPlayers = false;
	
	public PlayerSetup() {
		this.playerList = PlayerList.getInstance();

	}

	protected void createAllPlayers(Scanner user) {
		int numOfPlayers;
		boolean playersSelected = false;

		while (!playersSelected) {
			numOfPlayers = 0;
			while (!validNumPlayers) {
				numOfPlayers = getNumberOfPlayers(user);
			}
			List<Integer> thelist = new ArrayList<>();

	        thelist.add(0);
	        thelist.add(1);
	        thelist.add(2);
	        thelist.add(3);
	        thelist.add(4);
	        thelist.add(5);

			for (int i = 0; i < numOfPlayers; i++) {
		        Random rand = new Random(); 
		        int randint = rand.nextInt(thelist.size()); 
		        int k = thelist.get(randint);
		        thelist.remove(randint);

				createIndividualPlayer(user, i, k);
			}
			playersSelected = true;
		}
		
	}

	public int getNumberOfPlayers(Scanner user) {
		String userString;
		printout("\nHow many people are playing? (must be between 2 and 4)");
		userString = user.nextLine();
		return setNumPlayers(userString);
	}

	public int setNumPlayers(String userString) {
		int numOfPlayers =0;
		try {
			numOfPlayers = Integer.parseInt(userString);
		} catch (NumberFormatException e) {
			return numOfPlayers;
		}

		if ((numOfPlayers >= 2) && (numOfPlayers <= 4)) {
			validNumPlayers = true;
		}
		else{
			printout("Incorrect input\n");
		}
		return numOfPlayers;
	}

	public void createIndividualPlayer(Scanner user, int i, int k) {
		printout("\nPlayer "+(i+1)+"...");
		printout("Enter your name:");
		String name = user.nextLine();

		switch (k) {
	    	case 0:  	playerList.addPlayer(new Diver(i+1, name)); 	break;
	    	case 1:  	playerList.addPlayer(new Engineer(i+1, name));   	break;           		
	    	case 2: 	playerList.addPlayer(new Explorer(i+1, name));	break;
	    	case 3: 	playerList.addPlayer(new Navigator(i+1, name));	break;	
	    	case 4: 	playerList.addPlayer(new Pilot(i+1, name));	break;
	    	case 5: 	playerList.addPlayer(new Messenger(i+1, name));	break;
	    	default: printout("why am i hereCASE ERROR IN PlayerTurn.doTurn()");
		}
		Player player = playerList.getPlayer(i+1);
		printout(player.getName()+"'s adventurer is: "+ player.getPlayerType()+" ");
	}

	private void printout(String toPrint) {
		System.out.println(toPrint);
	}
}
