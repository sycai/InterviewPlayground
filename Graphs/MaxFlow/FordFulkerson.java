package Graphs.MaxFlow;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by sycai on 3/10/2017.
 */
public class FordFulkerson {
    private boolean[] marked;
    private FlowEdge[] edgeTo;
    private double maxFlow;

    public FordFulkerson(FlowNetwork G, int s, int t) {
        while (hasAugmentingPath(G, s, t)) {
            double bottleNeck = Double.POSITIVE_INFINITY;
            for (int v = t; v != s; v = edgeTo[v].other(v)) {
                bottleNeck = Math.min(bottleNeck, edgeTo[v].residualCapacityTo(v));
            }

            // Augment flow
            for (int v = t; v != s; v = edgeTo[v].other(v)) {
                edgeTo[v].addResidualFlowTo(v, bottleNeck);
            }

            maxFlow += bottleNeck;
        }
    }

    public double maxFlow()     { return maxFlow; }
    public boolean inCut(int v) { return marked[v];}

    private boolean hasAugmentingPath(FlowNetwork G, int s, int t) {
        marked = new boolean[G.V()];
        edgeTo = new FlowEdge[G.V()];
        Queue<Integer> q = new LinkedList<>();

        marked[s] = true;
        q.add(s);

        while (!q.isEmpty()) {
            int v = q.poll();
            for (FlowEdge e : G.adj(v)) {
                int w = e.other(v);
                if (e.residualCapacityTo(w) > 0 && !marked[w]) {
                    // For every edge to an unmarked vertex (in residual)
                    edgeTo[w] = e;
                    marked[w] = true;
                    q.add(w);
                }
            }
        }
        return marked[t];
    }

    public static void main (String[] args) {
        FlowNetwork G = new FlowNetwork(7);
        G.addEdge(new FlowEdge(0,1,3));
        G.addEdge(new FlowEdge(0,2,3));
        G.addEdge(new FlowEdge(0,3,4));
        G.addEdge(new FlowEdge(1,4,2));
        G.addEdge(new FlowEdge(2,1,10));
        G.addEdge(new FlowEdge(2,4,1));
        G.addEdge(new FlowEdge(3,5,6));
        G.addEdge(new FlowEdge(4,3,1));
        G.addEdge(new FlowEdge(4,5,1));
        G.addEdge(new FlowEdge(4,6,2));
        G.addEdge(new FlowEdge(5,6,5));

        FordFulkerson solution = new FordFulkerson(G, 0,6);
        System.out.println("Max Flow: " + solution.maxFlow() + "\n");
        System.out.println(G);

    }
}
