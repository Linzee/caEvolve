package me.ienze.caEvolve.fitness;

import me.ienze.caEvolve.Board;
import me.ienze.caEvolve.CA;
import me.ienze.caEvolve.FitnessCalculator;

/**
 * @author ienze
 */
public class ChessFitnessCalculator implements FitnessCalculator {

    private int idealSize;

    public ChessFitnessCalculator(int idealSize) {
        this.idealSize = idealSize;
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
                    sum += (board.get(x, y).equals(board.get(x + idealSize, y)) ? 0 : 1);
                }
            }

            double fitness = (sum / (board.getWidth() * board.getHeight()));

            sum = 0;
            for (int x = 0; x < board.getWidth(); x++) {
                for (int y = 0; y < board.getHeight(); y++) {
                    sum += (board.get(x, y).equals(board.get(x, y + idealSize)) ? 0 : 1);
                }
            }

            double fitness2 = (sum / (board.getWidth() * board.getHeight()));
            fitnesses[i] = (fitness + fitness2) / 2;
        }

        return fitnesses;
    }
}
