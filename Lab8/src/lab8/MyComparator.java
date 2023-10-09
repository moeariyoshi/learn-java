/**
 * @author Moe Ariyoshi
 * I have adhered to the Honor Code in this assignment. 
 * Spring 2023
 */
package lab8;

import java.util.Comparator;

public class MyComparator implements Comparator<Task> {
    /**
     * @param t1 first task to compare
     * @param t2 second task to compare
     * @return 
     * negative if s1 priority is lower in number than s2, 
     * positive if s2 is lower, 
     * and 0 if they are equivalent
     * 
     * even if t1 priority is lower, if it's not different my over 2, 
     * it will consider the deadline priority 
     */
    public int compare(Task t1, Task t2) {
        if (t1.priority == t2.priority) {
            return 0;
        //t1 priority is smaller but 
        } else if (t2.priority - t1.priority > 2) {
            //t1 has earlier deadline
            if (t1.deadline < t2.deadline) {
                return -1;
            } else {
                return 1;
            }
        } else if (t1.priority > t2.priority) {
            return -1;
            
        }
        return 1;
    }
}
