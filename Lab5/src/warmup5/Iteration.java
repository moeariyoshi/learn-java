/**
 * Moe Ariyoshi
 * Spring 2023
 * I have adhered to the Honor Code in this assignment. 
 */

package warmup5;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Iteration {
    
    static final int LIST_SIZE = 100000;
    
    static void addElements(List<Integer> list) {
        for (int i=0; i<LIST_SIZE; i++) {
            list.add(5);
        }
    }
    
    //Sum using for loop and .get()
    static int sum1(List<Integer> list) {
        int sum = 0;
        for (int i=0; i<list.size(); i++) {
            sum += list.get(i);
        }
        return sum;
    }
    
    //Sum with Iterator 
    static int sum2(List<Integer> list) {
        Iterator<Integer> iter = list.iterator();
        int sum = 0;
        while (iter.hasNext()) {
            int listItem = iter.next();
            sum += listItem;
        }
        return sum;
        
    }
    
    //"for-each" loop
    //Needs iterable interface 
    static int sum3(List<Integer> list) {
        int sum = 0;
        for (Integer num : list) {
            sum += num;
        }
        return sum;
    }
    

    
    public static void main(String[] args) {
        //ArrayList 
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        addElements(arrayList);
        
        //LinkedList 
        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        addElements(linkedList);
        
        
        LocalTime start = LocalTime.now();
        sum1(arrayList);
        LocalTime end = LocalTime.now();
        System.out.println("sum1(arrayList):  " + Duration.between(start, end).toString());
        
        //Prints: sum1(arrayList):  PT0.023918S
        // PT - period time 
        // 0.0239185 seconds 
        
       
        start = LocalTime.now();
        sum1(linkedList);
        end = LocalTime.now();
        System.out.println("sum1(linkedList): " + Duration.between(start,  end).toString());
        //sum1(linkedList): PT3.461957S
        
        
        start = LocalTime.now();
        sum2(arrayList);
        end = LocalTime.now();
        System.out.println("sum2(arrayList):  " + Duration.between(start, end).toString());
        //sum2(arrayList):  PT0.005303S

        start = LocalTime.now();
        sum2(linkedList);
        end = LocalTime.now();
        System.out.println("sum2(linkedList): " + Duration.between(start,  end).toString());
        //sum2(linkedList): PT0.002345S
        
        start = LocalTime.now();
        sum3(arrayList);
        end = LocalTime.now();
        System.out.println("sum3(arrayList):  " + Duration.between(start, end).toString());

        start = LocalTime.now();
        sum3(linkedList);
        end = LocalTime.now();
        System.out.println("sum3(linkedList): " + Duration.between(start,  end).toString());
   
        //sum3(arrayList):  PT0.005206S
        //sum3(linkedList): PT0.00743S
        
    }

}
