package Graphs.ShortestPath;

import Graphs.DirectedEdge;
import Graphs.EdgeWeightedDigraph;
import Graphs.NegativeCycleFinder;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by sycai on 2/22/2017.
 */
public class BellmanFordSP {
    private double[] distTo;                // length of path to v
    private DirectedEdge[] edgeTo;          // last edge on path to v
    private boolean[] onQ;                  // Is his vertex on the queue
    private Queue<Integer> queue;           // vertices being relaxed
    private int cost;                       // number of calls to relax()
    private Iterable<DirectedEdge> cycle;   // negative cycle in edgeTo[]?

    public BellmanFordSP(EdgeWeightedDigraph G, int s) {
        cost = 1;
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        onQ = new boolean[G.V()];
        queue = new LinkedList<>();
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
        queue.add(s);
        onQ[s] = true;
        while (!queue.isEmpty() && !this.hasNegativeCycle()) {
            int v = queue.poll();
            onQ[v] = false;
            relax(G, v);
        }
    }

    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;

                if (!onQ[w]) {
                    queue.add(w);
                    onQ[w] = true;
                }
            }

            if (cost++ % G.V() == 0) {
                findNegativeCycle(G);
            }
        }
    }

    private void findNegativeCycle(EdgeWeightedDigraph G) {
        int V = edgeTo.length;
        EdgeWeightedDigraph spt;
        spt = new EdgeWeightedDigraph(V);
        for (int v = 0; v < V; v++) {
            if (edgeTo[v] != null) {
                spt.addEdge(edgeTo[v]);
            }
        }
        NegativeCycleFinder cf = new NegativeCycleFinder(G);
        cycle = cf.getNegCycle();
    }

    public boolean hasNegativeCycle() {
        return cycle != null;
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(5);
        G.addEdge(new DirectedEdge(0,4,2));
        G.addEdge(new DirectedEdge(1,2,1));
        G.addEdge(new DirectedEdge(2,3,2));
        G.addEdge(new DirectedEdge(3,1,-4));

        BellmanFordSP bf = new BellmanFordSP(G,0);
        System.out.println(bf.hasNegativeCycle());
    }
}
