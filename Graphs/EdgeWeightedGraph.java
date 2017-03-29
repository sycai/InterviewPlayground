package Graphs;

import java.util.LinkedList;

/**
 * Created by sycai on 3/28/2017.
 */
public class EdgeWeightedGraph {
    private final int V;
    private int E;
    private LinkedList<Edge>[] adj;

    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (LinkedList<Edge>[]) new LinkedList[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new LinkedList<Edge>();
        }
    }

    public int V() { return V;}
    public int E() { return E;}

    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public Iterable<Edge> edges() {
        LinkedList<Edge> res = new LinkedList<>();
        for (int v = 0; v < V; v++) {
            for (Edge e : adj[v]) {
                if (e.other(v) > v) res.add(e);
            }
        }
        return res;
    }
}
