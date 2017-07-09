package Hard;

import javax.naming.Name;
import java.util.*;

/**
 * Created by sycai on 7/8/2017.
 * CCI 17.7 Each year, the government releases a list of the 10000 most common baby names and their frequencies (the
 * number of babies with that name). The only problem with this is that some names have multiple spellings. For example,
 * "John" and "Jon" are essentially the same name but would be listed separately in the list. Given two lists, one of
 * name/frequencies and the other of pairs of equivalent names, write an algorithm to print a new list of the true
 * frequency of each name. Note that if John and Jon are synonyms, and Jon and Johnny are synonyms, then John and
 * Johnny are synonyms. (It is both transitive and symmetric.) In the final list, any name can be used as the "real"
 * name.
 *
 * EXAMPLE
 * Input:
 *   Names: John(15), Jon(12), Chris(13), Kris(4), Christopher(19)
 *   Synonyms: (Jon, John), (John, Johnny), (Chris, Kris), (Chris, Christopher)
 * Output: John(27), Kris(36)
 */
public class BabyNames {
    private static class NameNode {
        private String name;
        private int frequency;

        public NameNode(String n , int freq) {
            name = n;
            frequency = freq;
        }

        public String getName() {return name;}
        public int getFrequency() {return frequency;}

        @Override
        public boolean equals(Object other) {
            if (other instanceof NameNode) {
                NameNode o = (NameNode) other;
                return this.name == o.name && this.frequency == o.frequency;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return name.hashCode() * 37 + frequency;
        }

    }

    private static class NameGraph {
        private Map<NameNode, List<NameNode>> edges;
        private Set<NameNode> nodes;

        public NameGraph() {
            edges = new HashMap<>();
            nodes = new HashSet<>();
        }

        public void addEdge(NameNode node1, NameNode node2) {
            if (edges.containsKey(node1)) {
                edges.get(node1).add(node2);
            } else {
                List<NameNode> endPoints = new LinkedList<>();
                endPoints.add(node2);
                edges.put(node1, endPoints);
            }

            if (edges.containsKey(node2)) {
                edges.get(node2).add(node1);
            } else {
                List<NameNode> endPoints = new LinkedList<>();
                endPoints.add(node1);
                edges.put(node2, endPoints);
            }
        }

        public void addNode(NameNode n) {
            nodes.add(n);
        }

        public List<NameNode> adj(NameNode node) {
            if (edges.containsKey(node))    return edges.get(node);
            else                            return new LinkedList<>();
        }

        public Set<NameNode> nodes() {
            return nodes;
        }
    }

    public static Map<String, Integer> getSanitizedCount(Map<String, Integer> count, String[][] synonyms) {
        Map<String, Integer> res = new HashMap<>();

        NameGraph graph = new NameGraph();
        for (String name : count.keySet()) {
            graph.addNode(new NameNode(name, count.get(name)));
        }
        // two synonyms are connected with an edge in the NameGraph
        for (String[] namePair : synonyms) {
            String name1 = namePair[0];
            NameNode node1 = new NameNode(name1, count.get(name1));
            String name2 = namePair[1];
            NameNode node2 = new NameNode(name2, count.get(name2));
            graph.addEdge(node1, node2);
        }

        // Use DFS to find all equivalent names
        HashSet<NameNode> visited = new HashSet<>();
        for (NameNode node : graph.nodes()) {
            if (!visited.contains(node)) {
                int nameCount = DFS(node, visited, graph);
                res.put(node.getName(), nameCount);
            }
        }
        return res;
    }

    private static int DFS(NameNode node, Set<NameNode> visited, NameGraph graph) {
        if (visited.contains(node)) return 0;

        int count = 0;
        visited.add(node);
        for (NameNode other : graph.adj(node)) {
            count += DFS(other, visited, graph);
        }

        return node.getFrequency() + count;
    }

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("John", 10);
        map.put("Jon", 3);
        map.put("Davis", 2);
        map.put("Kari", 3);
        map.put("Johnny", 11);
        map.put("Carlton", 8);
        map.put("Carleton", 2);
        map.put("Jonathan", 9);
        map.put("Carrie", 5);

        String[][] synonyms = {
                {"Jonathan", "John"},
                {"Jon", "Johnny"},
                {"Johnny", "John"},
                {"Kari", "Carrie"},
                {"Carleton", "Carlton"}
        };
        System.out.println(getSanitizedCount(map, synonyms));
    }
}
