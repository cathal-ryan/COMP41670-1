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

    /**
     * The private GameModel constructor
     */
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

    /**
     * getInstance singleton method gets single instance
     * of the GameModel.
     * @return Singleton GameModel object
     */
    public static GameModel getInstance() {
        if (theGameModel == null) {
            theGameModel = new GameModel();
        }
        return theGameModel;
    }

    /**
      * Update an observer with an int to tell how the
      * game was won or lost
      */
    @Override
    public void notifyUpdate(Observer o, int m) {
        o.update(m);
    }

    /**
      * cycle down to the next player. If its the last player
      * then the next player is the first player
      */
    public void setNextPlayer() {
        int index = theTeam.getPlayerIndex(currentPlayer);
        try {
            currentPlayer = theTeam.getPlayer(index + 1);
        } catch (Exception e) {
            currentPlayer = theTeam.getPlayer(0);
        }
    }

    /**
      * get the player whose turn it is
      */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
      * check if the current player is an Engineer
      */
    public boolean isEngineer() {
        if(currentPlayer.getPawn() instanceof Engineer)
            return true;
        return false;
    }

    /**
      * get the number of players who are playing
      */
    public int getNumPlayers() {
        return theTeam.getNumPlayers();
    }
    
    /**
      * return a given players hand
      */
    public List<TreasureCard> getPlayerHand(Player p1) {
        return p1.showHand();
    }

    /**
      * get the name of the player from their player number
      */
    public String getPlayerNameFromIndex(int index) {
        if (index < 0) {
            return currentPlayer.getName();
        }
        return theTeam.getPlayer(index).getName();
    }

    /**
      * get the player name from a Player Object
      */
    public String getPlayerName(Player player) {
        return player.getName();
    }
    
    /**
      * return the hand size of a player given a Plsyer object
      */
    public int getHandSize(Player play1) {
        return play1.handSize();
    }
    
    /**
      * check if the current player can trade
      * cannot trade if the hand is empty or have no treasures
      */
    public boolean hasCardsforTrade() {
        Hand hand = currentPlayer.getHand();
        if(!hand.canTrade()) 
            return false;
        else
            return true;
    }
    
    /**
      * return a list of all the player numbers
      */
    public List<Integer> getAllPlayerNums(int i) {
        return theTeam.getAllPlayerNums(i);
    }

    /**
      * get a Player object from their user index
      */
	public Player getPlayer(int userIn) {
        if(userIn<0){
            return currentPlayer;
        }
        return theTeam.getPlayer(userIn);
    }

    /**
      * Set the amount of actions left to 3
      */
    public void setActionsLeft() {
        actionsLeft = 3;
    }

    /**
      * Increase the amount of actions by 1
      */
    public void increaseActions() {
        actionsLeft++;
    }

    /**
      * return the amount of action a player has left
      */
    public int getActionsLeft() {
        return actionsLeft;
    }

    /**
      *
      */
    public void decreaseActions() {
        actionsLeft--;
    }

    /**
      * set if a turn is over or not
      */
    public void setTurnOver(boolean d) {
        turnOver = d;
    }

    /**
      * check if a players turn is over or not
      */
    public boolean getTurnOver() {
        return turnOver;
    }

    /**
      * show the card discarded whether it is a treasure card from hand
      * or a flood card
      */
    public String showDiscard(boolean Treasure) {
        if (Treasure) {
            return theTreasureDiscardPile.returnPrintedPile();
        } 
        else {
            return theFloodDiscardPile.returnPrintedPile();
        }
    }

    /**
      * try to move a player and if it is successfull, decrease their
      * actions and return true
      */
    public boolean movePlayer(char dir) {
        if(currentPlayer.movePawn(dir)) {
            decreaseActions();
            return true;
        }
        return false;
    }

    /**
      * try to shore up a tile and if it is successful, decrease their
      * actions and return true
      */
    public boolean shoreUp(Point p) {
        if (!currentPlayer.pawnShoreUp(p)) {
            return false;
        } else {
            theBoard.shoreUpTile(p);
            decreaseActions();
            return true;
        }
    }

    /**
      * return a String containing the cards in a players hand
      * uses their player number to determine which hand to show
      */
    public String getHandasString(int i) {
        if (i == -1) {
            return currentPlayer.getHand().getHandasString();
        }
        return theTeam.getPlayer(i).getHand().getHandasString();
    }

    /**
      * check if a given Player object contains a sandbags or 
      * helicopter card
      */
    public boolean checkHasCard(Player p1, boolean Helicopter) {
        if (!Helicopter) 
            return p1.checkHasCard(TreasureCardEnums.SANDBAGS);
        else
            return p1.checkHasCard(TreasureCardEnums.HELICOPTER_LIFT);
    }

    /**
      * remove a given card from the given Player objects's hand
      */
    public void removeCard(Player p1, TreasureCardEnums card) {
        int pos = p1.getHand().getIndexOfCard(card);
        p1.getHand().removeCard(pos);
    }

    /**
      * return a list of all the players the current player can trade with
      * to trade with another player, they must be on the same tile
      * the messenger can trade with anyone
      */
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

    /**
      * faciliates trading between 2 players. It removes a given card from the
      * current player, as long as is not a helicopter lift or sandbag card, and
      * gives it to the specified player
      */
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

    /**
      * removes the card in a given postion within the given players hand
      */
    public void removeCardByIndex(Player player, int index) {
        player.getHand().getCards().remove(index);
    }

    /**
      * find the players with either a helicopter lift or sandbags card in
      * their hand.
      * returns a list with the player numbers who have the cards
      */
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

    /**
      * move the specified player with the helicopterMove() method to 
      * the given point
      */
    public void heliMovePlayer(Player playerForHeliMove, Point p) {
        playerForHeliMove.helicopterMove(p);
    }

    /**
      * use a sandbags card to shore up a tile in a given position
      */
    public void useSandbags(Point p) {
        theBoard.shoreUpTile(p);
    }

    /**
      * deal a card from the treasure deck. If it is a water rise card,
      * then put all the discarded flood cards back into the flood deck
      * if the water level has risen to 5, update the observer with the
      * lose condition
      */
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

    /**
      * adds a card from the treasure deck to the discard pile
      */
    public void addToPile(TreasureCard c1) {
        theTreasureDiscardPile.addToPile(c1);
    }

    /**
      * adds a specified card from the treasure deck to the current
      * players hand
      */
    public void addCardfromDeck(TreasureCard c1) {
        currentPlayer.getHand().addCard(c1);
        
    }

    /**
      * return the water level
      */
    @SuppressWarnings("static-access")
    public int getWaterLevel() {
        return theWaterMeter.getWaterlevel();
    }

    /**
      * deal a card from the flood deck and flood the specified tile
      * if this does not sink the tile, add the card to the discard pile,
      * otherwise it is removed from game
      * Contains checks to various lose condition like if fools landing
      * sinks, or any of the treasure capture tiles sink before that 
      * treasure is captured.
      * If a lose condition is met, update the observer
      */
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

    /**
      * check if the specified tile is sunk or not
      */
    public Boolean isSunk(TilesEnums name) {
        return theBoard.isTileSunk(name);
    }

    /**
      * return a list containing the treasures that have been captured
      */
    public List<TypeEnums> listCaptured(){
        return theTreasureHandler.captured();
    }

    /**
      * check if the game has been won. Returns false if not all the
      * treasures have been captured or if not all the players are on
      * fools landing. 
      * If the game is winnable then update the observer
      */
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

    /**
      * attempt to capture a treasure. Checks if the player is on a tile 
      * where treasure can be captured, if the treasure has already been
      * captured, and if they have enough treasure cards in hand
      * If all these conditions are met, it captures the treasure and 
      * decreases their actions by 1
      */
    @SuppressWarnings("static-access")
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

    /**
      * returns the tile type the current player is on
      */
	public TypeEnums getCurrentTileType() {
        Point location = currentPlayer.getPawnPos();
        return theBoard.getTileType(location);
    }

    /**
      * returns the point that a tile with the given
      * name is located
      */
	public Point getTilePos(TilesEnums t1) {
		return theBoard.getTilePos(t1);
	}

    /**
      * checks if a given player can swim if the tile
      * sinks
      * If there if nowhere they can swim to, update 
      * the observer with the lose condition
      */
	public boolean canPlayerSwim(Player player) {
        if (!player.getPawn().canSwim()){
            notifyUpdate(loser,5);
            return false;
        }
        else{
            return true;
        }
	}

    /**
      * return a list of valid tiles that are not
      * sea or sunked tiles
      */
	public List<Point> getValidTiles() {
        return theBoard.getValidTiles();
    }

    /**
      * return a list of all tiles that can be
      * shored up using a sandbag card
      */
	public List<Point> getSandbagsTiles() {
		return theBoard.getSandbagsTiles();
	}
	
	/** Singleton destroyer for unit testing
     */
	public void destroyMe() {
		theGameModel=null;
	}
}
