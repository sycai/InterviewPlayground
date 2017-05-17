package SortingAndSearching;

import java.util.Random;

/**
 * Created by sycai on 5/8/2017.
 */
public class Quick {
    public static void sort(Comparable[] a) {
        shuffle(a);
        sort(a, 0, a.length-1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo)   return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    private static void shuffle(Comparable[] a) {
        Random rand = new Random();
        for (int i = 0; i < a.length; i++) {
            int j = i + rand.nextInt(a.length-i);
            swap(a,i,j);
        }
    }

    private static void swap(Comparable[] a, int i, int j) {
        if (i == j) return;
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static boolean less(Comparable a, Comparable b) {
        return (a.compareTo(b) < 0);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int store = lo + 1;
        Comparable pivot = a[lo];
        for (int search = lo + 1; search <= hi; search++) {
            if (less(a[search], pivot)) {
                swap(a, store, search);
                store++;
            }
        }
        swap(a, lo, --store);
        return store;
    }

    public static void main(String[] args) {
        Integer[] a = {2,1};
        Quick.sort(a);
        for (Integer i : a) {
            System.out.print(i + "  ");
        }
    }
}
