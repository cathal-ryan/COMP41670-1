package setup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Point;


import player.Player;
import player.Team;
import board.Board;

public class PlayerSetup {

	private Team theTeam;
	private SetupOutputs setupOuts;
	private SetupInputs setupIns;
	private Boolean validNumPlayers = false;
	private Board theBoard;
	private Point startLoc;
	
	public PlayerSetup() {
		this.theTeam = Team.getInstance();
		setupOuts = new SetupOutputs();
		setupIns = new SetupInputs();
		this.theBoard = Board.getInstance();
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
    	Player player = new Player(i,name,k);
    	startLoc = new Point(theBoard.getTilePos(player.pawnStartLoc()));
		player.setPawnPos(startLoc);
		theTeam.addPlayer(player);
		String playerType =  player.getPlayerType() +" "+ player.getChar();
		setupOuts.playerAndType(name, playerType);
		return usedNames;
	}

}
