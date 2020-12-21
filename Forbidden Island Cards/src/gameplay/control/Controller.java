package gameplay.control;

import java.util.ArrayList;
import java.util.List;
import gameplay.model.GameModel;
import gameplay.view.*;
import player.Player;
import cards.Card;
import cards.TreasureCard;
import enums.*;
import java.awt.Point;

/**
 * Singleton class representing the main game Controller using MVC Pattern.
 * Our implementation splits the controller into several classes to allow for the
 * sequencing to be split. This Controller class handles the main logic involved in
 * the sequences seen in the other controller classes. Controller has access to the model
 * which it can query for information on the current state of the game. The controller
 * also has methods to update the state of the game, and to interact with the view to
 * receive inputs from the user.
 * Has access to the observer to see whether game is won or lost, and can therefore inform
 * the sequential logic of the other classes enacting the controller.
 * 
 * @author Cathal Ryan and Conor Kneafsey
 *
 */
public class Controller{

    private GameModel           theGameModel; //State of the Game
    private GameOutputs         theOutputs;   // The view. Outputs and Inputs
    private GameInputs          theInputs;
    private LoseObserver        losing;       // Win and lose observers
    private WinObserver         winning;

    private static Controller   theController;
    
    /**
     * The private Controller constructor
     */
    private Controller() {
        theGameModel = GameModel.getInstance();
        theInputs = new GameInputs();
        theOutputs = new GameOutputs();
        this.losing = new LoseObserver();
        this.winning = new WinObserver();
    }
    /**
     * getInstance singleton method gets single instance
     * of the Controller.
     * @return Singleton Controller object
     */
    public static Controller getInstance() {
        if(theController == null) {
            theController = new Controller();
        }
        return theController;
    }

    //  ///////////////////////////////////
    //          GAME TERMINATION.
    //  ///////////////////////////////////

    /**Calls the observer to see if game has ended
     * @return boolean if game is over
     */
	public boolean isGameOver() {
		return losing.isGameOver();
    }
    
    /**Provides prompts to the user if the game has ended
     */
    public void gameOverPrompt() {
        // See whether we've won our lost, find the win/loss conditions and tell user
        if(losing.isGameLost()){
            String gameLoss = losing.getLossCondition();
            theOutputs.gameOver(gameLoss);
        }
        if(winning.isGameWon()){
            String gameWon = winning.getWinCondition();
            theOutputs.aWinnerIsYou(gameWon);
        }
	}

    //  ///////////////////////////////////
    //  SIMPLE INTERFACING WITH THE MODEL.
    //  ///////////////////////////////////

    /**Gets a player's name
     * @param i the index of player we want name of
     * @return String name of player
     */
    public String returnPlayerName(int i){
        return theGameModel.getPlayerNameFromIndex(i);
    }

    /**Gets a player pawn's character
     * @param i the index of player we want character of
     * @return String pawn character of player
     */
    public String returnPawnChar(int i){
        return theGameModel.getPlayer(i).getChar();
    }

    /**Gets a player pawn's character
     * @param i the index of player we want character of
     * @return String pawn character of player
     */
    public int getActionsLeft(){
        return theGameModel.getActionsLeft();
    }
    /**
     * @return Whether turn is over or not
     */
	public boolean getTurnOver() {
		return theGameModel.getTurnOver();
    }

    /**Gets current player's hand
     * @return String of player's hand
     */
    public String showAHand(){
        return theGameModel.getHandasString(-1);
    }

    /**Deal a treasure card from deck
     * @return TreasureCard from deck
     */
	public TreasureCard getTreasureCard() {
        return theGameModel.dealTreasure();
    }

    /**Returns the size of a player's hand
     * @param play1 The player we want the hand size of
     * @return Integer size of their hand
     */
    public int getHandSize(Player play1) {
        if(play1==null){
            play1 = theGameModel.getCurrentPlayer();
        }
		return theGameModel.getHandSize(play1);
    }

    /**Returns the water level
     * @return Integer water level
     */
    public int getWaterLevel(){
        return theGameModel.getWaterLevel();
    }

    /**Tell model we're done turn
     */
    public void setTurnOver(){
        theGameModel.setTurnOver(true);
    }

    /**Add this card to treasure discard
     * @param c1 card to be added to discard pile
     */
	public void addToPile(TreasureCard c1) {
        theGameModel.addToPile(c1);
    }
    
