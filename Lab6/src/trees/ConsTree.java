 /**
 * @author Moe Ariyoshi
 * I have adhered to the Honor Code in this assignment.
 * Spring 2023
 */

package trees;

import java.util.List;

public class ConsTree<T> extends BinaryTree<T> {
	private T data;
	private BinaryTree<T> leftChild;
	private BinaryTree<T> rightChild;

	public ConsTree(T data, BinaryTree<T> left, BinaryTree<T> right) {
		this.data = data;
		this.leftChild = left;
		this.rightChild = right;
	}

	
	public ConsTree(T data) {
		this(data, new EmptyTree<T>(), new EmptyTree<T>());
	}
	
	/**
     * Returns the data of the node
     * @return the data of the node
     */
    @Override
    public T data() {
        return this.data;
    }

    /**
     * @return the leftChild of the node
     */
    @Override
    public BinaryTree<T> leftChild() {
        return this.leftChild;
    }
    
    /**
     * @return the rightChild of the node
     */
    @Override
    public BinaryTree<T> rightChild() {
        return this.rightChild;
    }

    /**
     * Boolean if the BinaryTree has some type of data (and children)
     * @return false for ConsTree that has data; true for EmptyTree
     */
    public boolean isEmpty() {
        return false;
    }

    /**
     * @return the height of the tree
     */
    @Override
    public int height() {
        if (rightChild instanceof EmptyTree && leftChild instanceof EmptyTree) {
            return 0;
        }
        return 1 + Math.max(rightChild.height(), leftChild.height());
    }

    /**
     * Calculates and returns the length of the longest path from one node to another without repeating nodes
     * @return the length of the longest path 
     */
    @Override
    public int diameter() {
        
        int leftDiameter = leftChild.diameter();
        int rightDiameter = rightChild.diameter();
        int rootDiameter = leftChild.height() + rightChild.height() + 2;
        
        return Math.max(rootDiameter, Math.max(leftDiameter, rightDiameter));
        
    }

    /**
     * Counts number of node in the tree
     * @return the number of ConsTree nodes in the tree
     */
    @Override
    public int nodeCount() {        
        return rightChild.nodeCount() + leftChild.nodeCount() +1;
    }

    /**
     * Counts the number of leaves in the tree
     * @return the number of ConsTree nodes with no children
     */
    @Override
    public int leafCount() {
        int count = 0;
        
        if (rightChild instanceof EmptyTree && leftChild instanceof EmptyTree) {
            return 1;
        }
        
        count += rightChild.leafCount();
        count += leftChild.leafCount();
        
        return count;
        
    }
    
    /**
     * Counts the number of ConsTree nodes on a level
     * @param level the level in the tree to count the nodes on
     * @return the number of ConsTree nodes on the level
     */
    @Override
    public int levelCount(int level) {
        
        if (level > this.height()) {
            throw new IndexOutOfBoundsException();
        }
        
        int count = 0;
        
        if (level == 0) {
            count++;
            return count;
        }
        
        return rightChild.levelCount(level-1) + leftChild.levelCount(level-1);
        
        
    }
    
    /**
     * Reverses rightChildren and leftChildren for all nodes
     * @return A BinaryTree that is a mirror image 
     */
    @Override
    public BinaryTree<T> mirrorImage() {
        
        BinaryTree<T> newLeft = this.rightChild.mirrorImage();
        BinaryTree<T> newRight = this.leftChild.mirrorImage();
        
        return new ConsTree<T>(this.data, newLeft, newRight);
        
    }

    /**
     * Removes children if there is only one to a parent
     * @return A full BinaryTree  (0 or 2 children)
     */
    @Override
    public BinaryTree<T> pare() {
        if (rightChild instanceof EmptyTree | leftChild instanceof EmptyTree) {
            return new ConsTree<T>(data);
        } else {
            return new ConsTree<T>(data, leftChild.pare(), rightChild.pare());
        }
    }

    /**
     * Removes all children
     * @return A ConsTree with no children (1 node) 
     */
    @Override
    public BinaryTree<T> cutLeaves() {
        if (rightChild instanceof EmptyTree && leftChild instanceof EmptyTree) {
            return new EmptyTree<T>();
        }
        return new ConsTree<T>(data, leftChild.cutLeaves(), rightChild.cutLeaves());
    }

    /**
     * @return The maximum of the absolute value of the difference of the number of nodes in the left and right tree
     */
    @Override
    public int weightBalanceFactor() {
        
        
        int abs = Math.abs(leftChild.nodeCount()-rightChild.nodeCount());
        
        int childAbs = Math.max(rightChild.weightBalanceFactor(), leftChild.weightBalanceFactor());

        return Math.max(abs, childAbs);
    }

    /**
     * lists in preOrder
     * @param list a list 
     */
    @Override
    public void preOrderElements(List<T> list) {
        list.add(data);
        leftChild.preOrderElements(list);
        rightChild.preOrderElements(list);
    }

    /**
     * lists in postOrder
     * @param list a list 
     */
    @Override
    public void postOrderElements(List<T> list) {
        leftChild.postOrderElements(list);
        rightChild.postOrderElements(list);
        list.add(data);
    }

    /**
     * lists inOrder
     * @param list a list 
     */
    @Override
    public void inOrderElements(List<T> list) {
        leftChild.inOrderElements(list);
        list.add(data);
        rightChild.inOrderElements(list);
    }
}
