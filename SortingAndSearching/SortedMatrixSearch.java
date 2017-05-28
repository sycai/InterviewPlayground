package SortingAndSearching;

/**
 * Created by sycai on 5/20/2017.
 * CCI 10.9 Given an M * N matrix in which each row and each column is sorted in ascending order, write a method to
 * find an element.
 */
public class SortedMatrixSearch {
    public static boolean search (int[][] M, int target) {
        if (M.length == 0 || M[0].length == 0 || M == null) return false;
        return search(M, target, 0, 0, M.length-1, M[0].length-1);
    }

    private static boolean search(int[][] M, int target, int rowLo, int colLo, int rowHi, int colHi) {
        if (rowLo > rowHi || colLo > colHi)                 return false;
        if (rowLo == rowHi && colLo == colHi)               return M[rowLo][colLo] == target;
        if (rowHi >= M.length || colHi >= M[0].length)      return false;

        int rowMid = rowLo + (rowHi - rowLo) / 2;
        int colMid = colLo + (colHi - colLo) / 2;

        if (target < M[rowMid][colMid]) {
            return search(M, target, rowLo, colLo, rowMid, colMid);
        } else if (target == M[rowMid][colMid]) {
            return true;
        } else if (rowMid + 1 < M.length && colMid + 1 < M[0].length && target >= M[rowMid + 1][colMid + 1]) {
            return search(M, target, rowMid + 1, colMid + 1, rowHi, colHi);
        } else {
            return search(M, target, rowLo, colMid + 1, rowMid, colHi) ||
                    search(M, target, rowMid + 1, colLo, rowHi, colMid);
        }
    }

    public static void main(String[] args) {
        int[][] M = {
                {15,17,20},
                {18,21,26}};

        System.out.println(SortedMatrixSearch.search(M, 26));
    }
}
