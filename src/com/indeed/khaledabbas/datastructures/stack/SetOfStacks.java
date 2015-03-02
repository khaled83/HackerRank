package com.indeed.khaledabbas.datastructures.stack;

import java.util.*;

public class SetOfStacks<E>
{
    private int size = 0;

    private int capacity = 2;
    
    ArrayList<Stack<E>> stackList = new ArrayList<Stack<E>>();
    
    private Stack<E> internalStack(int indx)
    {   
        if(indx == stackList.size() )
            stackList.add( new Stack<E>() );
        
        return stackList.get( indx );
    }
    
    private Stack<E> internalStackForPush()
    {
        int stackIndx = size / capacity;
    
        return internalStack(stackIndx); 
    }
    
    private Stack<E> internalStackForPop()
    {
        int stackIndx = (size-1) / capacity;
        return internalStack(stackIndx); 
    }
    
    public void push(E e)
    {
        Stack<E> stack = internalStackForPush();
        stack.push(e);
    }
    
    public E pop()
    {
        Stack<E> stack = internalStackForPop();
        return stack.pop();
    }
    
    public E top()
    {
        Stack<E> stack = internalStackForPop();
        return stack.peek();
    }
    
    public String toStringForTesting()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for(Stack<E> stack : stackList)
        {
            while(!stack.empty())
                sb.append(stack.pop()).append(",");
        }
        sb.append("}");
        return new String(sb);
    }

}
