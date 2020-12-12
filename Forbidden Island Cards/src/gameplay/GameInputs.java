package gameplay;

import java.util.Scanner;

public class GameInputs {

    private Messenger theMessenger;
    private static Scanner input;

    public GameInputs() {
		input = new Scanner(System.in);
		theMessenger = new Messenger();
	}

    public boolean floodOrTreasure(){
        theMessenger.printFloodorTreasure();
        return getYesOrNo("Flood", "Treasure");
    }

    public static boolean getYesOrNo(String n, String y) {
        int userIn = 0;
        System.out.println("[0] " + n);
        System.out.println("[1] " + y);
        while (true) {
            String decision = input.nextLine();
            try {
                userIn = Integer.parseInt(decision);
            } catch (NumberFormatException e) {
                continue;
            }
            switch(userIn) {
                case 0:
                    return false;
                case 1:
                    return true;
                default:
                System.out.println("Invalid input.");
            }
        }
    }

    public int turnChoice(){
        boolean validInput = false;
        int userInput=-1;
        while (!validInput) {
            String userString = input.nextLine();
            try {
                userInput = Integer.parseInt(userString);
            } catch (NumberFormatException e) {
                theMessenger.generalError();
                continue;
            }
            if ((userInput >= 0) && (userInput < 10)) {
                validInput = true;
            }
        }
        return userInput;
    }

    public void nextLine(){
        @SuppressWarnings("unused")
        String start = input.nextLine();
    }
}
