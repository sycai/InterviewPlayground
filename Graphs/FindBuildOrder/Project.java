package Graphs.FindBuildOrder;

import java.util.ArrayList;

/**
 * Created by sycai on 1/20/2017.
 */
public class Project {
    public enum Status {UNVISITED, PROCESSING, VISITED};
    private ArrayList<Project> children;
    private String name;
    private Status status;

    public Project (String n) {
        children = new ArrayList<>();
        name = n;
        status = Status.UNVISITED;
    }

    public void addChildren (Project node) {
       children.add(node);
    }

    public ArrayList<Project> getChildren () {
        return children;
    }

    public void setStatus(Status stat) {
        status = stat;
    }

    public Status getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }
}

