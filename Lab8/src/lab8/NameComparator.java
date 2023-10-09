package lab8;

import java.util.Comparator;

public class NameComparator implements Comparator<Task> {
    /**
     * @param t1 first task to compare
     * @param t2 second task to compare
     * @return 
     * negative if s1 takes less time than s2, 
     * positive if s2 takes less time than s1, 
     * and 0 if they are equivalent
     */
    public int compare(Task t1, Task t2) {
        if (t1.name == t2.name) {
            return 0;
        } else if (t1.name.compareTo(t2.name)<0) {
            return -1;
        }
        return 1;
    }
}
