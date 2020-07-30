package com.DataStructs.Linear;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class rArray <Item extends Comparable<Item>> implements Iterable<Item> {
    
    private Object[] arr;
    private int index;
    
    public rArray() {
        arr  = new Object[10];
        index = 0;
    }
    
    public rArray(Item[] arr) {
        if (arr == null) throw new IllegalArgumentException();
        
        this.arr = new Object[arr.length];
        index = 0;
        
        while (arr[index] != null) {
            this.arr[index] = arr[index++];
        }
    }
    
    public rArray(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        
        arr = new Object[n];
        index = 0;
    }
    
    public void add(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (index == arr.length) grow();
        arr[index++] = item;
    }
    
    @SuppressWarnings("unchecked")
    public Item get(int i) {
        if (i > index) throw new IndexOutOfBoundsException();
        return (Item) arr[i];
    }
    
    public void removeItem(Item item) {
        int i = indexOf(item);
        if (i == -1) throw new NoSuchElementException();
        removeIndex(i);
    }
    
    public void removeIndex(int i) {
        if (i > index - 1) throw new IndexOutOfBoundsException();
        arr[i] = null;
        fix();
        if (arr.length > 10)
            if (index < arr.length/4) shrink();
    }
    
    @SuppressWarnings("unchecked")
    public int indexOf(Item item) {
        for(int i = 0; i < index; i++) {
            if (item.compareTo((Item) arr[i]) == 0) return i;
        }
        
        return -1;
    }
    
    @Override
    public Iterator<Item> iterator() {
        return new Itertool();
    }
    
    private class Itertool implements Iterator<Item>{
        private int i = 0;
        @Override
        public boolean hasNext() {
            if (i < index) return true;
            return false;
        }

        @SuppressWarnings("unchecked")
        @Override
        public Item next() {
            if (!hasNext()) throw new IllegalArgumentException();
            return (Item)arr[i++];
        }
    }
    
    private void fix() {
        boolean switched = false;
        
        for (int i = 1; i < index; i++) {
            if (arr[i-1] == null) {
                arr[i-1] = arr[i];
                arr[i] = null;
                switched = true;
            }
        }
        
        
        if(switched) {
            index--;
            fix();
        }
       
    }
    
    private void grow() {
        arr = Arrays.copyOf(arr, 2*arr.length);
    }
    
    private void shrink() {
        arr = Arrays.copyOf(arr, arr.length/2);
    }
    
    // Unit testing for class
    public static void main(String[] args) {
        rArray<Integer> arrList = new rArray<>();
        
        arrList.add(10);
        arrList.add(20);
        arrList.add(3094);
        arrList.add(32039);
        arrList.add(3902);
        arrList.add(390312);
        arrList.removeItem(1);
        arrList.removeIndex(1);
        
        arrList.forEach(System.out::println);
    }

    
    
}
