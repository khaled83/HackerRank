package com.amazon.datastructures.stacks;

import java.util.Stack;

//inheritance
public class StackWithMax1 extends Stack<Integer> {

	private static final long serialVersionUID = 1L;
	
	private final Stack<Integer> maxStack = new Stack<Integer>();
 
 public StackWithMax1() {
	 super();
     maxStack.push(Integer.MIN_VALUE);
 }
 
 public void push(int item) {
     super.push(item);
     if (item >= max()) {
         maxStack.push(item);
     }
 }
 
 @Override
 public Integer pop() {
     int top = super.pop();
     if (top == max()) {
         maxStack.pop();
     }
     return top;
 }
 
 public int max() {
     return maxStack.peek();
 }

}
