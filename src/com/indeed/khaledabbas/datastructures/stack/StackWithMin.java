package com.indeed.khaledabbas.datastructures.stack;

import com.indeed.khaledabbas.datastructures.stack.adt.Stack;

public class StackWithMin<E extends Comparable<E>> extends Stack<E>
{

    Stack<E> minStack = new Stack<E>();
    
    public void push(E e)
    {
        super.push(e);
        
        if(minStack.empty() || e.compareTo(minStack.top()) < 0 )
            minStack.push(e);
    }
    
    public E pop()
    {
        E top = super.pop();
        
        if(top == min() )
            minStack.pop();
        
        return top;
    }
    
    public E min()
    {
        return minStack.top();
    }
    
    
    
}
