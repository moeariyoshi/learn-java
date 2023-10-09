/**
 * A binary search tree using AVL balancing.  It uses a structure similar to
 * that of Lab 5 whereby special null trees are used to represent children that
 * aren't present.
 *
 * @author John Donaldson (Fall 2007)
 * @author Benjamin Kuperman (Spring 2008, Spring 2011)
 * @author Alexa Sharp (Fall 2009)
 * @author TODO You!
 */

package goodreads;

// STUDENTS need to implement the following methods to complete the class:
//  - get()
//  - put()
//  - part of restructure()
//  - Complete the JavaDoc comments
//  - optionally add in a remove method
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Moe Ariyoshi
 * I have adhered to the Honor Code in this assignment.
 * Spring 2023
 */
public class MyTreeMap<K extends Comparable<? super K>,V> extends AbstractMap<K,V>  {

    /** The index by which information is stored */
    K key;
    /** Value associated with the key above */
    V value;
    /** The height of this subtree */
    int height;
    /** The children of this subtree -- if both null, this tree is empty */
    MyTreeMap<K,V> left,right;
    /** The number of nodes within this subtree (including self) */
    int size;

    // change this value to true and I'll print the rotations
    private boolean debug = false;

    //--------------------------------------------------------------------------
    // Things TODO - I've moved all the methods you need to change up to here
    //--------------------------------------------------------------------------

    /**
     * Finds the value of the Tree with the searchKey and returns null if not found
     * The public get value is the get that is actually called and then calls this get method
     * @param searchKey the key of the tree we are looking for
     * @return the value of the TreeMap with the given key 
     */
    private V get(K searchKey) {
        
        MyTreeMap<K,V> node = this;
        V toReturn = null;
        
        if (node.isEmpty()) {
            return null;
        }
        
        if (node.key.compareTo(searchKey) == 0) {
            toReturn = node.value;
        } else if (node.key.compareTo(searchKey)>0){
            toReturn = this.left.get(searchKey);
        } else if (node.key.compareTo(searchKey)<0) {
            toReturn = this.right.get(searchKey);
        }
        
        return toReturn;
    }

    /**
     * Inserts or updates a key-value
     * @param key
     * @param value
     * @return a value if the key has already existed
     */
    @Override
    public V put(K key, V value){
        // insert node or update value
        V oldValue;
        
        //Insert new node!
        if (this.isEmpty()) {
            this.key = key;
            this.value = value;
            this.left = new MyTreeMap<K,V>();
            this.right = new MyTreeMap<K,V>();
            this.height = 0;
            this.size = 1;
            //no previous value
            return null;
        }
        
        //If a node with the key exists
        if (this.key.compareTo(key) == 0) {
            oldValue = this.value;
            this.value = value;
            return oldValue; 
        }
        
        //Node with key doesn't exist yet
        if (this.key.compareTo(key) > 0) {
            oldValue = this.left.put(key, value);
            
        } else {
            oldValue = this.right.put(key, value);
        }
        
        // now check to see if I am unbalanced
        if (this.left.height - this.right.height < -1 | this.left.height - this.right.height > 1 ) {
            restructure(this);
        }
        
        // fix my height
        this.setHeight();

        // fix my size
        this.size = left.size + right.size + 1;

        return oldValue;
    }


