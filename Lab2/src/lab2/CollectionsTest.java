/**
 *I have adhered to the Honor Code in this assignment.
 *
 *Lab Helper: Toby 
 *
 * @author Moe Ariyoshi
 * Spring 2023
 */

package lab2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;

public class CollectionsTest {
    public static int MAX_SIZE = 50000;
    public static int NUM_TESTS = 10;

    public static long TimeAdd(Collection<Integer> c) {
        // TODO: Implement this method.
        
        long startTime = java.lang.System.currentTimeMillis();
        
        for (int i=0; i< MAX_SIZE;i++) {
            c.add(i);
        }
        
        long endTime = java.lang.System.currentTimeMillis();
        
        long timeTaken = endTime - startTime;

        return timeTaken;
    }

    public static long TimeContains(Collection<Integer> c) {
        
        long startTime = java.lang.System.currentTimeMillis();
        
        for (int i=0; i < MAX_SIZE;i++) {
            c.contains(i);
        }
        
        long endTime = java.lang.System.currentTimeMillis();
        
        long timeTaken = endTime - startTime;

        return timeTaken;
        
    }

    public static void TestCollection(Collection<Integer> c) {
        long[] testResults = new long[NUM_TESTS];
        
        for (int i=0; i < NUM_TESTS; i++) {
            long testTime = TimeAdd(c) + TimeContains(c);
            testResults[i] = testTime;
        }
        long totalTestTime = 0;
        long max = testResults[0];
        long min = testResults[0];
        
        for (int i = 1; i < testResults.length; i++) {
            //finds minimum
            if (testResults[i] < min) {
                min = testResults[i];
            } 
            //finds maximum
            if (testResults[i] > max) {
                max = testResults[i];
            }
            //adds the test times to calculate average later
            totalTestTime += testResults[i];
        }
        
        //Calculates average
        long avg = totalTestTime/NUM_TESTS;
     
        // Terminate by printing your results
        System.out.println("The minimum of test time is: " + min);
        System.out.println("The minimum of test time is: " + max);
        System.out.println("The average of test time is: " + avg);
        
    }

    public static void main(String[] args) {
        Collection<Integer> c;
        
        System.out.println("ArrayList!");
        c = new ArrayList<Integer>();
        TestCollection(c);
        System.out.println("TreeSet!");
        c = new TreeSet<Integer>();
        TestCollection(c);
    }

}