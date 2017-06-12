package Moderate;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by sycai on 6/11/2017.
 * CCI 16.4 Design an algorithm to figure out if someone has won a game of tic-tac-toe on a N*N chess board.
 */
public class TicTacWin {
    // The following method is provided by the book
    // Use iterators to implement the algorithm
    enum Piece {Empty, Red, Blue}

    public static Piece hasWon(Piece[][] board) {
        if (board.length != board[0].length) return Piece.Empty;
        int size = board.length;

        ArrayList<PositionIterator> instructions = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            // Column iteration
            instructions.add(new PositionIterator(new Pos(0, i), 1, 0, size));
            // Row iteration
            instructions.add(new PositionIterator(new Pos(i, 0), 0, 1, size));
        }
        // Diagonal iteration
        instructions.add(new PositionIterator(new Pos(0, 0), 1, 1, size));
        instructions.add(new PositionIterator(new Pos(0, size - 1), 1, -1, size));

        for (PositionIterator iterator : instructions) {
            Piece winner = hasWon(board, iterator);
            if (winner != Piece.Empty) {
                return winner;
            }
        }
        return Piece.Empty;
    }

    private static Piece hasWon(Piece[][] board, PositionIterator iterator) {
        Pos firstPos = iterator.next();
        Piece first = board[firstPos.row][firstPos.column];
        while (iterator.hasNext()) {
            Pos pos = iterator.next();
            if (board[pos.row][pos.column] != first)
                return Piece.Empty;
        }
        return first;
    }
}

class PositionIterator implements Iterator<Pos> {
    private int rowIncrement, colIncrement, size;
    private Pos current;

    public PositionIterator(Pos p, int rowIncrement, int colIncrement, int size) {
        this.rowIncrement = rowIncrement;
        this.colIncrement = colIncrement;
        this.size = size;
        current = new Pos(p.row - rowIncrement, p.column - colIncrement);
    }

    @Override
    public boolean hasNext() {
        return current.row + rowIncrement < size &&
                current.column + colIncrement < size;
    }

    @Override
    public Pos next() {
        current = new Pos(current.row + rowIncrement, current.column + colIncrement);
        return current;
    }

}

class Pos {
    public int row;
    public int column;

    public Pos(int row, int column) {
        this.row = row;
        this.column = column;
    }
}