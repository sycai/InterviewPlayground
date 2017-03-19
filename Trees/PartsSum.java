package Trees;

import java.util.HashMap;
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

    private static int searchPath(BTNode root, HashSet<WrapInt> remainValues, int sum) {
        if (root == null)   return 0;
        int count = 0;
        WrapInt newElement = new WrapInt(sum);
        // a remaining value v_i equals to (targetSum - sum on the path from ancestor i)
        // remainValues is the set of all v_i where i is any ancestor of the current node.
        remainValues.add(newElement);
        for (WrapInt r : remainValues) {
            if (root.val == r.val)  count += 1;
        }

        for (WrapInt r : remainValues) {
            r.val -= root.val;
        }
        count += searchPath(root.left, remainValues, sum);
        count += searchPath(root.right, remainValues, sum);
        // Restore the set to its original state
        for (WrapInt r : remainValues) {
            r.val += root.val;
        }
        remainValues.remove(newElement);
        return count;
    }

    // Second version uses a hash map. This algorithm is given in the book CCI
    public static int countPathWithSum(BTNode root, int targetSum) {
        return countPathWithSum(root, targetSum, 0, new HashMap<>());
    }

    private static int countPathWithSum(BTNode node, int targetSum, int runningSum, HashMap<Integer, Integer> pathCount) {
        if (node == null)   return 0; // Base case;

        // Count paths with sum ending at the current node
        runningSum += node.val;
        int sum = runningSum - targetSum;
        int totalPaths = pathCount.getOrDefault(sum, 0);

        // If runningSum equals targetSum, then one additional path starts at root. Add this path
        if (runningSum == targetSum)    totalPaths++;

        // Increment pathCount, recurse, then decrement pathCount
        incrementHashTable(pathCount, runningSum, 1);
        totalPaths += countPathWithSum(node.left, targetSum, runningSum, pathCount);
        totalPaths += countPathWithSum(node.right, targetSum, runningSum, pathCount);
        incrementHashTable(pathCount, runningSum, -1);
        return totalPaths;
    }

    private static void incrementHashTable(HashMap<Integer, Integer> hashTable, int key, int delta) {
        int newCount = hashTable.getOrDefault(key, 0) + delta;
        if (newCount == 0)  hashTable.remove(key);
        else                hashTable.put(key, newCount);
    }

    public static void main(String[] args) {
        BTNode n1 = new BTNode(1);
        n1.left = new BTNode(2);
        n1.right = new BTNode(1);
        n1.left.left = new BTNode(2);
        BTNode n2 = new BTNode(-2);
        n2.left = new BTNode(2);
        n2.right = new BTNode(-1);
        n2.right.right = new BTNode(2);
        BTNode root = new BTNode(2);
        root.left = n1;
        root.right = n2;

        for (int targetSum = -5; targetSum < 10; targetSum++) {
            System.out.println(targetSum + ": " + numPath(root, targetSum) + " | " + countPathWithSum(root, targetSum));
        }
    }
}
