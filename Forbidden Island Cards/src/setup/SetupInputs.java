package setup;

import java.util.Scanner;

import cards.DiscardPile;

/**Provides major communications with the player, with system.out
 * as a way of communicating
 * This can be changed if you wish by creating a different version of messenger e.g with a GUI
 */
public class SetupInputs {

    private static SetupOutputs setupOutputs;
    private static Scanner input;

    public SetupInputs() {
        input = new Scanner(System.in);
        setupOutputs = new SetupOutputs();
    }

    public int setDifficulty() {
        boolean validInput = false;
        int waterlevel =0;
        while(!validInput){
            String decision = input.nextLine();
		    try {
			    waterlevel = Integer.parseInt(decision);
            } 
            catch (NumberFormatException e) {
                setupOutputs.error();
			    continue;
		    }
		    if ((waterlevel >= 1) && (waterlevel <= 4)) {
			    validInput = true;
		    }
        }
        return waterlevel;
    }
    
}
