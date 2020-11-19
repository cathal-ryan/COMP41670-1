package gameplay;

import java.util.Scanner;

public class Choices {
        private Choices(){}

        public static boolean getYesOrNo(Scanner input) {
            String decision;
            while(true) {
                decision = input.nextLine();
                switch(decision) {
                case "y":
                    return true;
                case "Y":
                    return true;
                case "n":
                    return false;
                case "N":
                    return false;
                default:
                System.out.println("Invalid input.");
            }
            }
        }
    
}
