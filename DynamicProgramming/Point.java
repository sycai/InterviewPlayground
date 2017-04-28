package DynamicProgramming;

/**
 * Created by sycai on 4/28/2017.
 */
public class Point {
    public int row;
    public int col;
    public Point(int r, int c) {
        row = r;
        col = c;
    }

    public String toString() {
        return String.format("(%d, %d)", row, col);
    }
}
