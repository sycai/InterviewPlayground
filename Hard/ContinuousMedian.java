package Hard;

import java.util.PriorityQueue;

/**
 * CCI 17.20 Numbers are randomly generated and passed to a method. Write a program to find and maintain the median
 * value as new values are generated.
 */

public class ContinuousMedian {
    // Invariant: if there are in total N numbers, there must be N/2 numbers in lowerHalf if N is even, or (N+1)/2
    // numbers in lowerHalf if N is odd.
    private PriorityQueue<Integer> lowerHalf;
    private PriorityQueue<Integer> upperHalf;
    private int totalNum;

    public ContinuousMedian() {
        lowerHalf = new PriorityQueue<>(((o1, o2) -> o2 - o1)); // The largest number sits at the head
        upperHalf = new PriorityQueue<>(); // The smallest number sits at the head
        totalNum = 0;
    }

    public void insert(int v) {
        if (lowerHalf.isEmpty()) {
            lowerHalf.add(v);
        } else {
            if (v <= lowerHalf.peek()) {
                lowerHalf.add(v);
            } else {
                upperHalf.add(v);
            }
        }
        totalNum++;
        rebalance();
    }

    public int getMedian() {
        return lowerHalf.peek();
    }

    private void rebalance() {
        int idealSize = totalNum - totalNum / 2;
        while (lowerHalf.size() < idealSize) {
            lowerHalf.add(upperHalf.poll());
        }
        while (lowerHalf.size() > idealSize) {
            upperHalf.add(lowerHalf.poll());
        }
    }

    public static void main(String[] args) {
        ContinuousMedian cm = new ContinuousMedian();
        int[] stream = {5,2,3,1,2,5,7,4};
        for (int i : stream) {
            cm.insert(i);
            System.out.println(cm.getMedian());
        }
    }
}
