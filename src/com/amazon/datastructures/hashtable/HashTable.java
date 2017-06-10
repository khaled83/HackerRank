package com.amazon.datastructures.hashtable;

import java.util.*;

public class HashTable<K,V> {
    
    Node<K,V>[] items;
    private int size;
    
    private static final int DEFAULT_INITIAL_CAPACITY = 10;
    
    // grow array when size/items.length exceeds loadFactor
    // private final int loadFactor;
    // private static final int DEFAULT_LOAD_FACTOR = 0.75;
    
    public HashTable() {
        this(DEFAULT_INITIAL_CAPACITY);
    }
    
	public HashTable(int initialCapacity) {
		items = (Node<K, V>[]) new Node[initialCapacity];
	}
    
    public V put(K k, V v) {
        ensureCapacity(size+1);
        int loc = hashLoc(k);
        Node<K,V> head = items[loc];
        
        Node<K, V> n = head;
        while (n != null && !n.k.equals(k)) {
    		n = n.next;
        }
        
        // key not found, create a new one
        if (n == null) {
        	n = new Node<K, V>(k, v);
        	n.next = head;
        	items[loc] = n;
        	size++;
        	return null;
        }
        // key exists, update only
        else {
        	V oldVal = n.v;
        	n.v = v;
        	return oldVal;
        }
    }
    
    private void ensureCapacity(int minCapacity) {
        int oldCapacity = items.length;
        int newCapacity = oldCapacity;
        if (oldCapacity < minCapacity) {
            newCapacity = oldCapacity + (oldCapacity >> 1);
        }
        if (newCapacity < minCapacity) {
            newCapacity = minCapacity;
        }
        grow(minCapacity);
    }
    
    // @Async    perform this asynchronously using external thread in time sensitive applications
    private void grow(int minCapacity) {
        if (items.length >= minCapacity)
            return;
            
        // JDK checks for Integer.MAX_VALUE - 8 too
        // rehash
        Iterable<Node<K, V>> entrySet = entrySet();
        items = (Node<K, V>[]) new Node[minCapacity];
        size = 0;
        for (Node<K, V> n : entrySet) {
            this.put(n.k, n.v);
        }
    }
    
    public V get(K k) {
        Node<K, V> n = items[hashLoc(k)];
        while (n != null) {
            if (n.k.equals(k))
                return n.v;
            n = n.next;
        }
        return null;
    }
    
    public V remove(K k) {
        Node<K, V> n = items[hashLoc(k)];
        Node<K, V> prev = null;
        while (n != null) {
            if (n.k.equals(k)) {
                // remove node
                V v = n.v;
                if (prev != null) {
                    prev.next = n.next;
                } else {
                    items[hashLoc(k)] = n.next;
                }
                return v;
            }
            prev = n;
            n = n.next;
        }
        size--;
        return null;
    }
    
    private int hashLoc(K k) {
        return k.hashCode() % items.length;
    }
    
    private Iterable<Node<K, V>> entrySet() {
        List<Node<K, V>> entrySet = new ArrayList<Node<K, V>>();
        for (Node<K, V> head : items) {
            while (head != null) {
                entrySet.add(head);
                head = head.next;
            }
        }
        return entrySet;
    }
    
    private static class Node<K, V> {
        Node<K, V> next;
        K k;
        V v;
        
        public Node(K k, V v) {
            this.k = k;
            this.v = v;
        }
    }
    
    public int size() {
    	return size;
    }
}
