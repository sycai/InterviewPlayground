package Graphs.MaxFlow;

import java.util.LinkedList;

/**
 * Created by sycai on 3/10/2017.
 */
public class FlowNetwork {
    private final int V;
    private int E;
    private LinkedList<FlowEdge>[] adj;

    public FlowNetwork(int V) {
        this.V = V;
        this.E = 0;
        this.adj = (LinkedList<FlowEdge>[]) new LinkedList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(FlowEdge e) {
        int v = e.from();
        int w = e.to();
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public int V()  { return V; }
    public int E()  { return E; }

    public Iterable<FlowEdge> adj(int v) {
        return adj[v];
    }

    public Iterable<FlowEdge> edges() {
        LinkedList<FlowEdge> res = new LinkedList<>();
        for (int v = 0; v < V; v++) {
            for (FlowEdge e : adj(v)) {
                if (e.from() == v)  res.add(e); // We only add edge once
            }
        }
        return res;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (E != 0) {
            for (FlowEdge e : edges()) {
                sb.append(e + "\n");
            }
        }
        return sb.toString();
    }
}
