package Hard;


/**
 * Created by sycai on 7/16/2017.
 * CCI 17.12 Consider a simple data structure called BiNode, which has pointers to two other nodes.
 *
 * The data structure BiNode could be used to represent both a binary tree (where node1 is the left node and node2 is
 * the right node) or a doubly linked list (where node1 is the previous node and node2 is the next node). Implement a
 * method to convert a binary search tree (implemented with BiNode) into a doubly linked list. The values should be
 * kept in order and the operation should be performed in place (that is, on the original data structure).
 *
 */
public class BiNodeSearchTree {
    private class BiNode {
        BiNode node1, node2;
        int data;

        public BiNode(int d) {
            node1 = null;
            node2 = null;
            data = d;
        }

        @Override
        public String toString() {
            return String.format("%d", data);
        }
    }

    private BiNode root;

    public BiNodeSearchTree() {
        root = null;
    }

    public BiNodeSearchTree(int[] values) {
        this();
        for (int v : values) {
            insert(v);
        }
    }

    public void insert(int d) {
        root = insert(d, root);
    }

    private BiNode insert(int d, BiNode root) {
        if (root == null) {
            return new BiNode(d);
        }
        else if (d <= root.data) {
            root.node1 = insert(d, root.node1);
        } else {
            root.node2 = insert(d, root.node2);
        }
        return root;
    }

    /**
     * Helper for concatenating two doubly-linked list nodes
     * @param prev
     * @param next
     */

    private void concat(BiNode prev, BiNode next) {
        prev.node2 = next;
        next.node1 = prev;
    }

    /******************************************************************
     * O(nlog n) algorithm                                            *
     ******************************************************************
     */
    // We return the head of the list
    public BiNode toDoublyLinkedList() {
        return transformRight(root);
    }

    // Transform left subtree into list and return its tail
    private BiNode transformLeft(BiNode root) {
        if (root == null)   return null;
        if (root.node1 != null) {
            BiNode tail = transformLeft(root.node1);
            concat(tail, root);
        }
        if (root.node2 != null) {
            BiNode head = transformRight(root.node2);
            concat(root, head);
        }
        BiNode curr = root;
        while (curr.node2 != null) {
            curr = curr.node2;
        }
        return curr;
    }

    // Transform the right subtree into list and return its head
    private BiNode transformRight(BiNode root) {
        if (root == null)   return null;
        if (root.node1 != null) {
            BiNode tail = transformLeft(root.node1);
            concat(tail, root);
        }
        if (root.node2 != null) {
            BiNode head = transformRight(root.node2);
            concat(root, head);
        }
        BiNode curr = root;
        while (curr.node1 != null) {
            curr = curr.node1;
        }
        return curr;
    }

    /******************************************************************
     * O(n) algorithm using circular list                             *
     ******************************************************************
     */

    public BiNode toDoublyLinkedList2() {
        return breakCircle(transformToCircle(root));
    }

    private BiNode breakCircle(BiNode root) {
        root.node1.node2 = null;
        root.node1 = null;
        return root;
    }

    private BiNode transformToCircle(BiNode root) {
        if (root == null)   return null;

        BiNode leftCircle = transformToCircle(root.node1);
        BiNode rightCircle = transformToCircle(root.node2);

        if (leftCircle == null && rightCircle == null) {
            root.node1 = root;
            root.node2 = root;
            return root;
        } else if (leftCircle != null && rightCircle == null) {
            concat(leftCircle.node1,root);
            concat(root, leftCircle);
            return leftCircle;
        } else if (leftCircle == null && rightCircle != null) {
            concat(rightCircle.node1, root);
            concat(root, rightCircle);
            return root;
        } else {
            concat(leftCircle.node1, root);
            concat(rightCircle.node1, leftCircle);
            concat(root, rightCircle);
            return leftCircle;
        }
    }


    public static void main(String[] args) {
        BiNodeSearchTree bst = new BiNodeSearchTree(new int[] {1});
        BiNode head = bst.toDoublyLinkedList2();
        for (BiNode curr = head; curr != null; curr = curr.node2) {
            System.out.print(" " + curr);
        }
    }
}
