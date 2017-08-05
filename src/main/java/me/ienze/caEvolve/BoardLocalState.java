package me.ienze.caEvolve;

import java.util.Iterator;

/**
 * @author ienze.
 */
class BoardLocalState implements Iterator<BoardLocalState> {

    private Board board;
    private int x;
    private int y;

    public int getNumericRepresentation() {
        return 0; //TODO num rep
    }

    public boolean hasNext() {
        return false;
    }

    public BoardLocalState next() {
        return null;
    }

    public void remove() {

    }
}
