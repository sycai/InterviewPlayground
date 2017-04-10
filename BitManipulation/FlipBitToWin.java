package BitManipulation;

import java.util.LinkedList;

/**
 * Created by sycai on 4/9/2017.
 * CCI 5.3 You have an integer and you can flip exactly one bit from a 0 to a 1. Write code to find the length of the longest
 * sequence of 1s you could create.
 *
 * e.g.
 * Input 1775 (11011101111)
 * Output 8
 */

public class FlipBitToWin {
    public static int flipBitToWin(int v) {
        LinkedList<Integer> segmentLength = new LinkedList<>();
        int lastZero = -1, currentIdx = 0;
        while (v != 0) {
            int firstBit = v % 2;
            if (firstBit == 0) {
                segmentLength.add((currentIdx - lastZero - 1));
                lastZero = currentIdx;
            }
            currentIdx++;
            v >>= 1;
        }

        int maxLength = 0;
        for (int i = 0; i < segmentLength.size() - 1; i++) {
            if (segmentLength.get(i) + segmentLength.get(i+1) + 1 > maxLength)
                maxLength = segmentLength.get(i) + segmentLength.get(i+1) + 1;
        }

        return maxLength;
    }


    // This algorithm went through slight modifications from the one given in the book
    public static int flipBit(int a) {
        /* If all 1s, this is already the longest sequence */
        if (~a == 0)    return Integer.BYTES*8;

        int currentLength = 0;
        int previousLength = 0;
        int maxLength = 1; // We can always have a sequence of at least one 1;

        while (a != 0)  {
            if ((a&1) == 1) {
                currentLength++;
            } else {
                previousLength = currentLength;
                currentLength = 0;
            }
            maxLength = Math.max(previousLength + currentLength + 1, maxLength);
            // Show execution process
            System.out.println(String.format("%15s   prevLen: %d currLen: %d",
                    Integer.toBinaryString(a),
                    previousLength,
                    currentLength));
            a >>>= 1;
        }
        return maxLength;
    }

    public static void main(String[] args) {
        Integer i = Integer.parseInt("11011101111",2);
        System.out.println("The length of the longest sequence: " + flipBit(i));
    }
}
