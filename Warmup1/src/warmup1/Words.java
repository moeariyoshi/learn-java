package warmup1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Words {

    public static void main(String[] args) {
	// TODO Auto-generated method stub
//	String words = "I love cookies";
//	Scanner scanner = new Scanner(words);
//	
//	while (scanner.hasNext()) {
//	    String word = scanner.next();
//	    System.out.println(word);
//	}
//	
//	scanner.close();
	
	// We need to declare scanner outside the try/catch block so that it is in scope
	// when we use it in our loop.
	
	Scanner scanner = null;
	try {
	    scanner = new Scanner(new File("input.txt")); 
	    
	    while (scanner.hasNext()) {
		String word = scanner.next();
		System.out.print(word);
		
		//println - one line per word
		//print - one line and no spaces
		//Hi!IlikefoodIlovesleepIadoremusic
		
		//prints a space between words
		//System.out.print(word + " ");  
		
	    }
	    scanner.close();
	    
	} catch (FileNotFoundException e) {
	    System.out.println("Problem opening file: " + e.getMessage());
	    System.exit(1);
	}

    }

}
