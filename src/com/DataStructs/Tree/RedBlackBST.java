package com.DataStructs.Tree;
public class RedBlackBST<Key extends Comparable<Key>, Value> extends BST {
    private static final boolean RED   = true;
    private static final boolean BLACK = false;

    private Node root;     // root of the BST
    
    private class Node {
        private boolean virtual;
        
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
   
    
}



