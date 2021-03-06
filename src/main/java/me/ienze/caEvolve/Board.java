package me.ienze.caEvolve;

import me.ienze.caEvolve.ca.DeterministicCA;
import me.ienze.twoDimMap.DistinctMapLayer;

import java.util.Iterator;

/**
 * @author ienze
 */
public class Board extends DistinctMapLayer implements Iterable<BoardLocalState> {

    private CaEvolveSettings settings;

    public Board(CaEvolveSettings settings, DeterministicCA ca) {
        super(settings.stateCount, settings.boardWidth, settings.boardHeight);
        this.settings = settings;

        this.settings.boardInitializer.init(this, ca);
    }

    public Board(Board board) {
        super(board);
    }

    public void step(CA ca) {
        Integer[][] newData = new Integer[getWidth()][getHeight()];
        forEach(localState -> newData[localState.getX()][localState.getY()] = ca.get(localState));
        data = newData;
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
