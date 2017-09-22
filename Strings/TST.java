package Strings;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Ternary search tries.
 * To search, we compare the first character in the key with the character at the root. If it is less, we take the left
 * link; if it is greater, we take the right link; and if it is equal, we take the middle link and move to the next
 * search key character.
 */

public class TST<Value> {
    private Node root;

    private class Node {
        char c;
        Node left, mid ,right;
        Value val;

        @Override
        public String toString() {
            return "" + c;
        }
    }

    public TST() {
        root = new Node();
    }

    public Value get(String key) {
        if (key.isEmpty()) {
            return root.val;
        }
        Node x = get(root, key, 0);
        if (x == null)  return null;
        return x.val;
    }

    private Node get(Node root, String key, int d) {
        if (key.isEmpty())  return this.root;
        if (root == null)   return null;
        char c = key.charAt(d);

        if (c < root.c) {
            return get(root.left, key, d);
        } else if( c > root.c) {
            return get(root.right, key, d);
        } else if (d < key.length() -1 ) {
            return get(root.mid, key, d+1);
        } else {
            return root;
        }
    }

    public void put(String key, Value val) {
        if (key.isEmpty()) {
            root.val = val;
        } else {
            root = put(root, key, val, 0);
        }
    }

    private Node put(Node root, String key, Value val, int d) {
        char c = key.charAt(d);
        if (root == null) {
            root = new Node();
            root.c = c;
        }

        if (c < root.c) {
            root.left = put(root.left, key, val, d);
        } else if (c > root.c) {
            root.right = put(root.right, key, val, d);
        } else if (d < key.length() - 1){
            root.mid = put(root.mid, key, val, d+1);
        } else {
            root.val = val;
        }
        return root;
    }

    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    public Iterable<String> keysWithPrefix(String pre) {
        Node n = get(root, pre, 0);
        Queue<String> q = new LinkedList<>();
        if (n == this.root) {
            collect(n, pre, q);
        } else {
            collect(n.mid, pre, q);
        }
        return q;
    }

    private void collect(Node root, String pre, Queue<String> q) {
        if (root == null)   return;

        if (root.val != null) {
            q.offer(pre + root.c);
        }

        collect(root.left, pre, q);
        collect(root.right, pre, q);
        collect(root.mid, pre+root.c, q);
    }

    public String longestPrefixOf(String s) {
        if (s.isEmpty()) {
            return root.val == null ? null : "";
        }

        int len = search(root.right, s, 0, 0);
        return s.substring(0, len + 1);
    }

    private int search(Node root, String s, int d, int l) {
        if (root == null)       return l;

        char c = s.charAt(d);
        if (c < root.c) {
            return search(root.left, s, d, l);
        } else if (c > root.c) {
            return search(root.right, s, d, l);
        } else if (d < s.length() - 1) {
            if (root.val != null) {
                return search(root.mid, s, d + 1, d);
            } else {
                return search(root.mid, s, d + 1, l);
            }
        } else {
            return d;
        }
    }

    public Iterable<String> keysThatMatch(String pat) {
        Queue<String> q = new LinkedList<>();
        if (pat.isEmpty() && root.val != null) {
            q.offer("");
        } else {
            collect(root.right, "", pat, q);
        }
        return q;
    }

    private void collect(Node root, String pre, String pat, Queue<String> q) {
        int d = pre.length();
        if (root == null) {
            if (d == pat.length()) {
                q.offer(pre);
            }
            return;
        }

        char c = pat.charAt(d);
        if (c == '.') {
            collect(root.left, pre, pat, q);
            collect(root.mid, pre + root.c, pat, q);
            collect(root.right, pre, pat, q);
        } else if ((c == root.c)) {
            collect(root.mid, pre + root.c, pat, q);
        } else if (c < root.c) {
            collect(root.left, pre, pat, q);
        } else {
            collect(root.right, pre, pat, q);
        }
    }
}
