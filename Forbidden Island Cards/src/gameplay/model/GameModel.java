package gameplay.model;

import java.awt.Point;
import java.util.*;
import board.Board;
import cards.*;
import enums.*;
import player.*;
import pawns.*;
import gameplay.control.LoseObserver;
import gameplay.control.Observer;
import gameplay.control.WinObserver;


/**
 * Singleton class representing the Game Model using MVC Pattern
 * Keeps the entire state of the game in one class which can be accessed by the controller
 * Has access to change multiple elements of the current state of the game
 * And can return important details to the Controller, who can prompt the model
 * to perform other actions to edit the game state.
 * Implements subject interface, will notify observers when changes in game state
 * create loss or win conditions
 * 
 * @author Cathal Ryan and Conor Kneafsey
 *
 */
public class GameModel implements Subject {

    private Team 				theTeam;                // The team. Functionally a list of players
    private Player 				currentPlayer;          // The player currently in control/has turn
    private int 				actionsLeft;            // How many turns the current player has
    private boolean 			turnOver;               // Has their turn ended
    private WaterMeter 			theWaterMeter;          // The Water Meter, must not go above 5
    private TreasureDiscardPile theTreasureDiscardPile; // Discard pile for Treasure cards
    private FloodDiscardPile 	theFloodDiscardPile;    // Discard pile for Flood cards
    private FloodDeck 			theFloodDeck;           // Deck for Flood Cards
    private TreasureDeck 		theTreasureDeck;        // Deck for Treasure Cards
    private Board 				theBoard;               // The Game Board
    private TreasureHandler 	theTreasureHandler;     // Treasure Handler allows capturing treasures
    private Observer 			loser;                  // Loss observer
    private Observer 			winner;                 // Win observer
    private static GameModel 	theGameModel = null;    // Singleton declaration

    private GameModel() {
        theTeam = Team.getInstance();
        theBoard = Board.getInstance();
        theFloodDeck = FloodDeck.getInstance();
        theTreasureDiscardPile = TreasureDiscardPile.getInstance();
        theFloodDiscardPile = FloodDiscardPile.getInstance();
        theTreasureDeck = TreasureDeck.getInstance();
        theTreasureHandler = TreasureHandler.getInstance();
        theWaterMeter = WaterMeter.getInstance();
        loser = new LoseObserver();
        winner = new WinObserver();
    }

    public static GameModel getInstance() {
        if (theGameModel == null) {
            theGameModel = new GameModel();
        }
        return theGameModel;
    }

    @Override
    public void notifyUpdate(Observer o, int m) {
        o.update(m);
    }

