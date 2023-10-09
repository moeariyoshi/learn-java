package trees;

import java.util.List;

/**
 * JavaDoc contained in this class!
 */
public abstract class BinaryTree<T> {
    
    /**
     * Returns the data of the node
     * @return the data of the node
     */
    public abstract T data();
    
    /**
     * @return the leftChild of the node
     */
    public abstract BinaryTree<T> leftChild();
    
    /**
     * @return the rightChild of the node
     */
    public abstract BinaryTree<T> rightChild();
    
    /**
     * Boolean if the BinaryTree has some type of data (and children)
     * @return false for ConsTree that has data; true for EmptyTree
     */
    public abstract boolean isEmpty();
    
    /**
     * @return the height of the tree
     */
    public abstract int height();
    
    /**
     * Calculates and returns the length of the longest path from one node to another without repeating nodes
     * @return the length of the longest path 
     */
    public abstract int diameter();
    
    /**
     * Counts number of node in the tree
     * @return the number of ConsTree nodes in the tree
     */
    public abstract int nodeCount();
    
    /**
     * Counts the number of leaves in the tree
     * @return the number of ConsTree nodes with no children
     */
    public abstract int leafCount();
    
    /**
     * Counts the number of ConsTree nodes on a level
     * @param level the level in the tree to count the nodes on
     * @return the number of ConsTree nodes on the level
     */
    public abstract int levelCount(int level);
    
    /**
     * Reverses rightChildren and leftChildren for all nodes
     * @return A BinaryTree that is a mirror image 
     */
    public abstract BinaryTree<T> mirrorImage();
    
    /**
     * Removes children if there is only one to a parent
     * @return A full BinaryTree  (0 or 2 children)
     */
    public abstract BinaryTree<T> pare();
    
    /**
     * Removes all children
     * @return A ConsTree with no children (1 node) 
     */
    public abstract BinaryTree<T> cutLeaves();
    
    /**
     * @return The maximum of the absolute value of the difference of the number of nodes in the left and right tree
     */
    public abstract int weightBalanceFactor();
    
    /**
     * lists in preOrder
     * @param list a list 
     */
    public abstract void preOrderElements(List<T> list);
    
    /**
     * lists in postOrder
     * @param list a list 
     */
    public abstract void postOrderElements(List<T> list);
    
    /**
     * lists inOrder
     * @param list a list 
     */
    public abstract void inOrderElements(List<T> list);
}
