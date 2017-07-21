package Hard;

/**
 * CCI 17.16 A popular masseuse receives a sequence of back-to-back appointment requests and is debating which ones to
 * accept. She needs a 15-minute break between appointments and therefore she cannot accept any adjacent requests. Given
 * a sequence of back-to-back appointment requests (all multiples of 15 minutes, none overlap, and none can be moved),
 * find the optimal (highest total booked minutes) set the masseuse can honor. Return the number of minutes.
 *
 * EXAMPLE
 * Input: {30, 15, 60, 75, 45, 15, 15, 45}
 * Output: 180 minutes ({30, 60, 45, 45})
 */

public class Masseuse {
    public static int optimalTime(int[] A) {
        if (A == null || A.length == 0) return 0;
        if (A.length == 1)              return A[0];

        int[] helper = new int[A.length];
        helper[0] = A[0];
        helper[1] = Math.max(A[0], A[1]);
        int optimal = 0;
        for (int i = 2; i < A.length; i++) {
            helper[i] = Math.max(A[i] + helper[i-2], helper[i-1]);
            optimal = Math.max(optimal, helper[i]);
        }

        return optimal;
    }

    public static void main(String[] args) {
        int[] A = {30, 15, 60, 75, 45, 15, 15, 45};
        System.out.println(optimalTime(A));
    }
}
