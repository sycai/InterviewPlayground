package Sorting;

/**
 * Created by sycai on 5/8/2017.
 */
public class Merge{
    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a,0,a.length-1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo)   return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid+1, hi);
        merge(a,lo,mid,hi);
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        // Copy a[lo..hi] to aux[lo..hi]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        // Merge
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)               a[k] = aux[j++]; // All entries in the first half have been inserted to a
            else if (j > hi)                a[k] = aux[i++]; // All entries in the last half have been inserted to a
            else if (less(aux[j], aux[i]))  a[k] = aux[j++];
            else                            a[k] = aux[i++];
        }
    }

    private static boolean less(Comparable a, Comparable b) {
        return (a.compareTo(b) < 0);
    }

    public static void main(String[] args) {
        Integer a[] = {2,9,2,8,3,0,5,4,7,1,4};
        Merge.sort(a);
        for (Integer i : a) {
            System.out.print(i + "  ");
        }
    }
}
