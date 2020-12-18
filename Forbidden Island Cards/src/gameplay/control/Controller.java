package gameplay.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import gameplay.model.GameModel;
import gameplay.view.GameInputs;
import gameplay.view.GameOutputs;
import player.Player;
import cards.Card;
import cards.HelicopterLift;
import cards.SandbagsCard;
import cards.TreasureCard;
import cards.TreasureDeckCard;
import enums.TreasureCardEnums;
import enums.TilesEnums;

public class Controller{

    private GameModel theGameModel;
    private GameOutputs theOutputs;
    private GameInputs theInputs;
    private LoseObserver losing = new LoseObserver();
    private WinObserver winning = new WinObserver();

    private static Controller theController = null;
    
    private Controller() {
        theGameModel = GameModel.getInstance();
        theInputs = new GameInputs();
        theOutputs = new GameOutputs();
        this.losing = new LoseObserver();
        this.winning = new WinObserver();
    }

    public static Controller getInstance() {
        if(theController == null) {
            theController = new Controller();
        }
        return theController;
    }

	public boolean isGameOver() {
		return losing.isGameOver();
	}

	public void newTurn() {
        theGameModel.setTurnOver(false);
        theGameModel.setActionsLeft();
        theGameModel.setNextPlayer();
        theOutputs.printBoard();
	}

    public String returnPlayerName(){
        return theGameModel.getPlayerNameFromIndex(-1);
    }

    public int getActionsLeft(){
        return theGameModel.getActionsLeft();
    }

	public boolean getTurnOver() {
		return theGameModel.getTurnOver();
    }
    
    public void setTurnOver(){
        theGameModel.setTurnOver(true);
    }

    public void movement(){
        if(theGameModel.getActionsLeft()<1){
            theOutputs.noActionsLeft();
        }
        else{
            theGameModel.movePlayer();
        }
    }

	public void shoreUp() {
        if(theGameModel.getActionsLeft()<1){
            theOutputs.noActionsLeft();
        }
        else{
            theGameModel.movePlayer();
        }
	}

	public void lookDiscarded() {
        boolean FlorTr = theInputs.floodOrTreasure();
        String pile = theGameModel.showDiscard(FlorTr);
        theOutputs.printPile(FlorTr, pile);
	}

	public void lookAtHands() {
        for(int i=0;i<theGameModel.getNumPlayers();i++){
            String name = theGameModel.getPlayerNameFromIndex(i);
            String hand = theGameModel.getHandasString(i);
            theOutputs.printHand(name, hand);
        }
    }

    public String showAHand(){
        return theGameModel.getHandasString(-1);
    }

	public void giveCard() {
        List<Integer> traders = new ArrayList<Integer>();
        if(theGameModel.getActionsLeft()<1){
            theOutputs.noActionsLeft();
            return;
        }
        else{
            if(theGameModel.canTrade()){
                traders = theGameModel.getTradePartners();
            }
            else{
                theOutputs.cantTrade();
                return;
            }
        }
        lookAtHands();
        theOutputs.whoToTrade();
        Player playerB = choosePlayer(traders);
        theOutputs.cardChoice(true);
		boolean validSelection = false;
		while(!validSelection){
			int cardnum = chooseFromHand(null, true);
			validSelection = transferTreasure(playerB, cardnum);
		}
		theGameModel.decreaseActions();
    }
    
    public int chooseFromHand(Player playerA, boolean ineligible){
        if(playerA==null){
            playerA = theGameModel.getCurrentPlayer();
        }
        List<Card> hands = theGameModel.getPlayerHand(playerA);
		for (int i = 0; i < hands.size(); i++) {
			if (!(ineligible && !(hands.get(i) instanceof TreasureCard))){
                Enum name = hands.get(i).getName();
                theOutputs.showOption(i,name.toString());
            }
		}
        return theInputs.handChoice(hands.size());
    }
    
    public boolean transferTreasure(Player playerB, int canum){
        if (!theGameModel.addCardfromPlayerA(playerB,canum)){
            return false;
        }
        int handSizeB = getHandSize(playerB);
        while(handSizeB > 5){
			discardTreasure(playerB);
		}
		return true;
	}

    public void discardTreasure(Player player){
        if(player==null){
            player = theGameModel.getCurrentPlayer();
        }
        boolean validIn = false;
        String name = theGameModel.getPlayerName(player);
        List hand = theGameModel.getPlayerHand(player);
        theOutputs.handTooBig(name);
        int userIn = chooseFromHand(player,false);
        if(!(hand.get(userIn) instanceof TreasureCard)){
            theOutputs.useIt();
            if(theInputs.getYesOrNo("No", "Yes")){
                if((hand.get(userIn) instanceof SandbagsCard)){
                    useSandbags(player);
                    return;
                }
                if((hand.get(userIn) instanceof HelicopterLift)){
                    useHelicopterLift(player);
                    return;
                }
            }
        }
        theGameModel.removeCardByIndex(player, userIn);
    }
    