    /**Add this card to current player's hand
     * @param c1 card to be added to hand
     */
	public void addCardtoHand(TreasureCard c1) {
        theGameModel.addCardfromDeck(c1);
	}
    
    //  ///////////////////////////////////
    //      SHOWING DATA FROM THE MODEL.
    //  ///////////////////////////////////

    /**Prints full game state, shows the board, water level, treasures captured, the hands, and discard piles
    */
    private void showGameState(){
        lookAtBoard();
        lookAtHands();
        theOutputs.printPile(false, theGameModel.showDiscard(false));
        theOutputs.printPile(true, theGameModel.showDiscard(true));
    }

    /**Prints the board, water level and treasures captured because it's important info
    */
    public void lookAtBoard() {
        theOutputs.printBoard();
        theOutputs.displayWater(getWaterLevel());
        List<TypeEnums> treasures = theGameModel.listCaptured();
        theOutputs.showCaptured(treasures);
    }

    /**Prints all Player's hands
    */
    public void lookAtHands() {
        for(int i=0;i<theGameModel.getNumPlayers();i++){
            // Get their name, pawn and hand and iterate through everyone
            String name = returnPlayerName(i);
            String pawn = theGameModel.getPlayer(i).getChar();
            String hand = theGameModel.getHandasString(i);
            theOutputs.printHand(name,pawn,hand);
        }
    }

    /**Looks at either discard pile
    */
	public void lookDiscarded() {
        boolean FlorTr = theInputs.floodOrTreasure(); //Ask user if they want to see flood or treasure pile
        String pile = theGameModel.showDiscard(FlorTr); // Get that pile from model
        theOutputs.printPile(FlorTr, pile);
	}
    
    //  ///////////////////////////////////
    //             GAME LOGIC
    //  ///////////////////////////////////

    /**Tell the model we are starting a new turn
    */
	public void newTurn() {
        theGameModel.setTurnOver(false); // Set turn over to false, turn is on
        theGameModel.setActionsLeft(); // Reset actions
        theGameModel.setNextPlayer(); //Set new player
        theOutputs.printBoard(); 
        lookAtHands(); 
	}

    /**Simple player movement function, can move up/down/left/right
    */
    public void movement(){
        if(theGameModel.getActionsLeft()<1){ // If actions<1, tell player
            theOutputs.noActionsLeft();
        }
        else{
            theOutputs.whereMove(); // Look for where to move to
            char dir = theInputs.moveDir(); 
            if(!theGameModel.movePlayer(dir)) {// tell the model to move player in specified direction
                theOutputs.printBoard();
                theOutputs.cantMove();
            }
            else
                theOutputs.printBoard();
        }
    }

    /**Allows players to shore up adjacent tiles
    */
	public void shoreUp() {
        if(theGameModel.getActionsLeft()<1){
            theOutputs.noActionsLeft();
        }
        else{
            theOutputs.whereShoreUp();
            boolean validSelection = false;
            if(theGameModel.isEngineer()){
                engShoreUp();
            }
            else {
                while(!validSelection){ // find a valid point for the user to shore up by prompting them
                    Point p = theInputs.selectTile();
                    validSelection = theGameModel.shoreUp(p);
                    if(!validSelection) {
                        theOutputs.cantShoreUp();
                        theOutputs.shoreAgain();
                        validSelection = theInputs.boolYN("Yes", "No");
                    }
                    theOutputs.printBoard();
                }
            }
        }
	}

    public void engShoreUp() {
        int shored = 2;
        boolean validSelection = false;
        while(!validSelection && shored>0) {
            Point p = theInputs.selectTile();
            validSelection = theGameModel.shoreUp(p);
            shored--;
            if(!validSelection) {
                shored++;
                theOutputs.cantShoreUp();
                theOutputs.shoreAgain();
                validSelection = theInputs.boolYN("Yes", "No");
            }
            else if(shored>0){
                theOutputs.shoreAgain();
                validSelection = theInputs.boolYN("Yes", "No");
            }
            theOutputs.printBoard();
        }
        if(shored == 0) {
            theGameModel.increaseActions();
        }
    }

