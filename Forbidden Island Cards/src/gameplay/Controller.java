package gameplay;

import java.util.Observable;
import java.util.Observer;
import gameplay.GameInputs;
import cards.DiscardPile;

public class Controller implements Observer{

    private GameState theGameState;
    private Messenger theMessenger;
    private GameInputs theInputs;

    private static Controller theController = null;
    
    private Controller() {
        theGameState = GameState.getInstance();
        theInputs = new GameInputs();
        theMessenger = new Messenger();
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
            theMessenger.noActionsLeftMessage();
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
        theMessenger.printPile(FlorTr, pile);
	}

	public void lookAtHands() {
        for(int i=0;i<theGameState.getNumPlayers();i++){
            String name = theGameState.getPlayerName(i);
            String hand = theGameState.getHandasString(i);
            theMessenger.printHand(name, hand);
        }
    }

	public void giveCard() {
        if(theGameState.getActionsLeft()<1){
            theMessenger.noActionsLeft();
        }
        else{
            if(theGameState.canTrade()){
                theGameState.Trade();
            }
            else{
                theMessenger.cantTrade();
            }
        }

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
}
