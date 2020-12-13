package gameplay.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import cards.Deck;
import cards.DiscardPile;
import cards.FloodDeck;
import cards.FloodDiscardPile;
import cards.Hand;
import cards.TreasureCard;
import cards.TreasureDeck;
import cards.TreasureDeckCard;
import cards.TreasureDiscardPile;
import enums.TreasureCardEnums;
import gameplay.WaterMeter;
import player.Player;
import player.Team;
import javax.security.auth.Subject;

public class GameModel extends Observable {
//    private Controller theController = Controller.getInstance();
    private Team theTeam;
    private Player currentPlayer;
    private int actionsLeft;
    private boolean turnOver;
    private WaterMeter theWaterMeter;
    private TreasureDiscardPile theTreasureDiscardPile;
    private FloodDiscardPile theFloodDiscardPile;
    private FloodDeck theFloodDeck;
    private TreasureDeck theTreasureDeck;
    
    private static GameModel theGameModel = null;
    
    private GameModel() {
        theTeam = Team.getInstance();
        theFloodDeck = FloodDeck.getInstance();
        theTreasureDiscardPile = TreasureDiscardPile.getInstance();
        theFloodDiscardPile = FloodDiscardPile.getInstance();
        theTreasureDeck = TreasureDeck.getInstance();
        currentPlayer = theTeam.getPlayer(theTeam.getNumPlayers()-1);
        theWaterMeter = WaterMeter.getInstance();
       // theController = Controller.getInstance();
    }

    public static GameModel getInstance() {
        if(theGameModel == null) {
            theGameModel = new GameModel();
        }
        return theGameModel;
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

    public Player getCurrentPlayer(){
        return currentPlayer;
    }

    public int getNumPlayers(){
        return theTeam.getAllPlayers().size();
    }

	public String getPlayerNameFromIndex(int index) {
        if(index<0){
            return currentPlayer.getName();
        }
        return theTeam.getPlayer(index).getName();
    }

    public String getPlayerName(Player player){
        return player.getName();
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

    public String showDiscard(boolean Treasure){
        if(Treasure){
            return theTreasureDiscardPile.returnPrintedPile();
        }
        else{
            return theFloodDiscardPile.returnPrintedPile();
        }
    }

    public void movePlayer() {
		if(!currentPlayer.canMove()){
			return;
        }
        else{
            currentPlayer.getPawn().move();
            decreaseActions();
        }
	}

    public void decreaseActions(){
        actionsLeft--;
    }
	public void shoreUp(){
		if(!currentPlayer.canShoreUp()){
			return;
        }
        else{
            currentPlayer.getPawn().shoreUp();
        }
    }

	public String getHandasString(int i) {
        return theTeam.getPlayer(i).getHand().getHandasString();
    }

	public boolean canTrade() {
        Hand hand = currentPlayer.getHand();
        if(!hand.canTrade()){
            return false;
        }
        if(checkPlayerPostions()){
            return false;
        }
        return true;
	}
    
	private boolean checkPlayerPostions() {
        return false;
    }

    public boolean checkHasCard(Player p1, boolean cardName) {
		if (cardName==false){
            return p1.checkHasCard(TreasureCardEnums.SANDBAGS);
        }
        else if (cardName){
            System.out.println("");
            return p1.checkHasCard(TreasureCardEnums.HELICOPTER_LIFT);
        }
        return false;
	}

	public List<Integer> getAllPlayerNums(int i) {
		return theTeam.getAllPlayerNums(i);
	}

	public int getTeamSize() {
		return theTeam.getNumPlayers();
	}

	public Player getPlayer(int userIn) {
		return theTeam.getPlayer(userIn);
	}

	public void removeCard(Player p1, TreasureCardEnums card) {
        int pos = p1.getHand().getIndexOfCard(card);
		p1.getHand().removeCard(pos);
	}

	public void heliMovePlayer(Player playerForHeliMove, int k) {
        playerForHeliMove.helicopterMove(k);
	}

	public List getTradePartners() {
        // GONNA NEED SOME LOGIC TO GIVE WHICH PLAYERS ARE ON SAME TILE AS CURRENTPLAYER, right now just returns everyone.
        return theTeam.getAllPlayerNums(currentPlayer.getNum()); 
	}

	public List getPlayerHand(Player p1) {
		return p1.showHand();
	}

	public boolean addCardfromPlayerA(Player PlayerB, int canum) {
        Hand playerHand = currentPlayer.getHand();
        TreasureDeckCard c1 = playerHand.getCards().get(canum);
		if(!(c1 instanceof TreasureCard)){
			return false;
        }
        else{
            PlayerB.addCardtoHand(c1);
            currentPlayer.getHand().getCards().remove(canum);
            return true;
        }
    }
    
    public int getHandSize(Player play1){
        return play1.handSize();
    }
    
	public void removeCardByIndex(Player player, int userIn) {
        player.getHand().getCards().remove(userIn);
    }

    public List<Integer> getPlayerswithSpecials(){
        List<Integer> eligible = new ArrayList<>();
        int i=0;
        for (Player p1 : theTeam.getAllPlayers()){
            if(p1.getHand().checkContains(TreasureCardEnums.HELICOPTER_LIFT) || p1.getHand().checkContains(TreasureCardEnums.SANDBAGS)){
                eligible.add(i);
            }
            i++;
        }
        return eligible;
    }

	public void useSandbags() {
	}
    

}
