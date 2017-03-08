package Graphs;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by sycai on 2/20/2017.
 */
public class DFS {
    private int[] prenum;
    private int[] postnum;
    private int counter = 1; // for prenum and postnum
    private LinkedList<DirectedEdge> backEdges;
    private LinkedList<DirectedEdge> crossEdges;
    private LinkedList<DirectedEdge> forwardEdges;

    public DFS(EdgeWeightedDigraph G, int s) {
        prenum = new int[G.V()];
        for (int i = 0; i < G.V(); i++) {
            prenum[i] = -1; // initial state - unvisited
        }
        postnum = new int[G.V()];
        for (int i = 0; i < G.V(); i++) {
            postnum[i] = -1; // initial state - unpoped
        }
        backEdges = new LinkedList<>();
        crossEdges = new LinkedList<>();
        forwardEdges = new LinkedList<>();
        DFSearch(G, s);
    }

    private void DFSearch(EdgeWeightedDigraph G, int u) {
        prenum[u] = counter++;
        for (DirectedEdge e : G.adj(u)) {
            int v = e.to();
            if (prenum[v] == -1) {// unvisited
                DFSearch(G, v);
            } else if (prenum[u] > prenum[v] && postnum[v] == -1) { // a back edge is found
                backEdges.add(e);
            } else if (prenum[u] > postnum[v]) { // a cross edge is found
                crossEdges.add(e);
            } else if (prenum[u] < prenum[v]) { // a forward edge is found
                forwardEdges.add(e);
            }
        }
        postnum[u] = counter++;
    }

    public List<DirectedEdge> getBackEdges() {
        LinkedList<DirectedEdge> res = new LinkedList<>();
        res.addAll(backEdges);
        return res;
    }

    public List<DirectedEdge> getCrossEdges() {
        LinkedList<DirectedEdge> res = new LinkedList<>();
        res.addAll(crossEdges);
        return res;
    }

    public List<DirectedEdge> getForwardEdges() {
        LinkedList<DirectedEdge> res = new LinkedList<>();
        res.addAll(forwardEdges);
        return res;
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(5);
        G.addEdge(new DirectedEdge(0,2,1));
        G.addEdge(new DirectedEdge(0,1,1));
        G.addEdge(new DirectedEdge(1,2,1));
        G.addEdge(new DirectedEdge(2,3,1));
        G.addEdge(new DirectedEdge(2,4,1));
        G.addEdge(new DirectedEdge(3,0,1));
        G.addEdge(new DirectedEdge(4,1,1));
        DFS res = new DFS(G,0);
        System.out.println("Back: " + res.getBackEdges());
        System.out.println("Cross: " + res.getCrossEdges());
        System.out.println("Forward: " + res.getForwardEdges());
    }
}
