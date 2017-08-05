package me.ienze.caEvolve;

import me.ienze.twoDimMap.DistinctMapLayer;

import java.util.Iterator;

/**
 * @author ienze
 */
class Board extends DistinctMapLayer implements Iterable<BoardLocalState> {

    private CaEvolveSettings settings;

    public Board(CaEvolveSettings settings) {
        super(settings.stateCount, settings.boardWidth, settings.boardHeight);
        this.settings = settings;
    }

    void step(CA ca) {
        for (BoardLocalState localState : this) {
            set(localState.getX(), localState.getY(), ca.get(localState));
        }
    }

    @Override
    public Iterator<BoardLocalState> iterator() {
        return new BoardLocalState(this, 0, 0);
    }

    @Override
    public Integer get(int x, int y) {
        return super.get(Math.floorMod(x, getWidth()), Math.floorMod(y, getHeight()));
    }

    @Override
    public void set(int x, int y, Integer value) {
        super.set(Math.floorMod(x, getWidth()), Math.floorMod(y, getHeight()), value);
    }

    public CaEvolveSettings getSettings() {
        return settings;
    }
}
