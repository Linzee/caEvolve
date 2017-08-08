package me.ienze.caEvolve.fitness;

import me.ienze.caEvolve.Board;
import me.ienze.caEvolve.CA;
import me.ienze.caEvolve.FitnessCalculator;

/**
 * @author ienze
 */
public class SolidFitnessCalculator implements FitnessCalculator {

    private int idealColor;

    public SolidFitnessCalculator() {
        this.idealColor = idealColor;
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
                    sum += board.get(x, y) == idealColor ? 1 : 0;
                }
            }

            double fitness = 1 - (sum / (board.getWidth() * board.getHeight()));
            fitnesses[i] = fitness;
        }

        return fitnesses;
    }
}
