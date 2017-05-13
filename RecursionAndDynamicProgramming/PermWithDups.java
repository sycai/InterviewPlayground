package RecursionAndDynamicProgramming;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sycai on 5/13/2017.
 * CCI 8.8 Write a method to compute all permutations of a string whose characters are not necessarily unique. The list
 * of permutations should not have duplicates
 */
public class PermWithDups {
    // The following methods are provided in the book
    public static ArrayList<String> printPerms(String s) {
        ArrayList<String> result = new ArrayList<>();
        HashMap<Character, Integer> map = buildFreqTable(s);
        printPerms(map, "", s.length(), result);
        return result;
    }

    private static HashMap<Character, Integer> buildFreqTable(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }

    private static void printPerms(HashMap<Character, Integer> map,
                                   String prefix,
                                   int remaining,
                                   ArrayList<String> result) {
        // Base case. Permutation has been completed
        if (remaining == 0) {
            result.add(prefix);
        } else {
        // Try remaining letters for next char, and generate remaining permutations
            for (char c : map.keySet()) {
                int count = map.get(c);
                if (count > 0) {
                    map.put(c, count - 1);
                    printPerms(map, prefix + c, remaining - 1, result);
                    map.put(c, count);
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(printPerms("abb"));
    }
}
