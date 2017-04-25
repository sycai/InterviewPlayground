package BitManipulation;

/**
 * Created by sycai on 4/24/2017.
 * Write a program to swap odd and even bits in an integer with as few instructions as possible.
 * e.g. bit 0 and bit 1 are swapped, bit 2 and bit 3 are swapped, and so on.
 */
public class PairwiseSwap {
    public static void main(String[] args) {
        int num = Integer.parseInt("01010101",2);
        System.out.println(Integer.toBinaryString(swap(num)));
        System.out.println(Integer.toBinaryString(swapOddEvenBits(num)));
    }

    public static int swap(int n) {
        // Note : this method is not well-designed. If a binary string's leftmost bit is zero, this algorithm
        // would ignore that zero.
        int rptr = 1;
        int lptr = 2;
        while (lptr <= n) {
            int rbit = n & rptr;
            int lbit = n & lptr;
            if (rbit != 0 && lbit == 0) { // 01 -> 10
                n += rptr;
            } else if (rbit == 0 && lbit != 0) { // 10 -> 01
                n -= rptr;
            }
            rptr<<= 2;
            lptr <<= 2;
        }
        return n;
    }

    public static int swapOddEvenBits(int x) {
        // This method is provided in the book
        // First we mask all odd bits with 101010 then shift them right by 1.
        // Then we mask all even bits with 010101 then shift them left by 1.
        // Finally we merge two values.
        return (((x & 0xaaaaaaaa) >> 1) | ((x & 0x55555555) << 1));
    }
}
