package com.amazon.datastructures.heaps;

import javax.naming.SizeLimitExceededException;

public class Heap {

    private int[] arr;
    
    private static final int DEFAULT_CAPACITY = 10;
    private final int MAX_SIZE;
    
    private int size = 0;
    
    public Heap() {
        this(DEFAULT_CAPACITY);
    }
    
    public Heap(int capacity) {
        arr = new int[capacity];
        this.MAX_SIZE = capacity;
    }
    
    public void add(int item) throws SizeLimitExceededException {
        checkCapacity();
        int loc = size;
        arr[loc]= item;
        size++;
        // trickle up to proper location
        int parent = (loc-1) / 2;
        while (parent >= 0 && arr[parent] < arr[loc]) {
            swap(arr, loc, parent);
            loc = parent;
            parent =(loc-1) / 2;
        }
    }
    
    public int peek() {
        checkEmpty();
        return arr[0];
    }
    
    /**
              [0  1  2  3  4  5  6 7 8 9 ]
    arr        60 50 30 10 40 20 - - - -
    
    s   size   arr                     loc  parent   left  right   larger
    -   ----   -------                 ---  ------   ----  -----   ------
    add(70)
    1    6     60 50 30 10 40 20
    2    7     60 50 30 10 40 20 70    6    2
    3    7     60 50 70 10 40 20 30    2    0
    4    7     70 50 60 10 40 20 30    0    0
    remove
    1    7     70 50 60 10 40 20 30    0   max=70
    2    6     30 50 60 10 40 20 70    0             1     2
    3          60 50 30 10 40 20 70    2             5     6       2
    4          60 50 30 10 40 20 70    TERMINATE
     
    */
    
    public int remove() {
        int max = peek();
        // swap last element with root
        swap(arr, 0, size-1);
        size--;
        // trickle down root element into proper location
        int loc = 0;
        int leftChild = 2 * loc + 1;
        int rightChild = leftChild + 1;
        while ((leftChild < size && arr[loc] < arr[leftChild]) || (rightChild < size && arr[loc] < arr[rightChild])) {
            int largerChild = arr[leftChild] > arr[rightChild] || rightChild >= size  ? leftChild : rightChild;
            swap(arr, loc, largerChild);
            loc = largerChild;
            leftChild = 2 * loc + 1;
            rightChild = leftChild + 1;
        }
        return max;
    }
    
    private void checkCapacity() throws SizeLimitExceededException {
        if (isFull())
            throw new SizeLimitExceededException();
    }
    
    private void checkEmpty() {
        if (isEmpty())
            throw new EmptyHeapException();
    }
    
    private boolean isEmpty() {
        return size == 0;
    }
    
    private boolean isFull() {
        return size == MAX_SIZE;
    }
    
    private void swap(int[] arr, int x, int y) {
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }
    
    private static class EmptyHeapException extends RuntimeException {
    	
    }
    

}
