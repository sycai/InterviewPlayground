package Graphs;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by sycai on 2/13/2017.
 */
public class Digraph {
    private final int V;                // number of vertices
    private int E;                      // number of edges
    private ArrayList<Integer>[] adj;   // adjacency lists

    public Digraph(int V) {
        this.V = V; this.E = 0;
        adj = (ArrayList<Integer>[]) new ArrayList[V];
        for (int v = 0; v < V; v++)
            adj[v] = new ArrayList<>();
    }

    public Digraph(EdgeWeightedDigraph G) {
        this(G.V());
        for (DirectedEdge e: G.edges()) {
            addEdge(e.from(), e.to());
        }
    }

    public int V()  { return V; }
    public int E()  { return E; }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public Digraph reverse() {
        Digraph R = new Digraph(V);
        for (int v = 0; v < V; v++)
            for (int w : adj(v))
                R.addEdge(w,v);
        return R;
    }

    private void BFS(int s) {
        boolean[] visited = new boolean[V];
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        visited[s] = true;
        while (!q.isEmpty()) {
            int v = q.poll();
            for (int w : adj(v)) {
                if (!visited[w]) {
                    q.add(w);
                    visited[w] = true;
                }
            }
        }

    }

    public static void main(String[] args) {
        Digraph dg = new Digraph(5);
        dg.addEdge(0,1);
        dg.addEdge(0,3);
        dg.addEdge(1,2);
        dg.addEdge(3,4);
        dg.BFS(0);
    }

}