    public void setNextPlayer() {
        int index = theTeam.getPlayerIndex(currentPlayer);
        try {
            currentPlayer = theTeam.getPlayer(index + 1);
        } catch (Exception e) {
            currentPlayer = theTeam.getPlayer(0);
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isEngineer() {
        if(currentPlayer.getPawn() instanceof Engineer)
            return true;
        return false;
    }

    public int getNumPlayers() {
        return theTeam.getNumPlayers();
    }
    
    public List<TreasureCard> getPlayerHand(Player p1) {
        return p1.showHand();
    }

    public String getPlayerNameFromIndex(int index) {
        if (index < 0) {
            return currentPlayer.getName();
        }
        return theTeam.getPlayer(index).getName();
    }

    public String getPlayerName(Player player) {
        return player.getName();
    }
    
    public int getHandSize(Player play1) {
        return play1.handSize();
    }
    
    public boolean hasCardsforTrade() {
        Hand hand = currentPlayer.getHand();
        if(!hand.canTrade()) 
            return false;
        else
            return true;
    }
    
    public List<Integer> getAllPlayerNums(int i) {
        return theTeam.getAllPlayerNums(i);
    }

	public Player getPlayer(int userIn) {
        if(userIn<0){
            return currentPlayer;
        }
        return theTeam.getPlayer(userIn);
    }

    public void setActionsLeft() {
        actionsLeft = 3;
    }

    public void increaseActions() {
        actionsLeft++;
    }

    public int getActionsLeft() {
        return actionsLeft;
    }

    public void setTurnOver(boolean d) {
        turnOver = d;
    }

    public boolean getTurnOver() {
        return turnOver;
    }

    public String showDiscard(boolean Treasure) {
        if (Treasure) {
            return theTreasureDiscardPile.returnPrintedPile();
        } 
        else {
            return theFloodDiscardPile.returnPrintedPile();
        }
    }

    public boolean movePlayer(char dir) {
        if(currentPlayer.getPawn().move(dir)) {
            decreaseActions();
            return true;
        }
        return false;
    }

    public boolean shoreUp(Point p) {
        if (!currentPlayer.getPawn().canShoreUp(p)) {
            return false;
        } else {
            theBoard.shoreUpTile(p);
            decreaseActions();
            return true;
        }
    }

    public void decreaseActions() {
        actionsLeft--;
    }

    public String getHandasString(int i) {
        if (i == -1) {
            return currentPlayer.getHand().getHandasString();
        }
        return theTeam.getPlayer(i).getHand().getHandasString();
    }

    public boolean checkHasCard(Player p1, boolean cardName) {
        if (cardName == false) {
            return p1.checkHasCard(TreasureCardEnums.SANDBAGS);
        } else if (cardName) {
            System.out.println("");
            return p1.checkHasCard(TreasureCardEnums.HELICOPTER_LIFT);
        }
        return false;
    }

    public void removeCard(Player p1, TreasureCardEnums card) {
        int pos = p1.getHand().getIndexOfCard(card);
        p1.getHand().removeCard(pos);
    }

    public List<Integer> getTradePartners() {
        List<Integer> allPlayers = theTeam.getAllPlayerNums(currentPlayer.getNum());
        if(!(currentPlayer.getPawn() instanceof Messenger)){
            Iterator<Integer> i = allPlayers.iterator();
            while (i.hasNext()){
                int x = i.next();
                if (!getPlayer(x).getPawnPos().equals(currentPlayer.getPawnPos())){
                    i.remove();
                }
            }
        }
        return allPlayers;
    }

    public boolean addCardfromPlayerA(Player PlayerB, int canum) {
        Hand playerHand = currentPlayer.getHand();
        TreasureCard c1 = playerHand.getCards().get(canum);
        TreasureCardEnums name = (TreasureCardEnums)c1.getName();
        if ((name == TreasureCardEnums.HELICOPTER_LIFT || name == TreasureCardEnums.SANDBAGS)) {
            return false;
        } 
        else {
            PlayerB.addCardtoHand(c1);
            currentPlayer.getHand().getCards().remove(canum);
            return true;
        }
    }

    public void removeCardByIndex(Player player, int userIn) {
        player.getHand().getCards().remove(userIn);
    }

    public List<Integer> getPlayerswithSpecials() {
        List<Integer> eligible = new ArrayList<>();
        int i = 0;
        for (Player p1 : theTeam.getAllPlayers()) {
            if (p1.getHand().checkContains(TreasureCardEnums.HELICOPTER_LIFT)
                    || p1.getHand().checkContains(TreasureCardEnums.SANDBAGS)) {
                eligible.add(i);
            }
            i++;
        }
        return eligible;
    }

    public void heliMovePlayer(Player playerForHeliMove, Point p) {
        playerForHeliMove.helicopterMove(p);
    }

    public void useSandbags(Point p) {
        TilesEnums name = theBoard.getTileName(p);
        theBoard.shoreUpTile(name);
    }

    public TreasureCard dealTreasure() {
        TreasureCard c1 = (TreasureCard) theTreasureDeck.dealCard();
        if ((TreasureCardEnums)c1.getName() == TreasureCardEnums.WATERS_RISE) {
            WaterMeter.cardDrawn();
            int waterLevel = WaterMeter.getWaterlevel();
            if (waterLevel >= 5) {
                notifyUpdate(loser,6);
            }
        }
        return c1;
    }

    public void addToPile(TreasureCard c1) {
        theTreasureDiscardPile.addToPile(c1);
    }

    public void addCardfromDeck(TreasureCard c1) {
        currentPlayer.getHand().addCard(c1);
    }

    public int getWaterLevel() {
        return theWaterMeter.getWaterlevel();
    }

    public Card dealFlood() {
        Card card1 = theFloodDeck.dealCard();
        TilesEnums t1 = (TilesEnums) card1.getName();
        theBoard.floodTile(t1);
        if((isSunk(TilesEnums.FOOLS_LANDING))){
            notifyUpdate(loser,4); // currently you lose whenever fools landing drawn
        }
        if((isSunk(TilesEnums.TEMPLE_OF_THE_MOON)) && (isSunk(TilesEnums.TEMPLE_OF_THE_SUN))){
            if(!theTreasureHandler.queryCaptured(TypeEnums.EARTH)){
                notifyUpdate(loser,0);
            }        
        }
        if((isSunk(TilesEnums.CAVE_OF_EMBERS)) && (isSunk(TilesEnums.CAVE_OF_SHADOWS))){
            if(!theTreasureHandler.queryCaptured(TypeEnums.FIRE)){
                notifyUpdate(loser,1);
            }
        }
        if((isSunk(TilesEnums.CORAL_PALACE)) && (isSunk(TilesEnums.TIDAL_PALACE))){
            if(!theTreasureHandler.queryCaptured(TypeEnums.WATER)){
                notifyUpdate(loser,2);
            }        
        }
        if((isSunk(TilesEnums.WHISPERING_GARDEN)) && (isSunk(TilesEnums.HOWLING_GARDEN))){
            if(!theTreasureHandler.queryCaptured(TypeEnums.WIND)){
                notifyUpdate(loser,3);
            }        
        }
        if(!isSunk(t1)){
            theFloodDiscardPile.addToPile(card1);
        }
        return card1;
    }

    public Boolean isSunk(TilesEnums name) {
        return theBoard.isTileSunk(name);
    }

    public List<TypeEnums> listCaptured(){
        return theTreasureHandler.captured();
    }

	public boolean canWin() {
        boolean winnable = true;
        if (!theTreasureHandler.allCaptured()) {
            winnable= false;
        }
        for (Player player:theTeam.getAllPlayers()){
            Point playerpos = player.getPawnPos();
            TilesEnums tilename = theBoard.getTileName(playerpos);
            if(!tilename.equals(TilesEnums.FOOLS_LANDING)){
                winnable= false;
            }
        }
        if(winnable){
            notifyUpdate(winner,7);
            notifyUpdate(loser,7);
        }
        return winnable;
	}

	public int capture() {
        // Check players position for if its on a treasure tile, and get name of tile
        // tile = pawn.currentTile();
        TypeEnums tile = theGameModel.getCurrentTileType();
        // Need to check are they on a treasure tile
        boolean onTreasureTile = (tile==TypeEnums.EARTH) || (tile==TypeEnums.FIRE) || (tile==TypeEnums.WIND) || (tile==TypeEnums.WATER);
        if(!onTreasureTile){
            return 2;
        }

        // Need to check if a treasure has already been captured.
        if(theTreasureHandler.queryCaptured(tile)){
            return 1;
        }

        if(currentPlayer.getHand().numofInstances(tile)<4){
            return 3;
        }
        currentPlayer.getHand().discardforTreasure(tile);
        theTreasureHandler.setTreasureCapture(tile);
        decreaseActions();
		return 0;
    }

	public TypeEnums getCurrentTileType() {
        Point location = currentPlayer.getPawnPos();
        return theBoard.getTileType(location);
    }

	public Point getTilePos(TilesEnums t1) {
		return theBoard.getTilePos(t1);
	}

	public boolean canPlayerSwim(Player player) {
        if (!player.getPawn().canSwim()){
            notifyUpdate(loser,5);
            return false;
        }
        else{
            return true;
        }
	}

	public List<Point> getValidTiles() {
        return theBoard.getValidTiles();
    }

	public List<Point> getSandbagsTiles() {
		return theBoard.getSandbagsTiles();
	}
}
