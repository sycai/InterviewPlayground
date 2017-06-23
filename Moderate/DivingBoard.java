package Moderate;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sycai on 6/22/2017.
 * CCI 16.11 You are building a diving board by placing a bunch of planks of wood end-to-end. There are two types of
 * planks, one of length ${shorter} and one of length ${longer}. You must use exactly k planks of wood. Write a method
 * to generate all possible lengths for the diving board.
 */
public class DivingBoard {
    public static Set<Integer> numLength(int shorter, int longer, int k) {
        Set<Integer> lengths = new HashSet<>();
        int minLength = shorter * k;
        int diff = longer - shorter;
        for (int i = 0; i <= k; i++) {
            lengths.add(minLength + i * diff);
        }
        return lengths;
    }
}
