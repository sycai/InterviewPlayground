package SortingAndSearching;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by sycai on 5/17/2017.
 * CCI 10.2 Write a method to sort an array of strings so that all the anagrams are next to each other
 */

public class GroupAnagrams {
    public static void group(String[] anagrams) {
        Arrays.sort(anagrams, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                char[] c1 = o1.toCharArray(), c2 = o2.toCharArray();
                Arrays.sort(c1);
                Arrays.sort(c2);
                String p1 = new String(c1), p2 = new String(c2);
                return p1.compareTo(p2);
            }
        });
    }

    public static void main(String[] args) {
        String[] anagrams = {"acc", "ab", "abc", "ba", "cca", "cab", "cba"};
        GroupAnagrams.group(anagrams);
        System.out.println(Arrays.toString(anagrams));
    }
}
