package Moderate;

/**
 * Created by sycai on 6/11/2017.
 * CCI 16.5 Write an algorithm which computes the number of trailing zeros
 */
public class FactorialZeros {
    public static int trailingZero(int N) {
        int count = 0;
        while (N >= 1) {
            count += N / 5;
            N /= 5;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(FactorialZeros.trailingZero(20));
    }
}
