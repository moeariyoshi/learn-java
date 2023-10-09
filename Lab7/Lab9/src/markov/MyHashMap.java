package markov;

import java.util.AbstractMap.SimpleEntry;
import java.util.LinkedList;
import java.util.List;

public class MyHashMap<K, V> {
    private static final int TABLE_SIZE[] = { 5, 11, 23, 47, 97, 197, 397, 797, 1597, 3203, 6421, 12853, 25717, 51437,
            102877, 205759, 411527, 823117, 1646237, 3292489, 6584983, 13169977, 26339969, 52679969, 105359939,
            210719881, 421439783, 842879579, 1685759167 };

    /**
     * Create a new array of <code>LinkedList<SimpleEntry<K, V>></code> prime size
     * at least <code>size</code>.
     * 
     * @param size The minimum size of the array.
     * @return The new array with all elements set to <code>null</code>.
     */
    @SuppressWarnings("unchecked")
    private LinkedList<SimpleEntry<K, V>>[] createTable(int size) {
        for (int primeCapacity : TABLE_SIZE) {
            if (primeCapacity >= size) {
                size = primeCapacity;
                break;
            }
        }
        return new LinkedList[size];
    }

    public MyHashMap() {
        // Implement this
    }

    public V get(K key) {
        // Implement this
        return null;
    }

    public V put(K key, V value) {
        // Implement this
        return null;
    }

    public List<SimpleEntry<K, V>> entryList() {
        // Implement this.
        return null;
    }
}
