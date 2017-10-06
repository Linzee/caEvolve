package me.ienze.caEvolve;

import me.ienze.caEvolve.board.OneDotBoardInitializer;
import me.ienze.caEvolve.board.RandomBoardInitializer;
import me.ienze.caEvolve.fitness.*;

import java.io.File;
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
    public int boardSteps = 10;
    public final int poolSize = 20;

    public float mutateCaChance = 0.36f;
    public float mutateGeneChance = 0.20f;

    public final FitnessCalculator fitnessCalculator = new ImageFitnessCalculator(new File("qr.png"));
    public final BoardInitializer boardInitializer = new RandomBoardInitializer(this);

    public int getPossibleStatesCount() {
        return (int) Math.pow(stateCount, (visibleRadius * 2 + 1) * (visibleRadius * 2 + 1));
    }
}