	public Player choosePlayer(List<Integer> eligible){
        theOutputs.choosePl();
        if(eligible==null){
            eligible = theGameModel.getAllPlayerNums(-1);
        }
        int size = theGameModel.getTeamSize();
        for(int i=0;i<size;i++){
            if (eligible.contains(i)){
                theOutputs.showOption(i, theGameModel.getPlayer(i).getName());
            }
        }
        int userIn = theInputs.playerChoice(theGameModel.getTeamSize(), eligible);
		return theGameModel.getPlayer(userIn);
    }

    public void useHelicopterLift(Player p1) {
        if(p1==null){
            p1 = theGameModel.getCurrentPlayer();
        }
        if(!theGameModel.checkHasCard(p1, true)){
            theOutputs.noHeli();
            return;
        }
        theGameModel.removeCard(p1, TreasureCardEnums.HELICOPTER_LIFT);
        if(theGameModel.canWin()){
            return;
        }
        int k = theInputs.heliWhere();
        theOutputs.whoWillFly();
        List <Integer> availforMove = theGameModel.getAllPlayerNums(-1);
        boolean keepMoving = true;
        do{
            Player playerForHeliMove = choosePlayer(availforMove);
            theGameModel.heliMovePlayer(playerForHeliMove, k);
            availforMove.remove(new Integer(playerForHeliMove.getNum()));
        }
        while(!availforMove.isEmpty() && keepGoingHeli());
    }

    public boolean keepGoingHeli() {
        theOutputs.heliAnyoneElse();
        return theInputs.getYesOrNo("No","Yes");
    }

	public void useSandbags(Player p1) {
        if(p1==null){
            p1 = theGameModel.getCurrentPlayer();
        }
        if(!theGameModel.checkHasCard(p1, false)){
            theOutputs.noSandbags();
            return;
        }
        theGameModel.removeCard(p1, TreasureCardEnums.SANDBAGS);
        theGameModel.useSandbags();
	}


    public void enquirePlayers(boolean asked){
        List<Integer> eligible = theGameModel.getPlayerswithSpecials();
        if(eligible.isEmpty()){
            if(asked){
                theOutputs.noSpecials();
            }
            return;
        }
        theOutputs.playSpecials();
        if(!theInputs.getYesOrNo("No","Yes")){
            return;
        }
        lookAtHands();
        theOutputs.whoForSpecial();
        Player player1 = choosePlayer(eligible);
        if(theGameModel.checkHasCard(player1, true) && theGameModel.checkHasCard(player1, false)){
            theOutputs.heliOrSand();
            if(theInputs.getYesOrNo("Sandbags", "Helicopter Lift")){
                useHelicopterLift(player1);
            }
            else{
                useSandbags(player1);
            }
        }
        else if(theGameModel.checkHasCard(player1, true)){
            useHelicopterLift(player1);
        }
        else if(theGameModel.checkHasCard(player1, false)){
            useSandbags(player1);
        }
    }

	public TreasureDeckCard getTreasureCard() {
        return theGameModel.dealTreasure();
	}

	public void addToPile(TreasureDeckCard c1) {
        theGameModel.addToPile(c1);
	}

	public void addCardtoHand(TreasureDeckCard c1) {
        theGameModel.addCardfromDeck(c1);
	}

	public int getHandSize(Player play1) {
        if(play1==null){
            play1 = theGameModel.getCurrentPlayer();
        }
		return theGameModel.getHandSize(play1);
    }
    
    public int getWaterLevel(){
        return theGameModel.getWaterLevel();
    }

	public void dealFloodCard() {
        Card card1 = theGameModel.dealFlood();
        TilesEnums t1 = (TilesEnums) card1.getName();
        if(theGameModel.isSunk(t1))
            theOutputs.sunkTile(card1.getName().toString());
        else
            theOutputs.floodedTile(card1.getName().toString());
	}

	public void gameOverPrompt() {
        if(losing.isGameLost()){
            String gameLoss = losing.getLossCondition();
            theOutputs.gameOver(gameLoss);
        }
        if(winning.isGameWon()){
            String gameWon = winning.getWinCondition();
            theOutputs.aWinnerIsYou(gameWon);
        }
	}
}
