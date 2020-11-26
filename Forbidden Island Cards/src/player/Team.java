package player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import enums.TreasureCardEnums;
import gameplay.Choices;

public class Team {
    private static Team  theTeam;
    private List<Player>       team;

    public static Team getInstance(){
        if(theTeam == null){
        	theTeam = new Team();
        }
        return theTeam;
    }

    private Team() { 
    	this.team = new ArrayList<Player>();
    }

    public int getNumPlayers() {
    	return team.size();
    }

    public Player getPlayer(int i) {
    	return team.get(i);
    }
    
    public void showAllHands(){
        for(int i=0;i<team.size();i++){
            Player player = getPlayer(i);
            System.out.println("\n"+player.getName()+"'s hand: ");	
            player.getHand().printHand();
        }
    }

    public int getPlayerIndex(Player player){
    	return team.indexOf(player)+1;
    }

    public void setPlayer(int i, Player updatedPlayer) {
    	team.set(i-1, updatedPlayer);
    }
    
    public void addPlayer(Player newPlayer) {
    	team.add(newPlayer);
    }
    
    public List<Player> getAllPlayers(){
    	return team;
    }

    public List<Integer> getAllPlayerNums(int exclude){
        List<Integer> allPlayers = new ArrayList<>(); 
        int i=0;
        for (Player j:team){
            if(!(j.getNum() == exclude)){
                allPlayers.add(i);
            }
            i++;
        }
        System.out.println(allPlayers);
        return allPlayers;
    }

	public Player choosePlayer(Scanner inputScanner, List<Integer> eligible){
        if(eligible==null){
            eligible = getAllPlayerNums(-1);
        }
        int userIn = 0;
        for(int i=0;i<team.size();i++){
            if (eligible.contains(i)){
                System.out.println("["+i+"] "+ getPlayer(i).getName());
            }
        }
		boolean validIn = false;
		while (!validIn) {
			String userString = inputScanner.nextLine();
			try {
				userIn = Integer.parseInt(userString);
			} catch (NumberFormatException e) {
                System.out.println("Invalid Input");
				continue;
            }
			if ((userIn >= 0) && (userIn < team.size()) && ((eligible.contains(userIn)))) {
				validIn = true;
            }
            else{
                System.out.println("Invalid Input");
            }
		}
		return getPlayer(userIn);
    }

    public List<Integer> getPlayerswithSpecials(){
        List<Integer> eligible = new ArrayList<>();
        int i=0;
        for (Player p1 : team){
            if(p1.getHand().checkContains(TreasureCardEnums.HELICOPTER_LIFT) || p1.getHand().checkContains(TreasureCardEnums.SANDBAGS)){
                eligible.add(i);
            }
            i++;
        }
        System.out.println(eligible);
        return eligible;
    }

    public boolean enquirePlayers(Scanner inputScanner, boolean asked){
        List<Integer> eligible = getPlayerswithSpecials();
        if(eligible.isEmpty()){
            if(asked){
                System.out.println("No one has a special card...");
            }
            return false;
        }
        if(!Choices.getYesOrNo(inputScanner,"Does anyone want to play their special card?", "No", "Yes")){
            return false;
        }
        Player player1 = choosePlayer(inputScanner,eligible);
        if(player1.checkHasCard(TreasureCardEnums.HELICOPTER_LIFT) && player1.checkHasCard(TreasureCardEnums.SANDBAGS)){
            if(Choices.getYesOrNo(inputScanner,"Do you want to play Helicopter Lift or Sandbags", "Sandbags", "Helicopter Lift")){
                return useHelicopterLift(inputScanner, player1);
            }
            else{
                useSandbags(player1);
                return false;
            }
        }
        if(player1.checkHasCard(TreasureCardEnums.HELICOPTER_LIFT)){
            return useHelicopterLift(inputScanner, player1);
        }
        if(player1.checkHasCard(TreasureCardEnums.SANDBAGS)){
            useSandbags(player1);
            return false;
        }
        return false;
    }

    public boolean useHelicopterLift(Scanner inputScanner, Player player) {
		if(!player.checkHasCard(TreasureCardEnums.HELICOPTER_LIFT)){
			System.out.println("You don't have a helicopter lift card :(");
			return false;
		}
		int pos = player.getHand().getIndexOfCard(TreasureCardEnums.HELICOPTER_LIFT);
		player.getHand().removeCard(pos);
		if (canWin()){
            System.out.println("You've played the Helicopter Lift card with all 4 treasures captured, with all players on Fools Landing!");
            return true;
        }
        System.out.println("Where do you want to move to\n Board.selectTile() coming soon...");
        System.out.println("Who is gonna fly there?");
        List <Integer> availforMove = getAllPlayerNums(-1);
        boolean keepMoving = true;
        do{
            Player playerForHeliMove = theTeam.choosePlayer(inputScanner,availforMove);
            playerForHeliMove.helicopterMove();
            availforMove.remove(playerForHeliMove.getNum());
            keepMoving = Choices.getYesOrNo(inputScanner,"Want to move anyone else here?", "No", "Yes");
        }
        while(!availforMove.isEmpty() && keepMoving);
        return false;
	}

    public void useSandbags(Player player) {
		if(!player.checkHasCard(TreasureCardEnums.SANDBAGS)){
			System.out.println("You don't have a Sandbags card :(");
			return;
		}
		int pos = player.getHand().getIndexOfCard(TreasureCardEnums.SANDBAGS);
		player.getHand().removeCard(pos);
        //Choose tile
        // tile.shoreUp()
        System.out.println("\nSandbags coming soon to a theatre near you\n");
	}

	private boolean canWin() {
        // Check the position of all players if theyre on fools landing
        return false;
    }

}
