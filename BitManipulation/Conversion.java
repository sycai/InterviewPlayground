package BitManipulation;

/**
 * Created by sycai on 4/24/2017.
 * CCI 5.6 Write a function to determine the number of bits you would need to flip to convert integer A to integer B
 * EXAMPLE
 * Input: 29 (or 11101), 15 (or 01111)
 * Output: 2
 */
public class Conversion {
    public static void main(String[] args) {
        int n1 = Integer.parseInt("111010101", 2);
        int n2 = Integer.parseInt("011111010", 2);
        System.out.println(conversion(n1,n2));
    }

    public static int conversion(int a, int b) {
        // Compare two bit strings from the right. If two bits are different, increase the counter by one.
        int res = 0;
        while (a != 0 || b != 0) {
            int a1 = a & 1;
            int b1 = b & 1;
            res += (a1 ^ b1); // XOR operator returns 1 if two operands are different.
            a >>= 1;
            b >>= 1;
        }
        return res;
    }

    public static int conversion2(int a, int b) {
        // This method is provided in the book
        int count = 0;
        for (int c = a ^ b; c != 0; c &= (c-1)) { // c &= (c-1) will clear the least significant bit in c
            count++;
        }
        return count;
    }
}
