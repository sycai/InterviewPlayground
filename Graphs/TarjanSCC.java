package Graphs;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by sycai on 2/13/2017.
 */
public class TarjanSCC {
    private boolean[] marked;
    private int[] id;
    private int[] low;
    private int pre;
    private int count;
    private Deque<Integer> stack;

    public TarjanSCC(Digraph G) {
        marked = new boolean[G.V()];
        stack = new LinkedList<>();
        id = new int[G.V()];
        low = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) dfs(G, v);
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        low[v] = pre++;
        int min = low[v];
        stack.push(v);
        for (int w : G.adj(v)) {
            if (!marked[w]) dfs(G, w);
            if (low[w] < min) min = low[w];
        }
        if (min < low[v]) {
            low[v] = min;
            return;
        }
        int w;
        do {
            w = stack.pop();
            id[w] = count;
            low[w] = G.V();
        } while (w != v);
        count++;
    }

    public int id(int v) {
        return id[v];
    }
}
