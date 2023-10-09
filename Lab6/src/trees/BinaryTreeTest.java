 /**
 * @author Moe Ariyoshi
 * I have adhered to the Honor Code in this assignment.
 * Spring 2023
 */

package trees;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class BinaryTreeTest {

    @Test
    void testData() {
        BinaryTree<Integer> test = new ConsTree<Integer>(5);
        assertEquals(5, test.data());
        
        BinaryTree<Integer> empty = new EmptyTree<Integer>();
        assertThrows(NoSuchElementException.class, () -> empty.data()); 
    }

    @Test
    void testLeftChild() {
        BinaryTree<Integer> leftChild = new ConsTree<Integer>(3, new EmptyTree<Integer>(), new EmptyTree<Integer>());
        BinaryTree<Integer> rightChild = new ConsTree<Integer>(7, new EmptyTree<Integer>(), new EmptyTree<Integer>());
        BinaryTree<Integer> test = new ConsTree<Integer>(5, leftChild, rightChild);
        assertEquals(leftChild, test.leftChild());
        
        BinaryTree<Integer> empty = new EmptyTree<Integer>();
        assertThrows(NoSuchElementException.class, () -> empty.leftChild());
    }

    @Test
    void testRightChild() {
        ConsTree<Integer> leftChild = new ConsTree<Integer>(3, new EmptyTree<Integer>(), new EmptyTree<Integer>());
        ConsTree<Integer> rightChild = new ConsTree<Integer>(7, new EmptyTree<Integer>(), new EmptyTree<Integer>());
        BinaryTree<Integer> test = new ConsTree<Integer>(5, leftChild, rightChild);
        assertEquals(rightChild, test.rightChild());
        
        
        BinaryTree<Integer> empty = new EmptyTree<Integer>();
        assertThrows(NoSuchElementException.class, () -> empty.rightChild());
    }

    @Test
    void testIsEmpty() {
        BinaryTree<Integer> test = new ConsTree<Integer>(5);
        assertEquals(false, test.isEmpty());
        
        BinaryTree<Integer> empty = new EmptyTree<Integer>();
        assertEquals(true, empty.isEmpty());
    }

    @Test
    void testHeight() {
        ConsTree<Integer> leftChild = new ConsTree<Integer>(3, new EmptyTree<Integer>(), new EmptyTree<Integer>());
        ConsTree<Integer> rightChild = new ConsTree<Integer>(7, new EmptyTree<Integer>(), new EmptyTree<Integer>());
        BinaryTree<Integer> test = new ConsTree<Integer>(5, leftChild, rightChild);
        assertEquals(1, test.height());
        
        BinaryTree<Integer> empty = new EmptyTree<Integer>();
        assertEquals(-1, empty.height());
        
    }

    @Test
    void testDiameter() {
        BinaryTree<Integer> leftChild = new ConsTree<Integer>(3, new EmptyTree<Integer>(), new EmptyTree<Integer>());
        BinaryTree<Integer> rightChild = new ConsTree<Integer>(7, new EmptyTree<Integer>(), new EmptyTree<Integer>());
        BinaryTree<Integer> test = new ConsTree<Integer>(5, leftChild, rightChild);
        
        assertEquals(2, test.diameter());
        
        BinaryTree<Integer> empty = new EmptyTree<Integer>();
        assertEquals(-1, empty.diameter());
    }

    @Test
    void testNodeCount() {
        BinaryTree<Integer> leftChild = new ConsTree<Integer>(3, new EmptyTree<Integer>(), new EmptyTree<Integer>());
        BinaryTree<Integer> rightChild = new ConsTree<Integer>(7, new EmptyTree<Integer>(), new EmptyTree<Integer>());
        BinaryTree<Integer> test = new ConsTree<Integer>(5, leftChild, rightChild);
        
        assertEquals(3, test.nodeCount());
        
        BinaryTree<Integer> empty = new EmptyTree<Integer>();
        assertEquals(0, empty.nodeCount());
        
    }

    @Test
    void testLeafCount() {
        BinaryTree<Integer> leftChild = new ConsTree<Integer>(3, new EmptyTree<Integer>(), new EmptyTree<Integer>());
        BinaryTree<Integer> rightChild = new ConsTree<Integer>(7, new EmptyTree<Integer>(), new EmptyTree<Integer>());
        BinaryTree<Integer> test = new ConsTree<Integer>(5, leftChild, rightChild);
        
        assertEquals(2, test.leafCount());
        
        BinaryTree<Integer> empty = new EmptyTree<Integer>();
        assertEquals(0, empty.leafCount());
    }

    @Test
    void testLevelCount() {
        BinaryTree<Integer> leftChild = new ConsTree<Integer>(3, new EmptyTree<Integer>(), new EmptyTree<Integer>());
        BinaryTree<Integer> rightChild = new ConsTree<Integer>(7, new EmptyTree<Integer>(), new EmptyTree<Integer>());
        BinaryTree<Integer> test = new ConsTree<Integer>(5, leftChild, rightChild);
        
        assertEquals(2, test.levelCount(1));
        assertThrows(IndexOutOfBoundsException.class, () -> test.levelCount(2));
        
        BinaryTree<Integer> empty = new EmptyTree<Integer>();
        assertEquals(0, empty.levelCount(0));
    }

    @Test
    void testMirrorImage() {
        BinaryTree<Integer> leftChild = new ConsTree<Integer>(3, new EmptyTree<Integer>(), new EmptyTree<Integer>());
        BinaryTree<Integer> rightChild = new ConsTree<Integer>(7, new EmptyTree<Integer>(), new EmptyTree<Integer>());
        BinaryTree<Integer> test = new ConsTree<Integer>(5, leftChild, rightChild);
        
        test = test.mirrorImage();
        
        assertEquals(rightChild.data(), test.leftChild().data());
    }

    @Test
    void testPare() {
        BinaryTree<Integer> leftChild = new ConsTree<Integer>(3, new EmptyTree<Integer>(), new EmptyTree<Integer>());
        BinaryTree<Integer> rightChild = new EmptyTree<Integer>();
        BinaryTree<Integer> test = new ConsTree<Integer>(5, leftChild, rightChild);
        
        test = test.pare();
        
        assertEquals(true, test.leftChild() instanceof EmptyTree);
    }

    @Test
    void testCutLeaves() {
        BinaryTree<Integer> leftChild = new ConsTree<Integer>(3, new EmptyTree<Integer>(), new EmptyTree<Integer>());
        BinaryTree<Integer> rightChild = new ConsTree<Integer>(7, new EmptyTree<Integer>(), new EmptyTree<Integer>());
        BinaryTree<Integer> test = new ConsTree<Integer>(5, leftChild, rightChild);
        
        test = test.cutLeaves();
        
        assertEquals(true, test.leftChild() instanceof EmptyTree);
        assertEquals(true, test.rightChild() instanceof EmptyTree);
        assertEquals(1, test.nodeCount());
        
    }

    @Test
    void testWeightBalanceFactor() {
        /**
         *      O
         *     / \
         *    c   c
         *   / \ / \
         *  c  e e  e
         */ 
         
        BinaryTree<Integer> leftLeftChild = new ConsTree<Integer>(3, new EmptyTree<Integer>(), new EmptyTree<Integer>());
        BinaryTree<Integer> leftRightChild = new EmptyTree<Integer>();
        BinaryTree<Integer> rightLeftChild = new EmptyTree<Integer>();
        BinaryTree<Integer> rightRightChild = new ConsTree<Integer>(7, new EmptyTree<Integer>(), new EmptyTree<Integer>());
        BinaryTree<Integer> leftChild = new ConsTree<Integer>(3, leftLeftChild, leftRightChild);
        BinaryTree<Integer> rightChild = new ConsTree<Integer>(7, rightLeftChild, rightRightChild);
        BinaryTree<Integer> test = new ConsTree<Integer>(5, leftChild, rightChild);
        
        
        assertEquals(1, test.weightBalanceFactor());
        
    }

    @Test
    void testPreOrderElements() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        BinaryTree<Integer> leftChild = new ConsTree<Integer>(3, new EmptyTree<Integer>(), new EmptyTree<Integer>());
        BinaryTree<Integer> rightChild = new ConsTree<Integer>(7, new EmptyTree<Integer>(), new EmptyTree<Integer>());
        BinaryTree<Integer> test = new ConsTree<Integer>(5, leftChild, rightChild);
        
        test.preOrderElements(list);
        
        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(5);
        expected.add(3);
        expected.add(7);
        
        for (int i = 0; i < list.size(); i++) {
           assertEquals(expected.get(i), list.get(i));
        }
        
    }

    @Test
    void testPostOrderElements() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        BinaryTree<Integer> leftChild = new ConsTree<Integer>(3, new EmptyTree<Integer>(), new EmptyTree<Integer>());
        BinaryTree<Integer> rightChild = new ConsTree<Integer>(7, new EmptyTree<Integer>(), new EmptyTree<Integer>());
        BinaryTree<Integer> test = new ConsTree<Integer>(5, leftChild, rightChild);
        
        test.postOrderElements(list);
        
        ArrayList<Integer> expected = new ArrayList<Integer>();
        
        expected.add(3);
        expected.add(7);
        expected.add(5);
        
        for (int i = 0; i < list.size(); i++) {
           assertEquals(expected.get(i), list.get(i));
        }
        
    }

    @Test
    void testInOrderElements() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        BinaryTree<Integer> leftChild = new ConsTree<Integer>(3, new EmptyTree<Integer>(), new EmptyTree<Integer>());
        BinaryTree<Integer> rightChild = new ConsTree<Integer>(7, new EmptyTree<Integer>(), new EmptyTree<Integer>());
        BinaryTree<Integer> test = new ConsTree<Integer>(5, leftChild, rightChild);
        
        test.inOrderElements(list);
        
        ArrayList<Integer> expected = new ArrayList<Integer>();
        
        expected.add(3);
        expected.add(5);
        expected.add(7);
        
        for (int i = 0; i < list.size(); i++) {
           assertEquals(expected.get(i), list.get(i));
        }
    }

}
