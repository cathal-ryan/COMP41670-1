package setup;

import java.util.Scanner;

/**Takes major inputs from the player for setup
* Can be easily refactored to a GUI in line with MVC framework for main game
*/
public class SetupInputs {

    private static SetupOutputs setupOutputs;
    private static Scanner input;

    protected SetupInputs() {
        input = new Scanner(System.in);
        setupOutputs = new SetupOutputs();
    }

    /**setBetween provides functionality for the choice between two integer values, for selecting players
     * and selecting the flood meter
     * @param flood - true if setting for flood meter, false if setting for number of players
     * @return the integer flood meter value or the number of players
     */
    protected int setBetween(boolean flood) {
        int[] lims = new int[2];

        // set the limits based on whether for water meter or number of players
        if(flood){
            lims[0] = 1; lims[1] = 4; 
        }
        else{
            lims[0] = 2; lims[1] = 4;
        }
        boolean validInput = false;
        int choice =0;
        // Get a valid choice between the limits
        while(!validInput){
            String decision = input.nextLine();
		    try {
			    choice = Integer.parseInt(decision);
            } 
            catch (NumberFormatException e) {
                setupOutputs.numError(lims[0],lims[1]);
			    continue;
		    }
		    if ((choice >= lims[0]) && (choice <= lims[1])) {
			    validInput = true; // We're within limits and so set true
            }
            else{
                setupOutputs.numError(lims[0],lims[1]);
            }
        }
        return choice; //Give the player's choice
    }

    /**Prompt the user for selection of a name
     * @return String name of the player
     */
    protected String playerName(){
        return input.nextLine();
    }
    
}
