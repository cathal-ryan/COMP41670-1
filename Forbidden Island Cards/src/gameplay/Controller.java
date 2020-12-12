package gameplay;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import gameplay.GameInputs;
import player.Player;
import cards.DiscardPile;
import cards.HelicopterLift;
import cards.SandbagsCard;
import cards.TreasureCard;
import enums.TreasureCardEnums;

public class Controller implements Observer{

    private GameState theGameState;
    private GameOutputs theOutputs;
    private GameInputs theInputs;

    private static Controller theController = null;
    
    private Controller() {
        theGameState = GameState.getInstance();
        EndingGame theWin = new EndingGame(theGameState);
        theGameState.addObserver(theWin);
        theInputs = new GameInputs();
        theOutputs = new GameOutputs();
    }

    public static Controller getInstance() {
        if(theController == null) {
            theController = new Controller();
        }
        return theController;
    }

    public boolean isGameWon(){
        return false;
    }

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	public boolean isGameOver() {
		return false;
	}

	public void newTurn() {
        theGameState.setTurnOver(false);
        theGameState.setActionsLeft();
        theGameState.setNextPlayer();
	}

    public String returnPlayerName(){
        return theGameState.getPlayerNameFromIndex(-1);
    }

    public int getActionsLeft(){
        return theGameState.getActionsLeft();
    }

	public boolean getTurnOver() {
		return theGameState.getTurnOver();
    }
    
    public void setTurnOver(){
        theGameState.setTurnOver(true);
    }

    public void movement(){
        if(theGameState.getActionsLeft()<1){
            theOutputs.noActionsLeft();
        }
        else{
            theGameState.movePlayer();
        }
    }

	public void shoreUp() {
        if(theGameState.getActionsLeft()<1){
            theOutputs.noActionsLeft();
        }
        else{
            theGameState.movePlayer();
        }
	}

	public void lookDiscarded() {
        boolean FlorTr = theInputs.floodOrTreasure();
        String pile = theGameState.showDiscard(FlorTr);
        theOutputs.printPile(FlorTr, pile);
	}

	public void lookAtHands() {
        for(int i=0;i<theGameState.getNumPlayers();i++){
            String name = theGameState.getPlayerNameFromIndex(i);
            String hand = theGameState.getHandasString(i);
            theOutputs.printHand(name, hand);
        }
    }

	public void giveCard() {
        List traders = new ArrayList<>();
        if(theGameState.getActionsLeft()<1){
            theOutputs.noActionsLeft();
            return;
        }
        else{
            if(theGameState.canTrade()){
                traders = theGameState.getTradePartners();
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
			validSelection = giveTreasureCard(playerB, cardnum);
		}
		theGameState.decreaseActions();
    }
    
    public int chooseFromHand(Player playerA, boolean ineligible){
        if(playerA==null){
            playerA = theGameState.getCurrentPlayer();
        }
        List hands = theGameState.getPlayerHand(playerA);
		for (int i = 0; i < hands.size(); i++) {
			if (!(ineligible && !(hands.get(i) instanceof TreasureCard))){
                theOutputs.showOption(i,hands.get(i).toString());
			}
		}
        return theInputs.handChoice(hands.size());
    }
    
    public boolean giveTreasureCard(Player playerB, int canum){
        if (!theGameState.addCardfromPlayerA(playerB,canum)){
            return false;
        }
        int handSizeB = theGameState.getHandSize(playerB);
        while(handSizeB > 5){
			discardTreasure(playerB);
		}
		return true;
	}

    public void discardTreasure(Player player){
        boolean validIn = false;
        String name = theGameState.getPlayerName(player);
        List hand = theGameState.getPlayerHand(player);
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
        theGameState.removeCardByIndex(player, userIn);
    }
    
	public Player choosePlayer(List<Integer> eligible){
        theOutputs.choosePl();
        if(eligible==null){
            eligible = theGameState.getAllPlayerNums(-1);
        }
        int size = theGameState.getTeamSize();
        for(int i=0;i<size;i++){
            if (eligible.contains(i)){
                theOutputs.showOption(i, theGameState.getPlayer(i).getName());
            }
        }
        int userIn = theInputs.playerChoice(theGameState.getTeamSize(), eligible);
		return theGameState.getPlayer(userIn);
    }

    public void useHelicopterLift(Player p1) {
        if(p1==null){
            p1 = theGameState.getCurrentPlayer();
        }
        if(!theGameState.checkHasCard(p1, true)){
            theOutputs.noHeli();
            return;
        }
        theGameState.removeCard(p1, TreasureCardEnums.HELICOPTER_LIFT);
        int k = theInputs.heliWhere();
        theOutputs.whoWillFly();
        List <Integer> availforMove = theGameState.getAllPlayerNums(-1);
        boolean keepMoving = true;
        do{
            Player playerForHeliMove = choosePlayer(availforMove);
            theGameState.heliMovePlayer(playerForHeliMove, k);
            availforMove.remove(new Integer(playerForHeliMove.getNum()));
        }
        while(!availforMove.isEmpty() && keepGoingHeli());
	}

	public void useSandbags(Player p1) {
        if(p1==null){
            p1 = theGameState.getCurrentPlayer();
        }
        if(!theGameState.checkHasCard(p1, false)){
            theOutputs.noSandbags();
            return;
        }
        theGameState.removeCard(p1, TreasureCardEnums.SANDBAGS);
        theGameState.useSandbags();
        
	}

	public boolean keepGoingHeli() {
        theOutputs.heliAnyoneElse();
        return theInputs.getYesOrNo("No","Yes");
    }

    public void enquirePlayers(boolean asked){
        List<Integer> eligible = theGameState.getPlayerswithSpecials();
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
        if(theGameState.checkHasCard(player1, true) && theGameState.checkHasCard(player1, false)){
            theOutputs.heliOrSand();
            if(theInputs.getYesOrNo("Sandbags", "Helicopter Lift")){
                useHelicopterLift(player1);
            }
            else{
                useSandbags(player1);
            }
        }
        else if(theGameState.checkHasCard(player1, true)){
            useHelicopterLift(player1);
        }
        else if(theGameState.checkHasCard(player1, false)){
            useSandbags(player1);
        }
    }

}
