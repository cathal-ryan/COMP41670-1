package player;

public class Engineer extends Player{

	public Engineer(int playerNum, String playerName) {
		super(playerNum, playerName);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String getPlayerType() {
		return "Engineer";
	}

}
