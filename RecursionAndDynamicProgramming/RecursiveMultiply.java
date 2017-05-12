package RecursionAndDynamicProgramming;

/**
 * Created by sycai on 5/12/2017.
 * CCI 8.5 Write a recursive function to multiply two positive integers without using the * operator. You can use
 * addition, subtraction, and bit shifting, but you should minimize the number of those operations.
 */
public class RecursiveMultiply {
    public static int mult(int a, int b) {
        // Base case
        if (b == 0) return 0;
        if (b == 1) return a;

        // Recursive step
        return mult(a, b&1) + (mult(a, b >>1 ) << 1);
    }

    public static void main(String[] args) {
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                System.out.println(String.format("%d * %d = %d", i, j, mult(i,j)));
            }
        }
    }
}
