package me.ienze.caEvolve;

import me.ienze.caEvolve.fitness.*;

import java.util.Random;

/**
 * @author ienze
 */
public class CaEvolveSettings {

    public final Random random = new Random();

    public final int stateCount = 2;
    public final int visibleRadius = 1;
    public final int boardWidth = 20;
    public final int boardHeight = 20;
    public int boardSteps = 20;
    public final int poolSize = 100;

    public float mutateCaChance = 0.30f;
    public float mutateGeneChance = 0.20f;

    public final FitnessCalculator fitnessCalculator = new CombinedFitnessCalculator(new ConstantRatioFitnessCalculator(this), new LargeAreaFitnessCalculator());

    public int getPossibleStatesCount() {
        return (int) Math.pow(stateCount, (visibleRadius * 2 + 1) * (visibleRadius * 2 + 1));
    }
}
