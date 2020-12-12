package gameplay;

import java.util.Scanner;

public class Choices {
        private Choices(){}

        public static boolean getYesOrNo(Scanner inputScanner, String message, String n, String y) {
            int userIn = 0;
            System.out.println("\n"+message);
            System.out.println("[0] "+ n);
            System.out.println("[1] "+ y);
            while(true) {
                String decision = inputScanner.nextLine();
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
    
}
