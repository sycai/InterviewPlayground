package Hard;

import Trees.IndexedTrie;
import java.util.List;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * CCI 17.17 Given a string b and an array of smaller strings T, design a method to search b for each small string in
 * T
 */

public class MultiSearch {
    private IndexedTrie trie;

    public MultiSearch(String s) {
        this.trie = new IndexedTrie();
        for (int i = 0; i < s.length(); i++) {
            trie.insertString(s.substring(i), i);
        }
    }

    public HashMap<String, List<Integer>> searchAll(String[] smalls) {
        HashMap<String, List<Integer>> res = new HashMap<>();
        for (String s : smalls) {
            // Get terminating location of each occurence
            List<Integer> locations = trie.search(s);

            // Adjust to starting location
            if (locations != null) {
               locations = locations.stream().map(x -> x - s.length()).collect(Collectors.toList());
            }
            res.put(s, locations);
        }
        return res;
    }

    public static void main(String[] args) {
        MultiSearch ms = new MultiSearch("mississippi");
        String[] smalls = {"is", "i", "ssi"};
        System.out.println(ms.searchAll(smalls));
    }
}
