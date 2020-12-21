package gameplay.view;

import java.awt.Point;
import java.util.List;
import java.util.Scanner;

/**
 * Takes inputs from the player and checks, using system.in
 * Allows for easy refactoring to a GUI. Could be subsituted for prompts to player etc.
 * 
 * @author Cathal Ryan and Conor Kneafsey
 */
public class GameInputs {

    private static GameOutputs theOutputs;
    private static Scanner input;

    public GameInputs() {
        input = new Scanner(System.in);
        theOutputs = new GameOutputs();
    }

    /**
     * Gets the movement direction
     * @return char direction in which to move
     */
    public char moveDir() {
        char userInput = 0;
        boolean validInput = false;
        while (!validInput) {
            String userString = input.nextLine();
            try {
                userInput = userString.charAt(0);
            } catch (NumberFormatException e) {
                theOutputs.generalError();
                continue;
            }
            if ((userInput == 'w') || (userInput == 'W')) {
                validInput = true;
                userInput = 'w';
            }
            else if ((userInput == 'a') || (userInput == 'A')) {
                validInput = true;
                userInput = 'a';
            }
            else if ((userInput == 's') || (userInput == 'D')) {
                validInput = true;
                userInput = 's';
            }
            else if ((userInput == 'd') || (userInput == 'D')) {
                validInput = true;
                userInput = 'd';
            }
            else{
                theOutputs.generalError();
            }
        }
        return userInput;

    }
    /**
     * Prompts choice between flood or treasure
     * @return choice of Flood or Treasure from boolYN
     */
    public boolean floodOrTreasure() {
        theOutputs.printFloodorTreasure();
        return boolYN("Flood", "Treasure");
    }

    /**
     * Allows for a simple choice of 3 options
     * @param n Option 0, usually no
     * @param y Option 1, usually yes
     * @param other Option 2, usually some other prompt like game state
     * @return integer representing option chosen, 0,1 or 2
     */
    public static int get3Choice(String n, String y, String other) {
        int userIn = 0;
        theOutputs.showOption(0,n,"");
        theOutputs.showOption(1,y,"");
        theOutputs.showOption(2,other,"");
        while (true) {
            String decision = input.nextLine();
            try {
                userIn = Integer.parseInt(decision);
            } catch (NumberFormatException e) {
                continue;
            }
            switch (userIn) {
                case 0:
                    return 0;
                case 1:
                    return 1;
                case 2:
                    return 2;
                default:
                    theOutputs.generalError();
            }
        }
    }

    /**
     * Allows for a simple choice of 2 options
     * @param n Option 0, usually no
     * @param y Option 1, usually yes
     * @return boolean false if option 0 chosen, true if option 1 chosen
     */
    public static boolean boolYN(String n, String y) {
        int userIn = 0;
        theOutputs.showOption(0,n,"");
        theOutputs.showOption(1,y,"");
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

    /**
     * Lets player choose which turn option to go for
     * @return integer representing choice of turn
     */
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
            // Can only pick between 11 choices
            if ((userInput >= 0) && (userInput < 11)) {
                validInput = true;
            }
            else{
                theOutputs.generalError();
            }
        }
        return userInput;
    }

    /**
     * Waits for player to hit enter to confirm
     */
    public void confirm(){
        @SuppressWarnings("unused")
        String start = input.nextLine();
    }

    /**
     * Lets player choose from a list of eligible players
     * @param size The size of the team of players
     * @param eligible Which players are eligible to be chosen here
     * @return integer number representing player number chosen
     */
    public int playerChoice(int size, List<Integer> eligible){
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
            // Must be in the eligible players, greater than 0 but less than size of the team
			if ((userIn >= 0) && (userIn < size && ((eligible.contains(userIn))))) {
				validIn = true;
            }
            else{
                theOutputs.generalError();
            }
        }
        return userIn;
    }

    /**
     * Lets player choose from a hand
     * @param size The size of the hand
     * @return integer number representing index of card chosen
     */
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
			if ((userIn >= 0) && (userIn < size )) {
				validIn = true;
            }
            else{
                theOutputs.generalError();
            }
        }
        return userIn;
    }

    /**
     * Lets player select a tile by entering in xy coordinates of the tile
     * @return Point with coordinates of the tile chosen
     */
	public Point selectTile() {
        int[] intArray = {0,0};
        boolean valid = false;
        for(int i=0;i<2;i++){
            if(i==0){
                theOutputs.enterCoords('x');
            }
            if(i==1){
                theOutputs.enterCoords('y');
            }
            valid = false;
            while (!valid) {
                String userString = input.nextLine();
                try {
                    intArray[i] = Integer.parseInt(userString);
                } catch (NumberFormatException e) {
                    theOutputs.generalError();
                    continue;
                }
                if ((intArray[i] >= 0) && (intArray[i] < 6 )) {
                    valid = true;
                }
                else{
                    theOutputs.generalError();
                }
            }
        }
        return new Point(intArray[0],intArray[1]);
    }
}
