package Hard;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by sycai on 7/5/2017.
 * CCI 17.4 An array A contains all the integers from 0 to n, except for one number which is missing. In this problem,
 * we cannot access an entire integer in A with a single operation. The elements of A are represented in binary, and
 * the only operation we can use to access them is "fetch the jth bit of A[i]", which takes constant time. Write code
 * to find the missing integer. Can you do it in O(n) time?
 */
public class MissingNumber {
    private static class NastyInt {
        int value;
        NastyInt (int n) {
            this.value = n;
        }

        int getBit(int i) {
            return (value & (1 << i)) >> i;
        }

        @Override
        public String toString() {
            return String.format("%4s", Integer.toBinaryString(value)).replace(' ', '0');
        }
    }

    public static int findMissing(NastyInt[] A) {
        List<NastyInt> list = new LinkedList<>(Arrays.asList(A));
        return findMissing(list, 0, A.length);
    }

    private static int findMissing(List<NastyInt> list, int digit, int n) {
        if ((1<<digit) > n) return 0;

        List<NastyInt> ones  = new LinkedList<>();
        List<NastyInt> zeros = new LinkedList<>();

        for (NastyInt ni : list) {
            if (ni.getBit(digit) == 0)  zeros.add(ni);
            else                        ones.add(ni);
        }

        // Since there is one missing number in this list, we need to add one to list size to get the correct count
        if ((list.size()+1) % 2 == 0) {
            if (zeros.size() - 1 == ones.size())        return (findMissing(ones, digit+1, n) << 1) + 1;
            else if (zeros.size() + 1 == ones.size())   return (findMissing(zeros, digit+1, n) << 1) + 0;
        } else {
            if (zeros.size() - 2 == ones.size())        return (findMissing(ones, digit+1, n) << 1) + 1;
            else if (zeros.size() == ones.size())       return (findMissing(zeros, digit+1, n) << 1) + 0;
        }

        // Notice: if execution ever reaches the last line, the given array must be invalid.
        // However, it is not guaranteed that all invalid arrays can lead execution there.
        throw new IllegalArgumentException("Invalid Array");
    }

    public static void main(String[] args) {
        final int N = 100;
        final int MISSING = 2;
        LinkedList<NastyInt> list = new LinkedList<>();
        for (int i = 0; i <= N; i++) {
           if (i != MISSING)    list.add(new NastyInt(i));
        }

        NastyInt[] A = new NastyInt[N];
        for (int i = 0; i < N; i++) {
            A[i] = list.get(i);
        }
        System.out.println(findMissing(A));
    }
}
