package Moderate;

import java.util.HashSet;

/**
 * Created by sycai on 6/30/2017.
 * CCI 16.22 An ant is sitting on an infinite loop grid of white and black squares. Initially, the grid is all white
 * and the ant faces right. At each step, it does the following:
 * (1) At a white square, flip the color of the square, turn 90 degrees right (clockwise), and move forward one unit.
 * (2) At a black square, flip the color of the square, turn 90 degrees left (counter-clockwise), and move forward one
 * unit.
 * Write a program to simulate the first K moves that the ant makes and print the final board as a grid. Note that you
 * are not provided with the data structure to represent the grid. This is something you must design yourself. The only
 * input to your method is K. You should print the final grid and return nothing. The method signature might be
 * something like void printKMoves(int K).
 */
public class LangtonsAnt {

    public static void printMoves(int K) {
        HashSet<AntPos> blacks = new HashSet<>();
        blacks.add(new AntPos(0,0));
        int direction = 1; // 0 is up, 1 is right, 2 is down, 3 is left
        AntPos pos = new AntPos(0,0);
        for (int count = 0; count < K; count++) {
            AntPos newPos = MoveForward(pos, direction);
            if (blacks.contains(newPos)) {
                blacks.remove(newPos);
                direction = ((direction - 1) % 4 + 4) % 4;
            } else {
                blacks.add(newPos);
                direction = (direction + 1) % 4;
            }
            pos = newPos;
        }
        printGrid(blacks);
    }

    private static AntPos MoveForward(AntPos pos, int direction) {
        AntPos res = new AntPos(pos);
        switch (direction) {
            case 0:     res.r -= 1;    break;
            case 1:     res.c += 1;    break;
            case 2:     res.r += 1;    break;
            case 3:     res.c -= 1;    break;
        }
        return res;
    }

    private static void printGrid(HashSet<AntPos> blacks) {
        int minRow = 0, maxRow = 0, minCol = 0, maxCol = 0;
        for (AntPos pos : blacks) {
            minRow = pos.r < minRow ? pos.r : minRow;
            maxRow = pos.r > maxRow ? pos.r : maxRow;
            minCol = pos.c < minCol ? pos.c : minCol;
            maxCol = pos.c > maxCol ? pos.c : maxCol;
        }
        for (int i = minRow; i <= maxRow; i++) {
            for (int j = minCol; j <= maxCol; j++) {
                if (i == 0 && j == 0)                           System.out.print(" S ");
                else if (blacks.contains(new AntPos(i,j)))      System.out.print(" * ");
                else                                            System.out.print("   ");
            }
            System.out.println();
        }
        System.out.println("===========================================");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 30; i++) {
            printMoves(i);
        }
    }
}

class AntPos {
    public int r;
    public int c;

    public AntPos(int r, int c) {
        this.r = r;
        this.c = c;
    }

    public AntPos(AntPos other) {
        this.r = other.r;
        this.c = other.c;
    }

    @Override
    public int hashCode() {
        return (r * 37) ^ c;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof AntPos) {
            AntPos cast = (AntPos) other;
            if (this.r == cast.r && this.c == cast.c) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return r +", " + c;
    }
}
