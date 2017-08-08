package me.ienze.caEvolve;

import java.util.Random;

/**
 * @author ienze
 */
public class CaEvolveSettings {

    public final Random random = new Random();

    public final int stateCount = 3;
    public final int visibleRadius = 1;
    public final int boardWidth = 20;
    public final int boardHeight = 20;
    public final int boardSteps = 8;
    public final int poolSize = 400;

    public final float mutateCaChance = 0.30f;
    public final float mutateGeneChance = 0.30f;

    public int getPossibleStatesCount() {
        return (int) Math.pow(stateCount, (visibleRadius*2+1)*(visibleRadius*2+1));
    }
}
