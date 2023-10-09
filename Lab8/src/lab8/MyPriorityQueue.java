/**
 * Implementation of Priority Queues - min heap!!
 *
 * @author Benjamin Kuperman (Spring 2011), Cynthia Taylor (Summer 2021)
 * @author Moe Ariysoshi
 * Spring 2023
 */
package lab8;

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class MyPriorityQueue<AnyType> extends AbstractQueue<AnyType> {

    /** Item to use for making comparisons */
    private Comparator<? super AnyType> cmp;

    /** ArrayList for the heap itself */
    private ArrayList<AnyType> heap;
   
    
    /**
     * Construct a heap with a given comparator.
     * 
     * @param cmp Comparator to use for ordering nodes
     */
    public MyPriorityQueue(Comparator<? super AnyType> comparator) {
        cmp = comparator;
        heap = new ArrayList<AnyType>();
    }

    //getter for the comparator 
    public Comparator<? super AnyType> comparator() {
        return this.cmp;
    }

    
    public int size() {
        return heap.size(); //caused errors when used the ArrayList size()
    }

    public void clear() {
        heap.clear();
    }

    /**
     * Adds to the end (bottom) so that the tree is complete 
     * @param o the object to add 
     * @return true if it's added! 
     */
    public boolean offer(AnyType o) {
        heap.add(o);
        percolateUp(size()-1);
        return true;
    }

    /**
     * get's the first (smallest) item in the min-heap
     * that is at the root!
     * @return the root! 
     */
    public AnyType peek() {
        return this.heap.get(0);
    }

    /**
     * removes the smallest item from the heap and returns it! 
     * @return
     */
    public AnyType poll() {
        AnyType removed = this.heap.remove(0);
        
        if (size() == 0) {
            return removed;
        }
        
        //the node at the end (right most node) gets moved to the top
        AnyType newTop = this.heap.remove(size()-1);
        this.heap.add(0,newTop);
        percolateDown(0);
        return removed;

    }

    
    public Iterator<AnyType> iterator() {
        return heap.iterator();
    }
    
    /**
     * Switches the value in hole and the value in switchIndex 
     * @param hole main value 
     * @param switchIndex the index of the value to switch positions with
     * @param toPercolate the value in index hole 
     */
    public void percolate(int hole, int switchIndex, AnyType toPercolate) {
        
    }

    //Put biggest number at the top 
    //switch with smaller of the two children
    private void percolateDown(int hole) {

        AnyType toPercolate = heap.get(hole);
        int leftIndex = lchild(hole);
        int rightIndex = rchild(hole);
        int switchIndex = leftIndex;
        
        if (switchIndex != -1) {
            //compare which of the child is smaller 
            if (rightIndex != -1) {
                if (cmp.compare(heap.get(rightIndex), heap.get(leftIndex)) < 0) {
                    switchIndex = rightIndex;
                }
            }
            
            if (cmp.compare(heap.get(switchIndex), toPercolate)<0) {
                AnyType oldValue = this.heap.set(switchIndex, toPercolate);
                heap.set(hole, oldValue);
                percolateDown(switchIndex);
            }
        //No children!   
        } else {
            return;
        }
    }

    private void percolateUp(int hole) {
        
        //cannot percolate up if it's the root!
        if (hole == 0) {
            return;
        }

        AnyType toPercolate = heap.get(hole);
        int parentIndex = parent(hole);
        
        //if child (toPercolate) is smaller, swap!
        if (cmp.compare(heap.get(parentIndex), heap.get(hole))>0) {
            AnyType oldValue = heap.set(parentIndex, toPercolate);
            heap.set(hole, oldValue);
            percolateUp(parentIndex);
        }
        
    }

    /**
     * Calculate the parent index of a node in a complete binary tree
     * 
     * @param index node to find parent index of
     * @return index of parent or -1 if there is no parent
     */
    private int parent(int index) {
        if (index == 0) {
            return -1;
        }
        int parentIndex = (index-1)/2;
        return parentIndex;
    }

    /**
     * Calculate the index for the left child of the given index in a complete
     * binary tree.
     * 
     * @param index node to find left child of
     * @return index of left child or -1 if there is no left child
     */
    private int lchild(int index) {
        int lchildIndex = index*2+1;
        if (lchildIndex >= size()) {
            return -1;
        }
        return lchildIndex;
    }

    /**
     * Calculate the index for the right child of the given index in a complete
     * binary tree.
     * 
     * @param index node to find right child of
     * @return index of right child or -1 if there is no right child
     */
    private int rchild(int index) {
        int rchildIndex = index*2+2;
        if (rchildIndex >= size()) {
            return -1;
        }
        return rchildIndex;

    }

}
