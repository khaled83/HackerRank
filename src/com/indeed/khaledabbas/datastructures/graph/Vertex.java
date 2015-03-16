package com.indeed.khaledabbas.datastructures.graph;

public class Vertex implements Comparable<Vertex> {
	
	public final String name;
	
	public Vertex(String name)
	{
		this.name = name;
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	@Override
	public int compareTo(Vertex o)
	{
		return this.name.compareTo(o.name);
	}

}
