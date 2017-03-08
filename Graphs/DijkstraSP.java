package Graphs;

import Heaps.IndexMinPQ;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by sycai on 2/18/2017.
 */
public class DijkstraSP {
    private double[] distTo;          // distTo[v] = distance of shortest s->v path
    private DirectedEdge[] edgeTo;  // edgeTo[v] = last edge on shortest s->v path
    private int[] numSP;
    private IndexMinPQ<Double> pq;  // priority queue of vertices

    public DijkstraSP(EdgeWeightedDigraph G, int s) {
        for (DirectedEdge e : G.edges()) {
            if (e.weight() < 0)
                throw new IllegalArgumentException("edge " + e + " has negative weight");
        }
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        numSP = new int[G.V()];

        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
            numSP[v] = 0;
        }
        distTo[s] = 0.0;
        numSP[s] = 1;

        // relax vertices in order of distance from s
        pq = new IndexMinPQ<>(G.V());
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (DirectedEdge e : G.adj(v)) {
                relax(e);
            }
        }
    }

    private void relax(DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            numSP[w] = numSP[v];
            if (pq.contains(w))     pq.decreaseKey(w, distTo[w]);
            else                    pq.insert(w, distTo[w]);
        } else if (distTo[w] == distTo[v] + e.weight()) {
            numSP[w] += numSP[v];
        }
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v))  return null;
        Deque<DirectedEdge> path = new LinkedList<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }

    public int numSPTo(int v) {
        return numSP[v];
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(8);
        G.addEdge(new DirectedEdge(0,1,1));
        G.addEdge(new DirectedEdge(1,3,1));
        G.addEdge(new DirectedEdge(3,4,1));
        G.addEdge(new DirectedEdge(0,4,3));
        G.addEdge(new DirectedEdge(0,2,1.5));
        G.addEdge(new DirectedEdge(2,4,1.5));
        G.addEdge(new DirectedEdge(4,6,2));
        G.addEdge(new DirectedEdge(4,5,1));
        G.addEdge(new DirectedEdge(5,7,2));
        G.addEdge(new DirectedEdge(6,7,1));
        DijkstraSP dsp = new DijkstraSP(G,0);
        System.out.println(dsp.numSPTo(7));
    }
}