    /**Allows players to give cards to players adjacent to them
    */
	public void giveCard() {
        List<Integer> traders = new ArrayList<Integer>();
        if(theGameModel.getActionsLeft()<1){ // If no actions left, then tell player and return
            theOutputs.noActionsLeft();
            return;
        }
        else{
            // Look to see if the player has cards available for trading
            if(!theGameModel.hasCardsforTrade()){
                theOutputs.cantTrade();
                return;
            }
            // Find viable players to give cards to, if there's none then return and tell player
            traders = theGameModel.getTradePartners();
            if(traders.isEmpty()){
                theOutputs.cantTrade();
                return;
            }
        }
        //Show hands and prompt user for selection
        lookAtHands();
        theOutputs.whoToTrade();
        Player playerB = choosePlayer(traders); // Select a player based on eligible traders
        theOutputs.cardChoice(true);
        boolean validSelection = false;
        // Continue until a valid card to trade selection has been made
		while(!validSelection){
			int cardnum = chooseFromHand(null, true); // Call function to find hand selection
			validSelection = transferTreasure(playerB, cardnum); // Call function to transfer the treasure card
		}
		theGameModel.decreaseActions(); // Decrement the player's actions
    }
    
    /**Prompts players for a choice
     * @param playerA player's hand to be chosen from
     * @param ineligible boolean setting mode, if true then helicopter lift and sandbags can't be selected
     * @return int index of what card from hand is chosen
     */ 
    public int chooseFromHand(Player playerA, boolean ineligible){
        // if no player specified, assume current player
        if(playerA==null){
            playerA = theGameModel.getCurrentPlayer();
        }
        List<TreasureCard> hands = theGameModel.getPlayerHand(playerA); // get hand

        // Show all the cards, if ineligble is true then only show non-special cards
		for (int i = 0; i < hands.size(); i++) {
            TreasureCardEnums name = (TreasureCardEnums) hands.get(i).getName();
			if (!(ineligible && (name==TreasureCardEnums.SANDBAGS || name==TreasureCardEnums.HELICOPTER_LIFT))){
                theOutputs.showOption(i,name.toString(),"");
            }
		}
        return theInputs.handChoice(hands.size());
    }
    
    /**Transfers treasure card to a playerB from the current player
     * @param playerB player to be given a card
     * @param canum Card number, representing the index of the card in current player's hand to be given
     * @return boolean indicating whether giving card has been succesful.
     */ 
    public boolean transferTreasure(Player playerB, int canum){
        //Tell model to add card at index canum from playerA to playerB, as long as its not a heli lift or sandbags
        if (!theGameModel.addCardfromPlayerA(playerB,canum)){
            return false;
        }
        // If hand size has gotten too big, then prompt for discard
        int handSizeB = getHandSize(playerB);
        while(handSizeB > 5 && !isGameOver()){
            discardTreasure(playerB);
            handSizeB = getHandSize(playerB);
		}
		return true; // All successful
    }
    
    /**Allows player to discard cards if their hand has become too large
     * @param player player to discard a card
     */ 
    public void discardTreasure(Player player){
        if(player==null){ // Assume current player if no input player
            player = theGameModel.getCurrentPlayer();
        }
        String name = theGameModel.getPlayerName(player); // Get player's name and their hand
        List<TreasureCard> hand = theGameModel.getPlayerHand(player);
        theOutputs.handTooBig(name);
        if(theInputs.boolYN("No","Yes")){ //Ask if they want to see current state of game before having to discard
            showGameState();
        }
        theOutputs.nowSelectCard();
        int userIn = chooseFromHand(player,false); // get a choice from their hand
        TreasureCardEnums cardname = (TreasureCardEnums) hand.get(userIn).getName();
        // If it's helicopter lift or sandbags, ask user if they want to use the card
        if((cardname==TreasureCardEnums.SANDBAGS || cardname == TreasureCardEnums.HELICOPTER_LIFT)){
            if(chooseOrShowState(2,"No", "Yes")){
                if(cardname==TreasureCardEnums.SANDBAGS){
                    useSandbags(player,true);
                    return;
                }
                if(cardname == TreasureCardEnums.HELICOPTER_LIFT){
                    useHelicopterLift(player);
                    return;
                }
            }
        }
        theGameModel.removeCardByIndex(player, userIn); // get model to get rid of card
    }
    
