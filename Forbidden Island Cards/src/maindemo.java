import java.util.Scanner;

import gameplay.GameManager;
import setup.Setup;


public class maindemo {
    public static void main(String[] args) {

		Scanner inputScanner = new Scanner(System.in);
		
        Setup.getInstance().doAllSetup(inputScanner);
        
        GameManager.getInstance().doGameplay(inputScanner);

        inputScanner.close();
        
    }
}
