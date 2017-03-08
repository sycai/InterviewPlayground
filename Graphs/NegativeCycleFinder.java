package Graphs;


import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by sycai on 2/26/2017.
 */
public class NegativeCycleFinder {
    private double[] distTo;
    private Queue<Integer> Q;
    private boolean[] onQ;
    private DirectedEdge[] edgeTo;
    private Deque<DirectedEdge> negCycle;
    private int counter;

    public NegativeCycleFinder(EdgeWeightedDigraph G) {
        distTo = new double[G.V()];
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = 0;
        }
        Q = new LinkedList<>();
        onQ = new boolean[G.V()];
        for (int v  = 0; v < G.V(); v++) {
            Q.add(v);
            onQ[v] = true;
        }
        edgeTo = new DirectedEdge[G.V()];
        while (!Q.isEmpty() && counter < 2 * G.V()) {
            int v = Q.poll();
            onQ[v] = false;
            relax(G, v);
        }

        if (!Q.isEmpty()) { // a negative cycle is found
            negCycle = new LinkedList<>();
            int s = Q.poll();
            for (DirectedEdge e = edgeTo[s]; e.from() != s; e = edgeTo[e.from()]) {
                negCycle.push(e);
            }
            negCycle.add(edgeTo[negCycle.peek().from()]);
        }

    }

    private void relax(EdgeWeightedDigraph G, int v) {
        counter++;
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;

                if (!onQ[w]) {
                    onQ[w] = true;
                    Q.add(w);
                }
            }
        }
    }

    public boolean hasNegCycle() {
        return negCycle != null;
    }

    public Iterable<DirectedEdge> getNegCycle() {
        return negCycle;
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(5);
        G.addEdge(new DirectedEdge(0,4,2));
        G.addEdge(new DirectedEdge(1,2,1));
        G.addEdge(new DirectedEdge(2,3,2));
        G.addEdge(new DirectedEdge(3,1,-3));

        NegativeCycleFinder cf = new NegativeCycleFinder(G);
        System.out.println(cf.getNegCycle());
    }
}
