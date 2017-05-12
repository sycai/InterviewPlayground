package RecursionAndDynamicProgramming;

/**
 * Created by sycai on 5/12/2017.
 * CCI 8.6 In the classic problem of the Towers of Hanoi, you have 3 towers and N disks of different sizes which
 * can slide onto any tower. The puzzle starts with disks sorted in ascending order of size from top to bottom (i.e.
 * each disk sits on top of an even larger one). You have the following constraints:
 *
 * (1) Only one disk can be moved at a time
 * (2) A disk is slid off the top of one tower onto another tower.
 * (3) A disk cannot be placed on top of a smaller disk.
 *
 * Write a program to move the disks from the first tower to the last using Stacks.
 */

public class Hanoi {
    public static void moveHanoi(int N) {
        moveHanoi(N, 1, 3, 2);
    }

    private static void moveHanoi(int N, int from, int to, int helper) {
        if (N == 1) {
            System.out.println(String.format("Move Disk %d from Tower %d to Tower %d", N, from, to));
        } else {
            moveHanoi(N-1, from ,helper, to);
            System.out.println(String.format("Move Disk %d from Tower %d to Tower %d", N, from, to));
            moveHanoi(N-1, helper, to, from);
        }
    }

    public static void main(String[] args) {
        moveHanoi(4);
    }
}
