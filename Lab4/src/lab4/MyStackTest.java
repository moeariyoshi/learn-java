package lab4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MyStackTest {
    
    @Test
    void testPush() {
        System.out.println("Testing Push");
        MyStack<Integer> test1 = new MyStack<Integer>();
        test1.push(1);
        test1.push(2);
        assertEquals(2, test1.size(), "Successfully added!");
        
    }

    @Test
    void testPop() {
        MyStack<Integer> test2 = new MyStack<Integer>();
        test2.push(1);
        test2.push(2);
        test2.push(3);
        assertEquals(3, test2.pop(), "What you popped is not 3!");
        assertEquals(2, test2.size(), "The size is wrong after popping!");
    }

    @Test
    void testTop() {
        MyStack<Integer> test = new MyStack<Integer>();
        test.push(1);
        test.push(2);
        assertEquals(2, test.top(), "Top is not what it should be");
    }

    @Test
    void testSize() {
        MyStack<Integer> test = new MyStack<Integer>();
        test.push(1);
        test.push(2);
        assertEquals(2, test.size(), "The size is wrong!");
    }

    @Test
    void testIsEmpty() {
        MyStack<Integer> test = new MyStack<Integer>();
        assertEquals(true, test.isEmpty(), "List is empty!");
        test.push(1);
        assertEquals(false, test.isEmpty(), "List is no longer empty!");
    }

    @Test
    void testClear() {
        System.out.println("Testing Clear");
        MyStack<Integer> test = new MyStack<Integer>();
        test.push(1);
        test.push(2);
        assertEquals(false, test.isEmpty(), "List is not empty!");
        test.clear();
        assertEquals(true, test.isEmpty(), "List should be empty!");
    }

}
