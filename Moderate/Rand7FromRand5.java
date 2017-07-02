package Moderate;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by sycai on 7/1/2017.
 * CCI 16.23 Implement a method rand7() given rand5(). That is, given a method that generates a random integer between
 * 0 and 4 (inclusive), write a method that generates a random integer between 0 and 6 (inclusive)
 */
public class Rand7FromRand5 {
    private static Random rand = new Random();
    private static int rand5() {
        return rand.nextInt(5);
    }

    public static int rand7() {
        while (true) {
            // num is uniformly distributed from 0 to 24 (inclusive)
            int num = 5 * rand5() + rand5();
            // The appearance of 21, 22, 23, 24 sightly favors 0, 1, 2, 3 as return value
            // Thus, we need to ignore them.
            if (num < 21)   return num % 7;
        }
    }

    public static void main(String[] args) {
        int[] count1 = new int[7];
        int[] count2 = new int[7];
        for (int i = 0; i < 700000; i++) {
            count1[rand7()]++;
            count2[rand.nextInt(7)]++;
        }
        // Compare our method with the one provided by standard library.
        System.out.println(Arrays.toString(count1));
        System.out.println(Arrays.toString(count2));
    }
}
