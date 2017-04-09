package Graphs.FindBuildOrder;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by sycai on 1/20/2017.
 */
public class BuildOrder {
    public static LinkedList<String> findBuildOrder(HashSet<Project> projects) {
        LinkedList<String> order = new LinkedList<>();
        while (order.size() < projects.size()) {
            for (Project p : projects) {
                if (p.getStatus() == Project.Status.UNVISITED) {
                    if (!DFS(p, order)) return null; // Report cycle
                }
            }
        }
        return order;
    }

    private static boolean DFS(Project node, LinkedList<String> order) {
        for (Project child : node.getChildren()) {
            if (child.getStatus() == Project.Status.PROCESSING)
                return false; // There exists a cycle
            if (child.getStatus() == Project.Status.UNVISITED) {
                child.setStatus(Project.Status.PROCESSING);
                if (!DFS(child, order))
                    return false; // Report cycle to the caller
            }
        }
        order.push(node.getName());
        node.setStatus(Project.Status.VISITED);
        return true;
    }
}
