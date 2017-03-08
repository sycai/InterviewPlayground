package WaterBucket;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by sycai on 2/21/2017.
 */
public class BucketCheck {
    private BucketGroup start;
    private BucketGroup end;
    private LinkedList<BucketGroup> path;
    private HashSet<Integer> vistied;

    public BucketCheck(BucketGroup s, BucketGroup e) {
        start = s;
        end = e;
        vistied = new HashSet<>();
        path = new LinkedList<>();
        // DFS(s, e);
    }

    private boolean DFS(BucketGroup s, BucketGroup e) {
        if (vistied.contains(s.hashCode()))    return false;

        vistied.add(s.hashCode());
        if (s.equals(e)) {
            path.push(s);
            return true;
        }

        for (int i = 0; i < s.getSize(); i++) {
            for (int j = 0; j < s.getSize(); j++) {
                if (i == j) continue;
                BucketGroup temp = new BucketGroup(s);
                temp.pourTo(i, j);
                if (DFS(temp, e)) {
                    path.push(s);
                    return true;
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {
        BucketGroup start = new BucketGroup(3);
        start.set(0,10,0);
        start.set(1,7,7);
        start.set(2,4,4);
        BucketGroup end = new BucketGroup(3);
        end.set(0,10,2);
        end.set(1,7,7);
        end.set(2,4,2);
        BucketCheck bc = new BucketCheck(start, end);
        System.out.println(bc.path);
        System.out.println(bc.vistied.size());
    }

}
