package me.ienze.caEvolve;

import java.util.Random;

/**
 * @author ienze.
 */
class CaEvolveSettings {

    public final Random random = new Random();

    public final int stateCount = 3;
    public final int visibleRadius = 1;
    public final int boardWidth = 10;
    public final int boardHeight = 10;
    public final int poolSize = 12;

    public int getPossibleStatesCount() {
        return (int) Math.pow(stateCount, (visibleRadius*2+1)*(visibleRadius*2+1));
    }
}
