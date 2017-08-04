package Trees;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * This code is from the Code Library of Cracking the Coding Interview, 6th Edition, with some modifications.
 * Note: Nulls are not acceptable in the trie
 */

public class Trie {
    private class TrieNode {
        HashMap<Character, TrieNode> children; // Children of this node
        char c;                                // Stored character. Leaf nodes always have '\0'

        public TrieNode() {
            children = new HashMap<>();
        }

        public TrieNode(char character) {
            this();
            c = character;
        }

        public TrieNode putChildAndGetNode(char c) {
            if (!hasChild(c)) {
                children.put(c, new TrieNode(c));
            }
            return children.get(c);
        }

        public TrieNode getChild(char c) {
            return children.get(c);
        }

        public boolean hasChild(char c) {
            return children.containsKey(c);
        }
    }

    private TrieNode root; // All string validation starts here.

    public Trie() {
        root = new TrieNode();
    }

    public Trie(List<String> list) {
        root = new TrieNode();
        for (String s : list) {
            insert(s);
        }
    }

    public void insert(String s) {
        if (s == null)  throw new IllegalArgumentException("Given string is null");
        insert(root, s);
    }

    private void insert(TrieNode root, String s) {
        if (s.isEmpty()) {
            root.putChildAndGetNode('\0');
        } else {
            TrieNode child = root.putChildAndGetNode(s.charAt(0));
            insert(child, s.substring(1));
        }
    }

    public boolean contains(String s) {
        return contains(root, s);
    }

    private boolean contains(TrieNode root, String s) {
        if (s == null) {
            throw new IllegalArgumentException("Given string is null");
        }

        if (s.isEmpty()) {
            return root.hasChild('\0');
        } else if (root.hasChild(s.charAt(0))){
            return contains(root.getChild(s.charAt(0)), s.substring(1));
        } else {
            return false;
        }
    }

    public boolean containsPrefix(String s) { return containsPrefix(root, s); }

    private boolean containsPrefix(TrieNode root, String s) {
        if (s == null ) {
            throw new IllegalArgumentException("Given string is null");
        }

        if (s.isEmpty()) {
            return true;
        } else if (root.hasChild(s.charAt(0))){
            return containsPrefix(root.getChild(s.charAt(0)), s.substring(1));
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        List<String> words = Arrays.asList("a", "abc", "abd", "");
        Trie t = new Trie(words);
        String[] tests = {"a", "ab", "abc", "abd", "abe", "b"};
        System.out.println("=====Contains Whole Word Test=====");
        for (String test : tests) {
            System.out.println(test + "\t" + t.contains(test));
        }
        System.out.println("=====Contains Prefix Test=====");
        for (String test : tests) {
            System.out.println(test + "\t" + t.containsPrefix(test));
        }
    }
}
