package warmup2;

import java.util.Scanner;


public class Arrays {

    public static void main(String[] args) {
	//create a new array of integers of length 10
	//int[] arr = new int[10];
	
//	for (int i = 0; i < arr.length; i++) {
//	    System.out.println(arr[i]);
//	}
	
	Scanner scanner = new Scanner(System.in);
	System.out.print("How many numbers do you want to enter? ");
	int numInt = scanner.nextInt();
	
	int[] arr = new int[numInt];
	
	for (int i = 0; i < numInt; i++) {
	    System.out.print("Enter an int: ");
	    arr[i] = scanner.nextInt();
	}
	
	for (int j = arr.length -1; j >= 0; j--) {
	   System.out.println(arr[j]);
	}
	
	scanner.close();

    }

}
