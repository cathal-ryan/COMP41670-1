package setup;

import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import player.Player;
import player.Team;

public class PlayerSetup {

	private Team theTeam;
	private Boolean validNumPlayers = false;
	
	public PlayerSetup() {
		this.theTeam = Team.getInstance();
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
		theTeam.addPlayer(new Player(i,name,k));
		Player player = theTeam.getPlayer(i);
		printout(player.getName()+"'s adventurer is: "+ player.getPlayerType()+" ");
	}

	private void printout(String toPrint) {
		System.out.println(toPrint);
	}
}
