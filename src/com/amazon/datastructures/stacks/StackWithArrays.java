package com.amazon.datastructures.stacks;

import java.util.*;

import javax.naming.SizeLimitExceededException;

public class StackWithArrays {

    private int[] arr;
    private HashMap<Integer, Integer> s;
    private HashMap<Integer, Integer> t;
    
    private static final int DEFAULT_NUM_STACKS = 3;
    private final int NUM_STACKS;
    
    private static final int DEFAULT_MAX_SIZE = 10;
    private final int MAX_SIZE;
    
    private int count;
    
    public StackWithArrays() {
        this(DEFAULT_MAX_SIZE, DEFAULT_NUM_STACKS);
    }
    
    public StackWithArrays(int max_size, int numStacks) {
        this.MAX_SIZE = max_size;
        arr = new int[MAX_SIZE];
        this.NUM_STACKS = numStacks;
        s = new HashMap<Integer, Integer>(NUM_STACKS);
        t = new HashMap<Integer, Integer>(NUM_STACKS);
        for (int stackId = 1; stackId <= NUM_STACKS; stackId++) {
        	s.put(stackId, 0);
        	t.put(stackId, -1);
        }
    }
    
    public void push(int stackId, int val) throws SizeLimitExceededException {
        checkCapacity();
        checkStackId(stackId);
        
        // shift stacks if necessary
        int topIndx = t.get(stackId);
        
        if (stackId < NUM_STACKS) {
        	if (!isEmpty(stackId) && (t.get(stackId)+1) == s.get(stackId+1))
        		shiftRight(stackId+1);
        	else if (isEmpty(stackId) && !isEmpty(stackId+1))
        		shiftRight(stackId+1);
        } else {
        	if (!isEmpty(stackId) && (t.get(stackId)+1) == MAX_SIZE)
        		shiftLeft(stackId-1);
        }
        
        // shifting checked/done
        if (stackId > 1 && isEmpty(stackId))
        	topIndx = t.get(stackId-1)+1;
        else
        	topIndx++;
        
        if (isEmpty(stackId) && stackId > 1)
        	s.put(stackId, t.get(stackId-1) + 1);
        
        arr[topIndx] = val;
        t.put(stackId, topIndx);
        count++;
    }
    
    private void shiftRight(int stackId) {
        for (int id = stackId; id >= 1; id--) {
            if (!isEmpty(id) && hasSpaceOnRight(id)) {
                for (int i = t.get(id) + 1; i > s.get(id); i--)
                    arr[i] = arr[i-1];
                s.put(id, s.get(id)+1);
                t.put(id, t.get(id)+1);
            }
        }
    }
    
    private void shiftLeft(int stackId) {
        for (int id = 1; id <= stackId; id++) {
            if (!isEmpty(id) && hasSpaceOnLeft(id)) {
                for (int i = s.get(id) - 1; i < t.get(id); i++)
                    arr[i] = arr[i+1];
                s.put(id, s.get(id)-1);
                t.put(id, t.get(id)-1);
            }
        }
    }
    
    private boolean hasSpaceOnRight(int stackId) {
        int topIndx = t.get(stackId);
        int nextIndx = stackId < NUM_STACKS
                        ? s.get(stackId+1)
                        : t.get(stackId)+1;
        if (nextIndx == (topIndx+1) || nextIndx == MAX_SIZE)
            return false;
        
        return true;
    }
    
    private boolean hasSpaceOnLeft(int stackId) {
        int sIndx = s.get(stackId);
        int prevIndx = stackId > 1
                        ? t.get(stackId-1)
                        : s.get(stackId)-1;
        if (prevIndx == (sIndx-1) || prevIndx == -1)
            return false;
            
        return true;
    }
    
    private void checkCapacity() throws SizeLimitExceededException {
        if (count == MAX_SIZE)
            throw new SizeLimitExceededException();
    }
    
    private void checkStackId(int stackId) {
        if (stackId <= 0 || stackId > NUM_STACKS)
            throw new IllegalArgumentException();
    }
    
    public int pop(int stackId) {
        checkStack(stackId);
        checkStackId(stackId);
        int topIndx = t.get(stackId);
        int top = arr[topIndx];
        // update topIndx
        topIndx--;
        // stack is empty
        if (topIndx < s.get(stackId)) {
        	topIndx = -1;
        	s.put(stackId, 0);
        }
        t.put(stackId, topIndx);
        count--;
        return top;
    }
    
    public int peek(int stackId) {
        checkStack(stackId);
        checkStackId(stackId);
        return arr[t.get(stackId)];
    }
    
    private void checkStack(int stackId) {
        if (isEmpty(stackId))
            throw new EmptyStackException();
    }
    
    public boolean isEmpty(int stackId) {
        checkStackId(stackId);
        return t.get(stackId) < s.get(stackId);
    }
    
    public int size(int stackId) {
        checkStackId(stackId);
        return t.get(stackId) - s.get(stackId) + 1;
    }
    
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	for (int stackId=1; stackId <= NUM_STACKS; stackId++) {
    		sb.append("[" + s.get(stackId) + "-").append(t.get(stackId) >= 0 ? t.get(stackId) + "" : "x").append("] ");
    		sb.append(stackId).append(" -> ");
    		for (int front=s.get(stackId); front <= t.get(stackId); front++)
    			sb
//    			.append(front)
//    			.append(":")
    			.append(arr[front])
    			.append(" ");
    		sb.append("\n");
    	}
    	return sb.toString();
    }

}
