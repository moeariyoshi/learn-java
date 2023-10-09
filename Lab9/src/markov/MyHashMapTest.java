/**
 * I have adhered to the Honor Code in this assignment. 
 * @author Moe Ariyoshi
 * Spring 2023
 */

package markov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MyHashMapTest {

    @Test
    //CONSTRUCTOR
    void testMyHashMap() {
	MyHashMap<String, Integer> test = new MyHashMap<String,Integer>();
	assertEquals(0, test.size());   //tests size
	assertEquals(true, test.isEmpty());
    };
    
    @Test
    void testPut() {
	MyHashMap<String, Integer> test = new MyHashMap<String,Integer>();
	assertEquals(0, test.size());
	assertEquals(true, test.isEmpty());
	
	test.put("One", 1);
	assertEquals(1, test.size());
	
	test.put("One", -1);
	assertEquals(1, test.size());
    }
    
    @Test
    void testGet() {
	MyHashMap<String, Integer> test = new MyHashMap<String,Integer>();
	
	test.put("One", 1);
	assertEquals(1, test.get("One"));
    }

    @Test
    void testEntryList() {
	MyHashMap<String, Integer> test = new MyHashMap<String,Integer>();
	
	test.put("One", 1);
	assertEquals(1, test.get("One"));
    }


    @Test
    void testIsEmpty() {
	MyHashMap<String, Integer> test = new MyHashMap<String,Integer>();
	assertEquals(true, test.isEmpty());
	
    }

    @Test
    void testRemove() {
	MyHashMap<String, Integer> test = new MyHashMap<String,Integer>();
	test.put("One", 1);
	test.put("Two", 2);
	assertEquals(2, test.size());
	test.remove("One");
	assertEquals(1, test.size());
	assertEquals(false, test.isEmpty(), "test is not empty!");   //tests empty
	assertEquals(false, test.containsKey("One"));
	assertEquals(false, test.containsValue(1));
	//How to check: removing a key not in the map does not decrease the size of the map.
    }

    @Test
    void testClear() {
	MyHashMap<String, Integer> test = new MyHashMap<String,Integer>();
	test.put("One", 1);
	test.put("Fifty", 50);
	test.clear();
	assertEquals(true, test.isEmpty());  //tests empty
	assertEquals(false, test.containsKey("Fifty"));
    }

    @Test
    void testContainsKey() {
	MyHashMap<String, Integer> test = new MyHashMap<String,Integer>();
	test.put("One", 1);
	test.put("Fifty", 50);
	
	assertEquals(true, test.containsKey("Fifty"));
	assertEquals(false, test.containsKey("Forty"));
    }

    @Test
    void testContainsValue() {
	MyHashMap<String, Integer> test = new MyHashMap<String,Integer>();
	test.put("One", 1);
	test.put("Fifty", 50);
	
	assertEquals(true, test.containsValue(1));
	assertEquals(false, test.containsValue(40));
    }

}
