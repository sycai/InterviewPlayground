package Hard;

import java.util.*;

/**
 * CCI 17.18 You are given two arrays, one shorter (with all distinct elements) and one longer. Find the shortest
 * subarray in the longer array that contains all the elements in the shorter array. The items can appear in any order
 *
 * EXAMPLE:
 * Input: {1, 5, 9} | {7, 5, 9, 0, 2, 1, 3, 5, 7, 9, 1, 1, 5, 8, 8, 9, 7}
 * Output: [7, 10]
 */


// Suppose shorter array has length m and longer array has length n
public class ShortestSupersequence {
    private HashMap<Integer, List<Integer>> elemToIdx;
    private int[] longer;

    public ShortestSupersequence(int[] shorter, int[] longer) {
        elemToIdx = new HashMap<>();
        this.longer = longer;

        // O(mn) nested loop
        for (int e : shorter) {
            elemToIdx.put(e, new LinkedList<>());
            for (int i = 0; i < longer.length; i++) {
                if (longer[i] == e) {
                   elemToIdx.get(e).add(i);
                }
            }
        }
    }

    public int[] getRes() {
        int[] startEnd = new int[2];
        int minLength = Integer.MAX_VALUE;
        TreeSet<Integer> sorted = new TreeSet<>();
        for (int e : elemToIdx.keySet()) {
            sorted.add(elemToIdx.get(e).remove(0));
        }
        // O(nlogm) loop
        while (true) {
           int start = sorted.pollFirst();
           int end = sorted.last();
           if (end - start < minLength) {
               minLength = end - start;
               startEnd[0] = start;
               startEnd[1] = end;
           }
           if (elemToIdx.get(longer[start]).isEmpty()) {
               break;
           } else {
               sorted.add(elemToIdx.get(longer[start]).remove(0));
           }
        }
        
        return startEnd;
    }
    
    public static void main(String[] args) {
        int[] longer = {7, 5, 9, 0, 2, 1, 3, 5, 7, 9, 1, 1, 5, 8, 8, 9, 7};
        int[] shorter = {1, 5, 9};
        ShortestSupersequence ss = new ShortestSupersequence(shorter, longer);
        System.out.println(Arrays.toString(ss.getRes()));
    }

}
