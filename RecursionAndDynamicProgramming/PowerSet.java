package RecursionAndDynamicProgramming;

import java.util.*;

/**
 * Created by sycai on 5/10/2017.
 * CCI 8.4 Write a method to return all subsets of a set
 */
public class PowerSet {
    public static Set<Set<Integer>> getPowerSet(Set<Integer> set) {
        Set<Set<Integer>> powerSet = new HashSet<>();
        powerSet.add(new HashSet<>());
        for (Integer i : set) {
            Set<Set<Integer>> temp = deepCopy(powerSet);
            for (Set<Integer> s : temp) {
                s.add(i);
            }

            for (Set<Integer> s : temp) {
                powerSet.add(s);
            }
        }
        return powerSet;
    }

    private static Set<Set<Integer>> deepCopy(Set<Set<Integer>> source) {
        Set<Set<Integer>> res = new HashSet<>();
        for (Set<Integer> s : source) {
            Set<Integer> temp = new HashSet<>();
            for (int i : s) {
                temp.add(i);
            }
            res.add(temp);
        }
        return res;
    }

    // The following methods use Combinatorics
    // They are from the book
    public static ArrayList<ArrayList<Integer>> getSubset2(ArrayList<Integer> set) {
        ArrayList<ArrayList<Integer>> allSubsets = new ArrayList<>();
        int max = 1 << set.size();
        for (int k = 0; k < max; k++) {
            ArrayList<Integer> subset = convertIntToSet(k, set);
            allSubsets.add(subset);
        }
        return allSubsets;
    }

    private static ArrayList<Integer> convertIntToSet(int x, ArrayList<Integer> set) {
        ArrayList<Integer> subset = new ArrayList<>();
        int index = 0;
        for (int k = x ; k > 0; k >>= 1) {
            if ((k&1) == 1) subset.add(set.get(index));
            index++;
        }
        return subset;
    }

    public static void main(String[] args) {
        ArrayList<Integer> set = new ArrayList<>(Arrays.asList(1,2,3));
        System.out.println(PowerSet.getSubset2(set));
    }
}
