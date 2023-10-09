/**
 * @author Moe Ariyoshi
 * Spring 2023
 * I have adhered to the Honor Code in this assignment. 
 */

package lab5;
import java.util.AbstractList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> extends AbstractList<T> {
	private Node head, tail;
	private int size;
	private int modCount;

	protected class Node {
		T data;
		Node next;
		Node prev;

		protected Node() {
			data = null;
			next = null;
			prev = null;
		}
	}
	
	class MyListIterator implements ListIterator<T> {

	    // Instance variables here
	    private MyLinkedList<T> list;
        private int index;
        private int iteratorModCount;
 
        
        MyListIterator(MyLinkedList<T> list, int modCount){
            this.list = list;
            this.index = 0;
            this.iteratorModCount = modCount;
        }
        
        /**
         * Checks to see if there is an element after the one now
         * @param index index stored in the iterator 
         * @param list list that the iterator is used in
         * @return boolean - true if there is a next element - false if there isn't 
         */
        
	    public boolean hasNext() {
	        check();
	        if (index >= list.size) {
	            return false;
            } else {
                return true;
            }
	    }

	    /**
         * Returns the next element 
         * @param index index stored in the iterator 
         * @param list list that the iterator is used in
         * @return the next element 
         */
	    
        @Override
        public T next() {
            check();
            if (index >= list.size) {
                System.out.println(index);
                throw new NoSuchElementException();
            }
            Node next = list.getNth(index);
            index++;
            return next.data;
        }
        
        /**
         * Checks to see if there is an element in the previous index
         * @param index stored in the iterator 
         * @return boolean - true if there is a next element - false if there isn't 
         */

        @Override
        public boolean hasPrevious() {
            check();
            if (index == 0) {
                return false;
            } else {
                return true;
            }
        }
        
        /**
         * Returns the previous element 
         * @param index index stored in the iterator 
         * @param list list that the iterator is used in
         * @return the next element 
         */
       

        @Override
        public T previous() {
            check();
            if (index == 0) {
                throw new NoSuchElementException();
            }
            Node prev = list.getNth(index).prev;
            index--;
            return prev.data;
            
        }

        @Override
        public int nextIndex() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public int previousIndex() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public void remove() {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void set(T e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void add(T e) {
            // TODO Auto-generated method stub
            
        }
        
        public void check() {
            if (iteratorModCount != list.modCount) {
                throw new ConcurrentModificationException();
            }
        }
        
//        //replaces itself with a new iterator if the modcounts do not match
//        public Iterator iteratorCheck() {
//            if (iteratorModCount != list.modCount) {
//                return this.list.iterator();
//            }
//            return this;
//        }
//        
//        public ListIterator listIteratorCheck() {
//            if (iteratorModCount != list.modCount) {
//                return this.list.listIterator();
//            }
//            return this;
//        }
//        
        

	}	

	public MyLinkedList() {
		head = new Node();
		tail = new Node();
		head.next = tail;
		tail.prev = head;
		size = 0;
		modCount = 0;
	}
	
	/**
     * Returns the node in the nth index
     * @param index of the linked list 
     * @return the nth node
     */
	
	//getNth returns a node 
	private Node getNth(int index) {
	    // head 0 1 2 3 4 5 6 7 8 tail
	    
	        Node current = this.head;
	        
	        for (int i = 0; i<=index; i++) {
	            current = current.next;
	        }
	        
	        return current;
	 
	}
	
	/**
     * Removes node n from the list
     * @param n node to be removed
     * @return void 
     */
	
	private void removeNode(Node n) {
	    if (n == this.head) {
	        this.head = n.next;
	        n.next.prev = null;
	    } else if ( n == this.tail ) {
	        this.tail = n.prev;
	        this.tail.next = null;
	    } else {
	        n.prev.next = n.next;
	        n.next.prev = n.prev; 
	    }
	}
	
	/**
     * getter for the size 
     * @return size 
     */
	
    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    /**
     * a function that calls getNth
     * @param index of the node we want  
     */
    
    @Override
    public T get(int index) {
        if (index >= size | index <0) {
            throw new IndexOutOfBoundsException();
        } else if (size == 0) {
            throw new NoSuchElementException();
        }
        return getNth(index).data;
    }
    
    /**
     * adds a node to the list
     * @param index of where we want to add
     * @param data of the new node
     */
    
    public void add(int index, T data) {
        if (data == null) {
            throw new NullPointerException();
        } else if (index > size | index < 0 ) {
            throw new IndexOutOfBoundsException();
        } else {
            
            Node newNode = new Node();
            newNode.data = data;
            
            if (this.size == 0) {
                
                this.head.next = newNode;
                this.tail.prev = newNode;
                newNode.prev = this.head;
                newNode.next = this.tail;
                
            } else {
                
                Node oldAtIndex = getNth(index);
                Node prevNode = getNth(index).prev;
                
                prevNode.next = newNode;
                newNode.next = oldAtIndex;
                oldAtIndex.prev = newNode;
                newNode.prev = prevNode;
           }
            size++;
            modCount++;
        } 
    }
    
    /**
     * adds a node to the end of the list
     * @param data of the new node
     */
    
    public boolean add(T data) {
        add(size, data);
        return true;
    }
    
    /**
     * sets the data of the node at index to data 
     * @param index of where we want to add
     * @param data of the new node
     * @return old data 
     */
    
    
    public T set(int index, T data) {
        if (data == null) {
            throw new NullPointerException();
            
        } else if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        
        T oldData = get(index);
        getNth(index).data = data;
        
        modCount++;
        
        return oldData; 
    }
    
    /**
     * removed the node at index from list 
     * @param index of node to remove
     * @return data of removed node
     */
    
    
    public T remove(int index) {
        
        if (index < 0 | index >= size) {
            throw new IndexOutOfBoundsException();
        }
        
        Node removed = getNth(index);
        removeNode(removed);
        
        modCount++;
        size--;
        return removed.data;
        
    }
    
    /**
     * delete all nodes from list 
     */
    
    public void clear() {
        head.next = tail;
        tail.prev = head;
        size = 0;
        modCount++;
    }
    
    /**
     * checks if the list is empty of not 
     * @return boolean
     */
    
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    //constructor for listIterator()
    public MyListIterator listIterator(){
        return new MyListIterator(this, this.modCount);
    }
    
    //constructor for iterator()
    public Iterator<T> iterator(){
        return new MyListIterator(this, this.modCount);
    }
    
}
