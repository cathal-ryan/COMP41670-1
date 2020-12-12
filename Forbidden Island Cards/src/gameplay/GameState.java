package gameplay;

import java.util.Observable;

import cards.Deck;
import cards.DiscardPile;
import cards.FloodDeck;
import cards.FloodDiscardPile;
import cards.TreasureDeck;
import cards.TreasureDiscardPile;
import player.Player;
import player.Team;

public class GameState extends Observable {
    private Team theTeam;
    private Player currentPlayer;
    private int actionsLeft;
    private boolean turnOver;
    private TreasureDiscardPile theTreasureDiscardPile;
    private FloodDiscardPile theFloodDiscardPile;
    private FloodDeck theFloodDeck;
    private TreasureDeck theTreasureDeck;
    

    private static GameState theGameState = null;
    
    private GameState() {
        theTeam = Team.getInstance();
        theFloodDeck = FloodDeck.getInstance();
        theTreasureDiscardPile = TreasureDiscardPile.getInstance();
        theFloodDiscardPile = FloodDiscardPile.getInstance();
        theTreasureDeck = TreasureDeck.getInstance();
        currentPlayer = theTeam.getPlayer(0);
    }

    public static GameState getInstance() {
        if(theGameState == null) {
            theGameState = new GameState();
        }
        return theGameState;
    }

    public void setNextPlayer() {
        int index = theTeam.getPlayerIndex(currentPlayer);
        try{
            currentPlayer = theTeam.getPlayer(index+1);
        }
        catch(Exception e){
            currentPlayer = theTeam.getPlayer(0);
        }
    }

    public int getNumPlayers(){
        return theTeam.getAllPlayers().size();
    }

	public String getPlayerName(int index) {
        if(index<0){
            return currentPlayer.getName();
        }
        return theTeam.getPlayer(index).getName();
    }
    
    public void setActionsLeft(){
        actionsLeft=3;
    }
    public int getActionsLeft(){
        return actionsLeft;
    }

	public void setTurnOver(boolean d) {
        turnOver=d;
    }  
    public boolean getTurnOver(){
        return turnOver;
    }

    public Deck returnDeck(boolean treasure){
        if (treasure){
            return theTreasureDeck;
        }
        else{
            return theFloodDeck;
        }
    }

    // public DiscardPile returnDiscard(boolean treasure){
    //     if (treasure){
    //         return theTreasureDiscardPile;
    //     }
    //     else{
    //         return theFloodDiscardPile;
    //     }
    // }

    public String showDiscard(boolean Treasure){
        if(Treasure){
            return theTreasureDiscardPile.returnPrintedPile();
        }
        else{
            return theFloodDiscardPile.returnPrintedPile();
        }
    }

    void movePlayer() {
		if(!currentPlayer.canMove()){
			return;
        }
        else{
            currentPlayer.getPawn().move();
        }
	}

	void shoreUp(){
		if(!currentPlayer.canShoreUp()){
			return;
        }
        else{
            currentPlayer.getPawn().shoreUp();
        }
	}

    private void useHelicopterLift() {
		theTeam.useHelicopterLift(inputScanner, player);
	}

    private void useSandbags() {
		theTeam.useSandbags(player);
	}
	
	private void useTeammateCard(){
		gameWon = theTeam.enquirePlayers(inputScanner, true);
	}

	private void captureATreasure(){
		if(!getActions()){
			return;
		}
		if(theTreasureHandler.captureTreasure(player)){
			actionsLeft--;
		}
	}

    private void giveCard() {
		if(!getActions()){
			return;
		}
		if(!player.getHand().canTrade()){
			System.out.println("You can't trade right now :(");
			return;
		}
		// Needs to be changed to who is on the same tile as you.
		displayHands();
		System.out.println("\nWho do you want to give a card to?");

		Player playernum = theTeam.choosePlayer(inputScanner, theTeam.getAllPlayerNums(player.getNum()));
		boolean validSelection = false;
		while(!validSelection){
			int cardnum = player.chooseFromHand(inputScanner, "give? You can't give Sandbags or Helicopter Lift", true);
			validSelection = player.giveTreasureCard(playernum, cardnum, inputScanner);
		}
		actionsLeft--;
	}

	public String getHandasString(int i) {
        return theTeam.getPlayer(i).getHand().printHand();
    }

	public boolean canTrade() {
		return false;
	}    

}
