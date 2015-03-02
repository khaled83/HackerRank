package com.indeed.khaledabbas.datastructures.queue.animalshelter;

import java.util.*;

public class AnimalShelterQueue
{
    private LinkedList<Cat> cats = new LinkedList<Cat>();
    private LinkedList<Dog> dogs = new LinkedList<Dog>();
    
    private int order = 0;
    
    public void enqueue(Animal e)
    {
        e.setOrder(order++);
        
        if(e instanceof Cat)
            cats.addFirst( (Cat) e);
        else if(e instanceof Dog)
            dogs.addFirst( (Dog) e);
    }
    
    public Cat dequeueCat()
    {
        return cats.removeLast();
    }
    
    public Dog dequeueDog()
    {
        return dogs.removeLast();
    }
    
    public Animal dequeueAny()
    {
        if( dogs.isEmpty())
            return dequeueCat();
        else if( cats.isEmpty() )
            return dequeueDog();

        Dog dogLatest = dogs.getLast();
        Cat catLatest = cats.getLast();
        
        if( catLatest.getOrder() < dogLatest.getOrder() )
            return cats.removeLast();
        else
            return dogs.removeLast();
    }
    

}