    /**Selection of a player from a list of eligible candidates
     * @param eligible List of player numbers who can be selected
     * @return Player who is chosen from list
     */ 
	public Player choosePlayer(List<Integer> eligible){
        theOutputs.choosePl();
        if(eligible==null){ // if no one in eligible, allow everyone to be picked
            eligible = theGameModel.getAllPlayerNums(-1);
        }
        int size = theGameModel.getNumPlayers();
        for(int i=0;i<size;i++){ // Show all players
            if (eligible.contains(i)){
                theOutputs.showOption(i,returnPlayerName(i),returnPawnChar(i));
            }
        }
        int userIn = theInputs.playerChoice(theGameModel.getNumPlayers(), eligible); // get the player choice and return this player
		return theGameModel.getPlayer(userIn);
    }

    /**Lets player fly to a location on the board with the use of Helicopter Lift card
     * @param Player Player who has heli lift card
     * @return Player who is chosen from list
     */ 
    public void useHelicopterLift(Player p1) {
        if(p1==null){ //if null, assume current
            p1 = theGameModel.getCurrentPlayer();
        }
        if(!theGameModel.checkHasCard(p1, true)){ // check they have a helicopter lift
            theOutputs.noHeli();
            return;
        }
        theGameModel.removeCard(p1, TreasureCardEnums.HELICOPTER_LIFT);
        if(theGameModel.canWin()){ //check if player can win right now, if so return and game is over
            return;
        }
        theOutputs.heliWhere();
        lookAtBoard();
        List<Point> heliOptions = theGameModel.getValidTiles(); // find where the team can go
        boolean validSelection=false;
        Point p = new Point(0,0);
        while(!validSelection){ // Get a selection of a tile to go to
            p = theInputs.selectTile();
            if(heliOptions.contains(p)){
                validSelection=true;
            }
            else{
                theOutputs.noMove();
            }
        }
        theOutputs.whoWillFly();
        List <Integer> availforMove = theGameModel.getAllPlayerNums(-1); // all players can fly
        boolean keepMoving = true;
        do{
            Player playerForHeliMove = choosePlayer(availforMove);
            theGameModel.heliMovePlayer(playerForHeliMove, p);
            availforMove.remove(new Integer(playerForHeliMove.getNum())); 
        }
        while(!availforMove.isEmpty() && chooseOrShowState(3, "No","Yes"));  
        // Keep asking player if they want to fly anyone else there, if theres's still players who can fly
        lookAtBoard();
    }

    /**Lets player shore up a location if they have sandbags card
     * @param Player Player who has sandbags card
     * @param discarding Boolean indicating if they're discarding this card or not
     * @return Player who is chosen from list
     */ 
	public void useSandbags(Player p1, boolean discarding) {
        if(p1==null){ // assume current player if null
            p1 = theGameModel.getCurrentPlayer();
        }
        if(!theGameModel.checkHasCard(p1, false)){ // check they have sandbags
            theOutputs.noSandbags();
            return;
        }
        List<Point> sandOptions = theGameModel.getSandbagsTiles(); // find where can be sandbags
        if(sandOptions.isEmpty()){ //if there's nowhere, tell the player and call model to get rid of card if they were discarding it
            theOutputs.nowhereToShore();
            if(discarding){
                theGameModel.removeCard(p1, TreasureCardEnums.SANDBAGS);
            }
            return;
        }
        theGameModel.removeCard(p1, TreasureCardEnums.SANDBAGS);
        theOutputs.sandbagsWhere();
        lookAtBoard();
        boolean validSelection=false;
        Point p = new Point(0,0);
        while(!validSelection){ // find a valid point for the user to shore up by prompting them
            p = theInputs.selectTile();
            if(sandOptions.contains(p)){
                validSelection=true;
            }
            else{
                theOutputs.cantSandbags();
            }
        }
        theGameModel.useSandbags(p); // call model to use sandbags here
        lookAtBoard();
    }
    
    /**Lets player choose between 3 options while potentially also showing the current state of the game
     * @param mode Specifies a mode of operation for asking the players
     * @param discarding Boolean indicating if they're discarding this card or not
     * @param discarding Boolean indicating if they're discarding this card or not
     * @return boolean choice between two options
     */ 
    private boolean chooseOrShowState(int mode, String n, String y){
        int selection=2;
        while(selection==2){ //Keep running loop while they havent made a choice between n and y
            switch (mode) {
                case 0:
                    theOutputs.playSpecials();
                    break;
                case 1:
                    theOutputs.heliOrSand();
                    break;
                case 2:
                    theOutputs.useIt();
                    break;
                case 3:
                    theOutputs.heliAnyoneElse();
                    break;
                default:
                    theOutputs.generalError();
            }
            selection =(theInputs.get3Choice(n, y, "Show the game state")); // Prompts a choice between 0,1,2 options
            if(selection==0){
                return false;
            }
            if(selection==1){
                return true;
            }
            else if (selection==2){ // Show game state again if 2
                showGameState();
            }
        }
        return false; //return false just in case
    }

