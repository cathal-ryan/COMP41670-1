package setup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Point;


import player.Player;
import player.Team;
import board.Board;
/**
 * Class to handle all setting up of players for a game of Forbidden Island
 * Gives each player a pawn for the board, and a unique adventurer.
 * Methods are protected, they shouldn't be used outside of setup.
 * 
 * @author  Cathal Ryan and Conor Kneafsey
 */
public class PlayerSetup {

	private Team theTeam;
	private SetupOutputs setupOuts;
	private SetupInputs setupIns;
	private Board theBoard;
	private Point startLoc;
	
	/**Constructor for PlayerSetup class
	 */
	public PlayerSetup() {
		this.theTeam = Team.getInstance();
		setupOuts = new SetupOutputs();
		setupIns = new SetupInputs();
		this.theBoard = Board.getInstance();
	}

	/**Creates all of the players
	 * Gets a random number to randomly assign an adventurer
	 */
	protected void createAllPlayers() {
		setupOuts.howManyPlaying();
		int numOfPlayers = setupIns.setBetween(false); // Get number of players
		List<Integer> theNums = new ArrayList<>(); // List of all numbers for random selection
		List<String> usedNames = new ArrayList<>(); // Names that have been used before
		theNums.add(0);
		theNums.add(1);
		theNums.add(2);
		theNums.add(3);
		theNums.add(4);
		theNums.add(5);
		for (int i = 0; i < numOfPlayers; i++) {
			Random rand = new Random();  // get a random number
			int randint = rand.nextInt(theNums.size()); 
			int k = theNums.get(randint);
			theNums.remove(randint); // Get rid of this number so each adventurer is unique
			usedNames = createPlayer(i, k, usedNames); // Create a new player, with its name being returned and added to usedNames
		}
	}

	/**Creates an individual player
	 * @return list of all used player names, so choice must be unique
	 */
	protected List<String> createPlayer(int i, int k, List<String> usedNames) {
		setupOuts.selectName(i); 
		boolean validName=false;
		String name = "";
		// Get the player's name if its unique
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
		// Create the player, and set his starting position to the adventurer starting position.
		Player player = new Player(i,name,k);
    	startLoc = new Point(theBoard.getTilePos(player.pawnStartLoc()));
		player.setPawnPos(startLoc);
		theTeam.addPlayer(player);
		String playerType =  player.getPlayerType() +" "+ player.getChar();
		setupOuts.playerAndType(name, playerType); // Tell the user what kind of player they are
		return usedNames; // Let the createAllPlayers know what names have been used already
	}

}
