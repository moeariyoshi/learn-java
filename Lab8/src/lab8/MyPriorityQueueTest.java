package lab8;

import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

class MyPriorityQueueTest {
    
    MyPriorityQueue<String> createTest(){
        Comparator<String> cmp = Comparator.naturalOrder();
        MyPriorityQueue<String> test = new MyPriorityQueue<String>(cmp);
        return test;
    }
    

    @Test
    void testOffer() {
        MyPriorityQueue<String> test = createTest();
        test.offer("Banana");
        assertEquals(1, test.size());
        assertEquals("Banana", test.peek());
        
        test.offer("Apple");
        assertEquals(2, test.size());
        //"Apple" that was added percolated up properly!
        assertEquals("Apple", test.peek());
    }

    @Test
    void testPeek() {
        MyPriorityQueue<String> test = createTest();
        test.offer("Banana");
        assertEquals("Banana", test.peek());
        //peek should not delete anything
        assertEquals(1, test.size());
    }

    @Test
    void testPoll() {
        MyPriorityQueue<String> test = createTest();
        test.offer("Banana");
        assertEquals(1, test.size());
        assertEquals("Banana", test.peek());
        
        test.offer("Apple");
        test.add("Citrus");
        
        test.poll();
        assertEquals(2, test.size());
        //"Citrus" that was added last Percolated down properly
        assertEquals("Banana", test.peek());
    }
    

}
