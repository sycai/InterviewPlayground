package Trees;

import java.util.HashSet;

/**
 * Created by sycai on 3/19/2017.
 * CCI 4.12 You are given a binary tree in which each node contains an integer value (which might be positive or
 * negative). Design an algorithm to count the number of paths that sum to a given value. The path does not need to
 * start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes)
 */
public class PartsSum {

    private static class WrapInt {
        public int val;
        public WrapInt(int v) {
            this.val = v;
        }
    }

    public static int numPath(BTNode root, int sum) {
        return searchPath(root, new HashSet<>(), sum);
    }

    private static int searchPath(BTNode root, HashSet<WrapInt> passValues, int sum) {
        if (root == null)   return 0;
        int count = 0;
        WrapInt newElement = new WrapInt(sum);
        passValues.add(newElement);
        for (WrapInt r : passValues) {
            if (root.val == r.val)  count += 1;
        }

        for (WrapInt r : passValues) {
            r.val -= root.val;
        }
        count += searchPath(root.left, passValues, sum);
        count += searchPath(root.right, passValues, sum);
        // Restore the set to its original state
        for (WrapInt r : passValues) {
            r.val += root.val;
        }
        passValues.remove(newElement);
        return count;
    }
    
    public static void main(String[] args) {
        BTNode n1 = new BTNode(1);
        n1.left = new BTNode(2);
        n1.right = new BTNode(1);
        n1.left.left = new BTNode(2);
        BTNode n2 = new BTNode(2);
        n2.left = new BTNode(2);
        n2.right = new BTNode(1);
        n2.right.right = new BTNode(2);
        BTNode root = new BTNode(2);
        root.left = n1;
        root.right = n2;
        System.out.println(numPath(root, 2));
    }
}
