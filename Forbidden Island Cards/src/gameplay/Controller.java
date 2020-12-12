package gameplay;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import gameplay.GameInputs;
import player.Player;
import cards.DiscardPile;
import cards.TreasureCard;
import enums.TreasureCardEnums;

public class Controller implements Observer{

    private GameState theGameState;
    private GameOutputs theOutputs;
    private GameInputs theInputs;

    private static Controller theController = null;
    
    private Controller() {
        theGameState = GameState.getInstance();
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
        return theGameState.getPlayerName(-1);
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
            theOutputs.noActionsLeftMessage();
        }
        else{
            theGameState.movePlayer();
        }
    }

    // public DiscardPile getDiscardPile(boolean treasure){
    //     return theGameState.returnDiscard(treasure);
    // }

	public void shoreUp() {
	}

	public void lookDiscarded() {
        boolean FlorTr = theInputs.floodOrTreasure();
        String pile = theGameState.showDiscard(FlorTr);
        theOutputs.printPile(FlorTr, pile);
	}

	public void lookAtHands() {
        for(int i=0;i<theGameState.getNumPlayers();i++){
            String name = theGameState.getPlayerName(i);
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
            }
        }
        lookAtHands();
        theOutputs.whoToTrade();
        Player p1 = choosePlayer(traders);
        theOutputs.cardChoice(true);
		boolean validSelection = false;
		while(!validSelection){
			int cardnum = p1.chooseFromHand(p1, true);
			validSelection = p1.giveTreasureCard(playernum, cardnum, inputScanner);
		}
		actionsLeft--;
    }
    
    public int chooseFromHand(Player p1, boolean ineligible){
        if(p1==null){
            p1 = theGameState.getCurrentPlayer();
        }
        int userIn = 0;
        List hands = theGameState.getPlayerHand(p1);
		for (int i = 0; i < hands.size(); i++) {
			if (!(ineligible && !(hands.get(i) instanceof TreasureCard))){
				System.out.println("[" + i + "]: " + showHand().get(i).getName());
			}
		}
		boolean validIn = false;
		while (!validIn) {
			String userString = inputScanner.nextLine();
			try {
				userIn = Integer.parseInt(userString);
			} catch (NumberFormatException e) {
				continue;
			}
			if ((userIn >= 0) && (userIn <= showHand().size()-1)) {
				validIn = true;
			}
			else{
				System.out.println("Invalid Input");
			}
		}
		return userIn;
	}
 

	public Player choosePlayer(List<Integer> eligible){
        theOutputs.choosePl();
        if(eligible==null){
            eligible = theGameState.getAllPlayerNums(-1);
        }
        int size = theGameState.getTeamSize();
        for(int i=0;i<size;i++){
            if (eligible.contains(i)){
                theOutputs.showPlayer(i, theGameState.getPlayer(i).getName());
            }
        }
        int userIn = theInputs.playerChoice(theGameState.getTeamSize(), eligible);
		return theGameState.getPlayer(userIn);
    }

    public void useHelicopterLift(Player p1) {
        if(p1==null){
            p1 = theGameState.getCurrentPlayer();
        }
        if(!theGameState.checkHasCard(true)){
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

	public void useSandbags() {
        if(!theGameState.checkHasCard("Sandbags")){
            theOutputs.noSandbags();
        }
        else{
            theGameState.useSandbags();
        }
	}

	public boolean keepGoingHeli() {
        theOutputs.heliAnyoneElse();
        return theInputs.getYesOrNo("No","Yes");
	}

}