    /**
     * Rebalances an AVL internal node using the restructure algorithm from
     * class. Suppose Z is the node (node) that fails the height balance property,
     * and let Y be Z's tallest child, and X be Y's tallest child.
     * Given these labels, let a, b, and c be the value of X,Y,Z in sorted order,
     * whatever that may be (so, if the tree looks like
     * <pre>
     *                 Z(c)
     *                /   \
     *              Y(a)  t3
     *              / \
     *             t0  X(b)
     *                / \
     *              t1  t2
     * </pre>
     * then the sorted order is Y,X,Z, and therefore a is Y's value,
     * b is X's value, and c is Z's value.)
     * Let t0, t1, t2, and t3 be the subtrees from left to right (so in
     * the example above, t0 is Y's left subtree, t1 is X's left subtree,
     * t2 is X's right subtree, and t3 is Z's right subtree.)
     * You will be setting these variables according to the definitions
     * above, and depending on which subtrees are causing the imbalance.
     * Once you've made these assignments, the provided code will return the
     * tree with the final arrangement of
     * <pre>
     *                 X(b)
     *                /   \
     *              Y(a)  Z(c)
     *              / \   / \
     *             t0 t1 t2 t3
     * </pre>
     * @param node Internal node failing the height balance property
     */
    private void restructure(MyTreeMap<K, V> node) {
        // fill in these values such that
        //      a < b < c
        // and
        //      t0->t3 are the left to right trees
        MyTreeMap<K,V> a, b, c, t0, t1, t2, t3;
 
        if (node.left.height > node.right.height) {
            // left side taller, right subtree is last
            
            if (node.left.left.height > node.left.right.height) {
                // left->left
                if (debug) System.err.println("Single right rotation at: "+node.key);

                /**               Z(c)
                 *                /   \
                 *              Y(b)  t3
                 *              / \
                 *            X(a) t2
                 *           / \
                 *          t0 t1  
                 */
                
                c = node;
                b = node.left;
                a = node.left.left;
                t0 = a.left;
                t1 = a.right;
                t2 = b.right;
                t3 = c.right;
                
 
            } else {
                // left->right turn
                if (debug) System.err.println("Double right rotation at: "+node.key);

                /**   
                 *                 Z(c)
                 *                /   \
                 *              Y(a)  t3
                 *              / \
                 *             t0  X(b)
                 *                / \
                 *              t1  t2
                 */
                
                c = node;
                a = node.left;
                b = node.left.right;
                t0 = a.left;
                t1 = b.left;
                t2 = b.right;
                t3 = c.right;

            }
        //right side is taller 
        } else {
            if (node.right.right.height > node.right.left.height) {
                // right->right
                if (debug) System.err.println("Single left rotation at: "+node.key);

                /**   
                 *                 Z(a)
                 *                /   \
                 *              t0    Y(b)  
                 *                    / \
                 *                   t1 X(c)
                 *                      / \
                 *                     t2  t3
                 */
                
                a = node;
                b = node.right;
                c = node.right.right;
                t0 = a.left;
                t1 = b.left;
                t2 = c.left;
                t3 = c.right;

            } else {
                // right->left turn
                if (debug) System.err.println("Double left rotation at: "+node.key);

                /**   
                 *                 Z(a)
                 *                /   \
                 *              t0    Y(c)  
                 *                    / \
                 *                  X(b) t3
                 *                 / \
                 *                t1  t2
                 */
                
                a = node;
                c = node.right;
                b = node.right.left; 
                t0 = a.left;
                t1 = b.left;
                t2 = b.right;
                t3 = c.right;

            }
        }


        // If you've done the above correctly, this should fix your current node
        MyTreeMap<K, V> newLeft = new MyTreeMap<>(a.key,a.value,t0,t1);
        MyTreeMap<K, V> newRight = new MyTreeMap<>(c.key,c.value,t2,t3);
        // now fix myself
        this.key = b.key;
        this.value = b.value;
        this.left = newLeft;
        this.right = newRight;
        setHeight();
    }

//    // TODO - optional implementation
//    @Override
//    public V remove(Object rkey){
//        MyTreeMap<K,V> node = this;
//        V toReturn = node.value;
//        
//        K rKey = (K)rkey;
//        if (this.key.compareTo(rKey) == 0) {
//            MyTreeMap<K,V> newTop = this.right.findMin();
//            node = new MyTreeMap<K,V>(newTop.key, newTop.value, node.left, node.right);
//            right.left = newTop.right;
//        } else if (this.key.compareTo(rKey) > 0){ 
//            left.remove(rKey);
//        } else {
//            right.remove(rKey);
//        }
//        
//        setHeight();
//        
//        if (this.left.height - this.right.height < -1 | this.left.height - this.right.height > 1 ) {
//            restructure(this);
//        }
//        
//        setHeight();
//        
//        return toReturn;
//    }
    
//    //call on right child of removed node
//    private MyTreeMap<K,V> findMin() {
//        MyTreeMap<K,V> newTop;
//        if (this.left.left.isEmpty()) {
//            newTop = this.left;
//        } else {
//            newTop = left.findMin();
//        }
//        return newTop;
//    }


