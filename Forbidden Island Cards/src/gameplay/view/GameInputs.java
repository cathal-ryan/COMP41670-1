package gameplay.view;

import java.util.List;
import java.util.Scanner;

public class GameInputs {

    private static GameOutputs theOutputs;
    private static Scanner input;

    public GameInputs() {
        input = new Scanner(System.in);
        theOutputs = new GameOutputs();
    }

    public boolean floodOrTreasure() {
        theOutputs.printFloodorTreasure();
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
            switch (userIn) {
                case 0:
                    return false;
                case 1:
                    return true;
                default:
                    theOutputs.generalError();
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
                theOutputs.generalError();
                continue;
            }
            if ((userInput >= 0) && (userInput < 10)) {
                validInput = true;
            }
            else{
                theOutputs.generalError();
            }
        }
        return userInput;
    }

    public void nextLine(){
        @SuppressWarnings("unused")
        String start = input.nextLine();
    }

    public int playerChoice(int size, List eligible){
        int userIn=0;
        boolean validIn = false;
		while (!validIn) {
			String userString = input.nextLine();
			try {
				userIn = Integer.parseInt(userString);
			} catch (NumberFormatException e) {
                theOutputs.generalError();
				continue;
            }
			if ((userIn >= 0) && (userIn < size && ((eligible.contains(userIn))))) {
				validIn = true;
            }
            else{
                theOutputs.generalError();
            }
        }
        return userIn;
    }

    public int handChoice(int size){
        int userIn=0;
        boolean validIn = false;
		while (!validIn) {
			String userString = input.nextLine();
			try {
				userIn = Integer.parseInt(userString);
			} catch (NumberFormatException e) {
                theOutputs.generalError();
				continue;
            }
			if ((userIn >= 0) && (userIn < size -1)) {
				validIn = true;
            }
            else{
                theOutputs.generalError();
            }
        }
        return userIn;
    }

    public int heliWhere(){
        System.out.println("Where do you want to move to?\nBoard prints..\they pick");
        return 0;
    }
}
