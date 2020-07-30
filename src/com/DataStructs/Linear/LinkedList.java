package com.DataStructs.Linear;

import java.util.Iterator;

public class LinkedList<Item extends Comparable<Item>> implements Iterable<Item> {
    
    private Node head;
    private Node last;
    int size;
    
    // Constructor that initializes everything to null.
    public LinkedList() {
        head = null;
        last = null;
        size = 0;
    }
    
    // Determines if the list is empty.
    public boolean isEmpty() {
        if (head == null) return true;
        return false;
    }
    
    // Returns the size of the linkedlist.
    public int size() {
        return size;
    }
    
    // Adds an item to this linkedlist.
    public void add(Item item) {
        if (item == null) throw new IllegalArgumentException("This linked list does not support the addition of null elements.");
        
        if (head == null) {
            head = new Node(item);
            last = head;
        } else {
            last.next = new Node(item);
            last = last.next;
        }
        
        size++;
    }
    
    // Get an item at a specific index in this linkedlist.
    public Item get(int i) {
        if (i > size - 1) throw new IndexOutOfBoundsException("Index " + i + " is out of bounds for an array of size " + size);
        
        Node temp = head;
        
        while (i > 0) {
            temp = temp.next;
            i--;
        }
        
        return temp.item;
    }
    
    // Remove an item at a specific index in this linkedlist.
    public void remove(int i) {
        if (i > size - 1) throw new IndexOutOfBoundsException("Index " + i + " is out of bounds for an array of size " + size);
        
        Node temp = head;
        
        while (i > 1) {
            temp = temp.next;
            i--;
        }
        
        if (temp.next != null)
            temp.next = temp.next.next;
        else temp.next = null;
    }
    
    // Return an iterator for the 
    @Override
    public Iterator<Item> iterator() {
        // TODO Auto-generated method stub
        return new List(head);
    }
    
    // Reverse the order of the linkedlist.
    public void reverse() {
        Node cur = head;
        Node next = head;
        Node prev = null;
        
        while (next != null) {
            next = cur.next;
            cur.next = prev;
            
            prev = cur;
            cur = next;
        }
        
        last = head;
        head = prev;
    }
    
    // Iterator for class.
    private class List implements Iterator<Item> {
        private Node cur;
        
        public List(Node head) {
            cur = head;
        }

        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return cur != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new UnsupportedOperationException();
            // TODO Auto-generated method stub
            Item temp = cur.item;
            cur = cur.next;
            return temp;
        }
    }
    
    // Node class for 
    private class Node {
        private Item item;
        private Node next;
        
        public Node(Item item){
            this.item = item;
        }
    }




   
    // Unit testing for the class.
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        
        System.out.println(list.isEmpty());
        
        list.add(13);
        list.add(423);
        list.add(23);
        list.add(022);
        list.add(122);
        list.add(25);
        list.remove(4);
        
        System.out.println(list.get(1));
        
        System.out.println(list.isEmpty());
        
        for (Integer i: list) {
            System.out.println(i);
        }
        
        list.reverse();
        
        for (Integer i: list) {
            System.out.println(i);
        }
    }

}
