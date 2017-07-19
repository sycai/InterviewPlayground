package Hard;


import java.util.Arrays;

/**
 * CCI 17.14 Design an algorithm to find the smallest K numbers in an array
 */

public class SmallestK {
    private int[] A;
    private int k;

    public SmallestK(int[] A, int k) {
        this.A = A;
        this.k = k;
        relocate(A, 0, A.length - 1);
    }


    // Pick a pivot and rearrange the array so that all the elements less than pivot come before it and
    // all those greater than pivot come after it. If the pivot is the kth smallest number, we know that
    // elements at indices from 0 to the pivot's index are smallest K numbers
    private void relocate(int[] A, int start, int stop) {
        if (start >= stop)  return;

        int pivot = A[start];
        int storeIdx = start + 1;
        for (int i = storeIdx; i <= stop; i++) {
            if (A[i] < pivot) {
                swap(i, storeIdx);
                storeIdx++;
            }
        }
        swap(--storeIdx, start);
        // Pivot is the kth smallest
        if      (storeIdx + 1 == k)     return;
        // Pivot is greater than the kth smallest, so search left
        else if (storeIdx + 1 >  k)     relocate(A, start, storeIdx);
        // Pivot is less than the kth smallest, so search right
        else                            relocate(A, storeIdx + 1, stop);

    }

    public int[] getSmallest() {
        return Arrays.copyOf(A, k);
    }

    private void swap(int i, int j) {
        if (i == j) return;
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    public static void main(String[] args) {
        int[] A = {1,4,2,8,5,7,3,0,6,9};
        for (int k = 0; k <= A.length; k++) {
            SmallestK sk = new SmallestK(Arrays.copyOf(A, A.length), k);
            System.out.println(Arrays.toString(sk.getSmallest()));
        }
    }
}
