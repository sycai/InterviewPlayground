package Trees;

/**
 * Created by sycai on 2/13/2017.
 */
public class BTNode {
    public int val;
    public BTNode left;
    public BTNode right;
    private int size;

    public BTNode(int v) {
        val = v;
        left = null;
        right = null;
        size = 1;
    }

    public void insert(int val) {
        insert(this, val);
    }

    private void insert(BTNode node, int val) {
        if (val < node.val) {
            if (node.left == null) {
                BTNode newNode = new BTNode(val);
                node.left = newNode;
            } else {
                insert(node.left, val);
            }
        } else {
            if (node.right == null) {
                BTNode newNode = new BTNode(val);
                node.right = newNode;
            } else {
                insert(node.right, val);
            }
        }
        node.size = node.left.size + node.right.size + 1;
    }

}
