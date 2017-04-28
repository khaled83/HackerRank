package com.amazon.datastructures.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class Graph {

    private int V;
    
    private HashSet<Integer>[] adj;
    
    public Graph(int V) {
        this.V = V;
        adj = (HashSet<Integer>[]) new HashSet[V];
        for (int v = 0; v < V; v++)
            adj[v] = new HashSet<Integer>();
    }
    
    public void addEdge(int v, int w) {
        checkVertex(v);
        checkVertex(w);
        adj[v].add(w);
    }
    
    private void checkVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException();
    }
    
    public int V() { return V; }
    
    public static List<Character> order(char[] values, HashMap<Character, Character> dep) {
        int n = values.length;
        Graph g = new Graph(n);
        for (char c : dep.keySet()) {
            int v = c - 'a';
            int w = dep.get(c) - 'a';
            g.addEdge(v, w);
        }
        
        
        HashSet<Integer> visited = new HashSet<Integer>();
        Stack<Integer> s = new Stack<Integer>();
        for (int v = 0; v < g.V() && s.isEmpty(); v++)
            if (g.adj[v].isEmpty()) {
                s.push(v);
                visited.add(v);
            }
                
        
        /**
        a => 
        b => 
        c => 
        d =>
        e => 
        f => 
        
               stack     w   v    visit
               -----     -   -    -----
        1      c         c         c
        2      cd        d        cd
        3      cda       a        cda
        4      cdab      b        cdab
        5      cdabe     e        cdabe
        6      cdabef    f        cdabef
        
        result = febadc
        
        f ===> a ==>          e
          ===> b ==> d ==> c
        */
        boolean unsolvable = false;
        while (visited.size() < g.V() && !unsolvable) {
            int w = s.peek();
            for (int v = 0; v < g.V(); v++) {
                if (g.adj[v].contains(w)) {
                	g.adj[v].remove(w);
                }
            }
            
            unsolvable = true;
            for (int v = 0; v < g.V(); v++) {
                if (!visited.contains(v) && g.adj[v].isEmpty()) {
                    s.push(v);
                    visited.add(v);
                    unsolvable = false;
                    break;
                }
            }
        }
        
        if (s.size() != n)
            throw new IllegalArgumentException("No solution can be found for the provided inputs");
        
        ArrayList<Character> result = new ArrayList<Character>();
        while (!s.isEmpty()) {
            char c = (char) ('a' + s.pop());
            result.add(c);
        }
        
        return result;
    }
    
}

