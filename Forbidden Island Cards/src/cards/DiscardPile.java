package cards;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

import enums.TilesEnums;


abstract public class DiscardPile {
	protected  Stack<Card>  Discarded;
	
	abstract public void putbackall();

	public void addToPile(Card card) {
		Discarded.push(card);
	}
	public void shuffle() {
		Collections.shuffle(Discarded);
	}

	public void printPile() 
	{ 
		// If stack is empty then return 
		if (Discarded.isEmpty()) 
			return; 
		 
		Card x = Discarded.peek(); 
	 
		// Pop the top element of the stack 
		Discarded.pop(); 
	 
		// Recursively call the function PrintPile 
		printPile(); 
	 
		// Print the stack element starting 
		// from the bottom 
		System.out.print(x.getName() + ", "); 
	 
		// Push the same element onto the stack 
		// to preserve the order 
		Discarded.push(x); 
	} 
	}
