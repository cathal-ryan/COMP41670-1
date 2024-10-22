package player;

import java.util.List;
import java.awt.Point;

import pawns.*;
import cards.Hand;
import cards.TreasureCard;
import enums.TreasureCardEnums;
import enums.TilesEnums;

/**
 * Class for each Player. Players have a unique name, character and number to
 * identify them, along with a hand and a pawn
 * 
 * @author Cathal Ryan and Conor Kneafsey
 */
public class Player {

	private String 		playerName;
	private Hand 		playerHand;
	private Pawn 		playerPawn;
	private int 		playerNum;
	private String 		playerChar;

	/**
	 * Constructor for a Player object
	 * 
	 * @param playerNum     The Player's number
	 * @param playerName    The Player's name
	 * @param adventurerNum Integer representing what adventurer to set the pawn as.
	 *                      Should be generated randomly between 0 and 5
	 */
	public Player(int playerNum, String playerName, int adventurerNum) {
		this.playerName = playerName;
		this.playerHand = new Hand(); // Create hand
		this.playerNum = playerNum;
		// Player's pawn and character will be based on adventurerNum
		switch (adventurerNum) {
			case 0:
				playerPawn = new Diver();
				playerChar = "D";
				break;
			case 1:
				playerPawn = new Engineer();
				playerChar = "G";
				break;
			case 2:
				playerPawn = new Explorer();
				playerChar = "E";
				break;
			case 3:
				playerPawn = new Navigator();
				playerChar = "N";
				break;
			case 4:
				playerPawn = new Pilot();
				playerChar = "P";
				break;
			case 5:
				playerPawn = new Messenger();
				playerChar = "M";
				break;
			default:
				playerPawn = new Navigator();
				playerChar = "N";
				break;
		}
	}
	
	
	//////////////////
	// Getters and Setters
	//////////////////
	public String getName() {
		return playerName;
	}

	public int getNum() {
		return playerNum;
	}

	public Hand getHand() {
		return playerHand;
	}

	public Pawn getPawn(){
		return playerPawn;
	}

	public String getChar(){
		return playerChar;
	}

	public String getPlayerType(){
		return playerPawn.getPlayerType();
	}

	public Point getPawnPos(){
		return playerPawn.getPos();
	}
	
	public void setPawnPos(Point p){
		playerPawn.setPos(p);
	}
	
	/** The player pawn's starting location
	 */
	public TilesEnums pawnStartLoc(){
		return playerPawn.startLoc();
	}

	///////
	// Important Methods 
	///////
	
	/** Returns hand as a list to be used externally
	 * @return list of cards
	 */
	public List<TreasureCard> showHand() {
		return playerHand.getCards();
	}

	public int handSize() {
		return playerHand.getCards().size();
	}

	/** Allows addition of a card to the player hand
	 * @param c1 the card to be added to the hand
	 */
	public void addCardtoHand(TreasureCard c1){
		playerHand.addCard(c1);
	}

	/** Checks whether the player has this card in their hand or not 
	 * 	@param name - the name of the treasure card to be checked
	 */
	public boolean checkHasCard(TreasureCardEnums name){
		if(!getHand().checkContains(name)){
			return false;
		}
		return true;		
	}
	
	/** Checks whether the player has this card in their hand or not 
	 * 	@param name - the name of the treasure card to be checked
	 */
	public boolean movePawn(char dir) {
		return playerPawn.move(dir);
	}

	/** Flys a player to a point
	 * 	@param p - the point to move the player to
	 */
	public void helicopterMove(Point p){
		playerPawn.helicopterMove(p);
	}
	
	/** Shores up a player based on a point input
	 * 	@param p - the point to shore up
	 *  @return boolean value indicating was shoring up succesful
	 */
	public boolean pawnShoreUp(Point p) {
		return playerPawn.canShoreUp(p);
	}
}
