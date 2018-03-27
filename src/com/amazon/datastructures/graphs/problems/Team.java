package com.amazon.datastructures.graphs.problems;

import java.util.*;

import com.amazon.datastructures.graphs.GraphDirected;

public class Team {

    private int[] heights;
    
    private final int NUM_PLAYERS;
    
    public Team(int[] heights, int numPlayers) {
    		this.heights = heights;
    		NUM_PLAYERS = numPlayers;
    }
    
    public static int teamPhotoMax(List<Team> teams) {
        // sort teams DESC by total height
        Comparator<Team> cmp = new Comparator<Team>() {
            public int compare(Team t1, Team t2) {
                Integer total1 = 0, total2 = 0;
                for (int i = 0; i < t1.NUM_PLAYERS; i++) {
                    total1 += t1.heights[i];
                    total2 += t2.heights[i];
                }
                return total1.compareTo(total2);
            }
        };
        List<Team> teamsSorted = new ArrayList<Team>(teams);
        Collections.sort(teamsSorted, cmp.reversed());
        
        // find valid pairs of teams
        int numTeams = teams.size();
        // team to indx
        HashMap<Team, Integer> teamIndx = new HashMap<Team, Integer>();
        for (int i = 0; i < numTeams; i++) {
        		teamIndx.put(teams.get(i), i);
        }
        // adjacency list graph
        // HashMap<Team, List<Team>> g = new HashMap<Team, List<Team>>();
        GraphDirected g = new GraphDirected(numTeams);
        // start from each team
        for (int src = 0; src < numTeams; src++) {
            Team v = teamsSorted.get(src);
            for (int dst = src + 1; dst < numTeams; dst++) {
                Team w = teamsSorted.get(dst);
                // check whether v can precede w
                if (isValidArrangement(v, w)) {
                    // g.getOrDefault(v, new ArrayList<Team>()).add(w);
                    g.addEdge(teamIndx.get(v), teamIndx.get(w));
                }
            }
        }
        
        return g.longestPath();
    }
    
    public static boolean isValidArrangement(Team t1, Team t2) {
        if (t1.heights.length != t2.heights.length) {
            return false;
        }
        int n = t1.heights.length;
        int total1 = 0, total2 = 0;
        for (int i = 0; i < n; i++) {
            total1 += t1.heights[i];
            total2 += t2.heights[i];
        }
        
        if (total1 <= total2) {
            return false;
        }
        
        int[] heightsSorted1 = new int[n];
        System.arraycopy(t1.heights, 0, heightsSorted1, 0, n);
        int[] heightsSorted2 = new int[n];
        System.arraycopy(t2.heights, 0, heightsSorted2, 0, n);
        for (int i = 0; i < n; i++) {
            if (heightsSorted1[i] <= heightsSorted2[i]) {
                return false;
            }
        }
        
        return true;
    }
}
