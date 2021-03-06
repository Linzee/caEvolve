package me.ienze.caEvolve.fitness;

import me.ienze.caEvolve.Board;
import me.ienze.caEvolve.CA;
import me.ienze.caEvolve.FitnessCalculator;

/**
 * @author ienze
 */
public class NeighborFitnessCalculator implements FitnessCalculator {

    private int idealNeighbors;

    public NeighborFitnessCalculator(int idealNeighbors) {
        this.idealNeighbors = idealNeighbors;
    }

    @Override
    public double[] calculateFitnesses(CA[] cas) {

        double[] fitnesses = new double[cas.length];

        for (int i = 0; i < cas.length; i++) {
            CA ca = cas[i];

            Board board = ca.getResultBoard();

            //calculate fitness
            double sum = 0;
            for (int x = 0; x < board.getWidth(); x++) {
                for (int y = 0; y < board.getHeight(); y++) {
                    int n = 0;

                    for (int dx = -1; dx <= 1; dx++) {
                        for (int dy = -1; dy <= 1; dy++) {
                            if (dx == 0 && dy == 0)
                                continue;

                            n += board.get(x + dx, y + dy);
                        }
                    }

                    sum += Math.abs(n - idealNeighbors);
                }
            }

            double fitness = 1 - (sum / (board.getWidth() * board.getHeight() * 8));
            fitnesses[i] = fitness;
        }

        return fitnesses;
    }
}
