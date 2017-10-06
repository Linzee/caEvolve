package me.ienze.caEvolve.fitness;

import me.ienze.caEvolve.Board;
import me.ienze.caEvolve.CA;
import me.ienze.caEvolve.FitnessCalculator;
import me.ienze.twoDimMap.DistinctMapLayer;
import me.ienze.twoDimMap.io.DistinctMapImageReader;

import java.io.File;
import java.io.IOException;

/**
 * @author ienze
 */
public class ImageFitnessCalculator implements FitnessCalculator {

    private DistinctMapLayer expected = null;

    public ImageFitnessCalculator(File file) {

        DistinctMapImageReader dmir = new DistinctMapImageReader();

        try {
            expected = dmir.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public double[] calculateFitnesses(CA[] cas) {

        double[] fitnesses = new double[cas.length];

        for (int i = 0; i < cas.length; i++) {
            CA ca = cas[i];

            Board board = ca.getResultBoard();


            double sum = 0;
            for (int x = 0; x < board.getWidth(); x++) {
                for (int y = 0; y < board.getHeight(); y++) {
                    sum += (board.get(x, y).equals(expected.get(x, y)) ? 0 : 1);
                }
            }

            double fitness = (sum / (board.getWidth() * board.getHeight()));

            fitnesses[i] = fitness;
        }

        return fitnesses;
    }

}
