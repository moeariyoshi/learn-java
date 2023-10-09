/**
 * @author Moe Ariyoshi
 * Spring 2023
 */

package lab4;

import java.util.NoSuchElementException;

import warmup4.LinkedList;

public class MyQueue<T> implements QueueADT<T> {
    
    private LinkedList<T> data;
    
    //MyQueue constructor 
    public MyQueue() {
       this.data = new LinkedList<T>();
    }

    @Override
    public void enqueue(T item) {
        this.data.addLast(item);
    }

    @Override
    public T dequeue() throws NoSuchElementException {
        if (this.data.isEmpty()) {
            throw new NoSuchElementException();
        } else {
           return this.data.removeFirst();
        }
    }

    @Override
    public T front() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        //Added peekFirst method in the warm up Linked List 
        return this.data.peekFirst();
    }

    @Override
    public int size() {
        return this.data.size();
    }

    @Override
    public boolean isEmpty() {
       if (this.size() == 0) {
           return true;
       } else {
           return false;
       }
    }

    @Override
    public void clear() {
        while (!this.isEmpty()) {
            this.dequeue();
         }
    }
    
}
