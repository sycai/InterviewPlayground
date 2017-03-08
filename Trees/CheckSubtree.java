package Trees;

/**
 * Created by sycai on 2/13/2017.
 */
public class CheckSubtree {
    private TreeNode T1;
    private TreeNode T2;

    public CheckSubtree(TreeNode t1, TreeNode t2) {
        int size1 = getSize(t1), size2 = getSize(t2);
        T1 = size1 > size2 ? t1 : t2;
        T2 = size1 > size2 ? t2 : t1;
    }

    private int getSize(TreeNode node) {
        if (node == null)
            return 0;
        else
            return getSize(node.left) + getSize(node.right) + 1;
    }

    public  boolean check() {
        return check(T1, T2);
    }

    private boolean check(TreeNode node1, TreeNode node2) {
        if (node2 == null)
            return true; // Empty tree is a subtree of any tree
        else if (node1 == null)
            return false;
        // node1 and node2 both are not null
        else if (node1.val == node2.val)
            return check(node1.left, node2.left) && check(node1.right, node2.right);
        else
            return check(node1.left, node2) || check(node1.right, node2);

    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.right.left = new TreeNode(4);
        root1.right.right = new TreeNode(5);

        TreeNode root2 = null;

        //TreeNode root2 = new TreeNode(3);
        //root2.left = new TreeNode(4);
        //root2.right = new TreeNode(5);

        CheckSubtree cs = new CheckSubtree(root1, root2);
        System.out.println(cs.check());
    }
}
