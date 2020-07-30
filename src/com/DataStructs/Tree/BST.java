package com.DataStructs.Tree;
import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Random;

public class BST<Key extends Comparable<Key>, Value> {
    // Root of tree.
    private Node root;
    
    public BST() {} // Constructs an empty binary search tree.
    
    // Helper class for BST
    private class Node {
        private Key key;
        private Value value;
        
        private Node left;
        private Node right;
        
        private int count;
        
        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }
    
    // Inserts a key-value pair into the binary search tree.
    public void put(Key key, Value value) {
        if (key == null || value == null) throw new IllegalArgumentException();
        
        if (root == null) root = new Node(key, value);
        else root = put(root, key, value);
    }
    
    // Helper for the put method. Recursive method that identifies the first valid location for the  new input. (replaces value if key already exists)
    private Node put(Node node, Key key, Value value) {
        if (node == null) return new Node(key, value);
        
        int cmp = key.compareTo(node.key);
        
        if (cmp > 0) node.right = put(node.right, key, value);
        else if (cmp < 0) node.left = put(node.left, key, value);
        else node.value = value;
        
        node.count = 1 + size(node.left) + size(node.right);
        
        return node;
    }
    
    public int size() {
        return size(root);
    }
    
    private int size(Node node) {
        if (node == null) return 0;
        return node.count;
    } 
    
    // Retrun the value associates with the given key. If the key is not found return null;
    public Value get(Key key) {
        Node node = root;
        while (node!= null) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) node = node.left;
            else if (cmp > 0) node = node.right;
            else return node.value;
        }
        return null;
    }
    
    // Return all keys in the binary search tree.
    public Iterable<Key> keys(){
        if (root == null) return null;
        
        ArrayDeque<Key> keys = new ArrayDeque<>();
        inOrder(root, keys);
        return keys;
    }
    
    // Helper class to construct the iterable.
    private void inOrder(Node node, Queue<Key> keys) {
        if(node.left != null) inOrder(node.left, keys);
        
        keys.add(node.key);
        
        if(node.right != null) inOrder(node.right, keys);
    }
    
    // Returns the largest key in the tree.
    public Key max() {
        Node node = root;
        while (node.right != null) {
            node = node.right;
        }
        
        return node.key;
    } 
    
    // Returns the smallest key in the tree.
    public Key min() {
        Node node = root;
        while (node.left != null){
            node = node.left;
        }
        return node.key;
    }
    
    // Returns the largest key value less than the given input.
    public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to floor() is null");
        if (isEmpty()) throw new NoSuchElementException("calls floor() with empty symbol table");
        Node x = floor(root, key);
        if (x == null) throw new NoSuchElementException("argument to floor() is too small");
        else return x.key;
    }

    // Helper method for floor method.
    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp <  0) return floor(x.left, key);
        Node t = floor(x.right, key); 
        if (t != null) return t;
        else return x; 
    }
 
    // Returns the smallest key value more than the given input.
    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to floor() is null");
        if (isEmpty()) throw new NoSuchElementException("calls floor() with empty symbol table");
        Node x = ceiling(root, key);
        if (x == null) throw new NoSuchElementException("argument to ceiling() is too big");
        else return x.key;
    }
    
    // Helper method for ceiling method.
    public Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) { 
            Node t = ceiling(x.left, key); 
            if (t != null) return t;
            else return x; 
        } 
        return ceiling(x.right, key); 
    }
    
    // Helper method for the class to determine whether the tree is empty or not.
    private boolean isEmpty() {
        if (root == null) return true;
        return false;
    }
    
    // Unit testing for Class
    public static void main(String[] args) {
        Random r = new Random();
        BST<Integer, Integer> tree = new BST<>();
        
        int rInt1 = r.nextInt(100) + 15;
        
        for (int i = 0; i < rInt1; i++) {
            tree.put(r.nextInt(1000) + 50, r.nextInt(10000));
        }
        
        tree.keys().forEach(i -> {
            System.out.println(i + " " + tree.get(i));
        });
        
        System.out.println(tree.size());
        System.out.println(tree.max());
        System.out.println(tree.min());
        System.out.println(tree.ceiling(68));
        System.out.println(tree.floor(68));
        
        
    }
    
}