    //--------------------------------------------------------------------------
    // ** NO CHANGES NEEDED BELOW - BUT YOU MIGHT WANT TO READ AND UNDERSTAND **
    //--------------------------------------------------------------------------


    //--------------------------------------------------------------------------
    // Constructors - outside users can only make an empty tree
    //--------------------------------------------------------------------------

    /**
     * Creates an empty tree.
     */
    public MyTreeMap(){
        this(null,null,null,null);
        this.size = 0;
    }

    /**
     * Build a tree from existing components.  To be used only within MyTreeMap
     * and subchildren.
     * @param key Key to store at this location
     * @param value Value to store at this location (associated with Key)
     * @param left An existing subtree, all Key values should be smaller than key
     * @param right An existing subtree, all Key values should be greater than key
     */
    protected MyTreeMap(K key, V value, MyTreeMap<K, V> left, MyTreeMap<K, V> right) {
        super();
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
        // fix the height
        setHeight();
        // and the size
        this.size = 1;
        if (left!=null) this.size+=left.size;
        if (right!=null) this.size+=right.size;
    }

    /**
     * Used to construct a single leaf element.  Empty subtrees are put in place of
     * children to allow for recursive methods to not have to worry about null
     * pointers.
     * @param key Key to store at this location
     * @param value Value to store at this location (associated with Key)
     */
    protected MyTreeMap(K key, V value) {
        this(key,value,new MyTreeMap<K,V>(), new MyTreeMap<K,V>());
        this.size = 1;
    }

    /**
     * Copies the values from the root of another tree into this root.  Note
     * that it does not clone those values, just duplicates the structure.
     * @param other The root whose values are copied into this node
     * @return the current node with new values
     */
    private MyTreeMap<K,V> selfCopy(MyTreeMap<K,V> other) {
        this.key = other.key;
        this.value = other.value;
        this.left = other.left;
        this.right = other.right;
        this.height = other.height;
        return this;
    }

    /**
     * Determine if this is a placeholder subtree for an empty leaf branch.
     * @return true only if this is a placeholder node, false otherwise
     */
    @Override
    public boolean isEmpty(){
        return left==null && right==null;
    }

    /**
     * Determine if this is a leaf node (not an empty node).
     * @return true if this node is not a placeholder and has no children
     */
    public boolean isLeaf() {
        return !isEmpty() && left.isEmpty() && right.isEmpty();
    }

    // This is required for implementing AbstractMap
    @Override
    public Set<Map.Entry<K,V>> entrySet(){

        return new AbstractSet<>() {

            @Override
            public Iterator<Map.Entry<K, V>> iterator() {
                return MyTreeMap.this.entries();
            }

            @Override
            public int size() {
                return MyTreeMap.this.size;
            }
        };
    }

    // This is a wrapper method to meet the requirements of the interface
    // Changes the object key to a K and calls get above!! 
    @Override
    public V get(Object searchKey){
        // We're not allowed to check to see if searchKey is an instanceof K :-(
        try {
            @SuppressWarnings("unchecked")
            K mykey = (K) searchKey;
            return get(mykey);
        } catch (ClassCastException cce) {
            return null;
        }
    }

    /**
     * Creates a StringBuffer (String takes too long) and then starts the
     * recursive traversal of nodes.
     * @return String representation of the (Key:Value) pairs
     */
    private String toStringHelper(){
        StringBuilder sbuf = new StringBuilder();
        toStringHelper(sbuf);
        return sbuf.toString();
    }

