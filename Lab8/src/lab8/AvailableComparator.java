package lab8;

import java.util.Comparator;

public class AvailableComparator implements Comparator<Task> {

    /**
     * Performs a case-sensitive comparison of two string objects.
     *
     * @param t1 first task to compare
     * @param t2 second task to compare
     * @return negative if s1 comes before s2, positive if s2 comes before s1, and 0
     *         if they are equivalent
     */
    public int compare(Task t1, Task t2) {
        if (t1.availableTime == t2.availableTime) {
            return 0;
        } else if (t1.availableTime < t2.availableTime) {
            return -1;
        }
        return 1;
    }

}
