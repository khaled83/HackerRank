package com.indeed.khaledabbas.datastructures.queue.animalshelter;

public abstract class Animal
{
    String name;
    private int order;
    
    public Animal(String name)
    {
        this.name = name;
    }
    
    public int getOrder() { return order; }
    
    public void setOrder(int order) { this.order = order; }
    
    @Override
    public String toString() { return name; }
}


