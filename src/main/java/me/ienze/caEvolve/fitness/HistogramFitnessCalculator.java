package me.ienze.caEvolve.fitness;

import me.ienze.caEvolve.Board;
import me.ienze.caEvolve.CA;
import me.ienze.caEvolve.FitnessCalculator;

import java.util.Arrays;
import java.util.PrimitiveIterator;

/**
 * @author ienze
 */
public class HistogramFitnessCalculator implements FitnessCalculator {

    private final double[] idealHistogram;

    public HistogramFitnessCalculator(double[] idealHistogram) {
        this.idealHistogram = idealHistogram;
    }

    @Override
    public double[] calculateFitnesses(CA[] cas) {

        double[] fitnesses = new double[cas.length];

        for (int i = 0; i < cas.length; i++) {

            CA ca = cas[i];

            Board board = ca.getResultBoard();

            double[] boardHistogram = new double[idealHistogram.length];

            for (int x = 0; x < board.getWidth(); x++) {
                for (int y = 0; y < board.getHeight(); y++) {
                    boardHistogram[board.get(x, y)] += 1;
                }
            }

            //normalize
            double sum = Arrays.stream(boardHistogram).sum();
            PrimitiveIterator.OfDouble boardHistogramNormal = Arrays.stream(boardHistogram).map(d -> d / sum).iterator();

            //distance
            double fitness = 1;
            for (double ih : idealHistogram) {
                fitness -= Math.abs(boardHistogramNormal.next() - ih);
            }

            fitnesses[i] = fitness;
        }

        return fitnesses;
    }
}
