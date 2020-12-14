package player;

import java.util.List;
import java.awt.Point;

import pawns.Pawn;
import pawns.*;

import cards.Hand;
import cards.TreasureDeckCard;
import enums.TreasureCardEnums;
import enums.TilesEnums;


public class Player {
	// ===========================================================
	// Variable Setup
	// ===========================================================
	private String playerName;
	private Hand playerHand;
	private Pawn playerPawn;
	private int playerNum;

	public Player(int playerNum, String playerName, int adventurerNum) {
		this.playerName = playerName;
		this.playerHand = new Hand();
		this.playerNum = playerNum;
		switch (adventurerNum) {
			case 0:
				playerPawn = new Diver();
				break;
			case 1:
				playerPawn = new Engineer();
				break;
			case 2:
				playerPawn = new Explorer();
				break;
			case 3:
				playerPawn = new Navigator();
				break;
			case 4:
				playerPawn = new Pilot();
				break;
			case 5:
				playerPawn = new Messenger();
				break;
			default:
				System.out.println("I shouldn't be here!");
		}
	}
	
	public String getName() {
		return playerName;
	}
	public int getNum() {
		return playerNum;
	}

	public List<TreasureDeckCard> showHand() {
		return playerHand.getCards();
	}
	
	public Hand getHand() {
		return playerHand;
	}
	
	public int handSize() {
		return playerHand.getCards().size();
	}

	public void drawTreasureCard(TreasureDeckCard card1) {
		playerHand.addCard(card1);
	}
	
	public Pawn getPawn(){
		return playerPawn;
	}

	public void setPawnPos(Point p){
		playerPawn.setPos(p);
	}

	public Point getPawnPos(){
		return playerPawn.getPos();
	}

	public TilesEnums pawnStartLoc(){
		return playerPawn.startLoc();
	}

	public String getPlayerType(){
		return playerPawn.getPlayerType();
	}

	public void addCardtoHand(TreasureDeckCard c1){
		playerHand.addCard(c1);
	}

	public boolean checkHasCard(TreasureCardEnums name){
		if(!getHand().checkContains(name)){
			return false;
		}
		return true;		
	}

	public void helicopterMove(int k){
		playerPawn.helicopterMove(k);
	}

	public boolean canMove() {
		return true;
	}

	public boolean canShoreUp() {
		return true;
	}
}
