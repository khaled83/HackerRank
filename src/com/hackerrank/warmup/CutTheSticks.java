package com.hackerrank.warmup;

import java.util.*;

public class CutTheSticks {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 5 4 4 2 2 8
        // 2 2 4 4 5 8
        
        int n = sc.nextInt();
        Integer[] arr = new Integer[n];
        for(int i=0; i<n; i++)
            arr[i] = sc.nextInt();
        
        Arrays.sort(arr, Collections.reverseOrder());
        LinkedList<Integer> list = new LinkedList<Integer>();
        for(int i=0; i<n; i++)
            list.add(arr[i]);
        
        while(list.size() > 0)
        {
            System.out.println(list.size());
            int smallest = list.getFirst();
            list.decrement(smallest);
        }
        
        sc.close();
    }
    
    static class LinkedList<E>
    {
        
        static class Node<E>
        {
            E element;
            Node<E> next;
            
            Node(E e)
            {
                this.element = e;
            }
        }
        
        private int size = 0;
        private Node<E> head;
        
        int size() { return size; }
        
        void add(E e)
        {
            Node<E> oldHead = head;
            Node<E> newNode = new Node<E>(e);
            newNode.next = oldHead;
            head = newNode;
            size++;
        }
        
        E getFirst()
        {
            return head.element;
        }
        
        @SuppressWarnings("unchecked")
		void decrement(E e)
        {
            Integer value = (Integer)e;
            Node<E> cur = head;
            Node<E> prev = null;
            
            Node<E> lastRemove = null;
            @SuppressWarnings("unused")
			Node<E> lastPrev = null;
            
            while(cur != null)
            {
                Integer curValue = (Integer) cur.element;
                curValue -= value;
                if(curValue > 0)
                    cur.element = (E) curValue;
                else
                {
                    lastRemove = cur;   
                    lastPrev = prev;
                }
                
                prev = cur;
                cur = cur.next;
            }
            
            // remove zero nodes
            cur = head;
            head = lastRemove.next;
            // nullify deleted nodes
            while(cur != head)
            {
                @SuppressWarnings("unused")
				Node<E> del = cur;
                cur = cur.next;
                del = null;
                size--;
            }
        }
        
    }
    
}
