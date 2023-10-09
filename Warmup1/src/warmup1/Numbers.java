package warmup1;

import java.util.Scanner;


public class Numbers {

    public static void main(String[] args) {
	// TODO Auto-generated method stub
	String numbers = "28 37 492";
	Scanner scanner = new Scanner(numbers);
	
	while (scanner.hasNextInt()) {
	    int num = scanner.nextInt();
	    System.out.println(num);
	}
	
	scanner.close();

    }

}
