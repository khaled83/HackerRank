package com.indeed.khaledabbas.datastructures.queue;

import java.util.Stack;

public class QueueWithTwoStacks<E>
{

    Stack<E> stack = new Stack<E>();
    Stack<E> reverse = new Stack<E>();
    
    public void enqueue(E e)
    {    
        stack.push(e);
    }
    
    public E dequeue()
    {
        shiftElements();        
        return reverse.pop();
    }
    
    public E getFront()
    {
        shiftElements();
        return reverse.peek();
    }
    
    private void shiftElements()
    {
        if(reverse.empty())
            while(!stack.empty())
                reverse.push( stack.pop() );
    }
    
    public boolean empty()
    {
        return size() == 0;
    }
    
    public int size()
    {
        return stack.size() + reverse.size();
    }

}
