package Trees;

import java.util.ArrayList;
import java.util.HashMap;

public class Trie {
    private class TrieNode {
        private HashMap<Character, TrieNode> children;
        private ArrayList<Integer> indices;

        public TrieNode() {
            children = new HashMap<>();
            indices = new ArrayList<>();
        }

        public void insertString(String s, int index) {
            if (s == null)  return;
            indices.add(index);
            if (s.length() > 0) {
                char value = s.charAt(0);
                TrieNode child = null;
                if (children.containsKey(value)) {
                    child = children.get(value);
                } else {
                    child = new TrieNode();
                    children.put(value, child);
                }

                String remainder = s.substring(1);
                child.insertString(remainder, index + 1);
            } else {
                children.put('\0', null);
            }
        }

        public ArrayList<Integer> search(String s) {
            if (s == null || s.length() == 0)   return indices;

            char first = s.charAt(0);
            if (children.containsKey(first)) {
                return children.get(first).search(s.substring(1));
            } else {
                return null;
            }
        }

        public boolean terminates() {
            return children.containsKey('\0');
        }

        public TrieNode getChild(char c) {
            return children.get(c);
        }
    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public Trie(String s) {
        this();
        root.insertString(s, 0);
    }

    public ArrayList<Integer> search(String s) {
        return new ArrayList<>(root.search(s));
    }

    public void insertString(String str, int loc) {
        root.insertString(str, loc);
    }

    public TrieNode getRoot() {
        return root;
    }

}
