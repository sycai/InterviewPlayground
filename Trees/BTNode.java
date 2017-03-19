package Trees;

/**
 * Created by sycai on 2/13/2017.
 */
public class BTNode {
    public int val;
    public BTNode left;
    public BTNode right;
    public int size;

    public BTNode(int v) {
        val = v;
        left = null;
        right = null;
        size = 1;
    }

}
