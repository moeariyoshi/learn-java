package goodreads;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MyTreeMapTest {
    
    MyTreeMap<Integer,String> createTestTree(){
        
        /**              10,"Ten"
         *                /   \
         *          5, "Five"  15, "Fifteen"
         *              / \
         *     3, "Three"  8,"Eight" 
         *           / \
         *    2, "Two"  4, "Four"
         */
        
        //Test Tree
        /**              "Ten"- 10
         *                /   \
         *        "Five" - 5  "Fifteen" - 15
         */
        
        MyTreeMap<Integer, String> testLeft = new MyTreeMap<Integer,String>(5, "Five");
        MyTreeMap<Integer, String> testRight = new MyTreeMap<Integer, String>(15, "Fifteen");
        MyTreeMap<Integer, String> test = new MyTreeMap<Integer, String>(10, "Ten", testLeft, testRight);
       
        return test;
        
    }

    @Test
    void testPutKV() {
        MyTreeMap<Integer, String> test = createTestTree();
        test.put(3, "Three");
        
        /**              10 "Ten"
         *                /   \
         *          5 "Five"  15 "Fifteen"
         *              / 
         *        3 "Three"
         */
        
        assertEquals(3, test.left.left.key);
        assertEquals("Three", test.left.left.value);
        
        
        
        test.put(2, "Two");
        //Left-left -> single rotation to the right 
        
        /**              10 "Ten"
         *                /   \
         *     !!!  5 "Five"  15 "Fifteen"
         *              / 
         *        3 "Three"
         *           /
         *       2 "Two"
         */
        
        /**              10 "Ten"
         *                /   \
         *          3 "Three"  15 "Fifteen"
         *              / \
         *        2 "Two" 5 "Five"
         */
        
        assertEquals(5, test.left.right.key);
        
        test.put(3, "San");
        
        /**              10 "Ten"
         *                /   \
         *          3 "San"  15 "Fifteen"
         *              / \
         *        2 "Two" 5 "Five"
         */
        
        //Tests if the value got replaced when key existed
        //Tests the get method as well
        assertEquals("San", test.get(3));
        //Makes sure that it is the same node as the previously "Three"
        assertEquals("San", test.left.value);
        
        test.put(8, "Eight");
        //left-right -> double rotation (left+right)
        
        /**              10 "Ten"   UNBALANCED!
         *                /   \
         *          3 "San"  15 "Fifteen"
         *              / \
         *        2 "Two" 5 "Five"
         *                 \
         *                 8 "Eight"
         */
        
        
        /**              10 "Ten"
         *                /   \
         *           5 "Five"  15 "Fifteen"
         *              /   \
         *          3 "San"  8 "Eight"
         *              / 
         *        2 "Two"             
         */
        
        /**                         
         *               5 "Five"  
         *              /        \
         *          3 "San"    10 "Ten" 
         *              /     /        \
         *        2 "Two" 8 "Eight"  15 "Fifteen"        
         */
        
        assertEquals(10, test.right.key);
        assertEquals(8, test.right.left.key);
        assertEquals(15, test.right.right.key);
        
        test.put(20, "Twenty");
        test.put(25, "Twenty-Five");
        //Right-right: single rotation to the left 
        
        /**                         
         *               5 "Five"  
         *              /        \
         *          3 "San"    10 "Ten" 
         *              /     /        \
         *        2 "Two" 8 "Eight"  15 "Fifteen"  UNBALANCED!
         *                              \
         *                              20 "Twenty" 
         *                                  \
         *                                   25 "Twenty-Five"   
         */
        
        /**                         
         *               5 "Five"  
         *              /        \
         *          3 "San"    10 "Ten" 
         *              /     /        \
         *        2 "Two" 8 "Eight"   20 "Twenty"     
         *                           /   \
         *                  15 "Fifteen"  25 "Twenty-Five" 
         *                                 
         */
        
        assertEquals(20, test.right.right.key);
        assertEquals(15, test.right.right.left.key);
        
        test.put(13, "Thirteen");
        //Right-left: double rotation: right+left 
        
        /**                         
         *               5 "Five"  
         *              /        \
         *          3 "San"    10 "Ten"   UNBALANCED!!!
         *              /     /        \
         *        2 "Two" 8 "Eight"   20 "Twenty"     
         *                           /   \
         *                  15 "Fifteen"  25 "Twenty-Five" 
         *                      /
         *                  13 "Thirteen"          
         */
        
        /**                         
         *               5 "Five"  
         *              /        \
         *          3 "San"    10 "Ten"   UNBALANCED!!!
         *              /     /        \
         *        2 "Two" 8 "Eight"   15 "Fifteen"     
         *                           /   \
         *                 13 "Thirteen" 20 "Twenty" 
         *                                 \
         *                                 25 "Twenty-Five"      
         */
        
        /**                         
         *               5 "Five"  
         *              /        \
         *          3 "San"    15 "Fifteen"   
         *              /     /        \
         *        2 "Two" 10 "Ten"   20 "Twenty"     
         *                /   \          \
         *          8 "Eight 13 "Thirteen" 25 "Twenty-Five" 
         *                                  
         */
        assertEquals(15, test.right.key);
        assertEquals(13, test.right.left.right.key);
    }

}
