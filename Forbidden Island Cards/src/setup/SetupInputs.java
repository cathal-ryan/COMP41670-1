package setup;

import java.util.Scanner;

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

    public int setBetween(boolean flood) {
        int[] lims = new int[2];
        if(flood){
            lims[0] = 1; lims[1] = 4;
        }
        else{
            lims[0] = 2; lims[1] = 4;
        }
        boolean validInput = false;
        int waterlevel =0;
        while(!validInput){
            String decision = input.nextLine();
		    try {
			    waterlevel = Integer.parseInt(decision);
            } 
            catch (NumberFormatException e) {
                setupOutputs.numError(lims[0],lims[1]);
			    continue;
		    }
		    if ((waterlevel >= lims[0]) && (waterlevel <= lims[1])) {
			    validInput = true;
            }
            else{
                setupOutputs.numError(lims[0],lims[1]);
            }
        }
        return waterlevel;
    }
    
    public String playerName(){
        return input.nextLine();
    }
    
}
