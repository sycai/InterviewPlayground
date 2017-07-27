package Hard;

import java.util.PriorityQueue;

/**
 * CCI 17.21 Imagine a histogram (bar graph). Design an algorithm to compute the volume of water it could hold if
 * someone poured water across the top. You can assume that each histogram bar has width 1.
 *
 * EXAMPLE
 * Input: {0, 0, 4, 0, 0, 6, 0, 0, 3, 0, 5, 0, 1, 0, 0, 0}
 *
 *                   |
 *                   | ~ ~ ~ ~ |
 *             | ~ ~ | ~ ~ ~ ~ |
 *             | ~ ~ | ~ ~ | ~ |
 *             | ~ ~ | ~ ~ | ~ |
 *             | ~ ~ | ~ ~ | ~ | ~ |
 *         -------------------------------
 *         0 0 4 0 0 6 0 0 3 0 5 0 1 0 0 0
 *
 * Output: 26
 */

public class VolumeOfHistogram {
    private static class Pair implements Comparable<Pair> {
        int idx;
        int height;

        public Pair(int i, int h) {
            idx = i;
            height = h;
        }

        @Override
        public int compareTo(Pair other) {
            return other.height - this.height;
        }

        @Override
        public String toString() {
            return String.format("pos:%d, ht:%d", idx, height);
        }

    }

    // Continuously pick the tallest bar and the second tallest bar,
    // and compute the water volume between them.
    // O(n^2)
    public static int getVolume(int[] A) {
        if (A == null || A.length == 0) return 0;

        boolean[] filled = new boolean[A.length];
        PriorityQueue<Pair> peaks = new PriorityQueue<>();
        for (int i = 0; i < A.length; i++) {
            if (A[i] != 0)  {
                peaks.add(new Pair(i, A[i]));
            }
        }
        if (peaks.isEmpty())    return 0;

        int V = 0;
        Pair firstPeak = peaks.poll();
        while (!peaks.isEmpty()) {
            Pair nextPeak = peaks.poll();
            int start = Math.min(firstPeak.idx, nextPeak.idx);
            int end = Math.max(firstPeak.idx, nextPeak.idx);
            for (int i = start + 1; i < end; i++) {
                if (!filled[i]) {
                    V += Math.max(nextPeak.height - A[i], 0);
                    filled[i] = true;
                }
            }
            firstPeak = nextPeak;
        }
        return V;
    }

    // Traverse A twice. Then at each index, calculate the water volume by looking to the
    // left tallest bar and right tallest bar.
    // O(n)
    public static int getVolume2(int[] A) {
        if (A == null || A.length == 0) return 0;
        int V = 0;

        int[] tallestLeft = new int[A.length];
        tallestLeft[0] = A[0];
        for (int i = 1; i < A.length; i++) {
           tallestLeft[i] = Math.max(tallestLeft[i-1], A[i]);
        }

        int tallestRight = A[A.length - 1];
        for (int i = A.length - 2; i >= 0; i--) {
            if (A[i] < tallestRight) {
                V += Math.min(tallestLeft[i], tallestRight) - A[i];
            } else {
                tallestRight = A[i];
            }
        }

        return V;
    }

    public static void main(String[] args) {
        int[] A = {5,4,3,2,1};
        System.out.println(getVolume2(A));
    }
}
