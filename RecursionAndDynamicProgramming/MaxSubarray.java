package RecursionAndDynamicProgramming;

/**
 * Created by sycai on 4/30/2017.
 * CLRS Chapter 4.1 Given an array S with real numbers (some may be negative), find indices i, j with i < j such that
 * the sum of values S[i] ... S[j] is maximized
 */
public class MaxSubarray {
    public static void main(String[] args) {
        double[] S = {13,-3,-25,20,-3,-16,-23,18,20,-7,12,-5,-22,15,-4,7};
        System.out.println(findMax(S));
    }

    public static double findMax(double[] S) {
        if (S.length == 0)  return 0;
        return findMax(S, 0, S.length-1);
    }

    private static double findMax(double[] S, int low, int high) {
        if (low == high)    return S[low];

        int mid = low + (high - low) / 2;

        double leftMax = findMax(S, low, mid);
        double rightMax = findMax(S, mid + 1, high);
        double crossMax = findCross(S, low, mid, high);

        return Math.max(leftMax, Math.max(rightMax, crossMax));
    }

    private static double findCross(double[] S, int low, int mid, int high) {
        double leftPartMax = Double.NEGATIVE_INFINITY;
        double leftSumSoFar = 0;
        for (int i = mid; i >= low; i--) {
            leftSumSoFar += S[i];
            if (leftSumSoFar > leftPartMax) leftPartMax = leftSumSoFar;
        }

        double rightPartMax = Double.NEGATIVE_INFINITY;
        double rightSumSoFar = 0;
        if (high > mid) {
            for (int i = mid + 1; i <= high; i++) {
                rightSumSoFar += S[i];
                if (rightSumSoFar > rightPartMax) rightPartMax = rightSumSoFar;
            }
        }

        return leftPartMax + rightPartMax;
    }
}
