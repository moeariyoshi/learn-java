/**
 * I have adhered to the Honor Code in this assignment. 
 * @author Moe Ariyoshi
 * Spring 2023
 */
package markov;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.SecureRandom;
import java.util.Random;

public class TextGenerator {
    
    

    public static void main(String[] args) {
	//command line parameters 
	// - an integer order
	// - an integer length
	// - a filename file
	
	if (args.length != 3) {
            System.err.println("MarkovModel requires three parameters: order length file");
            System.exit(1);
        }
	
	int order = Integer.parseInt(args[0]);
	int length = Integer.parseInt(args[1]);
	
	String input = "I have green eggs and hams";
        try {
            File file = new File(args[2]);
            input = Files.readString(file.toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
            e.printStackTrace();
            System.exit(1);
        }
	
	MarkovModel model = new MarkovModel(order);
	
	model.train(input);
	
	Random rand = new SecureRandom();
	
	String startString = input.substring(0,order);
	//String startString = model.getFirstPrefix();
	
	System.out.println(startString);
	String newText = model.generate(startString, length, rand);
	
	System.out.println(newText);
	
	
    }

}
