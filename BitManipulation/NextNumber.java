package BitManipulation;

/**
 * Created by sycai on 4/9/2017.
 * CCI 5.4 Given a positive integer, print the next smallest and the next largest number that have the same number of
 * 1 bits in their binary representation.
 */

public class NextNumber {
    public static void main(String[] args) {
        int num = Integer.parseInt("110110110", 2);
        System.out.println(Integer.toBinaryString(getNext(num)));
        System.out.println(Integer.toBinaryString(getPrev(num)));
    }

    private static int getNext(int n) {
        // To get the next smallest integer, we need to find the first non-trailing zero after c1 ones,
        // flip it to one, and add (c1-1) ones from the leftmost.
        int temp = n;
        int c0 = 0, c1 = 0;

        while ((temp & 1) == 0 && temp != 0) {
            c0++;
            temp >>= 1;
        }

        while ((temp & 1) == 1) {
            c1++;
            temp >>= 1;
        }

        if (c0 + c1 == 31 || c0 + c1 == 0 /*temp == 0*/)  return -1;
        n |= (1 << (c0 + c1)); // flip the first non-trailing zero to one
        n &= ~((1 << (c0 + c1)) - 1); // set all trailing bits to zero
        n |= (1 << (c1 - 1) ) - 1; //
        return n;
    }

    private static int getPrev(int n) {
        // To get the previous largest integer, we need to find the first non-trailing one after c1 ones,
        // flip it to zero, and add(c1 + 1) ones on the right of this bit.
        int temp = n;
        int c0 = 0, c1 = 0;

        while ((temp & 1) == 1) {
            c1++;
            temp >>= 1;
        }

        if (temp == 0) return -1; // binary representation of n does not contain zero, so no solution can be found

        while ((temp & 1) == 0 && temp != 0) {
            c0++;
            temp >>= 1;
        }

        n &= ~((1 << (c0 + c1 + 1)) - 1);
        n |= (((1 << (c1 + 1)) - 1) << (c0 - 1));
        return n;
    }
}
