package Hard;

import Trees.Trie;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * CCI 17.17 Given a string b and an array of smaller strings T, design a method to search b for each small string in
 * T
 */

public class MultiSearch {
    private Trie trie;

    public MultiSearch(String s) {
        this.trie = new Trie();
        for (int i = 0; i < s.length(); i++) {
            trie.insertString(s.substring(i), i);
        }
    }

    public HashMap<String, ArrayList<Integer>> searchAll(String[] smalls) {
        HashMap<String, ArrayList<Integer>> res = new HashMap<>();
        for (String s : smalls) {
            // Get terminating location of each occurence
            ArrayList<Integer> locations = trie.search(s);

            // Adjust to starting location
            if (locations != null) {
               for (int i = 0; i < locations.size(); i++) {
                   locations.set(i, locations.get(i) - s.length());
               }
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
