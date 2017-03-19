package Trees;

import java.util.Random;

/**
 * Created by sycai on 3/19/2017.
 *
 * CCI 4.11 You are implementing a binary tree class from scratch which, in addition to {@code insert}, {@code find}
 * and {@code delete}, has a method {@code getRandomNode()} which returns a random node from the tree.
 * All nodes should be equally likely to be chosen. Design and implement an algorithm for {@code getRandomNode},
 * and explain how you would implement the rest of the methods.
 */
public class RandomNodeTree {
    private BTNode root;
    private Random rand; // Random number generator

    public RandomNodeTree() {
        root = null;
        rand = new Random();
    }

    public int size(BTNode root) {
        if (root == null)   return 0;
        else                return root.size;
    }

    public void insert(int val) { root = insert(root, val);}

    private BTNode insert(BTNode root, int val) {
        if (root == null) { // The tree is empty
            return new BTNode(val);
        }

        if (val <= root.val) { // Binary search tree insert
            root.left = insert(root.left, val);
        } else {
            root.right = insert(root.right, val);
        }
        root.size = size(root.left) + size(root.right) + 1; // update the size of subtree
        return root;
    }


    public BTNode find(int val) { return find(root, val);}

    private BTNode find(BTNode root, int val) {
        if (root == null) {
            return null; // val is not in the tree
        }

        if (root.val == val) {
            return root;
        } else if (val < root.val) {
            return find(root.left, val);
        } else {
            return find(root.right, val);
        }
    }

    private BTNode deleteMin(BTNode root) {
        if (root.left == null)  return root.right;
        root.left = deleteMin(root.left);
        root.size = size(root.left) + size(root.right) + 1;
        return root;
    }

    private BTNode min(BTNode root) {
        if (root.left != null)  return min(root.left);
        else                    return root;
    }

    public void delete(int val) {
        root = delete(root, val);
    }

    private BTNode delete(BTNode root, int val) {
        if (root == null) return null;

        if      (val < root.val) delete(root.left, val);
        else if (val > root.val) delete(root.right, val);
        else {
            if (root.right== null)   return root.left;
            if (root.left == null)   return root.right;
            BTNode temp = root;
            root = min(temp.right);
            root.right = deleteMin(temp.right);
            root.left = temp.left;
        }
        root.size = size(root.left) + size(root.right) + 1;
        return root;
    }

    public BTNode getRandomNode() {
        int idx = rand.nextInt(size(root));
        return getNode(root, idx);
    }

    private BTNode getNode(BTNode root, int idx) {
        if      (idx < size(root.left))     return getNode(root.left, idx);
        else if (idx == size(root.left))    return root;
        else                                return getNode(root.right, idx - size(root.left) - 1);
    }

    /*public static void main(String[] args) {
        RandomNodeTree rnt = new RandomNodeTree();
        int[] vals = {4,3,0,7,1,6,5,2};
        for (int v : vals) {
            rnt.insert(v);
        }

        System.out.println("Find 5: " + rnt.find(5).val);
        rnt.delete(4);
        int[] count = new int[8];
        for (int i = 0; i < 7000; i++) {
            count[rnt.getRandomNode().val] ++;
        }
        for (int j = 0; j < 8; j++) {
            System.out.print(count[j] + " ");
        }
    }*/

}
