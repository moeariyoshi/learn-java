/**
 * @author Moe Ariyoshi
 * I have adhered to the Honor Code in this assignment. 
 * Spring 2023
 */

package lab8;

import java.util.Comparator;

public class DeadlineComparator implements Comparator<Task> {
    
    /**
     * @param t1 first task to compare
     * @param t2 second task to compare
     * @return negative if t1 is due before t2, positive if t1 is due after t2
     */

    @Override
    public int compare(Task t1, Task t2) {
        if (t1.deadline == t2.deadline) {
            return 0;
        } else if (t1.deadline < t2.deadline) {
            return -1;
        }
        return 1;
    }
  
}
