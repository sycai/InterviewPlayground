package Moderate;

/**
 * Created by sycai on 6/10/2017.
 * CCI 16.1 Write a function to swap a number in place (that is, without temporary variables).
 */
public class NumberSwapper {

    public static void main(String[] args) {
        int a = 1, b = 2;
        // Swap with arithmetic
        b = a + b;
        a = b - a;
        b = b - a;
        System.out.println(String.format("a = %d, b = %d", a, b));

        int c = 5, d = 7;
        // Swap with bit manipulation
        c = c ^ d;
        d = c ^ d;
        c = c ^ d;
        System.out.println(String.format("c = %d, d = %d", c, d));
    }
}
