 /**
 * @author Moe Ariyoshi
 * I have adhered to the Honor Code in this assignment.
 * Spring 2023
 */

package trees;

import java.util.List;
import java.util.NoSuchElementException;

public class EmptyTree<T> extends BinaryTree<T> {
    
    /**
     * Returns the data of the node
     * @return the data of the node
     */
    @Override
    public T data() {
        throw new NoSuchElementException();
    }

    /**
     * @return the leftChild of the node
     */
    @Override
    public BinaryTree<T> leftChild() {
        throw new NoSuchElementException();
    }

    /**
     * @return the rightChild of the node
     */
    @Override
    public BinaryTree<T> rightChild() {
        throw new NoSuchElementException();
    }

    /**
     * Boolean if the BinaryTree has some type of data (and children)
     * @return false for ConsTree that has data; true for EmptyTree
     */
    @Override
    public boolean isEmpty() {
        return true;
    }

    /**
     * @return the height of the tree
     */
    @Override
    public int height() {
        return -1;
    }

    /**
     * Calculates and returns the length of the longest path from one node to another without repeating nodes
     * @return the length of the longest path 
     */
    @Override
    public int diameter() {
        return -1;
    }

    /**
     * Counts number of node in the tree
     * @return the number of ConsTree nodes in the tree
     */
    @Override
    public int nodeCount() {
        return 0;
    }

    /**
     * Counts the number of leaves in the tree
     * @return the number of ConsTree nodes with no children
     */
    @Override
    public int leafCount() {
        return 0;
    }

    /**
     * Counts the number of ConsTree nodes on a level
     * @param level the level in the tree to count the nodes on
     * @return the number of ConsTree nodes on the level
     */
    @Override
    public int levelCount(int level) {
        return 0;
    }

    /**
     * Reverses rightChildren and leftChildren for all nodes
     * @return A BinaryTree that is a mirror image 
     */
    @Override
    public BinaryTree<T> mirrorImage() {
        return new EmptyTree();
    }

    /**
     * Removes children if there is only one to a parent
     * @return A full BinaryTree  (0 or 2 children)
     */
    @Override
    public BinaryTree<T> pare() {
        return new EmptyTree();
    }

    /**
     * Removes all children
     * @return A ConsTree with no children (1 node) 
     */
    @Override
    public BinaryTree<T> cutLeaves() {
        return new EmptyTree();
    }

    /**
     * @return The maximum of the absolute value of the difference of the number of nodes in the left and right tree
     */
    @Override
    public int weightBalanceFactor() {
        return 0;
    }

    /**
     * lists in preOrder
     * @param list a list 
     */
    @Override
    public void preOrderElements(List<T> list) {
   
    }

    /**
     * lists in postOrder
     * @param list a list 
     */
    @Override
    public void postOrderElements(List<T> list) {
    }

    /**
     * lists inOrder
     * @param list a list 
     */
    @Override
    public void inOrderElements(List<T> list) {
    }
}
