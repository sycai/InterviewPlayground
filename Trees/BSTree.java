package Trees;

/**
 * Binary search tree
 */
public class BSTree<T extends Comparable<T>> {
    private Node root;

    private class Node {
        private T val;
        private Node left;
        private Node right;

        public Node(T val) {
            this.val = val;
            left = null;
            right = null;
        }
    }

    public void insert(T elem) {
        root = insert(elem, root);
    }

    private Node insert(T elem, Node root) {
        if (root == null) {
            root = new Node(elem);
        } else if (elem.compareTo(root.val) < 0) {
            root.left = insert(elem, root.left);
        } else if (elem.compareTo(root.val) > 0) {
            root.right = insert(elem, root.right);
        }
        return root;
    }

    public boolean contains(T elem) {
        return contains(elem, root);
    }

    private boolean contains(T elem, Node root) {
        if (root == null) {
            return false;
        } else if (elem.compareTo(root.val) < 0) {
            return contains(elem, root.left);
        } else if (elem.compareTo(root.val) > 0) {
            return contains(elem, root.right);
        } else {
            return true;
        }
    }

    // Find the greatest element less than or equal to the
    // given one
    public T floor(T elem) {
        return floor(elem, root);
    }

    private T floor(T elem, Node root) {
        if (root == null) {
            return null;
        } else if (elem.compareTo(root.val) == 0) {
            return elem;
        } else if (elem.compareTo(root.val) < 0) {
            return floor(elem, root.left);
        } else {
            T res = floor(elem, root.right);
            return res == null ? root.val : res;
        }
    }

    // Find the smallest element greater than or equal to the
    // given one
    public T ceiling(T elem) {
        return ceiling(elem, root);
    }

    private T ceiling(T elem, Node root) {
        if (root == null) {
            return null;
        } else if (elem.compareTo(root.val) == 0) {
            return elem;
        } else if (elem.compareTo(root.val) > 0) {
            return ceiling(elem, root.right);
        } else {
            T res = ceiling(elem, root.left);
            return res == null ? root.val : res;
        }
    }

    // Delete the given element from the BSTree
    public void delete(T elem) {
        root = delete(elem, root);
    }

    private Node delete(T elem, Node root) {
        if (root == null) {
            return null;
        } else if (elem.compareTo(root.val) < 0) {
            root.left = delete(elem, root.left);
        } else if (elem.compareTo(root.val) > 0) {
            root.right = delete(elem, root.right);
        } else {
            if (root.right == null) {
                root = root.left;
            } else {
                root.val = min(root.right);
                root.right = deleteMin(root.right);
            }
        }
        return root;
    }

    private T min(Node root) {
        if (root == null) {
            return null;
        } else if (root.left == null) {
            return root.val;
        } else {
            return min(root.left);
        }
    }

    private Node deleteMin(Node root) {
        if (root == null) {
            return null;
        } else if (root.left == null) {
            return root.right;
        } else {
            root.left = deleteMin(root.left);
            return root;
        }
    }
}
