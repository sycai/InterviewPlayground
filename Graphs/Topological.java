package Graphs;

/**
 * Created by sycai on 2/22/2017.
 */
public class Topological {
    private Iterable<Integer> order;

    public Topological(Digraph G) {
        DirectedCycle cyclefinder = new DirectedCycle(G);
        if (!cyclefinder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public boolean isDAG() {
        return order != null;
    }

    public static void main(String[] args) {
        Digraph dg = new Digraph(5);
        dg.addEdge(0,1);
        dg.addEdge(1,3);
        dg.addEdge(0,2);
        dg.addEdge(0,3);
        dg.addEdge(3,4);
        Topological tp = new Topological(dg);
        for (int i : tp.order()) {
            System.out.print(i + " ");
        }

    }
}
