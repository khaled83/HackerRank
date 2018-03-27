package com.amazon.datastructures.heaps;

import javax.naming.SizeLimitExceededException;
import java.util.*;

public class Heap {

    private int[] items;
    
    private static final int DEFAULT_CAPACITY = 10;
    private final int MAX_SIZE;
    
    private int size = 0;
    
    public Heap() {
        this(DEFAULT_CAPACITY);
    }
    
    public Heap(int capacity) {
        items = new int[capacity];
        this.MAX_SIZE = capacity;
    }
    
    public void add(int item) throws SizeLimitExceededException {
        checkCapacity();
        int loc = size;
        items[loc]= item;
        size++;
        // trickle up to proper location
        int parent = (loc-1) / 2;
        while (parent >= 0 && items[parent] < items[loc]) {
            swap(items, loc, parent);
            loc = parent;
            parent =(loc-1) / 2;
        }
    }
    
    public int peek() {
        checkEmpty();
        return items[0];
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
        swap(items, 0, size-1);
        size--;
        // trickle down root element into proper location
        int loc = 0;
        int leftChild = 2 * loc + 1;
        int rightChild = leftChild + 1;
        while ((leftChild < size && items[loc] < items[leftChild]) || (rightChild < size && items[loc] < items[rightChild])) {
            int largerChild = items[leftChild] > items[rightChild] || rightChild >= size  ? leftChild : rightChild;
            swap(items, loc, largerChild);
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
    
    public List<Integer> kLargest(int k) {
        if (k > size) {
            throw new IllegalArgumentException("Heap contains only " + size + " elements");
        }
        PriorityQueue<Integer> pQ = new PriorityQueue<Integer>(Collections.reverseOrder());
        // maps value to array index
        HashMap<Integer, Integer> h = new HashMap<Integer, Integer>();
        pQ.add(items[0]);
        h.put(items[0], 0);
        List<Integer> res = new ArrayList<Integer>();
        while (res.size() < k) {
            int max = pQ.poll();
            res.add(max);
            // parent node index
            int parent = h.get(max);
            int left = left(parent);
            int right = right(parent);
            if (rangeCheck(left)) {
                pQ.add(items[left]);
                h.put(items[left], left);
            }
            if (rangeCheck(right)) {
                pQ.add(items[right]);
                h.put(items[right], right);
            }
        }
        return res;
    }
    
    private int left(int parent) {
        return parent * 2 + 1;
    }
    
    private int right(int parent) {
        return parent * 2 + 2;
    }
    
    private boolean rangeCheck(int indx) {
        return indx >= 0 && indx < size;
    }

    public static int findSalaryCap(List<Integer> salaries, int target) {
        // construct max heap
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
        // insert elements into heap
        // calc current total
        int total = 0;
        // O (n log(n))
        for (int s : salaries) {
            pq.add(s);
            total += s;
        }
        
        // number of salaries being discounted
        int count = 0;
        int max = Integer.MIN_VALUE;
        
        while (total > target) {
            int next = pq.peek();
            if (max > next) {
                int desiredDecrement = (total - target) / count;
                int possibleDecrement = (max - next) * count;
                int decrement = Math.min(desiredDecrement, possibleDecrement);
                total -= decrement * count;
                max = total > target ? next : (max - decrement);
            }
            else {
                max = pq.remove();
                count++;
            } 
        }
        
        return max;
    }
    
    public static int findSalaryCap2(List<Integer> salaries, int target) {
        Collections.sort(salaries);
        int total = 0;
        for (int s : salaries) {
            total += s;
        }
        
        int n = salaries.size();
        int max = salaries.get(n - 1);
        // number of equal max numbers
        int count = 1;
        for (int i = n - 2; i >= 0 && total > target; i--, count++) {
        		int cur = salaries.get(i);
            if (cur != max) {
                int desiredDecrement = (total - target) / count;
                int possibleDecrement = (max - cur) * count;
                int decrement = Math.min(desiredDecrement, possibleDecrement);
                total -= decrement * count;
                max = total > target ? cur : (max - decrement);
            }
        }
        
        return max;
    }
}
