package warmup4;

import java.util.NoSuchElementException;

public class LinkedList<E> {

    private class Node {
        public E data;
        public Node next;
        
        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }
        
    }
    
    private Node head;
    private Node tail;
    private int size;
    
    //LinkedList constructor 
    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    //LL isEmpty method
    public boolean isEmpty() {
       if (this.head == null) {
           return true;
       } else {
           return false;
       }
    }
    
    public void addFirst(E element) {
        Node newNode = new Node(element, this.head);
        
        this.head = newNode;
        
        //If no element in list
        if (this.tail == null) {
            this.tail = this.head;
        }
        
        this.size++;
        
    }
    
    public void printList() {
        Node node = this.head;
        
//        while (node != null) {
//            System.out.println(node.data);
//            node = node.next;
//        }
        int index = 0;
        
        for (node = this.head; node != null; node = node.next) {
            System.out.print(index + ": ");
            System.out.println(node.data);
            index ++;
        }
        
    }
    
    public E removeFirst() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        } 
//        else {
//            
//        }   
        Node removed = this.head;
        this.head = this.head.next;
        if (this.head == null) {
            this.tail = null;
        };
        this.size--;
        return removed.data;   
    }
    
    public void addLast(E element) {
        if (this.isEmpty()) {
            this.addFirst(element);
        } else {
// Adding a tail discards this!
//            Node node = this.head;
//            while (node.next != null) {
//                node = node.next;
//            }
            Node newNode = new Node(element, null);
            this.tail.next = newNode;
            this.tail = newNode; //this.tail = this.tail.next?
            this.size++;
        }
    }
    
    public E removeLast() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            if (this.head.next == null) {
                return this.removeFirst();
            } else {
                Node node = this.head;
                //Need second to last node!!
                while (node.next.next != null) {
                    node = node.next;
                }
                Node removed = node.next;
                node.next = null;
                this.size--;
                return removed.data;
            }
        }
    }
    
    
    public E peekFirst() {
        return this.head.data;
    }
    
    public int size() {
        return this.size;
    }
    
    public static void main(String[] args) {
        //Tests if new list is empty
        LinkedList<String> list = new LinkedList<String>();
        System.out.println("After creating new list: " + list.isEmpty());
        
        //Tests if list is not empty 
        list.addFirst("yellow");
        System.out.println("After adding one element: " + list.isEmpty());
        
        list.addFirst("blue");
        list.addFirst("red");
        
        list.printList();
        
        //Checking removeFirst method until empty 
        while (!list.isEmpty()) {
           System.out.println(list.removeFirst());
        }
        
        //Testing addLast()
        list.addLast("are");
        list.addFirst("lists");
        list.addLast("neat!");
        list.addFirst("Linked");
        list.printList();
        
        //Empty list
        while (!list.isEmpty()) {
            list.removeFirst();
         }
        
        System.out.println("List is empty: " + list.isEmpty());
        //Error!
        //list.removeFirst();
        list.addFirst("2");
        list.addFirst("1");
        list.removeLast();
        //Should print "1"
        list.printList();
 
    
        
        
    }
    
    
    
    

}
