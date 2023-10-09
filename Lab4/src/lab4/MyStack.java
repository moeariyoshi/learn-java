/**
 * @author Moe Ariyoshi
 * Spring 2023
 */

package lab4;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MyStack<T> implements StackADT<T> {
    
    private ArrayList<T> data;
    
    MyStack(){
        this.data = new ArrayList<T>();
    }
    
    @Override
    public void push(T item) {
       this.data.add(0,item);
    }

    @Override
    public T pop() throws NoSuchElementException {
        if (this.data.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return this.data.remove(0);
        }
    }

    @Override
    public T top() throws NoSuchElementException {
        return this.data.get(0);
    }

    @Override
    public int size() {
        return this.data.size();
    }

    @Override
    public boolean isEmpty() {
        return this.data.isEmpty();
    }

    @Override
    public void clear() {
       this.data.clear();
    }

}
