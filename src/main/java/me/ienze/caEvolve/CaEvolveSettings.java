package me.ienze.caEvolve;

import java.util.Random;

/**
 * @author ienze
 */
public class CaEvolveSettings {

    public final Random random = new Random();

    public final int stateCount = 2;
    public final int visibleRadius = 1;
    public final int boardWidth = 40;
    public final int boardHeight = 40;
    public final int poolSize = 40;

    public final float mutateCaChance = 0.20f;
    public final float mutateGeneChance = 0.20f;

    public int getPossibleStatesCount() {
        return (int) Math.pow(stateCount, (visibleRadius*2+1)*(visibleRadius*2+1));
    }
}
