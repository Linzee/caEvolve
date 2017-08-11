package me.ienze.caEvolve.fitness;

import com.sun.xml.internal.ws.util.StreamUtils;
import me.ienze.caEvolve.Board;
import me.ienze.caEvolve.CA;
import me.ienze.caEvolve.CaEvolveSettings;
import me.ienze.caEvolve.FitnessCalculator;

/**
 * @author ienze
 */
public class ConstantRatioFitnessCalculator implements FitnessCalculator {

    private final CaEvolveSettings settings;

    public ConstantRatioFitnessCalculator(CaEvolveSettings settings) {
        this.settings = settings;
    }

    @Override
    public double[] calculateFitnesses(CA[] cas) {

        double[] fitnesses = new double[cas.length];

        for (int i = 0; i < cas.length; i++) {

            CA ca = cas[i];

            double sum = 0;
            Board board = new Board(settings);

            int[] stateCountsBefore = getStateCounts(board);
            int[] stateCountsAfter = null;

            for(int s = 0; s<settings.boardSteps; s++) {

                board.step(ca);

                stateCountsAfter = getStateCounts(board);

                for (int j = 0; j < settings.stateCount; j++) {
                    sum = Math.abs(stateCountsBefore[j] - stateCountsAfter[j]);
                }

                stateCountsBefore = stateCountsAfter;
            }

            double fitness = 1 - Math.pow(1 + (sum / (settings.boardSteps * settings.stateCount * settings.boardWidth * settings.boardHeight)), 0.1);
            fitnesses[i] = fitness;
        }

        return fitnesses;
    }

    private int[] getStateCounts(Board board) {
        int[] stateCounts = new int[settings.stateCount];

        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                stateCounts[board.get(x, y)] += 1;
            }
        }

        return stateCounts;
    }
}
