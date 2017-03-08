package Graphs;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by sycai on 2/22/2017.
 */
public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    private Deque<Integer> cycle;
    private boolean[] onStack;

    public DirectedCycle(Digraph G) {
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) dfs(G, v);
        }
    }

    private void dfs(Digraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (this.hasCycle()) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G,w);
            } else if (onStack[w]) {
                cycle = new LinkedList<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    public static void main(String[] args) {
        Digraph dg = new Digraph(7);
        dg.addEdge(0,1);
        dg.addEdge(1,2);
        dg.addEdge(1,3);
        dg.addEdge(2,6);
        dg.addEdge(3,5);
        dg.addEdge(6,4);
        dg.addEdge(4,2);
        dg.addEdge(5,1);
        DirectedCycle dc = new DirectedCycle(dg);
        for (int v : dc.cycle()) {
            System.out.print(v + " ");
        }
    }
}
