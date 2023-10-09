package lab8;

import java.util.Comparator;

public class IgnoreCaseComparator implements Comparator<String> {

    /**
     * Performs a case-sensitive comparison of two string objects.
     *
     * @param s1 first string to compare
     * @param s2 second string to compare
     * @return negative if s1 comes before s2, positive if s2 comes before s1, and 0
     *         if they are equivalent
     */
    public int compare(String s1, String s2) {
        return s1.compareToIgnoreCase(s2);
    }
}
