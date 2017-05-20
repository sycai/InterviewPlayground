package SortingAndSearching;

/**
 * Created by sycai on 5/20/2017.
 * CCI 10.8 You have an array with all the numbers from 1 to N, where N is at most 32,000. The array may have duplicate
 * entries and you do not know what N is. With only 4 kilobytes of memory available, how would you print all duplicate
 * elements in the array?
 */

public class FindDuplicates {

    public static void printDup(int[] A) {
        byte[] bitVector = new byte[1 << 12]; // Use 4KB memory to hold all bits

        for (int num : A) {
            int i = num - 1; // Because number starts at 1
            int offset = i % Byte.SIZE;
            int mask = 1 << offset;
            if ((bitVector[i / Byte.SIZE] & mask) == mask) { // This bit has already been set. We've found a duplicate
                System.out.println(num);
            } else { // This is the first time that number i appears
                bitVector[i / Byte.SIZE] |= mask;
            }
        }
    }

    public static void main(String[] args) {
        int[] A = {56,23,23,7,23,1087,32000,31058,31058,6738,1087};
        FindDuplicates.printDup(A);
    }
}
