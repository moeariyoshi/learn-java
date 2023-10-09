package lab4;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class MyQueueTest {

    @Test
    void testEnqueue() {
        MyQueue<Integer> test = new MyQueue<Integer>();
        test.enqueue(1);
        test.enqueue(2);
        assertEquals(false, test.isEmpty(), "The queue is not empty!");
        assertEquals(1, test.front(), "Front is 1");
    }

    @Test
    void testDequeue() {
        MyQueue<Integer> test2 = new MyQueue<Integer>();
        test2.enqueue(1);
        test2.enqueue(2);
        test2.enqueue(3);
        assertEquals(3, test2.size(), "The size is 3!");
        test2.dequeue();
        assertEquals(2, test2.size(), "The size is now 2!");
        test2.dequeue();
        test2.dequeue(); //Queue is empty 
        assertEquals(0, test2.size(), "The size is 0!");
        assertThrows(NoSuchElementException.class, () -> test2.dequeue());
        
        
    }

    @Test
    void testFront() {
        System.out.println("Testing Front");
        MyQueue<Integer> test = new MyQueue<Integer>();
        assertThrows(NoSuchElementException.class, () -> test.front());
        test.enqueue(1);
        assertEquals(1, test.front(), "The front is 1!");
        
    }

    @Test
    void testSize() {
        System.out.println("Testing Size");
        MyQueue<Integer> test = new MyQueue<Integer>();
        test.enqueue(1);
        test.enqueue(2);
        test.enqueue(3);
        assertEquals(3, test.size(), "The size is 3!");
    }

    @Test
    void testIsEmpty() {
        System.out.println("Testing IsEmpty");
        MyQueue<Integer> test = new MyQueue<Integer>();
        assertEquals(true, test.isEmpty(), "The queue is empty!");
        test.enqueue(1);
        test.enqueue(2);
        assertEquals(false, test.isEmpty(), "The queue is not empty!");
    }

    @Test
    void testClear() {
        System.out.println("Testing IsEmpty");
        MyQueue<Integer> test = new MyQueue<Integer>();
        assertEquals(true, test.isEmpty(), "The queue is empty!");
        test.enqueue(1);
        test.enqueue(2);
        assertEquals(false, test.isEmpty(), "The queue is not empty!");
        test.clear();
        assertEquals(true, test.isEmpty(), "The queue is empty!");
        
    }

}
