/**
 * I have adhered to the Honor Code in this assignment. 
 * @author Moe Ariyoshi
 * Spring 2023
 */

package markov;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class MyHashMap<K, V> {
    
    
    private static final int TABLE_SIZE[] = { 5, 11, 23, 47, 97, 197, 397, 797, 1597, 3203, 6421, 12853, 25717, 51437,
            102877, 205759, 411527, 823117, 1646237, 3292489, 6584983, 13169977, 26339969, 52679969, 105359939,
            210719881, 421439783, 842879579, 1685759167 };
    
    //The array of buckets used in the hash map.
    private LinkedList<SimpleEntry<K, V>>[] table;
   
    //Current number of items in the table (not the number of buckets, use table.length for that).
    // Prime number size is good!
    private int size;
    
    //Maximum permitted load factor for the table
    // Size/capacity(number of buckets)
    private float loadFactor;
    private static final int DEFAULT_CAPACITY = 11;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**   
     * Create a new array of <code>LinkedList<SimpleEntry<K, V>></code> 
     * prime size at least <code>size</code>.
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
        //Why is it square brackets? 
        return new LinkedList[size];
    }

    //CONSTRUCTOR 
    public MyHashMap(int capacity, float loadFactor) {
	this.table = createTable(capacity);
	this.size = 0;
	this.loadFactor = loadFactor;
    }
    
    //CONSTRUCTOR DEFAULT
    public MyHashMap(){
	this.table = createTable(DEFAULT_CAPACITY);
	this.size = 0;
	this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

    public V get(K key) {
	
	if (containsKey(key)) {
	    int index = (key.hashCode() & Integer.MAX_VALUE) % this.table.length;
	    for (int i = 0; i < table[index].size(); i++) {
		if (table[index].get(i).getKey() == key) {
		    return table[index].get(i).getValue();
		}
	    }
	}
	
        return null;
    }
    
//    /**
//     * gets the key of the first element in the hashmap!
//     * @return
//     */
//    public K getFirstKey() {
//	
//	if (table.length == 0) {
//	    throw new NoSuchElementException();
//	}
//	int i=0;
//	while(table[i] == null) {
//	    i++;
//	}
//	return table[i].get(0).getKey();
//    }

    public V put(K key, V value) {
	
	if (key == null | value == null) {
	    throw new NullPointerException();
	}
	
	int index = (key.hashCode() & Integer.MAX_VALUE) % this.table.length;
	
	if (containsKey(key)) {
	    for (int j = 0; j < table[index].size(); j++) {
		if (table[index].get(j).getKey() == key) {
	    		return table[index].get(j).setValue(value);
	    	}	
	    }
	}
	
	//If key does not exist yet
	//& Integer.MAX_VALUE transforms it to a nonnegative integer
	
	if (this.size/this.table.length >= this.loadFactor) {
	    resize();
	}
	
	index = (key.hashCode() & Integer.MAX_VALUE) % this.table.length;
	
	if (table[index] == null) {
	    table[index] = new LinkedList<SimpleEntry<K, V>>();
	}
	
	this.table[index].add(new SimpleEntry<K,V>(key,value));
	
	size++;
	//No previous value 
        return null;
    }

    //Returns a list containing all of the entries in the hash map, in any order.
    //Java collections like ArrayList have an addAll(otherCollection) method 
    //that adds all of the elements of otherCollection.
    public List<SimpleEntry<K, V>> entryList() {
	
	List<SimpleEntry<K, V>> list = new ArrayList<SimpleEntry<K,V>>();
	
	for (int i = 0; i < this.table.length; i++) {
	    if (table[i] != null) {
		for (int j = 0; j < table[i].size(); j++) {
		    list.add(table[i].get(j));
		}
	    }
	}
	return list;
    }
    
    //Return the number of items in the hash map, not the number of buckets.
    public int size() {
	return size;
    }
    
    //Return true if size is 0, false otherwise (note you do not need an if statement for this).
    public boolean isEmpty() {
	if (size == 0) {
	    return true;
	}
	return false;
    }
    
    public V remove(K key) {
	if (!containsKey(key)) {
	    throw new NoSuchElementException();
	} else {
	    
	    V oldValue = this.get(key);
	    int index = (key.hashCode() & Integer.MAX_VALUE) % this.table.length;
	    
	    for (int j = 0; j < table[index].size(); j++) {
		if (table[index].get(j).getKey() == key) {
		    oldValue = table[index].remove(j).getValue();
		}
	    }
		
	    size--;
	    return oldValue;   
	}	
    }
    
    //Empties out the hash map
    public void clear() {
	for (int i = 0; i < table.length; i++) {
	    if (table[i] != null) {
		table[i].clear();
	    }
	}
	
	size = 0;
    }
    
    public boolean containsKey(K key) {
	int index = (key.hashCode() & Integer.MAX_VALUE) % this.table.length;
	
	if (table[index] != null) {
	    for (int j = 0; j < table[index].size(); j++) {
		if (table[index].get(j).getKey() == key) {
		    return true;
		}
	    }
	}
	
	return false;
    }
    
    public boolean containsValue(V value) {
	for (int i = 0; i < this.table.length; i++) {
	    if (table[i] != null) {
		for (int j = 0; j < table[i].size(); j++) {
		    if (table[i].get(j).getValue() == value) {
			return true;
		    }
		}
	    }
	}
	return false;
    }
    
    private void resize() {
	
	LinkedList<SimpleEntry<K, V>>[] oldTable = this.table.clone();
	
	this.table = createTable(this.table.length * 2);
	
	for (int i = 0; i < oldTable.length; i++) {
	    if (oldTable[i] != null) {
		for (int j = 0; j < oldTable[i].size(); j++) {
		    this.put(oldTable[i].get(j).getKey(), oldTable[i].get(j).getValue());
		}
	    }
	}
	
    }
    
    
}
