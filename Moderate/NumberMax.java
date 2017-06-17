package Moderate;

/**
 * Created by sycai on 6/15/2017.
 * CCI 16.7 Write a method that finds the maximum of two numbers. You should not use if-else or any other comparison
 * operator.
 */
public class NumberMax {
    // returns 0 if n is negative, 1 if n is positive
    private static int signOf(int n) {
        return 1 - ((n >> 31) & 0x1);
    }

    public static int maxOf(int a, int b) {
        int diffSign = signOf(a) ^ signOf(b);
        return diffSign * (signOf(a) * a + signOf(b) * b) + (1 - diffSign) * (signOf(a - b) * a + signOf(b - a) * b);
    }

    public static void main(String[] args) {
        System.out.println(maxOf(1, 0));
    }
}
