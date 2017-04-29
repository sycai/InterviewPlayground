package RecursionAndDynamicProgramming;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by sycai on 4/28/2017.
 * CCI 8.2 Imagine a robot sitting on the upper left corner of grid with r rows and c columns. The robot can only move
 * in two directions, right and down, but certain cells are "off limits" such that the robot cannot step on them.
 * Design an algorithm to find a path for the robot from the top left to the bottom right.
 */
public class RobotInAGrid {
    public static void main(String[] args) {
        boolean[][] maze = new boolean[5][9];
        for (int i = 0; i < 6; i++) {
            maze[0][i] = true;
        }

        maze[1][0] = maze[1][5] = true;
        maze[2][0] = maze[2][5] = maze[2][6] = maze[2][7] = maze[2][8] = true;
        maze[3][0] = maze[3][8] = true;
        for (int i = 0; i < 7; i++) {
            maze[4][i] = true;
        }
        maze[4][8] = true;
        System.out.println(getPath(maze));
    }

    // The following codes are given in the book

    public static List<Point> getPath(boolean[][] maze) {
        if (maze == null || maze.length == 0)   return null;
        List<Point> path = new ArrayList<>();
        HashSet<Point> failedPoints = new HashSet<>();
        if (getPath(maze, maze.length-1, maze[0].length-1, path, failedPoints)) {
            return path;
        }
        return null;
    }

    private static boolean getPath(boolean[][] maze, int row, int col, List<Point> path, HashSet<Point> failedPoints) {
        // If out of bounds or not available, return
        if (col < 0 || row < 0 || !maze[row][col]) {
            return false;
        }

        Point p = new Point(row, col);

        // If we have already visited this cell, return
        if (failedPoints.contains(p)) {
            return false;
        }

        boolean isAtOrigin = (row == 0) && (col == 0);

        // If there is a path from start to my current location, add my location
        if (isAtOrigin || getPath(maze, row, col-1, path, failedPoints) ||
                getPath(maze, row-1, col, path, failedPoints)) {
            path.add(p);
            return true;
        }

        failedPoints.add(p);
        return false;
    }
}
