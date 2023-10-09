 /**
 * @author Moe Ariyoshi
 * I have adhered to the Honor Code in this assignment.
 * Spring 2023
 */

package trees;


/**
 * Load a tree from a text file.  Format is line based, with each line
 * consisting of a String for data, followed by two ints indicating if
 * the node has a left child or right child.  (1 is yes, 0 is no).
 * Ordering of nodes is postorder.
 *
 * @author John Donaldson
 * @author Benjamin Kuperman (Spring 2007)
 * @author Alexa Sharp (Fall 2012)
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

/**
 * Has a method that makes a tree from a txt file
 * @param fname filename to load 
 * @return old value at index
 */
public class TreeLoader {
    
    /**
     * Creates a BinaryTree based on the files 
     * @param fname filename to load 
     * @return old value at index
     */
    public static BinaryTree<String> loadTreeFromFile(String fname) throws IOException {
	    
	    Stack<BinaryTree<String>> stack = new Stack<BinaryTree<String>>();
	    
	    Scanner file = new Scanner(new File(fname));
	    
	    while (file.hasNextLine()) {
	        String line = file.nextLine();
	       
	        Scanner info = new Scanner(line);
	        
	        
	        String data = info.next();
	        BinaryTree<String> left = new EmptyTree<String>();
	        BinaryTree<String> right = new EmptyTree<String>();
	        
	        String leftTag = info.next();
	        String rightTag = info.next();
	        
	        info.close();
	        
	        //Q. Why does the code not work when it's leftTag == "1"?
	        int leftNum = Integer.parseInt(leftTag);
	        int rightNum = Integer.parseInt(rightTag);
	       
	         
	        //Because the stack is a stack and it's post-order
	        //Right child has to be popped first
	        
	        if (rightNum == 1) {
	            right = stack.pop();
	        }
	        
	        if (leftNum == 1) {
	            left = stack.pop();
	        }
	        
	        ConsTree<String> newTree = new ConsTree<String>(data, left, right);
	        
	        stack.push(newTree);
	       
	    }
	    
	    //Why does the stack have so many trees? Never popped? 
	    //System.out.println(stack);
	    
	    
	    file.close();
	    
	    if (stack.isEmpty()) {
	        return new EmptyTree<String>();
	    } else {
	        return stack.pop();
	    }
	    
	}
}
