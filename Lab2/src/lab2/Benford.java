/**
 *I have adhered to the Honor Code in this assignment.
 * 
 * Lab Helper: Olivia Brown
 * @author Moe Ariyoshi
 * Spring 2023
 */


package lab2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Benford {

    public static void main(String[] args) {
        
        char firstChar;
        int integer;
        String number;
        int[] count = new int[10];
        Scanner file = null;
        
        try {
            file = new Scanner(new File(args[0]));
    
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!");
        } 
        
        
        do { 
            Scanner line = new Scanner(file.nextLine());
            
            while (line.hasNext()) {
                
                String word = line.next();
                firstChar = word.charAt(0);
                
                if (Character.isDigit(firstChar)) {
                    number = word.substring(0,1);
                    integer = Integer.parseInt(number);
                    count[integer]++;
            }
            
            }
            
            
         } while (file.hasNextLine());
         
        final int MAX_WIDTH = 50;
       
       int maxCount = count[0];
       int total = 0;
       
       for (int i = 0; i < count.length; i++) {
           if (count[i] > maxCount) {
               maxCount = count[i];    
           }
           
           total += count[i];
       }

       
       System.out.println("Welcome to the Benford analysis program!");
       
       for (int i=0; i < 10; i++) {
           
           float freq = (float)count[i]*100/total;
           
           System.out.printf("%d %8d %4.1f%% : ", i, count[i], freq);
           
           int numStars = Math.round(count[i]*MAX_WIDTH/maxCount);
          
           
           for (int j = 0; j < numStars; j++) {
               System.out.print("*");  
           }
           
           System.out.println();
           
       }
       
      
 
  }

}
