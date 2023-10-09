/**
 * I have adhered to the Honor Code in this Assignment
 *
 * @author Moe Ariyoshi
 * Spring 2023
 */

package lab1;

public class Pyramid {

    public static void main(String[] args) {
	
	if (args.length == 1) {
	    
	    int height = Integer.parseInt(args[0]);
	    
	    for (int i = 1; i < height + 1; i++) {
		
		for (int a = 1; a <= height -i; a++) {
		    System.out.print(" ");
		}
		for (int b = 1; b <= 2*i-1; b++) {
		    System.out.print("*");
		}
	
		
		System.out.println();
	    }
	
	    
	} else {
	    System.out.println("Invalid argument!");
	    System.exit(1);
	}

    }

}
