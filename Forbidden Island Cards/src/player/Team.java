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

	public Player choosePlayer(Scanner inputScanner, Player currentPlayer){
		int userIn = 0;
        for(int i=0;i<team.size();i++){
            Player player = getPlayer(i);
            if(currentPlayer != getPlayer(i)){
                System.out.println("["+i+"] "+player.getName());	
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
            System.out.println(team.indexOf(currentPlayer));
			if ((userIn >= 0) && (userIn < team.size()) && (userIn != team.indexOf(currentPlayer)) ) {
				validIn = true;
			}
		}
		return getPlayer(userIn);
    }

    public boolean enquirePlayers(Scanner inputScanner, boolean asked){
        List<Integer> eligible = new ArrayList<>();
        int i=0;
        for (Player p1 : team){
            if(p1.getHand().checkContains(TreasureCardEnums.HELICOPTER_LIFT) || p1.getHand().checkContains(TreasureCardEnums.SANDBAGS)){
                eligible.add(i);
            }
            i++;
        }
        if(eligible.isEmpty()){
            if(asked){
                System.out.println("No one has a special card...");
            }
            return false;
        }
        if(!Choices.getYesOrNo(inputScanner,"Does anyone want to play their special card?", "No", "Yes")){
            return false;
        }
        int userIn = 0;
        for(int j=0;j<eligible.size();j++){
            Player player = getPlayer(eligible.get(j));
            System.out.println("["+eligible.get(j)+"] "+player.getName());
        }
		boolean validIn = false;
		while (!validIn) {
			String userString = inputScanner.nextLine();
			try {
				userIn = Integer.parseInt(userString);
			} catch (NumberFormatException e) {
				continue;
			}
			if ((userIn >= 0) && (eligible.contains(userIn)))  {
				validIn = true;
			}
		}
        Player player1 = getPlayer(userIn);
        if(player1.checkHasCard(TreasureCardEnums.HELICOPTER_LIFT) && player1.checkHasCard(TreasureCardEnums.SANDBAGS)){
            if(Choices.getYesOrNo(inputScanner,"Do you want to play Helicopter Lift or Sandbags", "Helicopter Lift", "Sandbags")){
                return useHelicopterLift(inputScanner, player1);
            }
            else{
                useSandbags(player1);
                System.out.println("am i here2?");
                return false;
            }
        }
        if(player1.checkHasCard(TreasureCardEnums.HELICOPTER_LIFT)){
            return useHelicopterLift(inputScanner, player1);
        }
        if(player1.checkHasCard(TreasureCardEnums.SANDBAGS)){
            System.out.println("am i here1?");
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
		System.out.println("Who do you want to move?");
        Player playerForHeliMove = theTeam.choosePlayer(inputScanner,null);
        playerForHeliMove.helicopterMove();
        return false;
	}

    public void useSandbags(Player player) {
        System.out.println("am i here3?");
		if(!player.checkHasCard(TreasureCardEnums.SANDBAGS)){
			System.out.println("You don't have a Sandbags card :(");
			return;
		}
		int pos = player.getHand().getIndexOfCard(TreasureCardEnums.SANDBAGS);
		player.getHand().removeCard(pos);
        //Choose tile
        // tile.shoreUp()
        System.out.println("\nShoring up coming soon to a theatre near you\n");
	}

	private boolean canWin() {
        // Check the position of all players if theyre on fools landing
        return false;
    }

}
