/**
 * @author Moe Ariyoshi
 * I have adhered to the Honor Code in this assignment. 
 * Spring 2023
 */

package lab8;

import java.util.Comparator;

public class PriorityComparator implements Comparator<Task> {
    /**
     * @param t1 first task to compare
     * @param t2 second task to compare
     * @return 
     * negative if s1 priority is lower in number than s2, 
     * positive if s2 is lower, 
     * and 0 if they are equivalent
     */
    public int compare(Task t1, Task t2) {
        if (t1.priority == t2.priority) {
            return 0;
        } else if (t1.priority > t2.priority) {
            return -1;
        }
        return 1;
    }
}
