/**
 * I have adhered to the Honor Code in this Assignment
 *
 * @author Moe Ariyoshi
 * Spring 2023
 */

package lab1;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;



public class HiLo {

    public static void main(String[] args) {
	Random rnd = new Random();
	int target = rnd.nextInt(1000) + 1;
	
	System.out.println("Let's play a game!");
	System.out.println("I'm thinking of a number between 1 and 1000");
	System.out.println("Try to guess what it is!");
	
	Scanner userGuess = new Scanner(System.in);
	
	boolean guessed = false;
	
	int count = 0;
	
	int guess = 0;
	
	while (!guessed) {
	    
	    count++;
	    
	    boolean valid = false;
	    
	    do {
		try {
		    System.out.print("Enter guess: ");
		    guess = userGuess.nextInt();
		    valid = true;
		} catch (InputMismatchException e) {
		    System.out.println("Not valid!");
		}
		userGuess.nextLine();
		
	    } while (!valid);
	
	   	    
	    if (guess == target) {
	    	  guessed = true;
	    	  break; 
	    } else {
	    	    
	       if (guess < target) {
	    	    System.out.println("Too low!");
	       } else {
		   System.out.println("Too high!");
	       }
	    }
	
	}

	System.out.println("You guessed my number! It took you " + count + " tries!");
	userGuess.close();
	
    }

}
