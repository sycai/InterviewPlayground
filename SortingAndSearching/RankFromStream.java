package SortingAndSearching;

/**
 * Created by sycai on 5/29/2017.
 * CCI 10.10 Imagine you are reading in a stream of integers. Periodically, you wish to be able to look up the rank of
 * of a number x (the number of values less than or equal to x). Implement the data structures and algorithms to
 * support these operations. That is, implement the method "track(int x)", which is called when each number is
 * generated, and the method "getRankOfNumber(int x)", which returns the number of values less than or equal to x
 * (not including x itself).
 *
 * EXAMPLE
 * Stream (in order of appearance): 5, 1, 4, 4, 5, 9, 7, 13, 3
 * getRankOfNumber(1) = 0
 * getRankOfNumber(3) = 1
 * getRankOfNumber(4) = 3
 */
public class RankFromStream {
    // We implement a binary search tree
    private class TreeNode {
        int val;
        int leftSize;
        TreeNode left;
        TreeNode right;


        public TreeNode(int v) {
            val = v;
            leftSize = 0;
            left = null;
            right = null;
        }
    }

    TreeNode root;

    public RankFromStream() {
        root = null;
    }

    public void track(int x) {
        root = insert(x, root);
    }

    public int getRankOfNumber(int x) {
        return searchAndSum(x, root);
    }

    private TreeNode insert(int x, TreeNode root) {
        if (root == null) {
            root = new TreeNode(x);
        } else if (x <= root.val) {
            root.left = insert(x, root.left);
            root.leftSize += 1;
        } else {
            root.right = insert(x, root.right);
        }
        return root;

    }

    private int searchAndSum(int x, TreeNode root) {
        // x does not appear in the stream
        if (root == null) {
            return -1;
        }
        // If x is at the root, then the size of its left subtree is the rank
        else if (x == root.val) {
            return root.leftSize;
        }
        // If x is smaller than the root, keep searching
        else if (x <  root.val) {
            int result = searchAndSum(x, root.left);
            if (result != -1)   return result;
            else                return -1;
        }
        // If x is larger than the root, we know that all the elements on the left subtree is smaller than x.
        // Also, root.val is smaller than x. We need to count them while we recurse deeper into the tree.
        else {
            int result = searchAndSum(x, root.right);
            if (result != -1)   return root.leftSize + 1 + result;
            else                return -1;
        }
    }

    public static void main(String[] args) {
        RankFromStream rfs = new RankFromStream();
        int[] A = {5,1,4,4,5,9,7,13,3};
        for (int a : A) {
            rfs.track(a);
        }
        System.out.println(rfs.getRankOfNumber(1));
        System.out.println(rfs.getRankOfNumber(3));
        System.out.println(rfs.getRankOfNumber(4));
        System.out.println(rfs.getRankOfNumber(2));
    }

}