    /**
     * Adds (Key:Value) pairs inorder into the StringBuffer.
     * @param sbuf where to store the string version of the tree
     */
    private void toStringHelper(StringBuilder sbuf) {
        if(isEmpty())
            return;
        else {
            if(!left.isEmpty()) {
                left.toStringHelper(sbuf);
                sbuf.append(",");
            }
            sbuf.append( "("+key+":"+value+")" );
            if(!right.isEmpty()) {
                sbuf.append(",");
                right.toStringHelper(sbuf);
            }
        }
    }

    @Override
    public String toString(){
        return "[" + toStringHelper() + "]";
    }

    // helper methods for trees
    private void setHeight() {
        if (isEmpty())
            this.height = -1;
        else
            this.height = 1+Math.max(left.getHeight(),right.getHeight());
    }

    public int getHeight() {
        return height;
    }

    @Override
    public int size() {
        return this.size;
    }


    //--------------------------------------------------------------------------
    // Some iterators that you might find useful
    //--------------------------------------------------------------------------

    /**
     * Create an inorder iterator of the Keys in the tree.
     * @return inorder iterator of the Keys
     */
    public Iterator<K> keys() {
        return keys("in");
    }

    /**
     * Create an iterator over the Keys in the tree.  Will traverse the tree
     * inorder, preorder, or postorder depending upon the argument.
     * @param s string indicating what traversal to do.  Acceptable values
     *          are {"in", "inorder", "pre", "preorder", "post", "postorder"}
     * @return iterator over the Keys in the tree
     */
    public Iterator<K> keys(String s) {
        List<K> list = new LinkedList<>();
        if (s.equalsIgnoreCase("in")||s.equalsIgnoreCase("inorder")) {
            keysInOrder(list);
        } else if (s.equalsIgnoreCase("pre") || s.equalsIgnoreCase("preorder")) {
            keysPreOrder(list);
        } else if (s.equalsIgnoreCase("post") || s.equalsIgnoreCase("postorder")) {
            keysPostOrder(list);
        } else {
            throw new IllegalArgumentException("Unknown traversal method: "+s);
        }
        return list.iterator();
    }

    private void keysInOrder(List<K> l) {
        if (isEmpty()) return;
        this.left.keysInOrder(l);
        l.add(this.key);
        this.right.keysInOrder(l);
    }

    private void keysPreOrder(List<K> l) {
        if (isEmpty()) return;
        l.add(this.key);
        this.left.keysPreOrder(l);
        this.right.keysPreOrder(l);
    }

    private void keysPostOrder(List<K> l) {
        if (isEmpty()) return;
        this.left.keysPostOrder(l);
        this.right.keysPostOrder(l);
        l.add(this.key);
    }


    //--------------------------------------------------------------------------

    public Iterator<Entry<K, V>> entries() {
        List<Entry<K,V>> list = new LinkedList<>();
        entriesInOrder(list);
        return list.iterator();
    }
    private void entriesInOrder(List<Entry<K, V>> list) {
        if (isEmpty()) return;
        this.left.entriesInOrder(list);
        list.add(new MyEntry(key,value));
        this.right.entriesInOrder(list);
    }

    // This is the class you get from the above "entries()" iterator
    class MyEntry implements Map.Entry<K,V>{
        private K key;
        private V value;
        public MyEntry(K key, V value) {
            this.key=key; this.value=value;
        }
        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            throw new UnsupportedOperationException();
        }
    }


    //--------------------------------------------------------------------------
    // Nice to have a main, isn't it?
    //--------------------------------------------------------------------------

    public static void main(String[] args) {
        MyTreeMap<String, Integer> map = new MyTreeMap<>();

        int i = 0;
        for (String s: args) {
            System.out.println("Adding: >"+s+"<");
            map.put(s,i++);
            //System.out.println(map.put(s, i++));
            System.out.println("newheight = " + map.height);
        }

        System.out.println("Size is: " + map.size());

        System.out.println(map);

        System.out.println("Does it contain Apple? " + map.containsKey("Apple"));

        System.out.println("Keys\n----");
        for (String s: map.keySet()) {
            System.out.println(s);
        }
        System.out.println("Values\n------");
        for (Integer s: map.values()) {
            System.out.println(s);
        }
    }
}
