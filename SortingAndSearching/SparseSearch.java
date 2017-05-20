package SortingAndSearching;

/**
 * Created by sycai on 5/17/2017.
 * CCI 10.5 Given a sorted array of strings that is interspersed with empty strings, write a method to find the
 * location of a give string.
 *
 * EXAMPLE
 * Input: ball, {"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""}
 * Output: 4
 */
public class SparseSearch {
    public static int search(String[] A, String s) {
        return search(A, s, 0, A.length - 1);
    }

    public static int search(String[] A, String s, int lo, int hi) {
        if (lo > hi)    return -1;

        int mid = lo + (hi - lo ) / 2;
        if (A[mid] == "") { // if A[mid] is empty, we find the nearest non-empty string
            int left = mid - 1, right = mid + 1;
            while (left > lo) {
                if (A[left] != "" || A[right] != "")    break;
                left--;
                right++;
            }
            if (A[left] != "")          mid = left;
            else if (A[right] != "")    mid = right;
            else                        return -1;
        }

        if (A[mid].compareTo(s) < 0)        return search(A, s, mid + 1, hi);
        else if (A[mid].compareTo(s) == 0)  return mid;
        else                                return search(A, s, lo, mid);
    }

    public static void main(String[] args) {
        String[] A = {"", "", "", "", "", "", "", "", "", ""};
        System.out.println(SparseSearch.search(A, "car"));
    }
}
