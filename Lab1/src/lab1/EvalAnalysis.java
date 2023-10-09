/**
 * I have adhered to the Honor Code in this Assignment

 *
 * @author Moe Ariyoshi
 * Spring 2023
 * 
 */

package lab1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EvalAnalysis {

    public static void main(String[] args) {
    try {
	// TODO Auto-generated method stub
	Scanner file = new Scanner(new File(args[0]));
	
	String entry;
	
	
	int summerCoursesNum = 0;
	int summerEvalSum = 0;
	int regularCoursesNum = 0;
	int regularEvalSum = 0;

	
	do { 
	    entry = file.nextLine();
	    
	    //System.out.println(entry);
	    
	    Scanner line = new Scanner(entry);

	    for (int i = 0; i < 2; i++){
		line.nextInt();
	    };
	    
	    //third piece of data in line
	    int course = line.nextInt();
	    
	    	if (course == 1) {
	    	    summerCoursesNum++;
	    	    
	    	    line.nextInt();
		    
		    //fifth piece of data in line 
		    int eval = line.nextInt();
		    summerEvalSum += eval;
		    
	    	} else if (course == 2) {
	    	    regularCoursesNum++;
	    	    
	    	    line.nextInt();
		    
		    //fifth piece of data in line 
		    int eval = line.nextInt();
		    regularEvalSum += eval;
	    	}
	    	
	    	line.close();
	    	
	    
	}  while (file.hasNextLine());
	    
	
	file.close();
	    
	double summerAvg = summerEvalSum*1.0/summerCoursesNum;
	double regularAvg = regularEvalSum*1.0/regularCoursesNum;
	
	System.out.println("The average review of a summer class is: " + summerAvg);
	System.out.println("The average review of a regular class is: " + regularAvg);
    
    } catch (FileNotFoundException e) {
	System.err.println("Could not open file: " + e.getMessage());
    }
	
	
    }

}

/**
 tae.prn:
The average review of a summer class is: 2.5652173913043477
The average review of a regular class is: 1.921875
 */
