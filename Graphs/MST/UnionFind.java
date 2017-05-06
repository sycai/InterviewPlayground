package Graphs.MST;

import java.util.LinkedList;

/**
 * Created by sycai on 3/29/2017.
 */
public class UnionFind {
    private int[] id;
    private int[] sz;
    private int count;

    public UnionFind(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) id[i] = i;
        sz = new int[N];
        for (int i = 0; i < N; i++) sz[i] = 0;
    }

    public int count() {
        return count;
    }

    public int find(int p) {  // With path compression
        int r = p;
        LinkedList<Integer> ancestors = new LinkedList<>();
        while (id[r] != r) {
            r = id[r];
            ancestors.add(r);
        }
        id[p] = r;
        for (int a : ancestors) {
            id[a] = r;
        }
        return r;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int r1 = find(p), r2 = find(q);
        if (sz[r1] <= sz[r2]) {
            id[r1] = r2;
            sz[r2] += sz[r1];
        } else {
            id[r2] = r1;
            sz[r1] += sz[r2];
        }
        count--;
    }
}
