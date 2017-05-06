package Graphs.ShortestPath;

import Graphs.Graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by sycai on 5/6/2017.
 */
public class BreadthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s; // source

    public BreadthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        edgeTo[s] = s;
        this.s = s;
        bfs(G,s);
    }

    private void bfs(Graph G, int s) {
        Queue<Integer> queue = new LinkedList<>();
        marked[s] = true;
        queue.add(s);
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    marked[w] = true;
                    queue.add(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        LinkedList<Integer> path = new LinkedList<>();
        for (int w = v; w != s; w = edgeTo[w]) {
            path.addFirst(w);
        }
        path.addFirst(s);
        return path;
    }

    public static void main(String[] args) {
        Graph G = new Graph(5);
        G.addEdge(0,1);
        G.addEdge(0,2);
        G.addEdge(0,4);
        G.addEdge(1,3);
        G.addEdge(2,3);
        G.addEdge(2,4);
        G.addEdge(3,4);

        BreadthFirstPaths bfp = new BreadthFirstPaths(G,0);
        System.out.println(bfp.pathTo(4));
    }
}
