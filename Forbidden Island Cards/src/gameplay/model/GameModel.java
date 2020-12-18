package gameplay.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import board.Board;
import cards.*;
import enums.TilesEnums;
import enums.TreasureCardEnums;
import enums.TypeEnums;
import player.*;
import gameplay.control.LoseObserver;
import gameplay.control.Observer;
import gameplay.control.WinObserver;
import pawns.Messenger;
import board.Board;

public class GameModel implements Subject {
    // private Controller theController = Controller.getInstance();
    private Team theTeam;
    private Player currentPlayer;
    private int actionsLeft;
    private boolean turnOver;
    private WaterMeter theWaterMeter;
    private Board theBoard;
    private TreasureDiscardPile theTreasureDiscardPile;
    private FloodDiscardPile theFloodDiscardPile;
    private FloodDeck theFloodDeck;
    private TreasureDeck theTreasureDeck;
    private TreasureHandler theTreasureHandler;
    private Observer loser;
    private Observer winner;
    private static GameModel theGameModel = null;

    private GameModel() {
        theTeam = Team.getInstance();
        theBoard = Board.getInstance();
        theFloodDeck = FloodDeck.getInstance();
        theTreasureDiscardPile = TreasureDiscardPile.getInstance();
        theFloodDiscardPile = FloodDiscardPile.getInstance();
        theTreasureDeck = TreasureDeck.getInstance();
        theTreasureHandler = TreasureHandler.getInstance();
        currentPlayer = theTeam.getPlayer(theTeam.getNumPlayers() - 1);
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

    public int getNumPlayers() {
        return theTeam.getAllPlayers().size();
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

    public void setActionsLeft() {
        actionsLeft = 3;
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

    public Deck returnDeck(boolean treasure) {
        if (treasure) {
            return theTreasureDeck;
        } else {
            return theFloodDeck;
        }
    }

    public String showDiscard(boolean Treasure) {
        if (Treasure) {
            return theTreasureDiscardPile.returnPrintedPile();
        } else {
            return theFloodDiscardPile.returnPrintedPile();
        }
    }

    public void movePlayer() {
        if (!currentPlayer.canMove()) {
            return;
        } else {
            currentPlayer.getPawn().move();
            decreaseActions();
        }
    }

    public void decreaseActions() {
        actionsLeft--;
    }

    public void shoreUp() {
        if (!currentPlayer.canShoreUp()) {
            return;
        } else {
            currentPlayer.getPawn().shoreUp();
        }
    }

    public String getHandasString(int i) {
        if (i == -1) {
            return currentPlayer.getHand().getHandasString();
        }
        return theTeam.getPlayer(i).getHand().getHandasString();
    }

    // public boolean canTrade() {
    //     Hand hand = currentPlayer.getHand();
    //     if (!hand.canTrade()) {
    //         return false;
    //     }
    //     if (checkPlayerPostions()) {
    //         return false;
    //     }
    //     return true;
    // }

    // private boolean checkPlayerPostions() {
    //     return false;
    // }

    public boolean checkHasCard(Player p1, boolean cardName) {
        if (cardName == false) {
            return p1.checkHasCard(TreasureCardEnums.SANDBAGS);
        } else if (cardName) {
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

    public List<Integer> getTradePartners() {
        List<Integer> allPlayers = theTeam.getAllPlayerNums(currentPlayer.getNum());
      //  System.out.println(traders);

        if(!(currentPlayer.getPawn() instanceof Messenger)){
            Iterator<Integer> i = allPlayers.iterator();
            while (i.hasNext()){
                int x = i.next();
                if (getPlayer(x).getPawnPos() != currentPlayer.getPawnPos()){
                    i.remove();
                }
            }
        }
        return allPlayers;
    }

    public List getPlayerHand(Player p1) {
        return p1.showHand();
    }

    public boolean addCardfromPlayerA(Player PlayerB, int canum) {
        Hand playerHand = currentPlayer.getHand();
        TreasureDeckCard c1 = playerHand.getCards().get(canum);
        if (!(c1 instanceof TreasureCard)) {
            return false;
        } else {
            PlayerB.addCardtoHand(c1);
            currentPlayer.getHand().getCards().remove(canum);
            return true;
        }
    }

    public int getHandSize(Player play1) {
        return play1.handSize();
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

    public void useSandbags() {
    }

    public TreasureDeckCard dealTreasure() {
        TreasureDeckCard c1 = (TreasureDeckCard) theTreasureDeck.dealCard();
        if (c1 instanceof WaterRiseCard) {
            WaterMeter.cardDrawn();
            int waterLevel = WaterMeter.getWaterlevel();
            if (waterLevel >= 5) {
                notifyUpdate(loser,6);
            }
        }
        return c1;
    }

    public void addToPile(TreasureDeckCard c1) {
        theTreasureDiscardPile.addToPile(c1);
    }

    public void addCardfromDeck(TreasureDeckCard c1) {
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
            if(theTreasureHandler.queryCaptured(TypeEnums.EARTH)){
                notifyUpdate(loser,0);
            }        
        }
        if((isSunk(TilesEnums.CAVE_OF_EMBERS)) && (isSunk(TilesEnums.CAVE_OF_SHADOWS))){
            if(theTreasureHandler.queryCaptured(TypeEnums.FIRE)){
                notifyUpdate(loser,1);
            }
        }
        if((isSunk(TilesEnums.CORAL_PALACE)) && (isSunk(TilesEnums.TIDAL_PALACE))){
            if(theTreasureHandler.queryCaptured(TypeEnums.WATER)){
                notifyUpdate(loser,2);
            }        
        }
        if((isSunk(TilesEnums.WHISPERING_GARDEN)) && (isSunk(TilesEnums.HOWLING_GARDEN))){
            if(theTreasureHandler.queryCaptured(TypeEnums.WIND)){
                notifyUpdate(loser,3);
            }        
        }
        // should check other ways in which the player can lose here.
        // If its sunk, dont bother adding card to discard pile, remove from play
        // If its just flooded then add it.
        if(!isSunk(t1)){
            theFloodDiscardPile.addToPile(card1);
        }
        return card1;
    }

    public Boolean isSunk(TilesEnums name) {
        return theBoard.isTileSunk(name);
    }

    @Override
    public void notifyUpdate(Observer o, int m) {
        o.update(m);
    }

    public List<TypeEnums> listCaptured(){
        return theTreasureHandler.captured();
    }

	public boolean canWin() {
        // check the positions of all players, if they're on fools landing
        // check if all 4 treasures have been captured if thats the case then set it so checkifwon returns true
        double x= Math.random();
        if (!theTreasureHandler.allCaptured()) {
            return false;
        }

        // check positions of players

        boolean checkifWon = (x>0.5);
        if(checkifWon){
            notifyUpdate(winner,7);
            notifyUpdate(loser,7);
            return true;
        }
        else{
            return false;
        }
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
    
}
