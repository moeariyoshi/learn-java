/*
 *I have adhered to the Honor Code in this assignment.
 *
 * @author Moe Ariyoshi
 * Spring 2023
 */

package lab3;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MyArrayListTest {
    
    //Creates an array with 0-20
    public MyArrayList<Integer> createTestArray() {
	
	MyArrayList<Integer> test = new MyArrayList<Integer>(); 
	for (int i = 0; i <= 20; i++) {
	    test.add(i);
	}
	return test;
    }

    @Test
    void testSize() {
	MyArrayList<Integer> test1 = new MyArrayList<Integer>(); 
	assertEquals(0, test1.size(), "Size after construction");
	test1.add(0,5);
	assertEquals(1, test1.size(), "Size after add");
    }

//    @Test
//    void testAddAllCollectionOfQextendsE() {
//	fail("Not yet implemented");
//    }

//    @Test
//    void testGetInt() {
//	MyArrayList<Integer> test = new MyArrayList<Integer>(); 
//	for (int i = 0; i <= 20; i++) {
//	    test.add(i);
//	}
//	assertEquals("Get Value", test.get(1), 1);
//    }

//    @Test
//    void testAddIntE() {
//	fail("Not yet implemented");
//    }

    @Test
    void testAddE() {
	MyArrayList<Integer> test2 = createTestArray();
	for (int i = 0; i < test2.size(); i++) {
	    assertEquals(i, test2.get(i), "i is not always i");
	}
	
    }
    
    @Test
    void testAddIntEFront() {
	MyArrayList<Integer> test3 = new MyArrayList<Integer>(); 
	for (int i = 0; i <= 20; i++) {
	    test3.add(0, i);
	}
	for (int i = 0; i < test3.size(); i++) {
	    assertEquals(i, test3.get(20-i), "Value of data");
	}
    }
    
    @Test
    void testAddIntEMid() {
	MyArrayList<Integer> test4 = new MyArrayList<Integer>(); 
	for (int i = 0; i <= 20; i++) {
	    test4.add((test4.size())/2, i);
	}
	//1,3,5,7,9,11,13,15,17,19,20,18,16,14,12,10, 8, 6, 4, 2, 0
	for (int i = 0; i < 10; i++) {
	  //if index is 0-9: check if 2i+1 is equal 
	    assertEquals(2*i+1, test4.get(i), "Not odd in 0-9!");
	}
	
	for (int i = 11; i <= 20; i++) {
	  //if index is 10-20: check if 20-2(i-10)
	    assertEquals(20-2*(i-10), test4.get(i), "Not even in 10-20!");
	}
    }

    @Test
    void testAddOutOfBounds1() {
	MyArrayList<Integer> test5 = createTestArray();
	//skips one 
	assertThrows(IndexOutOfBoundsException.class, () -> test5.add(test5.size()+1, 22));
	
    }
    
    @Test
    void testAddOutOfBounds2() {
	MyArrayList<Integer> test6 = createTestArray();
	assertThrows(IndexOutOfBoundsException.class, () -> test6.add(-1, 8));
   	
       }
    
    @Test
    void testGetOutOfBounds1() {
	MyArrayList<Integer> test7 = createTestArray();
	assertThrows(IndexOutOfBoundsException.class, () -> test7.get(test7.size()));
	
    }
    
    @Test
    void testGetOutOfBounds2() {
	//creates an array of length 2
	MyArrayList<Integer> test8 = createTestArray();
	assertThrows(IndexOutOfBoundsException.class, () -> test8.get(-1));
	
    }
    
    @Test
    void testSet() {
	//creates an array with 0-20
	MyArrayList<Integer> test9 = createTestArray();
	for (int i = 0; i < 10; i++) {
	    
	    int oldValue = test9.set(i, test9.get(test9.size()-1-i));
	    test9.set(test9.size()-i-1, oldValue);
	}
	
	for (int i = 0; i<=20; i++) {
	    assertEquals(test9.get(20-i), i, "testSet!");
	}
	
	//Test index out of bounds 40 > 20
	//Not supposed to work!
	assertThrows(IndexOutOfBoundsException.class, () -> test9.set(40, 5));
	
    }
    
    @Test
    void testRemove() {
	MyArrayList<Integer> test10 = createTestArray();
	MyArrayList<Integer> evens = new MyArrayList<Integer>();
	for (int i = 0; i <= 10; i++) {
	    Integer removed = test10.remove(i);
	    evens.add(removed);
	}
	
	//checks for all odds in test array
	for (int i = 0; i<test10.size(); i++) {
	    assertEquals(2*i+1, test10.get(i), "Not all odds");
	}
	
	//checks for all evens in new array
	for (int i = 0; i<evens.size(); i++) {
	    assertEquals(2*i, evens.get(i), "Not all odds");
	} 
    }
    
    @Test
    void testIsEmpty() {
	MyArrayList<Integer> test11 = new MyArrayList<Integer>();
	test11.isEmpty();
	test11.add(0);
	test11.isEmpty();
	test11.remove(0);
	test11.isEmpty();
    }
    
    @Test
    void testClear() {
	MyArrayList<Integer> test12 = createTestArray();
	test12.isEmpty();
	test12.clear();
	test12.isEmpty();
    }
    
    

}
