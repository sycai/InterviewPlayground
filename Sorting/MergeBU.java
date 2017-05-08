package Sorting;

/**
 * Created by sycai on 5/8/2017.
 */
public class MergeBU {
    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        int N = a.length;
        aux = new Comparable[N];
        for (int sz = 1; sz < N; sz = sz + sz) {
            for (int lo = 0; lo < N - sz; lo += sz + sz) {
                merge(a,lo,lo+sz-1, Math.min(lo+sz+sz-1,N-1));
            }
        }
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        if (hi <= lo)   return;
        int i = lo, j = mid + 1;

        // Copy a[lo..hi] to aux[lo..hi]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        for (int k = lo; k <= hi; k++) {
            if      (i > mid)                   a[k] = aux[j++];
            else if (j > hi )                   a[k] = aux[i++];
            else if (less(aux[j], aux[i]))      a[k] = aux[j++];
            else                                a[k] = aux[i++];
        }
    }

    private static boolean less(Comparable a, Comparable b) {
        return (a.compareTo(b) < 0);
    }

    public static void main(String[] args) {
        Integer a[] = {1,4,2,8,5,7};
        MergeBU.sort(a);
        for (Integer i : a) {
            System.out.print(i + " ");
        }
    }
}
