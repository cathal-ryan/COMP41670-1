package setup;

import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import player.Player;
import player.Team;

public class PlayerSetup {

	private Team theTeam;
	private SetupOutputs setupOuts;
	private SetupInputs setupIns;
	private Boolean validNumPlayers = false;
	
	public PlayerSetup() {
		this.theTeam = Team.getInstance();
		setupOuts = new SetupOutputs();
		setupIns = new SetupInputs();
	}

	protected void createAllPlayers() {
		boolean playersSelected = false;
		setupOuts.howManyPlaying();
		int numOfPlayers = setupIns.setBetween(false);
		List<Integer> thelist = new ArrayList<>();
		List<String> usedNames = new ArrayList();
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
			usedNames = createIndividualPlayer(i, k, usedNames);
		}
	}
	
	public List<String> createIndividualPlayer(int i, int k, List<String> usedNames) {
		setupOuts.selectName(i);
		boolean validName=false;
		String name = "";
		while(!validName){
			name = setupIns.playerName();
			if (!usedNames.contains(name)){
				validName=true;
			}
			else{
				setupOuts.nameError();
			}
		}
		usedNames.add(name);
		theTeam.addPlayer(new Player(i,name,k));
		Player player = theTeam.getPlayer(i);
		String playerType =  player.getPlayerType();
		setupOuts.playerAndType(name, playerType);
		return usedNames;
	}
}
