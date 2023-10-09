package warmup1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Lines {

    public static void main(String[] args) {
	// TODO Auto-generated method stub
	
	try {
	    Scanner lineScanner = new Scanner(new File("input.txt")); 
	    
	    while (lineScanner.hasNextLine()) {
		String line = lineScanner.nextLine();
		Scanner wordScanner = new Scanner(line);
		
		while (wordScanner.hasNext()) {
		    String word = wordScanner.next();
		    System.out.print(word + " ");
		    
		    
		}
		
		System.out.println();
		
		wordScanner.close();
		
	    }
	    

	lineScanner.close();
	    
	} catch (FileNotFoundException e) {
	    System.out.println("Problem opening file: " + e.getMessage());
	    System.exit(1);
	}
	

    }

}
