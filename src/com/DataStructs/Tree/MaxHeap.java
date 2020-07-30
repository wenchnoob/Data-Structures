package com.DataStructs.Tree;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;

public final class MaxHeap<Key extends Comparable<Key>> {
    
    private Key[] keys;
    private int size;
    
    
    @SuppressWarnings("unchecked")
    public MaxHeap () {
        keys = (Key[]) new Comparable[10];
        size = 0;
    }
    
    @SuppressWarnings("unchecked")
    public MaxHeap(int initialCapacity) {
        keys = (Key[]) new Comparable[initialCapacity];
        size = 0;
    }
    
    // Adds a value to the Heap.
    public void insert(Key key) {
        if (key == null) throw new IllegalArgumentException();
        if (size == keys.length - 1) grow();
        
        keys[++size] = key;
        swim(size - 1);
        
    }
    
    // Returns the largest value in the Heap.
    public Key delMax() {
        if (size == 0) throw new NoSuchElementException();
        if (size < keys.length / 4) shrink();
        
        Key key = keys[1];
        keys[1] = keys[size--];
        keys[size + 1] = null;
        sink(1);
        return key;
    }
    
    // Returns the current amount of non-null elements in the internal array.
    public int size() {
        return size;
    }
    
    // Returns string representation of the class.
    public String toString() {
        return Arrays.toString(keys); //+ "\n" + size();
    }
    
    
    // Unit testing for the class
    public static void main(String[] args) {
        Random r = new Random();
        
        MaxHeap<Integer> MHeap = new MaxHeap<>();
        
        int reps = r.nextInt(1000) + 100;
        for (int i = 0; i < reps; i++) {
            MHeap.insert(r.nextInt(100));
            System.out.println(MHeap);
        }
        
        for (int i = 0; i < reps/2; i++) {
            System.out.println(MHeap.delMax() + " Was deleted.");
            System.out.println(MHeap);
        }
        
    }
    
    
    // Moves the node down the tree till it is in proper order.
    private void sink(int i) {
        if (isOrderedChildren(i) || size <= 2 * i) return;
        
        if (!(keys[i * 2 + 1] == null))
            if (keys[2 * i].compareTo(keys[2 * i + 1]) > 0) {
                exchange(2 * i, i);
                sink(2 * i);
            } else { 
                exchange(2 * i + 1, i);
                sink(2 * i + 1);
            }
        else
            exchange(2 * i, i);
    }
    
    // Moves the node up the tree till it is in proper order.
    private void swim(int i) {
        if (isOrderedParent(i)) return;
        
        if (keys[(i/2) * 2].compareTo(keys[(i/2) * 2 + 1]) > 0) exchange(i/2 * 2, i/2);
        else exchange(i/2 * 2 + 1, i/2);
        
        swim(i/2);
    }
    
    // Checks that the node is properly ordered with respect to its parent.
    private boolean isOrderedParent(int i) {
        if (i < 2) return true;
        if (keys[i/2].compareTo(keys[i]) < 0) return false;
        return true;
    }
    
    // Checks that the node is properly ordered with respect to its children.
    private boolean isOrderedChildren(int i) {
        if (size < 2 * i + 1) return true;
        
        Key parent = keys[i];
        
        if (parent.compareTo(keys[2 * i]) < 0) return false;
        if (parent.compareTo(keys[2 * i + 1]) < 0) return false;
        
        return true;
    }
    
    // Exchanges the values at the specified indexes for this internal array.
    private void exchange(int i, int j) {
        Key temp = keys[i];
        keys[i] = keys[j];
        keys[j] = temp;
    }
    
    // Doubles the size of this data structure's internal array.
    private void grow() {
        keys = Arrays.copyOf(keys, 2 * keys.length);
    }
    // Halves the size of this data structure's internal array.
    private void shrink() {
        keys = Arrays.copyOfRange(keys, 0, keys.length/2);
    }
}
