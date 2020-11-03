package player;

public class Pilot extends Player{

	public Pilot(int playerNum, String playerName) {
		super(playerNum, playerName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getPlayerType() {
		return "Pilot";
	}

}
