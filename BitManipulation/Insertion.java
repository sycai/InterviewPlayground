package BitManipulation;

/**
 * Created by sycai on 4/9/2017.
 * CCI 5.1 You are given two 32-bit numbers, N and M, and two bit positions, i and j. Write a method to insert M into
 * N such that M starts at bit j and ends at bit i. You can assume that the bits j through i have enough space to fit
 * all of M. That is, if M = 10011, you can assume that there are at least 5 bits between j and i. You would not, for
 * example, have j = 3 and i = 2, because M could not fully fit between bit 3 and bit 2.
 */
public class Insertion {
    public static int insert(int N, int M, int i, int j) {
        // In order to have mask of the form 111000111, we can negate the number 000111000.
        // On the other hand, 000111000 can be obtained by shifting 000000111 to the left by i bits.
        // To have 000000111, we shift 1 to the left by (j - i + 1) bits and decrease it by 1.
        int mask = ~(((1 << (j - i + 1)) - 1) << i);
        return (N & mask) | (M << i);
    }

    public static void main(String[] args) {
        int N = Integer.parseInt("10000000000",2);
        int M = Integer.parseInt("10011",2);
        System.out.println(Integer.toBinaryString(insert(N,M,3,7)));
    }
}
