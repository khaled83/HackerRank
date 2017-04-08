package com.khaledabbas.datastructures;

import java.util.Collection;

public class ArrayList<E>
{
    private Object[] elementData;
    private static final Object[] EMPTY_ELEMENT_DATA = {};
    int size;
    private static int DEFAULT_INITIAL_CAPACITY = 10;
    
    public ArrayList() {
        elementData = EMPTY_ELEMENT_DATA;
    }
    
    public ArrayList(int initialCapcity) throws IllegalArgumentException {
        if(initialCapcity < 0)
            throw new IllegalArgumentException("");
        
        elementData = new Object[initialCapcity];
    }
    
    public void add(E element) {
        ensureCapacity(size+1);
		elementData[size++] = element;
    }
    
    // 0 1 2 3
    public void add(E element, int indx) {
        rangeCheckForAdd(indx);
        arraycopy(elementData, indx, elementData, indx+1, size - indx);
        E oldElement = element(indx);
        elementData[indx] = element;
    }
    
    private void arraycopy(Object[] srcArr, int srcIndx, Object[] dstArr, int dstIndx, int length ) {
        for(int src=srcIndx, dst=dstIndx, times=0; times < length; times++, src++, dst++) 
        {
            dstArr[dst] = srcArr[src];
        }
    }
    
    private void rangeCheckForAdd(int loc) {
        if(loc < 0 || loc > size)
            throw new ArrayIndexOutOfBoundsException(loc);
    }
    
    private E element(int indx) {
        return (E) elementData[indx];
    }
    
    private void ensureCapacity(int minCapacity) {
        
        if(empty()) {
            minCapacity = Math.max( DEFAULT_INITIAL_CAPACITY, minCapacity );
        }
        
        if(elementData.length - minCapacity < 0) {
            grow(minCapacity);
        }            
        
    }
    
    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + ( oldCapacity >> 1 );
        newCapacity = Math.max( minCapacity, newCapacity );
        
        elementData = arraycopy( elementData, newCapacity );
    }
    
    private Object[] arraycopy(Object[] orgArr, int newLength) {
        Object[] newArr = new Object[newLength];
        int copyRange = Math.min(newLength, orgArr.length);
        for(int i=0; i< copyRange; i++) {
            newArr[i] = orgArr[i];
        }
        return newArr;
    }
    
    // 0 1 2 3    :    4
    public E remove(int indx)
    {
        rangeCheck(indx);
        E oldElement = element(indx);
        arraycopy(elementData, indx+1, elementData, indx, size-indx-1);
        elementData[--size]=null;
        return oldElement;
    }
    
    private void rangeCheck(int loc) {
        if(loc < 0 || loc >= size)
            throw new ArrayIndexOutOfBoundsException(loc);
    }
    
    public boolean contains(Object value) {
        return indexOf(value) >= 0;
    }
    
    public int indexOf(Object value) {
        if(value == null) {
            for(int i=0; i<size;i++)
                if(elementData[i] == null)
                    return i;
        }
        else {
            for(int i=0; i<size;i++)
                if(value.equals(elementData[i]))
                    return i;
        }
        return -1;
    }
    
    public void addAll(Collection<? extends E> collection) {
        Object[] newArr = collection.toArray();
        int numNew = newArr.length;
        ensureCapacity(size+ numNew);
        arraycopy( newArr, 0, elementData, size, numNew );
        size += numNew;
    }
    
    public void clear() {
        for(int indx = 0; indx<size; indx++)
            elementData[indx] = null;
        size = 0;
    }
    
    private boolean empty() {
        return size == 0;
    }
    
    public int size() {
    	return size;
    }
    
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("{");
    	for(int i=0; i<size;i++) {
    		if(elementData[i] != null)
    			sb.append(elementData[i].toString());
			sb.append(",");
    	}
    	if(!empty())
    		sb.setLength(sb.length()-1); // trim last ','
    	sb.append("}");
    	
    	return new String(sb);
    }
    

}