    /**Sees if any of the players want to use a Helicopter Lift or a Sandbags card
     * @param asked Specifies if this method was called specifically by the current player
     * @return boolean indicating whether the user has specifically said that theyr don't want to use a special card.
     *         This is mainly used for drawing cards when saying no should immediately draw new card
     */ 
    public boolean enquirePlayers(boolean asked){
        while(true){
            List<Integer> eligible = theGameModel.getPlayerswithSpecials(); // Get all players who can play a sandbags or heli lift
            if(eligible.isEmpty()){ // if its empty, return but give a message if the user wanted this called
                if(asked){
                    theOutputs.noSpecials();
                }
                return true;
            }
            String n = "No, Draw card!";
            if (asked) // If the user asked for this, then we're not drawing cards so just say nope
                n="Nope!";
            if(!chooseOrShowState(0,n,"Yes, play special card!")){ // See if they want to play a special card
                    return false;
            }
            lookAtHands();
            theOutputs.whoForSpecial();
            Player player1 = choosePlayer(eligible); // Choose from eligible players
            if(theGameModel.checkHasCard(player1, true) && theGameModel.checkHasCard(player1, false)){
                // if they have both cards, ask them again which they want to use
                if(chooseOrShowState(1, "Sandbags", "Helicopter Lift")){
                    useHelicopterLift(player1);
                }
                else{
                    useSandbags(player1,false);
                }
            }
            else if(theGameModel.checkHasCard(player1, true)){
                useHelicopterLift(player1);
            }
            else if(theGameModel.checkHasCard(player1, false)){
                useSandbags(player1,false);
            }
        }
    }

    /**Deals a flood card
     */ 
	public void dealFloodCard() {
        Card card1 = theGameModel.dealFlood();
        TilesEnums t1 = (TilesEnums) card1.getName();
        if(theGameModel.isSunk(t1)){ // If we've sunk then need to implement logic
            theOutputs.sunkTile(card1.getName().toString());
            if (!isGameOver()) // Check for game over
                sunkenTile(t1);
        }
        else // Tell user flooded tile
            theOutputs.floodedTile(card1.getName().toString());
	}

    /**Deals with a tile being sunk
     * @param tile The sunken tile
     */ 
    private void sunkenTile(TilesEnums tile){
        for (int i=0;i<theGameModel.getNumPlayers();i++){ // Check each player and see if they are impacted by sinking
            Player player = theGameModel.getPlayer(i);
            // If tile sinking affects them, proceed
            if (theGameModel.getTilePos(tile).equals(player.getPawnPos())){
                if(!theGameModel.canPlayerSwim(player)){ // check if they can swim, if not, return because game over
                    return;
                }
                theOutputs.needToSwim(player.getName(),player.getChar(),player.getPlayerType());
                lookAtBoard();
                boolean canSwimHere = false;
                List<Point> swimmables = player.getPawn().getViableSwims(); // find all places player can swim to
                while(!canSwimHere){ // 
                    Point swim = theInputs.selectTile(); //get a tile selected
                    if(swimmables.contains(swim)){
                        theGameModel.heliMovePlayer(player, swim); // get them to safety
                        canSwimHere=true;
                        theOutputs.printBoard();
                    }
                    if(!canSwimHere){ // they cant swim here
                        theOutputs.noMove();
                    }
                }
            }
        }
    }

    /**Deals with capturing treasures
     */ 
	public void captureATreasure() {
        if(theGameModel.getActionsLeft()<1){ // If no actions remain then return
            theOutputs.noActionsLeft();
            return;
        }
        TypeEnums tile = theGameModel.getCurrentTileType(); // find where player currently is from
        int captureMode = theGameModel.capture(); // get the capture mode and call the capture function
        if(captureMode == 0){  // We've succesfully captured treasure
            theOutputs.treasureCaptured(tile);
        }
        else if (captureMode > 0 && captureMode <4){ // treasure not captured for particular reason, tell user
            theOutputs.cantCapture(tile,captureMode);
        }
	}
}
