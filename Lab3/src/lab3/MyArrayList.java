/**
 *I have adhered to the Honor Code in this assignment.
 *
 * @author Moe Ariyoshi
 * Spring 2023
 */

package lab3;

import java.util.AbstractList;
import java.util.Collection;

public class MyArrayList<E> extends AbstractList<E> {
    private int size;
    private E[] data;
   
    @SuppressWarnings("unchecked")
    MyArrayList(int startSize) {
	this.data = (E [])new Object[startSize];
	this.size = 0;
    }
    
    MyArrayList() {
	this(2);
    }
    

    @Override
    public int size() {
	return this.size;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public E get(int index) {
	if (index >= size) {
	    throw new IndexOutOfBoundsException("Index Out of Bounds! You tried to get " +
		    index + " but the size is " + this.size );
	}
	return this.data[index];
    }
    
    @SuppressWarnings("unchecked")
    //private - cannot be used by the test methods?
    private void resize() {
	int oldSize = this.data.length;
	//System.out.println(oldSize);
	int newSize = oldSize * 2;
	E[] newArray = (E [])new Object[newSize];
	for (int i = 0; i < this.data.length; i++) {
	    newArray[i] = this.data[i];
	}
	this.data =  newArray;
	//System.out.println(this.data.length);
    }
    
    //adds at the index given 
    public void add(int index, E element){
	if (index > size) {
	    throw new IndexOutOfBoundsException("Index Out of Bounds! You tried to get " + index + " but the size is " + this.size );
	}
		    
	if (this.size >= this.data.length) {
	    this.resize();
	} 
	
	for (int i = this.size; i > index; i--) {
	    this.data[i] = this.data[i-1];
	}
	
	this.data[index] = element;
	this.size++;
	
	
    }
    
    //adds to the end of list and returns true if added!
    public boolean add(E element) {
	this.add(this.size,element);
	//don't do size++;
	//it's already in this.add(index, element);
	return true;
    }
    
    public E set(int index, E element) {
	if (index >= size) {
	    throw new IndexOutOfBoundsException("Index Out of Bounds! You can't set at this index!");
	} else {
	    E oldValue = this.get(index);
	    this.data[index] = element;
	    return oldValue;
	}
    }
    
    public E remove(int index) {
	E removed = this.get(index);
	
	for (int i = index; i < size - 1; i++) {
	   this.set(i, this.get(i+1));
	}
	
	this.set(size -1, null);
	this.size--;
	return removed;
    }
    
    public boolean isEmpty() {
	if (size == 0) {
	    return true;
	} else {
	    return false;
	}
    }
    
    public void clear() {
	for (int i = 0; i < size; i++) {
	    this.set(i, null);
	}
	this.size = 0;
    }
 

}
