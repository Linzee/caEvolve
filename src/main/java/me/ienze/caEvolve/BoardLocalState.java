package me.ienze.caEvolve;

import java.util.Iterator;

/**
 * @author ienze
 */
public class BoardLocalState implements Iterator<BoardLocalState> {

    private final Board board;
    private int x;
    private int y;

    public BoardLocalState(Board board) {
        this(board, 0, 0);
    }

    public BoardLocalState(Board board, int x, int y) {
        this.board = board;
        this.x = x;
        this.y = y;
    }

    public boolean hasNext() {
        return y < board.getHeight();
    }

    public BoardLocalState next() {
        BoardLocalState tmp = new BoardLocalState(board, x, y);

        x++;
        if (x >= board.getWidth()) {
            x = 0;
            y++;
        }

        return tmp;
    }

    public int getNumericRepresentation() {
        int num = 0, i = 0;

        int visibleRadius = board.getSettings().visibleRadius;

        for (int dx = -visibleRadius; dx <= visibleRadius; dx++) {
            for (int dy = -visibleRadius; dy <= visibleRadius; dy++) {
                int g = board.get(x + dx, y + dy);
                num += (int) Math.pow(board.getSettings().stateCount, i) * g;
                i++;
            }
        }

        return num;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Board getBoard() {
        return board;
    }
}
