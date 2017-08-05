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
        forEach(state -> set(state.getX(), state.getY(), settings.random.nextInt(settings.stateCount)));
    }

    void step(CA ca) {
        forEach(localState -> set(localState.getX(), localState.getY(), ca.get(localState)));
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
